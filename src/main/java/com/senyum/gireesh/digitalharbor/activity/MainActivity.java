package com.senyum.gireesh.digitalharbor.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.senyum.gireesh.digitalharbor.R;
import com.senyum.gireesh.digitalharbor.activity.fragment.HomeFragment;
import com.senyum.gireesh.digitalharbor.activity.fragment.LeaveFragment;
import com.senyum.gireesh.digitalharbor.activity.fragment.LoginFragment;
import com.senyum.gireesh.digitalharbor.activity.fragment.NotificationFragment;
import com.senyum.gireesh.digitalharbor.helper.SQLiteHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce;
    private Menu opt_Menu = null;
    private SQLiteHandler sql = null;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sql = new SQLiteHandler(MainActivity.this);

        viewPager = (ViewPager) findViewById(R.id.mainpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.home_tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "click again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        opt_Menu = menu;
        if (!sql.isLoggedIn()) {
            opt_Menu.findItem(R.id.action_profile).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            Toast.makeText(MainActivity.this, "User Profile", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupTabIcons() {
        int i = 0;
        tabLayout.getTabAt(i++).setIcon(R.mipmap.nav_home);
        if (!sql.isLoggedIn())
            tabLayout.getTabAt(i++).setIcon(R.mipmap.nav_login);
        tabLayout.getTabAt(i++).setIcon(R.mipmap.nav_leave);
        tabLayout.getTabAt(i++).setIcon(R.mipmap.nav_notif);
        if (sql.isLoggedIn())
            tabLayout.getTabAt(i++).setIcon(R.mipmap.nav_logout);
    }

    /**
     * Adding fragments to ViewPager
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        if (!sql.isLoggedIn())
            adapter.addFragment(new LoginFragment(), "Login");
        adapter.addFragment(new LeaveFragment(), "Leaves");
        adapter.addFragment(new NotificationFragment(), "Notification");
        if (sql.isLoggedIn())
            adapter.addFragment(new NotificationFragment(), "Logout");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}