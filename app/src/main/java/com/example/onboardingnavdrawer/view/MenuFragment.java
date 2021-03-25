package com.example.onboardingnavdrawer.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.elconfidencial.bubbleshowcase.BubbleShowCase;
import com.elconfidencial.bubbleshowcase.BubbleShowCaseBuilder;
import com.elconfidencial.bubbleshowcase.BubbleShowCaseListener;
import com.elconfidencial.bubbleshowcase.BubbleShowCaseSequence;
import com.example.onboardingnavdrawer.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuFragment extends Fragment {

    private BubbleShowCase bubbleShowCase;
    private View view;
    private String userId;
    private Button button1;
    private Button button2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        container.removeAllViews();
        view = inflater.inflate(R.layout.fragment_menu, container, false);

        Bundle bundle = this.getArguments();
        if (!bundle.isEmpty()) {
            userId = bundle.getString("UserId");

        }


//        bubbleShowCase = new BubbleShowCaseBuilder(getActivity())
//                .title("First Button").description("Tap here")
//                .arrowPosition(BubbleShowCase.ArrowPosition.TOP)
//                .targetView(view.findViewById(R.id.button1))
//                .showOnce(userId)
//                .show();

        if (!getDefaults(userId, getActivity())) {
            setUpListeners();
        }


        return view;
    }

    private void setUpListeners() {
        getButton1ShowCaseBuilder().show();
        setDefaults(userId,true,getActivity());
        //getSequence().show();


    }

    private BubbleShowCaseBuilder getButton1ShowCaseBuilder() {
        return new BubbleShowCaseBuilder(getActivity())
                .title("First Button").description("Tap here")
                .arrowPosition(BubbleShowCase.ArrowPosition.TOP)
                .listener(new BubbleShowCaseListener() {
                    @Override
                    public void onTargetClick(@NotNull BubbleShowCase bubbleShowCase) {
                        getButton2ShowCaseBuilder().show();

                    }

                    @Override
                    public void onCloseActionImageClick(@NotNull BubbleShowCase bubbleShowCase) {
                        getButton1ShowCaseBuilder().show();

                    }

                    @Override
                    public void onBackgroundDimClick(@NotNull BubbleShowCase bubbleShowCase) {

                    }

                    @Override
                    public void onBubbleClick(@NotNull BubbleShowCase bubbleShowCase) {

                    }
                })
                .targetView(view.findViewById(R.id.button1));

    }

    private BubbleShowCaseBuilder getButton2ShowCaseBuilder() {
        return new BubbleShowCaseBuilder(getActivity())
                .title("Second Button").description("Tap here")
                .arrowPosition(BubbleShowCase.ArrowPosition.TOP)
                .targetView(view.findViewById(R.id.button2));

    }

    private BubbleShowCaseSequence getSequence() {

        List<BubbleShowCaseBuilder> builders = new ArrayList<>();
        builders.add(getButton1ShowCaseBuilder());
        builders.add(getButton2ShowCaseBuilder());

        return new BubbleShowCaseSequence().addShowCases(builders);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setDefaults(String key, boolean value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private boolean getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parentViewGroup = (ViewGroup) view.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }
    }
}
