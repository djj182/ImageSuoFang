package com.example.imagesuofang;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        private ImageView mImageView;
        private Button btn1,btn2;
        private TextView mTextView;
        private AbsoluteLayout layout1;
        private Bitmap bmp;
        private int id=0;
        private int displayWidth,displayHeight;
        private float scaleWidth=1,scaleHeight=1;
        private final static String filename="/data/data/ex04_22.lcs/ex04_22_2.png";
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //取得屏幕分辨率
            DisplayMetrics dm=new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            displayWidth=dm.widthPixels;
            displayHeight=dm.heightPixels-80;

            bmp= BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher);
            layout1=(AbsoluteLayout)findViewById(R.id.layout1);
            mImageView=(ImageView)findViewById(R.id.myImageView);
            btn1=(Button)findViewById(R.id.myButton1);
            btn1.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    small();
                }
            });
            btn2=(Button)findViewById(R.id.myButton2);
            btn2.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    big();
                }
            });
        }
        private void small(){
            //获得Bitmap的高和宽
            int bmpWidth=bmp.getWidth();
            int bmpHeight=bmp.getHeight();
            //设置缩小比例
            double scale=0.8;
            //计算出这次要缩小的比例
            scaleWidth=(float)(scaleWidth*scale);
            scaleHeight=(float)(scaleHeight*scale);
            //产生resize后的Bitmap对象
            Matrix matrix=new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap resizeBmp=Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
            if(id==0){
                layout1.removeView(mImageView);
            }
            else{
                layout1.removeView((ImageView)findViewById(id));

            }
            id++;
            ImageView imageView=new ImageView(this);
            imageView.setId(id);
            imageView.setImageBitmap(resizeBmp);
            layout1.addView(imageView);
            setContentView(layout1);
            btn2.setEnabled(true);
        }
        private void big(){
            //获得Bitmap的高和宽
            int bmpWidth=bmp.getWidth();
            int bmpHeight=bmp.getHeight();
            //设置缩小比例
            double scale=1.25;
            //计算出这次要缩小的比例
            scaleWidth=(float)(scaleWidth*scale);
            scaleHeight=(float)(scaleHeight*scale);
            //产生resize后的Bitmap对象
            Matrix matrix=new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap resizeBmp=Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
            if(id==0){
                layout1.removeView(mImageView);
            }
            else{
                layout1.removeView((ImageView)findViewById(id));

            }
            id++;
            ImageView imageView=new ImageView(this);
            imageView.setId(id);
            imageView.setImageBitmap(resizeBmp);
            layout1.addView(imageView);
            setContentView(layout1);
            if(scaleWidth*scale*bmpWidth>displayWidth||scaleHeight*scale*scaleHeight>displayHeight){
                btn2.setEnabled(false);
            }
        }
    }