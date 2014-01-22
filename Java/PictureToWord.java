package com.yolaroo.yolarooabc;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PictureToWord extends LinearLayout {

	public int myButtonId;
	public int clickInt;
	
	public int clickCount;
	public int gameModular = 4;

	public boolean clickOk;
	public boolean clickAlright;
	
	List<Object> myInfoHolder;
	MyData theData = new MyData();

	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public PictureToWord(Context context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.picture_to_word, this, true);

		MainActivity.setMyGameName("pictureToWord");
		MainActivity.setMyContext(context);

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());

		
		if (MainActivity.getIsLarge()) {	
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					//layoutSize ();
				}
			}, 600);
		}			

		pictureToWordLoadView();
		pictureToWordCLickAction();
	}

	public void layoutSize () {
		Log.d("LayoutSize for Large","picture to word");
		LinearLayout fillLayout = (LinearLayout) findViewById(R.id.topFrame);		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				(int) (MainActivity.getTheHeight()*.8));
		layoutParams.setMargins(0, 10, 0, 50);
		fillLayout.setLayoutParams(layoutParams);
		
	    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 0, 0, 1);
		TextView myTextOne = (TextView) findViewById(R.id.textOne);
		myTextOne.setGravity(Gravity.CENTER | Gravity.BOTTOM);

		myTextOne.setLayoutParams(params);	
	}

	public void pictureToWordLoadView() {
		int CORNER_RADIUS = 20;

		String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
		myInfoHolder = new ArrayList<Object>();
		clickAlright = true;
		// myInfoHolder = ArrayList<Integer>();

		TextView textViewTitleOne = (TextView) findViewById(R.id.textOne);
		TextView textViewTitleTwo = (TextView) findViewById(R.id.textTwo);
		TextView textViewTitleThree = (TextView) findViewById(R.id.textThree);
		TextView textViewTitleFour = (TextView) findViewById(R.id.textFour);

		ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOne);
		ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwo);
		ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThree);
		ImageButton imageButtonSetterFour = (ImageButton) findViewById(R.id.imageFour);

		Button myButtonOne = (Button) findViewById(R.id.buttonOne);
		Button myButtonTwo = (Button) findViewById(R.id.buttonTwo);
		Button myButtonThree = (Button) findViewById(R.id.buttonThree);
		Button myButtonFour = (Button) findViewById(R.id.buttonFour);

		String myStringOne = myStringArray[0];
		String myStringTwo = myStringArray[1];
		String myStringThree = myStringArray[2];
		String myStringFour = myStringArray[3];

		myButtonOne.setText(myStringOne);
		myButtonTwo.setText(myStringTwo);
		myButtonThree.setText(myStringThree);
		myButtonFour.setText(myStringFour);

		myButtonOne.setBackgroundResource(R.layout.round_button);
		myButtonTwo.setBackgroundResource(R.layout.round_button);
		myButtonThree.setBackgroundResource(R.layout.round_button);
		myButtonFour.setBackgroundResource(R.layout.round_button);

		textViewTitleOne.setText(myStringOne);
		textViewTitleTwo.setText(myStringTwo);
		textViewTitleThree.setText(myStringThree);
		textViewTitleFour.setText(myStringFour);

		textViewTitleOne.setAlpha(0f);
		textViewTitleTwo.setAlpha(0f);
		textViewTitleThree.setAlpha(0f);
		textViewTitleFour.setAlpha(0f);

		myButtonOne.setAlpha(1f);
		myButtonTwo.setAlpha(1f);
		myButtonThree.setAlpha(1f);
		myButtonFour.setAlpha(1f);

		imageButtonSetterOne.setAlpha(1f);
		imageButtonSetterTwo.setAlpha(1f);
		imageButtonSetterThree.setAlpha(1f);
		imageButtonSetterFour.setAlpha(1f);

		int resIdOne = getResources().getIdentifier(MainActivity.getBuildName() + myStringOne,
				null, null);
		int resIdTwo = getResources().getIdentifier(MainActivity.getBuildName() + myStringTwo,
				null, null);
		int resIdThree = getResources().getIdentifier(
				MainActivity.getBuildName() + myStringThree, null, null);
		int resIdFour = getResources().getIdentifier(MainActivity.getBuildName() + myStringFour,
				null, null);

		Bitmap myBitmapOne = BitmapFactory.decodeResource(getResources(),
				resIdOne);
		Bitmap myBitmapTwo = BitmapFactory.decodeResource(getResources(),
				resIdTwo);
		Bitmap myBitmapThree = BitmapFactory.decodeResource(getResources(),
				resIdThree);
		Bitmap myBitmapFour = BitmapFactory.decodeResource(getResources(),
				resIdFour);

		imageButtonSetterOne.setImageBitmap(theData.getRoundedCornerBitmap(
				myBitmapOne, CORNER_RADIUS));
		imageButtonSetterTwo.setImageBitmap(theData.getRoundedCornerBitmap(
				myBitmapTwo, CORNER_RADIUS));
		imageButtonSetterThree.setImageBitmap(theData.getRoundedCornerBitmap(
				myBitmapThree, CORNER_RADIUS));
		imageButtonSetterFour.setImageBitmap(theData.getRoundedCornerBitmap(
				myBitmapFour, CORNER_RADIUS));
	}

	public void pictureToWordCLickAction() {

		Button myButtonOne = (Button) findViewById(R.id.buttonOne);
		myButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
				myAudioPlayer(myStringArray[0]);
				if (myInfoHolder.contains("1")) {
					Log.d("error", "1");
				} else {
					ClickCheckPTWSecond(1);
				}
			}
		});
		Button myButtonTwo = (Button) findViewById(R.id.buttonTwo);
		myButtonTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
				myAudioPlayer(myStringArray[1]);
				if (myInfoHolder.contains("2")) {
					Log.d("error", "2");
				} else {
					ClickCheckPTWSecond(2);
				}
			}
		});
		Button myButtonThree = (Button) findViewById(R.id.buttonThree);
		myButtonThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
				myAudioPlayer(myStringArray[2]);
				if (myInfoHolder.contains("3")) {
					Log.d("error", "3");
				} else {
					ClickCheckPTWSecond(3);
				}
			}
		});
		Button myButtonFour = (Button) findViewById(R.id.buttonFour);
		myButtonFour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
				myAudioPlayer(myStringArray[3]);
				if (myInfoHolder.contains("4")) {
					Log.d("error", "4");
				} else {
					ClickCheckPTWSecond(4);
				}
			}
		});

		// top click

		ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOne);
		imageButtonSetterOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
				myAudioPlayer(myStringArray[0]);
				if (myInfoHolder.contains(1)) {
					Log.d("error", "1");
				} else {
					if (clickAlright) {
						clickAlright = false;
						clickCheckPTW(1);
					}
				}
			}
		});

		ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwo);
		imageButtonSetterTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
				myAudioPlayer(myStringArray[1]);
				if (myInfoHolder.contains(2)) {
					Log.d("error", "2");
				} else {
					if (clickAlright) {
						clickAlright = false;
						clickCheckPTW(2);
					}
				}
			}
		});

		ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThree);
		imageButtonSetterThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
				myAudioPlayer(myStringArray[2]);
				if (myInfoHolder.contains(3)) {
					Log.d("error", "3");
				} else {
					if (clickAlright) {
						clickAlright = false;
						clickCheckPTW(3);
					}
				}
			}
		});

		ImageButton imageButtonSetterFour = (ImageButton) findViewById(R.id.imageFour);
		imageButtonSetterFour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
				myAudioPlayer(myStringArray[3]);
				if (myInfoHolder.contains(4)) {
					Log.d("error", "4");
				} else {
					if (clickAlright) {
						clickAlright = false;
						clickCheckPTW(4);
					}
				}
			}
		});
	}

	public void ClickCheckPTWSecond(int myInt) {
		Log.d("second", "click");
		if (myInt == clickInt && clickOk) {
			Button buttonViewTitle = (Button) findViewById(myButtonId);
			buttonViewTitle.setBackgroundColor(0x40000000);
			clickOk = false;
			clickAlright = true;
			clickCount++;
			Log.d("added", String.valueOf(myInt));
			myInfoHolder.add(myInt);
			viewChangePTW(myInt);
			finishCheckPTW();
		}
	}

	public void viewChangePTW(int myInt) {
		switch (myInt) {
		case 1:
			ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOne);
			Button myButtonOne = (Button) findViewById(R.id.buttonOne);
			TextView textViewTitleOne = (TextView) findViewById(R.id.textOne);
			imageButtonSetterOne.setAlpha(0.4f);
			myButtonOne.setAlpha(0.4f);
			textViewTitleOne.setAlpha(0.4f);
			break;
		case 2:
			ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwo);
			Button myButtonTwo = (Button) findViewById(R.id.buttonTwo);
			TextView textViewTitleTwo = (TextView) findViewById(R.id.textTwo);
			imageButtonSetterTwo.setAlpha(0.4f);
			myButtonTwo.setAlpha(0.4f);
			textViewTitleTwo.setAlpha(0.4f);
			break;
		case 3:
			ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThree);
			Button myButtonThree = (Button) findViewById(R.id.buttonThree);
			TextView textViewTitleThree = (TextView) findViewById(R.id.textThree);
			imageButtonSetterThree.setAlpha(0.4f);
			myButtonThree.setAlpha(0.4f);
			textViewTitleThree.setAlpha(0.4f);
			break;
		case 4:
			ImageButton imageButtonSetterFour = (ImageButton) findViewById(R.id.imageFour);
			Button myButtonfour = (Button) findViewById(R.id.buttonFour);
			TextView textViewTitleFour = (TextView) findViewById(R.id.textFour);
			imageButtonSetterFour.setAlpha(0.4f);
			myButtonfour.setAlpha(0.4f);
			textViewTitleFour.setAlpha(0.4f);
			break;
		}
	}

	public void clickCheckPTW(int myInt) {
		Log.d("first", "click");

		TextView textViewTitle = new TextView(MainActivity.getMyContext());
		Button buttonViewTitle = new Button(MainActivity.getMyContext());
		switch (myInt) {
		case 1:
			textViewTitle = (TextView) findViewById(R.id.textOne);
			textViewTitle.setAlpha(1f);
			buttonViewTitle = (Button) findViewById(R.id.buttonOne);
			myButtonId = buttonViewTitle.getId();
			buttonViewTitle.setBackgroundColor(0x80000fff);
			clickInt = 1;
			clickOk = true;
			break;
		case 2:
			textViewTitle = (TextView) findViewById(R.id.textTwo);
			textViewTitle.setAlpha(1f);
			buttonViewTitle = (Button) findViewById(R.id.buttonTwo);
			myButtonId = buttonViewTitle.getId();
			buttonViewTitle.setBackgroundColor(0x80000fff);
			clickInt = 2;
			clickOk = true;
			break;
		case 3:
			textViewTitle = (TextView) findViewById(R.id.textThree);
			textViewTitle.setAlpha(1f);
			buttonViewTitle = (Button) findViewById(R.id.buttonThree);
			myButtonId = buttonViewTitle.getId();
			buttonViewTitle.setBackgroundColor(0x80000fff);
			clickInt = 3;
			clickOk = true;
			break;
		case 4:
			textViewTitle = (TextView) findViewById(R.id.textFour);
			textViewTitle.setAlpha(1f);
			buttonViewTitle = (Button) findViewById(R.id.buttonFour);
			myButtonId = buttonViewTitle.getId();
			buttonViewTitle.setBackgroundColor(0x80000fff);
			clickInt = 4;
			clickOk = true;
			break;
		}
	}

	public void finishCheckPTW() {
		if (clickCount >= gameModular) {
			clickCount = 0;
			pictureToWordLoadView();
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
