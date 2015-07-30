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

public class OneChatFragment extends Fragment {
    public static final String EXTRA_USER_ID = "ChatIntent.UserID";


//    public static OneChatFragment newInstance(int id) {
//        Bundle args = new Bundle();
//        args.putSerializable(EXTRA_USER_ID, id);
//
//        OneChatFragment fragment = new OneChatFragment();
//        fragment.setArguments(args);
//
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        int readerId = (int)getArguments().getSerializable(EXTRA_USER_ID);
//        mComment = CommentLab.get(getActivity()).getComment(readerId);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.one_comment_fragment, parent, false);
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
//
//    }
}

