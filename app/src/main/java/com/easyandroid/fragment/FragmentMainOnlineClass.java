package com.easyandroid.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.easyandroid.R;
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

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_online_class;
	}

	@Override
	protected void initView() {
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
			public void typeClick(String word) {
				ToastUtil.makeToastShort(getActivity(), "type:" + word);
			}

			@Override
			public void stageClick(String word) {
				ToastUtil.makeToastShort(getActivity(), "stage:" + word);
			}

			@Override
			public void doOK(String typeWord, String stageWord, String timeWord, String hotWord) {
				ToastUtil.makeToastShort(getActivity(),
						"type:" + typeWord
								+ "\n" + "stage:" + stageWord
								+ "\n" + "time:" + timeWord
								+ "\n" + "hot:" + hotWord);
				mClassOnlineChoosePopupWindow.dismiss();
			}

			@Override
			public void doCancel() {
				mClassOnlineChoosePopupWindow.dismiss();
			}
		});
		mImageViewChoose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mClassOnlineChoosePopupWindow.setWidth(mFrameLayoutOnlineClassTitle.getWidth());
				mClassOnlineChoosePopupWindow.showAsDropDown(mFrameLayoutOnlineClassTitle);
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
	}

	private void initChooseView() {
		for (int i = 0; i < 15; i++) {
			typelist.add("type" + i);
		}
		for (int i = 0; i < 15; i++) {
			stagelist.add("stage" + i);
		}

	}

	public interface OpenNavListener {
		void openNav();
	}

	public void SetOpenNavListener(OpenNavListener mOpenNavListener) {
		this.mOpenNavListener = mOpenNavListener;
	}
}
