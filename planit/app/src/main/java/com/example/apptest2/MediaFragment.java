package com.example.apptest2;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MediaFragment extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_media, container, false);

        ViewPager2 viewPager2 = v.findViewById(R.id.viewPager);

        viewPager2.setAdapter(new ArticlePagerAdapter(this));
        final TabLayout tabLayout = v.findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, (tab, position) -> {
                    switch (position){
                        case 0:{
                            tab.setText("New");
                            tab.setIcon(R.drawable.new_icon);
                            break;
                        }
                        case 1:{
                            tab.setText("Popular");
                            tab.setIcon(R.drawable.favorite_icon);
                            break;
                        }
                    }
                }
        );
        tabLayoutMediator.attach();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });

        return v;
    }
}
