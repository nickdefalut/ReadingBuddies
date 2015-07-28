package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */

import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class OneCommentFragment extends Fragment {
    public static final String EXTRA_COMMENT_ID = "commentintent.COMMNET_ID";

    Comment mComment;
    TextView mTitleField;
    Button mDateButton;
    CheckBox mSolvedCheckBox;

    public static OneCommentFragment newInstance(int commentId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_COMMENT_ID, commentId);

        OneCommentFragment fragment = new OneCommentFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int commentId = (int)getArguments().getSerializable(EXTRA_COMMENT_ID);
        mComment = CommentLab.get(getActivity()).getComment(commentId);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.one_comment_fragment, parent, false);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

        mTitleField = (TextView) v.findViewById(R.id.read_title);
        mTitleField.setText(mComment.getBookTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                mComment.setTitle(c.toString());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

