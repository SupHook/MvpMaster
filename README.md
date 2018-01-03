# MvpMaster
#### 集成到项目中
**Step 1**. Add the JitPack repository to your build file
    
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
**Step 2**. Add the dependency
    
    dependencies {
	        compile 'com.github.SupHook:MvpMaster:1.0'
	}
  
#### 如何使用
##### Presenter继承**BasePresenter**
```Java
    public class LoginPresenter extends BasePresenter<LoginView>
    {
        private LoginService service;
    
        public LoginPresenter()
        {
            this.service = new LoginService();
        }
    
        public void startRequest()
        {
            if (getMVPView() != null)
            {
                getMVPView().requestLoading();
            }
            /**
             * 模拟网络请求
             */
            service.request(new LoginCallback()
            {
                @Override
                public void onResponse()
                {
                    if (getMVPView() != null)
                        getMVPView().requestSuccess();
                }
    
                @Override
                public void onFailed()
                {
                    if (getMVPView() != null)
                        getMVPView().requestFailed();
                }
            });
        }
    }
 ```

##### View继承**BaseView**
```java
public interface LoginView extends BaseView
{
    void requestFailed();

    void requestSuccess();

    void requestLoading();
}

```
##### Activity继承**MVPActivity**,实现View层（Fragment用法相同）
```java
   public class LoginActivity extends MVPActivity<LoginView, LoginPresenter> implements LoginView
    {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
        }
    
        @Override
        protected void onResume()
        {
            super.onResume();
            //此处不能再onResume之前调用
            getPresenter().startRequest();
        }
    
        @Override
        public void requestFailed()
        {
    
        }
    
        @Override
        public void requestSuccess()
        {
    
        }
    
        @Override
        public void requestLoading()
        {
    
        }
    }
```    

