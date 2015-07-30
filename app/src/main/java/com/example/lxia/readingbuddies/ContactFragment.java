package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactFragment extends ListFragment {

    private static final String TAG = "ContactFragment";

    private ArrayList<Contact> mReaders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        getActivity().setTitle(R.string.comments_title);
        init();
        ReaderAdapter adapter = new ReaderAdapter(mReaders);
        setListAdapter(adapter);
        setRetainInstance(true);
    }

    private void init() {

        mReaders = new ArrayList<>();
        mReaders.add(new Contact(0,"Abdullah Shoaib"));
        mReaders.add(new Contact(1,"Ogre_BGR"));
        mReaders.add(new Contact(1,"Rocknrolla"));
        mReaders.add(new Contact(1,"john hallw"));
        mReaders.add(new Contact(0,"KU la sha"));
        mReaders.add(new Contact(0,"Han Han"));
        mReaders.add(new Contact(0,"Jian Jun"));
        mReaders.add(new Contact(1,"Wu Di Haio"));
        mReaders.add(new Contact(0,"Flying Bird"));
        mReaders.add(new Contact(1,"Sdash Maerk"));
        mReaders.add(new Contact(1,"OPikbn Dumsa"));
        mReaders.add(new Contact(1,"Ljdasf Koilu"));
        mReaders.add(new Contact(0,"Phasu Mhfas"));
        mReaders.add(new Contact(0,"Jopaf Shoaib"));

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // To be done, jump to another fragment
//        Reader r = (Reader)(getListAdapter()).getItem(position);
//        // start an instance of CrimeActivity
//        Intent i = new Intent(getActivity(), OneChatActivity.class);
//        i.putExtra(OneChatFragment.EXTRA_USER_ID, r.getId());
       // startActivityForResult(i, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((ReaderAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class ReaderAdapter extends ArrayAdapter<Contact> {
        public ReaderAdapter(ArrayList<Contact> crimes) {
            super(getActivity(), android.R.layout.simple_list_item_1, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_contact, null);
            }

            // configure the view for this Reader
            Contact r = mReaders.get(position);

            CircleImageView header =
                    (CircleImageView)convertView.findViewById(R.id.cv_head);

            if (r.getId() == 0) {
                header.setImageResource(R.drawable.head);
            } else {
                header.setImageResource(R.drawable.head_boy);
            }
            TextView genderTextView =
                    (TextView)convertView.findViewById(R.id.reader_name);
            genderTextView.setText(r.getName());


            return convertView;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_comment_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

