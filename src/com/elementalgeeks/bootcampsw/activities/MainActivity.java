package com.elementalgeeks.bootcampsw.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;

import com.elementalgeeks.bootcampsw.R;
import com.elementalgeeks.bootcampsw.fragments.AboutFragment;
import com.elementalgeeks.bootcampsw.fragments.EventsListFragment;
import com.elementalgeeks.bootcampsw.fragments.EventsMapFragment;

public class MainActivity extends ActionBarActivity implements TabListener {	
	private Fragment[] fragments = new Fragment[]{ new EventsListFragment(), 
												   new EventsMapFragment(),
										           new AboutFragment()};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		actionBar.addTab(
				actionBar.newTab()
					     .setText(getString(R.string.title_list))
					     .setTabListener(this));
		
		actionBar.addTab(
				actionBar.newTab()
					     .setText(getString(R.string.title_map))
					     .setTabListener(this));
		
		actionBar.addTab(
				actionBar.newTab()
					     .setText(getString(R.string.title_about))
					     .setTabListener(this));	
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		for (Fragment frag : fragments) {
			transaction.add(R.id.main, frag).hide(frag);
		}
		transaction.show(fragments[0]).commit();
	}

	@Override
	public void onTabReselected(Tab arg0,
			android.support.v4.app.FragmentTransaction arg1) {
	}

	@Override
	public void onTabSelected(Tab tab,
			android.support.v4.app.FragmentTransaction transaction) {
		for (Fragment frag : fragments) {
			transaction.hide(frag);
		}
		transaction.show(fragments[tab.getPosition()]);	
	}

	@Override
	public void onTabUnselected(Tab arg0,
			android.support.v4.app.FragmentTransaction arg1) {
	}	
}