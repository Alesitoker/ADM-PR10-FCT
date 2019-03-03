package com.iessaladillo.alejandro.adm_pr10_fct.ui.company;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentCompaniesItemBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ListCompaniesFragmentAdapter extends ListAdapter<Company, ListCompaniesFragmentAdapter.ViewHolder> {

    protected ListCompaniesFragmentAdapter() {
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
    public ListCompaniesFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentCompaniesItemBinding b = FragmentCompaniesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListCompaniesFragmentAdapter.ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCompaniesFragmentAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItem(position).getId();
    }

    @Override
    protected Company getItem(int position) {
        return super.getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        FragmentCompaniesItemBinding b;

        public ViewHolder(@NonNull FragmentCompaniesItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        public void bind(Company company) {
            b.lblName.setText(company.getName());
            b.lblEmail.setText(company.getEmail());
            b.lblPhonenumber.setText(String.valueOf(company.getPhone()));
            b.lblAddress.setText(company.getAddress());
        }
    }
}
