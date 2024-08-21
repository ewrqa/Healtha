package com.wd.health.utils;

import android.os.Handler;
import android.widget.ImageView;

import com.wd.health.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>项目名称:Healtha</p>
 * <p>包名:com.wd.health.utils</p>
 * <p>简述:语音播放动画</p>
 *
 * @author 张凯涛
 * @date 2022/8/5
 */
public class VoicePlayingBgUtil {
    private Handler handler;

    private ImageView imageView;

    private ImageView lastImageView;

    private Timer timer = new Timer();
    private TimerTask timerTask;

    private int i;

    private int modelType = 1;//类型

    private int[] leftVoiceBg = new int[] { R.drawable.gray1, R.drawable.gray2, R.drawable.gray3 };

    public VoicePlayingBgUtil(int  handler) {
        super();

    }

    public void voicePlay() {
        if (imageView == null) {
            return;
        }
        i = 0;
        timerTask = new TimerTask() {

            @Override
            public void run() {
                if (imageView != null) {
                    if (modelType == 1) {
                        changeBg(leftVoiceBg[i % 3], false);
                    }
                }
                else {
                    return;
                }
                i++;
            }
        };
        timer.schedule(timerTask, 0, 500);
    }

    public void stopPlay() {
        lastImageView = imageView;
        if (lastImageView != null) {
            switch (modelType) {
                case 1:
                    changeBg(R.drawable.gray3, true);

                    break;
                default:
                    changeBg(R.drawable.gray3, true);
                    break;
            }
            if (timerTask != null) {
                timerTask.cancel();
            }
        }
    }

    private void changeBg(final int id, final boolean isStop) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isStop) {
                    lastImageView.setImageResource(id);
                }
                else {
                    imageView.setImageResource(id);

                }
            }
        });
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setModelType(int modelType) {
        this.modelType = modelType;
    }
}
