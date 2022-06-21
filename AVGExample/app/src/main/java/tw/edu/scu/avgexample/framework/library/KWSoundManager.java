package tw.edu.scu.avgexample.framework.library;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;

import tw.edu.scu.avgexample.framework.utility.KWLog;


public class KWSoundManager {

    private static KWSoundManager sharedInstance = null;

    // other instance variables can be here

    private Context context;
    private SoundPool soundPool;
    private MediaPlayer mediaPlayer;

    private HashMap<String, String> soundHashMap = new HashMap<>();

    private KWSoundManager(Context context) {
        this.context = context;
    }

    public static KWSoundManager sharedInstance(Context context) {
        if (sharedInstance == null) {
            sharedInstance = new KWSoundManager(context);
            sharedInstance.inita();
        }
        return(sharedInstance);
    }

    protected void inita() {
        this.soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .build();
    }

    public void playSound(int resId) {

        int soundId = getSoundIdByResId(resId);

        if (soundId != -1) {

            KWLog.d("[資訊] 該音效索引編號（" + soundId + "）已存在 Local Pool 中，將直接播放");
            KWSoundManager.this.soundPool.play(soundId, 1, 1, 1 ,0 ,1);

        }
        else {
            final int newSoundId = soundPool.load(context, resId, 1);

            KWLog.d("[資訊] 該音效尚未建立索引，將建立新的音效索引編號：" + newSoundId + "，並置放於 Local Pool 中");
            combineSoundIdAndResId(resId, newSoundId);

            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    KWLog.d("[資訊] 音效索引編號（" + newSoundId + "）載入成功，將開始播放");
                    KWSoundManager.this.soundPool.play(newSoundId, 1, 1, 1 ,0 ,1);
                }
            });
        }
    }

    public void combineSoundIdAndResId(int resId, int soundId) {
        soundHashMap.put(String.valueOf(resId), String.valueOf(soundId));
    }

    public int getSoundIdByResId(int resId) {
        String soundIdString = soundHashMap.get(String.valueOf(resId));

        if (soundIdString != null) {
            return Integer.parseInt(soundIdString);
        }

        return -1;
    }

    public void playBgm(int resId) {
        stopBgm();
        this.mediaPlayer = MediaPlayer.create(this.context, resId);

        if (this.mediaPlayer != null) {
            KWLog.d("[資訊] 播放新的背景音樂（bgm）：" + resId);
            //AudioManager audioManager = (AudioManager) this.context.getSystemService(Context.AUDIO_SERVICE);
            //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);
            this.mediaPlayer.setLooping(true);
            this.mediaPlayer.setVolume(0.5f, 0.5f);
            this.mediaPlayer.start();
        }

    }

    public void pauseBgm() {
        if (this.mediaPlayer != null) {
            try {
                this.mediaPlayer.pause();
            }
            catch(Exception ex) {
                KWLog.d("[錯誤] pauseBgm: " + ex.toString());
            }
        }
    }


    public void resumeBgm() {
        if (this.mediaPlayer != null) {
            try {
                this.mediaPlayer.start();
            }
            catch(Exception ex) {
                KWLog.d("[錯誤] resumeBgm: " + ex.toString());
            }

        }
    }
    public void stopBgm() {
        if (this.mediaPlayer != null) {
            KWLog.d("[資訊] 停止當前所有背景音樂（bgm）");

            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }


}
