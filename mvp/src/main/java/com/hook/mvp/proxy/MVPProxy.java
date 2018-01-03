package com.hook.mvp.proxy;

import android.os.Bundle;
import android.util.Log;

import com.hook.mvp.factory.PresenterFactory;
import com.hook.mvp.presenter.BasePresenter;
import com.hook.mvp.view.BaseView;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/2 21:18
 * 组织：Vanson
 * 描述：代理-管理Presenter生命周期以及view之间的关联
 */
public class MVPProxy<V extends BaseView, P extends BasePresenter<V>> implements PresenterProxy<V, P>
{

    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String PRESENTER_KEY = "presenter_key";

    private PresenterFactory<V, P> factory;
    private P presenter;
    private Bundle bundle;
    private boolean isAttachView;

    public MVPProxy(PresenterFactory<V, P> factory)
    {
        this.factory = factory;
    }

    /**
     * 设置工厂方法，此方法智能在getPresenter()之前调用
     *
     * @param presenterFactory presenter工厂
     */
    @Override
    public void setPresenterFactory(PresenterFactory<V, P> presenterFactory)
    {
        if (presenterFactory != null)
        {
            throw new IllegalArgumentException("这个方法只能在getPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }

        this.factory = presenterFactory;
    }

    /**
     * 获取Presenter工厂类
     *
     * @return
     */
    @Override
    public PresenterFactory<V, P> getPresenterFactory()
    {
        return factory;
    }

    /**
     * 获取Presenter
     *
     * @return
     */
    @Override
    public P getPresenter()
    {
        Log.e("MVP", "Proxy getPresenter");
        if (factory != null)
        {
            if (presenter == null)
            {
                presenter = factory.createPresenter();
                presenter.onCreatePresenter(bundle != null ? null : bundle.getBundle(PRESENTER_KEY));
            }
        }
        return presenter;
    }

    /**
     * 绑定Presenter和View
     *
     * @param view
     */
    public void onResume(V view)
    {
        getPresenter();
        Log.e("MVP", "Proxy onResume");
        if (presenter != null && !isAttachView)
        {
            presenter.onAttachMVPView(view);
            isAttachView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView()
    {
        Log.e("MVP", "Proxy onDetachMvpView");
        if (presenter != null && isAttachView)
        {
            presenter.onDetachMVPView();
            isAttachView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy()
    {
        Log.e("MVP", "Proxy onDestroy");
        if (presenter != null)
        {
            onDetachMvpView();
            presenter.onDestroyPresenter();
            presenter = null;
        }
    }

    /**
     * 意外销毁时调用
     *
     * @return Bundle 存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState()
    {
        Log.e("MVP", "Proxy onSaveInstanceState");
        Bundle bundle = new Bundle();
        getPresenter();
        if (presenter != null)
        {
            Bundle presenterBundle = new Bundle();
            //回调
            presenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
        }

        return bundle;
    }

    /**
     * 意外关闭恢复Presenter
     *
     * @param saveInstanceState
     */
    public void onRestoreInstanceState(Bundle saveInstanceState)
    {
        Log.e("MVP", "Proxy onRestoreInstanceState");
        Log.e("MVP", "Proxy onRestoreInstanceState Presenter =" + presenter);
        bundle = saveInstanceState;
    }
}
