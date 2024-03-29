package com.iitg.alcheringa.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.iitg.alcheringa.R;

@SuppressLint("NewApi")
public class ConcertFragment extends Fragment {
	private Animator mCurrentAnimator;
	private int mShortAnimationDuration;
	View rootView;
	int flag = 4;
	int[] myArray = {R.drawable.proshow2 ,R.drawable.palotay,R.drawable.proshow3,R.drawable.proshow0,
			R.drawable.ustad,R.drawable.blitz,R.drawable.lucky,R.drawable.pronight1};

	public ConcertFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_concert, container, false);
		final Button proshows = (Button)rootView.findViewById(R.id.imageButton1);
		final Button concerts = (Button)rootView.findViewById(R.id.imageButton2);
		
		String fontPath = "fonts/blendascript.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        proshows.setTypeface(tf);
        concerts.setTypeface(tf);

		proshows.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				flag = 0;
				concerts.setBackgroundColor(getResources().getColor(R.color.concertfocus));
				concerts.setTextColor(getResources().getColor(R.color.black));
				proshows.setBackground(getResources().getDrawable(R.drawable.concert_button_border));
				proshows.setTextColor(getResources().getColor(R.color.white));
				updateImages();
			}
		});

		concerts.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				flag = 4;
				concerts.setBackground(getResources().getDrawable(R.drawable.concert_button_border));
				concerts.setTextColor(getResources().getColor(R.color.white));
				proshows.setBackgroundColor(getResources().getColor(R.color.concertfocus));
				proshows.setTextColor(getResources().getColor(R.color.black));
				updateImages();
			}
		});

		concerts.setBackground(getResources().getDrawable(R.drawable.concert_button_border));
		concerts.setTextColor(getResources().getColor(R.color.white));
		proshows.setBackgroundColor(getResources().getColor(R.color.concertfocus));
		proshows.setTextColor(getResources().getColor(R.color.black));
		updateImages();

		// Retrieve and cache the system's default "short" animation time.
		mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);

		return rootView;
	}
	
	private void updateImages()
	{
		final View thumb1View = rootView.findViewById(R.id.thumb_button_1);
		thumb1View.setBackgroundResource(myArray[flag+0]);
		thumb1View.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				zoomImageFromThumb(thumb1View, myArray[flag+0]);
			}
		});

		final View thumb2View = rootView.findViewById(R.id.thumb_button_2);
		thumb2View.setBackgroundResource(myArray[flag+1]);
		thumb2View.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				zoomImageFromThumb(thumb2View, myArray[flag+1]);
			}
		});

		final View thumb3View = rootView.findViewById(R.id.thumb_button_3);
		thumb3View.setBackgroundResource(myArray[flag+2]);
		thumb3View.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				zoomImageFromThumb(thumb3View, myArray[flag+2]);
			}
		});

		final View thumb4View = rootView.findViewById(R.id.thumb_button_4);
		thumb4View.setBackgroundResource(myArray[flag+3]);
		thumb4View.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				zoomImageFromThumb(thumb4View, myArray[flag+3]);
			}
		});
	}

	private void zoomImageFromThumb(final View thumbView, int imageResId) {
		// If there's an animation in progress, cancel it immediately and proceed with this one.
		if (mCurrentAnimator != null) {
			mCurrentAnimator.cancel();
		}

		// Load the high-resolution "zoomed-in" image.
		final ImageView expandedImageView = (ImageView) rootView.findViewById(R.id.expanded_image);
		expandedImageView.setImageResource(imageResId);

		// Calculate the starting and ending bounds for the zoomed-in image. This step
		// involves lots of math. Yay, math.
		final Rect startBounds = new Rect();
		final Rect finalBounds = new Rect();
		final Point globalOffset = new Point();

		// The start bounds are the global visible rectangle of the thumbnail, and the
		// final bounds are the global visible rectangle of the container view. Also
		// set the container view's offset as the origin for the bounds, since that's
		// the origin for the positioning animation properties (X, Y).
		thumbView.getGlobalVisibleRect(startBounds);
		rootView.findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
		startBounds.offset(-globalOffset.x, -globalOffset.y);
		finalBounds.offset(-globalOffset.x, -globalOffset.y);

		// Adjust the start bounds to be the same aspect ratio as the final bounds using the
		// "center crop" technique. This prevents undesirable stretching during the animation.
		// Also calculate the start scaling factor (the end scaling factor is always 1.0).
		float startScale;
		if ((float) finalBounds.width() / finalBounds.height()
				> (float) startBounds.width() / startBounds.height()) {
			// Extend start bounds horizontally
			startScale = (float) startBounds.height() / finalBounds.height();
			float startWidth = startScale * finalBounds.width();
			float deltaWidth = (startWidth - startBounds.width()) / 2;
			startBounds.left -= deltaWidth;
			startBounds.right += deltaWidth;
		} else {
			// Extend start bounds vertically
			startScale = (float) startBounds.width() / finalBounds.width();
			float startHeight = startScale * finalBounds.height();
			float deltaHeight = (startHeight - startBounds.height()) / 2;
			startBounds.top -= deltaHeight;
			startBounds.bottom += deltaHeight;
		}

		// Hide the thumbnail and show the zoomed-in view. When the animation begins,
		// it will position the zoomed-in view in the place of the thumbnail.
		thumbView.setAlpha(0f);
		expandedImageView.setVisibility(View.VISIBLE);

		// Set the pivot point for SCALE_X and SCALE_Y transformations to the top-left corner of
		// the zoomed-in view (the default is the center of the view).
		expandedImageView.setPivotX(0f);
		expandedImageView.setPivotY(0f);

		// Construct and run the parallel animation of the four translation and scale properties
		// (X, Y, SCALE_X, and SCALE_Y).
		AnimatorSet set = new AnimatorSet();
		set
		.play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left,
				finalBounds.left))
				.with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top,
						finalBounds.top))
						.with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
						.with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));
		set.setDuration(mShortAnimationDuration);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				mCurrentAnimator = null;
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				mCurrentAnimator = null;
			}
		});
		set.start();
		mCurrentAnimator = set;

		// Upon clicking the zoomed-in image, it should zoom back down to the original bounds
		// and show the thumbnail instead of the expanded image.
		final float startScaleFinal = startScale;
		expandedImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mCurrentAnimator != null) {
					mCurrentAnimator.cancel();
				}

				// Animate the four positioning/sizing properties in parallel, back to their
				// original values.
				AnimatorSet set = new AnimatorSet();
				set
				.play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
				.with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
				.with(ObjectAnimator
						.ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
						.with(ObjectAnimator
								.ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));
				set.setDuration(mShortAnimationDuration);
				set.setInterpolator(new DecelerateInterpolator());
				set.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						thumbView.setAlpha(1f);
						expandedImageView.setVisibility(View.GONE);
						mCurrentAnimator = null;
					}

					@Override
					public void onAnimationCancel(Animator animation) {
						thumbView.setAlpha(1f);
						expandedImageView.setVisibility(View.GONE);
						mCurrentAnimator = null;
					}
				});
				set.start();
				mCurrentAnimator = set;
			}
		});
	}
}
