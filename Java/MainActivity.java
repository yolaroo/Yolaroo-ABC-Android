package com.yolaroo.yolarooabc;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class MainActivity extends Activity {

	public static int myIndex;
	public static int mySingleIndex;
	public static View myParentView;
	public static String myGameName;
	public static Context myContext;

	public static String myDataName;
	public static boolean secondMode;
	public static int clickCount;

	public static String theCorrectWord;
	
	public static int fingerCount;
	int myBackgroundInt;
	public static boolean screenSwitch;

	public static int theWidth;
	public static int theHeight;
	public static int theDensity;
	public static boolean isLarge;
	public static boolean isXLarge;
	
	private static final int SWIPE_MIN_DISTANCE = 200;
	final public static String buildName = "com.yolaroo.yolarooabc:drawable/";
	final public static String audioBuildName = "com.yolaroo.yolarooabc:raw/";

	public GestureDetector gestures;

	private static DrawerLayout mDrawerLayout;
	private static RelativeLayout mDrawerList;
	public static boolean myAudioOff;
	public static MediaPlayer mp = new MediaPlayer();

	public static boolean fingerTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LinearLayout myMainLayout = (LinearLayout) findViewById(R.id.mainLayout);
		myParentView = myMainLayout;
		MenuLoad menu = new MenuLoad(this);
		((ViewGroup) myMainLayout).addView(menu);
		myContext = this;
		myGameName = "theMenu";
		myDataName = "basicSet";
		screenSwitch = true;
		gestures = new GestureDetector(MainActivity.this, new GestureListener(
				this));

		setMySwitches();
		setMyDrawer();
		backgroundMyDraw();
		myDisplay();
	}

	public void setMySwitches() {
		Switch mySwitchAudio = (Switch) findViewById(R.id.switch1);
		mySwitchAudio
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							Log.d("switch", "on");
							myAudioOff = false;
						} else {
							Log.d("switch", "off");
							myAudioOff = true;
						}
					}
				});

		Switch mySwitchData = (Switch) findViewById(R.id.switch2);
		mySwitchData
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							myDataName = "basicSet";
							MyData theData = new MyData();
							theData.reLoad();
						} else {
							myDataName = "extraSet";
							MyData theData = new MyData();
							theData.reLoad();
						}
					}
				});

		Switch mySwitchBackground = (Switch) findViewById(R.id.switch3);
		mySwitchBackground
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							screenSwitch = true;
							backgroundMyDraw();
						} else {
							screenSwitch = false;
						}
					}
				});
	}

	public void setMyDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (RelativeLayout) findViewById(R.id.myleftlayout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

		mDrawerLayout.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				int action = event.getAction();
				switch (action & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_POINTER_DOWN:
					setFingerCount(event.getPointerCount());
					Log.d("Main Fingers Set", Integer.toString(fingerCount));
					break;
				}
				fingerCount = event.getPointerCount();

				if (fingerCount > 1) {
					fingerTest = true;
				} else if (fingerCount == 1) {
					final Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							fingerTest = false;
						}
					}, 300);
				}
				try {
					return gestures.onTouchEvent(event);
				} catch (Exception e) {
					Log.d("touch one", "error");
					return false;
				}
			}
		});
		Button myCloseButton = (Button) findViewById(R.id.backbutton);
		myCloseButton.setOnClickListener(closeClick(myCloseButton));

		Button myNextButton = (Button) findViewById(R.id.nextbutton);
		myNextButton.setOnClickListener(nextClick(myNextButton));
	}

	View.OnClickListener closeClick(final Button Mybutton) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				// mDrawerLayout.closeDrawer(mDrawerList);
				if (myGameName.equals("theMenu")) {
				} else {
					menuViewLoad();
				}
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						mDrawerLayout.closeDrawer(mDrawerList);
					}
				}, 300);
			}
		};
	}

	View.OnClickListener nextClick(final Button Mybutton) {
		return new View.OnClickListener() {
			public void onClick(View v) {

				String[] gameArray = new String[] {
						"letterIntroduction", "letterMatch","pictureToWord",
						"FourPictureMatch", "wordSpell", "fourWordMatch",
						"hideOnePicture","wordScramble" };

				int myGameNumber = java.util.Arrays.asList(gameArray).indexOf(myGameName);
				int myArrayLength = gameArray.length;
				int myNewGameNumber = myGameNumber + 1;
				if (myNewGameNumber > myArrayLength) {
					myNewGameNumber = myGameNumber;
				}
				else {
				}
				try {
					myGameName = gameArray[myNewGameNumber];
				}
				catch (Exception e){
					Log.d("nextGameError","main");
				}
				MyData theData = new MyData();
				theData.swipeLoad();
			}
		};
	}

	public void menuViewLoad() {
		try {
			((ViewGroup) myParentView).removeAllViews();
			MenuLoad menu = new MenuLoad(myContext);
			((ViewGroup) myParentView).addView(menu);
		} catch (Exception e) {
			Log.d("menu action", "error");
		}
	}

	public static void myMenuAction() {
		mDrawerLayout.openDrawer(mDrawerList);
	}

	public void myDisplay() {

		// display info
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		
		// note
		// 1280 width
		
		// nexus 5
		// h 1080 - [360]
		// w 1794 - [594]
		// d 3
		
		// 7'
		// h 736 - [553]
		// w 1280 - [962]
		// d 1.33
		
		float dpDensity = getResources().getDisplayMetrics().density;
		float dpHeight = outMetrics.heightPixels;
		float dpWidth = outMetrics.widthPixels;

		Log.d("the height - ", Float.toString(dpHeight));
		Log.d("the width - ", Float.toString(dpWidth));
		Log.d("density - ", Float.toString(dpDensity));

		theHeight = (int) (Math.round(dpHeight));
		theWidth = (int) (Math.round(dpWidth));
		theDensity = (int) (Math.round(dpDensity));
	
		Log.d("Screen Size --- ", getMySizeName());
		
	}

	public String getMySizeName() {
	    int screenLayout = this.getResources().getConfiguration().screenLayout;
	    screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

	    switch (screenLayout) {
	    case Configuration.SCREENLAYOUT_SIZE_SMALL:
	        return "small";
	    case Configuration.SCREENLAYOUT_SIZE_NORMAL:
	        return "normal";
	    case Configuration.SCREENLAYOUT_SIZE_LARGE:
	        isLarge = true;
	        return "large";
	    case Configuration.SCREENLAYOUT_SIZE_XLARGE:
	        isXLarge = true;
	    	return "xlarge";
	    default:
	        return "undefined";
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void backgroundMyDraw() {
		if (screenSwitch) {
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					try {
						LinearLayout myMainLayout = (LinearLayout) findViewById(R.id.mainLayout);
						String[] myStringArray = { "mybga", "mybgb", "mybgc",
								"mybgd", "mybge", "mybgf" };
						myBackgroundInt++;
						if (myBackgroundInt > myStringArray.length) {
							myBackgroundInt = 0;
						}
						int resIdOne = getResources().getIdentifier(
								buildName + myStringArray[myBackgroundInt],
								null, null);
						myMainLayout.setBackgroundResource(resIdOne);
					} catch (Exception e) {
					}
					backgroundMyDraw();
				}
			}, 80000);
		}
	}

	//
	// Setter
	//

	public static void setTheCorrectWord (String myString) {
		theCorrectWord = myString;
	}
	
	public static void setMyData(String thisDataName) {
		myDataName = thisDataName;
	}

	public static void setFingerTest(boolean thisFingerTest) {
		fingerTest = thisFingerTest;
	}

	public static void setFingerCount(int thisIsFingerCount) {
		fingerCount = thisIsFingerCount;
	}

	public static void setMyContext(Context thisIsMyContext) {
		myContext = thisIsMyContext;
	}

	public static void setMyIndex(int thisIsMyIndex) {
		myIndex = thisIsMyIndex;
	}

	public static void setMySingleIndex(int thisIsMySingleIndex) {
		mySingleIndex = thisIsMySingleIndex;
	}

	public static void setMyGameName(String thisIsMyGameName) {
		myGameName = thisIsMyGameName;
	}

	public void setMyParentView(View thisIsMyParentView) {
		myParentView = thisIsMyParentView;
	}

	public static void setSecondMode(Boolean thisIsMySecondMode) {
		secondMode = thisIsMySecondMode;
	}

	public static void setClickCount(int thisIsMyClickCount) {
		clickCount = thisIsMyClickCount;
	}

	//
	// Getter
	//

	public static int getSWIPE_MIN_DISTANCE() {
		return SWIPE_MIN_DISTANCE;
	}
	
	public static String getAudioBuildName () {
		return audioBuildName;
	}
	
	public static String getTheCorrectWord () {
		return theCorrectWord;
	}
	
	public static String getBuildName() {
		return buildName;
	}
	
	public static String getMyDataName() {
		return myDataName;
	}

	public static boolean getFingerTest() {
		return fingerTest;
	}

	public static int getTheDensity () {
		return theDensity;
	}
	
	public static int getTheHeight() {
		return theHeight;
	}

	public static int getTheWidth() {
		return theWidth;
	}

	public static boolean getIsLarge () {
		return isLarge;
	}
	
	public static boolean getisXLarge () {
		return isXLarge;
	}
	
	public static int getFingerCount() {
		return fingerCount;
	}

	public static Context getMyContext() {
		return myContext;
	}

	public static int getclickCount() {
		return clickCount;
	}

	public static Boolean getSecondMode() {
		return secondMode;
	}

	public static String getMyGameName() {
		return myGameName;
	}

	public static View getMyViewParent() {
		return myParentView;
	}

	public static int getMyIndex() {
		return myIndex;
	}

	public static int getMySingleIndex() {
		return mySingleIndex;
	}

	//
	// back button
	//

	@Override
	public void onBackPressed() {
		MyData theData = new MyData();
		theData.myBackPress((ViewParent) myParentView, myContext);
	}

	//
	// Gesture
	//

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("wrong touch", "main activity");
		return false;
	}

	private class GestureListener implements GestureDetector.OnGestureListener,
			GestureDetector.OnDoubleTapListener {

		public GestureListener(MainActivity mainActivity) {
		}

		@Override
		public boolean onDown(MotionEvent e) {
			// Log.d("Main", "On Down");
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2,
				final float velocityX, final float velocityY) {
			if (myGameName.equals("theMenu")) {
				try {
					LinearLayout myMainLayout = (LinearLayout) findViewById(R.id.mainLayout);
					String[] myStringArray = { "mybga", "mybgb", "mybgc",
							"mybgd", "mybge", "mybgf" };
					myBackgroundInt++;
					if (myBackgroundInt > myStringArray.length) {
						myBackgroundInt = 0;
					}
					int resIdOne = getResources().getIdentifier(
							buildName + myStringArray[myBackgroundInt], null,
							null);
					myMainLayout.setBackgroundResource(resIdOne);
				} catch (Exception e) {
				}
			} else {
				if ((Math.abs(e1.getX() - e2.getX()) > SWIPE_MIN_DISTANCE)
						|| (Math.abs(e1.getY() - e2.getY()) > SWIPE_MIN_DISTANCE)) {
					if (e1.getX() > e2.getX()) { // right to left
						MyData theData = new MyData();
						theData.mySwipeRight();
					} else {
						MyData theData = new MyData();
						theData.mySwipeLeft();
					}
				} else {
					Log.d("swipe", "not long enough");
				}
			}
			return true;
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {

		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {

		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// Log.d("single in gesture", "main");
			return false;
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// Log.d("main tapconfirm", "main");
			return false;
		}
	}

	//
	// audio
	//

	public static void myMainAudioPlayer(int resIdOne) {
		if (myAudioOff) {
			// empty
		} else {
			try {
				mp = MediaPlayer.create(myContext, resIdOne);
				mp.start();
				mp.setOnCompletionListener(new OnCompletionListener() {
					public void onCompletion(MediaPlayer mp) {
						mp.reset();
						mp.release();
					}
				});
			} catch (Exception e) {
				Log.d("audio", "error");
				e.printStackTrace();
			}
		}
	}
}
