package com.biogram.frags;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.biogram.MainActivity;
import com.biogram.R;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class home extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName() ;
    AnimatedBottomBar animatedBottomBar;
    FragmentManager fragmentManager;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragment = new com.biogram.frags.homeFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();

        animatedBottomBar = findViewById(R.id.bottom_bar);
        animatedBottomBar.selectTabById(R.id.tab_bread,true);

        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int lastIndex, @Nullable AnimatedBottomBar.Tab lastTab,
                                      int newIndex, @NonNull AnimatedBottomBar.Tab newTab) {

                fragment = null;

                switch (newTab.getId()) {
                    case R.id.tab_home:
                        fragment = new com.biogram.frags.contactFragment();
                        break;
                    case R.id.tab_alarm:
                        fragment = new com.biogram.frags.searchFragment();
                        break;
                    case R.id.tab_bread:
                        fragment = new com.biogram.frags.homeFragment();
                        break;
                }


                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();
                } else {
                    Log.e(TAG, "ERRoR iN CREATING");
                }
            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });

    }
}


