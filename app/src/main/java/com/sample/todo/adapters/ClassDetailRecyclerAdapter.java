package com.sample.todo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.todo.BR;
import com.sample.todo.R;
import com.sample.todo.database.dao.entity.ClassTable;
import com.sample.todo.databinding.ClassesRecyclerViewBinding;
import com.sample.todo.view.activity.MainActivity;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ClassDetailRecyclerAdapter extends RecyclerView.Adapter<ClassDetailRecyclerAdapter.RecyclerViewHolder> {

    private List<ClassTable> classTableList;
    ClassesRecyclerViewBinding binding;

    public ClassDetailRecyclerAdapter(List<ClassTable> classTableList) {
        this.classTableList = classTableList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.classes_recycler_view, parent, false);

        return new RecyclerViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {

        ClassTable item = classTableList.get(position);
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
        return classTableList.size();
    }

    public void addItems(List<ClassTable> classTableList) {
        this.classTableList = classTableList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public ClassesRecyclerViewBinding classesRecyclerViewBinding;

        public RecyclerViewHolder(ClassesRecyclerViewBinding classesRecyclerViewBinding) {
            super(classesRecyclerViewBinding.getRoot());
            this.classesRecyclerViewBinding = classesRecyclerViewBinding;
        }

        public void bind(Object obj) {
            classesRecyclerViewBinding.setVariable(BR.classTable, obj);
            classesRecyclerViewBinding.executePendingBindings();
        }
    }
}