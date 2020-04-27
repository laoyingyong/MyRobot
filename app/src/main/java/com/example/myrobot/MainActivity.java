package com.example.myrobot;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity
{
    private EditText et;
    private Button btn;
    private RecyclerView rv;
    private List<Msg> msgList = new ArrayList<>();
    private MsgAdapter adapter;
    private String [] arr={"近来可好，我亲爱的主人！",
            "欢迎主人的归来！",
            "很高兴又见到了我的主人！",
            "主人您好，请问有什么可以帮您的吗？",
            "我是一个能跟用户智能交互的“人”，可以帮用户查询一些实用的资料，比如天气预报、LoL战绩、IP地址等，还拥有一些娱乐系统，比如笑话和抽签等。"
    };
    private long curTime=0,oldTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.et);
        btn=findViewById(R.id.btn);
        rv=findViewById(R.id.rv);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        rv.setAdapter(adapter);

        initMsgs();


        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String content = et.getText().toString();
                if(!content.equals(""))
                {
                    Msg msg = new Msg();
                    msg.setContent(content);
                    msg.setType(Msg.TYPE_SEND);
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = simpleDateFormat.format(new Date());
                    msg.setTime(format);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1); // 当有新消息时，刷新 RecyclerView 中的显示
                    rv.scrollToPosition(msgList.size()-1);//// 将RecyclerView 定位到最后一行
                    et.setText("");
                    InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et.getWindowToken(), 0);

                    HttpUtil.sendHttpRequest("http://i.itpk.cn/api.php?api_key=fe6ed258c8faf18e6400bd7a9d401f16&api_secret=jwxa3c845wxb&question=" + content, new HttpCallbackListener()
                    {
                        @Override
                        public void onFinish(String response)
                        {
                            Msg msg = new Msg();
                            msg.setContent(response);
                            msg.setType(Msg.TYPE_RECEIVE);
                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String format = simpleDateFormat.format(new Date());
                            msg.setTime(format);
                            msgList.add(msg);
                            adapter.notifyItemInserted(msgList.size() - 1); // 当有新消息时，刷新 RecyclerView 中的显示
                            rv.scrollToPosition(msgList.size()-1);//// 将RecyclerView 定位到最后一行

                        }

                        @Override
                        public void onError(Exception e)
                        {

                        }
                    });


                }


            }
        });
    }


    private void initMsgs()
    {
        Random random=new Random();
        int i = random.nextInt(arr.length);
        Msg msg1 = new Msg();
        msg1.setType(Msg.TYPE_RECEIVE);
        msg1.setContent(arr[i]);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        msg1.setTime(format);
        msgList.add(msg1);

    }



}
