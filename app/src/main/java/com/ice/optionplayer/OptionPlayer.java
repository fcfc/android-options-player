package com.ice.optionplayer;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class OptionPlayer extends Activity {

	private static final String LIST_TAB_TAG = "List";
	private static final String MAP_TAB_TAG = "Map";
	private static final String MAP_NEU_TAG = "Neutral";
	private static final String MAP_SYN_TAG = "Synthetic";

	TabHost tabHost;
	private ListView listView;	
	private ListView listView2;
	private ListView listView3;
	private ListView listView4;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    Resources res = getResources(); // Resource object to get Drawables

	    tabHost = (TabHost) findViewById(android.R.id.tabhost);
	    tabHost.setup();

	    // setup list view
	    listView = (ListView) findViewById(R.id.list);
	    listView.setEmptyView((TextView) findViewById(R.id.empty));
	    
        List<MyListItem> pointsList = new ArrayList<MyListItem>();
        TypedArray bull = res.obtainTypedArray(R.array.bull);

        for (int i=0; i<bull.length(); i++)    {
            pointsList.add(new MyListItem(R.drawable.ic_menu_compass, bull.getString(i), null, null, null, null));
		}

        listView.setAdapter(new CustomAdapter(this, pointsList));
        listView.setOnItemClickListener(new OnItemClickListener() {
    
           public void onItemClick(AdapterView parent, View view, int position, long id) {
                CharSequence testString = ((TextView) view).getText();
                Intent i = new Intent(OptionPlayer.this, OptionCalc.class);
                i.putExtra("type", testString);
                startActivity(i);
            }
        });

        listView2 = (ListView) findViewById(R.id.list2);
	    listView2.setEmptyView((TextView) findViewById(R.id.empty));
        List<String> point2List = new ArrayList<String>();
	    
        TypedArray bear = res.obtainTypedArray(R.array.bear);

        // populate list

        for (int i=0; i<bear.length(); i++)    {
            point2List.add(new String(bear.getString(i)));

	   }
        listView2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, point2List));
        listView2.setVisibility(View.INVISIBLE);
        listView2.setOnItemClickListener(new OnItemClickListener() {
            
        public void onItemClick(AdapterView parent, View view, int position, long id) {
             // programmatically switch tabs to the map view
        	//  Object o = this.getItem(position);
            CharSequence testString = ((TextView) view).getText();
            Intent i = new Intent(OptionPlayer.this, OptionCalc.class);
            i.putExtra("type", testString);
            startActivity(i);
            }
        });
        
        listView3 = (ListView) findViewById(R.id.list3);
	    listView3.setEmptyView((TextView) findViewById(R.id.empty));
	    
        List<String> point3List = new ArrayList<String>();
        TypedArray neutral = res.obtainTypedArray(R.array.neutral);

        // populate list

        for (int i=0; i<neutral.length(); i++)    {
            point3List.add(new String(neutral.getString(i)));

	   }

        
        listView3.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, point3List));
        listView3.setVisibility(View.INVISIBLE);
        listView3.setOnItemClickListener(new OnItemClickListener() {
            
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                        	    
                        	                    // programmatically switch tabs to the map view
//                        	            		Object o = this.getItem(position);
                        	                    CharSequence testString = ((TextView) view).getText();
                        	                    Intent i = new Intent(OptionPlayer.this, OptionCalc.class);
                        	            	    i.putExtra("type", testString);
                        	                    startActivity(i);

                        	            }
                        	        });
        
        listView4 = (ListView) findViewById(R.id.list4);
	    listView4.setEmptyView((TextView) findViewById(R.id.empty));
	    
        List<String> point4List = new ArrayList<String>();
        TypedArray synthetic = res.obtainTypedArray(R.array.synthetic);

        for (int i=0; i<synthetic.length(); i++)    {
            point4List.add(new String(synthetic.getString(i)));

	   }

        listView4.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, point4List));
        listView4.setVisibility(View.INVISIBLE);
        listView4.setOnItemClickListener(new OnItemClickListener() {
            
        public void onItemClick(AdapterView parent, View view, int position, long id) {
                        	    
                        	                    // programmatically switch tabs to the map view
//                        	            		Object o = this.getItem(position);
                        	                    CharSequence testString = ((TextView) view).getText();
                        	                    Intent i = new Intent(OptionPlayer.this, OptionCalc.class);
                        	            	    i.putExtra("type", testString);
                        	                    startActivity(i);

                        	            }
                        	        });
        // add views to tab host
	            tabHost.addTab(tabHost.newTabSpec(LIST_TAB_TAG).setIndicator("Bull",
	                      res.getDrawable(R.drawable.ic_tab_bull)).setContent(new TabContentFactory() {
	                public View createTabContent(String arg0) {
	                    return listView;
	                }
	            }));
	            tabHost.addTab(tabHost.newTabSpec(MAP_TAB_TAG).setIndicator("Bear",
	            		res.getDrawable(R.drawable.ic_tab_bear)).setContent(new TabContentFactory() {
	                public View createTabContent(String arg0) {
	                    return listView2;
	                }
	            }));
	            tabHost.addTab(tabHost.newTabSpec(MAP_NEU_TAG).setIndicator("Neutral",
	                      res.getDrawable(R.drawable.ic_tab_albums)).setContent(new TabContentFactory() {
	                public View createTabContent(String arg0) {
	                    return listView3;
	                }
	            }));
	            tabHost.addTab(tabHost.newTabSpec(MAP_SYN_TAG).setIndicator("Synthetic",
	                      res.getDrawable(R.drawable.ic_tab_albums)).setContent(new TabContentFactory() {
	                public View createTabContent(String arg0) {
	                    return listView4;
	                }
	            }));
	            
	            tabHost.setCurrentTab(3);
	            tabHost.setCurrentTab(2);
	            tabHost.setCurrentTab(1);
	            tabHost.setCurrentTab(0);

	}


	public void onResume(Bundle savedInstanceState) {
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
	
}


