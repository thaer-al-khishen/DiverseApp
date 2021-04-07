package com.example.diverseapp.Activities

import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.diverseapp.R

//Not included in the project
//This was just to grasp the notion of the slider
class DraggingActivity : AppCompatActivity() {

    var myButton: Button? = null
    var myView: View? = null
    var isUp = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dragging)

        myView = findViewById(R.id.my_view);
        myButton = findViewById(R.id.my_button);

        myView?.setVisibility(View.INVISIBLE);
        myButton?.setText("Slide up");
        isUp = false;

//        myView.setOnT
    }

    // slide the view from below itself to the current position
    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0F
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    // slide the view from its current position to below itself
    fun slideDown(view: View) {
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            0F,  // fromYDelta
            view.height.toFloat()
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    fun onSlideViewButtonClick(view: View?) {
        if (isUp) {
            slideDown(myView!!)
            myButton!!.text = "Slide up"
        } else {
            slideUp(myView!!)
            myButton!!.text = "Slide down"
        }
        isUp = !isUp
    }


}