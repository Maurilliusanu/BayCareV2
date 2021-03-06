package com.example.hariharsudan.bc.User.Login_Modules.Profile;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hariharsudan.bc.Adapter.AnimationUtils;
import com.example.hariharsudan.bc.Adapter.WHadapter;
import com.example.hariharsudan.bc.R;
import com.tistory.dwfox.dwrulerviewlibrary.utils.DWUtils;
import com.tistory.dwfox.dwrulerviewlibrary.view.DWRulerSeekbar;
import com.tistory.dwfox.dwrulerviewlibrary.view.ObservableHorizontalScrollView;
import com.tistory.dwfox.dwrulerviewlibrary.view.ScrollingValuePicker;


/**
 * Created by caik on 2016/9/25.
 */

public class BottomDialogHeight extends DialogFragment {

    private ScrollingValuePicker myScrollingValuePicker;
    private DWRulerSeekbar dwRulerSeekbar;

    private static final float MIN_VALUE = 96;
    private static final float MAX_VALUE = 308;
    private static final float LINE_RULER_MULTIPLE_SIZE = 5f;
    private TextView rulerText;
    public static TextView seekbarText;

    public static BottomDialogHeight newInstance() {

        Bundle args = new Bundle();

        BottomDialogHeight fragment = new BottomDialogHeight();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_bottom_height,container,false);
        AnimationUtils.slideToUp(view);

        myScrollingValuePicker = (ScrollingValuePicker) view.findViewById(R.id.myScrollingValuePicker);
        myScrollingValuePicker.setViewMultipleSize(LINE_RULER_MULTIPLE_SIZE);
        myScrollingValuePicker.setMaxValue(MIN_VALUE, MAX_VALUE);
        myScrollingValuePicker.setValueTypeMultiple(5);
        seekbarText = (TextView)view.findViewById(R.id.seekbarText);
        Button ok = (Button) view.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uheight.setText(WHadapter.getHeight());
                UserEditProf.setHeight(WHadapter.getHeight());


            }
        });
        myScrollingValuePicker.getScrollView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    myScrollingValuePicker.getScrollView().startScrollerTask();
                }
                return false;
            }
        });

        seekbarText.setText("Height : 30cm");

        myScrollingValuePicker.setOnScrollChangedListener(new ObservableHorizontalScrollView.OnScrollChangedListener() {

            @Override
            public void onScrollChanged(ObservableHorizontalScrollView view, int l, int t) {
            }

            @Override
            public void onScrollStopped(int l, int t) {
                seekbarText.setText("Height : " +
                        DWUtils.getValueAndScrollItemToCenter(myScrollingValuePicker.getScrollView()
                                , l
                                , t
                                , MAX_VALUE
                                , MIN_VALUE
                                , myScrollingValuePicker.getViewMultipleSize()) +"cm");

                int h = DWUtils.getValueAndScrollItemToCenter(myScrollingValuePicker.getScrollView()
                        , l
                        , t
                        , MAX_VALUE
                        , MIN_VALUE
                        , myScrollingValuePicker.getViewMultipleSize());

                System.out.println("asdf "+h);
                WHadapter.setHeight(String.valueOf(h));
            }
        });



        return view;
    }

}
