package com.example.apptest2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ArticlePagerAdapter extends FragmentStateAdapter {

    public ArticlePagerAdapter(@NonNull MediaFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new NewFragment();
            default:
                return new PopularFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
