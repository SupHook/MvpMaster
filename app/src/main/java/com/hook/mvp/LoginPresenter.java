package com.hook.mvp;

import com.hook.mvp.presenter.BasePresenter;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/3 16:31
 * 组织：Vanson
 * 描述：
 */
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
