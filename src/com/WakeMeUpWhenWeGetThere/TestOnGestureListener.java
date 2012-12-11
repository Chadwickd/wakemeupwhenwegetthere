package com.WakeMeUpWhenWeGetThere;

import android.graphics.PorterDuff;
import android.view.MotionEvent;

import com.google.android.maps.OverlayItem;

public class TestOnGestureListener extends
		android.view.GestureDetector.SimpleOnGestureListener {
	@Override
	public boolean onDoubleTap(MotionEvent e) {
		if (onDoubleTapListener != null) {
			System.out.println(e.getX());
			System.out.println(e.getY());
			Geo = mappy.getProjection().fromPixels((int) e.getX(),
					(int) e.getY());
			itemizedOverlay1.setItem(0, (new OverlayItem(Geo, "shit",
					"moreshit")));
			overlays.add(itemizedOverlay1);
			okButton.getBackground().setColorFilter(0xFF00FF00,
					PorterDuff.Mode.MULTIPLY);
			okButton.setText("Wake Me Up When We Get There!");
			return onDoubleTapListener.onDoubleTap(e);
		}
		return false;
	}
}
