package com.hcsw.androidapplicationblocksample.app;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by gwjang on 2014. 3. 20..
 */
public class BaseFragment extends Fragment {

	protected ActionBar getActionBar() {
		return ((ActionBarActivity) getActivity()).getSupportActionBar();
	}

}
