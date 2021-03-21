package com.example.onboardingnavdrawer.view;

import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.onboardingnavdrawer.R;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class MenuFragment extends Fragment {

    private ShowcaseView showcaseView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        container.removeAllViews();
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        Target target = new Target() {
            @Override
            public Point getPoint() {
                return new Point(700, 1400);
            }
        };

        showcaseView = new ShowcaseView.Builder(getActivity())
                .setTarget(new ViewTarget(view.findViewById(R.id.button1)))
                .setContentTitle("This is title").hideOnTouchOutside()
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"Clicked",Toast.LENGTH_LONG);
                    }
                })
                .build();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
