package com.example.myrobot;

public class Msg
{
    public static final int TYPE_RECEIVE=0;
    public static final int TYPE_SEND=1;
    private String content;
    private int type;
    private String time;

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
