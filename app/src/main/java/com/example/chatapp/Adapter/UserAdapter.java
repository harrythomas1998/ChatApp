package com.example.chatapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatapp.MessageActivity;
import com.example.chatapp.Model.Chat;
import com.example.chatapp.Model.User;
import com.example.chatapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<User> mUsers;
    private boolean isChat;

    String theLastMessage;




    public UserAdapter(Context mContext, List<User> mUsers, boolean isChat){

        this.mUsers = mUsers;
        this.mContext = mContext;
        this.isChat = isChat;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder myHolder = (ViewHolder) holder;

        final User user = mUsers.get(position);
        myHolder.username.setText(user.getUsername());

        if(user.getImageURL().equals("default")){

            myHolder.profile_image.setImageResource(R.mipmap.ic_launcher);

        }
        else{

            Glide.with(mContext).load(user.getImageURL()).into(myHolder.profile_image);
        }


        if(isChat){

            if(user.getStatus().equals("online")){

                myHolder.img_on.setVisibility(View.VISIBLE);
                myHolder.img_off.setVisibility(View.GONE);
            }
            else{

                myHolder.img_on.setVisibility(View.GONE);
                myHolder.img_off.setVisibility(View.VISIBLE);
            }
        }
        else{

            myHolder.img_on.setVisibility(View.GONE);
            myHolder.img_off.setVisibility(View.GONE);

        }

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public ImageView profile_image;
        private ImageView img_on;
        private ImageView img_off;
        private TextView last_msg;

        public ViewHolder(View itemView){

            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            img_on = itemView.findViewById(R.id.img_on);
            img_off = itemView.findViewById(R.id.img_off);
            last_msg = itemView.findViewById(R.id.last_msg);
        }
    }



}
