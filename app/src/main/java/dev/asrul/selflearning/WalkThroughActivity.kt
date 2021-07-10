package dev.asrul.selflearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import dev.asrul.selflearning.Adapter.WalkthroughAdapter
import dev.asrul.selflearning.Utils.MySharedPreferences

class WalkThroughActivity : AppCompatActivity() {
    lateinit var walkthroughAdapter: WalkthroughAdapter
    lateinit var pre: MySharedPreferences
    lateinit var vpWalkthrough: ViewPager
    lateinit var llDots: LinearLayout
    lateinit var tvLanjutkan: TextView
    lateinit var tvLewati: TextView
    val dots = arrayOfNulls<TextView>(3)
    var currentPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through)

        walkthroughAdapter = WalkthroughAdapter(this)
        pre = MySharedPreferences(this)
        vpWalkthrough = findViewById(R.id.vp_walkthrough)
        tvLewati = findViewById(R.id.tv_lewati)
        tvLanjutkan = findViewById(R.id.tv_lanjutkan)

        vpWalkthrough.adapter = walkthroughAdapter
        dotIndicator(currentPage)
        initAction()
    }

    private fun dotIndicator(p: Int) {
        llDots = findViewById(R.id.ll_dots)
        llDots.removeAllViews()

        for(i in 0..dots.size - 1) {
            dots[i] = TextView(this)
            dots[i]?.textSize = 35f
            dots[i]?.setTextColor(resources.getColor(R.color.teal_700))
            dots[i]?.text = Html.fromHtml("&#8226")
            llDots.addView(dots[i])
        }

        if (dots.size > 0) {
            dots[p]?.setTextColor(resources.getColor(R.color.purple_700))
        }
    }

    fun initAction() {
        vpWalkthrough.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                dotIndicator(position)
                currentPage = position

                if (position == dots.size - 1) {
                    tvLanjutkan.setOnClickListener {
                        pre.firstInstall = true
                        val intent = Intent(this@WalkThroughActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        tvLanjutkan.setOnClickListener {
            vpWalkthrough.setCurrentItem(currentPage + 1)
        }

        tvLewati.setOnClickListener {
            pre.firstInstall = true
            val intent = Intent(this@WalkThroughActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}