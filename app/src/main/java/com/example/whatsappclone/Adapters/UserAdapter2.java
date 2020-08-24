package com.example.whatsappclone.Adapters;

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
import com.example.whatsappclone.MessageActivity;
import com.example.whatsappclone.Models.User;
import com.example.whatsappclone.R;

import java.util.List;

public class UserAdapter2 extends RecyclerView.Adapter<UserAdapter2.ViewHolder> {

    private Context mContext;
    private List<User> mUser;

    public UserAdapter2(Context mContext, List<User> mUser) {
        this.mContext = mContext;
        this.mUser = mUser;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item_2, parent, false);

        return new UserAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final User user = mUser.get(position);
        holder.username.setText(user.getUsername());
        holder.number.setText(user.getPhone());

        if (user.getImageURI().equals("default")) {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide
                    .with(mContext)
                    .load(user.getImageURI())
                    .into(holder.profile_image);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username, number;
        public ImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.contactUsername);
            profile_image = itemView.findViewById(R.id.profileImage);
            number = itemView.findViewById(R.id.contactNumber);
        }
    }

}
