package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form.selectCompany;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentSelectCompanyItemBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.OnSelectItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class SelectCompanyFragmentAdapter extends ListAdapter<Company, SelectCompanyFragmentAdapter.ViewHolder> {

    private OnSelectItemClickListener onSelectItemClickListener;

    public SelectCompanyFragmentAdapter() {
        super(new DiffUtil.ItemCallback<Company>() {
            @Override
            public boolean areItemsTheSame(@NonNull Company oldItem, @NonNull Company newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Company oldItem, @NonNull Company newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName()) &&
                        oldItem.getPhone() == newItem.getPhone() &&
                        TextUtils.equals(oldItem.getEmail(), newItem.getEmail()) &&
                        TextUtils.equals(oldItem.getLogo(), newItem.getLogo()) &&
                        TextUtils.equals(oldItem.getAddress(), newItem.getAddress());
            }
        });
    }

    @NonNull
    @Override
    public SelectCompanyFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentSelectCompanyItemBinding b = FragmentSelectCompanyItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SelectCompanyFragmentAdapter.ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectCompanyFragmentAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItem(position).getId();
    }

    @Override
    public Company getItem(int position) {
        return super.getItem(position);
    }

    public void setOnSelectItemClickListener(OnSelectItemClickListener onSelectItemClickListener) {
        this.onSelectItemClickListener = onSelectItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        FragmentSelectCompanyItemBinding b;

        public ViewHolder(@NonNull FragmentSelectCompanyItemBinding b) {
            super(b.getRoot());
            this.b = b;

            b.CLayout.setOnClickListener(v -> onSelectItemClickListener.onItemClick(getAdapterPosition()));
        }

        public void bind(Company company) {
            b.lblName.setText(company.getName());
            b.lblEmail.setText(company.getEmail());
            b.lblPhonenumber.setText(String.valueOf(company.getPhone()));
            b.lblAddress.setText(company.getAddress());
        }
    }
}
