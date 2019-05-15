package top.timewl.localculture.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import top.timewl.localculture.R;
import top.timewl.localculture.Tools.StreamTools;

public class LoginActivity extends Activity {


    private EditText phone;
    private EditText code;
    private Button getCode;
    private Button login;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                //判断验证码发送成功
                case 1 :
                    String code = (String) msg.obj;
                    if (code.equals("0")){
                        Toast.makeText(LoginActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                        CountDownTimer timer = new CountDownTimer(10000,1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                getCode.setEnabled(false);
                                getCode.setText("已发送(" + millisUntilFinished / 1000 + ")");
                            }

                            @Override
                            public void onFinish() {
                                getCode.setEnabled(true);
                                getCode.setText("重新获取验证码");
                            }
                        }.start();
                    }
                    else if (code.equals("1016")){
                        Toast.makeText(LoginActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "请检查你的网络连接", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    String login = (String) msg.obj;
                    if (login.equals("true")){
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "验证码错误或已失效", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initView();
        initListener();
    }

    private void initListener() {
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String phoneNumber = phone.getText().toString().trim();
                        String path = "http://chinaqwe.top:8080/regedit/sendmsg";
                        String data = "mobileNumber=" + phoneNumber;
                        URL url = null;
                        try {
                            url = new URL(path);
                            //获取connection链接对象
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            //设置请求方法和超时时间
                            connection.setRequestMethod("POST");
                            connection.setConnectTimeout(10000);
                            //添加头信息
                            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                            connection.setRequestProperty("Content-Length",data.length() + "");
                            //设置标记，允许连接对象输出
                            connection.setDoOutput(true);
                            connection.getOutputStream().write(data.getBytes());

                            int code = connection.getResponseCode();
                            if (code == 200){
                                //获取流的数据，以服务器的形式返回
                                InputStream inputStream = connection.getInputStream();
                                String content = StreamTools.readStream(inputStream);
                                String result = parseseCode(content);
                                Message message = new Message();
                                message.obj = result;
                                message.what = 1;
                                handler.sendMessage(message);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String phoneNumber = phone.getText().toString().trim();
                        String codeNumber = code.getText().toString().trim();
                        String path = "http://chinaqwe.top:8080/regedit/checkVerificationCode";
                        String data = "mobileNumber=" + phoneNumber + "&verficationCode=" + codeNumber;
                        URL url = null;
                        try {
                            url = new URL(path);
                            //获取connection链接对象
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            //设置请求方法和超时时间
                            connection.setRequestMethod("POST");
                            connection.setConnectTimeout(10000);
                            //添加头信息
                            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                            connection.setRequestProperty("Content-Length",data.length() + "");
                            //设置标记，允许连接对象输出
                            connection.setDoOutput(true);
                            connection.getOutputStream().write(data.getBytes());

                            int code = connection.getResponseCode();
                            if (code == 200){
                                //获取流的数据，以服务器的形式返回
                                InputStream inputStream = connection.getInputStream();
                                String content = StreamTools.readStream(inputStream);
                                Message message = new Message();
                                message.what = 2;
                                message.obj = content;
                                handler.sendMessage(message);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }

    private String parseseCode(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String result = jsonObject.getString("result");
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    private void initView() {
        phone = findViewById(R.id.phone);
        code = findViewById(R.id.code);
        getCode = findViewById(R.id.getcode);
        login = findViewById(R.id.login);
    }
}
