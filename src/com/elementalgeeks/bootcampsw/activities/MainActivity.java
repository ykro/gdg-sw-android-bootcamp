package com.elementalgeeks.bootcampsw.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.elementalgeeks.bootcampsw.App;
import com.elementalgeeks.bootcampsw.R;
import com.elementalgeeks.bootcampsw.fragments.AboutFragment;
import com.elementalgeeks.bootcampsw.fragments.EventsListFragment;
import com.elementalgeeks.bootcampsw.fragments.EventsMapFragment;

public class MainActivity extends ActionBarActivity implements TabListener {
	private int currentTab = 0;
	private boolean menuEnabled = false;
	
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
			transaction.add(R.id.main, frag);
		}
		transaction.commit();
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
		currentTab = tab.getPosition();
		transaction.show(fragments[currentTab]);	
		
		if (currentTab == 2) {
			menuEnabled = false;
		} else {
			menuEnabled = true;
		}
	}

	@Override
	public void onTabUnselected(Tab arg0,
			android.support.v4.app.FragmentTransaction arg1) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);		
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu (Menu menu) {
		menu.getItem(0).setEnabled(menuEnabled);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_update:
				App app = (App)getApplicationContext();
				menuEnabled = false;
				app.apiRequest(fragments[currentTab]);
				return true;
			default :
				return super.onOptionsItemSelected(item);
		}		
	}
	
	public boolean isMenuEnabled() {
		return menuEnabled;
	}

	public void setMenuEnabled(boolean menuEnabled) {
		this.menuEnabled = menuEnabled;
	}

	
}