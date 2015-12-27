package com.example.jaydon.presenter;

/**
 * Created by luoxf on 2015/12/24.
 */
public interface BasePresenter {
    /**
     * 避免Activity在调用onDestroy后无法回收,如果有延时的任务需要取消
     */
    public void clear();
}
