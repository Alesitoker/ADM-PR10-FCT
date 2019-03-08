package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.form.selectStudent;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentSelectCompanyItemBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentSelectStudentBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentSelectStudentItemBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentStudentsItemBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.OnSelectItemClickListener;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.student.list.ListStudentsFragmentAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SelectStudentFragmentAdapter extends ListAdapter<StudentCompany, SelectStudentFragmentAdapter.ViewHolder> {

    private OnSelectItemClickListener onSelectItemClickListener;

    public SelectStudentFragmentAdapter() {
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
    public SelectStudentFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentSelectStudentItemBinding b = FragmentSelectStudentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SelectStudentFragmentAdapter.ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectStudentFragmentAdapter.ViewHolder holder, int position) {
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

    public void setOnSelectItemClickListener(OnSelectItemClickListener onSelectItemClickListener) {
        this.onSelectItemClickListener = onSelectItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        FragmentSelectStudentItemBinding b;

        public ViewHolder(@NonNull FragmentSelectStudentItemBinding b) {
            super(b.getRoot());
            this.b = b;

            b.CLayout.setOnClickListener(v -> onSelectItemClickListener.onItemClick(getAdapterPosition()));
        }

        public void bind(StudentCompany student) {
            b.lblName.setText(student.getName());
            b.lblEmail.setText(student.getEmail());
            b.lblPhonenumber.setText(String.valueOf(student.getPhone()));
            b.lblcompanyName.setText(student.getCompanyName());
        }
    }
}
