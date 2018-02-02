package com.example.shivam.dellproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeActivity extends AppCompatActivity {
    EditText editText;
    Button submit_button,scan_barcode;
    private ZXingScannerView zXingScannerView;
    private static final String CODE = "error";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        editText = (EditText)findViewById(R.id.input_service_text);
        submit_button = (Button)findViewById(R.id.submit_button_barcode);
        scan_barcode = (Button)findViewById(R.id.barcode_scan_button);
        scan_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(BarcodeActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(BarcodeActivity.this,
                            new String[]{Manifest.permission.CAMERA}, 1);

                }
                scanCode(view);

            }
        });
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check_text = editText.getText().toString();
                check_text = perform_validation(check_text);
                if (!check_text.equals(CODE)){
                    Intent intent = new Intent(BarcodeActivity.this,NavigatorActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    public void scanCode(View view){
        zXingScannerView = new ZXingScannerView(this);
        zXingScannerView.setResultHandler(new ZxingScannerResultHandler());
        setContentView(zXingScannerView);
        zXingScannerView.startCamera();
    }
    class ZxingScannerResultHandler implements ZXingScannerView.ResultHandler{

        @Override
        public void handleResult(com.google.zxing.Result result) {
            String result_code = result.getText();
            Toast.makeText(BarcodeActivity.this,result_code,Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_barcode);
            editText.setText(result_code);
        }
    }
    public String perform_validation(String text){
        //TODO : Perform Validations Accordingly
        if (text.trim().length()<4){
            return CODE;
        }
        else {
            return text;
        }

    }
    @Override
    public void  onPause(){

        super.onPause();
        //zXingScannerView.stopCamera();
    }
}
