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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FourPictureMatch extends RelativeLayout {

	public Button myGlobalButton;

	MyData theData = new MyData();
	
	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public FourPictureMatch(Context context) {
		super(context);

		Log.d("FourPictureMatch","Loaded");
		
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.four_picture_match, this, true);

		MainActivity.setMyContext(context);

		MainActivity.setMyGameName("FourPictureMatch");

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());
		
		fourPictureMatchViewLoad();
		fourPictureMatchClickAction();
	}

	public void fourPictureMatchViewLoad() {

		int CORNER_RADIUS = 20;

		String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());

		List<Object> list = new ArrayList<Object>();
		Collections.shuffle(list);
		Collections.shuffle(Arrays.asList(myStringArray));

		Random myRandom = new Random();
		int theRandom = myRandom.nextInt(3);
		MainActivity.setTheCorrectWord(myStringArray[theRandom]);

		Button myButtonOne = (Button) findViewById(R.id.topFrame);
		myButtonOne.setText(MainActivity.getTheCorrectWord());
		myButtonOne.setBackgroundResource(R.layout.square_button);

		String myStringOne = myStringArray[0];
		String myStringTwo = myStringArray[1];
		String myStringThree = myStringArray[2];
		String myStringFour = myStringArray[3];

		TextView textViewTitleOne = (TextView) findViewById(R.id.textOneFPM);
		TextView textViewTitleTwo = (TextView) findViewById(R.id.textTwoFPM);
		TextView textViewTitleThree = (TextView) findViewById(R.id.textThreeFPM);
		TextView textViewTitleFour = (TextView) findViewById(R.id.textFourFPM);

		textViewTitleOne.setAlpha(0f);
		textViewTitleTwo.setAlpha(0f);
		textViewTitleThree.setAlpha(0f);
		textViewTitleFour.setAlpha(0f);

		ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOneFPM);
		ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwoFPM);
		ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThreeFPM);
		ImageButton imageButtonSetterFour = (ImageButton) findViewById(R.id.imageFourFPM);

		imageButtonSetterOne.setAlpha(1f);
		imageButtonSetterTwo.setAlpha(1f);
		imageButtonSetterThree.setAlpha(1f);
		imageButtonSetterFour.setAlpha(1f);

		textViewTitleOne.setText(myStringOne);
		textViewTitleTwo.setText(myStringTwo);
		textViewTitleThree.setText(myStringThree);
		textViewTitleFour.setText(myStringFour);

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

	public void fourPictureMatchClickAction() {

		Button myButtonOne = (Button) findViewById(R.id.topFrame);
		myGlobalButton = myButtonOne;
		myButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myGlobalButton.setBackgroundColor(0x80FF0000);
				myAudioPlayer(MainActivity.getTheCorrectWord());
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						myGlobalButton.setBackgroundResource(R.layout.square_button);
					}
				}, 300);
			}
		});

		ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOneFPM);
		imageButtonSetterOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textViewTitleOne = (TextView) findViewById(R.id.textOneFPM);
				String clickAnswer = textViewTitleOne.getText().toString();
				myAudioPlayer(clickAnswer);
				clickCheckFPM(clickAnswer, 1);
			}
		});

		ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwoFPM);
		imageButtonSetterTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textViewTitleOne = (TextView) findViewById(R.id.textTwoFPM);
				String clickAnswer = textViewTitleOne.getText().toString();
				myAudioPlayer(clickAnswer);
				clickCheckFPM(clickAnswer, 2);
			}
		});

		ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThreeFPM);
		imageButtonSetterThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textViewTitleOne = (TextView) findViewById(R.id.textThreeFPM);
				String clickAnswer = textViewTitleOne.getText().toString();
				myAudioPlayer(clickAnswer);
				clickCheckFPM(clickAnswer, 3);
			}
		});

		ImageButton imageButtonSetterFour = (ImageButton) findViewById(R.id.imageFourFPM);
		imageButtonSetterFour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView textViewTitleOne = (TextView) findViewById(R.id.textFourFPM);
				String clickAnswer = textViewTitleOne.getText().toString();
				myAudioPlayer(clickAnswer);
				clickCheckFPM(clickAnswer, 4);
			}
		});
	}

	public void clickCheckFPM(String myString, int myInt) {
		switch (myInt) {
		case 1:
			TextView textViewTitleOne = (TextView) findViewById(R.id.textOneFPM);
			textViewTitleOne.setAlpha(1f);
			reloadFPM(myString, myInt);
			break;
		case 2:
			TextView textViewTitleTwo = (TextView) findViewById(R.id.textTwoFPM);
			textViewTitleTwo.setAlpha(1f);
			reloadFPM(myString, myInt);
			break;
		case 3:
			TextView textViewTitleThree = (TextView) findViewById(R.id.textThreeFPM);
			textViewTitleThree.setAlpha(1f);
			reloadFPM(myString, myInt);
			break;
		case 4:
			TextView textViewTitleFour = (TextView) findViewById(R.id.textFourFPM);
			textViewTitleFour.setAlpha(1f);
			reloadFPM(myString, myInt);
			break;
		}
	}

	public void reloadFPM(String myString, int myInt) {
		if (myString.equals(MainActivity.getTheCorrectWord())) {

			ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOneFPM);
			ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwoFPM);
			ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThreeFPM);
			ImageButton imageButtonSetterFour = (ImageButton) findViewById(R.id.imageFourFPM);

			TextView textViewTitleOne = (TextView) findViewById(R.id.textOneFPM);
			TextView textViewTitleTwo = (TextView) findViewById(R.id.textTwoFPM);
			TextView textViewTitleThree = (TextView) findViewById(R.id.textThreeFPM);
			TextView textViewTitleFour = (TextView) findViewById(R.id.textFourFPM);

			switch (myInt) {
			case 1:
				imageButtonSetterTwo.setAlpha(0.4f);
				imageButtonSetterThree.setAlpha(0.4f);
				imageButtonSetterFour.setAlpha(0.4f);
				textViewTitleTwo.setAlpha(0.4f);
				textViewTitleThree.setAlpha(0.4f);
				textViewTitleFour.setAlpha(0.4f);
				break;
			case 2:
				imageButtonSetterOne.setAlpha(0.4f);
				imageButtonSetterThree.setAlpha(0.4f);
				imageButtonSetterFour.setAlpha(0.4f);
				textViewTitleOne.setAlpha(0.4f);
				textViewTitleThree.setAlpha(0.4f);
				textViewTitleFour.setAlpha(0.4f);
				break;
			case 3:
				imageButtonSetterOne.setAlpha(0.4f);
				imageButtonSetterTwo.setAlpha(0.4f);
				imageButtonSetterFour.setAlpha(0.4f);
				textViewTitleOne.setAlpha(0.4f);
				textViewTitleTwo.setAlpha(0.4f);
				textViewTitleFour.setAlpha(0.4f);
				break;
			case 4:
				imageButtonSetterOne.setAlpha(0.4f);
				imageButtonSetterTwo.setAlpha(0.4f);
				imageButtonSetterThree.setAlpha(0.4f);
				textViewTitleOne.setAlpha(0.4f);
				textViewTitleTwo.setAlpha(0.4f);
				textViewTitleThree.setAlpha(0.4f);
				break;
			}
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					fourPictureMatchViewLoad();
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
			
			if ((Math.abs(e1.getX() - e2.getX()) > MainActivity.getSWIPE_MIN_DISTANCE()) || (Math.abs(e1.getY() - e2.getY()) > MainActivity.getSWIPE_MIN_DISTANCE())) {
				if (e1.getX() > e2.getX()) { // right to left
					Log.d("Right", "Second Fling");
					theData.mySwipeRight();
				} else {
					Log.d("Left", "Second fling");
					theData.mySwipeLeft();
				}
			}
			else {
				Log.d("swipe","not long enough");
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
		//theData.myFingerCounter(ev);
		return mGestureDetector.onTouchEvent(ev);
	}
	
}