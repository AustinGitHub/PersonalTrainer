package com.personaltrainer.android.personaltrainer;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;


/**
 * Created by Austin on 3/19/2018.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

        Camera camera;
    SurfaceHolder holder;
    public CameraView(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            Camera.Parameters params = camera.getParameters();
        List<Camera.Size> sizes = params.getSupportedPictureSizes();
        Camera.Size mSize = null;

        for(Camera.Size size : sizes) {
            mSize = size;
        }

            if(this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE){
                params.set("Orientation","portrait");
                camera.setDisplayOrientation(90);
                params.setRotation(90);
            } else {
                params.set("Orientation","landscape");
                camera.setDisplayOrientation(0);
                params.setRotation(0);
            }

            params.setPictureSize(mSize.width,mSize.height);
            camera.setParameters(params);
            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch(IOException e) {
                e.printStackTrace();
            }
    }
}