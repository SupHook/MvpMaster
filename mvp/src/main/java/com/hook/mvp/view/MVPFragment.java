package com.hook.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.hook.mvp.factory.PresenterFactory;
import com.hook.mvp.factory.PresenterFactoryImpl;
import com.hook.mvp.presenter.BasePresenter;
import com.hook.mvp.proxy.MVPProxy;
import com.hook.mvp.proxy.PresenterProxy;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/3 15:48
 * 组织：Vanson
 * 描述：
 */
public abstract class MVPFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment implements PresenterProxy<V, P>
{
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    @SuppressWarnings("unchecked")
    private MVPProxy<V, P> proxy = new MVPProxy<>(PresenterFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.e("MVP", "V onCreate");
        Log.e("MVP", "V onCreate proxy = " + proxy);
        Log.e("MVP", "V onCreate this = " + this.hashCode());
        if (savedInstanceState != null)
        {
            proxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.e("MVP", "V onResume");
        proxy.onResume((V) this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.e("MVP", "V onDestroy");
        proxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY, proxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterFactory<V, P> presenterFactory)
    {
        proxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterFactory<V, P> getPresenterFactory()
    {
        return proxy.getPresenterFactory();
    }

    @Override
    public P getPresenter()
    {
        return proxy.getPresenter();
    }
}
