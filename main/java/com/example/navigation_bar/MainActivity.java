package com.example.navigation_bar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;

    private final int[] tabIcons = {
            R.drawable.ic_menu_slideshow,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_gallery
    };

    private final String[] tabTitles = {
            "Tab 1",
            "Tab 2",
            "Tab 3"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);
        myViewPagerAdapter = new MyViewPagerAdapter(this);
        viewPager2.setAdapter(myViewPagerAdapter);


//        new TabLayoutMediator(tabLayout, viewPager2,
//                (tab, position) -> {
//                    tab.setText(tabTitles[position]);
//                    tab.setIcon(tabIcons[position]);
//                }
//        ).attach();

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    View customView = getLayoutInflater().inflate(R.layout.custom_tab, null);
                    ImageView imageView = customView.findViewById(R.id.tabIcon);
                    TextView textView = customView.findViewById(R.id.tabText);
                    imageView.setImageResource(tabIcons[position]);
                    textView.setText(tabTitles[position]);
                    tab.setCustomView(customView);
                }
        ).attach();

//        // Set icons and text for the tabs
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_menu_gallery);
////        tabLayout.getTabAt(0).setText("Tab 1");
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_menu_slideshow);
//        //tabLayout.getTabAt(1).setText("Tab 2");
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_menu_camera);
//       // tabLayout.getTabAt(2).setText("Tab 3");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }
}