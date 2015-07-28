package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CommentLab {
    private ArrayList<Comment> mComments;

    private static CommentLab sReaderLab;
    private Context mAppContext;

    private CommentLab(Context appContext) {
        mAppContext = appContext;
        mComments = new ArrayList<Comment>();
        for (int i = 0; i < 100; i++) {
            Comment c = new Comment();
            c.setId(i);
            c.setTitle("BookName #" + i);
            c.setDescription("Description #" + i);
            c.setLocation("Location #" + i);
            c.setCommentName("CommentName #" + i);
            mComments.add(c);
        }
    }

    public static CommentLab get(Context c) {
        if (sReaderLab == null) {
            sReaderLab = new CommentLab(c.getApplicationContext());
        }
        return sReaderLab;
    }

    public Comment getComment(int id) {
        for (Comment c : mComments) {
            if (c.getId()== id)
                return c;
        }
        return null;
    }

    public ArrayList<Comment> getComments() {
        return mComments;
    }
    public void addCrime(Comment c) {
        mComments.add(c);
    }
}

