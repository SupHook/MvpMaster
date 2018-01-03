package com.hook.mvp.proxy;

import com.hook.mvp.factory.PresenterFactory;
import com.hook.mvp.presenter.BasePresenter;
import com.hook.mvp.view.BaseView;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/2 21:01
 * 组织：Vanson
 * 描述：Presenter代理接口，没有写死工厂，可以自定以工厂
 */
public interface PresenterProxy<V extends BaseView, P extends BasePresenter<V>>
{
    /**
     * 设置Presenter工厂
     *
     * @param presenterFactory presenter工厂
     */
    void setPresenterFactory(PresenterFactory<V, P> presenterFactory);

    /**
     * 获取Presenter工厂
     *
     * @return presenter工厂
     */
    PresenterFactory<V, P> getPresenterFactory();


    /**
     * 获取Presenter
     *
     * @return 当前被创建的Presenter
     */
    P getPresenter();
}
