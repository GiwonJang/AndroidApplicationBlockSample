package com.hcsw.androidapplicationblocksample.app;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * Created by gwjang on 2014. 3. 5..
 */
public class GZipRequest extends StringRequest {

	public GZipRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}

	public GZipRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
		super(url, listener, errorListener);
	}

	// parse the gzip response using a GZIPInputStream
	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		StringBuilder output = new StringBuilder();
		InputStreamReader reader = null;
		BufferedReader in = null;
		GZIPInputStream gStream = null;
		try {
			gStream = new GZIPInputStream(new ByteArrayInputStream(response.data));
			reader = new InputStreamReader(gStream);
			in = new BufferedReader(reader);
			String read;
			while ((read = in.readLine()) != null) {
				output.append(read);
			}
		} catch (IOException e) {
			return Response.error(new ParseError());
		} finally {
			try {
				if (reader != null) reader.close();
				if (in != null) in.close();
				if (gStream != null) gStream.close();
			} catch (IOException e) {/* do nothing... */}
		}
		return Response.success(output.toString(), HttpHeaderParser.parseCacheHeaders(response));
	}
}