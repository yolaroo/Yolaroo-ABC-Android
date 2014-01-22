package com.yolaroo.yolarooabc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class WordScramble extends LinearLayout {

	public int mySingleIndex;
	public int wordClickCount;

	public int gameModular = 4;
	public boolean clickOk;
	public boolean secondMode;

	public Button myGlobalButton;
	String[] myRandomStringArray;
	List<Object> myInfoHolder;

	MyData theData = new MyData();

	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public WordScramble(Context context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.word_scramble, this, true);

		MainActivity.setMyGameName("wordScramble");

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());

		MainActivity.setMyContext(context);

		wordScrambleLoadView();
	}

	public void wordScrambleLoadView() {
		int CORNER_RADIUS = 20;
		int fontSize;
		int buttonPadding = 15;
		int buttonMargin = 10;

		mySingleIndex = MainActivity.getMySingleIndex();

		String[] myStringArray = theData.myDataLoad(MainActivity.getMyDataName());
		myInfoHolder = new ArrayList<Object>();

		MainActivity.setTheCorrectWord(myStringArray[MainActivity.getMyIndex() + mySingleIndex]);
		wordClickCount = 0;
		clickOk = true;

		String[] strArray = MainActivity.getTheCorrectWord().split("");
		strArray = Arrays.copyOfRange(strArray, 1, strArray.length);

		int stringLength = strArray.length;
		String[] tempStrArray = MainActivity.getTheCorrectWord().split("");
		myRandomStringArray = Arrays.copyOfRange(tempStrArray, 1,
				tempStrArray.length);

		List<Object> randomListArray = new ArrayList<Object>();
		Collections.shuffle(randomListArray);
		Collections.shuffle(Arrays.asList(myRandomStringArray));

		// layout
		
		LinearLayout topLayout = (LinearLayout) findViewById(R.id.frameOneTop);
		topLayout.removeAllViews();

		LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.frameOneBottom);
		bottomLayout.removeAllViews();

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
		
		// note
		// 1280 width
		// 100 @8
		
		// nexus 5 
		// 1794 width
		// 120 @9
		
		for (int i = 0; i <= stringLength - 1; i++) {
			Button button1 = new Button(MainActivity.getMyContext());
			Button button2 = new Button(MainActivity.getMyContext());
			String theString1 = strArray[i];
			String theString2;
			if (secondMode) {
				theString2 = myRandomStringArray[i];
			} else {
				theString2 = strArray[i];
			}

			button1.setText(theString1);
			button1.setTextSize(fontSize);
			button1.setTextColor(0xFFffffff);
			button1.setBackgroundColor(0x80ffffff);
			button1.setTextColor(0x80000000);
			button1.setPadding(buttonPadding, buttonPadding, buttonPadding,
					buttonPadding);
			button1.setGravity(1);
			button1.setTag(i);
			button1.setWidth(width);

			button2.setText(theString2);
			button2.setTextSize(fontSize);
			button2.setTextColor(0xFFffffff);
			button2.setBackgroundResource(R.layout.round_button);
			button2.setPadding(buttonPadding, buttonPadding, buttonPadding,
					buttonPadding);
			button2.setGravity(1);
			button2.setTag(i);
			button2.setWidth(width);

			topLayout.addView(button1, layoutParams);
			bottomLayout.addView(button2, layoutParams);

			button2.setOnClickListener(wordScrambleButtonPress(button2));
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

	View.OnClickListener wordScrambleButtonPress(final Button button2) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				if (clickOk) {
					String myString = button2.getText().toString();
					clickCheckWordScramble(myString, button2);
				}
			}
		};
	}

	public void clickCheckWordScramble(String myString, Button button2) {
		myAudioPlayer(myString);
		String[] strArray = null;
		strArray = MainActivity.getTheCorrectWord().split("");
		strArray = Arrays.copyOfRange(strArray, 1, strArray.length);
		int myTag = (Integer) button2.getTag();

		if (myInfoHolder.contains(myTag)) {
			Log.d("error", "reclick");
		} else {
			if (strArray[wordClickCount].equals(myString)
					&& strArray[wordClickCount] != null) {
				myInfoHolder.add(myTag);
				LinearLayout topLayout = (LinearLayout) findViewById(R.id.frameOneTop);
				View myView = topLayout.getChildAt(wordClickCount);
				Button myButton = new Button(MainActivity.getMyContext());
				myButton = (Button) myView;
				myButton.setBackgroundColor(0x807B68EE);
				myButton.setTextColor(0x80FFFFFF);

				button2.setBackgroundColor(0x80ffffff);
				button2.setTextColor(0x4d000000);
				wordClickCount++;
				finishCheckWScramble();
			} else {
				button2.setBackgroundColor(0x8020B2AA);
				myGlobalButton = button2;

				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						myGlobalButton.setBackgroundColor(0x80000000);
					}
				}, 300);
			}
		}
	}

	public void finishCheckWScramble() {
		if (wordClickCount >= MainActivity.getTheCorrectWord().length()) {
			clickOk = false;
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					if (secondMode) {
						mySingleIndex++;
						secondMode = false;
					} else {
						secondMode = true;
					}
					if (mySingleIndex >= gameModular) {
						mySingleIndex = 0;
						// NextIndex();
					}
					MainActivity.setMySingleIndex(mySingleIndex);
					wordScrambleLoadView();
				}
			}, 300);
		} else {
			//
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
