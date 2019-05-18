package com.example.firebasedemo.Screens.LoginRegisterScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebasedemo.R;
import com.example.firebasedemo.Screens.NotesListScreen.ReadingWritingToRealDb;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginRegisterPresenterImp implements LoginRegisterContract.LoginRegisterPresenter
{
        private FirebaseAuth firebaseAuth;
        private static String TAG="login";
        GoogleSignInClient mGoogleSignInClient;
        LoginRegisterContract.LoginRegisterView loginRegisterView;
        Context context;
        Activity activity;
        GoogleSignInOptions gso;

    public LoginRegisterPresenterImp(LoginRegisterContract.LoginRegisterView loginRegisterView) {
        this.loginRegisterView = loginRegisterView;

    }

    public LoginRegisterPresenterImp(LoginRegisterContract.LoginRegisterView loginRegisterView, Context context,Activity activity) {
        this.loginRegisterView = loginRegisterView;
        this.context = context;
        this.activity=activity;

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(activity,gso);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            loginRegisterView.showMsg("Google Sign in successfflly");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            loginRegisterView.goToNextActivity();

                        } else {
                            // If sign in fails, display a message to the user.
                            loginRegisterView.showMsg("Google Sign in Failed");


                        }
                    }
                });
    }

    @Override
    public void loginMethod(View view,String email,String password) {

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(activity,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d(TAG,"signInWithEmail");
                            loginRegisterView.showMsg("Login successfflly");
                            loginRegisterView.goToNextActivity();

                        }
                        else {
                            Log.w(TAG,"Sign in With Failure",task.getException());
                            loginRegisterView.showMsg("login Failed");
                        }
                    }
                });

    }

    @Override
    public void registerMethod(View view,String email,String password) {

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    loginRegisterView.showMsg("Regsiteration Successfuly");
                    loginRegisterView.goToNextActivity();

                }
                else {
                    loginRegisterView.showMsg("Regsiteration Failed");

                }
            }
        });
    }

    @Override
    public void beforeAuth(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = task.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            loginRegisterView.showMsg("Google Sign in Failed");



        }
    }

    @Override
    public Intent getSignInIntent() {
       return mGoogleSignInClient.getSignInIntent();
    }
}
