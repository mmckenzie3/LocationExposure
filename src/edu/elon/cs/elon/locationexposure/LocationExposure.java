package edu.elon.cs.elon.locationexposure;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LocationExposure extends Activity {

	private TextView textView;
	private RelativeLayout relativeLayout;
	private LocationManager locManager;
	
	private int[] colors = {Color.BLUE, Color.GREEN};
	private int which;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_exposure);
		
		textView = (TextView) findViewById(R.id.textview);
		relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);
		
		which = -1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_location_exposure, menu);
		return true;
	}

	@Override
	protected void onResume(){
		super.onResume();
		
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		//locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		locManager.removeUpdates(locationListener);
	}
	
	private LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// grab the location
			double currentLatitude = location.getLatitude();
			double currentLongitude = location.getLongitude();
			
			textView.setText("Latitude: " + currentLatitude + "\n" + "Longitude: " + currentLongitude);
			which = (which+1) % colors.length;
			
		}
	};
}
