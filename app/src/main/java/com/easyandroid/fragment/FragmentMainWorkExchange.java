package com.easyandroid.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.easyandroid.R;
import com.easyandroid.dto.APPType;
import com.easyandroid.dto.ExProjectFilter;
import com.easyandroid.dto.ExProjectInfo;
import com.easyandroid.fragment.workExchange.ProjectDetailsActivity;
import com.easyandroid.fragment.workExchange.ProjectListAdapter;
import com.easyandroid.fragment.workExchange.ProjectReleaseActivity;
import com.easyandroid.util.OpenNavListener;
import com.easyandroid.util.RequestUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.easyandroid.util.Constant.IP;
import static com.easyandroid.util.Constant.user;

/**
 * Created by 三臻 on 2017/7/9.
 */

public class FragmentMainWorkExchange extends BaseFragment {
    private ListView showWordExchangeListView;
    private FrameLayout mFrameLayoutWorkExchangeTitle;
    private ImageView mImageViewOpenNv;
    private TextView mTextViewTitle;
    private ImageView mImageViewChoose;
    private Button button_project_release;
    private OpenNavListener mOpenNavListener;
    //  定义 储存  项目类型  和 排序方式 值的变量
    private String[] type_value = {"",""};
    private String[] sort_type = {"时间降序","时间升序","热度降序","热度升序"};
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work_exchange;
    }

    @Override
    protected void initView() {
        showWordExchangeListView = (ListView) findViewById(R.id.showWordExchangeListView);
        mFrameLayoutWorkExchangeTitle = (FrameLayout) findViewById(R.id.frameLayout_work_exchange_title);
        mImageViewOpenNv = (ImageView) findViewById(R.id.imageView_openNv);
        mTextViewTitle = (TextView) findViewById(R.id.textView_title);
        mImageViewChoose = (ImageView) findViewById(R.id.imageView_choose);
        button_project_release = (Button) findViewById(R.id.button_project_release);
        /*//测试数据
        String dataString = "[{\"id\":9,\"pubId\":1,\"pubPort\":\"upload/schooluser/por/f66ba4fa-0e90-4a4b-8e62-6b34bab09330.jpg\",\"pubName\":\"1212\",\"likeCount\":0,\"seeCount\":0,\"images\":\"upload/exProject/images/7edc94d8-e1be-48b0-a593-34033a2a7acc.jpg,upload/exProject/images/9a837552-e846-4285-894a-8beba068a9f9.jpg,upload/exProject/images/0d19bdcc-3a0c-40a5-bc70-21db12aea329.jpg\",\"classType\":\"aaaaaaa\",\"introduce\":\"1231232132\",\"requireDoc\":\"upload/exProject/reqWord/e24159ba-451e-476d-9d3d-f04efb037e62.doc\",\"desDoc\":\"upload/exProject/desWord/071768f7-ae4a-46c2-81a7-8d581f76ddb5.doc\",\"code\":\"upload/exProject/code/de4022e5-bcf7-4b95-a1b9-e6dd14d79601.zip\",\"apk\":\"upload/exProject/apk/a25b3854-45da-42f2-9db8-7499c841d90c.apk\",\"upData\":\"2017年07月13日 16:45:15\",\"proName\":\"1231\"},{\"id\":8,\"pubId\":1,\"pubPort\":\"upload/schooluser/por/f66ba4fa-0e90-4a4b-8e62-6b34bab09330.jpg\",\"pubName\":\"1212\",\"likeCount\":0,\"seeCount\":0,\"images\":\"upload/exProject/images/0d9493b3-9cfc-471e-9ae4-f7349656d605.jpg,upload/exProject/images/431b6fc1-15d7-4779-9ba4-bc8a6d6baf87.jpg,upload/exProject/images/df1e6f16-6ec2-4ed1-a91e-afef0557fc15.jpg\",\"classType\":\"aaaaaaa\",\"introduce\":\"1231232132\",\"requireDoc\":\"upload/exProject/reqWord/32a9f4cb-0531-4e5b-99ac-ad1cdcb56d37.doc\",\"desDoc\":\"upload/exProject/desWord/fa0fa150-0204-4825-8b80-98ad9b97c5b7.doc\",\"code\":\"upload/exProject/code/1f043fbb-44a5-4667-8817-59eecb005b29.zip\",\"apk\":\"upload/exProject/apk/38979571-e25c-4c42-9c57-69be74d30297.apk\",\"upData\":\"2017年07月13日 16:45:13\",\"proName\":\"1231\"},{\"id\":7,\"pubId\":1,\"pubPort\":\"upload/schooluser/por/f66ba4fa-0e90-4a4b-8e62-6b34bab09330.jpg\",\"pubName\":\"1212\",\"likeCount\":0,\"seeCount\":0,\"images\":\"upload/exProject/images/20d27cf2-0a77-4507-a4a4-5eed16be3160.jpg,upload/exProject/images/d603a817-9f5a-46a3-bff0-f3f36a4a79ad.jpg,upload/exProject/images/534ed25f-5756-4bb5-b789-0f4bb7c5737a.jpg\",\"classType\":\"aaaaaaa\",\"introduce\":\"1231232132\",\"requireDoc\":\"upload/exProject/reqWord/ff7cfb61-6770-4d31-95f4-c0b212698b28.doc\",\"desDoc\":\"upload/exProject/desWord/3a5a3e6d-6139-4d67-a7c6-9256b9bc76c2.doc\",\"code\":\"upload/exProject/code/9f1b8179-003a-46ca-b6f4-79d5eceac0d5.zip\",\"apk\":\"upload/exProject/apk/fff8a933-a3bc-4a8b-b06e-e8499977cabb.apk\",\"upData\":\"2017年07月13日 16:45:11\",\"proName\":\"1231\"}]\n";
        List<ExProjectInfo> data_list = new ArrayList<ExProjectInfo>(Arrays.asList(new Gson().fromJson(dataString,ExProjectInfo[].class)));
        ProjectListAdapter adapter = new ProjectListAdapter(getContext(),R.layout.list_item_work_exchang,data_list);
        showWordExchangeListView.setAdapter(adapter);*/

        //----------获取所有类型的项目交流 项目,12 个
        ExProjectFilter exProjectFilter = new ExProjectFilter();
        APPType appType = new APPType();
        appType.setId(-1l);
        exProjectFilter.setAppType(appType);
        final RequestUtil<ExProjectFilter> requestUtil = new RequestUtil(IP+"/exProject/getExProjectWithCondition");
        requestUtil.conn(exProjectFilter,ExProjectFilter.class, new RequestUtil.RequestCallback(){
            @Override
            public void before() {
                //动画实现
                Toast.makeText(getContext(), "请稍后,加载中...", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void success(String res) {
                //获取到 所有类型  前12个
                if(null != requestUtil || !"".equals(res)){
                    List<ExProjectInfo> project_list = new ArrayList<ExProjectInfo>(Arrays.asList(new Gson().fromJson(res,ExProjectInfo[].class)));
                    if(project_list.size()>0){
                        ProjectListAdapter adapter = new ProjectListAdapter(getContext(),R.layout.list_item_work_exchang,project_list);
                        showWordExchangeListView.setAdapter(adapter);
                    }else{
                        Toast.makeText(getContext(), "返回数据为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void error(Exception e) {
                Toast.makeText(getContext(), "访问出错", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void bindEvent() {
        //设置每个展示项目的点击进入详情界面
        showWordExchangeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExProjectInfo data = (ExProjectInfo) parent.getAdapter().getItem(position);
                String data_string = new Gson().toJson(data);
                Intent intent = new Intent();
                //数据传到 项目详情界面
                intent.putExtra("project",data_string);
                intent.setClass(getContext(), ProjectDetailsActivity.class);
                startActivity(intent);
            }
        });
        //--------------------展开个人中心的点击事件
        mImageViewOpenNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOpenNavListener.openNav();
            }
        });
        //--------------------类别选择的点击事件
        mImageViewChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int widthPixels = getResources().getDisplayMetrics().widthPixels;
                final int heightPixels = getResources().getDisplayMetrics().heightPixels;
                //获取界面
                final View view = LayoutInflater.from(getContext()).inflate(R.layout.word_exchangge_type_choose,null);
                //设置 scrollView_type_choose 的高度为 屏幕高的百分之九十
                ScrollView scrollView_type_choose = (ScrollView) view.findViewById(R.id.scrollView_type_choose);
                LinearLayout.LayoutParams scrollview_params = new LinearLayout.LayoutParams(widthPixels,heightPixels/11*9);
                scrollView_type_choose.setLayoutParams(scrollview_params);
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
                //弹窗设置
                popupWindow.setContentView(view);
                popupWindow.setFocusable(true);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(mImageViewChoose,0,-100);
                //所有项目类型  按钮
                final List<Button> typebuttons = new ArrayList<Button>();
                //排序选择 按钮x
                final List<Button> sort_buttons =new ArrayList<Button>();
                //---------------设置 确定 按钮的大小
                final Button type_choose_ok = (Button) view.findViewById(R.id.button_type_choose_ok);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthPixels/5,heightPixels/16);
                params.setMargins(0,heightPixels/25,0,heightPixels/25);//设置底部边距
                type_choose_ok.setLayoutParams(params);
                //------------------设置取消按钮大小   不选择,返回按钮,移除选择界面,不操作
                Button choose_back_button = (Button)view.findViewById(R.id.button_type_choose_back);
                LinearLayout.LayoutParams params_1 = new LinearLayout.LayoutParams(widthPixels/5,heightPixels/16);
                params_1.setMargins(0,heightPixels/25,widthPixels/20,heightPixels/25);//设置底部,和左 边距
                choose_back_button.setLayoutParams(params_1);
                choose_back_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                //获取所有类别
                RequestUtil getAlltypeHttp = new RequestUtil(IP+"/exProject/getAppType");
                getAlltypeHttp.conn("", String.class, new RequestUtil.RequestCallback() {
                    @Override
                    public void before() {
                        Toast.makeText(getContext(), "请稍后，加载中...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void success(String res) {
                        //取到所有类型
                        final List<APPType> type_list = new ArrayList<APPType>(Arrays.asList(new Gson().fromJson(res,APPType[].class)));
                        //设置 整个屏幕  朦胧(已取消)
                /*WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 0.5f;
                getActivity().getWindow().setAttributes(lp);*/
                        GridLayout type_gridLayout = (GridLayout) view.findViewById(R.id.gridLayout_typeChoose);
                        //-----------------类型选择  数据 填充   先移除 其中所有组件
                        type_gridLayout.removeAllViews();
                        for (int i = 0; i< type_list.size();i++){
                            final int position = i;
                            //设置button 外的 linearlayout的属性
                            LinearLayout linearLayout = new LinearLayout(getContext());
                            linearLayout.setGravity(Gravity.CENTER);
                            //实例化一个  button 并且设置属性
                            Button button = new Button(getContext());
                            LinearLayout.LayoutParams button_params = new LinearLayout.LayoutParams(widthPixels/5,heightPixels/16);
                            button.setLayoutParams(button_params);
                            button.setBackgroundResource(R.drawable.bg_line_radius_green);//设置背景颜色
                            button.setText(type_list.get(i).getName());
                            button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.text_size_small));

                            //判断 前一个选择的是否是  当前类型,是--> 按钮标注
                            if(type_value[0].equals(type_list.get(i).getId()+"")){
                                button.setTextColor(0xffffffff);
                                button.setBackgroundResource(R.drawable.bg_box_radius_green);
                            }
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //先初始化 显示的颜色 和 字体颜色  属性
                                    for(Button button1 : typebuttons){
                                        button1.setTextColor(0xff3f3f3f);
                                        button1.setBackgroundResource(R.drawable.bg_line_radius_green);
                                    }
                                    //点击  实现改变
                                    Button type_button = (Button) v;
                                    type_button.setTextColor(0xffffffff);
                                    type_button.setBackgroundResource(R.drawable.bg_box_radius_green);
                                    type_value[0] = type_list.get(position).getId()+"";
                                    Toast.makeText(getContext(), type_value[0], Toast.LENGTH_SHORT).show();
                                }
                            });
                            //添加按钮到 网格布局中
                            linearLayout.addView(button);
                            //布局设置
                            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                            params.setMargins(0,0,0,heightPixels/25);
                            //设置按钮的位置和 权重s
                            params.rowSpec = GridLayout.spec(i/3,1f);//行
                            params.columnSpec = GridLayout.spec(i%3,1f);//列
                            typebuttons.add(button);
                            type_gridLayout.addView(linearLayout,params);
                        }
                        //-----------------排序  选择  数据 填充   先移除 其中所有组件
                        GridLayout gridLayout_sortType_choose = (GridLayout) view.findViewById(R.id.gridlayout_sorttype_choose);
                        gridLayout_sortType_choose.removeAllViews();
                        for(int i=0; i<sort_type.length; i++){
                            final int position = i+1;
                            LinearLayout linearLayout = new LinearLayout(getContext());
                            linearLayout.setGravity(Gravity.CENTER);
                            Button button = new Button(getContext());
                            LinearLayout.LayoutParams button_params = new LinearLayout.LayoutParams(widthPixels/5,heightPixels/16);
                            button.setBackgroundResource(R.drawable.bg_line_radius_orange);//设置背景颜色
                            button.setLayoutParams(button_params);//设置按钮的宽,高
                            button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.text_size_small));//设置字体大小
                            button.setText(sort_type[i]);

                            //  判断 上次选择的 排序  类型是否是当前类型   是 --> 按钮标注
                            if((position+"").equals(type_value[1])){
                                button.setTextColor(0xffffffff);
                                button.setBackgroundResource(R.drawable.bg_box_radius_orange);
                            }
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //先初始化 按钮 属性 和 字体
                                    for(Button b : sort_buttons){
                                        b.setTextColor(0xff3f3f3f);
                                        b.setBackgroundResource(R.drawable.bg_line_radius_orange);
                                    }
                                    //设置 选中按钮  属性改变
                                    Button bt = (Button) v;
                                    bt.setTextColor(0xffffffff);
                                    bt.setBackgroundResource(R.drawable.bg_box_radius_orange);
                                    type_value[1] = position+"";
                                    Toast.makeText(getContext(), type_value[1], Toast.LENGTH_SHORT).show();
                                }
                            });

                            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                            params.setMargins(0,0,0,heightPixels/25);//设置底部边距
                            params.rowSpec = GridLayout.spec(i/4,1f);//设置行
                            params.columnSpec  = GridLayout.spec(i%4,1f);//设置列
                            linearLayout.addView(button);
                            linearLayout.setLayoutParams(params);
                            sort_buttons.add(button);
                            gridLayout_sortType_choose.addView(linearLayout);
                        }
                        //---------------设置 点击事件  并且开始访问网络查询
                        type_choose_ok.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                if(!"".equals(type_value[0]) && !"".equals(type_value[1])){
                                    ExProjectFilter exProjectFilter = new ExProjectFilter();
                                    APPType appType = new APPType();
                                    Long apptype_id = new Long(Integer.parseInt(type_value[0]));
                                    int sort_id = Integer.parseInt(type_value[1]);
                                    appType.setId(apptype_id);//类型id

                                    exProjectFilter.setFilter(sort_id);//排序方式
                                    exProjectFilter.setPageCount(12);//查询12条数据
                                    exProjectFilter.setAppType(appType);

                                    RequestUtil<ExProjectFilter> exprojectHttp = new RequestUtil<ExProjectFilter>(IP+"/exProject/getExProjectWithCondition");
                                    exprojectHttp.conn(exProjectFilter, ExProjectFilter.class, new RequestUtil.RequestCallback() {
                                        @Override
                                        public void before() {
                                            Toast.makeText(getContext(), "加载中，请稍后...", Toast.LENGTH_SHORT).show();
                                        }
                                        @Override
                                        public void success(String res) {
                                            if(null != res && !"".equals(res)){
                                                List<ExProjectInfo> project_list = new ArrayList<ExProjectInfo>(Arrays.asList(new Gson().fromJson(res,ExProjectInfo[].class)));
                                                if(project_list.size()>0){
                                                    ProjectListAdapter adapter = new ProjectListAdapter(getContext(),R.layout.list_item_work_exchang,project_list);
                                                    showWordExchangeListView.setAdapter(null);
                                                    showWordExchangeListView.setAdapter(adapter);
                                                    Toast.makeText(getContext(), "加载完毕", Toast.LENGTH_SHORT).show();
                                                }
                                            }else {
                                                Toast.makeText(getContext(), "按照条件查询返回数据为空", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        @Override
                                        public void error(Exception e) {
                                            Toast.makeText(getContext(), "按照条件项目查询故障", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    popupWindow.dismiss();
                                }else{
                                    Toast.makeText(getContext(), "请选择", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    @Override
                    public void error(Exception e) {
                        Toast.makeText(getContext(), "访问出错", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //----------------跳转到 项目发布界面
        button_project_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有用户登陆,有-->跳转,无-->提示未登陆,到登陆界面
                if(user != null){
                    Intent intent = new Intent(getContext(), ProjectReleaseActivity.class);
                    //还要加登陆信息
                    startActivity(intent);
                }else{
                    //到登陆界面
                    Toast.makeText(getContext(), "您还没有登陆", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void setOpenNavListen(OpenNavListener mOpenNavListener){
        this.mOpenNavListener = mOpenNavListener;
    }
}