package com.example.zhaziraoskenbayeva.githubusers;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;
import com.beardedhen.androidbootstrap.BootstrapThumbnail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by z.oskenbaeva on 19.01.2018.
 */

public class GithubUsersAdapter extends RecyclerView.Adapter<GithubUsersAdapter.GithubUsersViewHolder> {
    List<User> userList;
    public GithubUsersAdapter(List<User> regions) {
        userList = regions;
    }

    @Override
    public GithubUsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_info,
                parent, false);
        GithubUsersViewHolder wivh = new GithubUsersViewHolder(v);
        return wivh;
    }

    @Override
    public void onBindViewHolder(GithubUsersViewHolder holder, int position) {
        holder.tvLogin.setText(userList.get(position).getLogin());
        holder.tvHtmlUrl.setText(userList.get(position).getHtml_url());

        Picasso.with(holder.itemView.getContext())
                .load(userList.get(position).getPhoto_url())
                //.resize(size, size)
                //.centerCrop()
                .into(holder.ivPhoto);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void swapItems(List<User> usersList) {
        final UsersDiffCallback diffCallback = new UsersDiffCallback(this.userList, usersList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.userList.clear();
        this.userList.addAll(usersList);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class GithubUsersViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv_user;
        TextView tvLogin;
        TextView tvHtmlUrl;
        BootstrapCircleThumbnail ivPhoto;

        public GithubUsersViewHolder(View v) {
            super(v);
            cv_user = (CardView)v.findViewById(R.id.cv_user);
            tvLogin = (TextView) v.findViewById(R.id.tvLogin);
            tvHtmlUrl = (TextView) v.findViewById(R.id.tvHtmlUrl);
            ivPhoto = (BootstrapCircleThumbnail) v.findViewById(R.id.ivPhoto);
        }
    }

}
