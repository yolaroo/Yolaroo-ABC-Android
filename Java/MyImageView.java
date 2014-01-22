package com.yolaroo.yolarooabc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyImageView extends FrameLayout{

	public ImageButton myImageButton;
	public TextView myTextView;
	public String myName;
	
	final public String buildName = "com.yolaroo.yolarooabc:drawable/";
	
	public MyImageView(Context context) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.image_button, this, true);

		myImageButton = (ImageButton) findViewById(R.id.frameImage);
		myTextView = (TextView) findViewById(R.id.frameText);
	}

	// setter
	
	public void setMyTextAlpha (float myAlpha) {
		myTextView.setAlpha(myAlpha);
	}
	
	public void setMyName (String myString) {
		myName = myString;
		setMyImageButton(myString);
		setMytextView(myString);
	}
		
	public void setMyImageButton (String myStringOne) {
		int CORNER_RADIUS = 20;
		int resIdOne = getResources().getIdentifier(buildName + myStringOne,
				null, null);
		Bitmap myBitmapOne = BitmapFactory.decodeResource(getResources(),
				resIdOne);
		myImageButton.setImageBitmap(getRoundedCornerBitmap(
				myBitmapOne, CORNER_RADIUS));
	}

	public void setMytextView (String myStringOne) {
		myTextView.setText(myStringOne);
	}
	
	// getter
	
	public String getMyStringName () {
		return myName;
	}
	
	public ImageButton getMyImagebutton () {
		return myImageButton;
	}
	
	//round
	
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
	
}
