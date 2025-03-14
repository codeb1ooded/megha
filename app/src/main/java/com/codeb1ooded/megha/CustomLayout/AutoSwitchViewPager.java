package com.codeb1ooded.megha.CustomLayout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

/**
 * Created by megha on 5/8/16.
 */
public class AutoSwitchViewPager extends ViewPager {

    private Runnable mSwitcher = new Runnable() {

        /*
         * (non-Javadoc)
         * @see java.lang.Runnable#run()
         * @since Jun 13, 2013
         * @author rajeshcp
         */
        @Override
        public void run() {
            if( AutoSwitchViewPager.this.getAdapter() != null ) {
                int count = AutoSwitchViewPager.this.getCurrentItem();

                if( count ==  (AutoSwitchViewPager.this.getAdapter().getCount() - 1) ) {
                    count = 0;
                }else {
                    count++;
                }

                Log.d(this.getClass().getName(), "Current Page  " + count + "");
                try {
                    AutoSwitchViewPager.this.setCurrentItem(count, true);
                }
                catch (OutOfMemoryError e){
                    FirebaseCrash.report(new Exception("Out of memory handled: " + e.getMessage()));
                    Toast.makeText(getContext(), "Auto Switch can't work due to out of memory.", Toast.LENGTH_SHORT).show();
                }
            }
            AutoSwitchViewPager.this.postDelayed(this, 2000);
        }
    };

    /**
     * @param context
     * @return of type AutoSwitchViewPager
     * Constructor function
     * @since Jun 13, 2013 
     * @author rajeshcp
     */
    public AutoSwitchViewPager(Context context) {
        this(context, null);
    }

    /**
     * @param context
     * @param attrs
     * @return of type AutoSwitchViewPager
     * Constructor function
     * @since Jun 13, 2013 
     * @author rajeshcp
     */
    public AutoSwitchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        postDelayed(mSwitcher, 2000);
    }

    /*
     * (non-Javadoc)
     * @see android.support.v4.view.ViewPager#onTouchEvent(android.view.MotionEvent)
     * @since Jun 13, 2013
     * @author rajeshcp
     */
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        switch (arg0.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP :
                postDelayed(mSwitcher, 2000);
                break;

            default:
                removeCallbacks(mSwitcher);
                break;
        }
        return super.onTouchEvent(arg0);
    }

}