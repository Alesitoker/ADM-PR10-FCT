package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentVisitsItemBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.OnSelectItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ListVisitsFragmentAdapter extends ListAdapter<VisitStudent, ListVisitsFragmentAdapter.ViewHolder> {

    private OnSelectItemClickListener onSelectItemClickListener;

    protected ListVisitsFragmentAdapter() {
        super(new DiffUtil.ItemCallback<VisitStudent>() {
            @Override
            public boolean areItemsTheSame(@NonNull VisitStudent oldItem, @NonNull VisitStudent newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull VisitStudent oldItem, @NonNull VisitStudent newItem) {
                return TextUtils.equals(oldItem.getDay(), newItem.getDay()) &&
                        TextUtils.equals(oldItem.getStartTime(), newItem.getStartTime()) &&
                        TextUtils.equals(oldItem.getEndTime(), newItem.getEndTime());
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentVisitsItemBinding b = FragmentVisitsItemBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
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
    protected VisitStudent getItem(int position) {
        return super.getItem(position);
    }

    public void setOnSelectItemClickListener(OnSelectItemClickListener onSelectItemClickListener) {
        this.onSelectItemClickListener = onSelectItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        FragmentVisitsItemBinding b;

        public ViewHolder(@NonNull FragmentVisitsItemBinding b) {
            super(b.getRoot());
            this.b = b;

            b.CLayout.setOnClickListener(v -> onSelectItemClickListener.onItemClick(getAdapterPosition()));
        }

        public void bind(VisitStudent visit) {
            b.lblDay.setText(visit.getDay());
            b.lblTime.setText(b.getRoot().getResources().
                    getString(R.string.lblTime, visit.getStartTime(), visit.getEndTime()));
            b.lblStudentName.setText(visit.getStudentName());
        }
    }
}
