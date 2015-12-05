package com.boldijarpaul.polihack.mvp.presenter;

import android.content.Context;

import com.boldijarpaul.polihack.dagger.DaggerApp;
import com.boldijarpaul.polihack.mvp.model.User;
import com.boldijarpaul.polihack.mvp.view.LoginView;
import com.boldijarpaul.polihack.rx.RxPresenter;
import com.boldijarpaul.polihack.service.UserService;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter extends RxPresenter<LoginView> {

    @Inject
    UserService mUserService;

    public LoginPresenter(LoginView view, Context context) {
        super(view);
        DaggerApp.get(context).graph().inject(this);
    }

    public void loginUser(String facebookId, String name) {
        User user = new User();
        user.facebookId = facebookId;
        user.name = name;
        mUserService.loginUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        getView().login(user);
                    }
                });
    }
}
