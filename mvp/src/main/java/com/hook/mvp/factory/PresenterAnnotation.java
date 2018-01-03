package com.hook.mvp.factory;

import com.hook.mvp.presenter.BasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/2 20:40
 * 组织：Vanson
 * 描述：注解-标注创建Presenter
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface PresenterAnnotation
{
    Class<? extends BasePresenter> createPresenter();
}
