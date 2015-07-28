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

public class ContactFragment extends ListFragment {

    private static final String TAG = "ContactFragment";

    private ArrayList<Reader> mReaders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        getActivity().setTitle(R.string.comments_title);
        mReaders = ReaderLab.get(getActivity()).getReaders();
        ReaderAdapter adapter = new ReaderAdapter(mReaders);
        setListAdapter(adapter);
        setRetainInstance(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // To be done, jump to another fragment
        Reader r = (Reader)(getListAdapter()).getItem(position);
        // start an instance of CrimeActivity
        Intent i = new Intent(getActivity(), OneChatActivity.class);
        i.putExtra(OneChatFragment.EXTRA_USER_ID, r.getId());
        startActivityForResult(i, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((ReaderAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class ReaderAdapter extends ArrayAdapter<Reader> {
        public ReaderAdapter(ArrayList<Reader> crimes) {
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
            Reader r = getItem(position);

            TextView nameTextView =
                    (TextView)convertView.findViewById(R.id.reader_name);
            nameTextView.setText(r.getName());
            TextView genderTextView =
                    (TextView)convertView.findViewById(R.id.reader_gender);
            genderTextView.setText(r.getGender());


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

