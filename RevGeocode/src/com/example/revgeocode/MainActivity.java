package com.example.revgeocode;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	Geocoder geocode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		geocode = new Geocoder(this);
		List<Address> address = null;

		try {
			if(Geocoder.isPresent()){
				
				Log.i("RevGeocode class", "Geocoder is present");
				address = geocode.getFromLocation(44.87, -93.33, 1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.i("RevGeocode class", "Iterating through addresses");

		Iterator<Address> i = address.iterator();
		while(i.hasNext()){

			Log.i("RevGeocode class", "Address = " + ((Address)i.next()).toString());
			((TextView) findViewById(R.id.tvAddress)).setText(((Address)i.next()).toString());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
