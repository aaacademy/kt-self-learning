package dev.asrul.selflearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import dev.asrul.selflearning.Utils.MySharedPreferences

class SplashActivity : AppCompatActivity() {
    lateinit var pre: MySharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        pre = MySharedPreferences(this)
        Handler().postDelayed({
            var i = Intent()
            if (!pre.firstInstall) {
                i = Intent(this@SplashActivity, WalkThroughActivity::class.java)
            } else {
                i = Intent(this@SplashActivity, MainActivity::class.java)
            }
            startActivity(i)
            finish()
        }, 2000)
    }
}