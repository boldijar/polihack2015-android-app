package com.boldijarpaul.polihack.dagger;


import com.boldijarpaul.polihack.MainActivity;
import com.boldijarpaul.polihack.activities.LoginAppActivity;
import com.boldijarpaul.polihack.mvp.presenter.LoginPresenter;

public interface DaggerGraph {
    void inject(DaggerApp daggerApp);

    void inject(MainActivity mainActivity);

    void inject(LoginPresenter loginPresenter);

    void inject(LoginAppActivity loginAppActivity);
}
