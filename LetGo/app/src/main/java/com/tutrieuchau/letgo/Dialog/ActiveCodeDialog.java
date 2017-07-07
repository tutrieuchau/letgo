package com.tutrieuchau.letgo.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.tutrieuchau.letgo.R;

import java.util.List;

/**
 * Created by ductam on 2017/07/06.
 */

public class ActiveCodeDialog extends Dialog implements View.OnClickListener {

    Activity activity;
    public ActiveCodeDialog(Activity activity){
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_active_account);
        Button btnActive = (Button) findViewById(R.id.btnActive);
        TextView btnResendEmail = (TextView) findViewById(R.id.btnResendEmail);
        btnActive.setOnClickListener(this);
        btnResendEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnActive:
                //TODO: Active
                break;
            case R.id.btnResendEmail:
                // TODO: Resend Email
                break;
            default:
                break;
        }
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }
}
