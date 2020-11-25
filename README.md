# SOPT 27th Assignment

## Preview

<div>
    
</div>

## 내용

### 1주차 과제 - 필수

```Kotlin
class SignUpFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(
            inflater,
            R.layout.fragment_sign_up,
            container,
            false
        )
        setObserver()

        binding.signUpOkBtn.setOnClickListener {
            if (isValidate(binding)) {
                viewModel.setUser(
                    User(
                        binding.signUpNameEdtxt.text.toString(),
                        binding.signUpEmailEdt.text.toString(),
                        binding.signUpPassEdt.text.toString()
                    )
                )
                showToast("회원가입 성공")
                findNavController().popBackStack()
            } else
                showToast("회원가입 실패, 빈칸 없이 작성해주세요")
        }
        return binding.root
    }

    private fun isValidate(binding: FragmentSignUpBinding): Boolean {
        return binding.signUpEmailEdt.text.isNotBlank() && binding.signUpNameEdtxt.text.isNotBlank() && binding.signUpPassEdt.text.isNotBlank()
    }

    private fun setObserver() {
        viewModel.user.observe(viewLifecycleOwner) {
            viewModel.signUp()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}


Activity를 하나만 사용하고 fragment를 사용해 보다 앱을 가볍게 사용할 수 있도록 설계했습니다. 또한 6주차 과제였던 Retrofit을 이용해 서버와의 통신을 할 수 있도록 설계하였습니다. 
```

### 1주차 과제 - 성장과제 1

``` kotlin
class SignInFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentSignInBinding>(
            inflater,
            R.layout.fragment_sign_in,
            container,
            false
        )
        val loginController = LoginController(Injection.provideLoginDataStore(requireContext()))
        binding.mainviewmodel = viewModel

        autoLogin(loginController)
        setObserver(loginController)

        binding.loginBtn.setOnClickListener {
            if (isValidate(binding)) {
                viewModel.signIn(
                    SignInUser(
                        binding.mainEmailEdt.text.toString(),
                        binding.mainPassEdt.text.toString()
                    )
                )
            } else {
                showToast("로그인 실패")
            }
        }

        binding.signUpTxt.setOnClickListener {
            moveToSignUp()
        }

        return binding.root
    }

    private fun setObserver(loginController: LoginController) {
        viewModel.userData.observe(viewLifecycleOwner){
            if(it.email.isNotBlank() && it.password.isNotBlank() && it.userName.isNotBlank()) {
                loginController.setAutoLogin()
                loginController.saveUserData(it)
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
            }
        }
    }

    private fun autoLogin(loginController: LoginController) {
        if (loginController.getAutoLogin()) {
            showToast("자동로그인")
            viewModel.setUserData(loginController.fetchUserData())
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
        }
    }

    private fun isValidate(binding: FragmentSignInBinding): Boolean {
        return binding.mainEmailEdt.text.isNotBlank() && binding.mainPassEdt.text.isNotBlank()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun moveToSignUp(){
        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }

}
Databinding을 통해 ViewModel의 user데이터를 editText의 Text로 들어갈 수 있도록 하였습니다.
```

### 1주차 과제 - 성장과제1

``` kotlin
<SignInFragment.kt>

private fun setObserver(loginController: LoginController) {
        viewModel.userData.observe(viewLifecycleOwner){
            if(it.email.isNotBlank() && it.password.isNotBlank() && it.userName.isNotBlank()) {
                loginController.setAutoLogin()
                loginController.saveUserData(it)
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
            }
        }
    }

    private fun autoLogin(loginController: LoginController) {
        if (loginController.getAutoLogin()) {
            showToast("자동로그인")
            viewModel.setUserData(loginController.fetchUserData())
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
        }
    }
자동로그인은 기존에 로그인 버튼을 누를 경우, 서버와 통신해 로그인을 수행하고, response 값으로 받은 데이터를 SharedPreference에 저장합니다.
<LoginController.kt>

class LoginController(
    private val LoginDataStore: SharedPreferences
) : OnControlled {
    private val KEY = MyKeyStore.provideAutoLoginKey()

    override fun setAutoLogin() {
        LoginDataStore.edit().putBoolean(KEY, true).apply()
    }

    override fun saveUserData(userData: UserData) {
        LoginDataStore.edit()
            .putString("userName",userData.userName)
            .putString("userEmail",userData.email)
            .putString("userPassword",userData.password)
            .apply()
    }

    override fun fetchUserData(): UserData {
        return UserData(
            LoginDataStore.getString("userEmail",null).toString(),
            LoginDataStore.getString("userPassword",null).toString(),
            LoginDataStore.getString("userName",null).toString()
        )
    }

    override fun getAutoLogin(): Boolean {
        return LoginDataStore.getBoolean(KEY, false)
    }

}
이를 통해 autoLogin이 true로 설정되어있을 때 SharedPreference에 있는 user 데이터를 가져와 자동로그인이 수행됩니다.
```

