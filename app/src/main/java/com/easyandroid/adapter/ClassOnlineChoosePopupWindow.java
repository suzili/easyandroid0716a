package com.easyandroid.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.easyandroid.R;
import com.easyandroid.dto.APPType;
import com.easyandroid.util.Constant;
import com.easyandroid.util.HttpUtil;
import com.easyandroid.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 三臻 on 2017/7/10.
 */

public class ClassOnlineChoosePopupWindow<T> extends PopupWindow {
	private LayoutInflater inflater;

	private GridView mGridViewType;
	private GridView mGridViewStage;
	private Button mButtonSortTime;
	private Button mButtonSortHot;
	private Button mButtonSortOk;
	private Button mButtonSortCancel;

	private Context context;

	private APPType typeWord = new APPType();
	private APPType stageWord = new APPType();
	private String timeWord = "down";
	private String hotWord = "down";
	private boolean sortIsTime = true;

	private ItemClickListener mItemClickListener;
	public ItemSelectListener mItemSelectListener;

	private int selectTypeNum = 0;
	private int selectStageNum = 0;

	private TypeAdapter mTypeAdapter;
	private StageAdapter mStageAdapter;

	private List<APPType> typelist = new ArrayList<>();
	private List<APPType> stagelist = new ArrayList<>();

	private Handler mHandler = new Handler();

	public ClassOnlineChoosePopupWindow(Context context, ItemClickListener mItemClickListener) {
		super(context);
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.mItemClickListener = mItemClickListener;

		initView();
	}

	private void initView() {
		View view = inflater.inflate(R.layout.popupwindow_online_class_choose, null);
		setContentView(view);
		setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
		setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0x00);
		setBackgroundDrawable(dw);

		mGridViewType = (GridView) view.findViewById(R.id.gridView_type);
		mGridViewStage = (GridView) view.findViewById(R.id.gridView_stage);
		mButtonSortTime = (Button) view.findViewById(R.id.button_sort_time);
		mButtonSortHot = (Button) view.findViewById(R.id.button_sort_hot);
		mButtonSortOk = (Button) view.findViewById(R.id.button_sort_ok);
		mButtonSortCancel = (Button) view.findViewById(R.id.button_sort_cancel);

		mButtonSortTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("down".equals(timeWord)) {
					mButtonSortTime.setText("时间升序");
					timeWord = "up";
				} else {
					mButtonSortTime.setText("时间降序");
					timeWord = "down";
				}
				sortIsTime = true;
				setCheckTime(true);
			}
		});
		mButtonSortHot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("down".equals(hotWord)) {
					mButtonSortHot.setText("热度升序");
					hotWord = "up";
				} else {
					mButtonSortHot.setText("热度降序");
					hotWord = "down";
				}
				sortIsTime = false;
				setCheckTime(false);
			}
		});

		mButtonSortOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (sortIsTime) {
					mItemClickListener.doOK(typeWord.getId() + "", stageWord.getId() + "", sortIsTime, timeWord);
				} else {
					mItemClickListener.doOK(typeWord.getId() + "", stageWord.getId() + "", sortIsTime, hotWord);
				}
			}
		});
		mButtonSortCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mItemClickListener.doCancel();
			}
		});

		mItemSelectListener = new ItemSelectListener() {
			@Override
			public void select(int typePosition, int stagePosition) {
				if (typePosition >= 0) {
					selectTypeNum = typePosition;
					mTypeAdapter.notifyDataSetChanged();
				}
				if (stagePosition >= 0) {
					selectStageNum = stagePosition;
					mStageAdapter.notifyDataSetChanged();
				}
			}
		};
		initTypeData();
		initStageData();
	}

	private void initTypeData() {
		new Thread() {
			@Override
			public void run() {
				HttpUtil mHttpUtil = HttpUtil.getInstance();
				mHttpUtil.postJson(Constant.apptype, new JsonObject().toString(), new HttpUtil.PostCallBack() {
					@Override
					public void success(final String res) {
						typelist = Arrays.asList(new Gson().fromJson(res, APPType[].class));
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								initTypeAdapter();
							}
						});
					}

					@Override
					public void error(final Exception e) {
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								ToastUtil.makeToastShort(context, e + "");
							}
						});
					}
				});
			}
		}.start();
	}

	private void initStageData() {
		new Thread() {
			@Override
			public void run() {
				HttpUtil mHttpUtil = HttpUtil.getInstance();
				mHttpUtil.postJson(Constant.appstage, new JsonObject().toString(), new HttpUtil.PostCallBack() {
					@Override
					public void success(final String res) {
						stagelist = Arrays.asList(new Gson().fromJson(res, APPType[].class));
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								initStageAdapter();
							}
						});
					}

					@Override
					public void error(final Exception e) {
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								ToastUtil.makeToastShort(context, e + "");
							}
						});
					}
				});
			}
		}.start();
	}

	private void initTypeAdapter() {
		if (typelist.size() > 0) {
			typeWord = typelist.get(0);
		}
		mTypeAdapter = new TypeAdapter();
		mGridViewType.setAdapter(mTypeAdapter);
	}

	private void initStageAdapter() {
		if (stagelist.size() > 0) {
			stageWord = stagelist.get(0);
		}
		mStageAdapter = new StageAdapter();
		mGridViewStage.setAdapter(mStageAdapter);
	}

	public class TypeAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return typelist.size();
		}

		@Override
		public APPType getItem(int position) {
			return typelist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_online_class_type, null);
			holder.mButtonType = (Button) convertView.findViewById(R.id.button_type);

			holder.mButtonType.setText(typelist.get(position).getName());
			if (position == selectTypeNum) {
				holder.mButtonType.setTextColor(context.getResources().getColor(R.color.colorWhite));
				holder.mButtonType.setBackgroundResource(R.drawable.bg_box_radius_green);
			}
			final ViewHolder finalHolder = holder;
			holder.mButtonType.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mItemClickListener.typeClick(position, finalHolder.mButtonType.getText() + "");
					typeWord = typelist.get(position);
					mItemSelectListener.select(position, -1);
				}
			});
			return convertView;
		}

		private class ViewHolder {
			private Button mButtonType;
		}
	}

	public class StageAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return stagelist.size();
		}

		@Override
		public APPType getItem(int position) {
			return stagelist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_online_class_stage, null);
			holder.mButtonStage = (Button) convertView.findViewById(R.id.button_stage);
			convertView.setTag(holder);
			holder.mButtonStage.setText(stagelist.get(position).getName());
			if (position == selectStageNum) {
				holder.mButtonStage.setTextColor(context.getResources().getColor(R.color.colorWhite));
				holder.mButtonStage.setBackgroundResource(R.drawable.bg_box_radius_deepblue);
			}
			final ViewHolder finalHolder = holder;
			holder.mButtonStage.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mItemClickListener.stageClick(position, finalHolder.mButtonStage.getText() + "");
					stageWord = stagelist.get(position);
					mItemSelectListener.select(-1, position);
				}
			});
			return convertView;
		}

		private class ViewHolder {
			private Button mButtonStage;
		}
	}

	public interface ItemClickListener {
		void typeClick(int position, String word);

		void stageClick(int position, String word);

		void doOK(String typeWord, String stageWord, boolean isSortTime, String upOrDown);

		void doCancel();
	}

	public interface ItemSelectListener {
		void select(int typePosition, int stagePosition);
	}

	private void setCheckTime(boolean isTime) {
		if (isTime) {
			mButtonSortTime.setTextColor(context.getResources().getColor(R.color.colorWhite));
			mButtonSortTime.setBackgroundResource(R.drawable.bg_box_radius_orange);

			mButtonSortHot.setTextColor(context.getResources().getColor(R.color.colorBlack));
			mButtonSortHot.setBackgroundResource(R.drawable.bg_line_radius_orange);
		} else {
			mButtonSortTime.setTextColor(context.getResources().getColor(R.color.colorBlack));
			mButtonSortTime.setBackgroundResource(R.drawable.bg_line_radius_orange);

			mButtonSortHot.setTextColor(context.getResources().getColor(R.color.colorWhite));
			mButtonSortHot.setBackgroundResource(R.drawable.bg_box_radius_orange);

		}
	}
}
