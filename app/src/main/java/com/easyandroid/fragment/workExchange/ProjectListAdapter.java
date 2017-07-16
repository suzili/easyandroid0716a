package com.easyandroid.fragment.workExchange;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.easyandroid.R;
import com.easyandroid.dto.ExProjectInfo;

import java.io.File;
import java.net.URL;
import java.util.List;

import static com.easyandroid.util.Constant.IP;

/**
 * Created by NightStar on 2017/7/13.
 */

public class ProjectListAdapter extends ArrayAdapter<ExProjectInfo>{
    private Context context;
    private int resouceid;
    public ProjectListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ExProjectInfo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resouceid = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ExProjectInfo data = getItem(position);
        ProjectViewHolder viewHolder ;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resouceid,parent,false);
            viewHolder = new ProjectViewHolder();
            viewHolder.project_imageView = (RoundedRectangleView) convertView.findViewById(R.id.imageView_project_picture);
            viewHolder.project_introduct_textView = (TextView)convertView.findViewById(R.id.textView_project_brief);
            viewHolder.project_name_textView = (TextView) convertView.findViewById(R.id.textView_projectName);
            viewHolder.project_type_textView = (TextView) convertView.findViewById(R.id.textView_project_type);
            viewHolder.project_browseNum_textView  = (TextView) convertView.findViewById(R.id.textView_browse_num);
            viewHolder.project_releaseTime_textView = (TextView) convertView.findViewById(R.id.textView_upload_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ProjectViewHolder) convertView.getTag();
        }
        //展示图片设值
        String[] images = data.getImages().split(",");
        String url = IP+"/"+images[0];
        Glide.with(context).load(url).error(R.drawable.club_avatar).into(viewHolder.project_imageView);
        Log.e("iamge",url);
        //项目简介
        if(!"".equals(data.getIntroduce())){
            viewHolder.project_introduct_textView.setText(data.getIntroduce());
        }
        //项目名称
        if(!"".equals(data.getProName())){
            viewHolder.project_name_textView.setText("项目名称: "+data.getProName());
        }
        //项目类型
        if(!"".equals(data.getClassType())){
            viewHolder.project_type_textView.setText("类型: "+data.getClassType());
        }
        //浏览人数
        if(!"".equals(data.getSeeCount())){
            viewHolder.project_browseNum_textView.setText(data.getSeeCount()+" 人浏览");
        }
        //发布时间
        if(!"".equals(data.getUpData())){
            viewHolder.project_releaseTime_textView.setText(data.getUpData());
        }
        return convertView;
    }

    class ProjectViewHolder{
        private ImageView project_imageView;
        private TextView project_introduct_textView;
        private TextView project_name_textView;
        private TextView project_type_textView;
        private TextView project_browseNum_textView;
        private TextView project_releaseTime_textView;
    }
}
