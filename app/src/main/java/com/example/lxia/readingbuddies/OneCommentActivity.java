package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class OneCommentActivity extends AppCompatActivity{

    protected Fragment createFragment() {
        int crimeId = (int)getIntent()
                .getSerializableExtra(OneCommentFragment.EXTRA_COMMENT_ID);
        return OneCommentFragment.newInstance(crimeId);
    }
}
