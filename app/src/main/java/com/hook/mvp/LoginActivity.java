package com.hook.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hook.mvp.view.MVPActivity;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/3 16:43
 * 组织：Vanson
 * 描述：
 */
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
