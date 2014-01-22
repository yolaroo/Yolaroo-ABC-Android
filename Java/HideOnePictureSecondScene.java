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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HideOnePictureSecondScene extends LinearLayout {

	public int myButtonId;


	MyData theData = new MyData();

	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public HideOnePictureSecondScene(Context context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.hide_one_pic_second_scene, this, true);

		MainActivity.setMyContext(context);

		MainActivity.setMyGameName("hideOnePicture");

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());

		HideOnePictureSecondSceneLoad();
		hideOnePictureClickAction();
	}

	public void HideOnePictureSecondSceneLoad() {
		int CORNER_RADIUS = 20;

		// top
		String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
		List<Object> list = new ArrayList<Object>();
		Collections.shuffle(list);
		Collections.shuffle(Arrays.asList(myStringArray));
		MainActivity.setTheCorrectWord(myStringArray[3]);

		// bot
		String[] myBottomStringArray = theData.fourArraySetter(MainActivity.getMyIndex());
		List<Object> bottomList = new ArrayList<Object>();
		Collections.shuffle(bottomList);
		Collections.shuffle(Arrays.asList(myBottomStringArray));

		// top
		TextView textViewTitleOne = (TextView) findViewById(R.id.textOne);
		TextView textViewTitleTwo = (TextView) findViewById(R.id.textTwo);
		TextView textViewTitleThree = (TextView) findViewById(R.id.textThree);

		ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOne);
		ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwo);
		ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThree);

		String myStringOne = myStringArray[0];
		String myStringTwo = myStringArray[1];
		String myStringThree = myStringArray[2];

		textViewTitleOne.setText(myStringOne);
		textViewTitleTwo.setText(myStringTwo);
		textViewTitleThree.setText(myStringThree);

		textViewTitleOne.setAlpha(0f);
		textViewTitleTwo.setAlpha(0f);
		textViewTitleThree.setAlpha(0f);

		imageButtonSetterOne.setAlpha(1f);
		imageButtonSetterTwo.setAlpha(1f);
		imageButtonSetterThree.setAlpha(1f);

		int resIdOne = getResources().getIdentifier(MainActivity.getBuildName() + myStringOne,
				null, null);
		int resIdTwo = getResources().getIdentifier(MainActivity.getBuildName() + myStringTwo,
				null, null);
		int resIdThree = getResources().getIdentifier(
				MainActivity.getBuildName() + myStringThree, null, null);

		Bitmap myBitmapOne = BitmapFactory.decodeResource(getResources(),
				resIdOne);
		Bitmap myBitmapTwo = BitmapFactory.decodeResource(getResources(),
				resIdTwo);
		Bitmap myBitmapThree = BitmapFactory.decodeResource(getResources(),
				resIdThree);

		imageButtonSetterOne.setImageBitmap(theData.getRoundedCornerBitmap(
				myBitmapOne, CORNER_RADIUS));
		imageButtonSetterTwo.setImageBitmap(theData.getRoundedCornerBitmap(
				myBitmapTwo, CORNER_RADIUS));
		imageButtonSetterThree.setImageBitmap(theData.getRoundedCornerBitmap(
				myBitmapThree, CORNER_RADIUS));

		// bottom
		Button myButtonOne = (Button) findViewById(R.id.buttonOne);
		Button myButtonTwo = (Button) findViewById(R.id.buttonTwo);
		Button myButtonThree = (Button) findViewById(R.id.buttonThree);
		Button myButtonFour = (Button) findViewById(R.id.buttonFour);

		String myStringOneBottom = myBottomStringArray[0];
		String myStringTwoBottom = myBottomStringArray[1];
		String myStringThreeBottom = myBottomStringArray[2];
		String myStringFourBottom = myBottomStringArray[3];

		myButtonOne.setText(myStringOneBottom);
		myButtonTwo.setText(myStringTwoBottom);
		myButtonThree.setText(myStringThreeBottom);
		myButtonFour.setText(myStringFourBottom);

		myButtonOne.setBackgroundResource(R.layout.round_button);
		myButtonTwo.setBackgroundResource(R.layout.round_button);
		myButtonThree.setBackgroundResource(R.layout.round_button);
		myButtonFour.setBackgroundResource(R.layout.round_button);

		myButtonOne.setAlpha(1f);
		myButtonTwo.setAlpha(1f);
		myButtonThree.setAlpha(1f);
		myButtonFour.setAlpha(1f);
	}

	public void hideOnePictureClickAction() {
		Button myButtonOne = (Button) findViewById(R.id.buttonOne);
		myButtonId = myButtonOne.getId();
		myButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickCheckHOPSS(1);
			}
		});
		Button myButtonTwo = (Button) findViewById(R.id.buttonTwo);
		myButtonId = myButtonTwo.getId();
		myButtonTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickCheckHOPSS(2);
			}
		});
		Button myButtonThree = (Button) findViewById(R.id.buttonThree);
		myButtonId = myButtonThree.getId();
		myButtonThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickCheckHOPSS(3);
			}
		});
		Button myButtonFour = (Button) findViewById(R.id.buttonFour);
		myButtonId = myButtonFour.getId();
		myButtonFour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickCheckHOPSS(4);
			}
		});

		// top

		ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOne);
		imageButtonSetterOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textViewTitle = (TextView) findViewById(R.id.textOne);
				textViewTitle.setAlpha(1f);
				myAudioPlayer((String) textViewTitle.getText());
			}
		});

		ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwo);
		imageButtonSetterTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textViewTitle = (TextView) findViewById(R.id.textTwo);
				textViewTitle.setAlpha(1f);
				myAudioPlayer((String) textViewTitle.getText());
			}
		});

		ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThree);
		imageButtonSetterThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textViewTitle = (TextView) findViewById(R.id.textThree);
				textViewTitle.setAlpha(1f);
				myAudioPlayer((String) textViewTitle.getText());
			}
		});
	}

	public void clickCheckHOPSS(int myInt) {
		String myString = "";
		Button myButton = new Button(MainActivity.getMyContext());
		switch (myInt) {
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
					((ViewGroup) MainActivity.getMyViewParent()).removeAllViews();
					theData.menuButtonViewLoad();
					HideOnePicture thisView = new HideOnePicture(MainActivity.getMyContext());
					((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
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
