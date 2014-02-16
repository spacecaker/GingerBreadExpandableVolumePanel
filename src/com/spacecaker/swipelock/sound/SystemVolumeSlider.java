package com.spacecaker.swipelock.sound;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.spacecaker.swipelock.R;

public class SystemVolumeSlider extends LinearLayout {
	
    public static final String my_pref = "MyPrefsFile";
	 
	 public SystemVolumeSlider(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
		    final ImageView media =new ImageView(context);
		    LayoutParams mediaLin = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		    mediaLin.gravity=Gravity.CENTER_VERTICAL;
		    media.setLayoutParams(mediaLin);        
		    media.setImageResource(R.drawable.ic_volume_system);
		    this.addView(media);
		    
		    final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		    int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
		    int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
		    final SeekBar volControl =new SeekBar(context);
            volControl.setEnabled(true);  		   
		    this.addView(volControl);		    
		    volControl.setMax(maxVolume);
		    volControl.setProgress(curVolume);
		    
		    LayoutParams seekbar = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		    seekbar.gravity=Gravity.CENTER_VERTICAL;
		    seekbar.weight = 1f;
	        volControl.setLayoutParams(seekbar);		    
		    volControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		        @Override
		        public void onStopTrackingTouch(SeekBar arg0) {
		        }

		        @Override
		        public void onStartTrackingTouch(SeekBar arg0) {
		        }

		        @Override
		        public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		            audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, arg1, 0);
		        }
		    });
		    
		    final ImageView volsetting =new ImageView(context);
		    this.addView(volsetting);
		    LayoutParams settingBox = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		    settingBox.gravity=Gravity.CENTER_VERTICAL;
		    volsetting.setLayoutParams(settingBox);        
		    volsetting.setImageResource(R.drawable.ic_volume_settings_button); 
			SharedPreferences sharedPreferences = context.getSharedPreferences("VolumePrefsFile",Context.MODE_PRIVATE);    
			Boolean VolumeExtendedVisibility = sharedPreferences.getBoolean("VolumeExtendedVisibility",false);
			if (VolumeExtendedVisibility == true){
				volsetting.setSelected(false);
			}
			else{
				volsetting.setSelected(true); 
			}			
		    volsetting.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {
					if (v.isSelected()){
						v.setSelected(false);
						Intent intent = new Intent();
						intent.setAction("com.spacecaker.swipelock.sound.HIDE_VOLUME_TOGGLE");
						context.sendBroadcast(intent);
						SharedPreferences sharedPreferences = context.getSharedPreferences("VolumePrefsFile",Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
						editor.putBoolean("VolumeExtendedVisibility", true); //true or false
						editor.commit();
					} else {
						v.setSelected(true);
						Intent intent = new Intent();
						intent.setAction("com.spacecaker.swipelock.sound.UNHIDE_VOLUME_TOGGLE");
						context.sendBroadcast(intent);
						SharedPreferences sharedPreferences = context.getSharedPreferences("VolumePrefsFile",Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
						editor.putBoolean("VolumeExtendedVisibility", false); //true or false
						editor.commit();
					}		};
		    });	
			
		    BroadcastReceiver mRingerInfoReceiver=new BroadcastReceiver(){
		          @Override
		          public void onReceive(Context context, Intent intent) {
		  		    int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);  
		  		    volControl.setProgress(curVolume);
		          }
		      };
	          context.registerReceiver(mRingerInfoReceiver, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
	          context.registerReceiver(mRingerInfoReceiver, new IntentFilter("android.media.VIBRATE_SETTING_CHANGED"));
	 }

	}
