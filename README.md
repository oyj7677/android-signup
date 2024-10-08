# Step 2 - 회원가입(뷰)

## 기능 요구 사항

1. 디자인 시안을 참고하여 회원가입 뷰를 구현한다.

## 프로그래밍 요구 사항
1. ViewModel, Hilt 등은 회원가입 미션에서 활용하지 않는다. 컴포즈 학습에 집중하자.
2. 사용자 입력 및 유효성 검사에 대해서는 이 단계에서 고민하지 않아도 된다.
3. 컴포저블 함수가 너무 많은 일을 하지 않도록 분리하기 위해 노력해 본다.
4. Material3 Button, TextField를 활용한다.

# 기능 목록
- [O] 웰컴 타이틀 구현
- [O] 유저 이름 TextField 구현
- [O] 이메일 TextField 구현
- [O] 비밀번호 TextField 구현
- [O] 비밀번호 확인 TextField 구현
- [O] TextField 함수 그룹화
- [O] 회원가입 버튼 구현

# Step 3 - 회원가입(유효성 검사)

## 기능 요구 사항

1. 디자인 시안을 참고하여 회원가입 뷰에 유효성 검사 로직을 추가한다.

## 프로그래밍 요구 사항
1. 유효성 검사 로직에 대한 테스트 코드를 추가한다.

# 기능 목록
- [O] 유저 이름 TextField 컴포넌트 생성
- [O] 유저 이름 TextField 유효성 검사
- [O] 이메일 TextField 컴포넌트 생성
- [O] 이메일 TextField 유효성 검사
- [O] 비밀번호 TextField 컴포넌트 생성
- [O] 비밀번호 TextField 유효성 검사
- [O] 비밀번호 확인 TextField 컴포넌트 생성
- [O] 비밀번호 확인 TextField 유효성 검사

# Step 4 - 회원가입(리팩터링)

## 기능 요구 사항
1. 디자인 시안을 참고하여 모든 필드가 에러 없이 채워진 경우에만 Sign up 버튼을 활성화한다.
2. (선택사항) Sign up 버튼을 클릭하면 회원가입 완료 스낵바가 노출된다.

## 프로그래밍 요구 사항
1. 유효성 검사 로직과 뷰 로직을 나누어 관심사를 분리한다.
2. 모든 로직에 테스트 코드를 추가한다.
   - 테스트 가능한 부분과 테스트하기 힘든 부분을 분리해 테스트 가능한 부분에 대해서만 테스트를 진행한다.


# 기능 목록
- [O] 모든 필드가 에러 없이 채워진 경우에만 Sign up 버튼을 활성화
- [O] Sign up 버튼을 클릭하면 회원가입 완료 스낵바가 노출

