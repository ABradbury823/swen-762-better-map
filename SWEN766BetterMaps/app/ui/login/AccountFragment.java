package com.example.swen766_bettermaps.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
        // Inflate the fragment's layout
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Retrieve the bundle passed from the previous fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            RITUser ritUser = bundle.getParcelable("RIT_USER");

            if (ritUser != null) {
                TextView accountInfoTextView = view.findViewById(R.id.account_info_text);
                if (accountInfoTextView != null) {
                    String displayText = "Welcome, " + ritUser.getFirst_name() +
                            "\nRIT ID: " + ritUser.getRIT_id() +
                            "\nEmail: " + ritUser.getEmail();
                    accountInfoTextView.setText(displayText);
                } else {
                    Log.e("AccountFragment", "TextView not found in the layout.");
                }
            } else {
                Log.e("AccountFragment", "RITUser object is null.");
            }
        } else {
            Log.e("AccountFragment", "Bundle is null.");
        }

        // Handle sign-out functionality
        View signOutButton = view.findViewById(R.id.sign_out_button);
        if (signOutButton != null) {
            signOutButton.setOnClickListener(v -> {
                // Clear any session data if applicable
                clearUserSession();

                // Navigate back to the LoginFragment
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_accountFragment_to_loginFragment);
            });
        } else {
            Log.e("AccountFragment", "Sign-out button not found in the layout.");
        }
    }

    private void clearUserSession() {
        // Add logic here to clear user session, such as clearing SharedPreferences or any cache
        Log.d("AccountFragment", "User session cleared.");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
    }
}