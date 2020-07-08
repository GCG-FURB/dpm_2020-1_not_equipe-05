package br.com.furb.trabalhofinal.service;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import br.com.furb.trabalhofinal.ui.login.SignInActivity;

public class GoogleSignInService {

    private static GoogleSignInService googleSignInService;
    private GoogleSignInOptions gso;

    private GoogleSignInService() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

    public static GoogleSignInService getInstance() {
        if (googleSignInService == null) {
            googleSignInService = new GoogleSignInService();
        }
        return googleSignInService;
    }

    public void signIn(Activity activity) {
        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    }

    public void logOut(Activity activity) {
        FirebaseAuth.getInstance().signOut();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(activity, SignInActivity.class);
                        activity.startActivity(intent);
                    }
                });
    }

}
