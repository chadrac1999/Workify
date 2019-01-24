package kodoratech.androidkotlin.workify

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import kodoratech.androidkotlin.workify.Offres.PrincipalActivity

class CarousselActivity : AppCompatActivity(), View.OnClickListener {

    var layouts: IntArray = intArrayOf(R.layout.first_slide, R.layout.second_slide)
    lateinit var mPager: ViewPager
    lateinit var dotsLayout: LinearLayout
    lateinit var dots: Array<ImageView>
    lateinit var mAdapter: PageAdapter
    lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caroussel)
        if (PrefManager(this).checkPreference()){
            loadHome()
        }
        if (Build.VERSION.SDK_INT >= 19){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        mPager = findViewById<ViewPager>(R.id.pager) as ViewPager
        mAdapter = PageAdapter(layouts, this)
        mPager.adapter = mAdapter
        dotsLayout = findViewById<LinearLayout>(R.id.dots) as LinearLayout
        btnNext = findViewById<Button>(R.id.btn_next) as Button
        btnNext.setOnClickListener(this)
        createDots(0)
        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position == layouts.size - 1){
                    btnNext.setText("Commencer")
                }
                else{
                    btnNext.setText("Suivant")
                }
            }

        })
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_next ->
            {
                loadNextSlide()
            }
        }
    }

    private fun loadNextSlide() {
        var nextSlider: Int = mPager.currentItem + 1

        if (nextSlider < layouts.size){
            mPager.setCurrentItem(nextSlider)
        }
        else{
            loadHome()
            PrefManager(this).writeSP()
        }
    }

    private fun loadHome() {
        startActivity(Intent(this, PrincipalActivity::class.java))
        finish()
    }

    fun createDots(position: Int){
        if (dotsLayout != null){
            dotsLayout.removeAllViews()
        }
        dots = Array(layouts.size, {i -> ImageView(this)})

        for (i in 0..layouts.size - 1){
            dots[i] = ImageView(this)
            if (i == position){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots))
            }
            else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots))
            }
            val params: LinearLayout.LayoutParams =LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(4,0,4,0)
            dotsLayout.addView(dots[i], params)
        }
    }

}
