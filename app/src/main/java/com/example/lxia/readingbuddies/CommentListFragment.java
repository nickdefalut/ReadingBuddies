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

public class CommentListFragment extends ListFragment {

    private static final String TAG = "CommentListFragment";

    private ArrayList<Comment> mComments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.comments_title);
        mComments = CommentLab.get(getActivity()).getComments();
        CommentAdapter adapter = new CommentAdapter(mComments);
        setListAdapter(adapter);
        setRetainInstance(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // To be done, jump to another fragment
        Comment c = (Comment)(getListAdapter()).getItem(position);
        // start an instance of CrimeActivity
        Intent i = new Intent(getActivity(), OneCommentActivity.class);
        i.putExtra(OneCommentFragment.EXTRA_COMMENT_ID, c.getId());
        startActivityForResult(i, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((CommentAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class CommentAdapter extends ArrayAdapter<Comment> {
        public CommentAdapter(ArrayList<Comment> crimes) {
            super(getActivity(), android.R.layout.simple_list_item_1, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_comment, null);
            }

            // configure the view for this Comment
            Comment c = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.comment_book_name);
            titleTextView.setText(c.getBookTitle());
            TextView descriptionTextView =
                    (TextView)convertView.findViewById(R.id.comment_description);
            descriptionTextView.setText(c.getDescription());
            TextView readerTextView =
                    (TextView)convertView.findViewById(R.id.comment_reader_name);
            readerTextView.setText(c.getCommentName());
            TextView locationTextView =
                    (TextView)convertView.findViewById(R.id.comment_location);
            locationTextView.setText(c.getLocation());

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
            case R.id.menu_item_new_comment:
                Intent i = new Intent(getActivity(), NewReadingActivity.class);
                startActivityForResult(i, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

