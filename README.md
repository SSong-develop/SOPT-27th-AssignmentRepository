# HKsAssignment
## Preview
<div>
<img src="https://github.com/SSong-develop/HKsAssignment/blob/master/1%EC%A3%BC%EC%B0%A8-%EA%B3%BC%EC%A0%9C-%EB%B0%8F-%EC%84%B1%EC%9E%A5%EA%B3%BC%EC%A0%9C.gif" width="300" height="650" />
<img src="https://github.com/SSong-develop/HKsAssignment/blob/master/2%EC%A3%BC%EC%B0%A8-%EA%B3%BC%EC%A0%9C-%EB%B0%8F-%EC%84%B1%EC%9E%A5%EA%B3%BC%EC%A0%9C.gif" width="300" height="650" />
</div>
## 내용
``` 
<SignUpActivity.kt>
fun sendIntent(){
        val newIntent = Intent()
        newIntent.putExtra("name",binding.signUpNameEdtxt.text.toString())
        newIntent.putExtra("id",binding.signUpIdEdt.text.toString())
        newIntent.putExtra("password",binding.signUpPassEdt.text.toString())
        Toast.makeText(this,"회원가입 성공",Toast.LENGTH_SHORT).show()
        setResult(RESULT_OK,newIntent)
        finish()
    }

fun goMain(view : View){
        if(viewModel.isValidate(binding.signUpNameEdtxt.text.toString(),
                binding.signUpIdEdt.text.toString(),
                binding.signUpPassEdt.text.toString())){
            regist()
            sendIntent()
        }
        else {
            Toast.makeText(this,"공백없이 전부 적어주세요",Toast.LENGTH_SHORT).show()
        }
    }

SignUpActivity에서 회원가입을 하게 되면 Toast메시지와 함께 intent를 통해 name , id , password를 보내도록 하여 그 다음 로그인 화면에 회원가입 했던 값이 보여지도록 했습니다. 
또한 sendIntent 함수를 호출하는 조건으으로 모든 editText부분에 기입이 완료 되었는지를 확인할 수 있도록 했습니다.
기본적으로 Main과 SignUp 에는 ViewModel을 적용해 기타 데이터들을 보관할 수 있도록 구현했습니다.
