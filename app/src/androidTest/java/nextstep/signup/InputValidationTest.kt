package nextstep.signup

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import nextstep.signup.ui.component.EmailTextField
import nextstep.signup.ui.component.InputErrorText
import nextstep.signup.ui.component.PasswordConfirmTextField
import nextstep.signup.ui.component.PasswordTextField
import nextstep.signup.ui.component.UserNameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    var context: Context = ApplicationProvider.getApplicationContext()

    private val userName = mutableStateOf("")
    private val email = mutableStateOf("")
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    private val userNameValid = mutableStateOf(false)
    private val emailValid = mutableStateOf(false)
    private val passwordValid = mutableStateOf(false)
    private val passwordConfirmValid = mutableStateOf(false)

    @Before
    fun setup() {
        composeTestRule.setContent {
            Column {
                Column {
                    UserNameTextField(text = userName.value,
                        onTextValueChange = { userName.value = it })

                    userNameValid.value =
                        userName.value.matches(Regex(stringResource(id = R.string.regex_user_name)))

                    if (userName.value.isNotEmpty()) {
                        InputErrorText(
                            stringResource(id = R.string.err_msg_user_name),
                            userNameValid.value
                        )
                    }
                }

                Column {
                    EmailTextField(text = email.value, onTextValueChange = { email.value = it })

                    emailValid.value =
                        email.value.matches(Regex(stringResource(id = R.string.regex_email)))

                    if (email.value.isNotEmpty()) {
                        InputErrorText(
                            stringResource(id = R.string.err_msg_email),
                            emailValid.value
                        )
                    }
                }

                Column {
                    PasswordTextField(text = password.value,
                        onTextValueChange = { password.value = it })

                    passwordValid.value =
                        password.value.matches(Regex(stringResource(id = R.string.regex_password)))

                    if (password.value.isNotEmpty()) {
                        InputErrorText(
                            stringResource(id = R.string.err_msg_password),
                            passwordValid.value
                        )
                    }
                }

                Column {
                    PasswordConfirmTextField(
                        text = passwordConfirm.value,
                        onTextValueChange = { passwordConfirm.value = it },
                        password = password.value
                    )

                    passwordConfirmValid.value = password.value == passwordConfirm.value

                    if (passwordConfirm.value.isNotEmpty()) {
                        InputErrorText(
                            stringResource(id = R.string.err_msg_password_confirm),
                            passwordConfirmValid.value
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    onClick = { },
                    enabled = userNameValid.value && emailValid.value && passwordValid.value && passwordConfirmValid.value
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_up),
                        modifier = Modifier.padding(
                            top = 16.dp,
                            bottom = 16.dp,
                            start = 24.dp,
                            end = 24.dp
                        )
                    )
                }
            }
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        userName.value = "김컴포즈"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_user_name))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        userName.value = "김컴포즈입니다"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_user_name)).assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // when
        email.value = "oyj7677@gmail"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_email)).assertExists()
    }

    @Test
    fun 이메일_형식이_올바르다면_에러메시지가_노출되지_않는다() {
        // when
        email.value = "oyj7677@gmail.com"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_email))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호는_8에서_16자이고_영문_숫자_조합이어야_한다() {
        // when
        password.value = "12345678dd"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // when
        password.value = "12345678901234567"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password)).assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함하지_않는다면_에러메시지가_노출된다() {
        // when
        password.value = "12345678"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password)).assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // when
        password.value = "12345678@"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password)).assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치하지_않는다면_에러메시지가_노출된다() {
        // when
        password.value = "12345678dd"
        passwordConfirm.value = "12345678dd!"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.err_msg_password_confirm))
            .assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치한다면_에러메시지가_노출되지_않는다() {
        // when
        password.value = "12345678dd"
        passwordConfirm.value = "12345678dd"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.err_msg_password_confirm))
            .assertDoesNotExist()
    }

    @Test
    fun 모든_입력항목의_유효성_검사가_통과될_때_회원가입_버튼이_활성화된다() {
        // when
        userName.value = "김컴포즈"
        email.value = "oyj7677@gmail.com"
        password.value = "12345678dd"
        passwordConfirm.value = "12345678dd"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up))
            .assertIsEnabled()
    }

    @Test
    fun 입력항목중_하나라도_통과하지_못한다면_회원가입_버튼은_비활성화된다() {
        // when : 이메일 형식이 올바르지 않은 경우
        userName.value = "김컴포즈"
        email.value = "oyj7677@gmail"
        password.value = "12345678dd"
        passwordConfirm.value = "12345678dd"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up))
            .assertIsNotEnabled()
    }
}