package com.hcsw.androidapplicationblocksample.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.androidannotations.annotations.EFragment;

/**
 * Created by gwjang on 2014. 2. 27..
 */
@EFragment
public class VolleySampleFragment extends Fragment
{
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private static final String SAMPLE_IMAGE_REQ_URL = "https://www.google.co.kr/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&docid=q9RfPKKQqcn-sM&tbnid=7xa3QwEnvaHl0M:&ved=0CAUQjRw&url=http%3A%2F%2Fbarbeskumpe.com%2Fclassic_mac&ei=hUUOU6q4JITLkgXJk4DgAQ&bvm=bv.61965928,d.dGI&psig=AFQjCNHqFnTBjyoTmCFDn-h4QhC-IIEsJw&ust=1393530622523434";//"http://127.0.0.1:8000/images";
	private static final String TAG = "VolleySampleFragment";

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	/**
	 * Returns a new instance of this fragment for the given section
	 * number.
	 */
	public static VolleySampleFragment newInstance(int sectionNumber)
	{
		VolleySampleFragment fragment = new VolleySampleFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public VolleySampleFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		TextView textView = (TextView) rootView.findViewById(R.id.section_label);
		textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));

		mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
		mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(100));

		mRequestQueue.add(
				new GsonRequest<SampleImage>(
						SAMPLE_IMAGE_REQ_URL,
						SampleImage.class,
						null,
						new Response.Listener<SampleImage>()
						{
							public void onResponse(SampleImage response)
							{
								Log.i(TAG, response.getTitle());
								//appendItemToList(response.items);
								//notifyDataSetChanged();
							}
						},
						new Response.ErrorListener()
						{
							@Override
							public void onErrorResponse(VolleyError volleyError)
							{
								Log.e(TAG, volleyError.getMessage(), volleyError);
							}
						}
				));

		return rootView;
	}

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(
				getArguments().getInt(ARG_SECTION_NUMBER));
	}

	@Override
	public void onStop()
	{
		super.onStop();
		mRequestQueue.cancelAll(this);
	}
}