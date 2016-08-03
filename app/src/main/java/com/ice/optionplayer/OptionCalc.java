package com.ice.optionplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class OptionCalc extends Activity {

	final Map<String , Path> paths = new HashMap<String, Path>() {{
		put("Bull Calendar Spread", new Path());
		// Bullish strategies
	}
	};
	
    private ArrayList<Coordinate> mSnakeTrail = new ArrayList<Coordinate>();

	final Map<String , Strategy> strategies = new HashMap<String , Strategy>() {{
		// Bullish strategies

		put("Bull Calendar Spread", new Strategy("Sell 1 Near-Term OTM Call", "Buy 1 Long-Term OTM Call", "", "", new Path(), new Path()));
		put("Bull Call Spread", new Strategy("Buy 1 ITM Call", "Sell 1 OTM Call", "", "", new Path(), new Path()));
		put("Bull Put Spread", new Strategy("Buy 1 OTM Put", "Sell 1 ITM Put", "", "", new Path(), new Path()));
		put("Call Backspread", new Strategy("Sell 1 ITM Call", "Buy 2 OTM Calls", "", "", new Path(), new Path()));
		put("Collar Strategy", new Strategy("Long 100 Shares", "Sell 1 OTM Call", "Buy 1 OTM Put", "", new Path(), new Path()));
		put("Costless Collar", new Strategy("Long 100 Shares", "Sell 1 OTM LEAPS Call", "Buy 1 ATM LEAPS Put", "", new Path(), new Path()));
		put("Covered Call OTM", new Strategy("Long 100 Shares", "Sell 1 Call", "", "", new Path(), new Path()));
		put("Covered Call ITM", new Strategy("Long 100 Shares", "Sell 1 ITM Call", "", "", new Path(), new Path()));
		put("Covered Straddle", new Strategy("Long 100 Shares", "Sell 1 ATM Call", "Sell 1 ATM Put", "", new Path(), new Path()));
		put("Diagonal Bull Call Spread", new Strategy("Buy 1 Long-Term ITM Call", "Sell 1 Near-Term OTM Call", "", "", new Path(), new Path()));
		put("Long Call", new Strategy("Buy 1 ATM Call", "", "", "", new Path(), new Path()));
		put("Married Put", new Strategy("Long 100 Shares", "Buy 1 ATM Put", "", "", new Path(), new Path()));
		put("Protective Put", new Strategy("", "", "", "", new Path(), new Path()));
		put("Uncovered Put Write", new Strategy("Sell 1 ATM Put", "", "", "", new Path(), new Path()));
		put("Covered Put Combination", new Strategy("Long 100 Shares", "Sell 1 OTM Call", "Sell 1 OTM Put", "", new Path(), new Path()));

		// Bearish strategies
		put("Bear Call Spread", new Strategy("Buy 1 OTM Call", "Sell 1 ITM Call", "", "", new Path(), new Path()));
		put("Bear Put Spread", new Strategy("Buy 1 ITM Put", "Sell 1 OTM Put", "", "", new Path(), new Path()));
		put("Covered Put", new Strategy("Short 100 Shares", "Sell 1 ATM Put", "", "", new Path(), new Path()));
		put("Diagonal Bear Put Spread", new Strategy("Buy 1 Long-Term ITM Put", "Sell 1 Near-Term OTM Put", "", "", new Path(), new Path()));
		put("Long Put", new Strategy("Buy 1 ATM Put", "", "", "", new Path(), new Path()));
		put("Naked Call ITM", new Strategy("Sell 1 ITM Call", "", "", "", new Path(), new Path()));
		put("Naked Call OTM", new Strategy("Sell 1 OTM Call", "", "", "", new Path(), new Path()));
		put("Put Backspread", new Strategy("Sell 1 ITM Put", "Buy 2 OTM Puts", "", "", new Path(), new Path()));
		put("Protective Call", new Strategy("Short 100 Shares", "Buy 1 ATM Call", "", "", new Path(), new Path()));

		// Neutral strategies
		put("Butterfly Spread", new Strategy("Sell 1 ITM Call", "Buy 1 ITM Call(Lower Strike)", "Sell 1 OTM Call", "Buy 1 OTM Call (Higher Strike)", new Path(), new Path()));;
	//	put("Calendar Spread", new Strategy("Sell 1 ITM Call", "Buy 1 ITM Call(Lower Strike)", "Sell 1 OTM Call", "Buy 1 OTM Call (Higher Strike)", new Path(), new Path()));;
		put("Condor", new Strategy("Sell 1 ITM Call", "Buy 1 ITM Call(Lower Strike)", "Sell 1 OTM Call", "Buy 1 OTM Call (Higher Strike)", new Path(), new Path())); 
		put("Iron Butterfly", new Strategy("Buy 1 OTM Put", "Sell 1 ATM Put", "Sell 1 ATM Call", "Buy 1 OTM Call", new Path(), new Path()));
		put("Iron Condor", new Strategy("Sell 1 OTM Put", "Buy 1 OTM Put(Lower Strike)", "Sell 1 OTM Call", "Buy 1 OTM Call(Higher Strike)", new Path(), new Path()));
		put("Long Put Butterfly", new Strategy("Buy 1 OTM Put", "Sell 2 ATM Puts", "Buy 1 ITM Put", "", new Path(), new Path())); 
		put("Long Straddle", new Strategy("Buy 1 ATM Call", "Buy 1 ATM Put", "", "", new Path(), new Path())); 
		put("Long Strangle", new Strategy("Buy 1 OTM Call", "Buy 1 OTM Put", "", "", new Path(), new Path())); 
		put("Neutral Calendar Spread", new Strategy("Sell 1 Near-Term ATM Call", "Buy 1 Long-Term ATM Call", "", "", new Path(), new Path()));
		put("Put Ratio Spread", new Strategy("Buy 1 ITM Put",  "Sell 2 OTM Puts", "", "", new Path(), new Path()));
		put("Ratio Call Write", new Strategy("Long 100 Shares", "Sell 2 ATM Calls", "", "", new Path(), new Path()));
		put("Ratio Put Write", new Strategy("Short 100 Shares", "Sell 2 ATM Puts", "", "", new Path(), new Path()));
		put("Ratio Spread", new Strategy("Buy 1 ITM Call", "Sell 2 OTM Calls", "", "", new Path(), new Path())); 
		put("Short Butterfly", new Strategy("Sell 1 ITM Call", "Buy 2 ATM Calls", "Sell 1 OTM Call", "", new Path(), new Path())); 
		put("Short Condor", new Strategy("Buy 1 ITM Call", "Sell 1 ITM Call(Lower Strike)", "Buy 1 OTM Call", "Sell 1 OTM Call (Higher Strike)", new Path(), new Path())); 
		put("Short Put Butterfly", new Strategy("Sell 1 ITM Put", "Buy 2 ATM Puts", "Sell 1 OTM Put", "", new Path(), new Path()));
		put("Short Straddle", new Strategy("Sell 1 ATM Call", "Sell 1 ATM Put", "", "", new Path(), new Path())); 
		put("Short Strangle", new Strategy("Sell 1 OTM Call",  "Sell 1 OTM Put", "", "", new Path(), new Path())); 
		put("Variable Ratio Write", new Strategy("Long 100 Shares", "Sell 1 ITM Call", "Sell 1 OTM Call", "", new Path(), new Path()));
		put("Reverse Iron Condor", new Strategy("Buy 1 OTM Put", "Sell 1 OTM Put(Lower Strike)", "Buy 1 OTM Call", "Sell 1 OTM Call(Higher Strike)", new Path(), new Path()));
		put("Reverse Iron Butterfly", new Strategy("Sell 1 OTM Put", "Buy 1 ATM Put", "Buy 1 ATM Call", "Sell 1 OTM Call", new Path(), new Path()));
		put("Long Guts", new Strategy("Buy 1 ITM Call", "Buy 1 ITM Put", "", "", new Path(), new Path()));
		put("Short Guts", new Strategy("Sell 1 ITM Call", "Sell 1 ITM Put", "", "", new Path(), new Path())); 
		put("Long Call Ladder", new Strategy("Buy 1 ITM Call", "Sell 1 ATM Call", "Sell 1 OTM Call", "", new Path(), new Path()));
		put("Short Call Ladder", new Strategy("Sell 1 ITM Call", "Buy 1 ATM Call", "Buy 1 OTM Call", "", new Path(), new Path()));
		put("Long Put Ladder", new Strategy("Buy 1 ITM Put", "Sell 1 ATM Put", "Sell 1 OTM Put", "", new Path(), new Path()));
		put("Short Put Ladder", new Strategy("Sell 1 ITM Put", "Buy 1 ATM Put", "Buy 1 OTM Put", "", new Path(), new Path()));
		put("Strip", new Strategy("Buy 1 ATM Call", "Buy 2 ATM Puts", "", "", new Path(), new Path()));
		put("Strap", new Strategy("Buy 2 ATM Calls", "Buy 1 ATM Put", "", "", new Path(), new Path()));
		
		// Synthetic strategies
		put("Synthetic Long Call", new Strategy("Long 100 Shares", "Buy 1 ATM Put", "", "", new Path(), new Path()));
		put("Synthetic Long Put", new Strategy("Short 100 Shares", "Buy 1 ATM Call", "", "", new Path(), new Path()));
		put("Synthetic Long Stock (Split Strikes)", new Strategy("Buy 1 OTM Call", "Sell 1 OTM Put", "", "", new Path(), new Path()));
		put("Synthetic Long Stock", new Strategy("Buy 1 ATM Call", "Sell 1 ATM Put", "", "", new Path(), new Path()));
		put("Synthetic Short Call", new Strategy("Short 100 Shares", "Sell 1 ATM Put", "", "", new Path(), new Path()));
		put("Synthetic Short Put", new Strategy("Long 100 Shares", "Sell 1 ATM Call", "", "", new Path(), new Path()));
		put("Synthetic Short Stock (Split Strikes)", new Strategy("Sell 1 OTM Call", "Buy 1 OTM Put", "", "", new Path(), new Path()));
		put("Synthetic Short Stock", new Strategy("Buy 1 ATM Put", "Sell 1 ATM Call", "", "", new Path(), new Path()));
		put("Long Call Synthetic Straddle", new Strategy("Buy 2 ATM Calls", "Short 100 Shares", "", "", new Path(), new Path()));
		put("Long Put Synthetic Straddle", new Strategy("Buy 2 ATM Puts", "Long 100 Shares", "", "", new Path(), new Path()));
		put("Short Call Synthetic Straddle", new Strategy("Sell 2 ATM Calls", "Long 100 Shares", "", "", new Path(), new Path()));
		put("Short Put Put Synthetic Straddle", new Strategy("Sell 2 ATM Puts", "Short 100 Shares", "", "", new Path(), new Path()));

	}
	};
	/* 

	*/


	
        public final static int mButtonHeight = 120;
        public final static int mButtonWidth = 80;

        /* path points */
    	public Point cp = new Point (300, 250);
    	public int rtx = 350;
    	public int ltx = 250;
    	public int endx = 440;
    	public int neg1y = 175;
    	public int neg2y = 200;
    	public int neg3y = 225;
    	public int neg4y = 250;
    	public int y1 = 125;
    	public int y2 = 100;
    	public int y3 = 75;
    	public int y4 = 50;
    	public int x1 = 150;
    		
    	private double strikePrice = 300;
    	private double riskLevel = 100;
    	public OptionGraph graph2;
    	public String strategyType;

 
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.optioncalc);
    	    Intent i = getIntent();
    	    strategyType = i.getStringExtra("type");
            TextView DT = (TextView) findViewById(R.id.duoTube);
            DT.setText(strategyType);
            
            TextView l1 = (TextView) findViewById(R.id.leg1);          // set up legs
            l1.setText(strategies.get(strategyType).leg1);
            TextView l2 = (TextView) findViewById(R.id.leg2);          // set up legs
            l2.setText(strategies.get(strategyType).leg2);
            TextView l3 = (TextView) findViewById(R.id.leg3);          // set up legs
            l3.setText(strategies.get(strategyType).leg3);
            TextView l4 = (TextView) findViewById(R.id.leg4);          // set up legs
            l4.setText(strategies.get(strategyType).leg4);
            populateGraphPath();
            populateOptionPath();

         	
         	graph2 = (OptionGraph) findViewById(R.id.graphduo);
         	graph2.setDataPath(strategies.get(strategyType).graphPath, strategies.get(strategyType).optionPath);              // draws path on graph

    		//  Event Handler for Seek Bar 
    		SeekBar seekBarMix = (SeekBar) findViewById(R.id.plateVoltage);
           	seekBarMix.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                	public void onProgressChanged(SeekBar s, int progress, boolean touch){

                    strikePrice = progress - 25;
                 	TextView PL = (TextView) findViewById(R.id.leg1val);

                 	PL.setText(Double.toString(strikePrice));
                 	calculateAll(strikePrice, riskLevel);

             }


    				@Override
    				public void onStartTrackingTouch(SeekBar seekBar) {
    				}

    				@Override
    				public void onStopTrackingTouch(SeekBar seekBar) {
    				}
                });
           	
           	
           	SeekBar seekBarEC = (SeekBar) findViewById(R.id.screenVoltage);
           	seekBarEC.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                	public void onProgressChanged(SeekBar s, int progress, boolean touch){
                	riskLevel = progress;
                    TextView SCR = (TextView) findViewById(R.id.leg2val);
                 	SCR.setText("     " + Double.toString(riskLevel));
                 	calculateAll(strikePrice, riskLevel);

             }


    				@Override
    				public void onStartTrackingTouch(SeekBar seekBar) {
    					// TODO Auto-generated method stub
    					
    				}

    				@Override
    				public void onStopTrackingTouch(SeekBar seekBar) {
    					// TODO Auto-generated method stub
    					
    				}
                });
        }
        

        protected void calculateAll(double slider1, double slider2) {
 
             	Path optionPath = strategies.get(strategyType).optionPath;
             	Path dataPath  = strategies.get(strategyType).graphPath;
             	
             	graph2 = (OptionGraph) findViewById(R.id.graphduo);
             	graph2.setDataPath(dataPath, optionPath);
             	graph2.setSlider1(slider1);      
             	graph2.setSlider2(slider2);      

         }
        

    	public boolean onCreateOptionsMenu(Menu menu){
    		MenuInflater inflater = getMenuInflater();
    		inflater.inflate(R.menu.menu, menu);
    		return true;

    		}
    	@Override
    	public boolean onOptionsItemSelected(MenuItem item)
    	{
    	switch (item.getItemId()) {
    	   case 1: 
               break;
    	   case 2: /* .. start help activity .. */ break;
    	   case 3: /* .. start feedback activity .. */ break;
    	   case 4: /* .. start about activity .. */ break;
    	}
		return false;
    	}
    	
        protected void populateGraphPath() {

        	Path testPath = new Path();
        	Point start = new Point (150, 150);

      	    testPath  = strategies.get("Bull Call Spread").graphPath;
        	testPath.moveTo(x1, neg2y);
         	testPath.lineTo(ltx, neg2y);
    		testPath.lineTo(rtx, y3);
    		testPath.lineTo(endx, y3);

      		testPath  = strategies.get("Bull Calendar Spread").graphPath;
      		testPath.moveTo(start.x, start.y);
    		testPath.lineTo(250, 150);
    		testPath.lineTo(350, 250);
    		testPath.lineTo(480, 100);

    		testPath  = strategies.get("Bull Put Spread").graphPath;
        	testPath.moveTo(x1, neg3y);
         	testPath.lineTo(ltx, neg3y);
    		testPath.lineTo(rtx, y2);
    		testPath.lineTo(endx, y2);
    		
    		testPath  = strategies.get("Call Backspread").graphPath;
      		testPath.moveTo(start.x, start.y);
      		testPath.lineTo(250, 150);
    		testPath.lineTo(350, 250);
    		testPath.lineTo(480, 150);

    		testPath  = strategies.get("Collar Strategy").graphPath;
           	testPath.moveTo(x1, neg2y);
         	testPath.lineTo(ltx, neg2y);
    		testPath.lineTo(rtx, y3);
    		testPath.lineTo(endx, y3);

    		testPath  = strategies.get("Costless Collar").graphPath;
      		testPath.moveTo(x1, cp.y);
    		testPath.lineTo(cp.x, cp.y);
    		testPath.lineTo(rtx, y3);
    		testPath.lineTo(endx, y3);

    		testPath = strategies.get("Covered Call OTM").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);

    		testPath = strategies.get("Covered Call ITM").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);

    		testPath = strategies.get("Covered Straddle").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);

    		testPath = strategies.get("Diagonal Bull Call Spread").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);

    		testPath = strategies.get("Long Call").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);

    		testPath = strategies.get("Married Put").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);

    		testPath = strategies.get("Protective Put").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);

    		testPath = strategies.get("Uncovered Put Write").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);

    		testPath = strategies.get("Covered Put Combination").graphPath;
      		testPath.moveTo(x1, cp.y);
    		testPath.lineTo(ltx, neg2y);
    		
    		// Bearish strategies
    		testPath = strategies.get("Bear Call Spread").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Bear Put Spread").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Covered Put").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Diagonal Bear Put Spread").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);

    		testPath = strategies.get("Long Put").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Naked Call ITM").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Naked Call OTM").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Put Backspread").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Protective Call").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);

    		// Neutral strategies
    		testPath = strategies.get("Butterfly Spread").graphPath;
      		testPath.moveTo(x1, neg2y);
         	testPath.lineTo(ltx, neg2y);
         	testPath.lineTo(cp.x, y3);
         	testPath.lineTo(rtx, neg2y);
         	testPath.lineTo(endx, neg2y);

    		testPath = strategies.get("Condor").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Iron Butterfly").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Iron Condor").graphPath;
      		testPath.moveTo(x1, neg4y);
         	testPath.lineTo(ltx, neg4y);
         	testPath.lineTo(cp.x, y3);
         	testPath.lineTo(rtx, neg2y);
         	testPath.lineTo(endx, neg2y);
    		testPath = strategies.get("Long Put Butterfly").graphPath; 
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Long Straddle").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Long Strangle").graphPath; 
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Neutral Calendar Spread").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Put Ratio Spread").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Ratio Call Write").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Ratio Put Write").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Ratio Spread").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Short Butterfly").graphPath; 
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Short Condor").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Short Put Butterfly").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Short Straddle").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Short Strangle").graphPath; 
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Variable Ratio Write").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Reverse Iron Condor").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Reverse Iron Butterfly").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Long Guts").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Short Guts").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Long Call Ladder").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Short Call Ladder").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Long Put Ladder").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Short Put Ladder").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
   		    testPath = strategies.get("Strip").graphPath;
      		testPath.moveTo(x1, cp.y);
        	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Strap").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
