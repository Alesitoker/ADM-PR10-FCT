package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentStudentsItemBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ListStudentsFragmentAdapter extends ListAdapter<StudentCompany, ListStudentsFragmentAdapter.ViewHolder> {

    protected ListStudentsFragmentAdapter() {
        super(new DiffUtil.ItemCallback<StudentCompany>() {
            @Override
            public boolean areItemsTheSame(@NonNull StudentCompany oldItem, @NonNull StudentCompany newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull StudentCompany oldItem, @NonNull StudentCompany newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName()) &&
                        oldItem.getPhone() == newItem.getPhone() &&
                        TextUtils.equals(oldItem.getEmail(), newItem.getEmail()) &&
                        TextUtils.equals(oldItem.getCompanyName(), newItem.getCompanyName()) &&
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
    protected StudentCompany getItem(int position) {
        return super.getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        FragmentStudentsItemBinding b;

        public ViewHolder(@NonNull FragmentStudentsItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        public void bind(StudentCompany student) {
            b.lblName.setText(student.getName());
            b.lblEmail.setText(student.getEmail());
            b.lblPhonenumber.setText(String.valueOf(student.getPhone()));
            b.lblcompanyName.setText(student.getCompanyName());
        }
    }
}