### 2주차 과제 - 필수

```kotlin
class ProfileAdapter(
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    var data = mutableListOf<ProfileData>()
    
    class ProfileViewHolder(val binding: ProfileItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProfileData, clickListener: OnItemClickListener) {
            binding.apply {
                profileData = data
                executePendingBindings()
            }
            binding.root.setOnClickListener {
                clickListener.onItemClicked(data)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProfileItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.profile_item, parent, false)
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int
    ) {
        holder.bind(data[position], itemClickListener)
    }

    fun moveItem(from: Int, to: Int): Boolean {
        val tempData = data.get(from)
        data.removeAt(from)
        data.add(to, tempData)
        notifyItemMoved(from, to)
        notifyItemChanged(from, to)
        return true
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}

interface OnItemClickListener {
    fun onItemClicked(profileData: ProfileData)
}
DataBinding을 이용해 recyclerview Adapter를 구현했습니다. 상세화면으로 가는 이벤트는 interface를 선언해 리사이클러뷰가 보여지는 UserListFragment.kt에서 구현하도록 하였습니다.

<UserListFragment.kt>

    override fun onItemClicked(profileData: ProfileData) {
        findNavController().navigate(R.id.detailFragment)
    }
```

### 2주차 과제 - 성장 과제2

```kotlin
<UserListFragment.kt> 
private fun changeLayoutManager() {
        if (binding.homeRecyclerView.layoutManager is GridLayoutManager) {
            binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.homeRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

버튼을 누르면 recyclerview의 layoutManager를 변경할 수 있도록 하였습니다.
```

### 2주차 과제 - 성장 과제2

```kotlin
    private fun setTouchHelper(recyclerView: RecyclerView) {
        val itemTouchHelper = androidx.recyclerview.widget.ItemTouchHelper(object :
            androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(
                androidx.recyclerview.widget.ItemTouchHelper.UP or androidx.recyclerview.widget.ItemTouchHelper.DOWN,
                androidx.recyclerview.widget.ItemTouchHelper.START or androidx.recyclerview.widget.ItemTouchHelper.END
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return profileAdapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                profileAdapter.removeItem(viewHolder.adapterPosition)
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if (actionState == androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG) {
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
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

ItemTouchHelper를 구현해 이를 리사이클러뷰에 적용할 수 있도록 했습니다.
```

### 3주차 과제 - 필수 과제

```kotlin
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        mainViewPagerAdapter = MainViewPagerAdapter(parentFragmentManager)
        setViewpager()
        addViewPagerListener()
        setBottomNavigation()
        return binding.root
    }

    private fun addViewPagerListener() {
        binding.viewpagerMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.bottomNavigationMain.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })
    }

    private fun setBottomNavigation() {
        binding.bottomNavigationMain.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when (it.itemId) {
                R.id.menu_home -> index = 0
                R.id.menu_User -> index = 1
                R.id.menu_setting -> index = 2
            }
            binding.viewpagerMain.currentItem = index
            true
        }
    }

    private fun setViewpager() {
        binding.viewpagerMain.apply {
            adapter = mainViewPagerAdapter
        }
    }
    
세미나에서 배웠던 코드를 참고해 구현하였습니다. 
```

### 6주차 과제 - 필수 과제

```kotlin
<RetrofitBuilder>
private fun getInstance(BaseUrl : String) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val retrofitService : RetrofitService = getInstance(MyKeyStore.provideSignBaseUrl()).create(RetrofitService::class.java)

<RetrofitService>
    // 로그인
    @Headers("Content-Type:application/json")
    @POST("users/signin")
    suspend fun postSignIn(
        @Body  signInUser: SignInUser
    ) : UserInfo

    // 회원가입
    @Headers("Content-Type:application/json")
    @POST("users/signup")
    suspend fun postSignUp(
        @Body user: User
    ) : UserInfo

<MainRepository.kt>
    suspend fun signUp(user : User) = retrofitService.postSignUp(user)

    suspend fun signIn(signInUser: SignInUser) : UserInfo = retrofitService.postSignIn(signInUser)

<MainViewModel.kt>
fun signIn(signInUser: SignInUser) = viewModelScope.launch{
        try{
            _userData.value = repository.signIn(signInUser).userData
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun signUp() = viewModelScope.launch{
        _user.value?.let {
            try{
                repository.signUp(it)
            } catch (e : NullPointerException){
                e.printStackTrace()
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

Retrofit을 이용해 서버와의 통신을 수행할 수 있도록 하였습니다.
ViewModel과 Repository패턴을 이용하였습니다.

```



