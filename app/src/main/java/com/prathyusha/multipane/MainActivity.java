package com.prathyusha.multipane;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements OnVersionNameSelectionChangeListener {

    public final static String POSITION ="position";
    private String tag="mainActivity";
    private int lastState = -1;
    Bundle element = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


             // Check whether the Activity is using the layout verison with the fragment_container
            // FrameLayout and if so we must add the first fragment
            // However if we are being restored from a previous state, then we don't
            // need to do anything and should return or we could end up with overlapping Fragments
            if (savedInstanceState != null){
                int restorePosition = savedInstanceState.getInt("position");
                if(restorePosition != -1)
                {
                    OnSelectionChanged(restorePosition);
                }
            }
    }


    @Override
    public void OnSelectionChanged(int versionNameIndex) {

        this.lastState = versionNameIndex;
        DescriptionFragment descriptionFragment = (DescriptionFragment) getSupportFragmentManager().findFragmentById(R.id.description_fragment);

        if (descriptionFragment == null)
        {
            Log.d(tag,"inside onSelectionChanged: "+ versionNameIndex);

            Intent intent = new Intent(this,DetailFragmentActivity.class);
            Bundle element = new Bundle();
            element.putInt("position",versionNameIndex);
            intent.putExtra("bundle",element);
            startActivity(intent);
        }
       else {
            // If description is available, we are in two pane layout
            // so we call the method in DescriptionFragment to update its content
            descriptionFragment.setDescription(versionNameIndex);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle oustState)
    {
        oustState.putInt("position",lastState);
    }


    @Override

    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() != 0) {

            getFragmentManager().popBackStack();

        } else {

            super.onBackPressed();

        }

    }

}
