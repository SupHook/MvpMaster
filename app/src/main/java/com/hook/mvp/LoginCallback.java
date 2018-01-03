package com.hook.mvp;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/3 16:39
 * 组织：Vanson
 * 描述：模拟网络请求响应
 */
public interface LoginCallback
{
    void onResponse();

    void onFailed();
}
