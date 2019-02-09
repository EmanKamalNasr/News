package com.example.android.news;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragments.BusinessFragment;
import fragments.FilmFragment;
import fragments.HomeFragment;
import fragments.MusicFragment;
import fragments.PoliticsFragment;
import fragments.SportFragment;
import fragments.TechnologyFragment;

/**
 * Created by HP on 2/7/2019.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final int HOME = 0;
    private static final int SPORT = 1;
    private static final int MUSIC = 2;
    private static final int FILM = 3;
    private static final int POLITICS = 4;
    private static final int TECHNOLOGY = 5;
    private static final int BUSINESS = 6;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case HOME:
                return new HomeFragment();
            case SPORT:
                return new SportFragment();
            case MUSIC:
                return new MusicFragment();
            case FILM:
                return new FilmFragment();
            case POLITICS:
                return new PoliticsFragment();
            case TECHNOLOGY:
                return new TechnologyFragment();
            case BUSINESS:
                return new BusinessFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

}


