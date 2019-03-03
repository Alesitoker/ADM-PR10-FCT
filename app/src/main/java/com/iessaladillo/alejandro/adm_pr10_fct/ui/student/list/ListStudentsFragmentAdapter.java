package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentStudentsItemBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ListStudentsFragmentAdapter extends ListAdapter<Student, ListStudentsFragmentAdapter.ViewHolder> {

    protected ListStudentsFragmentAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName()) &&
                        oldItem.getPhone() == newItem.getPhone() &&
                        TextUtils.equals(oldItem.getEmail(), newItem.getEmail()) &&
                        TextUtils.equals(oldItem.getCompany(), newItem.getCompany()) &&
                        TextUtils.equals(oldItem.getSchedule(), newItem.getSchedule());
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentStudentsItemBinding b = FragmentStudentsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItem(position).getId();
    }

    @Override
    protected Student getItem(int position) {
        return super.getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        FragmentStudentsItemBinding b;

        public ViewHolder(@NonNull FragmentStudentsItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        public void bind(Student student) {
            b.lblName.setText(student.getName());
            b.lblEmail.setText(student.getEmail());
            b.lblPhonenumber.setText(String.valueOf(student.getPhone()));
        }
    }
}
