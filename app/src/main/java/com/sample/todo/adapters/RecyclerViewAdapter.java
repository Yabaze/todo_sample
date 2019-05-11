package com.sample.todo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.todo.BR;
import com.sample.todo.R;
import com.sample.todo.database.dao.Semester;
import com.sample.todo.databinding.RecyclerItemBinding;
import com.sample.todo.view.activity.MainActivity;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Semester> semesterList;
    RecyclerItemBinding binding;

    public RecyclerViewAdapter(List<Semester> semesterList) {
        this.semesterList = semesterList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycler_item, parent, false);

        return new RecyclerViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {

        Semester item = semesterList.get(position);
        holder.bind(item);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MainActivity) binding.getRoot().getContext()).longPressed(item);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return semesterList.size();
    }

    public void addItems(List<Semester> semesterList) {
        this.semesterList = semesterList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public RecyclerItemBinding recyclerItemBinding;

        public RecyclerViewHolder(RecyclerItemBinding recyclerItemBinding) {
            super(recyclerItemBinding.getRoot());
            this.recyclerItemBinding = recyclerItemBinding;
        }

        public void bind(Object obj) {
            recyclerItemBinding.setVariable(BR.semester, obj);
            recyclerItemBinding.executePendingBindings();
        }
    }
}