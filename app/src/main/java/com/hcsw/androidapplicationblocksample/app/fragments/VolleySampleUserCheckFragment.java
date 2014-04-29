package com.hcsw.androidapplicationblocksample.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hcsw.androidapplicationblocksample.app.net.GsonRequest;
import com.hcsw.androidapplicationblocksample.app.R;
import com.hcsw.androidapplicationblocksample.app.entities.UserCheckData;
import com.hcsw.androidapplicationblocksample.app.helpers.UIHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by gwjang on 2014. 3. 20..
 */
public class VolleySampleUserCheckFragment extends BaseFragment {

	private static final Logger LOGGER = LoggerFactory.getLogger(VolleySampleUserCheckFragment.class);
	private static final String API_APP_VER_URL = "https://testapi.ollehtvcast.com/api/user/check";

	private RequestQueue mRequestQueue;
	@InjectView(R.id.tv_app_ver)
	TextView tv_app_ver;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View rootView = inflater.inflate(R.layout.fragment_volley_sample_app_version, container, false);
		ButterKnife.inject(this, rootView);

		mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

		int dp = UIHelper.convertPxToDp(1080);

		return rootView;
	}

	@Override
	public void onStop() {
		super.onStop();
		mRequestQueue.cancelAll(this);
	}

	@OnClick(R.id.btn_get_app_ver)
	public void onGetAppVer(Button button) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json; charset=utf-8");
		headers.put("content-type", "application/json; charset=UTF-8");

		Map<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("device_id", "aaa");
		requestParams.put("platform", "AM");
		requestParams.put("type", "E");
		requestParams.put("value", "spponge@hotmail.com");

		/*mRequestQueue.add(
				new StringRequest(
						Request.Method.GET,
						"https://api.twitter.com/1.1/search/tweets?q=android",
						new Response.Listener<String>() {
							public void onResponse(String response) {
								LOGGER.info(response);
								tv_app_ver.setText(response);
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

		mRequestQueue.add(
				new GsonRequest<UserCheckData>(
						Request.Method.GET,
						API_APP_VER_URL,
						UserCheckData.class,
						headers,
						requestParams,
						new Response.Listener<UserCheckData>() {
							public void onResponse(UserCheckData response) {
								LOGGER.info(Integer.toString(response.getData().getCount()));
								tv_app_ver.setText(Integer.toString(response.getData().getCount()));
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError volleyError) {
								LOGGER.error(volleyError.getMessage(), volleyError);
							}
						}
				)
		);

		//JsonObject jsonObject = new JsonObject();
		//jsonObject.addProperty("", "");

		/*mRequestQueue.add(new JsonObjectRequest(Request.Method.GET, API_APP_VER_URL, headers,
				new Response.Listener<JSONObject>() {
					public void onResponse(JSONObject jsonRoot) {
						tv_app_ver.setText(jsonRoot.toString());
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						LOGGER.error(volleyError.getMessage(), volleyError);
					}
				}
		));*/

		/*mRequestQueue.add(new JsonRequest<UserCheckData>(Request.Method.GET, url, null,
				new Response.Listener<JSONObject>() {
					public void onResponse(JSONObject jsonRoot) {
						mNextPageToken = jsonGet(jsonRoot, "next", null);
						List<Items> items = parseJson(jsonRoot);
						appendItemsToList(item);
						notifyDataSetChanged();
					}
				}
		);*/
	}
}
