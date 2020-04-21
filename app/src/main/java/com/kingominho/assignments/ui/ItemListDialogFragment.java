package com.kingominho.assignments.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.kingominho.assignments.R;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link ItemListDialogFragment.Listener}.</p>
 */
public class ItemListDialogFragment extends BottomSheetDialogFragment {

    private static final String TAG = "ItemListDialog: ";
    // TODO: Customize parameter argument names
    private static final String ARG_SELECTED_RADIO = "selected_radio";
    private Listener mListener;
    private int selectedRadio;


    // TODO: Customize parameters
    public static ItemListDialogFragment newInstance(int selectedRadio) {
        Log.d(TAG, "newInstance: new ItemListDialogFragment created. selectedRadio: " + selectedRadio);
        final ItemListDialogFragment fragment = new ItemListDialogFragment();
        fragment.selectedRadio = selectedRadio;
        final Bundle args = new Bundle();
        args.putInt(ARG_SELECTED_RADIO, selectedRadio);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        RadioButton radioShowAll = view.findViewById(R.id.radio_show_all);
        RadioButton radioShowCompleted = view.findViewById(R.id.radio_show_completed);
        RadioButton radioShowRemaining = view.findViewById(R.id.radio_show_remaining);

        switch (selectedRadio) {
            case R.id.radio_show_all:
                radioShowAll.setChecked(true);
                break;
            case R.id.radio_show_completed:
                radioShowCompleted.setChecked(true);
                break;
            case R.id.radio_show_remaining:
                radioShowRemaining.setChecked(true);
                break;
        }

        radioShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                mListener.onItemClicked(v, checked);
            }
        });

        radioShowCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                mListener.onItemClicked(v, checked);
            }
        });

        radioShowRemaining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                mListener.onItemClicked(v, checked);
            }
        });
        /*
        public void onRadioButtonClicked(View view) {
    // Is the button now checked?
    boolean checked = ((RadioButton) view).isChecked();

    // Check which radio button was clicked
    switch(view.getId()) {
        case R.id.radio_pirates:
            if (checked)
                // Pirates are the best
            break;
        case R.id.radio_ninjas:
            if (checked)
                // Ninjas rule
            break;
    }
}
         */
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Fragment parent = getParentFragment();
        if (parent != null) {
            mListener = (Listener) parent;
        } else {
            mListener = (Listener) context;
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public interface Listener {
        void onItemClicked(View v, boolean isChecked);
    }


}
