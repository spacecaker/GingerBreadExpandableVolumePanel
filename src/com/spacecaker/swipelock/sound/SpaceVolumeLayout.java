package com.spacecaker.swipelock.sound;


import com.spacecaker.swipelock.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SpaceVolumeLayout extends LinearLayout {
	
	LinearLayout volumelayout;
	
	 public SpaceVolumeLayout(final Context context, AttributeSet attrs) {
		  super(context, attrs);
			volumelayout = (LinearLayout) findViewById(R.id.volume_layout);	  
	 	    SharedPreferences sharedPreferences = context.getSharedPreferences("VolumePrefsFile",Context.MODE_PRIVATE);    
	 	    Boolean VolumeExtendedVisibility = sharedPreferences.getBoolean("VolumeExtendedVisibility",true);
	 	      if (VolumeExtendedVisibility == false){
            	  volumelayout.setVisibility(VISIBLE);
	 	      }
	 	      else{
            	  volumelayout.setVisibility(GONE); 
	 	      }
          
          BroadcastReceiver mReceiver = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	  volumelayout.setVisibility(GONE); 
              }
              
          }; 
          BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
              @Override
              public void onReceive(Context c, Intent i) {
            	  volumelayout.setVisibility(VISIBLE); 
              }
              
          };        
          context.registerReceiver(mReceiver, new IntentFilter("com.spacecaker.swipelock.sound.HIDE_VOLUME_TOGGLE")); 
          context.registerReceiver(mReceiver1, new IntentFilter("com.spacecaker.swipelock.sound.UNHIDE_VOLUME_TOGGLE"));
	 }
	 
	 
}


