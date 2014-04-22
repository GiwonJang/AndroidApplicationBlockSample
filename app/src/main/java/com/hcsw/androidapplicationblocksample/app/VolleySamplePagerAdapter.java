package com.hcsw.androidapplicationblocksample.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * The <code>PagerAdapter</code> serves the fragments when paging.
 *
 * @author mwho
 */
public class VolleySamplePagerAdapter extends FragmentPagerAdapter {

	private static final String[] CONTENT = new String[] { "Recent", "Artists", "Albums" };
	private List<Fragment> fragments;

	public VolleySamplePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return CONTENT[position % CONTENT.length].toUpperCase();
	}

	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
}