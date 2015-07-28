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

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_home:
                if(commentListFragment==null)
                {
                    commentListFragment=new CommentListFragment();
                }
                replaceFragment(commentListFragment);
                    break;
            case R.id.radio_discover:
                if(nearByFragment==null)
                {
                    nearByFragment=new NearByFragment();
                }
                replaceFragment(nearByFragment);
                break;
            case R.id.radio_contact:
                if(contactFragment==null)
                {
                    contactFragment=new ContactFragment();
                }
                replaceFragment(contactFragment);
                break;
            case R.id.radio_me:
                if(meFragment==null)
                {
                    meFragment=new MeFragment();
                }
                replaceFragment(meFragment);
                    break;
        }
    }

    void replaceFragment(Fragment newFragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragmentContainer, newFragment);
      //  transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    void addFragment(Fragment fragemntToBeUsed)
    {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        //if (fragment == null) {
            fragment = fragemntToBeUsed;
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        //}
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Fragment commentListFragment=new CommentListFragment();
        replaceFragment(commentListFragment);
    }
}
