package com.mran.nmusic.fragmentutil;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.mran.nmusic.R;
import com.mran.nmusic.main.view.MainFragment;
import com.mran.nmusic.search.view.SearchFragment;

/**
 * Created by 张孟尧 on 2016/10/6.
 */

public class FragmentControl {
    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private SearchFragment searchFragment;

    public FragmentControl(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (mainFragment != null) {
            fragmentTransaction.hide(mainFragment);
        }
        if (searchFragment != null) {
            fragmentTransaction.hide(searchFragment);
        }
    }

    public void selectFragment(String showFragmentid) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (showFragmentid) {
            case "mainfragment":
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                    fragmentTransaction.add(R.id.main_fragment_holder,mainFragment, "mainfragment");
                } else {
                    fragmentTransaction.show(mainFragment);
                }
                break;
            case "searchfragment":
                if (searchFragment == null) {
                    searchFragment = new SearchFragment();
                    fragmentTransaction.add(R.id.main_fragment_holder,searchFragment, "searchfragment");
                } else {
                    fragmentTransaction.show(searchFragment);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    public void restoreFragmentState(Bundle saveInstanceState) {
        if (saveInstanceState != null) {
            mainFragment = new MainFragment();
            mainFragment = (MainFragment) fragmentManager.findFragmentByTag("mainfragment");
            searchFragment = new SearchFragment();
            searchFragment = (SearchFragment) fragmentManager.findFragmentByTag("searchfragment");
        }
    }
}
