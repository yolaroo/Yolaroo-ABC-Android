package com.yolaroo.yolarooabc;

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

public class LetterToPictureSecondScene extends LinearLayout {

	public String theCorrectLetter;
	public int mySingleIndex;
	public int clickCount;
	public int gameModular = 4;

	public boolean clickOk;
	
	public Button myGlobalButton;

	MyData theData = new MyData();

	private GestureDetector mGestureDetector;
	@SuppressWarnings("unused")
	private OnSwipeListener mOnSwipeListener;

	public LetterToPictureSecondScene(Context context) {
		super(context);

		Log.d("loaded", "letter to picture scene two");

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.letter_match, this, true);

		mGestureDetector = new GestureDetector(context,
				new MySimpleOnGestureListener());

		MainActivity.setMyGameName("letterMatch");

		MainActivity.setMyContext(context);
		letterMatchLoadViewSecond();

	}

	public void letterMatchLoadViewSecond() {

		int CORNER_RADIUS = 20;

		mySingleIndex = MainActivity.getMySingleIndex();

		String[] myStringGroupSmall = theData.myDataLoad("smallLetter");
		int myAbcInt = (int) (Math.round(MainActivity.getMyIndex() / gameModular));
		theCorrectLetter = myStringGroupSmall[myAbcInt];
		clickCount = 0;
		clickOk = true;
		String[] myStringArray = theData.fourArraySetter(MainActivity.getMyIndex());

		Button myButtonOne = (Button) findViewById(R.id.topFrame);
		myGlobalButton = myButtonOne;
		myGlobalButton.setTextColor(0xFFDC143C);

		myButtonOne.setText(theCorrectLetter);
		myButtonOne.setTag(0);

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

		imageButtonSetterOne.setAlpha(0.4f);
		imageButtonSetterTwo.setAlpha(0.4f);
		imageButtonSetterThree.setAlpha(0.4f);
		imageButtonSetterFour.setAlpha(0.4f);

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

		letterMatchClickAction();
	}

	public void letterMatchClickAction() {
		Button myButtonOne = (Button) findViewById(R.id.topFrame);
		myButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Button myButtonOne = (Button) findViewById(R.id.topFrame);
				myGlobalButton.setTextColor(0xFFaaaaaa);
				myGlobalButton.setAlpha(0.4f);
				myAudioPlayer(theCorrectLetter);

				if (myButtonOne.getTag().equals(0) && clickOk) {
					clickOk = false;
					Log.d("set", "one");

					myButtonOne.setTag(1);
					letterMatchClickCheck(1);
				} else if (myButtonOne.getTag().equals(1) && clickOk) {
					clickOk = false;
					Log.d("set", "two");

					myButtonOne.setTag(2);
					letterMatchClickCheck(2);
				} else if (myButtonOne.getTag().equals(2) && clickOk) {
					clickOk = false;
					Log.d("set", "three");

					myButtonOne.setTag(3);
					letterMatchClickCheck(3);
				} else if (myButtonOne.getTag().equals(3) && clickOk) {
					clickOk = false;
					Log.d("set", "four");

					myButtonOne.setTag(4);
					letterMatchClickCheck(4);
				}
			}
		});

		ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOneFPM);
		imageButtonSetterOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (clickCount == 1) {
					ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOneFPM);
					imageButtonSetterOne.setAlpha(0.4f);
					TextView textViewTitleOne = (TextView) findViewById(R.id.textOneFPM);
					textViewTitleOne.setAlpha(0.4f);
					String clickAnswer = textViewTitleOne.getText().toString();
					myAudioPlayer(clickAnswer);
					myGlobalButton.setTextColor(0xFFDC143C);
					myGlobalButton.setAlpha(1.0f);
					clickOk = true;
				}
			}
		});

		ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwoFPM);
		imageButtonSetterTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (clickCount == 2) {
					ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwoFPM);
					imageButtonSetterTwo.setAlpha(0.4f);
					TextView textViewTitleOne = (TextView) findViewById(R.id.textTwoFPM);
					textViewTitleOne.setAlpha(0.4f);
					String clickAnswer = textViewTitleOne.getText().toString();
					myAudioPlayer(clickAnswer);
					myGlobalButton.setTextColor(0xFFDC143C);
					myGlobalButton.setAlpha(1.0f);
					clickOk = true;
				}
			}
		});

		ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThreeFPM);
		imageButtonSetterThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (clickCount == 3) {
					ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThreeFPM);
					imageButtonSetterThree.setAlpha(0.4f);
					TextView textViewTitleOne = (TextView) findViewById(R.id.textThreeFPM);
					textViewTitleOne.setAlpha(0.4f);
					String clickAnswer = textViewTitleOne.getText().toString();
					myAudioPlayer(clickAnswer);
					myGlobalButton.setTextColor(0xFFDC143C);
					myGlobalButton.setAlpha(1.0f);
					clickOk = true;
				}
			}
		});

		ImageButton imageButtonSetterFour = (ImageButton) findViewById(R.id.imageFourFPM);
		imageButtonSetterFour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (clickCount == 4) {
					ImageButton imageButtonSetterFour = (ImageButton) findViewById(R.id.imageFourFPM);
					imageButtonSetterFour.setAlpha(0.4f);
					TextView textViewTitleOne = (TextView) findViewById(R.id.textFourFPM);
					textViewTitleOne.setAlpha(0.4f);
					String clickAnswer = textViewTitleOne.getText().toString();
					myAudioPlayer(clickAnswer);
					myGlobalButton.setTextColor(0xFFDC143C);
					myGlobalButton.setAlpha(1.0f);
					letterMatchReset();
					clickOk = true;
				}
			}
		});
	}

	public void letterMatchClickCheck(int myInt) {
		Log.d("letter top", "check");

		switch (myInt) {
		case 1:
			ImageButton imageButtonSetterOne = (ImageButton) findViewById(R.id.imageOneFPM);
			imageButtonSetterOne.setAlpha(1.0f);
			clickCount = 1;
			break;
		case 2:
			ImageButton imageButtonSetterTwo = (ImageButton) findViewById(R.id.imageTwoFPM);
			imageButtonSetterTwo.setAlpha(1.0f);
			clickCount = 2;
			break;
		case 3:
			ImageButton imageButtonSetterThree = (ImageButton) findViewById(R.id.imageThreeFPM);
			imageButtonSetterThree.setAlpha(1.0f);
			clickCount = 3;
			break;
		case 4:
			ImageButton imageButtonSetterFour = (ImageButton) findViewById(R.id.imageFourFPM);
			imageButtonSetterFour.setAlpha(1.0f);
			clickCount = 4;
			break;
		}
	}

	public void letterMatchReset() {
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Log.d("reloaded,", "the matrix");
				((ViewGroup) MainActivity.getMyViewParent()).removeAllViews();
				theData.menuButtonViewLoad();
				LetterToPictureLoad thisView = new LetterToPictureLoad(
						MainActivity.getMyContext());
				((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
			}
		}, 300);
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
