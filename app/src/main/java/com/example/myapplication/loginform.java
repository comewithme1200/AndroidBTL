package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class loginform extends AppCompatActivity {
    Button Signin;
    Boolean flag = false;
    ArrayList<user> arr;
    TextInputEditText txtusername, txtpassword;
    SharedPreferences sharedPreferences;
    String savestateusername = "";
    String savestatepassword ="";
    Boolean check = false;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loginform);
        //ánh xạ
        Signin = (Button) findViewById(R.id.sginbtn);
        arr = new ArrayList<>();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        txtusername = (TextInputEditText) findViewById(R.id.usernameinput);
        txtpassword = (TextInputEditText) findViewById(R.id.passwordinput);
        t = (TextView)findViewById(R.id.test) ;
        t.setVisibility(View.INVISIBLE);
        //Lấy trạng thái đăng nhập
        getsavestate();
        //txtusername.setText(sharedPreferences.getString("username",""));
        //txtpassword.setText(sharedPreferences.getString("password",""));
        //đưa cờ logout ra
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle!=null)
        {
            check =bundle.getBoolean("check");
        }
        if(check == true){
            sharedPreferences.edit().clear().commit();
            getsavestate();
            t.setText(savestateusername+" "+ savestatepassword+ "success");
        }
            //kiểm tra đã đăng nhập chưa
            checked();

        getData("http://192.168.212.2/BTL/getuserdata.php");
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < arr.size(); i++) {
                    if (arr.get(i).getUsername().equals(txtusername.getText().toString()) && arr.get(i).getPassword().equals(txtpassword.getText().toString())) {
                        flag = true;
                    }
                }
                if (flag) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", txtusername.getText().toString());
                    editor.putString("password", txtpassword.getText().toString());
                    editor.commit();
                    getsavestate();
                    Intent intent = new Intent(loginform.this, Scscreen.class);
                    startActivity(intent);
                    finish();
                } else if (flag == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(loginform.this).setTitle("Thông báo").setMessage("Bạn nhập sai tên đăng nhập hoặc mật khẩu!");
                    builder.setPositiveButton("OK", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    private void checked() {
        if (savestateusername != "" && savestatepassword != "") {
            Intent intent = new Intent(loginform.this, Scscreen.class);
            startActivity(intent);
            finish();
        }
    }
    private void getsavestate()
    {
        savestateusername = sharedPreferences.getString("username", "");
        savestatepassword = sharedPreferences.getString("password", "");
    }
    private void getData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(loginform.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = null;
                    try {
                        object = response.getJSONObject(i);
                        arr.add(new user(object.getInt("maNV"), object.getString("Username"), object.getString("Password"), object.getString("Decen")));
                        //Toast.makeText(loginform.this, object.getInt("maNV") + "", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(loginform.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this).setTitle("Thông báo").setMessage("Bạn có muốn thoát không?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
        //super.onBackPressed();
    }
}