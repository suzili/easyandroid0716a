package com.easyandroid.fragment.workExchange;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easyandroid.R;
import com.easyandroid.dto.ExProjectInfo;

import java.util.List;

import static com.easyandroid.util.Constant.IP;

/**
 * Created by NightStar on 2017/7/10.
 * 项目交流界面  listview 适配器
 */

public class WorkExchangListAdapter extends ArrayAdapter<ExProjectInfo>{
    private Context context;
    private int resource;
    public WorkExchangListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ExProjectInfo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ExProjectInfo data = getItem(position);
        WE_listViewHolder viewHolder;
        if (convertView != null){
            viewHolder = (WE_listViewHolder) convertView.getTag();
        }else{
            viewHolder = new WE_listViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
            //交流项目展示图片
            viewHolder.iamgeView_project_iamge = (ImageView) convertView.findViewById(R.id.imageView_project_picture);
            //简介
            viewHolder.textView_project_brief = (TextView) convertView.findViewById(R.id.textView_project_brief);
            //项目名称
            viewHolder.textView_projectName = (TextView) convertView.findViewById(R.id.textView_projectName);
            //项目类型
            viewHolder.textView_projectType = (TextView) convertView.findViewById(R.id.textView_project_type);
            //浏览量
            viewHolder.imageView_browse_image = (ImageView) convertView.findViewById(R.id.imageView_browse_image);
            viewHolder.textView_browse_num = (TextView) convertView.findViewById(R.id.textView_browse_num);
            //上传时间
            viewHolder.textView_upload_time = (TextView) convertView.findViewById(R.id.textView_upload_time);
            convertView.setTag(viewHolder);
        }
        //设置 项目简介
        if(!"".equals(data.getIntroduce())){
            if(data.getIntroduce().length()>= 130){
                viewHolder.textView_project_brief.setText(data.getIntroduce().substring(0,127)+"...");
            }else{
                viewHolder.textView_project_brief.setText(data.getIntroduce());
            }
        }
        //设置项目名称
        viewHolder.textView_projectName.setText("项目名称："+data.getProName());
        //设置 项目图片,首先取到3张图片,选择第一张展示
        String[] images = data.getImages().split(",");
        Glide.with(context).load(IP+""+images[0]).error(R.drawable.club_avatar).into(viewHolder.iamgeView_project_iamge);
        //设置项目类型
        if(!"".equals(data.getClassType())){
            viewHolder.textView_projectType.setText(data.getClassType());
        }
        //设置浏览量
        if(!"".equals(data.getSeeCount())){
            viewHolder.textView_browse_num.setText(data.getSeeCount());
        }
        //设置项目上传时间
        if(!"".equals(data.getUpData())){
            viewHolder.textView_upload_time.setText(data.getUpData());
        }
        return convertView;
    }

    class WE_listViewHolder{
        private ImageView iamgeView_project_iamge;
        private TextView textView_project_brief;
        private TextView textView_projectName;
        private TextView textView_projectType;

        //点赞量   取消
        /*private LinearLayout layout_like_num;
        private ImageView imageView_like_image;
        private TextView textView_like_num;*/

        private ImageView imageView_browse_image;
        private TextView textView_browse_num;

        //下载量   取消
        /*private ImageView imageView_downlod_image;
        private TextView textView_download_num;*/

        private TextView textView_upload_time;
    }
}
