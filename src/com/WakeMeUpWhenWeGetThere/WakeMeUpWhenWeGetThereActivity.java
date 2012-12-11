package com.WakeMeUpWhenWeGetThere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class WakeMeUpWhenWeGetThereActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void callMaps(View view) {

		Intent MapIntent = new Intent(this, MapViewer.class);
		MapIntent.putExtra("PostText",
				((EditText) findViewById(R.id.editText1)).getText().toString());
		startActivity(MapIntent);

	}
}