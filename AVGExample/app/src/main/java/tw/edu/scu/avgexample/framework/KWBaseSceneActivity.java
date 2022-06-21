package tw.edu.scu.avgexample.framework;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import android.util.Size;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.dialog.KWOptionDialog;
import tw.edu.scu.avgexample.framework.model.KWEventModel;
import tw.edu.scu.avgexample.framework.utility.KWLog;
import tw.edu.scu.avgexample.framework.utility.KWScreenUtils;
import tw.edu.scu.avgexample.framework.utility.KWUtils;
import tw.edu.scu.avgexample.sample.SampleMenuMainActivity;

public class KWBaseSceneActivity extends KWBaseFullScreenActivity {

    private boolean isSceneInitialize = false;

    private FrameLayout containerFrameLayout;
    private FrameLayout backgroundFrameLayout;
    private FrameLayout characterFrameLayout;

    public ImageView backgroundImageView;

    public ImageView characterLeftImageView;
    public ImageView characterCenterImageView;
    public ImageView characterRightImageView;

    public ImageView pictureImageView;

    public ArrayList<ImageView> characterImageViewArrayList = new ArrayList<>();

    protected KWEventManager eventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //隱藏ActionBar
        getSupportActionBar().hide();

        //確認當前場竟是否是第一次被載入，如果是的話，則執行相關的初始話動作
        //之所以不寫在onCreate是為了保留其他開發人員的xml layout彈性空間
        //避免不同於正常開發流程的寫作習慣
        if (!this.isSceneInitialize) {

            this.containerFrameLayout = findViewById(R.id.containerFrameLayout);

            //如果containerFrameLayout不存在，則不做任何事情，相當於是一般的Activity
            if (this.containerFrameLayout == null) {
                KWLog.d("[錯誤] 找不到 containerFrameLayout，無法初始化預設場景的資源");
                return;
            }

            if (!initializeContainer()) {
                KWLog.d("[錯誤] 找不到當前 Activity 的 RootView 參數，無法初始話預設場景的資源");
                return;
            }

            this.isSceneInitialize = true;

            this.eventManager = new KWEventManager(this);

            updateManager();

            //為了避免Child Class沒有在initializeManager中，初始化Manager，所以這邊要處理防呆
            if (this.eventManager == null) {
                this.eventManager = new KWEventManager(this);
            }

            initializeBackgroundLayer();
            initializeCharacterLayer();
            initializeEventPictureLayer();
            initializeListener();
            initializeEvent();
            initializeExitListener();
        }
    }

    //提供給Child Class作為覆寫之用途
    protected void updateManager() {

    }

    private boolean initializeContainer() {

        int constraintLayoutWidthPixel = KWScreenUtils.dpToPx(this, KWAppConstants.DEFAULT_GAME_CONTAINER_WIDTH_DP);
        int constraintLayoutHeightPixel = KWScreenUtils.dpToPx(this, KWAppConstants.DEFAULT_GAME_CONTAINER_HEIGHT_DP);

        ConstraintLayout constraintLayout = (ConstraintLayout)KWUtils.getRootViewFromActivity(this);

        if (constraintLayout == null) {
            return false;
        }

        ConstraintLayout.LayoutParams containerConstraintLayoutParams = new ConstraintLayout.LayoutParams(constraintLayoutWidthPixel, constraintLayoutHeightPixel);

        this.containerFrameLayout.setLayoutParams(containerConstraintLayoutParams);

        Size size = KWScreenUtils.getDeviceSize(this);
        KWLog.d("[資訊] 當前裝置寬為： " + size.getWidth() + ", 高為：" + size.getHeight());

        float factor = KWScreenUtils.getFullScreenScaleFactor(this);
        KWLog.d("[資訊] 寬高調整係數為：" + factor);

        this.containerFrameLayout.setScaleX(factor);
        this.containerFrameLayout.setScaleY(factor);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(containerFrameLayout.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        constraintSet.connect(containerFrameLayout.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        constraintSet.setHorizontalBias(containerFrameLayout.getId(), 0.5f);
        constraintSet.connect(containerFrameLayout.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        constraintSet.connect(containerFrameLayout.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        constraintSet.setVerticalBias(containerFrameLayout.getId(), 0.5f);

        constraintSet.applyTo(constraintLayout);

        return true;
    }

    private void initializeBackgroundLayer() {

        //建立背景圖層
        this.backgroundFrameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams backgroundFrameLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        this.backgroundFrameLayout.setLayoutParams(backgroundFrameLayoutParams);

        //建立背景
        FrameLayout.LayoutParams backgroundImageViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);

        this.backgroundImageView = new ImageView(this);
        this.backgroundImageView.setLayoutParams(backgroundImageViewParams);
        this.backgroundImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.backgroundFrameLayout.addView(this.backgroundImageView);

        this.containerFrameLayout.addView(this.backgroundFrameLayout, 0);
    }

    private void initializeCharacterLayer() {

        //建立角色圖層
        int dialogHeightPixel = KWScreenUtils.dpToPx(this, KWAppConstants.DEFAULT_DIALOG_HEIGHT_DP);

        this.characterFrameLayout = new FrameLayout(this);

        FrameLayout.LayoutParams characterFrameLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        characterFrameLayoutParams.setMargins(0,0,0, dialogHeightPixel);

        this.characterFrameLayout.setLayoutParams(characterFrameLayoutParams);

        //宣告通用的角色元件常數
        int characterHeightPixel = KWScreenUtils.dpToPx(this, KWAppConstants.DEFAULT_CHARACTER_HEIGHT_DP);
        int characterMarginOffsetPixel = KWScreenUtils.dpToPx(this, KWAppConstants.DEFAULT_CHARACTER_MARGIN_OFFSET);

        //建立左側的角色
        FrameLayout.LayoutParams characterLeftImageViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, characterHeightPixel, Gravity.BOTTOM | Gravity.CENTER);
        characterLeftImageViewParams.setMargins(0,0,characterMarginOffsetPixel,0);

        this.characterLeftImageView = new ImageView(this);
        this.characterLeftImageView.setLayoutParams(characterLeftImageViewParams);
        this.characterLeftImageView.setScaleType(ImageView.ScaleType.FIT_END);
        this.characterFrameLayout.addView(this.characterLeftImageView);

        //建立中間的角色
        FrameLayout.LayoutParams characterCenterImageViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, characterHeightPixel, Gravity.BOTTOM | Gravity.CENTER);
        characterCenterImageViewParams.setMargins(0,0,0,0);

        this.characterCenterImageView = new ImageView(this);
        this.characterCenterImageView.setLayoutParams(characterCenterImageViewParams);
        this.characterCenterImageView.setScaleType(ImageView.ScaleType.FIT_END);
        this.characterFrameLayout.addView(this.characterCenterImageView);

        //建立右側的角色
        FrameLayout.LayoutParams characterRightImageViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, characterHeightPixel, Gravity.BOTTOM | Gravity.CENTER);
        characterRightImageViewParams.setMargins(characterMarginOffsetPixel,0,0,0);

        this.characterRightImageView = new ImageView(this);
        this.characterRightImageView.setLayoutParams(characterRightImageViewParams);
        this.characterRightImageView.setScaleType(ImageView.ScaleType.FIT_END);
        this.characterFrameLayout.addView(this.characterRightImageView);

        this.containerFrameLayout.addView(this.characterFrameLayout);

        //加入至ArrayList中以方便管理
        this.characterImageViewArrayList.add(this.characterLeftImageView);
        this.characterImageViewArrayList.add(this.characterCenterImageView);
        this.characterImageViewArrayList.add(this.characterRightImageView);
    }

    private void initializeEventPictureLayer() {

        //建立放置於中間的事件圖片
        FrameLayout.LayoutParams characterCenterImageViewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        characterCenterImageViewParams.setMargins(20,20,20,20);

        this.pictureImageView = new ImageView(this);
        this.pictureImageView.setLayoutParams(characterCenterImageViewParams);
        this.pictureImageView.setPadding(20,20,20,20);
        this.pictureImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.containerFrameLayout.addView(this.pictureImageView);
    }

    protected void initializeListener() {
        this.eventManager.setPlayListener(new KWEventManager.KWEventManagerPlayListener() {
            @Override
            public void willPlayEvent(String eventIdentifier, int eventIndex, KWEventModel eventModel) {
                KWBaseSceneActivity.this.willPlayEvent(eventIdentifier, eventIndex, eventModel);
            }

            @Override
            public void didPlayEvent(String eventIdentifier, int eventIndex, KWEventModel eventModel) {
                KWBaseSceneActivity.this.didPlayEvent(eventIdentifier, eventIndex, eventModel);
            }
        });

        this.eventManager.setFinishListener(new KWEventManager.KWEventManagerFinishListener() {
            @Override
            public void willFinishAllEvent(String eventIdentifier) {
                KWBaseSceneActivity.this.willFinishAllEvent(eventIdentifier);
            }

            @Override
            public void didFinishAllEvent(String eventIdentifier) {
                KWBaseSceneActivity.this.didFinishAllEvent(eventIdentifier);
            }
        });

        this.eventManager.setOptionListener(new KWOptionDialog.KWOptionDialogOptionListener() {
            @Override
            public void onOptionSelected(String identifier, int index) {
                KWBaseSceneActivity.this.onOptionSelected(identifier, index);
            }
        });
    }

    protected void initializeEvent() {

    }

    protected void initializeExitListener() {
        super.setOnExitListener(new KWOnExitListener() {
            @Override
            public void onExit() {
                switchSceneActivity(SampleMenuMainActivity.class);
            }
        });
    }

    protected void willPlayEvent(String eventIdentifier, int eventIndex, KWEventModel eventModel) {
        KWLog.d("[資訊] 事件合集: " + eventIdentifier + " 中的事件（" + eventIndex + "）即將播放...");

    }

    protected void didPlayEvent(String eventIdentifier, int eventIndex, KWEventModel eventModel) {
        KWLog.d("[資訊] 事件合集: " + eventIdentifier + " 中的事件（" + eventIndex + "）已播放");

    }

    protected void willFinishAllEvent(String eventIdentifier) {
        KWLog.d("[資訊] 事件合集: " + eventIdentifier + " 中的所有事件即將播完...");

    }

    protected void didFinishAllEvent(String eventIdentifier) {
        KWLog.d("[資訊] 事件合集: " + eventIdentifier + " 中的所有事件已全數播放完畢");
    }

    protected void onOptionSelected(String identifier, int index) {
        KWLog.d("[資訊] [分歧選項 " + identifier + "] 選擇了第 " + index + " 的選項");
    }

    //http://www.zoftino.com/android-activity-transition-animation-examples
    protected void switchSceneActivity(final Class targetClass) {
        switchSceneActivity(targetClass, R.anim.kw_scene_animation_fade_in, R.anim.kw_scene_animation_fade_out, 250);
    }

    protected void switchSceneActivity(final Class targetClass, boolean animatoin) {
        if (animatoin) {
            switchSceneActivity(targetClass);
        }
        else {
            switchSceneActivity(targetClass, false, 250);
        }
    }

    protected void switchSceneActivity(final Class targetClass, int delay) {
        switchSceneActivity(targetClass, R.anim.kw_scene_animation_fade_in, R.anim.kw_scene_animation_fade_out, delay);
    }

    protected void switchSceneActivity(final Class targetClass, boolean animatoin, int delay) {
        switchSceneActivity(targetClass, R.anim.kw_scene_animation_no_effect, R.anim.kw_scene_animation_no_effect, delay);
    }

    protected void switchSceneActivity(final Class targetClass, int enterAnimationResouseId, int exitAnimationResouseId) {
        switchSceneActivity(targetClass, enterAnimationResouseId, exitAnimationResouseId, 250);

    }

    protected void switchSceneActivity(final Class targetClass, final int enterAnimationResouseId, final int exitAnimationResouseId, int delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                KWUtils.switchActivity(KWBaseSceneActivity.this, targetClass);

                if (enterAnimationResouseId != -1 && exitAnimationResouseId != -1) {
                    overridePendingTransition(enterAnimationResouseId, exitAnimationResouseId);
                }

            }
        }, delay);
    }

    @Override
    protected void onDetectedGesture(int type) {
        super.onDetectedGesture(type);
    }


}