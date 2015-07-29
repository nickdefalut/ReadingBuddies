package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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

    private void loadUrl(final ImageView imageview) {

        String url = "http://api.douban.com/v2/book/isbn/9787530209936";
        StringRequest urlRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.has("image")) {
                                Utils.loadImage(jsonObject.getString("iamge"), imageview);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        urlRequest.setTag(TAG);
        MyApplication.getInstance().getVolleyQueue().add(urlRequest);
    }

    private void loadData() {

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

            ViewHodler holder = null;
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_comment, null);

                holder = new ViewHodler();
                holder.image = (ImageView) convertView.findViewById(R.id.iv_book);
                holder.bookname = (TextView) convertView.findViewById(R.id.tv_book_name);
                holder.loc = (TextView) convertView.findViewById(R.id.tv_location);
                holder.comment = (TextView) convertView.findViewById(R.id.tv_comment);
                holder.reader = (TextView) convertView.findViewById(R.id.tv_reader);

                convertView.setTag(holder);

            } else {

                holder = (ViewHodler) convertView.getTag();

            }

            // configure the view for this Comment
            Comment c = getItem(position);

            return convertView;
        }
    }

    static class ViewHodler {

        ImageView image;
        TextView bookname;
        TextView reader;
        TextView loc;
        TextView comment;
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

