package com.example.gustavohenrique.customcomponents.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import com.example.gustavohenrique.customcomponents.R
import com.example.gustavohenrique.customcomponents.components.Onboarding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addOnboarding()
    }

    private fun addOnboarding() {

        val set = ConstraintSet() //create a set of Constraints

        val onBoarding = Onboarding(this) // Creating my component
        mainActivityLayout.addView(onBoarding, 0) // Index 0 is first index at the view hierarchy

        set.clone(mainActivityLayout) // Take the constraints already exists

        set.connect(onBoarding.id, ConstraintSet.TOP, mainActivityLayout.id, ConstraintSet.TOP,0)
        set.connect(onBoarding.id, ConstraintSet.START,mainActivityLayout.id, ConstraintSet.START, 0)
        set.connect(onBoarding.id, ConstraintSet.END,mainActivityLayout.id, ConstraintSet.END, 0)
        set.connect(onBoarding.id, ConstraintSet.BOTTOM, mainActivityLayout.id, ConstraintSet.BOTTOM, 0)

        set.applyTo(mainActivityLayout)
    }
}
