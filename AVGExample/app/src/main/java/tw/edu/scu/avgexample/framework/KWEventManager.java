package tw.edu.scu.avgexample.framework;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import tw.edu.scu.avgexample.framework.dialog.KWFullScreenMessageDialog;
import tw.edu.scu.avgexample.framework.dialog.KWMessageDialog;
import tw.edu.scu.avgexample.framework.dialog.KWOptionDialog;
import tw.edu.scu.avgexample.framework.library.KWSoundManager;
import tw.edu.scu.avgexample.framework.model.KWEventModel;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;
import tw.edu.scu.avgexample.framework.model.KWOptionEventModel;
import tw.edu.scu.avgexample.framework.model.KWPictureEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWEventCharacterUtils;
import tw.edu.scu.avgexample.framework.utility.KWLog;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;
import tw.edu.scu.avgexample.framework.utility.KWScreenUtils;

import static tw.edu.scu.avgexample.framework.KWAppConstants.DEFAULT_DIALOG_HEIGHT_DP;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_NO_BACKGROUND_MUSIC_ID;

public class KWEventManager {

    public interface KWEventManagerPlayListener {
        void willPlayEvent(String eventIdentifier, int eventIndex, KWEventModel eventModel);
        void didPlayEvent(String eventIdentifier, int eventIndex, KWEventModel eventModel);
    }

    public interface KWEventManagerFinishListener {
        void willFinishAllEvent(String eventIdentifier);
        void didFinishAllEvent(String eventIdentifier);
    }

    protected KWBaseSceneActivity sceneActivity;
    private ArrayList<KWEventModel> eventModelArrayList = new ArrayList<>();

    private KWEventManagerPlayListener playListener;
    private KWEventManagerFinishListener finishListener;
    private KWOptionDialog.KWOptionDialogOptionListener optionListener;

    private String eventIdentifier;
    private int eventIndex = 0;
    private boolean isPlaying = false;

    protected KWMessageDialog messageDialog;
    protected KWOptionDialog optionDialog;
    protected KWFullScreenMessageDialog fullScreenMessageDialog;

    public KWEventManager(KWBaseSceneActivity sceneActivity) {
        this.sceneActivity = sceneActivity;
        initMessageDialog();
        initFullScreenDialog();
    }

    //TODO: ??????KWBaseSceneActivity?????????????????????????????????????????????class???????????????xib layout
    //[Reference] https://www.cnblogs.com/angeldevil/archive/2012/03/31/2426242.html
    protected void initMessageDialog() {
        this.messageDialog = new KWMessageDialog(this.sceneActivity);
        this.messageDialog.getDialog().setCancelable(false);
        Window dialogWindow = messageDialog.getDialog().getWindow();

        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();

        //???????????????????????????????????????????????????????????????
        //layoutParams.width = KWScreenUtils.dpToPx(this.sceneActivity, KWScreenUtils.getScreenWidthInDPs(this.sceneActivity) * 0.8f);
        //layoutParams.height = KWScreenUtils.dpToPx(this.sceneActivity, KWScreenUtils.getScreenHeightInDPs(this.sceneActivity) * 0.25f);

        //?????????????????????????????????????????????????????????????????????????????????
        layoutParams.width = KWScreenUtils.dpToPx(this.sceneActivity, KWScreenUtils.getScreenWidthInDPs(this.sceneActivity));
        layoutParams.height = KWScreenUtils.dpToPx(this.sceneActivity, DEFAULT_DIALOG_HEIGHT_DP);

        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        dialogWindow.setAttributes(layoutParams);
    }

    protected void initOptionDialog(KWOptionEventModel optionEventModel) {
        this.optionDialog = null;
        this.optionDialog = new KWOptionDialog(this.sceneActivity, optionEventModel.getIdentifier());
        this.optionDialog.getDialog().setCancelable(false);
        this.optionDialog.setOnOptionSelectedListener(this.optionListener);

        Window dialogWindow = this.optionDialog.getDialog().getWindow();

        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();

        layoutParams.width = KWScreenUtils.dpToPx(this.sceneActivity, KWScreenUtils.getScreenWidthInDPs(this.sceneActivity));
        layoutParams.height = KWScreenUtils.dpToPx(this.sceneActivity, KWScreenUtils.getScreenHeightInDPs(this.sceneActivity));

        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(layoutParams);
    }

    protected void initFullScreenDialog() {
        this.fullScreenMessageDialog = new KWFullScreenMessageDialog(this.sceneActivity);
        this.fullScreenMessageDialog.getDialog().setCancelable(false);
        Window dialogWindow = this.fullScreenMessageDialog.getDialog().getWindow();

        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();

        layoutParams.width = KWScreenUtils.dpToPx(this.sceneActivity, KWScreenUtils.getScreenWidthInDPs(this.sceneActivity));
        layoutParams.height = KWScreenUtils.dpToPx(this.sceneActivity, KWScreenUtils.getScreenHeightInDPs(this.sceneActivity));

        dialogWindow.setAttributes(layoutParams);
    }

    public void addEvent(KWEventModel eventModel) {
        try {
            this.eventModelArrayList.add((KWEventModel)eventModel.clone());
        }
        catch (Exception ex) {
            KWLog.d("[??????] ?????? Clone ??? EventModel ???????????????????????????????????????????????????????????????????????????????????????????????????????????????Event????????????????????????????????????Action?????????????????????????????????????????????????????????????????????");
            this.eventModelArrayList.add(eventModel);
        }
    }

