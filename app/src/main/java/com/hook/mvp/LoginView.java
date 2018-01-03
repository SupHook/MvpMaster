package com.hook.mvp;

import com.hook.mvp.view.BaseView;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/3 16:32
 * 组织：Vanson
 * 描述：
 */
public interface LoginView extends BaseView
{
    void requestFailed();

    void requestSuccess();

    void requestLoading();
}
