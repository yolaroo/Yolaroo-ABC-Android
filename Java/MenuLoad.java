package com.yolaroo.yolarooabc;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuLoad extends LinearLayout {

	Context myContext;
	MyData theData = new MyData();

	public MenuLoad(Context context) {
		super(context);
		myContext = context;
		Log.d("loaded", "menu");

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.menu_layout, this, true);

		MainActivity.setMyGameName("theMenu");
		menuActionLoad();

	}

	public void menuActionLoad() {

		Button myButton0 = (Button) findViewById(R.id.button0);
		myButton0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.removeAllViews();
				theData.menuButtonViewLoad();
				LetterIntroductionLoad thisView = new LetterIntroductionLoad(
						myContext);
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.addView(thisView);
			}
		});

		Button myButton0ii = (Button) findViewById(R.id.button0ii);
		myButton0ii.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.removeAllViews();
				theData.menuButtonViewLoad();
				LetterToPictureLoad thisView = new LetterToPictureLoad(
						myContext);
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.addView(thisView);
			}
		});

		Button myButton1 = (Button) findViewById(R.id.button1);
		myButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.removeAllViews();
				theData.menuButtonViewLoad();
				PictureToWord thisView = new PictureToWord(myContext);
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.addView(thisView);
			}
		});

		Button myButton2 = (Button) findViewById(R.id.button2);
		myButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.removeAllViews();
				theData.menuButtonViewLoad();
				FourPictureMatch thisView = new FourPictureMatch(myContext);
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.addView(thisView);
			}
		});

		Button myButton3 = (Button) findViewById(R.id.button3);
		myButton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.removeAllViews();
				theData.menuButtonViewLoad();
				WordSpell thisView = new WordSpell(myContext);
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.addView(thisView);
			}
		});

		Button myButton4 = (Button) findViewById(R.id.button4);
		myButton4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.removeAllViews();
				theData.menuButtonViewLoad();
				FourWordMatch thisView = new FourWordMatch(myContext);
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.addView(thisView);
			}
		});

		Button myButton4ii = (Button) findViewById(R.id.game4ii);
		myButton4ii.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.removeAllViews();
				theData.menuButtonViewLoad();
				HideOnePicture thisView = new HideOnePicture(myContext);
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.addView(thisView);
			}
		});

		Button myButton5 = (Button) findViewById(R.id.button5);
		myButton5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.removeAllViews();
				theData.menuButtonViewLoad();
				WordScramble thisView = new WordScramble(myContext);
				((ViewGroup) (ViewParent) MainActivity.getMyViewParent())
						.addView(thisView);
			}
		});
	}

}
