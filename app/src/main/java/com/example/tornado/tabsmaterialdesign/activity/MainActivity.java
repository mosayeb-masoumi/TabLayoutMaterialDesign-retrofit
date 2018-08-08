package com.example.tornado.tabsmaterialdesign.activity;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.tornado.tabsmaterialdesign.R;
import com.example.tornado.tabsmaterialdesign.fragment.FavoriteFragment;
import com.example.tornado.tabsmaterialdesign.fragment.NewFragment;
import com.example.tornado.tabsmaterialdesign.fragment.SortingFragment;

import java.util.ArrayList;
import java.util.List;

import ir.tapsell.sdk.Tapsell;
import ir.tapsell.sdk.TapsellAd;
import ir.tapsell.sdk.TapsellAdRequestListener;
import ir.tapsell.sdk.TapsellAdRequestOptions;
import ir.tapsell.sdk.TapsellAdShowListener;
import ir.tapsell.sdk.TapsellShowOptions;
import ir.tapsell.sdk.bannerads.TapsellBannerType;
import ir.tapsell.sdk.bannerads.TapsellBannerView;

public class MainActivity extends AppCompatActivity {

    //initialize video
    TapsellAd ad;

//    private Toolbar toolbar;
    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.ic_new ,
            R.drawable.ic_favorite,
            R.drawable.ic_sortting
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //banner ad
        TapsellBannerView banner1 = (TapsellBannerView)findViewById(R.id.banner1);
        //zoneId standard banner(for activating standard banner goto (https://dashboard.tapsell.ir) click on taarif tabigh gah jadid va entekhab standard banner va kharej kardan an az halate test.
        banner1.loadAd(MainActivity.this, "5b6ab34cc21bf000011dd5ad", TapsellBannerType.BANNER_320x50);


        //initialize video ad
        loadAd("5b6ab7d9c21bf000011dd5b2", TapsellAdRequestOptions.CACHE_TYPE_CACHED);
        final Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                loadAd("5b6ab7d9c21bf000011dd5b2", TapsellAdRequestOptions.CACHE_TYPE_CACHED);
                handler.postDelayed(this, 3000000);
            }
        };
        handler.postDelayed(runnable, 3000000);



       toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[2]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[0]);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FavoriteFragment(), "محبوب ترین ها");
        adapter.addFragment(new SortingFragment(), "دسته بندی");
        adapter.addFragment(new NewFragment(), "جدیدترین ها");
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










    public void showAD(){
        if (ad != null) {

            TapsellShowOptions showOptions = new TapsellShowOptions();
            showOptions.setBackDisabled(false);
            showOptions.setImmersiveMode(true);
            showOptions.setRotationMode(TapsellShowOptions.ROTATION_UNLOCKED);
            showOptions.setShowDialog(true);

            showOptions.setWarnBackPressedDialogMessage("سلام دوست من بک نزن");
            showOptions.setWarnBackPressedDialogMessageTextColor(Color.RED);
            showOptions.setWarnBackPressedDialogAssetTypefaceFileName("IranNastaliq.ttf");
            showOptions.setWarnBackPressedDialogPositiveButtonText("ادامه بده");
            showOptions.setWarnBackPressedDialogNegativeButtonText("ولم کن بزن بیرون");
            //      showOptions.setWarnBackPressedDialogPositiveButtonBackgroundResId(R.drawable.button_background);
//        showOptions.setWarnBackPressedDialogNegativeButtonBackgroundResId(R.drawable.button_background);
            showOptions.setWarnBackPressedDialogPositiveButtonTextColor(Color.WHITE);
            showOptions.setWarnBackPressedDialogNegativeButtonTextColor(Color.GREEN);
//        showOptions.setWarnBackPressedDialogBackgroundResId(R.drawable.dialog_background);
            ad.show(MainActivity.this, showOptions);
            ad.show(MainActivity.this, showOptions, new TapsellAdShowListener() {
                @Override
                public void onOpened(TapsellAd ad) {
                    Log.e("MainActivity", "on ad opened");
                }

                @Override
                public void onClosed(TapsellAd ad) {
                    Log.e("MainActivity", "on ad closed");
                }
            });
            MainActivity.this.ad = null;
        }

    }






    private void loadAd(final String zoneId, final int catchType) {

        if (ad == null) {
            TapsellAdRequestOptions options = new TapsellAdRequestOptions(catchType);
//            progressDialog.setCancelable(false);
//            progressDialog.setMessage("Loading ...");
//            progressDialog.show();
            Tapsell.requestAd(MainActivity.this, zoneId, options, new TapsellAdRequestListener() {
                @Override
                public void onError(String error) {
                    Toast.makeText(MainActivity.this, "ERROR:\n" + error, Toast.LENGTH_SHORT).show();
                    Log.e("Tapsell", error);
//                    progressDialog.dismiss();
                }

                @Override
                public void onAdAvailable(TapsellAd ad) {

                    MainActivity.this.ad = ad;
//                    showAddBtn.setEnabled(true);
                    Log.e("Tapsell", "adId: " + (ad == null ? "NULL" : ad.getId()) + " available, zoneId: " + (ad == null ? "NULL" : ad.getZoneId()));
//                    progressDialog.dismiss();

                    //todo show ad method
                    showAD();




//                new AlertDialog.Builder(MainActivity.this).setTitle("Title").setMessage("Message").show();
                }

                @Override
                public void onNoAdAvailable() {
                    Toast.makeText(MainActivity.this, "No Ad Available", Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
                    Log.e("Tapsell", "No Ad Available");
                }

                @Override
                public void onNoNetwork() {
                    Toast.makeText(MainActivity.this, "No Network", Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
                    Log.e("Tapsell", "No Network Available");
                }

                @Override
                public void onExpiring(TapsellAd ad) {
                    MainActivity.this.ad = null;
                    loadAd(zoneId, catchType);
                }
            });
        }
    }



}

