package com.example.pinexample.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.pinexample.R;
import com.example.pinexample.fragments.CreatePinFragment;
import com.example.pinexample.fragments.TermsFragment;
import com.example.pinexample.fragments.WelcomeFragment;
import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.PageIndicator;

/**
 * The first run Activity that initializes the welcome fragments. 
 * Implements the ViewPagerIndicator library.
 * @author drakuwa
 *
 */
public class MainFragmentActivity extends FragmentActivity {
	
	private PageIndicator mIndicator;
	private ViewPager pager;
	private static final int[] TAB_TITLES = new int[] {
		R.string.empty, R.string.empty, R.string.empty, R.string.empty };
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// check if the app is ran for the first time
		prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		if (prefs.getBoolean("initialized", false)) {
			// this is not a first time the application is started
			// check if there is a pin set
			if (prefs.getString("pin", "").trim().length()>0){
				Intent pin = new Intent(MainFragmentActivity.this, PinActivity.class);
				pin.putExtra("whereTo", "main");
				startActivity(pin);finish();}
			else {
				// if there is no pin set, just start the main activity
				startActivity(new Intent(MainFragmentActivity.this, StartActivity.class));
				finish();}
		}
		// else, set the content view and show the welcome fragments...
		setContentView( R.layout.firstrun_fragment );
		
		// set up a FragmentTitleAdapter from the support library
		FragmentPagerAdapter adapter = new FragmentTitleAdapter(getSupportFragmentManager());
		// initialize the ViewPager with the FragmentTitleAdapter adapter
		pager = (ViewPager) findViewById(R.id.viewpager);
		pager.setAdapter(adapter);
		
		mIndicator = (LinePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(pager);
	}
	
	@Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
        	pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }
	
	/**
	 * Titles provider class
	 */
	class FragmentTitleAdapter extends FragmentPagerAdapter {
		public FragmentTitleAdapter(FragmentManager fm) {super(fm);}
		@Override
		public Fragment getItem(int position) {
			if(position==0) return WelcomeFragment.newInstance(TAB_TITLES[position % TAB_TITLES.length]);
			else if(position==3) return CreatePinFragment.newInstance(TAB_TITLES[position % TAB_TITLES.length]);
			return TermsFragment.newInstance(TAB_TITLES[position % TAB_TITLES.length]);
		}
		@Override
		public CharSequence getPageTitle(int position) {
			return getString(TAB_TITLES[position % TAB_TITLES.length]);}
		@Override public int getCount() {	return TAB_TITLES.length;}
	}
}
