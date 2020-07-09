package com.example.crudsqlite.Activity;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.crudsqlite.Database.PegawaiHelper;
import com.example.crudsqlite.Fragment.HomeFragment;
import com.example.crudsqlite.R;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MainActivity extends AppCompatActivity{

    Fragment fragment;
    private PegawaiHelper pegawaiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            doHomeFragment();
        }

        pegawaiHelper = PegawaiHelper.getInstance(getApplicationContext());
        pegawaiHelper.open();
    }



    private void doHomeFragment() {
        fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pegawaiHelper.close();
    }
}
