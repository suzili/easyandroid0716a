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

import com.bumptech.glide.Glide;
import com.easyandroid.R;
import com.easyandroid.dto.Comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.easyandroid.util.Constant.IP;

/**
 * Created by NightStar on 2017/7/11.
 */

public class CommentListAdapter extends ArrayAdapter<Comment>{
    private Context context;
    private int resource;
    public CommentListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Comment> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Comment data = getItem(position);
        CommentListViewHolder viewHolder = null;
        if(convertView != null){
            viewHolder = (CommentListViewHolder) convertView.getTag();
        }else{
            viewHolder = new CommentListViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
            viewHolder.imageView_commenter_avatar = (ImageView) convertView.findViewById(R.id.imageView_commenter_avatar);
            viewHolder.textView_commenter_name = (TextView) convertView.findViewById(R.id.textView_commenter_name);
            viewHolder.textView_comment_content = (TextView) convertView.findViewById(R.id.textView_comment_content);
            viewHolder.layout_like_num = (LinearLayout) convertView.findViewById(R.id.layout_like_num);
            viewHolder.imageView_like_image = (ImageView) convertView.findViewById(R.id.imageView_like_image);
            viewHolder.textView_like_num = (TextView) convertView.findViewById(R.id.textView_like_num);
            viewHolder.textView_comment_time = (TextView) convertView.findViewById(R.id.textView_comment_time);
            convertView.setTag(viewHolder);
        }
        //评论者头像
        if(null != data.getCmerPor() && !"".equals(data.getCmerPor())){
            Glide.with(context).load(IP+data.getCmerPor()).error(R.drawable.club_avatar).into(viewHolder.imageView_commenter_avatar);
        }
        //评论者名称
        if(null != data.getCmerName() && !"".equals(data.getCmerName())){
            viewHolder.textView_commenter_name.setText(data.getCmerName());
        }
        //评论内容
        if(null != data.getContent() && !"".equals(data.getContent())){
            viewHolder.textView_comment_content.setText(data.getContent());
        }
        //点赞量
        if(!"".equals(data.getLikeCount()+"")){
            viewHolder.textView_like_num.setText(data.getLikeCount()+"");
        }
        //评论时间
        if(null != data.getCmTime() && !"".equals(data.getCmTime())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(data.getCmTime());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String pdate = sdf2.format(date);
                viewHolder.textView_comment_time.setText(pdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //还差点赞 功能 实现
        return convertView;
    }

    class CommentListViewHolder{
        private ImageView imageView_commenter_avatar;   //评论者头像
        private TextView textView_commenter_name;       //评论者名称
        private TextView textView_comment_content;      //评论内容
        private LinearLayout layout_like_num;            //包含点赞图标和数量的 layout
        private ImageView imageView_like_image;         //点赞图标
        private TextView textView_like_num;             //点赞数量
        private TextView textView_comment_time;         //评论时间
    }
}
