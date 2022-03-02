package com.vaibhav.githubclone

import android.view.View
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import androidx.test.filters.LargeTest
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.vaibhav.githubclone.adapter.ReposRecyclerViewAdapter
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition

@RunWith(JUnit4::class)
@LargeTest
class ProfileActivityTest {

    lateinit var scenario: ActivityScenario<MainActivity>
    private val user = "vaibhavjain30699"

    @Before
    fun setup() {
        scenario = launchActivity()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        onView(withId(R.id.username)).perform(ViewActions.typeText(user))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.getDetails)).perform(click())
    }

    @Test
    fun checkUsernameInProfileScreen() {
        onView(withText("@$user")).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun checkRepositoriesTabInProfileScreen() {
        onView(withText("Repositories")).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun checkSwipeOnViewPagerInProfileScreen() {
        onView(withId(R.id.tabLayout)).perform(
            CustomViewAction(
                swipeLeft(),
                isDisplayingAtLeast(30)
            )
        )
    }

    @Test
    fun checkForRecyclerViewAndScroll() {
        onView(withText("Repositories")).perform(click())
        onView(withId(R.id.allReposRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.allReposRecyclerView)).perform(
            actionOnItemAtPosition<ReposRecyclerViewAdapter.ReposViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.contributorsRecyclerView)).check(matches(isDisplayed()))
    }


    class CustomViewAction(
        private val action: ViewAction?,
        private val matcher: org.hamcrest.Matcher<View>?
    ) : ViewAction {
        override fun getConstraints(): org.hamcrest.Matcher<View> {
            return matcher!!
        }

        override fun getDescription(): String {
            return action?.description ?: "Bad Action"
        }

        override fun perform(uiController: UiController?, view: View?) {
            action?.perform(uiController, view)
        }

    }
}