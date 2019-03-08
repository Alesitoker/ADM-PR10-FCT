package com.iessaladillo.alejandro.adm_pr10_fct.ui.nextVisits;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentNextvisitsItemBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentVisitsItemBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.OnSelectItemClickListener;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.list.ListVisitsFragmentAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NextVisitsFragmentAdapter extends ListAdapter<VisitStudent, NextVisitsFragmentAdapter.ViewHolder> {

    private OnSelectItemClickListener onSelectItemClickListener;

    protected NextVisitsFragmentAdapter() {
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
    public NextVisitsFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentNextvisitsItemBinding b = FragmentNextvisitsItemBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);
        return new NextVisitsFragmentAdapter.ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull NextVisitsFragmentAdapter.ViewHolder holder, int position) {
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

        FragmentNextvisitsItemBinding b;

        public ViewHolder(@NonNull FragmentNextvisitsItemBinding b) {
            super(b.getRoot());
            this.b = b;

            b.CLayout.setOnClickListener(v -> onSelectItemClickListener.onItemClick(getAdapterPosition()));
        }

        public void bind(VisitStudent visit) {
            if (visit.getDay() != null)
                b.lblDay.setText(visit.getDay());
            Log.d("agua", visit.getStudentName());
            b.lblStudentName.setText(visit.getStudentName());
        }
    }
}
