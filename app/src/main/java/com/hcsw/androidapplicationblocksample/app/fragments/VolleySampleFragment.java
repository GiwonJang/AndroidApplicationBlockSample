package com.hcsw.androidapplicationblocksample.app.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcsw.androidapplicationblocksample.app.MainActivity;
import com.hcsw.androidapplicationblocksample.app.R;
import com.hcsw.androidapplicationblocksample.app.VolleySamplePagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gwjang on 2014. 2. 27..
 */
public class VolleySampleFragment extends BaseFragment {

	private static final Logger LOGGER = LoggerFactory.getLogger(VolleySampleFragment.class);
	private ViewPager mViewPager;
	private VolleySamplePagerAdapter mPagerAdapter;

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static VolleySampleFragment newInstance(int sectionNumber) {
		VolleySampleFragment fragment = new VolleySampleFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public VolleySampleFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View rootView = inflater.inflate(R.layout.fragment_main_volley, container, false);

		mPagerAdapter = new VolleySamplePagerAdapter(getActivity(), getActivity().getSupportFragmentManager());
		mViewPager = (ViewPager) rootView.findViewById(R.id.vp_pager);
		mViewPager.setAdapter(mPagerAdapter);

		//Bind the title indicator to the adapter
		TabPageIndicator tabPagerIndicator = (TabPageIndicator) rootView.findViewById(R.id.vpi_indicator);
		tabPagerIndicator.setViewPager(mViewPager);
		tabPagerIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		super.onAttach(activity);
	}

	@Override
	public void onStop() {
		super.onStop();
	}
}