package com.easyandroid.activity;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.easyandroid.R;
import com.easyandroid.util.GalleyTransFormer;
import com.easyandroid.view.RoundedRectangleView;

public class OnlineClassDetailActivity extends AppCompatActivity {

	private ImageView mImageViewBack;
	private ViewPager mViewpagerOnlineClassDetail;
	private FrameLayout mFramelayoutBanner;
	private ImageView mImageViewTurnLeft;
	private ImageView mImageViewTurnRight;

	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_online_class_detail);

		initView();
		initBanner();
	}

	private void initView() {
		mImageViewBack = (ImageView) findViewById(R.id.imageView_back);
		mViewpagerOnlineClassDetail = (ViewPager) findViewById(R.id.viewpager_online_class_detail);
		mFramelayoutBanner = (FrameLayout) findViewById(R.id.framelayout_banner);
		mImageViewTurnLeft = (ImageView) findViewById(R.id.imageView_turn_left);
		mImageViewTurnRight = (ImageView) findViewById(R.id.imageView_turn_right);

		mFramelayoutBanner.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return mViewpagerOnlineClassDetail.dispatchTouchEvent(event);
			}
		});
		mImageViewTurnLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				turnViewPager(false);
			}
		});
		mImageViewTurnRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				turnViewPager(true);
			}
		});
		mImageViewBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void initBanner() {
		mViewpagerOnlineClassDetail.setPageTransformer(true, new GalleyTransFormer());
		mViewpagerOnlineClassDetail.setOffscreenPageLimit(3);
		mViewpagerOnlineClassDetail.setAdapter(new PagerAdapter() {
			@Override
			public int getCount() {
				return 3;
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView((View) object);
			}

			@Override
			public Object instantiateItem(ViewGroup container, final int position) {
				RoundedRectangleView mRoundedRectangleView = new RoundedRectangleView(getBaseContext());
				mRoundedRectangleView.setRoundPx(30);
				mRoundedRectangleView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				mRoundedRectangleView.setImageResource(R.drawable.pic_banner_pic);
				mRoundedRectangleView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mViewpagerOnlineClassDetail.setCurrentItem(position);
					}
				});
				container.addView(mRoundedRectangleView);
				return mRoundedRectangleView;

//				ImageView imageView = new ImageView(getBaseContext());
//				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//				imageView.setImageResource(R.drawable.pic_banner_red);
//				imageView.setBackgroundResource(R.drawable.bg_box_radius_green);
//				imageView.setOnClickListener(new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						mViewpagerOnlineClassDetail.setCurrentItem(position);
//					}
//				});
//				container.addView(imageView);
//				return imageView;
			}
		});
		mViewpagerOnlineClassDetail.setCurrentItem(1);
	}

	private void turnViewPager(boolean isRight){
		int thisIndex = mViewpagerOnlineClassDetail.getCurrentItem();
		if (isRight){
			if (thisIndex != 2){
				mViewpagerOnlineClassDetail.setCurrentItem(++thisIndex);
			}
		}else{
			if (thisIndex != 0){
				mViewpagerOnlineClassDetail.setCurrentItem(--thisIndex);
			}
		}
	}
}
