package com.example.firebasedemo.Screens.LoginRegisterScreen;

import android.content.Intent;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface LoginRegisterContract {
    interface LoginRegisterView{
        void goToNextActivity();
        void showMsg(String msg);
    }
    interface LoginRegisterPresenter
    {
         void firebaseAuthWithGoogle(GoogleSignInAccount acct);
         void loginMethod(View view,String email,String password);
         void registerMethod(View view,String email,String password);
         void beforeAuth(Intent data);
        Intent getSignInIntent();
    }
}
