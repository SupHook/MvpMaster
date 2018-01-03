package com.hook.mvp.factory;

import com.hook.mvp.presenter.BasePresenter;
import com.hook.mvp.view.BaseView;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/2 20:26
 * 组织：Vanson
 * 描述：创建Presenter工厂接口,指定所有绑定view和presenter的类型
 */
public interface PresenterFactory<V extends BaseView, P extends BasePresenter<V>>
{
    /**
     * 创建Presenter
     *
     * @return 需要创建的Presenter
     */
    P createPresenter();
}
