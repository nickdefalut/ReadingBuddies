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

    private ArrayList<Comment> comments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.comments_title);

        init();

        CommentAdapter adapter = new CommentAdapter(comments);
        setListAdapter(adapter);
        setRetainInstance(true);
    }

    private void init() {

        comments = new ArrayList<>();

        //int mId, String mBookTitle, Date mDate, String mCommentName, String mIsbn, String mDescription, String mLocatio
        Comment comment = new Comment(0, "", null, "For software programmer and hardware engineer" ,"", "Comm Operator makes it more efficient for development of hardware-software application, client-server application as well as internet application. \n" +
                "It will boost the speed to design, development, debug and test applications and hardware devices, such as relay boards, Electronic Total Station, Global Positioning System, chemical and medical analysis instruments, DMX devices and SMTP protocol etc.",
                "Beijing");
        comments.add(comment);

        comment = new Comment(0, "", null, "It can send and receive data in high speed." ,"", "It can send and receive data in high speed. Data can be viewed in Text, Hex and Decimal format. It's also able to create complex structure data, like ZigBee/XBee API data framework or GIS Garmin data package. \n" +
                "All data are stored in lists which can be accessed easily from GUI. Data can be sent automatically with flexible auto sending rules. Perl, Python and Ruby script are supported as well as user's EXE and Plug-in dll.",
                "Beijing");
        comments.add(comment);
        comment = new Comment(0, "", null, "Data list can be created to a COP file. " ,"", "Data list can be created to a COP file. COP file can be opened by Comm Operator Pal, which is FREE lite version of Comm Operator. \n" +
                "\n" +
                "For system designer, Comm Operator will save your time and money to build prototype. The protocol design can be done with Comm Operator only. The content in send data list can be used as test data for later development.", "Beijing");
        comments.add(comment);
        comment = new Comment(0, "", null, "Comm Operator" ,"", "Comm Operator makes it more efficient for development of hardware-software application, client-server application as well as internet application. \n" +
                "It will boost the speed to design, development, debug and test applications and hardware devices, such as relay boards, Electronic Total Station, Global Positioning System, chemical and medical analysis instruments, DMX devices and SMTP protocol etc.",
                "Beijing");

        comments.add(comment);

        comment = new Comment(0, "", null, "For software programmer and hardware engineer" ,"", "Comm Operator makes it more efficient for development of hardware-software application, client-server application as well as internet application. \n" +
                "It will boost the speed to design, development, debug and test applications and hardware devices, such as relay boards, Electronic Total Station, Global Positioning System, chemical and medical analysis instruments, DMX devices and SMTP protocol etc.",
                "Beijing");
        comments.add(comment);

        comment = new Comment(0, "", null, "It can send and receive data in high speed." ,"", "It can send and receive data in high speed. Data can be viewed in Text, Hex and Decimal format. It's also able to create complex structure data, like ZigBee/XBee API data framework or GIS Garmin data package. \n" +
                "All data are stored in lists which can be accessed easily from GUI. Data can be sent automatically with flexible auto sending rules. Perl, Python and Ruby script are supported as well as user's EXE and Plug-in dll.",
                "Beijing");
        comments.add(comment);
        comment = new Comment(0, "", null, "Data list can be created to a COP file. " ,"", "Data list can be created to a COP file. COP file can be opened by Comm Operator Pal, which is FREE lite version of Comm Operator. \n" +
                "\n" +
                "For system designer, Comm Operator will save your time and money to build prototype. The protocol design can be done with Comm Operator only. The content in send data list can be used as test data for later development.", "Beijing");
        comments.add(comment);
        comment = new Comment(0, "", null, "Comm Operator" ,"", "Comm Operator makes it more efficient for development of hardware-software application, client-server application as well as internet application. \n" +
                "It will boost the speed to design, development, debug and test applications and hardware devices, such as relay boards, Electronic Total Station, Global Positioning System, chemical and medical analysis instruments, DMX devices and SMTP protocol etc.",
                "Beijing");

        comments.add(comment);

        comment = new Comment(0, "", null, "For software programmer and hardware engineer" ,"", "Comm Operator makes it more efficient for development of hardware-software application, client-server application as well as internet application. \n" +
                "It will boost the speed to design, development, debug and test applications and hardware devices, such as relay boards, Electronic Total Station, Global Positioning System, chemical and medical analysis instruments, DMX devices and SMTP protocol etc.",
                "Beijing");
        comments.add(comment);

        comment = new Comment(0, "", null, "It can send and receive data in high speed." ,"", "It can send and receive data in high speed. Data can be viewed in Text, Hex and Decimal format. It's also able to create complex structure data, like ZigBee/XBee API data framework or GIS Garmin data package. \n" +
                "All data are stored in lists which can be accessed easily from GUI. Data can be sent automatically with flexible auto sending rules. Perl, Python and Ruby script are supported as well as user's EXE and Plug-in dll.",
                "Beijing");
        comments.add(comment);
        comment = new Comment(0, "", null, "Data list can be created to a COP file. " ,"", "Data list can be created to a COP file. COP file can be opened by Comm Operator Pal, which is FREE lite version of Comm Operator. \n" +
                "\n" +
                "For system designer, Comm Operator will save your time and money to build prototype. The protocol design can be done with Comm Operator only. The content in send data list can be used as test data for later development.", "Beijing");
        comments.add(comment);
        comment = new Comment(0, "", null, "Comm Operator" ,"", "Comm Operator makes it more efficient for development of hardware-software application, client-server application as well as internet application. \n" +
                "It will boost the speed to design, development, debug and test applications and hardware devices, such as relay boards, Electronic Total Station, Global Positioning System, chemical and medical analysis instruments, DMX devices and SMTP protocol etc.",
                "Beijing");

        comments.add(comment);
    }

    private void loadUrl(final ImageView imageview, String isbn) {

        String url = "http://api.douban.com/v2/book/isbn/" +isbn;
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
                                Utils.loadImage(jsonObject.getString("image"), imageview);
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
        Intent i = new Intent(getActivity(), BookCommentActivity.class);
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
            loadUrl(holder.image, c.getmIsbn());

            holder.image.setImageResource(R.drawable.book);
            holder.bookname.setText(c.getCommentName());
            holder.loc.setText("Location : " + c.getLocation() + "   Date: 2015/07/29");

            holder.comment.setText(c.getDescription());
            holder.reader.setText("Reader : Abdullah Shoaib");


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

