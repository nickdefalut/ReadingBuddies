package com.example.lxia.readingbuddies;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ReadingActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    RadioGroup radioGroup;
    Fragment meFragment;
    Fragment commentListFragment;
    Fragment contactFragment;
    Fragment nearByFragment;

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        boolean checked = ((RadioButton) view).isChecked();

        boolean isNew = false;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_home:
                if(commentListFragment==null)
                {
                    commentListFragment = new CommentListFragment();
                    isNew = true;
                }
                addFragment(commentListFragment, isNew);
                    break;
            case R.id.radio_discover:
                if(nearByFragment==null)
                {
                    nearByFragment = new NearByFragment();
                    isNew = true;
                }
                addFragment(nearByFragment, isNew);
                break;
            case R.id.radio_contact:
                if(contactFragment==null)
                {
                    contactFragment = new ContactFragment();
                    isNew = true;
                }
                addFragment(contactFragment, isNew);
                break;
            case R.id.radio_me:
                if(meFragment==null)
                {
                    meFragment = new MeFragment();
                    isNew = true;
                }
                addFragment(meFragment, isNew);
                    break;
        }
    }

    void replaceFragment(Fragment newFragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragmentContainer, newFragment);
        transaction.addToBackStack(newFragment.toString());

        // Commit the transaction
        transaction.commit();
    }

    void addFragment(Fragment fragment, boolean isNew)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       // Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) return;

        if (meFragment != null) {
            transaction.hide(meFragment);
        }

        if (commentListFragment != null) {
            transaction.hide(commentListFragment);
        }

        if (contactFragment != null) {
            transaction.hide(contactFragment);
        }

        if (nearByFragment != null) {
            transaction.hide(nearByFragment);
        }

        if (isNew) {
            transaction
                    .add(R.id.fragmentContainer, fragment);
        }
        transaction.show(fragment);
        transaction.commit();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        commentListFragment = new CommentListFragment();

        addFragment(commentListFragment, true);
    }
}
