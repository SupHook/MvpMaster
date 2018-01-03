package com.hook.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hook.mvp.view.BaseView;

/**
 * 作者：Zuo Lei
 * 时间：2018/1/2 20:29
 * 组织：Vanson
 * 描述：MVP-所有Presenter层基类,指定绑定的View类型,实现生命周期
 */
public class BasePresenter<V extends BaseView>
{
    private V view;

    /**
     * Presenter被创建时调用
     *
     * @param saveState 意外销毁重建后的bundle
     */
    public void onCreatePresenter(@Nullable Bundle saveState)
    {
        Log.e("MVP", "P onCreatePresenter");
    }


    /**
     * 绑定View
     *
     * @param view
     */
    public void onAttachMVPView(V view)
    {
        this.view = view;
        Log.e("MVP", "P onResume");
    }


    /**
     * 解绑View
     */
    public void onDetachMVPView()
    {
        view = null;
        Log.e("MVP", "P onDetachMVPView");
    }


    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPresenter()
    {
        Log.e("MVP", "P onDestroy");
    }

    /**
     * Presenter意外销毁时调用，与Activity、Fragment的onSaveInstance时机相同
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState)
    {
        Log.e("MVP", "P onSaveInstanceState");
    }

    /**
     * 获取View层
     *
     * @return view
     */
    public V getMVPView()
    {
        return view;
    }
}
