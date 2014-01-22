package com.yolaroo.yolarooabc;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.FrameLayout.LayoutParams;

public class LetterToPictureLoad extends FrameLayout {

	public String theCorrectLetter;
	public int mySingleIndex;
	public int gameModular = 4;

	public Button myGlobalButton;
		
	MyData theData = new MyData();

	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public LetterToPictureLoad(Context context) {
		super(context);

		Log.d("loaded", "letter to picture");

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.letter_match_scene_one, this, true);

		MainActivity.setMyGameName("letterMatch");
		MainActivity.setMyContext(context);

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());

		
		letterMatchLoadView();
	}

	public void letterMatchLoadView() {

		mySingleIndex = MainActivity.getMySingleIndex();

		String[] myStringGroupSmall = theData.myDataLoad("smallLetter");
		int myAbcInt = (int) (Math.round(MainActivity.getMyIndex() / gameModular));
		theCorrectLetter = myStringGroupSmall[myAbcInt];

		Button myButtonOne = (Button) findViewById(R.id.bigSpellingButton);
		myButtonOne.setText(theCorrectLetter);
		myButtonOne.setTag(0);
		myGlobalButton = myButtonOne;

		myButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myGlobalButton.setAlpha(0.4f);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						myGlobalButton.setAlpha(1.0f);
						letterMatchSceneOneCheck();
					}
				}, 300);
			}
		});
	}

	public void letterMatchSceneOneCheck() {
		myAudioPlayer(theCorrectLetter);
		if (myGlobalButton.getTag().equals(0)) {
			myGlobalButton.setTextColor(0xFFFF4500);
			myGlobalButton.setTag(1);
		} else if (myGlobalButton.getTag().equals(1)) {
			myGlobalButton.setTextColor(0xFF00BFFF);
			myGlobalButton.setTag(2);
		} else if (myGlobalButton.getTag().equals(2)) {
			myGlobalButton.setTextColor(0xFFFFB6C1);
			myGlobalButton.setTag(3);
		} else if (myGlobalButton.getTag().equals(3)) {
			myGlobalButton.setTextColor(0x00800080);
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					((ViewGroup) MainActivity.getMyViewParent()).removeAllViews();
					theData.menuButtonViewLoad();
					LetterToPictureSecondScene thisView = new LetterToPictureSecondScene(
							MainActivity.getMyContext());
					((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
				}
			}, 300);
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
