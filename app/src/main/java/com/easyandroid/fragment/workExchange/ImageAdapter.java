package com.easyandroid.fragment.workExchange;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.easyandroid.R;

import static com.easyandroid.util.Constant.IP;

/**
 * Created by NightStar on 2017/7/11.
 */

public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private int selectItem;
    private String[] imageData_list;

    public ImageAdapter(Context context,String[] imageData_list) {
        mContext = context;
        this.imageData_list = imageData_list;
    }
    @Override
    public int getCount() {
//        return Integer.MAX_VALUE;//最大值能使图片无限滑动
        return imageData_list.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new RoundedRectangleView(mContext);
//        imageView.setImageResource(imageData_list[position%imageData_list.length]);//实现循环滑动
//        imageView.setImageResource(imageData_list[position%imageData_list.length]);
        Glide.with(mContext).load(IP+"/"+imageData_list[position]).error(R.drawable.club_avatar).into(imageView);
        if(selectItem==position){
            //图片选中是，设置图片为大图
            imageView.setLayoutParams(new Gallery.LayoutParams(100*3,139*3));
        }
        else{
            //图片未选中时，设置图片为小图
            imageView.setLayoutParams(new Gallery.LayoutParams(76*3,106*3));
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
}
