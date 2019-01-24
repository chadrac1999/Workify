package kodoratech.androidkotlin.workify

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    private var mHandler: Handler? = null
    private var delayTime: Long = 3000 // 3 second

    private var mRunnable: Runnable = Runnable {
        if (!isFinishing){
            val intent = Intent(applicationContext, CarousselActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize the Handler
        mHandler = Handler()

        //Navigate with Delay
        mHandler!!.postDelayed(mRunnable, delayTime)
    }

    public override fun onDestroy() {
        if (mHandler !== null){
            mHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}
