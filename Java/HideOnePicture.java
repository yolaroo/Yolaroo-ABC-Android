package com.yolaroo.yolarooabc;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class HideOnePicture extends LinearLayout {

	List<Object> myInfoHolder;
	
	MyData theData = new MyData();

	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public HideOnePicture(Context context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.hide_one_pic, this, true);

		MainActivity.setMyContext(context);

		MainActivity.setMyGameName("hideOnePicture");

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());

		hideOnePictureLoadView();

	}

	public void hideOnePictureLoadView() {

		int mywidth = MainActivity.getTheWidth();
		int myMargin = 10;
		mywidth = (mywidth - (4 * myMargin)) / 4;
		int myheight = (int) (int) (Math.round(mywidth / 0.9f));
		;

		String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());

		myInfoHolder = new ArrayList<Object>();

		LinearLayout topLayout = (LinearLayout) findViewById(R.id.mainFrame);
		LinearLayout.LayoutParams layoutParamsOne = new LinearLayout.LayoutParams(
				mywidth, myheight);
		layoutParamsOne.setMargins(0, myMargin, 0, 0);

		MyImageView imageOne = new MyImageView(MainActivity.getMyContext());
		imageOne.setMyName(myStringArray[0]);
		imageOne.setMyTextAlpha(0.0f);
		ImageButton imgOne = imageOne.getMyImagebutton();
		imgOne.setOnClickListener(hidePictureButtonPress(imgOne, imageOne, 1));
		topLayout.addView(imageOne, layoutParamsOne);

		MyImageView imageTwo = new MyImageView(MainActivity.getMyContext());
		imageTwo.setMyName(myStringArray[1]);
		imageTwo.setMyTextAlpha(0.0f);
		ImageButton imgTwo = imageTwo.getMyImagebutton();
		imgTwo.setOnClickListener(hidePictureButtonPress(imgTwo, imageTwo, 2));
		topLayout.addView(imageTwo, layoutParamsOne);

		MyImageView imageThree = new MyImageView(MainActivity.getMyContext());
		imageThree.setMyName(myStringArray[2]);
		imageThree.setMyTextAlpha(0.0f);
		ImageButton imgThree = imageThree.getMyImagebutton();
		imgThree.setOnClickListener(hidePictureButtonPress(imgThree,
				imageThree, 3));
		topLayout.addView(imageThree, layoutParamsOne);

		MyImageView imageFour = new MyImageView(MainActivity.getMyContext());
		imageFour.setMyName(myStringArray[3]);
		imageFour.setMyTextAlpha(0.0f);
		ImageButton imgFour = imageFour.getMyImagebutton();
		imgFour.setOnClickListener(hidePictureButtonPress(imgFour, imageFour, 4));
		topLayout.addView(imageFour, layoutParamsOne);
	}

	View.OnClickListener hidePictureButtonPress(final ImageButton imgOne,
			final MyImageView imageOne, final int myInt) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				myInfoHolder.add(myInt);
				imageOne.setMyTextAlpha(1.0f);
				// imgOne.setAlpha(0.3f);
				myAudioPlayer(imageOne.getMyStringName());
				finishCheck();
			}
		};
	}

	public void finishCheck() {
		if (myInfoHolder.contains(1) && myInfoHolder.contains(2)
				&& myInfoHolder.contains(3) && myInfoHolder.contains(4)) {

			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					((ViewGroup) MainActivity.getMyViewParent()).removeAllViews();
					theData.menuButtonViewLoad();
					HideOnePictureSecondScene thisView = new HideOnePictureSecondScene(
							MainActivity.getMyContext());
					((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
				}
			}, 500);
		}
	}

	public void myAudioPlayer(String myAudioString) {
		int resIdOne = getResources().getIdentifier(
				MainActivity.getAudioBuildName() + myAudioString, null, null);
		MainActivity.myMainAudioPlayer(resIdOne);
	}

	private class MySimpleOnGestureListener extends
			GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			// Log.d("ondown", "two");
			return super.onDown(e);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if ((Math.abs(e1.getX() - e2.getX()) > MainActivity.getSWIPE_MIN_DISTANCE())
					|| (Math.abs(e1.getY() - e2.getY()) > MainActivity.getSWIPE_MIN_DISTANCE())) {
				if (e1.getX() > e2.getX()) { // right to left
					Log.d("Right", "Second Fling");
					theData.mySwipeRight();
				} else {
					Log.d("Left", "Second fling");
					theData.mySwipeLeft();
				}
			} else {
				Log.d("swipe", "not long enough");
			}
			return false;
		}
	}

	public interface OnSwipeListener {
		public abstract void onSwipe(View view, int direction);
	}

	public void setOnSwipeListener(final OnSwipeListener listener) {
		mOnSwipeListener = listener;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		theData.myFingerCounter(ev);
		return mGestureDetector.onTouchEvent(ev);
	}

}
