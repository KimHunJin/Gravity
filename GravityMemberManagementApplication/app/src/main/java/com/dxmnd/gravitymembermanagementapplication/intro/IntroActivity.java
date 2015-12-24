package com.dxmnd.gravitymembermanagementapplication.intro;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dxmnd.gravitymembermanagementapplication.R;
import com.dxmnd.gravitymembermanagementapplication.activity.MainActivity;
import com.dxmnd.gravitymembermanagementapplication.adapter.ViewPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

public class IntroActivity extends Activity {

    ViewPager mViewPager;
    PagerAdapter mAdapter;

    int[] mImage;
    Button btnStart;
    CirclePageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mImage = new int[]{
                R.drawable.gravity_intro_one,
                R.drawable.gravity_intro_two
        };

        btnStart = (Button) findViewById(R.id.btnStart);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new ViewPagerAdapter(IntroActivity.this, mImage);

        mViewPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);

        mIndicator.setPageColor(0x212121);
        mIndicator.setFillColor(0x880000FF);
        mIndicator.setStrokeColor(0xFF000000);
    }

    public void mClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart: {
                Intent iSkip = new Intent(getBaseContext(), MainActivity.class);
                startActivity(iSkip);
                break;
            }
        }
    }

    public void onPause() {
        super.onPause();
        finish();
    }
}
