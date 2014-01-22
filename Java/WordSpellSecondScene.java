package com.yolaroo.yolarooabc;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class WordSpellSecondScene extends LinearLayout {

	public int mySingleIndex;
	
	public int clickCount;
	public int wordClickCount;
	
	public boolean clickOk;

	public Button myGlobalButton;
	List<Object> myInfoHolder;

	MyData theData = new MyData();

	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public WordSpellSecondScene(Context context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.word_spell_scene_two, this, true);

		MainActivity.setMyGameName("wordSpell");

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());

		MainActivity.setMyContext(context);

		wordSpellSceneTwoLoadView();
	}

	public void wordSpellSceneTwoLoadView() {

		int CORNER_RADIUS = 20;
		int fontSize;
		int buttonPadding = 15;
		int buttonMargin = 10;

		mySingleIndex = MainActivity.getMySingleIndex();

		clickOk = true;
		wordClickCount = 1;

		String[] myStringArray = theData.myDataLoad(MainActivity.getMyDataName());
		MainActivity.setTheCorrectWord(myStringArray[MainActivity.getMyIndex() + mySingleIndex]);
		myInfoHolder = new ArrayList<Object>();

		String[] strArray = MainActivity.getTheCorrectWord().split("");
		int stringLength = strArray.length;

		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.frameTwoTop);
		mainLayout.removeAllViews();

		int width = MainActivity.getTheWidth();
		width = (int) ((width/10) *.66);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,(int) (width*1.55));
		layoutParams.setMargins(buttonMargin, 0, 0, 0);

		if (MainActivity.getIsLarge()){
			fontSize = 48;
		}
		else if (MainActivity.getisXLarge()) {
			fontSize = 62;
		}
		else {
			fontSize = 38;
		}
	
		for (int i = 1; i < stringLength; i++) {
			Button button1 = new Button(MainActivity.getMyContext());
			String theString1 = strArray[i];
			button1.setText(theString1);
			button1.setTextSize(fontSize);
			button1.setTextColor(0xFFffffff);
			button1.setBackgroundResource(R.layout.round_button);
			button1.setPadding(buttonPadding, buttonPadding, buttonPadding,
					buttonPadding);
			button1.setGravity(1);
			button1.setTag(i);
			button1.setWidth(width);

			mainLayout.addView(button1, layoutParams);

			button1.setOnClickListener(getOnClickDoSomething(button1));
		}

		ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.bigSpellingImage);
		int resIdOne = getResources().getIdentifier(MainActivity.getBuildName() + MainActivity.getTheCorrectWord(),
				null, null);
		Bitmap myBitmapOne = BitmapFactory.decodeResource(getResources(),
				resIdOne);
		imageButtonSetterOne.setImageBitmap(theData.getRoundedCornerBitmap(
				myBitmapOne, CORNER_RADIUS));

		imageButtonSetterOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myAudioPlayer(MainActivity.getTheCorrectWord());
			}
		});
	}

	View.OnClickListener getOnClickDoSomething(final Button button1) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				String myString = button1.getText().toString();
				if (clickOk) {
					clickCheckWS(myString, button1);
				}
			}
		};
	}

	public void clickCheckWS(String myString, Button button1) {

		String[] strArray = MainActivity.getTheCorrectWord().split("");
		myAudioPlayer(myString);
		Log.d("letter", strArray[wordClickCount]);
		int myTag = (Integer) button1.getTag();

		if (myInfoHolder.contains(myTag)) {
			Log.d("error", "reclick");
		} else {
			if (strArray[wordClickCount].equals(myString)) {
				myInfoHolder.add(myTag);
				button1.setBackgroundColor(0x80ffffff);
				button1.setTextColor(0x80000000);
				wordClickCount++;
				finishCheckWS();
			} else { // error
				button1.setBackgroundColor(0x800000ff);
				myGlobalButton = button1;
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						myGlobalButton.setBackgroundColor(0x80000000);
						Log.d("error", "general");
					}
				}, 300);
			}
		}
	}

	public void finishCheckWS() {

		if (wordClickCount >= 1 + MainActivity.getTheCorrectWord().length()) {
			clickOk = false;
			mySingleIndex++;
			if (mySingleIndex > 3)
				mySingleIndex = 0;
			MainActivity.setMySingleIndex(mySingleIndex);
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					((ViewGroup) MainActivity.getMyViewParent()).removeAllViews();
					theData.menuButtonViewLoad();
					WordSpell thisView = new WordSpell(MainActivity.getMyContext());
					((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
				}
			}, 300);
		} else {
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
