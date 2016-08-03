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
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
;

public class OptionGraph extends View implements Graph {
    public static final String PREFS_NAME = "MyPrefsFile";

	public int[] dataPoints = {180, 170, 160, 150, 140, 130, 120, 110, 100, 90, 80, 70};
	public String xLabels[] = {"10", "20", "30", "40", "50", "60", "70", "80"};
	public String yLabels[] = {"10", "20", "30", "40", "50"};

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
        TypedArray a=getContext().obtainStyledAttributes(attrs,R.styleable.Graph);
 
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
                                
        // draw horizontal lines
        
        
        canvas.drawText("P/L", 20, 60, mTextPaint);
        canvas.drawText("0", 20, yorigin/2, mTextPaint);
        canvas.drawText("-$200", 20, yorigin,  mTextPaint);
        canvas.drawText("$300", 20, yorigin/4, mTextPaint);
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
    
    public void setXLabels(Canvas c, Paint paint)  {
        paint.setColor(0xEEEEEEEE);
        int xoffset = 115;
        int yoffset = yorigin+50;
        c.drawText(xLabels[0], xoffset+100, yoffset, paint);
        c.drawText(xLabels[1], xoffset+200, yoffset, paint);
        c.drawText(xLabels[2], xoffset+300, yoffset, paint);
        c.drawText(xLabels[3], xoffset+400, yoffset, paint);
        c.drawText(xLabels[4], xoffset+500, yoffset, paint);
        c.drawText(xLabels[5], xoffset+600, yoffset, paint);
        c.drawText(xLabels[6], xoffset+700, yoffset, paint);
        c.drawText(xLabels[7], xoffset+800, yoffset, paint);

    }
	@Override
    public void setYLabels()  {

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


	}


