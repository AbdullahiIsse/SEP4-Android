package com.abdu.and_sep4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.ContentView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import com.abdu.and_sep4.View.Login.LoginActivity;
import com.abdu.and_sep4.View.Main.MainActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    @Rule
    public ActivityScenarioRule<LoginActivity>activityScenarioRule = new ActivityScenarioRule<>(LoginActivity.class);



    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.abdu.and_sep4", appContext.getPackageName());
    }


    @Test
    public void clickLoginBtnIfUsernameAndPasswordIsEmpty(){
        String use = "username can not be empty";
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText(use)).check(matches (isDisplayed()));
    }


    @Test
    public void clickLoginBtnIfPasswordIsEmpty(){
        String username = "bob";
        String passwordError = "password can not be empty";
        onView(withId(R.id.et_username)).perform(ViewActions.typeText(username));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText(passwordError)).check(matches (isDisplayed()));
    }

    @Test
    public void clickLoginBtnIfUsernameAndPasswordIsNotEmptyAndValid(){
        String username = "bob";
        String password = "123";
        onView(withId(R.id.et_username)).perform(ViewActions.typeText(username));
        onView(withId(R.id.et_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.rv_home)).check(matches (isDisplayed()));
    }

    @Test
    public void clickLoginBtnIfUsernameAndPasswordIsNotEmptyAndNotValid(){
        String username = "bob";
        String password = "1234";
        String error = "User not found";
        onView(withId(R.id.et_username)).perform(ViewActions.typeText(username));
        onView(withId(R.id.et_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText(error)).check(matches (isDisplayed()));

    }


    @Test
    public void loginAndSeeIfTerrariumIsAvailable(){
        String terrarium = "Terrarium1";
        clickLoginBtnIfUsernameAndPasswordIsNotEmptyAndValid();
        onView(withText(terrarium)).check(matches (isDisplayed()));
    }

    @Test
    public void loginAndSeeIfTerrariumIsNotAvailable(){
        String terrariumEmpty = "Can not find any Terrarium";
        String username = "Admin";
        String password = "123";
        onView(withId(R.id.et_username)).perform(ViewActions.typeText(username));
        onView(withId(R.id.et_password)).perform(ViewActions.typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText(terrariumEmpty)).check(matches (isDisplayed()));
    }

    @Test
    public void loginAndSeeIfTerrariumTemperatureIsAvailable(){

        clickLoginBtnIfUsernameAndPasswordIsNotEmptyAndValid();
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_home),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_date), withText("2022-11-11T13:23:44"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));


    }

    @Test
    public void loginAndSeeIfTerrariumTemperatureIsNotAvailable(){

        clickLoginBtnIfUsernameAndPasswordIsNotEmptyAndValid();
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_home),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_current_temp), withText("temperature is not available for terrarium"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));


    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}