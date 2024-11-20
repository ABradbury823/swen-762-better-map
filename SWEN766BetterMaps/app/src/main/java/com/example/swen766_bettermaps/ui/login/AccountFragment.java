package com.example.swen766_bettermaps.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swen766_bettermaps.R;
import com.example.swen766_bettermaps.data.RITUser;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView accountInfoTextView = getActivity().findViewById(R.id.account_info_text);

        // Retrieve RITUser from Intent
        RITUser ritUser = getActivity().getIntent().getParcelableExtra("RIT_USER");  // For Parcelable
        if (ritUser != null) {
            String displayText = "Welcome, " + ritUser.getFirst_name() +
                    "\nRIT ID: " + ritUser.getRIT_id() +
                    "\nEmail: " + ritUser.getEmail();
            accountInfoTextView.setText(displayText);
        }

        // Implement sign-out functionality
        getActivity().findViewById(R.id.sign_out_button).setOnClickListener(v -> {
            // Logic for signing out and returning to the login screen
            getActivity().finish();
        });
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        // TODO: Use the ViewModel
    }

}