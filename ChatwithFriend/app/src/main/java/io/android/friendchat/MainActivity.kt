package io.android.friendchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed(
            {
            if(user!=null){
                val dashBoardIntent = Intent(this, DashBoardActivity::class.java)
                startActivity(dashBoardIntent)
                    finish()
            }
                else{
                    val signInIntent = Intent(this, GoogleLogIn::class.java)
                startActivity(signInIntent)
                finish()
            }
            }
            ,2000)

    }
}