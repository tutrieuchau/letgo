package com.tutrieuchau.letgo.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tutrieuchau.letgo.Dialog.ActiveCodeDialog;
import com.tutrieuchau.letgo.R;
import com.tutrieuchau.letgo.Service.Service;
import com.tutrieuchau.letgo.Service.ServiceCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends BaseActivity implements View.OnClickListener{
    private EditText edtUsername;
    private EditText edtPassword;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // init button and EditText
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        TextView btnSignUp = (TextView) findViewById(R.id.btnSignUp);
        ImageButton btnLoginViaFb = (ImageButton) findViewById(R.id.btnLoginViaFacebook);
        ImageButton btnLoginViaGg = (ImageButton) findViewById(R.id.btnLoginViaGoogle);
        edtUsername = (EditText) findViewById(R.id.username);
        edtPassword = (EditText) findViewById(R.id.password);

        btnLogin.setOnClickListener(this);
        btnLoginViaFb.setOnClickListener(this);
        btnLoginViaGg.setOnClickListener(this);

        // init
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.activity = this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnLoginViaFacebook:
                break;
            case R.id.btnLoginViaGoogle:
                break;
            case R.id.btnSignUp:
                break;
        }
    }

    /**
     * Login via username and password
     */
    private void login(){
        if(edtUsername.getText().toString().trim().equalsIgnoreCase("") || edtPassword.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(activity,R.string.error_username_empty,Toast.LENGTH_SHORT).show();
        }else{
            //TODO: Login
            JSONObject json = new JSONObject();
            try {
                json.put("code","100");
                json.put("username",edtUsername.getText().toString().trim());
                json.put("password",edtPassword.getText().toString().trim());
                new Service(activity, new ServiceCallback() {
                    @Override
                    public void onProcessComplete(JSONObject result) {
                        try {
                            if(result == null){
                                Toast.makeText(activity,R.string.error_password_wrong,Toast.LENGTH_SHORT).show();
                            }else if(result.getInt("code") == 100){
                                //TODO: login success
                            }else  if(result.getInt("code") == 101){
                                Toast.makeText(activity,R.string.error_password_wrong,Toast.LENGTH_SHORT).show();
                            }else if(result.getInt("code") == 102){
                                ActiveCodeDialog dialog = new ActiveCodeDialog(activity);
                                dialog.show();
                            }else if(result.getInt("code") == 103){
                                Toast.makeText(activity,R.string.error_username_not_exist,Toast.LENGTH_SHORT).show();
                            }else if(result.getInt("code") == 104){
                                // login wrong many time
                                //TODO:login wrong many time
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).execute(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     *  Login via facebook
     */
    private  void loginViaFacebook(){

    }

    /**
     *  Login via google
     */

    private void loginViaGoogle(){

    }
}
