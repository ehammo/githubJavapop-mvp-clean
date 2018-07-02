package com.ehammo.githubjavapop_mvp_clean.presentation.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ehammo.githubjavapop_mvp_clean.R;
import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;

import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryHolder> {

    private RepositoryCollection mCollection;

    private RepositoryItemCallback mCallback;

    public interface RepositoryItemCallback {
        void onItemClick(Repository repository);
    }

    public RepositoryAdapter() {
        mCollection = new RepositoryCollection();
        mCallback = null;
    }

    public void setRepositoryCollection(RepositoryCollection collection) {
        mCollection = collection;
        notifyDataSetChanged();
    }

    public void setRepositoryCallback(RepositoryItemCallback callback) {
        mCallback = callback;
    }

    @Override
    public RepositoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.element_repository, parent, false);
        return new RepositoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryHolder holder, int position) {
        Repository repository = mCollection.getElement(position);
        holder.setInfo(repository);
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    class RepositoryHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView name;
        TextView desc;
        TextView forks;
        TextView stars;
        CircleImageView avatar;
        TextView username;
        TextView fullusername;

        RepositoryHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            desc = itemView.findViewById(R.id.tvDesc);
            forks = itemView.findViewById(R.id.tvForks);
            stars = itemView.findViewById(R.id.tvStars);
            avatar = itemView.findViewById(R.id.ivAvatar);
            username = itemView.findViewById(R.id.tvUsername);
            fullusername = itemView.findViewById(R.id.tvFullUserName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mCallback != null) {
                int position = getAdapterPosition();
                Repository repository = mCollection.getElement(position);
                mCallback.onItemClick(repository);
            }
        }

        public void setInfo(Repository repository) {
            name.setText(repository.getName());
            desc.setText(repository.getDescription());
            forks.setText(repository.getForksCount().toString());
            stars.setText(repository.getStargazersCount().toString());
            username.setText(repository.getOwner().getLogin());
            fullusername.setText(repository.getOwner().getName());
            // todo : colocar glide em data
//            Glide.with(activity).load(repository.getOwner().getAvatarUrl()).asBitmap()
//                    .into(new SimpleTarget<Bitmap>(320, 240) {
//                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                            Drawable drawable = new BitmapDrawable(activity.getResources(), resource);
//                            avatar.setImageDrawable(drawable);
//                        }
//                    });


        }
    }

}
