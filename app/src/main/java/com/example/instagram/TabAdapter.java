package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int tabposition) {
        switch (tabposition) {

            case 0:
                ProfileTab profiletab = new ProfileTab();
                return profiletab;

            case 1:
                UsersTab usertab = new UsersTab();
                return usertab;

            case 2:
                //Instead of making a new variable of type ShareImage, we can simply return it

                return new SharePictureTab();

            default:
                return null;

        }
//        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch(position){

            case 0:
                return "Profile";

            case 1:
                return "Users";

            case 2:
                return "Share Picture";

            default:
                return null;

        }


    }
}
