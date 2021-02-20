package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class user_profile extends AppCompatActivity {
    private Button change;
    private String name,mail,phone,decen;
    TextInputEditText txtname,txtmail,txtphone,txtdecen;
    FloatingActionButton floatbtn;

    private void gettext(){
        name = txtname.getText().toString();
        mail = txtmail.getText().toString();
        phone = txtphone.getText().toString();
        decen = txtdecen.getText().toString();
    }
    private void showMenu()
    {
        PopupMenu menu = new PopupMenu(this,floatbtn);
        menu.getMenuInflater().inflate(R.menu.profile_menu, menu.getMenu());
        menu.show();
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile_edit:
                        txtname.setEnabled(true);
                        txtmail.setEnabled(true);
                        txtphone.setEnabled(true);
                        txtdecen.setEnabled(true);
                        change.setVisibility(View.VISIBLE);
                        gettext();
                        break;
                    case R.id.edit_avatar:
                        break;
                    case R.id.edit_cover:
                        break;
                }


                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //ánh xạ
        change = (Button)findViewById(R.id.changebtn);
        txtname = (TextInputEditText)findViewById(R.id.profile_name);
        txtmail = (TextInputEditText)findViewById(R.id.profile_mail);
        txtphone = (TextInputEditText)findViewById(R.id.profile_phone);
        txtdecen = (TextInputEditText)findViewById(R.id.profile_decen);
        floatbtn = (FloatingActionButton)findViewById(R.id.float_btn);

        gettext();
        //Listener
        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(user_profile.this).setTitle("Thông báo").setMessage("Bạn có muốn đổi không?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtname.setEnabled(false);
                                txtmail.setEnabled(false);
                                txtphone.setEnabled(false);
                                txtdecen.setEnabled(false);
                                change.setVisibility(View.INVISIBLE);
                            }
                        });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                txtname.setText(name);
                                txtmail.setText(mail);
                                txtphone.setText(phone);
                                txtdecen.setText(decen);
                                txtname.setEnabled(false);
                                txtmail.setEnabled(false);
                                txtphone.setEnabled(false);
                                txtdecen.setEnabled(false);
                                change.setVisibility(View.INVISIBLE);
                    }
                });
                dialog.show();
            }
        });
    }
}