package com.ice.optionplayer;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

;

public class OptionGraph extends View implements Graph {
    public static final String PREFS_NAME = "MyPrefsFile";

    List<Integer> dataPoints = new ArrayList();// graph drawing constants
    List<String> xLabels = new ArrayList<String>();
    List<String> yLabels = new ArrayList<String>();

	private int strikePrice;
	private int xorigin = 150;
	private int yorigin = 500;
    public Path dataPath = new Path();
    public Path optionPath = new Path();

    //  Constructor for class
    public OptionGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
        Bitmap _scratch = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
//      canvas.drawColor(Color.BLACK);
        TypedArray a=getContext().obtainStyledAttributes(attrs, R.styleable.Graph);
 
        }
 
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mLinePaint = new Paint();
        Paint mLine2Paint = new Paint();
        Paint mTextPaint = new Paint();
        mLinePaint.setColor(0xFF0000FF);
        mLine2Paint.setColor(0x88008800);
        mLinePaint.setStrokeWidth(1);
        mLine2Paint.setStrokeWidth(1);
        canvas.drawLine(xorigin, 0, xorigin, yorigin, mLinePaint);                                     // draw vertical axis
        canvas.drawLine(xorigin, yorigin/2, 1050, yorigin/2, mLinePaint);                               // draw horizontal axis
        canvas.drawLine(xorigin, yorigin, 1050, yorigin, mLinePaint);                                  // draw horizontal axis

        mLinePaint.setColor(0x66005500);
        mTextPaint.setStrokeWidth(1);
        mTextPaint.setTextSize(40);
        
        setXLabels(canvas, mTextPaint);
        setYLabels(canvas, mTextPaint);

        // draw horizontal lines
        
        
        canvas.drawText("P/L", 20, 00, mTextPaint);
        mLinePaint.setColor(0x66005500);

        mLinePaint.setColor(0xFF0000FF);
    	int xval = xorigin+50;
    	int yval = yorigin/2;
        for (int i=0; i<18; i++) {
        	canvas.drawLine(xval, yval-10, xval, yval+10, mLinePaint);                                           // draw check marks
        	canvas.drawLine(xval, yval-250, xval, yval+250, mLine2Paint);                                           // draw check marks
        	xval += 50;
        }
        
        drawPayoffLine(dataPath, canvas, mTextPaint);       
        drawOptionLine(optionPath, canvas, mLinePaint);                  

        mLinePaint.setColor(Color.DKGRAY);


    }
	
	void setDP(final int newData)  {
			invalidate();              // this redraws page
	}
	
    
    public void drawPayoffLine(Path path, Canvas c, Paint paint)  {
        paint.setStrokeWidth(2);
        paint.setStyle(Style.STROKE);
		c.drawPath(path, paint);
    }
    
    public void drawOptionLine(Path path, Canvas c, Paint paint)  {
        paint.setColor(0xFFEEEEEE);
        paint.setStyle(Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[] {10,20}, 0));
        paint.setStrokeWidth(1);
		c.drawPath(path, paint);

    }

    
    public void setDataPath(Path aPath, Path bPath)  {

    	dataPath = aPath;
    	optionPath = bPath;
        invalidate();
    }
    
    public void setSlider1(double d)  {
        invalidate();
    }
    
    public void setSlider2(double d)  {
        invalidate();
    }
    
    public void setXLabels(Canvas c, Paint paint) {
        paint.setColor(0xEEEEEEEE);
        int xoffset = 115;
        int yoffset = yorigin + 50;
        xLabels.add("");
        xLabels.add("A");
        xLabels.add("B");
        xLabels.add("C");
        xLabels.add("D");
        xLabels.add("E");
        xLabels.add("F");
        xLabels.add("G");
        xLabels.add("H");
        xLabels.add("I");

        for (int i = 0; i < xLabels.size(); i++)   {
            c.drawText(xLabels.get(i), xoffset, yoffset, paint);
        xoffset += 100;
        }

    }

    public void setYLabels(Canvas c, Paint paint)  {
        paint.setColor(0xEEEEEEEE);
        int xoffset = 20;
        int yoffset = yorigin + 50;
        yLabels.add("");
        yLabels.add("-$200");
        yLabels.add("C");
        yLabels.add("$0");
        yLabels.add("E");
        yLabels.add("$300");
        yLabels.add("G");
        yLabels.add("H");

        for (int i = 0; i < yLabels.size(); i++)   {
            c.drawText(yLabels.get(i), xoffset, yoffset, paint);
            yoffset -= 100;
        }

    }

    

	public void setWidth() {
	}
	public void setLength(){
	}
	public void setXDivisions() {
	}
	public void setYDivisions() {
	}

    
	int getDataPoint(String name)  {
		return(10);
		}

	@Override
	public void setXLabels() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void setYLabels() {
        // TODO Auto-generated method stub

    }

	}


