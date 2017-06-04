package com.jackzhang.example.cv_qqstep;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * QQ 运动步数
 * Created by Jack on 2017/5/23.
 */

public class QQStepView  extends View {
    private int mOuterColor=Color.RED;
    private int mInnerColor=Color.BLUE;
    private int mBorderWidth=20;//20px
    private float mStepTextSize;
    private int mStepTextColor=Color.RED;

    private Paint mPaintOut;
    private Paint mPaintInner;
    private Paint mPaintText;


    private int mStepMax=200;//总共的步数
    private int mCurrentStep=100;//当前的频数数

    public QQStepView(Context context) {
        super(context);
    }
    public QQStepView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }
    public QQStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //1、分析效果
        //2、确定自定义属性，编写attrs.xml
        //3、在布局中使用
        //4、在自定义View中获取自定义的属性
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.QQStepView);
        mOuterColor=array.getColor(R.styleable.QQStepView_outerColor,mOuterColor);
        mInnerColor=array.getColor(R.styleable.QQStepView_innerColor,mInnerColor);
        mBorderWidth=(int)array.getDimension(R.styleable.QQStepView_borderWidth,mBorderWidth);
        mStepTextSize=array.getDimension(R.styleable.QQStepView_stepTextSize,14);
        mStepTextColor=array.getColor(R.styleable.QQStepView_stepTextColor,mStepTextColor);
        array.recycle();

        mPaintOut=new Paint();
        mPaintOut.setAntiAlias(true);
        mPaintOut.setStrokeWidth(mBorderWidth);
        mPaintOut.setColor(mOuterColor);
        mPaintOut.setStrokeCap(Paint.Cap.ROUND);//边处圆状
        mPaintOut.setStyle(Paint.Style.STROKE);//空心


        mPaintInner=new Paint();
        mPaintInner.setAntiAlias(true);
        mPaintInner.setStrokeWidth(mBorderWidth);
        mPaintInner.setColor(mInnerColor);
        mPaintInner.setStrokeCap(Paint.Cap.ROUND);//边处圆状
        mPaintInner.setStyle(Paint.Style.STROKE);//空心

        mPaintText=new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(mStepTextColor);
        mPaintText.setTextSize(mStepTextSize);

        //5、onMeasure()
        //6、画外圆弧，内圆弧，文字
        //7、文字
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //调用者布局文件中 wrap_content --》AT_MOST （需给默认值）

        //宽度不一致 取最小值，确保是个正方形
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width>height?height:width,width>height?height:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //6.1 画外圆弧
        /*
        int center=getWidth()/2;
        int radius=getWidth()/2-mBorderWidth/2;

        RectF rectF=new RectF(center-radius,center-radius,center+radius,center+radius);
        */
        RectF rectF=new RectF(mBorderWidth/2,mBorderWidth/2,getWidth()-mBorderWidth/2,getHeight()-mBorderWidth/2);


        canvas.drawArc(rectF,135,270,false,mPaintOut);
        //6.2 画内圆弧  百分比来算
        if (mStepMax==0)return;;
        float sweepAngle=(float) mCurrentStep/mStepMax;

        canvas.drawArc(rectF,135,270*sweepAngle,false,mPaintInner);
        //6.3 画文字
        String stepText=mCurrentStep+"";
        Rect textBounds=new Rect();
        mPaintText.getTextBounds(stepText,0,stepText.length(),textBounds);
        int dx=getWidth()/2-textBounds.width()/2;
        //基线 baseLine
        Paint.FontMetrics fontMetrics=mPaintText.getFontMetrics();
        int dy=(int)((fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom);
        int baseLine=getHeight()/2+dy;

        canvas.drawText(stepText,dx,baseLine,mPaintText);
    }
    public void setStepMax(int stepMax){
        this.mStepMax=stepMax;
    }
    public void setmCurrentStep(int currentStep){
        this.mCurrentStep=currentStep;
        //不断重新绘制
        invalidate();
    }
}
