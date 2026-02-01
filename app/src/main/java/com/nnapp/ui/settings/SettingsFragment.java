package com.nnapp.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.nnapp.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                           ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize settings
        initSettings();

        return root;
    }

    private void initSettings() {
        // TODO: Implement settings
        // - Server configuration
        // - App preferences
        // - User settings
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
