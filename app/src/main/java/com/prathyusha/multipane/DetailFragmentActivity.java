package com.prathyusha.multipane;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class DetailFragmentActivity extends AppCompatActivity {

    private int position;
    private String tag = "DetailFragmentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fragment);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            finish();
        }
        Intent intent = this.getIntent();
        Bundle element = intent.getBundleExtra("bundle");

        DescriptionFragment detailFragment = new DescriptionFragment();
        detailFragment.setArguments(element);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.detailFrame, detailFragment);
        transaction.commit();


    }

}
