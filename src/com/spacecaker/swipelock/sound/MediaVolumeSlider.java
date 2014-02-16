package com.spacecaker.swipelock.sound;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.spacecaker.swipelock.R;

public class MediaVolumeSlider extends LinearLayout {
	
    public static final String my_pref = "MyPrefsFile";
	 
	 public MediaVolumeSlider(final Context context, AttributeSet attrs) {
		  super(context, attrs);
		  
		  
		    final ImageView media =new ImageView(context);
		    LayoutParams mediaLin = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		    mediaLin.gravity=Gravity.CENTER_VERTICAL;
		    media.setLayoutParams(mediaLin);        
		    media.setImageResource(R.drawable.ic_volume_media);
		    this.addView(media);
		    
		    final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		    int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		    int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
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
		            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);
		        }
		    });
		    
		    final ImageView volsetting =new ImageView(context);
		    this.addView(volsetting);
		    LayoutParams settingBox = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		    settingBox.gravity=Gravity.CENTER_VERTICAL;
		    volsetting.setLayoutParams(settingBox);        
		    volsetting.setImageResource(R.drawable.ic_volume_settings_button);    				  
		    volsetting.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try{ 
						Intent i = new Intent();
						i.setClassName("com.android.settings", "com.android.settings.Settings");
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(i);
						}
						catch(Exception ex){           
						}						
				}
			});

	 }

	}
