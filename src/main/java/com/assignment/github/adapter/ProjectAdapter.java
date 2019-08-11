package com.assignment.github.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.github.R;
import com.assignment.github.model.pojo.Project;
import com.assignment.github.view.activity.ProjectActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectHolder> {

    private Activity activity;
    private List<Project> projectList;
    public ProjectAdapter(Activity activity) {
        this.activity = activity;
    }
    @NonNull
    @Override
    public ProjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recycler_view_item, viewGroup, false);
        return new ProjectHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProjectHolder holder, final int i) {
        holder.author.setText(projectList.get(i).getUsername());
        holder.projectName.setText(projectList.get(i).getRepo().getName());
        Glide.with(activity).load(projectList.get(i).getAvatar()).into(holder.avatar);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ProjectActivity.class);
                intent.putExtra("project", projectList.get(i));
                activity.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        if (projectList == null) {
            return 0;
        } else {
            return projectList.size();
        }
    }
    class ProjectHolder extends RecyclerView.ViewHolder {
        TextView author, projectName;
        ImageView avatar;
        LinearLayout layout;
        public ProjectHolder(@NonNull View itemView) {
            super(itemView);
            projectName = (TextView) itemView.findViewById(R.id.projectTextView);
            layout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            author = (TextView) itemView.findViewById(R.id.authorTextView);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
        }
    }
    public void updateList(List<Project> itemList){
        this.projectList = itemList;
        notifyDataSetChanged();
    }
}
