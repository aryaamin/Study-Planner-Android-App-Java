package com.example.sp2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter
{   private int tabsNumber;
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, int tabs) {
        super(fragmentManager, lifecycle);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 0:
                return new fisrtFragment();
            case 1 :
                return new secondFragment();
            case 2 :
                return new thirdFragment();
            case 3 :
                return new fourthFragment();
                default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return tabsNumber;
    }
}
