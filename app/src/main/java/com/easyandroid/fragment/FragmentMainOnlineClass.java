package com.easyandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.easyandroid.R;
import com.easyandroid.activity.OnlineClassDetailActivity;
import com.easyandroid.adapter.ClassOnlineChoosePopupWindow;
import com.easyandroid.adapter.ItemListOnlineClassAdapter;
import com.easyandroid.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 三臻 on 2017/7/9.
 */

public class FragmentMainOnlineClass extends BaseFragment {
	private ListView mListViewOnlineClassList;
	private ImageView mImageViewOpenNv;
	private TextView mTextViewTitle;
	private ImageView mImageViewChoose;
	private FrameLayout mFrameLayoutOnlineClassTitle;

	private List<String> typelist = new ArrayList<>();
	private List<String> stagelist = new ArrayList<>();
	private List<String> mainList = new ArrayList<>();

	private OpenNavListener mOpenNavListener;
	private ClassOnlineChoosePopupWindow mClassOnlineChoosePopupWindow;

	private Handler mHandler = new Handler();

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_online_class;
	}

	@Override
	protected void initView() {
		getArguments();
		mListViewOnlineClassList = (ListView) findViewById(R.id.listView_online_class_list);
		mImageViewOpenNv = (ImageView) findViewById(R.id.imageView_openNv);
		mTextViewTitle = (TextView) findViewById(R.id.textView_title);
		mImageViewChoose = (ImageView) findViewById(R.id.imageView_choose);

		mFrameLayoutOnlineClassTitle = (FrameLayout) findViewById(R.id.frameLayout_online_class_title);
	}

	@Override
	protected void bindEvent() {
		initClassList();
		initChooseView();
		mClassOnlineChoosePopupWindow = new ClassOnlineChoosePopupWindow(getActivity(), typelist, stagelist, new ClassOnlineChoosePopupWindow.ItemClickListener() {
			@Override
			public void typeClick(int position, String word) {
				ToastUtil.makeToastShort(getActivity(), "type:" + word);
			}

			@Override
			public void stageClick(int position, String word) {
				ToastUtil.makeToastShort(getActivity(), "stage:" + word);
			}

			@Override
			public void doOK(String typeWord, String stageWord, boolean isSortTime, String upOrDown) {
				String Sortby = "Hot";
				if (isSortTime) {
					Sortby = "Time";
				}
				ToastUtil.makeToastShort(getActivity(),
						"type:" + typeWord
								+ "\n" + "stage:" + stageWord
								+ "\n" + "Sortby:" + Sortby
								+ "\n" + "upOrDown:" + upOrDown);
				mClassOnlineChoosePopupWindow.dismiss();
			}

			@Override
			public void doCancel() {
				mClassOnlineChoosePopupWindow.dismiss();
			}
		});
		mClassOnlineChoosePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
//				mImageViewChoose.setImageResource(R.drawable.ic_list_online_class_type);
				setChooserAnimition(false);
			}
		});

		mImageViewChoose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mClassOnlineChoosePopupWindow.setHeight(mListViewOnlineClassList.getHeight());
				mClassOnlineChoosePopupWindow.setWidth(mFrameLayoutOnlineClassTitle.getWidth());
				mClassOnlineChoosePopupWindow.showAsDropDown(mFrameLayoutOnlineClassTitle);
//				mImageViewChoose.setImageResource(R.drawable.ic_list_online_class_type_fill);
				setChooserAnimition(true);
			}
		});
	}

	private void initClassList() {
		for (int i = 0; i < 12; i++) {
			mainList.add(i + "人点赞");
		}
		mImageViewOpenNv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mOpenNavListener.openNav();
			}
		});
		mListViewOnlineClassList.setAdapter(new ItemListOnlineClassAdapter(getActivity(), mainList));
		mListViewOnlineClassList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ToastUtil.makeToastShort(getActivity(), "position" + position);
				startActivity(new Intent(getActivity(), OnlineClassDetailActivity.class));
			}
		});
	}

	private void initChooseView() {
		for (int i = 0; i < 5; i++) {
			typelist.add("type" + i);
		}
		for (int i = 0; i < 3; i++) {
			stagelist.add("stage" + i);
		}
	}

	public interface OpenNavListener {
		void openNav();
	}

	public void SetOpenNavListener(OpenNavListener mOpenNavListener) {
		this.mOpenNavListener = mOpenNavListener;
	}

	private void setChooserAnimition(final boolean isOpen) {
		final int sleepTime = 70;
		new Thread() {
			@Override
			public void run() {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						if (isOpen) {
							mImageViewChoose.setImageResource(R.drawable.ic_list_online_class_type_fill_a);
						} else {
							mImageViewChoose.setImageResource(R.drawable.ic_list_online_class_type_fill_c);
						}
					}
				});
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						if (isOpen) {
							mImageViewChoose.setImageResource(R.drawable.ic_list_online_class_type_fill_b);
						} else {
							mImageViewChoose.setImageResource(R.drawable.ic_list_online_class_type_fill_b);
						}
					}
				});
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						if (isOpen) {
							mImageViewChoose.setImageResource(R.drawable.ic_list_online_class_type_fill_c);
						} else {
							mImageViewChoose.setImageResource(R.drawable.ic_list_online_class_type_fill_a);
						}
					}
				});
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						if (isOpen) {
							mImageViewChoose.setImageResource(R.drawable.ic_list_online_class_type_fill);
						} else {
							mImageViewChoose.setImageResource(R.drawable.ic_list_online_class_type);
						}
					}
				});
			}
		}.start();
	}
}
