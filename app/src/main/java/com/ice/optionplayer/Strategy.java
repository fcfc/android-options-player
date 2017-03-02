package com.ice.optionplayer;

import android.graphics.Path;


class Strategy {

	public String name;
	public String play1;
	public String play2;
	public String play3;
	public String play4;
	public String leg1;
	public String leg2;
	public String leg3;
	public String leg4;
	public Path graphPath;
	public Path optionPath;
	
	public Strategy(String string, String string2, String string3,
					String string4, Path path, Path path2) {
		// Auto-generated constructor
		play1 = string;
		play2 = string2;
		play3 = string3;
		play4 = string4;
		leg1 = string;
		leg2 = string2;
		leg3 = string3;
		leg4 = string4;
		graphPath = path;
     	optionPath  = path2;
		
	}
	public void setGraphPath(int x, int y)
	{
	}
	public void setOptionPath(int x, int y)
	{
	}
}

