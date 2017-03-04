package com.example.user.lets.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.user.lets.Views.ChatFragment;

/**
 * Created by Shayan on 04-Mar-17.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    private String tabTitle[] = new String[]{"Profile", "Chats"};

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return ChatFragment.newInstance("Events", 0);
            default:
                return ChatFragment.newInstance("Chats", 1);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
