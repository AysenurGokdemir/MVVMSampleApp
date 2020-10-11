package com.aysenur.mvvmsampleapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aysenur.mvvmsampleapp.R;
import com.aysenur.mvvmsampleapp.databinding.ProjectListItemBinding;
import com.aysenur.mvvmsampleapp.service.model.Project;
import com.aysenur.mvvmsampleapp.view.callback.ProjectClickCallback;

import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    List<? extends Project> projectList;

    @Nullable
    private final ProjectClickCallback projectClickCallback;

    //nesne bu parametre degerine gore başlatılır
    public ProjectAdapter(@Nullable ProjectClickCallback projectClickCallback) {
        this.projectClickCallback = projectClickCallback;
    }

    public void setProjectList(final List<? extends Project> projectList){
        if (this.projectList==null){
            this.projectList=projectList;
            notifyItemRangeInserted(0,projectList.size());
        }else{
            DiffUtil.DiffResult result=DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ProjectAdapter.this.projectList.size();
                }

                @Override
                public int getNewListSize() {
                    return projectList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProjectAdapter.this.projectList.get(oldItemPosition).id == projectList.get(newItemPosition).id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Project project = projectList.get(newItemPosition);
                    Project old=projectList.get(oldItemPosition);


                    return project.id == old.id && Objects.equals(project.git_url, old.git_url);
                }
            });
            this.projectList= projectList;
            result.dispatchUpdatesTo(this);
        }
    }

   //data binding
    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProjectListItemBinding binding= DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.project_list_item,
                        parent, false);
        binding.setCallback(projectClickCallback);
        return new ProjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        holder.binding.setProject(projectList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {

        return projectList == null ? 0 : projectList.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {

        final ProjectListItemBinding binding;

        public ProjectViewHolder(ProjectListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
