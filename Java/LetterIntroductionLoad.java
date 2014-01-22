package com.yolaroo.yolarooabc;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class LetterIntroductionLoad extends LinearLayout {

	public String theCorrectLetter;
	public int mySingleIndex;
	public int gameModular = 4;

	List<Object> myInfoHolder;

	MyData theData = new MyData();

	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public LetterIntroductionLoad(Context context) {
		super(context);
		Log.d("loaded", "letter introduction");

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.letter_introduction, this, true);

		MainActivity.setMyContext(context);
		MainActivity.setMyGameName("letterIntroduction");

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());

		letterIntroductionLoadView();
	}

	public void letterIntroductionLoadView() {

		// int CORNER_RADIUS = 20;
		int fontSizeBottom = 50;
		int fontSizeTop = 50;
		int buttonPadding = 30;

		// string
		String[] myStringGroupSmall = theData.myDataLoad("smallLetter");
		int myAbcInt = (int) (Math.round(MainActivity.getMyIndex() / gameModular));
		theCorrectLetter = myStringGroupSmall[myAbcInt];
		myInfoHolder = new ArrayList<Object>();

		int width = MainActivity.getTheWidth();
		width = (int) (width / 3.6);
		
		// Left-Top Text
		LinearLayout leftLayout = (LinearLayout) findViewById(R.id.frameLeft);
		leftLayout.removeAllViews();
		LinearLayout.LayoutParams leftLayoutParams = new LinearLayout.LayoutParams(
				width, width);
		Button topLeftButton = new Button(MainActivity.getMyContext());

		topLeftButton.setText(theCorrectLetter);
		topLeftButton.setTextSize(fontSizeTop);
		topLeftButton.setTextColor(0xFFffffff);
		topLeftButton.setBackgroundResource(R.layout.round_button);
		topLeftButton.setPadding(buttonPadding, buttonPadding, buttonPadding,
				buttonPadding);
		topLeftButton.setGravity(Gravity.CENTER_VERTICAL
				| Gravity.CENTER_HORIZONTAL);
		leftLayout.addView(topLeftButton, leftLayoutParams);

		topLeftButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout leftLayout = (LinearLayout) findViewById(R.id.frameLeft);
				leftLayout.removeAllViews();
				myInfoHolder.add("1");
				myAudioPlayer(theCorrectLetter);
				letterIntroductionLoadViewSecondView(1);
			}
		});

		// Right-Top Image
		LinearLayout rightLayout = (LinearLayout) findViewById(R.id.frameRight);
		rightLayout.removeAllViews();
		LinearLayout.LayoutParams rightLayoutParams = new LinearLayout.LayoutParams(
				width, width);

		Button topRightButton = new Button(MainActivity.getMyContext());
		topRightButton.setText(theCorrectLetter);
		topRightButton.setTextSize(fontSizeTop);
		topRightButton.setTextColor(0xFFffffff);
		topRightButton.setBackgroundResource(R.layout.round_button);
		topRightButton.setPadding(buttonPadding, buttonPadding, buttonPadding,
				buttonPadding);
		topRightButton.setGravity(Gravity.CENTER_VERTICAL
				| Gravity.CENTER_HORIZONTAL);
		rightLayout.addView(topRightButton, rightLayoutParams);

		topRightButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout rightLayout = (LinearLayout) findViewById(R.id.frameRight);
				rightLayout.removeAllViews();
				myInfoHolder.add("2");
				myAudioPlayer(theCorrectLetter);
				letterIntroductionLoadViewSecondView(2);
			}
		});

		// Bottom Text
		LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.bottomFrame);
		bottomLayout.removeAllViews();
		LinearLayout.LayoutParams bottomLayoutParams = new LinearLayout.LayoutParams(
				(int) (width*2.3), (width/2));
		Button bottomButton = new Button(MainActivity.getMyContext());
		bottomButton.setText(theCorrectLetter);
		bottomButton.setTextSize(fontSizeBottom);
		bottomButton.setTextColor(0xFFffffff);
		bottomButton.setBackgroundResource(R.layout.round_button);
		bottomButton.setPadding(buttonPadding, buttonPadding, buttonPadding,
				buttonPadding);
		bottomButton.setGravity(Gravity.CENTER_VERTICAL
				| Gravity.CENTER_HORIZONTAL);
		bottomLayout.addView(bottomButton, bottomLayoutParams);

		bottomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.bottomFrame);
				bottomLayout.removeAllViews();
				myInfoHolder.add("3");
				myAudioPlayer(theCorrectLetter);
				letterIntroductionLoadViewSecondView(3);
			}
		});
	}

	public void letterIntroductionLoadViewSecondView(int myInt) {

		int CORNER_RADIUS = 20;
		int fontSizeBottom = 50;
		int fontSizeTop = 80;
		int buttonPadding = 30;
		// int buttonMargin = 10;

		int myAbcInt = (int) (Math.round(MainActivity.getMyIndex() / gameModular));
		String[] myStringGroupSmall = theData.myDataLoad("smallLetter");
		theCorrectLetter = myStringGroupSmall[myAbcInt];
		String[] myStringGroupLarge = theData.myDataLoad("largeLetter");
		String myStringLarge = myStringGroupLarge[myAbcInt];

		String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
		MainActivity.setTheCorrectWord(myStringArray[mySingleIndex]);
		Log.d("myint", Integer.toString(myAbcInt));

		int width = MainActivity.getTheWidth();
		width = (int) (width / 3.6);
		
		switch (myInt) {
		case 1:
			// Left-Top Text
			LinearLayout leftLayout = (LinearLayout) findViewById(R.id.frameLeft);
			
			LinearLayout.LayoutParams leftLayoutParams = new LinearLayout.LayoutParams(
					width, width);
			Button topLeftButton = new Button(MainActivity.getMyContext());

			topLeftButton.setText(myStringLarge + theCorrectLetter);
			topLeftButton.setTextSize(fontSizeTop);
			topLeftButton.setTextColor(0xFFffffff);
			topLeftButton.setBackgroundResource(R.layout.round_button);
			topLeftButton.setPadding(buttonPadding, buttonPadding,
					buttonPadding, buttonPadding);
			topLeftButton.setGravity(Gravity.CENTER_VERTICAL
					| Gravity.CENTER_HORIZONTAL);
			leftLayout.addView(topLeftButton, leftLayoutParams);

			letterIntroductionFinishCheck();

			topLeftButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					myAudioPlayer(theCorrectLetter);
					letterIntroductionFinishCheck();
				}
			});
			break;

		case 2:
			// Right-Top Image
			LinearLayout rightLayout = (LinearLayout) findViewById(R.id.frameRight);
			LinearLayout.LayoutParams rightLayoutParams = new LinearLayout.LayoutParams(
					width, width);
			ImageButton myImage = new ImageButton(MainActivity.getMyContext());
			int resIdOne = getResources().getIdentifier(
					MainActivity.getBuildName() + MainActivity.getTheCorrectWord(), null, null);
			Bitmap myBitmapOne = BitmapFactory.decodeResource(getResources(),
					resIdOne);
			myImage.setImageBitmap(theData.getRoundedCornerBitmap(myBitmapOne,
					CORNER_RADIUS));
			myImage.setScaleType(ScaleType.FIT_CENTER);
			myImage.setBackgroundColor(Color.TRANSPARENT);
			rightLayout.addView(myImage, rightLayoutParams);

			letterIntroductionFinishCheck();

			myImage.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					letterIntroductionFinishCheck();
					myAudioPlayer(MainActivity.getTheCorrectWord());
				}
			});
			break;

		case 3:
			// Bottom Text
			LinearLayout bottomLayout = (LinearLayout) findViewById(R.id.bottomFrame);
			LinearLayout.LayoutParams bottomLayoutParams = new LinearLayout.LayoutParams(
					(int) (width*2.3), width/2);
			Button bottomButton = new Button(MainActivity.getMyContext());
			bottomButton.setText(MainActivity.getTheCorrectWord());
			bottomButton.setTextSize(fontSizeBottom);
			bottomButton.setTextColor(0xFFffffff);
			bottomButton.setBackgroundResource(R.layout.round_button);
			bottomButton.setPadding(buttonPadding, buttonPadding,
					buttonPadding, buttonPadding);
			bottomButton.setGravity(Gravity.CENTER_VERTICAL
					| Gravity.CENTER_HORIZONTAL);
			bottomLayout.addView(bottomButton, bottomLayoutParams);

			letterIntroductionFinishCheck();

			bottomButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					letterIntroductionFinishCheck();
					myAudioPlayer(MainActivity.getTheCorrectWord());
				}
			});
			break;
		}
	}

	public void letterIntroductionFinishCheck() {
		Log.d("finish", "check");
		if (myInfoHolder.contains("1") && myInfoHolder.contains("2")
				&& myInfoHolder.contains("3")) {
			Log.d("finish", "done");

			mySingleIndex++;
			if (mySingleIndex > 3)
				mySingleIndex = 0;
			final Handler secondHandler = new Handler();
			secondHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					myAudioPlayer(MainActivity.getTheCorrectWord());
				}
			}, 300);
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					letterIntroductionLoadView();
				}
			}, 600);
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
