package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */

import java.util.UUID;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class NewReadingActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NewReadingFragment();
    }
}
