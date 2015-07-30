package com.example.lxia.readingbuddies;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.CircularPropagation;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class BookCommentActivity extends AppCompatActivity {

    private CircleImageView image;
    private TextView mName,mReader;
    private ListView mListView;
    private CommentAdapter adapter;

    private List<Comment> comments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_comment);

        init();
    }

    private void initView(){

        image = (CircleImageView) findViewById(R.id.cv_book);
        mName = (TextView) findViewById(R.id.tv_book_name);
        mReader = (TextView) findViewById(R.id.tv_reader);

        mListView = (ListView) findViewById(R.id.lv_comment);

    }

    private void init() {

        initView();

        image.setImageResource(R.drawable.book);
        mName.setText("The Book Of Lost Things");
        mReader.setText("Abdullah Shoaib");

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

        adapter = new CommentAdapter(comments);
        mListView.setAdapter(adapter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class CommentAdapter extends BaseAdapter {

        private List<Comment> mComments;

        public CommentAdapter(List<Comment> list) {

            this.mComments = list;

        }

        @Override
        public int getCount() {
            return mComments == null ? 0 : mComments.size();
        }

        @Override
        public Object getItem(int position) {

            return comments.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (null == convertView) {

                convertView = getLayoutInflater()
                        .inflate(R.layout.comment_detail, parent, false);

                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.loc = (TextView) convertView.findViewById(R.id.tv_loc);
                holder.comment = (TextView) convertView.findViewById(R.id.tv_comment);
                holder.time = (TextView) convertView.findViewById(R.id.tv_time);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Comment c = (Comment) getItem(position);

            holder.title.setText(c.getCommentName());
            holder.loc.setText("Location : " +c.getLocation());
            holder.comment.setText(c.getDescription());
            holder.time.setText("Date : 2051/7/29");

            // configure the view for this Comment
      //

      //      loadUrl(holder.image, c.getmIsbn());

            return convertView;
        }

    }

    static class ViewHolder {

        TextView title;
        TextView time;
        TextView loc;
        TextView comment;

    }

}
