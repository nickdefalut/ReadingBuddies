package com.example.lxia.readingbuddies;

/**
 * Created by lxia on 7/27/2015.
 */

import java.util.ArrayList;

import android.content.Context;

public class ReaderLab {
    private ArrayList<Reader> mReaders;

    private static ReaderLab sReaderLab;
    private Context mAppContext;

    private ReaderLab(Context appContext) {
        mAppContext = appContext;
        mReaders = new ArrayList<Reader>();
        for (int i = 0; i < 100; i++) {
            Reader r = new Reader();
            r.setName("Name"+i);
            r.setGender("Gender" + i);
            r.setId(i);
            mReaders.add(r);
        }
    }

    public static ReaderLab get(Context c) {
        if (sReaderLab == null) {
            sReaderLab = new ReaderLab(c.getApplicationContext());
        }
        return sReaderLab;
    }

    public Reader getReader(String name) {
        for (Reader c : mReaders) {
            if (c.getName()== name)
                return c;
        }
        return null;
    }

    public ArrayList<Reader> getReaders() {
        return mReaders;
    }
    public void addCrime(Reader c) {
        mReaders.add(c);
    }
}

