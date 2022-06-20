package com.example.payback.activities

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.payback.R
import com.example.payback.models.Hits
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HitContentsActivityTest{
    @Rule
    @JvmField
    public var mActivityTestRule = ActivityTestRule(HitContentsActivity::class.java)
    lateinit var hitmodel : Hits

    @Test
    fun testRecycler()
    {
        mActivityTestRule = IntentsTestRule(HitContentsActivity::class.java)
        mActivityTestRule.launchActivity(Intent())
        Espresso.onView(withId(R.id.HitsRecycler)).perform(
            RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(2,click()))
    }

//    @org.junit.Test
//    fun init() {
//        var attributes  = Hits("1","2","3","ascsc","",0,0,""
//            ,"","","",0,0,0,98,65,988
//            ,90,50,9,"Hemin","")
//
//
//
//        hitmodel = Hits("1","2","3","ascsc","",0,0,""
//            ,"","","",0,0,0,98,65,988
//            ,90,50,9,"Hemin","")
//        mActivityTestRule = IntentsTestRule(HitDetailActivity::class.java)
//        mActivityTestRule.launchActivity(Intent())
//        mActivityTestRule.DetailRecycler.
//        Espresso.onView(
//            ViewMatchers.withId(R.id.DetailRecycler).per
//            .check(ViewAssertions.matches(ViewMatchers.withText("Hemin")))
//    }

}