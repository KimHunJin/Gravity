package com.dxmnd.gravitymembermanagementapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dxmnd.gravitymembermanagementapplication.R;

public class MainActivity extends AppCompatActivity {

    LinearLayout[] mLinearLayouts;
    EditText edtToolSearch;
    ImageView imgToolSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinearLayouts = new LinearLayout[]{
                (LinearLayout) findViewById(R.id.linearMemberList),
                (LinearLayout) findViewById(R.id.linearMemberAdd),
                (LinearLayout) findViewById(R.id.linearMemberMod),
                (LinearLayout) findViewById(R.id.linearMemberDelete)
        };

        edtToolSearch = (EditText)findViewById(R.id.edt_tool_search);
        imgToolSearch = (ImageView)findViewById(R.id.img_tool_search);
        imgToolSearch.setVisibility(View.INVISIBLE);
        edtToolSearch.setText("GRAVITY");
        edtToolSearch.setEnabled(false);
        edtToolSearch.setGravity(Gravity.CENTER);

    }

    /**
     * linear 클릭 이벤트 메서드
     * <p>
     * intent만 있음
     *
     * @param v
     */
    public void lClick(View v) {
        switch (v.getId()) {
            case R.id.linearMemberList: {
                startActivity(new Intent(getApplicationContext(), MemberListActivity.class));
                break;
            }
            case R.id.linearMemberAdd: {
                startActivity(new Intent(getApplicationContext(), MemberAddActivity.class));
                break;
            }
            case R.id.linearMemberMod: {
                startActivity(new Intent(getApplicationContext(), MemberModListActivity.class));
                break;
            }
            case R.id.linearMemberDelete: {
                startActivity(new Intent(getApplicationContext(), MemberDeleteActivity.class));
                break;
            }
        }
    }
}
