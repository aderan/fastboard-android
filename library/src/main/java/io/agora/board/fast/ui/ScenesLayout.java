package io.agora.board.fast.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.herewhite.sdk.domain.Scene;
import com.herewhite.sdk.domain.SceneState;

import io.agora.board.fast.FastRoom;
import io.agora.board.fast.R;
import io.agora.board.fast.model.FastStyle;

/**
 * @author fenglibin
 */
public class ScenesLayout extends LinearLayout implements RoomController {
    private ImageView scenePrev;
    private ImageView sceneNext;
    private ImageView sceneAdd;
    private TextView sceneIndicator;

    private int sceneIndex;
    private int sceneSize;
    private String scenePath;
    private FastRoom fastRoom;

    private final OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == scenePrev) {
                prevScene();
            } else if (v == sceneNext) {
                nextScene();
            } else if (v == sceneAdd) {
                addScene();
            }
        }
    };

    public ScenesLayout(@NonNull Context context) {
        this(context, null);
    }

    public ScenesLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScenesLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        setupView(context);
    }

    private void prevScene() {
        fastRoom.getRoom().setSceneIndex(sceneIndex - 1, null);
    }

    private void nextScene() {
        fastRoom.getRoom().setSceneIndex(sceneIndex + 1, null);
    }

    private void addScene() {
        String dir = scenePath.substring(0, scenePath.lastIndexOf("/"));
        int targetIndex = sceneIndex + 1;

        Scene scene = new Scene();
        fastRoom.getRoom().putScenes(dir, new Scene[]{scene}, targetIndex);
        fastRoom.getRoom().setSceneIndex(targetIndex, null);
    }

    private void setupView(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_scenes, this, true);
        scenePrev = root.findViewById(R.id.tool_scene_prev);
        sceneNext = root.findViewById(R.id.tool_scene_next);
        sceneAdd = root.findViewById(R.id.tool_scene_add);
        sceneIndicator = root.findViewById(R.id.tool_scene_indicator);

        scenePrev.setOnClickListener(onClickListener);
        sceneNext.setOnClickListener(onClickListener);
        sceneAdd.setOnClickListener(onClickListener);
    }

    @Override
    public void setFastRoom(FastRoom fastRoom) {
        this.fastRoom = fastRoom;
    }

    public void setFastStyle(FastStyle fastStyle) {
        setBackground(ResourceFetcher.get().getLayoutBackground(fastStyle.isDarkMode()));
        sceneNext.setImageTintList(ResourceFetcher.get().getIconColor(fastStyle.isDarkMode(), true));
        scenePrev.setImageTintList(ResourceFetcher.get().getIconColor(fastStyle.isDarkMode(), true));
        sceneAdd.setImageTintList(ResourceFetcher.get().getIconColor(fastStyle.isDarkMode()));
        sceneIndicator.setTextColor(ResourceFetcher.get().getTextColor(fastStyle.isDarkMode()));
    }

    @Override
    public void updateSceneState(SceneState sceneState) {
        sceneIndex = sceneState.getIndex();
        scenePath = sceneState.getScenePath();
        sceneSize = sceneState.getScenes().length;

        updateUI();
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        int sceneNo = sceneIndex + 1;
        sceneIndicator.setText(sceneNo + "/" + sceneSize);
        sceneNext.setEnabled(sceneNo < sceneSize);
        scenePrev.setEnabled(sceneIndex > 0);
    }
}
