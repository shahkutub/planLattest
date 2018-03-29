package com.nanosoft.planInternational.tracking.jsinterface;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Nanosoft-Android on 6/12/2017.
 */

class AudioRecorder {
    private String LOG_TAG = "AudioRecordTest";
    private int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private String mFileName = null;

    private MediaRecorder mRecorder = null;

    private MediaPlayer mPlayer = null;

    // Requesting permission to RECORD_AUDIO
    private String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO};
    private Context ctx;

    AudioRecorder(Context ctx) {
        this.ctx = ctx;
        initialize();
    }

    private final void initialize() {
        this.mFileName = ((Activity) ctx).getExternalCacheDir().getAbsolutePath();
        this.mFileName = this.mFileName + "/audiorecordtest.3gp";
        ActivityCompat.requestPermissions((Activity) ctx, this.permissions, this.REQUEST_RECORD_AUDIO_PERMISSION);
    }

    public void startRecording() {
        this.mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);


        try {
            mRecorder.prepare();
            mRecorder.start();
            Toast.makeText(ctx, "Record Started", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(ctx, "Could not start recording", Toast.LENGTH_SHORT).show();
        }
    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        Toast.makeText(ctx, "Recording Stopped", Toast.LENGTH_LONG).show();
    }

    public void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Toast.makeText(ctx, "Something Wrong!", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(ctx, "Started Playing", Toast.LENGTH_LONG).show();
    }

    private byte[] getByteArray() {
        File file = new File(mFileName);
        FileInputStream fin = null;
        byte[] fileContent = null;
        try {
            // create FileInputStream object
            fin = new FileInputStream(file);

            fileContent = new byte[(int) file.length()];

            // Reads up to certain bytes of data from this input stream into an array of bytes.
            fin.read(fileContent);

            fin.close();

        } catch (Exception e) {
        }
        return fileContent;
    }

    public String SaveAudio() {
        byte[] arr = getByteArray();
        String x = Base64.encodeToString(arr, Base64.DEFAULT);
        File file = new File(mFileName);
        file.delete();
        return x;
    }

    public void stopPlaying() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer = null;
        }
    }
}
