package com.ehammo.githubjavapop_mvp_clean.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ehammo.githubjavapop_mvp_clean.R;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryModel;
import com.ehammo.githubjavapop_mvp_clean.presentation.contract.RepositoryContract;
import com.ehammo.githubjavapop_mvp_clean.ui.view.RepositoryRowView;

import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryHolder> {

    private RepositoryContract.RepositoryPresenter presenter;


    public RepositoryAdapter(RepositoryContract.RepositoryPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public RepositoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.element_repository, parent, false);
        return new RepositoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryHolder holder, int position) {
        presenter.onBindRepositoryRowViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getRepositoriesRowsCount();
    }

    class RepositoryHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, RepositoryRowView {
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
            if (presenter != null) {
                int position = getAdapterPosition();
                presenter.onRepositoryChosen(position);
            }
        }

        public void setInfo(RepositoryModel repositoryModel) {
            name.setText(repositoryModel.getName());
            desc.setText(repositoryModel.getDescription());
            forks.setText(String.valueOf(repositoryModel.getForksCount()));
            stars.setText(String.valueOf(repositoryModel.getStargazersCount()));
            username.setText(repositoryModel.getOwner().getLogin());
            fullusername.setText(repositoryModel.getOwner().getName());
            // todo : colocar glide em data
//            Glide.with(activity).load(repositoryModel.getOwner().getAvatarUrl()).asBitmap()
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