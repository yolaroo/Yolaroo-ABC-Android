package com.yolaroo.yolarooabc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyData extends MainActivity {
	
	//
	// Data Manipulation
	//
	
	public String[] fourArraySetter(int myOldIndex) {
		
		String[] myStringArray = myDataLoad(MainActivity.getMyDataName());
		int myIndex = indexCheckFour(myOldIndex);

		String myStringOne = myStringArray[myIndex];
		String myStringTwo = myStringArray[myIndex + 1];
		String myStringThree = myStringArray[myIndex + 2];
		String myStringFour = myStringArray[myIndex + 3];

		String[] myFourStringArray = new String[] { myStringOne, myStringTwo,
				myStringThree, myStringFour };

		return myFourStringArray;
	}
	
	public int indexCheckFour(int myIndex) {
		String[] myStringArray = myDataLoad(MainActivity.getMyDataName());
		int myLength = myStringArray.length;

		//Log.d("length", Integer.toString(myLength));
		//Log.d("index", Integer.toString(myIndex));

		if (myIndex < 0) {
			Log.d("less", "than zero");
			myIndex = myLength - 4;
			MainActivity.setMyIndex(myIndex);
		} else if (myIndex + 4 > myLength) {
			myIndex = 0;
			MainActivity.setMyIndex(myIndex);
		}
		return myIndex;
	}
	
	public String[] myDataLoad(String myString) {
		if (myString == "extraSet") {
			String[] myDataArray01 = new String[] { "ant", "ax", "ankle",
					"apple", "bear", "ball", "banana", "bed", "cat", "cut",
					"cake", "car", "dog", "dig", "doll", "door", "elephant",
					"egg", "eggcup", "empty", "fish", "fin", "fan", "four",
					"goose", "gate", "girl", "goat", "hen", "hat", "hair",
					"hand", "insect", "ill", "in", "ink", "jellyfish", "jump",
					"jam", "juice", "kangaroo", "kick", "key", "kite", "lion",
					"leg", "leaf", "lemon", "monkey", "mouth", "mango", "milk",
					"nannygoat", "nose", "neck", "nest", "octopus", "on",
					"ostrich", "ox", "panda", "pen", "pear", "pig", "quail",
					"quiet", "queen", "quilt", "rabbit", "read", "rain",
					"rose", "seal", "sun", "sand", "sea", "tiger", "teeth",
					"tea", "tie", "uncle", "under", "umbrella", "up", "viper",
					"victory", "van", "vase", "worm", "wet", "water", "window",
					"fox", "box", "six", "wax", "yak", "yes", "yogurt", "yoyo",
					"zebra", "zipper", "zigzag", "zoo" };
			return myDataArray01;
		} else if (myString == "basicSet") {
			String[] myDataArray02 = new String[] { "apple", "ant", "acorn",
					"alligator", "ball", "banana", "bat", "bear", "cake",
					"cat", "corn", "cow", "duck", "dog", "door", "dinosaur",
					"earth", "egg", "elephant", "eraser", "fish", "fly", "fox",
					"frog", "giraffe", "girl", "goat", "goose", "hand", "hen",
					"hippo", "house", "ice", "igloo", "ink", "insect", "jam",
					"jet", "jewel", "juice", "kangaroo", "key", "kite", "kiwi",
					"leaf", "leg", "lemon", "lion", "mango", "milk", "monkey",
					"music", "nail", "net", "nose", "nurse", "octopus",
					"orange", "ostrich", "owl", "pig", "pear", "pencil",
					"panda", "queen", "quail", "qiling", "quilt", "rabbit",
					"rain", "rainbow", "robot", "sea", "star", "seal", "sun",
					"tea", "tiger", "tomato", "train", "ufo", "umbrella",
					"unicorn", "up", "vampire", "van", "vase", "violin",
					"water", "window", "wolf", "worm", "xenops", "xiphias",
					"xray", "xylophone", "yacht", "yak", "yogurt", "yoyo",
					"zebra", "zipper", "zombie", "zoo" };
			return myDataArray02;
		} else if (myString == "largeLetter") {
			String[] myLargeLetterDataArray = new String[] { "A", "B", "C",
					"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
					"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
			return myLargeLetterDataArray;
		} else if (myString == "smallLetter") {
			String[] mySmallLetterDataArray = new String[] { "a", "b", "c",
					"d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
					"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
			return mySmallLetterDataArray;
		} else {
			Log.d("error","database load");
			return null;
		}
	}
	
	//
	//Round corners 
	//
	
	public  Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(4, 0, bitmap.getWidth() - 4,
				bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
	
	//
	// menu button
	//

	public void menuButtonViewLoad() {

		int buttonSize;
		int fontSize;
		if(MainActivity.getIsLarge()) {
			buttonSize = 60;
			fontSize = 20;
		}
		else if (MainActivity.getisXLarge()) {
			buttonSize = 60;
			fontSize = 30;
		}
		else {
			buttonSize = 80;
			fontSize = 10;
		}
		
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				buttonSize, buttonSize);

		layoutParams.setMargins(0, 0, 0, 0);
		Button menuButton = new Button(MainActivity.getMyContext());
		menuButton.setText("=");
		menuButton.setTextSize(fontSize);
		menuButton.setTextColor(0xFFffffff);
		menuButton.setBackgroundColor(0x80000000);
		menuButton.setGravity(Gravity.CENTER_VERTICAL
				| Gravity.CENTER_HORIZONTAL);

		((ViewGroup) MainActivity.getMyViewParent()).addView(menuButton, layoutParams);

		menuButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//menuViewLoad();
				MainActivity.myMenuAction();
			}
		});
	}

	public void menuViewLoad() {
		try {
			((ViewGroup) MainActivity.getMyViewParent()).removeAllViews();
			MenuLoad menu = new MenuLoad(MainActivity.getMyContext());
			((ViewGroup) MainActivity.getMyViewParent()).addView(menu);
		} catch (Exception e) {
			Log.d("menu action","error");
		}
	}
	
	//
	// Nav
	//
	
	public void mySwipeRight() {
		mySingleIndex = 0;
		MainActivity.setMySingleIndex(mySingleIndex);
		NextIndex();
		swipeLoad();
	}

	public void mySwipeLeft() {
		mySingleIndex = 0;
		MainActivity.setMySingleIndex(mySingleIndex);
		BackIndex();
		swipeLoad();
	}

	public void reLoad () {
		swipeLoad();
	}
	
	public void swipeLoad() {
		String gameName = MainActivity.getMyGameName();
		((ViewGroup) MainActivity.getMyViewParent()).removeAllViews();
		menuButtonViewLoad();
				
		if (gameName.equals("pictureToWord")) {
			PictureToWord thisView = new PictureToWord( MainActivity.getMyContext());
			((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
		}else if (gameName.equals("letterIntroduction")) {
			LetterIntroductionLoad thisView = new LetterIntroductionLoad( MainActivity.getMyContext());
			((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
		}else if (gameName.equals("letterMatch")) {
			LetterToPictureLoad thisView = new LetterToPictureLoad( MainActivity.getMyContext());
			((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
		}else if (gameName.equals("FourPictureMatch")) {
			FourPictureMatch thisView = new FourPictureMatch( MainActivity.getMyContext());
			((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
		} else if (gameName.equals("wordSpell")) {
			WordSpell thisView = new WordSpell( MainActivity.getMyContext());
			((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
		} else if (gameName.equals("fourWordMatch")) {
			FourWordMatch thisView = new FourWordMatch( MainActivity.getMyContext());
			((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
		} else if (gameName.equals("wordScramble")) {
			WordScramble thisView = new WordScramble( MainActivity.getMyContext());
			((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
		} else if (gameName.equals("hideOnePicture")) {
			HideOnePicture thisView = new HideOnePicture( MainActivity.getMyContext());
			((ViewGroup) MainActivity.getMyViewParent()).addView(thisView);
		}	
	}

	public void myFingerCounter (MotionEvent event) {
	    int fingerCount = event.getPointerCount();
	    if (fingerCount > 1) {
	    	setFingerTest(true);
	    }
	    else if (fingerCount == 1) {
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					setFingerTest(false);	
				}
			}, 300);
	    }
	}
	
	public void NextIndex() {
		MainActivity.setSecondMode(false);
		MainActivity.setMySingleIndex(0);
		MainActivity.setClickCount(0);		
		myIndex = MainActivity.getMyIndex();
		boolean myFingerTest = MainActivity.getFingerTest();
		
		if (myFingerTest) {
			myIndex = myIndex + 8;
		}
		else {
			myIndex = myIndex + 4;
		}
	
		indexCheckFour(myIndex);
		MainActivity.setMyIndex(myIndex);
	}

	public void BackIndex() {
		MainActivity.setSecondMode(false);
		MainActivity.setMySingleIndex(0);
		MainActivity.setClickCount(0);
		myIndex = MainActivity.getMyIndex();
		
		boolean myFingerTest = MainActivity.getFingerTest();

		if (myFingerTest) {
			myIndex = myIndex - 8;
		}
		else {
			myIndex = myIndex - 4;
		}
		
		indexCheckFour(myIndex);
		MainActivity.setMyIndex(myIndex);
	}

	public void myBackPress (ViewParent aView,Context aContext) {	
		String myGameName = getMyGameName(); 
		if (myGameName.equals("theMenu")) {
			Log.d("back button","the menu");
		}
		else {
			Log.d("reload","from back button");
			menuViewLoad();
		}
	}
	
}
