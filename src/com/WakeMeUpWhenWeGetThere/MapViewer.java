package com.WakeMeUpWhenWeGetThere;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapViewer extends MapActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		String values = "N10 2PS";
		List<Address> locations = null;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapviewer);
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		Bundle extras = getIntent().getExtras();
		values = extras.getString("PostText");

		Geocoder gc = new Geocoder(this);
		try {
			locations = gc.getFromLocationName(values, 1);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		MapController mc = mapView.getController();
		GeoPoint gp = new GeoPoint(
				(int) (locations.get(0).getLatitude() * 1000000),
				(int) (locations.get(0).getLongitude() * 1000000));
		mc.setZoom(mapView.getMaxZoomLevel() - 5);
		mc.setCenter(gp);
		Button button = (Button) findViewById(R.id.okbutton);
		List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		overlays.add(new MapGestureDetectorOverlay(gp, mapView,
				new SimpleOnGestureListener(), new SimpleOnGestureListener(),
				button));

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}