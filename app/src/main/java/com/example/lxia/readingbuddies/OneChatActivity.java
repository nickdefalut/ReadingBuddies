package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OneChatActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private EditText mWords;
    private Button mSubmt;

    private List<Chat> chats;
    private MyAdapter mAdapter;


    private String userId = "1";
    private String toUserId = "11";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_comment_fragment);
        init();

    }

    private void init() {

        setTitle("Chatting");
        userId = getIntent().getStringExtra("userId");
        toUserId = getIntent().getStringExtra("toUserId");

        initView();

        mAdapter = new MyAdapter();

        chats = new ArrayList<>();
        chats.add(new Chat("1","11","Hello","1"));
        chats.add(new Chat("11","1","Hello sfsaf","1"));


        chats.add(new Chat("1","11","Hello","1"));
        chats.add(new Chat("11","1","Good morning Hello","1"));
        chats.add(new Chat("1","11","Good morning too","1"));
        chats.add(new Chat("1","11","Hello","1"));
        chats.add(new Chat("1","11","Hello","1"));
        chats.add(new Chat("1","11","Hello","1"));
        chats.add(new Chat("1","11","Hello","1"));
        chats.add(new Chat("1","11","Hello","1"));
        chats.add(new Chat("1","11","Hello","1"));
        chats.add(new Chat("1", "11", "Hello", "1"));

        mListView.setAdapter(mAdapter);


    }

    private void initView() {

        mSubmt = (Button) findViewById(R.id.submit);
        mWords = (EditText) findViewById(R.id.et_words);
        mListView = (ListView) findViewById(R.id.lv_chat);
        mSubmt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.submit:

                String word = mWords.getText().toString();
                if (!TextUtils.isEmpty(word)) {
                    chats.add(new Chat(userId, toUserId, word, "1"));
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(this, "Empty Message", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return chats == null ? 0 :chats.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder holder = null;

            if (convertView == null) {

                holder = new ViewHolder();
                int type = getItemViewType(position);
                if (type == 1) {
                    convertView = getLayoutInflater().inflate(R.layout.chat_left, parent, false);
                    holder.tv = (TextView) convertView.findViewById(R.id.tv_chat);
                } else {
                    convertView = getLayoutInflater().inflate(R.layout.chat_right, parent, false);
                    holder.tv = (TextView) convertView.findViewById(R.id.tv_chat);
                }

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv.setText(chats.get(position).getContent());

            return convertView;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }


        @Override
        public int getItemViewType(int position) {

            // left
            if (userId.equals(chats.get(position).getFromUserId())) {
                return 0;
            }

            return 1;

        }
    }

    static class ViewHolder {
        TextView tv;
    }
}
