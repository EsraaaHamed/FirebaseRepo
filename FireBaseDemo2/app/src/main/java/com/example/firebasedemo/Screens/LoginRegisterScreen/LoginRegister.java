package com.example.firebasedemo.Screens.LoginRegisterScreen;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;

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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginRegister extends AppCompatActivity implements LoginRegisterContract.LoginRegisterView {
    EditText emailTxt;
    EditText passwordTxt;
    Button loginBtn;
    Button registerBtn;
    Button googleLoginBtn;
    Button facebookLoginBtn;
    //private FirebaseAuth firebaseAuth;
    private static String TAG="login";
    //GoogleSignInClient mGoogleSignInClient;
    LoginRegisterContract.LoginRegisterPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        emailTxt=(EditText)findViewById(R.id.emailTxt);
        passwordTxt=(EditText)findViewById(R.id.passwordTxt);
        loginBtn=(Button)findViewById(R.id.loginBtn);
        registerBtn=(Button)findViewById(R.id.registerBtn);
        googleLoginBtn=(Button)findViewById(R.id.googleLogin);
        facebookLoginBtn=(Button)findViewById(R.id.facebookBtn);
        presenter=new LoginRegisterPresenterImp(this,this,this);
        printKeyHash();
        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.firebasedemo",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String msg=Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
    }

    private void signIn() {
        Intent signInIntent = presenter.getSignInIntent();
        startActivityForResult(signInIntent, 101);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            presenter.beforeAuth(data);

            /*Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

                Toast.makeText(LoginRegister.this, "Google Sign in Failed", Toast.LENGTH_SHORT).show();


            }*/
        }
    }



    @Override
    protected void onStart() {
        super.onStart();

    }

    public void loginMethod(View view) {
        String email=emailTxt.getText().toString();
        String password=passwordTxt.getText().toString();
        presenter.loginMethod(view,email,password);


    }

   public void registerMethod(View view) {
       String email=emailTxt.getText().toString();
       String password=passwordTxt.getText().toString();
       presenter.registerMethod(view,email,password);

    }

    @Override
    public void goToNextActivity() {
        Intent Dbintent = new Intent(LoginRegister.this, ReadingWritingToRealDb.class);
        startActivity(Dbintent);

    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(LoginRegister.this, msg, Toast.LENGTH_SHORT).show();


    }
}
