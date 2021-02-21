package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class addNL extends AppCompatActivity {
    EditText txttennl, txtgianl, txtdonvi, txtsoluong;
    Button thembtn, huybtn;

    private void Anhxa() {
        txttennl = (EditText) findViewById(R.id.txttennl);
        txtgianl = (EditText) findViewById(R.id.txtgianl);
        txtdonvi = (EditText) findViewById(R.id.txtdonvi);
        txtsoluong = (EditText) findViewById(R.id.txtsoluong);
        thembtn = (Button) findViewById(R.id.suabtn);
        huybtn = (Button) findViewById(R.id.huybtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_n_l);
        Anhxa();
        thembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txttennl.getText().toString().isEmpty() || txtgianl.getText().toString().isEmpty() ||  txtdonvi.getText().toString().isEmpty() ||  txtsoluong.getText().toString().isEmpty()){
                    Toast.makeText(addNL.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    themnguyenlieu("http://192.168.212.2/BTL/insertNL.php");
                }
            }
        });
        huybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void themnguyenlieu(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(addNL.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(addNL.this,Qlkho.class));

                }
                else{
                    Toast.makeText(addNL.this,"Thêm lỗi",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("tenNL",txttennl.getText().toString());
                params.put("giaNL",txtgianl.getText().toString());
                params.put("donvi",txtdonvi.getText().toString());
                params.put("soluong",txtsoluong.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}