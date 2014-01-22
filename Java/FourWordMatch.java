package com.yolaroo.yolarooabc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

public class FourWordMatch extends LinearLayout {

	public int myButtonId;

	public Button myGlobalButton;
	String[] myRandomStringArray;
	List<Object> myInfoHolder;

	MyData theData = new MyData();

	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public FourWordMatch(Context context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.four_word_match, this, true);

		MainActivity.setMyContext(context);
		MainActivity.setMyGameName("fourWordMatch");

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());

		fourWordMatchLoadView();
		fourWordMatchClickAction();
	}

	public void fourWordMatchLoadView() {

		int CORNER_RADIUS = 20;

		String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());

		List<Object> list = new ArrayList<Object>();
		Collections.shuffle(list);
		Collections.shuffle(Arrays.asList(myStringArray));

		Random myRandom = new Random();
		int theRandom = myRandom.nextInt(3);
		MainActivity.setTheCorrectWord(myStringArray[theRandom]);

		Button myButtonOne = (Button) findViewById(R.id.buttonOne);
		Button myButtonTwo = (Button) findViewById(R.id.buttonTwo);
		Button myButtonThree = (Button) findViewById(R.id.buttonThree);
		Button myButtonFour = (Button) findViewById(R.id.buttonFour);

		myButtonOne.setBackgroundResource(R.layout.round_button);
		myButtonTwo.setBackgroundResource(R.layout.round_button);
		myButtonThree.setBackgroundResource(R.layout.round_button);
		myButtonFour.setBackgroundResource(R.layout.round_button);

		String myStringOne = myStringArray[0];
		String myStringTwo = myStringArray[1];
		String myStringThree = myStringArray[2];
		String myStringFour = myStringArray[3];

		myButtonOne.setText(myStringOne);
		myButtonTwo.setText(myStringTwo);
		myButtonThree.setText(myStringThree);
		myButtonFour.setText(myStringFour);

		ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.fourWordBigSpellingImage);
		int resIdOne = getResources().getIdentifier(MainActivity.getBuildName() + MainActivity.getTheCorrectWord(),
				null, null);
		Bitmap myBitmapOne = BitmapFactory.decodeResource(getResources(),
				resIdOne);
		imageButtonSetterOne.setImageBitmap(theData.getRoundedCornerBitmap(
				myBitmapOne, CORNER_RADIUS));
	}

	public void fourWordMatchClickAction() {
		Button myButtonOne = (Button) findViewById(R.id.buttonOne);
		myButtonId = myButtonOne.getId();
		myButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickCheckFWM(1);
			}
		});
		Button myButtonTwo = (Button) findViewById(R.id.buttonTwo);
		myButtonId = myButtonTwo.getId();
		myButtonTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickCheckFWM(2);
			}
		});
		Button myButtonThree = (Button) findViewById(R.id.buttonThree);
		myButtonId = myButtonThree.getId();
		myButtonThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickCheckFWM(3);
			}
		});
		Button myButtonFour = (Button) findViewById(R.id.buttonFour);
		myButtonId = myButtonFour.getId();
		myButtonFour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickCheckFWM(4);
			}
		});

		ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.fourWordBigSpellingImage);
		imageButtonSetterOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myAudioPlayer(MainActivity.getTheCorrectWord());
			}
		});
	}

	public void clickCheckFWM(int myButtonId) {
		String myString = "";
		Button myButton = new Button(MainActivity.getMyContext());
		switch (myButtonId) {
		case (1):
			myButton = (Button) findViewById(R.id.buttonOne);
			myString = myButton.getText().toString();
			break;
		case (2):
			myButton = (Button) findViewById(R.id.buttonTwo);
			myString = myButton.getText().toString();
			break;
		case (3):
			myButton = (Button) findViewById(R.id.buttonThree);
			myString = myButton.getText().toString();
			break;
		case (4):
			myButton = (Button) findViewById(R.id.buttonFour);
			myString = myButton.getText().toString();
			break;
		}
		myAudioPlayer(myString);

		if (myString.equals(MainActivity.getTheCorrectWord())) {
			myButton.setBackgroundColor(0x804B0082);
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					fourWordMatchLoadView();
				}
			}, 300);
		} else {
			myButton.setBackgroundColor(0x808b0000);
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
