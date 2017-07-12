package com.easyandroid.util;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * github : https://github.com/oblivion0001/AndroidStudioProjects
 * Blog : http://blog.csdn.net/qq_16666847
 * Created by oblivion on 2016/12/19.
 */
public class GalleyTransFormer implements ViewPager.PageTransformer {
	private static final float min_scale = 0.85f;

	@Override
	public void transformPage(View page, float position) {
		float scaleFactor = Math.max(min_scale, 1 - Math.abs(position));
		float rotate = 20 * Math.abs(position);
		if (position < -1) {
		} else if (position < 0) {
			page.setScaleX(scaleFactor);
			page.setScaleY(scaleFactor);
			page.setRotationY(rotate);
		} else if (position >= 0 && position < 1) {
			page.setScaleX(scaleFactor);
			page.setScaleY(scaleFactor);
			page.setRotationY(-rotate);
		} else if (position >= 1) {
			page.setScaleX(scaleFactor);
			page.setScaleY(scaleFactor);
			page.setRotationY(-rotate);
		}
//		if (position < -1) {
//
//		} else if (position < 0) {
//			page.setScaleX(scaleFactor);
//			page.setScaleY(scaleFactor);
//			page.setRotationY(rotate);
//		} else if (position >= 0 && position < 0.25) {
//			page.setScaleX(scaleFactor);
//			page.setScaleY(scaleFactor);
//			page.setRotationY(-rotate);
//		}else if (position >= 0.25 && position < 0.5) {
//			page.setScaleX(scaleFactor);
//			page.setScaleY(scaleFactor);
//			page.setRotationY(-rotate);
//		}else if (position >= 0.5 && position < 0.75) {
//			page.setScaleX(scaleFactor);
//			page.setScaleY(scaleFactor);
//			page.setRotationY(-rotate);
//		}else if (position >= 0.75 && position < 1) {
//			page.setScaleX(scaleFactor);
//			page.setScaleY(scaleFactor);
//			page.setRotationY(-rotate);
//		} else if (position >= 1) {
//			page.setScaleX(scaleFactor);
//			page.setScaleY(scaleFactor);
//			page.setRotationY(-rotate);
//		}
	}
}