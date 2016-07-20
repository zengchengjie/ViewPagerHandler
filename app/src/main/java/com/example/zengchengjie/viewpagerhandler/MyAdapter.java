package com.example.zengchengjie.viewpagerhandler;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by zengchengjie on 2016/4/22.
 */
public class MyAdapter extends PagerAdapter {
    private ImageView[] imageView;
    private Context mcontext;


    public MyAdapter(ImageView[] imageView, Context mcontext) {//通过构造函数传参
        this.imageView = imageView;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return imageView.length;//参数的意思?
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageView[position]);//添加图片
        //这里可以添加点击事件
        if (position == imageView.length - 1) {
            imageView[position].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext,MainActivity.class);
                    mcontext.startActivity(intent);
                }
            });
        }
        return imageView[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageView[position]);//循环移除每一个图片
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    //参数是什么意思？---->将imageview传递进来
}
