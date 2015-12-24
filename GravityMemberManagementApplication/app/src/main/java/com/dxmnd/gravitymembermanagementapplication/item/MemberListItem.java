package com.dxmnd.gravitymembermanagementapplication.item;

/**
 * Created by HunJin on 2015-12-22.
 */
public class MemberListItem {
    private int mNumber;
    private String mName;
    private String mGender;

    public int getmNumber() {
        return mNumber;
    }

    public String getmName() {
        return mName;
    }

    public String getmGender() {
        return mGender;
    }

    public MemberListItem(int number, String name, String gender) {
        mNumber = number;
        mName = name;
        mGender = gender;
    }
}
