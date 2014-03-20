package com.hcsw.androidapplicationblocksample.app;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gwjang on 2014. 3. 20..
 */
public class VolleySampleTabListener<T extends Fragment> implements ActionBar.TabListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(VolleySampleTabListener.class);

	private Fragment mFragment;
	private final Activity mActivity;
	private final String mTag;
	private final Class<T> mClass;

	/**
	 * Constructor used each time a new tab is created.
	 *
	 * @param activity The host Activity, used to instantiate the fragment
	 * @param tag      The identifier tag for the fragment
	 * @param clz      The fragment's Class, used to instantiate the fragment
	 */
	public VolleySampleTabListener(Activity activity, String tag, Class<T> clz) {
		mActivity = activity;
		mTag = tag;
		mClass = clz;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		// Check if the fragment is already initialized
		if (mFragment == null) {
			// If not, instantiate and add it to the activity
			mFragment = Fragment.instantiate(mActivity, mClass.getName());
			ft.add(android.R.id.content, mFragment, mTag);
		} else {
			// If it exists, simply attach it in order to show it
			ft.attach(mFragment);
		}
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
		if (mFragment != null) {
			// Detach the fragment, because another one is being attached
			ft.detach(mFragment);
		}
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
		// User selected the already selected tab. Usually do nothing.
	}
}
