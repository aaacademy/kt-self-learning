package dev.asrul.selflearning.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import dev.asrul.selflearning.R

class WalkthroughAdapter(val context: Context): PagerAdapter() {
    val imgArray: IntArray = intArrayOf(
        R.drawable.js,
        R.drawable.ts,
        R.drawable.react
    )
    val titleArray: Array<String> = arrayOf(
        "Javascript",
        "Typescript",
        "React"
    )
    override fun getCount(): Int {
        return imgArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view: View = `object` as View
        container.removeView(view)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(context).inflate(R.layout.slide_walkthrough, container, false)
        val textTitle: TextView = view.findViewById(R.id.tv_title)
        val img: ImageView = view.findViewById(R.id.iv_img)

        textTitle.text = titleArray[position]
        img.setImageDrawable(ContextCompat.getDrawable(context, imgArray[position]))
        container.addView(view)
        return view
    }
}