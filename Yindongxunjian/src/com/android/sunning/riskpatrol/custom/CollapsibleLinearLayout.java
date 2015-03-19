package com.android.sunning.riskpatrol.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

public class CollapsibleLinearLayout extends LinearLayout {

    /**
     * 是否已经展开
     */
    private boolean expanded;
    /**
     * expand animation time
     */
    private int expandTime = 300;
    /**
     * collapse animation time
     */
    private int collapseTime = 300;
    /**
     * toggle image animation time
     */
    private int toggleTime = 300;


    private float fromStartDegrees = 0;
    private float toStartDegrees = -180;
    private float fromBackDegrees = -180;
    private float toBackDegrees = 0;
    private View toggleView;
    private int heightWithAnimation;

    public CollapsibleLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CollapsibleLinearLayout(Context context) {
        super(context);
    }

    public void toggle() {
        if (expanded) {
            collapse();
        } else {
            expand();
        }
    }

    public void expand() {
        if (heightWithAnimation == 0) {
            measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            heightWithAnimation = getMeasuredHeight();
        }
        final int targetHeight = heightWithAnimation;

        getLayoutParams().height = 0;
        this.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
//                Log.i("test", "interpolated time: " + interpolatedTime + ", targetHeight" + targetHeight);
                getLayoutParams().height = interpolatedTime == 1 ? LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        // 1dp/ms
        a.setDuration(expandTime);
        // a.setDuration((int) (targetHeight / getContext().getResources()
        // .getDisplayMetrics().density));
        startAnimation(a);
        expanded = true;
        if (toggleView != null) {
            rotateToggleView(toggleView, expanded);
        }
    }

    public void collapse() {
        final int initialHeight = getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                if (interpolatedTime == 1) {
                    setVisibility(View.GONE);
                } else {
                    getLayoutParams().height = initialHeight
                            - (int) (initialHeight * interpolatedTime);
                    requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(collapseTime);
        // a.setDuration((int) (initialHeight / getContext().getResources()
        // .getDisplayMetrics().density));
        startAnimation(a);
        expanded = false;
        if (toggleView != null) {
            rotateToggleView(toggleView, expanded);
        }
    }

    public void setToggleView(View toggleView) {
        this.toggleView = toggleView;
    }

    private void rotateToggleView(View view, boolean isExpand) {
        // view.clearAnimation();
        float fromDegrees = 0;
        float toDegrees = 0;
        if (isExpand) {
            // Log.i("test", "向上");0, -180
            fromDegrees = fromStartDegrees;
            toDegrees = toStartDegrees;
        } else {
            // Log.i("test", "向下");-180, 0
            fromDegrees = fromBackDegrees;
            toDegrees = toBackDegrees;
        }
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees,
                toDegrees, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(toggleTime);
        rotateAnimation.setFillAfter(true);
        view.startAnimation(rotateAnimation);
    }

    public void setDegrees(float fromStartDegrees, float toStartDegrees, float fromBackDegrees, float toBackDegrees) {
        this.fromStartDegrees = fromStartDegrees;
        this.toStartDegrees = toStartDegrees;
        this.fromBackDegrees = fromBackDegrees;
        this.toBackDegrees = toBackDegrees;
    }

    public void setAnimationTime(int expandTime, int collapseTime, int toggleTime) {
        this.expandTime = expandTime;
        this.collapseTime = collapseTime;
        this.toggleTime = toggleTime;
    }

    public void setHeightWithAnimation(int heightWithAnimation) {
        this.heightWithAnimation = heightWithAnimation;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}