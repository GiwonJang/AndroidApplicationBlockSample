package com.hcsw.androidapplicationblocksample.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.viewpagerindicator.TabPageIndicator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Vector;

/**
 * Created by gwjang on 2014. 3. 20..
 */
public class VolleySampleImageListFragment extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View rootView = inflater.inflate(R.layout.fragment_main, container, false);

		/*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
		textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));

		mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
		mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(100));

		mRequestQueue.add(
				new GsonRequest<SampleImage>(
						SAMPLE_IMAGE_REQ_URL,
						SampleImage.class,
						null,
						new Response.Listener<SampleImage>() {
							public void onResponse(SampleImage response) {
								LOGGER.info(response.getTitle());
								//appendItemToList(response.items);
								//notifyDataSetChanged();
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError volleyError) {
								LOGGER.error(volleyError.getMessage(), volleyError);
							}
						}
				)
		);*/

		return rootView;
	}
}
