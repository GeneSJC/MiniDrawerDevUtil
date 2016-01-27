package com.mikepenz.materialdrawer.app;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mikepenz.materialdrawer.app.fragment.LM_Fragment;
import com.mikepenz.materialdrawer.app.fragment.PM_Fragment;

public class FragmentDemo1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentdemo_main);

        Fragment fragment = new PM_Fragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        Configuration config = getResources().getConfiguration();

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            /**
             * Landscape mode of the device
             */
            fragment = new LM_Fragment();
        }
        else
        {
            /**
             * Portrait mode of the device
             */
            fragment = new PM_Fragment();
        }

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    protected void onCreate2(Bundle savedInstanceState)
    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragmentdemo_main);
//
//        Configuration config = getResources().getConfiguration();
//
//        FragmentManager fragmentManager = getFragmentManager();
//
//        FragmentTransaction fragmentTransaction =
//                fragmentManager.beginTransaction();
//
//        /**
//         * Check the device orientation and act accordingly
//         */
//        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            /**
//             * Landscape mode of the device
//             */
//            LM_Fragment ls_fragment = new LM_Fragment();
//            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, ls_fragment);
//        }
//        else
//        {
//            /**
//             * Portrait mode of the device
//             */
//            PM_Fragment pm_fragment = new PM_Fragment();
//            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, pm_fragment);
//        }
//
//        fragmentTransaction.commit();
    }

}