/*    		
    		// Synthetic strategies
    		testPath = strategies.get("Synthetic Long Call").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Synthetic Long Put").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Synthetic Long Stock (Split Strikes)").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Synthetic Long Stock").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath= strategies.get("Synthetic Short Call").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Synthetic Short Put").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Synthetic Short Stock (Split Strikes)").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Synthetic Short Stock").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Long Call Synthetic Straddle").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Long Put Synthetic Straddle").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Short Call Synthetic Straddle").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
    		testPath = strategies.get("Short Put Put Synthetic Straddle").graphPath;
      		testPath.moveTo(x1, cp.y);
         	testPath.lineTo(ltx, neg2y);
         	*/
    		
     }
        
        protected void populateOptionPath() {
      	Path oPath = new Path();
        	
      	    oPath  = strategies.get("Bull Call Spread").optionPath;
      		oPath  = strategies.get("Bull Calendar Spread").optionPath;
    		oPath  = strategies.get("Bull Put Spread").optionPath;
    		oPath  = strategies.get("Call Backspread").optionPath;
    		oPath  = strategies.get("Collar Strategy").optionPath;
           	oPath.moveTo(ltx, neg2y);
    		oPath.lineTo(rtx, y3);
    		oPath.lineTo(endx, y3);

    		oPath  = strategies.get("Costless Collar").optionPath;
      		oPath.moveTo(ltx-40, neg4y);
    		oPath.lineTo(rtx-20, y3-20);
    		oPath.lineTo(endx, y3-20);

    		oPath = strategies.get("Covered Call OTM").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);

    		oPath = strategies.get("Covered Call ITM").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);

    		oPath = strategies.get("Covered Straddle").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);

    		oPath = strategies.get("Diagonal Bull Call Spread").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);

    		oPath = strategies.get("Long Call").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);

    		oPath = strategies.get("Married Put").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);

    		oPath = strategies.get("Protective Put").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);

    		oPath = strategies.get("Uncovered Put Write").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);

    		oPath = strategies.get("Covered Put Combination").optionPath;
      		oPath.moveTo(x1, cp.y);
    		oPath.lineTo(ltx, neg2y);
    		
    		// Bearish strategies
    		oPath = strategies.get("Bear Call Spread").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Bear Put Spread").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Covered Put").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Diagonal Bear Put Spread").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);

    		oPath = strategies.get("Long Put").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Naked Call ITM").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Naked Call OTM").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Put Backspread").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Protective Call").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);

    		// Neutral strategies
    		oPath = strategies.get("Butterfly Spread").optionPath;
      		oPath.moveTo(x1, neg2y);
         	oPath.lineTo(ltx, neg2y);
         	oPath.lineTo(cp.x, y3);
         	oPath.lineTo(rtx, neg2y);
         	oPath.lineTo(endx, neg2y);

    		oPath = strategies.get("Condor").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Iron Butterfly").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Iron Condor").optionPath;
      		oPath.moveTo(x1, neg4y);
         	oPath.lineTo(ltx, neg4y);
         	oPath.lineTo(cp.x, y3);
         	oPath.lineTo(rtx, neg2y);
         	oPath.lineTo(endx, neg2y);
    		oPath = strategies.get("Long Put Butterfly").optionPath; 
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Long Straddle").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Long Strangle").optionPath; 
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Neutral Calendar Spread").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Put Ratio Spread").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Ratio Call Write").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Ratio Put Write").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Ratio Spread").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Short Butterfly").optionPath; 
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Short Condor").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Short Put Butterfly").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Short Straddle").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Short Strangle").optionPath; 
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Variable Ratio Write").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Reverse Iron Condor").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Reverse Iron Butterfly").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Long Guts").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Short Guts").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Long Call Ladder").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Short Call Ladder").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Long Put Ladder").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Short Put Ladder").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
   		    oPath = strategies.get("Strip").optionPath;
      		oPath.moveTo(x1, cp.y);
        	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Strap").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
/*    		
    		// Synthetic strategies
    		oPath = strategies.get("Synthetic Long Call").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Synthetic Long Put").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Synthetic Long Stock (Split Strikes)").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Synthetic Long Stock").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath= strategies.get("Synthetic Short Call").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Synthetic Short Put").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Synthetic Short Stock (Split Strikes)").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Synthetic Short Stock").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Long Call Synthetic Straddle").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Long Put Synthetic Straddle").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Short Call Synthetic Straddle").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
    		oPath = strategies.get("Short Put Put Synthetic Straddle").optionPath;
      		oPath.moveTo(x1, cp.y);
         	oPath.lineTo(ltx, neg2y);
         	*/
        }
        
        /**
         * Simple class containing two integer values and a comparison function.
         * There's probably something I should use instead, but this was quick and
         * easy to build.
         * 
         */
        private class Coordinate {
            public int x;
            public int y;

            public Coordinate(int newX, int newY) {
                x = newX;
                y = newY;
            }

            public boolean equals(Coordinate other) {
                if (x == other.x && y == other.y) {
                    return true;
                }
                return false;
            }

            @Override
            public String toString() {
                return "Coordinate: [" + x + "," + y + "]";
            }
        }
}
       