package com.hcsw.androidapplicationblocksample.app.helpers;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import com.hcsw.androidapplicationblocksample.app.MyApplication;

/**
 * Created by gwjang on 2014. 4. 29..
 */
public class UIHelper {

	public static int convertPxToDp(int px) {
		WindowManager wm = (WindowManager) MyApplication.getAppContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		float logicalDensity = metrics.density;
		int dp = Math.round(px / logicalDensity);
		return dp;
	}

	public static int convertDpToPx(int dp) {
		return Math.round(
				TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
						MyApplication.getAppResources().getDisplayMetrics())
		);
	}
}
