package com.example.myrobot;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>
{
    private static long curTime=0,oldTime=0;
    private List<Msg> mMsgList;
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        TextView timeTv1;
        TextView timeTv2;
        public ViewHolder(View view)
        {
            super(view);
            leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            leftMsg = (TextView) view.findViewById(R.id.left_msg);
            rightMsg = (TextView) view.findViewById(R.id.right_msg);
            timeTv1=(TextView)view.findViewById(R.id.timeTv1);
            timeTv2=(TextView)view.findViewById(R.id.timeTv2);

        }
    }
    public MsgAdapter(List<Msg> msgList)
    {
        mMsgList = msgList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Msg msg = mMsgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVE)
        {
            // 如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
            holder.timeTv1.setText(msg.getTime());
        } else if(msg.getType() == Msg.TYPE_SEND)
        {
            // 如果是发出的消息，则显示右边的消息布局，将左边的消息布局隐藏
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
            holder.timeTv2.setText(msg.getTime());
        }
    }
    @Override
    public int getItemCount()
    {
        return mMsgList.size();
    }


}
