package com.hcsw.androidapplicationblocksample.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hcsw.androidapplicationblocksample.app.fragments.VolleySampleUserCheckFragment;

import java.util.List;
import java.util.Vector;

/**
 * The <code>PagerAdapter</code> serves the fragments when paging.
 *
 * @author mwho
 */
public class VolleySamplePagerAdapter extends FragmentPagerAdapter {

	private static final String[] CONTENT = new String[]{"PPM App Ver.", "Artists", "Albums"};
	private List<Fragment> fragments;

	public VolleySamplePagerAdapter(Context context, FragmentManager fm) {
		super(fm);

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(context, VolleySampleUserCheckFragment.class.getName()));
		fragments.add(Fragment.instantiate(context, MainActivity.PlaceholderFragment.class.getName()));
		fragments.add(Fragment.instantiate(context, MainActivity.PlaceholderFragment.class.getName()));
		this.fragments = fragments;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return CONTENT[position % CONTENT.length];
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