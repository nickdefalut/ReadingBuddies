package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */

import java.util.UUID;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class OneCommentActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        int crimeId = (int)getIntent()
                .getSerializableExtra(OneCommentFragment.EXTRA_COMMENT_ID);
        return OneCommentFragment.newInstance(crimeId);
    }
}
