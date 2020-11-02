

# HKsAssignment

## Preview
<div>
<img src="https://github.com/SSong-develop/HKsAssignment/blob/master/1%EC%A3%BC%EC%B0%A8-%EA%B3%BC%EC%A0%9C-%EB%B0%8F-%EC%84%B1%EC%9E%A5%EA%B3%BC%EC%A0%9C.gif" width="300" height="650" />
<img src="https://github.com/SSong-develop/HKsAssignment/blob/master/2%EC%A3%BC%EC%B0%A8-%EA%B3%BC%EC%A0%9C-%EB%B0%8F-%EC%84%B1%EC%9E%A5%EA%B3%BC%EC%A0%9C.gif" width="300" height="650" />
</div>

##  제목2(#2개)

``` Using Code

```

##  내용

### 1차 과제 - 필수

```kotlin
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
```

### 1차 과제 - 성장과제1

``` <SignUpActivity.kt>
fun regist(){
        autoLogin.edit().putString("name",binding.signUpNameEdtxt.text.toString()).commit()
        autoLogin.edit().putString("id",binding.signUpIdEdt.text.toString()).commit()
        autoLogin.edit().putString("password",binding.signUpPassEdt.text.toString()).commit()
    }

SharedPreference에 값을 넣어 회원가입을 하고 이를 다른 액티비티에서도 꺼내어 확인 할 수 있도록 했습니다.
이 또한 goMain() 함수에서 validate 과정을 거친 후 수행하도록 하였습니다.
추후, Room으로 리팩토링하여 활용하기 쉽도록 구현할 예정입니다.
```

### 1차 과제 - 성장과제2

``` <MainActivity.kt>
// 자동로그인 함수
private fun auto(sharedPreferences: SharedPreferences) {
        if(sharedPreferences.getBoolean("autoKey",false) == true){
            Toast.makeText(this,"자동로그인",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

// 일단은 자동로그인 함수
    fun init() {
        autoLogin = getSharedPreferences("autoLogin1", MODE_PRIVATE)
        auto(autoLogin)
    }

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java) // change ktx
        binding.mainviewmodel = viewModel
        binding.activity = this

        init()
    }

기존에 회원가입을 한 후에 다시 앱을 실행했을 떄 먼저 init() 함수를 실행 해 값이 있는 지 없는지를 확인한 후 있는 경우 자동로그인 할 수 있도록 구현했습니다.
```

### 2차 과제 - 필수

``` <ProfileAdapter.kt>
class ProfileViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : ProfileData, listener : View.OnClickListener){
            itemView.title_txt.text = data.title
            itemView.subtitle_txt.text = data.subTitle
            itemView.setOnClickListener(listener)
        }
    }

override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int
    ) {
        val listener = View.OnClickListener {
            val activity = context as HomeActivity
            activity.detailPage(data[position])
        }
        holder.bind(data[position],listener)
    }

기존 ViewHolder 클래스에 clickListener bind함수의 매개변수로 추가해 itemClick을 구현했습니다.
클릭을 구현하는 여러가지의 방법이 있는데 저의 경우 clickListener 또한 ViewHolder에서 bind시 같이 되어야 할 것 같다는 생각에 listener를 추가해야한다는 생각으로 구현했습니다.

<Detail Activity.kt>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent = intent
        title = intent.getStringExtra("title").toString()
        subTitle = intent.getStringExtra("subtitle").toString()
        date = intent.getStringExtra("date").toString()
        detail = intent.getStringExtra("detail").toString()

        detail_title.text = title
        detail_subtitle.text = subTitle
        detail_date.text = date
        detail_txt.text = detail
    }
디테일 액티비티에서는 이름을 제외한 데이터는 간략한 더미데이터를 통해 상세화면을 보여주도록 했습니다.
```

### 2차 과제 - 성장과제1

``` <HomeActivity.kt>
act_btn.setOnClickListener {
            homeRecyclerView.apply {
                if(isChecked == false){
                    layoutManager = GridLayoutManager(this@HomeActivity,2)
                    isChecked = true
                } else {
                    layoutManager = LinearLayoutManager(this@HomeActivity)
                    isChecked = false
                }
            }
        }
FloatingButton이 클릭되면 boolean 값에 따라 gridLayoutManager와 LinearLayoutManager를 적용해줌으로써 layoutManager를 변경할 수 있도록 구현했습니다.
```

### 2차 과제 - 성장과제2

```<HomeActivity.kt>
// TouchHelper
        val itemTouchHelper = ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(UP or DOWN , START or END){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return profileAdapter.moveItem(viewHolder.adapterPosition,target.adapterPosition)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                profileAdapter.removeItem(viewHolder.adapterPosition)
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if(actionState == ACTION_STATE_DRAG){
                    viewHolder?.itemView?.setBackgroundColor(Color.LTGRAY)
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.setBackgroundColor(Color.WHITE)
            }
        })
        itemTouchHelper.attachToRecyclerView(homeRecyclerView)

SimpleCallback을 통해 touchHelper를 구현했습니다.
액티비티내에서 선언해서 사용함으로 리팩토링이 필요해 따로 클래스를 만들어 사용할 예정입니다.
```

