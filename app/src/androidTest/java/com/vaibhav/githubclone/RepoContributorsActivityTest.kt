package com.vaibhav.githubclone

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import com.vaibhav.githubclone.adapter.ReposRecyclerViewAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@LargeTest
class RepoContributorsActivityTest {

    lateinit var scenario: ActivityScenario<MainActivity>
    private val user = "vaibhavjain30699"

    @Before
    fun setup() {
        scenario = launchActivity()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        onView(withId(R.id.username)).perform(ViewActions.typeText(user))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.getDetails)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Repositories")).perform(ViewActions.click())
        onView(withId(R.id.allReposRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ReposRecyclerViewAdapter.ReposViewHolder>(
                0,
                ViewActions.click()
            )
        )
    }

    @Test
    fun checkForRecyclerView() {
        onView(withId(R.id.contributorsRecyclerView)).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun clickViewProfileButton() {
        onView(withText(R.string.view_profile_button_label)).perform(click())
        onView(withId(R.id.profilePicture)).check(matches(isDisplayed()))
    }

}