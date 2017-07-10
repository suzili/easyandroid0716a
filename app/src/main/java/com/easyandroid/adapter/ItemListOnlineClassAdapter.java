package com.easyandroid.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyandroid.R;

/**
 * Created by 三臻 on 2017/7/10.
 */
public class ItemListOnlineClassAdapter extends BaseAdapter {

	private List<String> objects = new ArrayList<>();

	private Context context;
	private LayoutInflater layoutInflater;

	public ItemListOnlineClassAdapter(Context context,List<String> objects) {
		this.context = context;
		this.objects = objects;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return objects.size();
	}

	@Override
	public String getItem(int position) {
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.item_list_online_class, null);
			convertView.setTag(new ViewHolder(convertView));
		}
		initializeViews((String) getItem(position), (ViewHolder) convertView.getTag());
		return convertView;
	}

	private void initializeViews(String object, ViewHolder holder) {
		holder.textViewItemOnlineClass.setText(object);
	}

	protected class ViewHolder {
		private ImageView imageViewItemOnlineClassImg;
		private TextView textViewItemOnlineClassMain;
		private TextView textViewItemWorkFrom;
		private TextView textViewItemStage;
		private TextView textViewItemType;
		private TextView textViewTime;
		private TextView textViewItemOnlineClass;

		public ViewHolder(View view) {
			imageViewItemOnlineClassImg = (ImageView) view.findViewById(R.id.imageView_item_online_class_img);
			textViewItemOnlineClassMain = (TextView) view.findViewById(R.id.textView_item_online_class_main);
			textViewItemWorkFrom = (TextView) view.findViewById(R.id.textView_item_work_from);
			textViewItemStage = (TextView) view.findViewById(R.id.textView_item_stage);
			textViewItemType = (TextView) view.findViewById(R.id.textView_item_type);
			textViewTime = (TextView) view.findViewById(R.id.textView_time);
			textViewItemOnlineClass = (TextView) view.findViewById(R.id.textView_item_online_class);
		}
	}
}

