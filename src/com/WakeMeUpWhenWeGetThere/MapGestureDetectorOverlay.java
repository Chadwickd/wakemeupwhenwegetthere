package com.WakeMeUpWhenWeGetThere;

import java.util.List;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapGestureDetectorOverlay extends Overlay implements
		OnGestureListener, OnDoubleTapListener {
	private final GestureDetector gestureDetector;
	private OnGestureListener onGestureListener;
	private OnDoubleTapListener onDoubleTapListener;
	private MapView mappy;
	private Drawable drawable1;
	private MyItemizedOverlay itemizedOverlay1;
	private GeoPoint Geo;
	private List<Overlay> overlays;
	private Button okButton;

	public MapGestureDetectorOverlay() {
		gestureDetector = new GestureDetector(this);
	}

	public MapGestureDetectorOverlay(GeoPoint gpt, MapView mappy1,
			OnGestureListener onGestureListener,
			OnDoubleTapListener onDoubleTapListener, Button button) {
		this();
		mappy = mappy1;
		Geo = gpt;
		setOnGestureListener(onGestureListener);
		setOnDoubleTapListener(onDoubleTapListener);

		drawable1 = mappy.getResources().getDrawable(
				R.drawable.ic_menu_info_details);
		overlays = mappy.getOverlays();
		itemizedOverlay1 = new MyItemizedOverlay(drawable1);
		itemizedOverlay1.addOverlay(new OverlayItem(Geo, "Food Title 1",
				"Food snippet 1"));
		okButton = button;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {
		if (gestureDetector.onTouchEvent(event)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		if (onGestureListener != null) {
			return onGestureListener.onDown(e);

		}
		return false;
	}

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

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (onGestureListener != null) {
			return onGestureListener.onFling(e1, e2, velocityX, velocityY);
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		if (onGestureListener != null) {
			onGestureListener.onLongPress(e);
		}
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		if (onGestureListener != null) {
			onGestureListener.onScroll(e1, e2, distanceX, distanceY);
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		if (onGestureListener != null) {
			onGestureListener.onShowPress(e);
		}
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		if (onGestureListener != null) {
			onGestureListener.onSingleTapUp(e);
		}
		return false;
	}

	public boolean isLongpressEnabled() {
		return gestureDetector.isLongpressEnabled();
	}

	public void setIsLongpressEnabled(boolean isLongpressEnabled) {
		gestureDetector.setIsLongpressEnabled(isLongpressEnabled);
	}

	public OnGestureListener getOnGestureListener() {
		return onGestureListener;
	}

	public void setOnGestureListener(OnGestureListener onGestureListener) {
		this.onGestureListener = onGestureListener;
	}

	public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
		this.onDoubleTapListener = onDoubleTapListener;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
}