package io.android.friendchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.common.SignInButton


class GoogleSignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var  googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_sign_in)

        val signInBtn:SignInButton =  findViewById(R.id.sign_in_button)
        signInBtn.setOnClickListener {
            signIn()
        }

//        configure sign in
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_client_id))
            .requestEmail()
            .build()

//        end configure sign in

        googleSignInClient = GoogleSignIn.getClient(this,gso)

//start initializing auth
        auth = Firebase.auth
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//            Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // [START_EXCLUDE]
                startActivity(Intent(this, MainActivity::class.java))
                // [END_EXCLUDE]
            }
        }
    }
// end onActivity result



    //        [start auth_with_google]
    private fun firebaseAuthWithGoogle(idToken:String) {
        val credentials = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credentials)
            .addOnCompleteListener(this){
                    task ->
                if (task.isSuccessful){
//                        sign in success
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }else{
//                        if sign in fails, display a message to the user
                    Log.w(TAG,"signInWithCredential:Failure", task.exception
                    )
                    Toast.makeText(this,"Authentication Failed.",Toast.LENGTH_SHORT).show()
                }
            }
    }



    private fun signIn(){
        // Configure Google Sign In
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}