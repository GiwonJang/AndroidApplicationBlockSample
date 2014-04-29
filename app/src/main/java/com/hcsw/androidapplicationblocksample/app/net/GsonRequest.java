package com.hcsw.androidapplicationblocksample.app.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Volley adapter for JSON requests that will be parsed into Java objects by Gson.
 */
public class GsonRequest<T> extends Request<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(GsonRequest.class);

	private final Gson gson = new Gson();
	private final Class<T> clazz;
	private final Map<String, String> headers;
	private final Listener<T> listener;
	private final Map<String, String> requestParams = new HashMap<String, String>();

	/**
	 * Make a GET request and return a parsed object from JSON.
	 *
	 * @param url URL of the request to make
	 * @param clazz Relevant class object, for Gson's reflection
	 * @param headers Map of request headers
	 */
	public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Map<String, String> requestParams,
	                   Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.clazz = clazz;
		this.headers = headers;
		this.listener = listener;
		if (requestParams != null)
			this.requestParams.putAll(requestParams);
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers != null ? headers : super.getHeaders();
	}

	@Override
	public Map<String, String> getParams() {
		return requestParams.size() > 0 ? requestParams : null;
	}

	@Override
	protected void deliverResponse(T response) {
		listener.onResponse(response);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

			LOGGER.debug("NetworkResponse : " + json);

			return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}
}