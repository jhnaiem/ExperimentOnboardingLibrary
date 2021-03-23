package com.example.onboardingnavdrawer.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.elconfidencial.bubbleshowcase.BubbleShowCase;
import com.elconfidencial.bubbleshowcase.BubbleShowCaseBuilder;
import com.example.onboardingnavdrawer.R;

public class MenuFragment extends Fragment {

    private BubbleShowCase bubbleShowCase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        container.removeAllViews();
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        String  userId = getArguments().getString("UserId");

        bubbleShowCase = new BubbleShowCaseBuilder(getActivity())
                .title("First Button").description("Tap here")
                .arrowPosition(BubbleShowCase.ArrowPosition.TOP)
                .targetView(view.findViewById(R.id.button1))
                .showOnce(userId)
                .show();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
