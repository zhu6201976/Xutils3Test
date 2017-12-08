package com.example.administrator.test.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.test.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * xutils3多线程下载
 * compile 'org.xutils:xutils:3.3.42'
 * D:\tools\tomcat6.0.29\Tomcat 6.0\webapps\ROOT\weixin.exe文件到sd卡中
 * http://10.0.2.2:8080/weixin.exe
 * D/MainActivity: onSuccess: result.length():34540560
 * 2017年12月8日20:44:50
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText et_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        et_url = (EditText) findViewById(R.id.et_url);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "you allow the permission", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "you denied the permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    public void mulThreadDownload(View view) {
        String path = et_url.getText().toString().trim();
        if (path.isEmpty()) {
            Toast.makeText(this, "please input the url", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams params = new RequestParams();
        params.setUri(path);
        params.setSaveFilePath(Environment.getExternalStorageDirectory() + "/weixin.exe");
        x.http().get(params, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {
                Log.d(TAG, "onSuccess: result.length():" + result.length());
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
