package com.example.zengchengjie.viewpagerhandler;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * 第一次进来先显示viewpager,以后就不显示了
 * 使用一个boolean变量控制第一次是true
 */

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private int[] imgsId = {R.mipmap.icon1, R.mipmap.icon2, R.mipmap.icon3, R.mipmap.icon4};
    //建立一个图片id数组 存放图片的id
    private ImageView[] imageViews;//建立一个图片数组用于改变图片
    private ViewPager viewPager;
    private ImageView[] dots;
    private LinearLayout linearLayout;
    private Button mbuttonsave;
    private EditText meditname, meditpassword;
    private String NAME = "password";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        MyAdapter adapter = new MyAdapter(imageViews, getApplicationContext());
        //实例化adapter对象，并将图片动态加载进来

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(imageViews.length * 1000);//
        viewPager.setOnPageChangeListener(this);
        myHandler.sendEmptyMessageDelayed(1, 2000);//设置滚动
    }

    private void init() {//初始化工作
        viewPager = (ViewPager) findViewById(R.id.pager);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
//        mbuttonsave = (Button) findViewById(R.id.button_save);
//        meditname = (EditText) findViewById(R.id.edit_name);
//        meditpassword = (EditText) findViewById(R.id.edit_password);
//        mbuttonsave.setOnClickListener(this);
        getImageViews();
        getDotImage();
    }

    public void getImageViews() {//设置图片 数组的每一个image都设置一个图片id
        imageViews = new ImageView[imgsId.length];
        for (int i = 0; i < imageViews.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imgsId[i]);
            imageViews[i] = imageView;
        }
    }

    public void getDotImage() {//循环放入图片
        dots = new ImageView[imageViews.length];
        for (int i = 0; i < dots.length; i++) {
            ImageView imageView = new ImageView(this);
            dots[i] = imageView;
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(50, 50);
            layout.leftMargin = 20;
            layout.rightMargin = 20;
            linearLayout.addView(dots[i], layout);
        }
    }

    public void getDots(int position) {//循环放入小圆点
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setImageResource(R.mipmap.find_pic);
            } else {
                dots[i].setImageResource(R.mipmap.find_not_pic);
            }
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        getDots(position);//页面滑动时调用这个方法，做到每滑动一次就能够改变一次图片
    }


    @Override
    public void onPageSelected(int position) {
    }//这里也可以添加圆点方法,两种方法有什么区别


    @Override
    public void onPageScrollStateChanged(int state) {
    }


    public Handler myHandler = new Handler() {//不理解的部分 循环加入？显示消息当前页？
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);//设置获得的页数
            myHandler.sendEmptyMessageDelayed(1, 2000);
        }
    };


    @Override
    public void onClick(View v) {

        String name = meditname.getText().toString();//获取数据
        String password = meditpassword.getText().toString();

        if (name != null && password == null) {
            SharedPreferences sharedPreferences = getSharedPreferences("NAME", MODE_APPEND);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("username", name);//添加数据
            editor.putString("password", password);
            editor.commit();//提交数据
        }
    }
}
