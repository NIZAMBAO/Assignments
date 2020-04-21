package com.kingominho.assignments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.kingominho.assignments.R;
import com.kingominho.assignments.entity.Assignment;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {
    private List<Assignment> mAssignmentList;
    private OnClickListener mListener;

    public AssignmentAdapter(List<Assignment> mAssignmentList) {
        this.mAssignmentList = mAssignmentList;
    }

    public void setOnClickListener(OnClickListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        holder.bindAssignment(mAssignmentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mAssignmentList.size();
    }

    class AssignmentViewHolder extends RecyclerView.ViewHolder {

        private TextView textTopic, textSubject, textDueDate;
        private MaterialCardView containerView;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            containerView = itemView.findViewById(R.id.container);
            textTopic = itemView.findViewById(R.id.text_topic);
            textSubject = itemView.findViewById(R.id.text_subject);
            textDueDate = itemView.findViewById(R.id.text_due_date);
        }

        public void bindAssignment(Assignment assignment) {
            containerView.setCardBackgroundColor(assignment.getColorCode());
            textTopic.setText(assignment.getTopic());
            textSubject.setText(assignment.getSubject() + "");
            textDueDate.setText(assignment.getDueDate());
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.OnClick(assignment, getAdapterPosition());
                    }
                }
            });
        }

        public TextView getTextTopic() {
            return textTopic;
        }

        public TextView getTextSubject() {
            return textSubject;
        }

        public TextView getTextDueDate() {
            return textDueDate;
        }

        public MaterialCardView getContainerView() {
            return containerView;
        }
    }

    public interface OnClickListener {
        void OnClick(Assignment assignment, int position);
    }
}