    public void play(String eventIdentifier) {

        this.eventIdentifier = eventIdentifier;

        if (this.isPlaying) {
            KWLog.d("[??????] ????????????????????????????????????????????????????????????????????????????????????");
            return;
        }

        if (this.eventIndex < this.eventModelArrayList.size()) {

            KWEventModel eventModel = this.eventModelArrayList.get(this.eventIndex++);

            if (this.playListener != null) {
                this.playListener.willPlayEvent(this.eventIdentifier, eventIndex, eventModel);
            }

            startEvent(eventModel);

            if (this.playListener != null) {
                this.playListener.didPlayEvent(this.eventIdentifier, eventIndex, eventModel);
            }
        }
        else {
            finish();
        }
    }

    private void continuePlay() {
        this.isPlaying = false;
        play(this.eventIdentifier);
    }

    public void stop() {

        if (this.eventModelArrayList.size() == 0) {
            return;
        }

        finish();
    }

    private void finish() {

        if (this.finishListener != null) {
            this.finishListener.willFinishAllEvent(this.eventIdentifier);
        }

        KWLog.d("[??????] ????????????????????????");
        KWEventCharacterUtils.hideAllCharacterImageView(this.sceneActivity.characterImageViewArrayList, true);
        this.messageDialog.dismiss();
        this.fullScreenMessageDialog.dismiss();

        this.eventModelArrayList.clear();
        this.eventIndex = 0;

        this.isPlaying = false;

        String tempIdentifier = this.eventIdentifier;
        this.eventIdentifier = null;

        if (this.finishListener != null) {
            this.finishListener.didFinishAllEvent(tempIdentifier);
        }

    }

    private void startEvent(KWEventModel eventModel) {

        this.isPlaying = true;
        KWEventCharacterUtils.hideAllCharacterImageView(this.sceneActivity.characterImageViewArrayList, false);
        KWEventCharacterUtils.setAllCharacterImageViewAlpha(this.sceneActivity.characterImageViewArrayList, 1.0f);

        //???????????????eventModel???????????????????????????
        updateBackground(eventModel);
        updateBgmAndSound(eventModel);

        if (this.fullScreenMessageDialog != null && this.fullScreenMessageDialog.isShowing()) {
            this.fullScreenMessageDialog.dismiss();
        }

        //TODO: ?????????????????????eventModel??????????????????????????????????????????????????????
        if (eventModel.getClass().equals(KWThirdPersonEventModel.class)) {
            KWEventCharacterUtils.setAllCharacterImageViewAlpha(this.sceneActivity.characterImageViewArrayList, 0.75f);
            KWEventCharacterUtils.updateCharacter(this.sceneActivity.characterImageViewArrayList, eventModel);

            //?????????????????????????????????????????????????????????
            if (eventModel.getMessage() == null) {
                //updateBackground(eventModel);
                continuePlay();
                return;
            }
        }
        //?????????????????????????????????
        else if (eventModel.getClass().equals(KWPictureEventModel.class)) {
            this.sceneActivity.pictureImageView.setVisibility(View.VISIBLE);
            Drawable pictureDrawable = ((KWPictureEventModel)eventModel).getPictureDrawable();
            if (pictureDrawable != null) {
                this.sceneActivity.pictureImageView.setImageDrawable(pictureDrawable);
            }

        }
        //???????????????????????????????????????
        else if (eventModel.getClass().equals(KWFullScreenEventModel.class)) {
            this.fullScreenMessageDialog.show(eventModel.getMessage(), eventModel.getMessageColorString());
            this.fullScreenMessageDialog.setOnClickListener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    continuePlay();
                }
            });

            //updateBackground(eventModel);
            return;
        }
        //??????????????????????????????
        else if (eventModel.getClass().equals(KWOptionEventModel.class)) {
            showOptionDialog((KWOptionEventModel)eventModel);
            this.isPlaying = false;
            return;
        }

        //?????????????????????????????????????????????????????????????????????????????????
        if (!eventModel.getClass().equals(KWPictureEventModel.class)) {
            this.sceneActivity.pictureImageView.setVisibility(View.GONE);
            this.sceneActivity.pictureImageView.setImageDrawable(KWResourceUtils.getEmptyDrawable());
        }

        this.messageDialog.show(eventModel.getCharacterName(), eventModel.getMessage(), eventModel.getMessageColorString());
        this.messageDialog.setOnClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                continuePlay();
            }
        });
    }

    private void updateBackground(KWEventModel eventModel) {
        if (eventModel.getBackgroundDrawable() != null) {
            this.sceneActivity.backgroundImageView.setImageDrawable(eventModel.getBackgroundDrawable());
        }
    }

    private void updateBgmAndSound(KWEventModel eventModel) {
        int bgmResId = eventModel.getBackgroundMusicResourceId();
        int soundResId = eventModel.getSoundEffectResourceId();

        if (bgmResId == KW_EVENT_NO_BACKGROUND_MUSIC_ID) {
            KWSoundManager.sharedInstance(sceneActivity).stopBgm();
        }
        else if (bgmResId != -1) {
            playBgm(bgmResId);
        }

        if (soundResId != -1) {
            playSound(soundResId);
        }
    }

    private void showOptionDialog(KWOptionEventModel optionEventModel) {
        initOptionDialog(optionEventModel);
        optionDialog.show(optionEventModel.getHint(), optionEventModel.getOptionTitleArrayList());
    }

    public void setPlayListener(KWEventManagerPlayListener playListener) {
        this.playListener = playListener;
    }

    public void setFinishListener(KWEventManagerFinishListener finishListener) {
        this.finishListener = finishListener;
    }

    public void setOptionListener(KWOptionDialog.KWOptionDialogOptionListener optionListener) {
        this.optionListener = optionListener;
    }

    public void playBgm(int resId) {
        KWSoundManager.sharedInstance(sceneActivity).playBgm(resId);
    }

    public void playSound(int resId) {
        KWSoundManager.sharedInstance(sceneActivity).playSound(resId);
    }
}
