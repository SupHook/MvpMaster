package com.hook.mvp.factory;

import com.hook.mvp.presenter.BasePresenter;
import com.hook.mvp.view.BaseView;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/2 20:43
 * 组织：Vanson
 * 描述：Presenter工厂实现类
 */
public class PresenterFactoryImpl<V extends BaseView, P extends BasePresenter<V>> implements PresenterFactory
{
    private final Class<P> presenterClass;

    private PresenterFactoryImpl(Class<P> presenterClass)
    {
        this.presenterClass = presenterClass;
    }

    /**
     * 根据注解创建Presenter工厂实现类
     *
     * @param viewClass 与被创建Presenter相关联的View层实现类
     * @param <V>       当前View实现的接口类型
     * @param <P>       当前创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends BaseView, P extends BasePresenter<V>> PresenterFactoryImpl<V, P> createFactory(Class<?> viewClass)
    {
        PresenterAnnotation presenterAnnotation = viewClass.getAnnotation(PresenterAnnotation.class);
        Class<P> pClass = null;
        if (presenterAnnotation != null)
            pClass = (Class<P>) presenterAnnotation.createPresenter();
        return pClass == null ? null : new PresenterFactoryImpl<V, P>(pClass);
    }


    @Override
    public BasePresenter createPresenter()
    {
        try
        {
            return presenterClass.newInstance();
        } catch (Exception e)
        {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@PresenterAnnotation(xx.class)注解");
        }
    }
}
