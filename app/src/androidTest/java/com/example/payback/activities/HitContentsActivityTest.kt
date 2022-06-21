package com.example.payback.activities

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.payback.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HitContentsActivityTest{
    @Rule
    @JvmField
     var mActivityTestRule = ActivityTestRule(HitContentsActivity::class.java)

    @Test
    fun testRecycler()
    {
        mActivityTestRule = IntentsTestRule(HitContentsActivity::class.java)
        mActivityTestRule.launchActivity(Intent())
        Espresso.onView(withId(R.id.HitsRecycler)).perform(
            RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(2,click()))
    }




}