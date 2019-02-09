package com.example.android.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;

    private int[] iconIds = {R.mipmap.ic_ho, R.mipmap.ic_spo, R.mipmap.ic_mu,
            R.mipmap.ic_mov, R.mipmap.ic_po, R.mipmap.ic_tec, R.mipmap.ic_bu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.sliding_tabs);
        for (int i = 0; i < 7; i++) {
            tabLayout.addTab(tabLayout.newTab().setIcon(iconIds[i]));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_id);
        ActionBarDrawerToggle toggle_btn = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle_btn);
        toggle_btn.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_id);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener
                (new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        if (savedInstanceState == null) {
            navigationView.getMenu().performIdentifierAction(R.id.home, 0);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_id);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home:
                viewPager.setCurrentItem(0);
                getSupportActionBar().setTitle(R.string.home);
                break;
            case R.id.sport:
                viewPager.setCurrentItem(1);
                getSupportActionBar().setTitle(R.string.sport);
                break;
            case R.id.music:
                viewPager.setCurrentItem(2);
                getSupportActionBar().setTitle(R.string.music);
                break;
            case R.id.film:
                viewPager.setCurrentItem(3);
                getSupportActionBar().setTitle(R.string.film);
                break;
            case R.id.politics:
                viewPager.setCurrentItem(4);
                getSupportActionBar().setTitle(R.string.politics);
                break;
            case R.id.tech:
                viewPager.setCurrentItem(5);
                getSupportActionBar().setTitle(R.string.tech);
                break;
            case R.id.business:
                viewPager.setCurrentItem(6);
                getSupportActionBar().setTitle(R.string.business);
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_id);
        item.setChecked(true);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
