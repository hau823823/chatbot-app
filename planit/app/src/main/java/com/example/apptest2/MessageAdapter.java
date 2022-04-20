package com.example.apptest2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private final int TYPE_MESSAGE_SENT = 0;
    private final int TYPE_MESSAGE_RECEIVED = 1;
    private final int TYPE_MESSAAGE_RECEIVED_WITHOUT_BUTTON = 2;

    private LayoutInflater inflater;
    private List<JSONObject> messages = new ArrayList<>();

    public MessageAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    private static class SentMessageHolder extends RecyclerView.ViewHolder {

        TextView messageTxt;

        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);

            messageTxt = itemView.findViewById(R.id.sentTxt);
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder{

        TextView messageTxt;
        View imageView;

        public ReceivedMessageHolder(@NonNull View itemView) {
            super(itemView);

            messageTxt = itemView.findViewById(R.id.receivedTxt);
            imageView = itemView.findViewById(R.id.sendTxt);
            imageView.setOnClickListener(MessageAdapter.this);
        }
    }

    private static class ReceivedMessageHolder_without_button extends RecyclerView.ViewHolder{

        TextView messageTxt;

        public ReceivedMessageHolder_without_button(@NonNull View itemView) {
            super(itemView);

            messageTxt = itemView.findViewById(R.id.receivedTxt);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        switch(viewType){
            case TYPE_MESSAGE_SENT:
                view = inflater.inflate(R.layout.item_send_message, parent, false);
                return new SentMessageHolder(view);
            case TYPE_MESSAGE_RECEIVED:
                view = inflater.inflate(R.layout.item_received_message, parent, false);
                return new ReceivedMessageHolder(view);
            case TYPE_MESSAAGE_RECEIVED_WITHOUT_BUTTON:
                view = inflater.inflate(R.layout.item_recieved_message_without_button, parent, false);
                return new ReceivedMessageHolder_without_button(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        JSONObject message = messages.get(position);

        try {
            if(message.getBoolean("isSent")){
                SentMessageHolder messageHolder = (SentMessageHolder) holder;
                messageHolder.messageTxt.setText(message.getString("message"));
            }else if(message.getBoolean("show")){
                ReceivedMessageHolder messageHolder = (ReceivedMessageHolder) holder;
                messageHolder.messageTxt.setText(message.getString("message"));
                messageHolder.imageView.setTag(position);
            }else{
                ReceivedMessageHolder_without_button messageHolder = (ReceivedMessageHolder_without_button) holder;
                messageHolder.messageTxt.setText(message.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {

        JSONObject message = messages.get(position);

        try {
            if(message.getBoolean("isSent")){
                return TYPE_MESSAGE_SENT;
            }else if(message.getBoolean("show")){
                return TYPE_MESSAGE_RECEIVED;
            }else{
                return  TYPE_MESSAAGE_RECEIVED_WITHOUT_BUTTON;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void addItem(JSONObject jsonObject){
        messages.add(jsonObject);
    }

    public interface OnItemClickListener  {
        void onItemClick(View v,  int position) throws JSONException;
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener  listener) {
        this.mOnItemClickListener  = listener;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            try {
                mOnItemClickListener.onItemClick(v, position);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}