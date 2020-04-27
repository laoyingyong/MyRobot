package com.example.myrobot;

public interface HttpCallbackListener
{
    void onFinish(String response);
    void onError(Exception e);
}
