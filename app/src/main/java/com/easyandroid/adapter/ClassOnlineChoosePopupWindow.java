package com.easyandroid.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.easyandroid.R;

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

	private List<String> typelist;
	private List<String> stagelist;
	private TypeAdapter mTypeAdapter;
	private Context context;

	private String typeWord = "";
	private String stageWord = "";
	private String timeWord = "down";
	private String hotWord = "down";

	private ItemClickListener mItemClickListener;

	public ClassOnlineChoosePopupWindow(Context context, List<String> typelist, List<String> stagelist, ItemClickListener mItemClickListener) {
		super(context);
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.typelist = typelist;
		this.stagelist = stagelist;
		this.mItemClickListener = mItemClickListener;
		init();
	}

	private void init() {
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
			}
		});
		mButtonSortHot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if ("down".equals(hotWord)) {
					mButtonSortHot.setText("时间升序");
					hotWord = "up";
				} else {
					mButtonSortHot.setText("时间降序");
					hotWord = "down";
				}
			}
		});
		mGridViewType.setAdapter(new TypeAdapter());
		mGridViewStage.setAdapter(new StageAdapter());
		mButtonSortOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mItemClickListener.doOK(typeWord, stageWord,timeWord,hotWord);
			}
		});
		mButtonSortCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mItemClickListener.doCancel();
			}
		});
	}

	public class TypeAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return typelist.size();
		}

		@Override
		public Object getItem(int position) {
			return typelist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.item_online_class_type, null);
				holder.mButtonType = (Button) convertView.findViewById(R.id.button_type);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.mButtonType.setText(typelist.get(position));
			final ViewHolder finalHolder = holder;
			holder.mButtonType.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mItemClickListener.typeClick(finalHolder.mButtonType.getText() + "");
					typeWord = finalHolder.mButtonType.getText() + "";
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
		public Object getItem(int position) {
			return stagelist.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.item_online_class_stage, null);
				holder.mButtonStage = (Button) convertView.findViewById(R.id.button_stage);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.mButtonStage.setText(stagelist.get(position));
			final ViewHolder finalHolder = holder;
			holder.mButtonStage.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mItemClickListener.stageClick(finalHolder.mButtonStage.getText() + "");
					stageWord = finalHolder.mButtonStage.getText() + "";
				}
			});
			return convertView;
		}

		private class ViewHolder {
			private Button mButtonStage;
		}
	}

	public interface ItemClickListener {
		void typeClick(String word);

		void stageClick(String word);

		void doOK(String typeWord, String stageWord, String timeWord, String hotWord);

		void doCancel();
	}
}
