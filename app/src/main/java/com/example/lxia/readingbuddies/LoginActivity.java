package com.example.lxia.readingbuddies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;
    private Button mButton;
    private TextView mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void initView() {
        mUsername = (EditText)findViewById(R.id.et_username);
        mPassword = (EditText) findViewById(R.id.et_password);


        mButton = (Button) findViewById(R.id.submit);
        mButton.setOnClickListener(this);

        mRegister = (TextView) findViewById(R.id.tv_register);
        mRegister.setOnClickListener(this);
    }

    private void init() {
        
        initView();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.submit:

                login();
                break;
            case R.id.tv_register:
                gotoRegister();
            default: break;
        }
    }

    private void gotoRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }


    private void login() {
        final String username = mUsername.getText().toString();
        final String password = mPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            showMessage("Empty Username");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            showMessage("Empty Password");
            return;
        }

//        final ProgressDialog pd = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
//        pd.setCancelable(false);
//        pd.setMessage("login now");
//        pd.show();

        //send request

        String url = "http://api.douban.com/v2/book/isbn/9787530209936";
        StringRequest loginRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        pd.dismiss();
                        showMessage("Good");
                        startActivity(new Intent(LoginActivity.this, ReadingActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        pd.dismiss();
                        showMessage("Error");
                        startActivity(new Intent(LoginActivity.this, ReadingActivity.class));
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
}
