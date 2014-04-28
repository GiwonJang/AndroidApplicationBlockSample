package com.hcsw.androidapplicationblocksample.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcsw.androidapplicationblocksample.app.R;

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
