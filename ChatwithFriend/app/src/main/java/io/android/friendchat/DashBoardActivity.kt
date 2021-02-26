package io.android.friendchat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth


class DashBoardActivity : AppCompatActivity() {
    // declare the GoogleSignInClient
    lateinit var mGoogleSignInClient: GoogleSignInClient
    // val auth is initialized by lazy
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val signOutBtn = findViewById<Button>(R.id.sign_out_btn)



    val yr_name = findViewById<TextView>(R.id.your_name)

        val currentUser = auth.currentUser
        yr_name.text = currentUser?.displayName


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
// pass the same server client ID used while implementing the LogIn feature earlier.


        signOutBtn.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                val intent= Intent(this, GoogleLogIn::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}