package com.example.lxia.readingbuddies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEmail;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mRePassword;
    private TextView mLogin;
    private Button mSubmit;
    private String mGender;
    private CircleImageView mFace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }


    private void initView() {

        mEmail = (EditText) findViewById(R.id.et_email);
        mUsername = (EditText) findViewById(R.id.et_username);
        mPassword = (EditText) findViewById(R.id.et_password);
        mRePassword = (EditText) findViewById(R.id.et_re_password);
        mLogin = (TextView) findViewById(R.id.tv_login);
        mLogin.setOnClickListener(this);

        mSubmit = (Button) findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);

        mFace = (CircleImageView) findViewById(R.id.cv_face);
        mFace.setOnClickListener(this);

    }

    private void init() {
        initView();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_boy:
                if (checked)
                    mGender = "1";
                    break;
            case R.id.rb_girl:
                if (checked)
                    mGender = "0";
                    break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

        return super.onOptionsItemSelected(item);
    }

    private void register() {
        String email = mUsername.getText().toString();
        final String username = mUsername.getText().toString();
        final String password = mPassword.getText().toString();
        String repassword = mRePassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            showMessage("Empty Email");
            return;
        }

        if (TextUtils.isEmpty(username)) {
            showMessage("Empty Username");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            showMessage("Empty Password");
            return;
        }

        if (TextUtils.isEmpty(repassword)) {
            showMessage("Empty Repassword");
            return;
        }

        if (!TextUtils.equals(password, repassword)){
            showMessage("Repassword and Password are not equal");
            return;
        }

        final ProgressDialog pd = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
        pd.setMessage("Register now");
        pd.show();

        //send request

        String url = "";
        StringRequest loginRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        showMessage("Good");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        showMessage("Error");
                    }
                }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("name", username);
                map.put("password", password);
                return map;
            }
        };

        loginRequest.setTag(this.getLocalClassName());

        MyApplication.getInstance().getVolleyQueue().add(loginRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().getVolleyQueue().cancelAll(this.getLocalClassName());
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_login:
                gotoLogin();
                break;
            case R.id.submit:
                register();
                break;
            case R.id.cv_face:
                openGallery();
                break;
            default:break;
        }
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                String [] proj={MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(data.getData(), proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(column_index);
                cursor.close();

                showMessage(path);

                Bitmap bitmap = BitmapFactory.decodeFile(path);
                mFace.setImageBitmap(bitmap);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void gotoLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
