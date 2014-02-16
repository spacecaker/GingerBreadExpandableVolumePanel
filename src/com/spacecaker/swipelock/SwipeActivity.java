package com.spacecaker.swipelock;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SwipeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_swipe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.swipe, menu);
		return true;
	}

}
