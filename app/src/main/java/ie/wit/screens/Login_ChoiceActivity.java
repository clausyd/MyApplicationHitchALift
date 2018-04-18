package ie.wit.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;


public class Login_ChoiceActivity extends Home_Screen_Activity{

    TextView signUp;
    TextView loginEmail;
    SignInButton signInButton;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private TextView mAuthCodeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        signUp = findViewById(R.id.signUp);
        loginEmail = findViewById(R.id.loginEmail);
        signInButton = findViewById(R.id.sign_in_button);
       // mAuthCodeTextView = findViewById(R.id.detail);

        //validateServerClientID();



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

       // mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.sign_in_button:
//                        signIn();
//                        break;
//                }
//            }
//        });

        loginEmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), RegistrationActivity.class);
                startActivityForResult(myIntent, 0);

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_log) {
//            Intent myIntent = new Intent(getApplicationContext(), Home_Screen_Activity.class);
//            startActivityForResult(myIntent, 0);
//
//
//        }
//        return super.onOptionsItemSelected(item);
//
//    }
//
//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // [START on_start_sign_in]
//        // Check for existing Google Sign In account, if the user is already signed in
//        // the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
//        // [END on_start_sign_in]
//    }
//
//    // [START onActivityResult]
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//    // [END onActivityResult]
//
//    // [START handleSignInResult]
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            // Signed in successfully, show authenticated UI.
//            updateUI(account);
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
//        }
//    }
//
//
//    // [START signOut]
//    private void signOut() {
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // [START_EXCLUDE]
//                        updateUI(null);
//                        // [END_EXCLUDE]
//                    }
//                });
//    }
//    // [END signOut]
//
//    // [START revokeAccess]
//    private void revokeAccess() {
//        mGoogleSignInClient.revokeAccess()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // [START_EXCLUDE]
//                        updateUI(null);
//                        // [END_EXCLUDE]
//                    }
//                });
//
//
//    }
//
//    private void updateUI(@Nullable GoogleSignInAccount account) {
//        if (account != null) {
//            //mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));
//
//            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
//            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
//        } else {
//            //mStatusTextView.setText(R.string.signed_out);
//
//            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
//            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
//
//        }
//    }
//
//    private void validateServerClientID() {
//        String serverClientId = getString(R.string.server_client_id);
//        String suffix = ".apps.googleusercontent.com";
//        if (!serverClientId.trim().endsWith(suffix)) {
//            String message = "Invalid server client ID in strings.xml, must end with " + suffix;
//
//            Log.w(TAG, message);
//            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//        }
//    }
//
//
//
//
////    @Override
////    public void onClick(View v) {
////        switch (v.getId()) {
////            case R.id.sign_in_button:
////                signIn();
////                break;
////            case R.id.sign_out_button:
////                signOut();
////                break;
////            case R.id.disconnect_button:
////                revokeAccess();
////                break;
////        }
////    }
}
