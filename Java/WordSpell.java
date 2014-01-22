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
import android.widget.Button;
import android.widget.FrameLayout;

public class WordSpell extends FrameLayout {

	public int mySingleIndex;

	public Button myGlobalButton;
	List<Object> myInfoHolder;

	MyData theData = new MyData();

	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public WordSpell(Context context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.word_spell, this, true);

		MainActivity.setMyGameName("wordSpell");

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());

		MainActivity.setMyContext(context);

		wordSpellLoadView();
	}

	public void wordSpellLoadView() {

		mySingleIndex = MainActivity.getMySingleIndex();

		String[] myStringArray = theData.myDataLoad(MainActivity.getMyDataName());
		MainActivity.setTheCorrectWord(myStringArray[MainActivity.getMyIndex() + mySingleIndex]);
		myInfoHolder = new ArrayList<Object>();

		Button myButtonOne = (Button) findViewById(R.id.bigSpellingButton);
		myButtonOne.setText(MainActivity.getTheCorrectWord());
		myGlobalButton = myButtonOne;

		myButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myGlobalButton.setBackgroundColor(0x59FF0000);
				myAudioPlayer(MainActivity.getTheCorrectWord());
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						((ViewGroup) MainActivity.getMyViewParent()).removeAllViews();
						theData.menuButtonViewLoad();
						WordSpellSecondScene thisView = new WordSpellSecondScene(
								MainActivity.getMyContext());
						((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
					}
				}, 300);
			}
		});
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
