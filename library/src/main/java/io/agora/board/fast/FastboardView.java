package io.agora.board.fast;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.herewhite.sdk.WhiteboardView;
import com.herewhite.sdk.domain.RoomPhase;

import io.agora.board.fast.library.R;
import io.agora.board.fast.model.FastSdkOptions;
import io.agora.board.fast.model.FastStyle;
import io.agora.board.fast.ui.LoadingLayout;
import io.agora.board.fast.ui.RoomController;

/**
 * @author fenglibin
 */
public class FastboardView extends FrameLayout implements BoardStateObserver {
    WhiteboardView whiteboardView;
    RoomController roomController;
    LoadingLayout loadingLayout;

    FastContext fastContext;
    FastSdk fastSdk;

    public FastboardView(@NonNull Context context) {
        this(context, null);
    }

    public FastboardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FastboardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupFastContext(context);
        setupView(context);
        setupStyle(context, attrs, defStyleAttr);
    }

    private void setupFastContext(@NonNull Context context) {
        fastContext = new FastContext(context);
    }

    private void setupView(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_fast_board_view, this, true);
        whiteboardView = root.findViewById(R.id.white_board_view);
        roomController = root.findViewById(R.id.fast_room_controller);
        loadingLayout = root.findViewById(R.id.fast_loading_layout);
    }

    private void setupStyle(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FastboardView, defStyleAttr, R.style.DefaultFastBoardView);
        int mainColor = a.getColor(R.styleable.FastboardView_fbv_main_color, Color.parseColor("#3381FF"));
        boolean darkMode = a.getBoolean(R.styleable.FastboardView_fbv_dark_mode, false);
        a.recycle();

        FastStyle fastStyle = new FastStyle();
        fastStyle.setMainColor(mainColor);
        fastStyle.setDarkMode(darkMode);
        fastContext.initFastStyle(fastStyle);

        roomController.updateFastStyle(fastStyle);
        loadingLayout.updateFastStyle(fastStyle);
    }

    @Override
    public void onRoomPhaseChanged(RoomPhase roomPhase) {
        switch (roomPhase) {
            case connecting:
            case reconnecting:
                loadingLayout.setShown(true);
                break;
            case connected:
                loadingLayout.setShown(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void onGlobalStyleChanged(FastStyle fastStyle) {
        updateStyle(fastStyle);
    }

    private void updateStyle(FastStyle fastStyle) {
        whiteboardView.setBackgroundColor(ContextCompat.getColor(
                getContext(),
                R.color.fast_day_night_bg
        ));
        roomController.updateFastStyle(fastStyle);
        loadingLayout.updateFastStyle(fastStyle);
    }

    public FastStyle getFastStyle() {
        return fastContext.getFastStyle();
    }

    public FastSdk obtainFastSdk(FastSdkOptions options) {
        if (fastSdk == null) {
            fastSdk = new FastSdk(this);
            fastSdk.initSdk(new FastSdkOptions(options.getAppId()));
            onFastSdkCreated();
        }
        return fastSdk;
    }

    private void onFastSdkCreated() {
        fastSdk.registerObserver(this);
        roomController.attachSdk(fastSdk);
    }
}
