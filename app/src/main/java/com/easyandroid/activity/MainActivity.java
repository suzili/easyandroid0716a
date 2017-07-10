package com.easyandroid.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.easyandroid.R;
import com.easyandroid.fragment.FragmentMainKnowledgeBroadcast;
import com.easyandroid.fragment.FragmentMainOnlineClass;
import com.easyandroid.fragment.FragmentMainPracticeOnline;
import com.easyandroid.fragment.FragmentMainWorkExchange;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {
	private DrawerLayout drawer;
	private NavigationView navigationView;

	private ViewPager mViewpagerMain;
	private BottomNavigationView mBottomnavigationMain;

	private List<Fragment> fragmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initSuperView();
		initView();
		initViewPager();
	}

	//初始化基本View(侧边栏)
	private void initSuperView() {

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	private void initView() {
		mViewpagerMain = (ViewPager) findViewById(R.id.viewpager_main);
		mBottomnavigationMain = (BottomNavigationView) findViewById(R.id.bottomnavigation_main);

		mBottomnavigationMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch (item.getItemId()) {
					case R.id.navigation_work_exchange:
						mViewpagerMain.setCurrentItem(0);
						return true;
					case R.id.navigation_online_class:
						mViewpagerMain.setCurrentItem(1);
						return true;
					case R.id.navigation_practice_online:
						mViewpagerMain.setCurrentItem(2);
						return true;
					case R.id.navigation_knowledge_broadcast:
						mViewpagerMain.setCurrentItem(3);
						return true;
				}
				return false;
			}
		});
	}

	private void initViewPager() {
		fragmentList = new ArrayList<>();
		fragmentList.add(new FragmentMainWorkExchange());
		FragmentMainOnlineClass mFragmentMainOnlineClass = new FragmentMainOnlineClass();
		mFragmentMainOnlineClass.SetOpenNavListener(new FragmentMainOnlineClass.OpenNavListener() {
			@Override
			public void openNav() {
				drawer.openDrawer(GravityCompat.START);
			}
		});

		fragmentList.add(mFragmentMainOnlineClass);
		fragmentList.add(new FragmentMainPracticeOnline());
		fragmentList.add(new FragmentMainKnowledgeBroadcast());

		FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public Fragment getItem(int position) {
				return fragmentList.get(position);
			}

			@Override
			public int getCount() {
				return fragmentList.size();
			}
		};
		mViewpagerMain.setAdapter(mAdapter);
		mViewpagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				mBottomnavigationMain.getMenu().getItem(position).setChecked(true);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	@Override
	public void onBackPressed() {
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_camera) {
			// Handle the camera action
		} else if (id == R.id.nav_gallery) {

		} else if (id == R.id.nav_slideshow) {

		} else if (id == R.id.nav_manage) {

		} else if (id == R.id.nav_share) {

		} else if (id == R.id.nav_send) {

		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}
