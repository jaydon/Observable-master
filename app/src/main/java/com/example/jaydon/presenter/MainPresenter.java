package com.example.jaydon.presenter;

import android.os.Handler;
import android.text.TextUtils;
import com.example.jaydon.task.TaskManager;

/**
 * Created by luoxf on 2015/12/24.
 */
public class MainPresenter implements BasePresenter{
    public IMainPresenter mainActivity;
    public Handler mHandler =  new Handler();;
    public MainPresenter(IMainPresenter mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void validateCredentials(String username, String password) {
        mainActivity.showProgress();
        login(username, password);
    }

    public void login(final String username, final String password) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean error = false;
                        if (TextUtils.isEmpty(username)) {
                            mainActivity.setUsernameError();
                            mainActivity.hideProgress();
                            error = true;
                        }
                        if (TextUtils.isEmpty(password)) {
                            mainActivity.setPasswordError();
                            mainActivity.hideProgress();
                            error = true;
                        }
                        if (!error) {
                            mainActivity.onSuccess();
                        }
                    }
                }, 2000);
            }
        };
        TaskManager.execute(r);

    }

    /**
     * 避免Activity在调用onDestroy后无法回收
     */
    @Override
    public void clear() {
        mHandler.removeCallbacksAndMessages(null);
    }

    public interface IMainPresenter {
        public void showProgress();
        public void hideProgress();
        public void setUsernameError();
        public void setPasswordError();
        public void onSuccess();
    }
}



