package academy.learnprogramming.candy;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import academy.learnprogramming.candy.response.ChatResponse;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyviewHolder> {
    Context context;
    List<ChatResponse> chatResponses;
    List<ChatResponse> chat;
    String s;
    String a;

    public ChatAdapter(Context context, List<ChatResponse> chatResponses) {
        this.context = context;
        this.chatResponses = chatResponses;
    }

    @NonNull
    @Override
    public ChatAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.message_sent,viewGroup,false);
        //YoYo.with(Techniques.Landing).duration(400).repeat(0).playOn(view);
        //Toast.makeText(context, "bna hi", Toast.LENGTH_SHORT).show();
        return new ChatAdapter.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyviewHolder myviewHolder, int i) {
        //chatResponses.size();

        //Toast.makeText(context, ""+chatResponses.get(i).getSname(), Toast.LENGTH_SHORT).show();
        if(chatResponses.get(i).getSname().equalsIgnoreCase("admin")){
            myviewHolder.textView1.setVisibility(View.INVISIBLE);
            myviewHolder.textView.setText(chatResponses.get(i).getMessage());
            myviewHolder.textView.setVisibility(View.VISIBLE);
            //myviewHolder.textView1.setVisibility(View.INVISIBLE);


        }else{
            myviewHolder.textView.setVisibility(View.INVISIBLE);
            myviewHolder.textView1.setText(chatResponses.get(i).getMessage());
            myviewHolder.textView1.setVisibility(View.VISIBLE);
            //myviewHolder.textView.setVisibility(View.INVISIBLE);

        }


        //myviewHolder.textView.setText(chatResponses.get(i).getMessage());



    }

    public void setChatResponses(List<ChatResponse> chatResponse)
    {
        if (this.chatResponses != null){
           // Toast.makeText(context, "null hua", Toast.LENGTH_SHORT).show();
            this.chatResponses.clear();
            //this.chatResponses=null;
            notifyDataSetChanged();
        }
        this.chatResponses=chatResponse;
        notifyDataSetChanged();
    }
    public List<ChatResponse> getdata(){
        return chatResponses;
    }

    @Override
    public int getItemCount() {
        if(chatResponses!= null){

            return chatResponses.size();
        }
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView textView,textView1;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.chatt);
            textView1 = itemView.findViewById(R.id.chat);


        }
    }
}
