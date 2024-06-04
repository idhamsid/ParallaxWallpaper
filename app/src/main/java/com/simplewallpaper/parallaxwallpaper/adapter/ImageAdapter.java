package com.simplewallpaper.parallaxwallpaper.adapter;

import static com.simplewallpaper.parallaxwallpaper.ui.MainActivity.dir;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.simplewallpaper.parallaxwallpaper.config.SettingsAlien;
import com.simplewallpaper.parallaxwallpaper.config.Utils;
import com.simplewallpaper.spacewallpaper.R;
import com.simplewallpaper.parallaxwallpaper.ui.WallpaperActivity;

import com.simplewallpaper.parallaxwallpaper.model.Wallpaper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter {
    public static ArrayList<Wallpaper> wallList;
    public static ArrayList<Wallpaper> mFilteredList;
    public static Intent intent;
    private final int VIEW_ITEM = 0;
    public Context context;
    public static String url_image;
    public static String name_wallpaper;

    public ImageAdapter(ArrayList<Wallpaper> webLists, Context context) {
        ImageAdapter.wallList = webLists;
        mFilteredList = webLists;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_list, parent, false);
            return new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_progressbar, parent, false);
            return new ProgressViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if (holder instanceof ViewHolder) {
            final Wallpaper webList = mFilteredList.get(position);
            if (webList.getImage_url().startsWith("http")) {
                Glide
                        .with(context)
                        .load(webList.getImage_url())
                        .centerCrop()
                        .into(((ViewHolder) holder).imgUrl);

            } else {
                Glide
                        .with(context)
                        .load("file:///android_asset/" + webList.getImage_url())
                        .centerCrop()
                        .into(((ViewHolder) holder).imgUrl);
            }
            ((ViewHolder) holder).txtName.setText(webList.getWallapper_name());

            ((ViewHolder) holder).imgUrl.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ObsoleteSdkInt")
                @Override
                public void onClick(View v) {
                    url_image = webList.getImage_url();
                    name_wallpaper = webList.getWallapper_name();

                    SharedPreferences myKoin = context.getSharedPreferences("savelinkwall", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = myKoin.edit();
                    editor.putString("savelinkwall",name_wallpaper);
                    editor.apply();

                    File mediaStorageDir = new File(dir + "/" + name_wallpaper + ".jpeg");
                    if (!mediaStorageDir.exists()) {
                        final ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("Download file");
                        progressDialog.show();
                        if (webList.getImage_url().startsWith("http")) {
                            Glide.with(context).asFile()
                                    .load(webList.getImage_url())
                                    .apply(new RequestOptions()
                                            .format(DecodeFormat.PREFER_ARGB_8888)
                                            .override(Target.SIZE_ORIGINAL))
                                    .into(new Target<File>() {
                                        @Override
                                        public void onStart() {

                                        }

                                        @Override
                                        public void onStop() {

                                        }

                                        @Override
                                        public void onDestroy() {

                                        }

                                        @Override
                                        public void onLoadStarted(@Nullable Drawable placeholder) {

                                        }

                                        @Override
                                        public void onLoadFailed(@Nullable Drawable errorDrawable) {

                                        }

                                        @Override
                                        public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                            storeImage(resource);
                                        }

                                        @Override
                                        public void onLoadCleared(@Nullable Drawable placeholder) {

                                        }

                                        @Override
                                        public void getSize(@NonNull SizeReadyCallback cb) {

                                        }

                                        @Override
                                        public void removeCallback(@NonNull SizeReadyCallback cb) {

                                        }

                                        @Nullable
                                        @Override
                                        public Request getRequest() {
                                            return null;
                                        }

                                        private void storeImage(File image) {
                                            File pictureFile = getOutputMediaFile();
                                            if (pictureFile == null) {
                                                return;
                                            }
                                            try {
                                                FileOutputStream output = new FileOutputStream(pictureFile);
                                                FileInputStream input = new FileInputStream(image);

                                                FileChannel inputChannel = input.getChannel();
                                                FileChannel outputChannel = output.getChannel();

                                                inputChannel.transferTo(0, inputChannel.size(), outputChannel);
                                                output.close();
                                                input.close();
                                                intent = new Intent(context, WallpaperActivity.class);
                                                intent.putExtra("position", position);
                                                context.startActivity(intent);
                                                progressDialog.dismiss();
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        private File getOutputMediaFile() {
                                            if (!dir.exists()) {
                                                if (!dir.mkdirs())
                                                    return null;
                                            }

                                            File mediaFile;
                                            mediaFile = new File(dir.getPath() + "/" + name_wallpaper + ".jpeg");
                                            return mediaFile;
                                        }

                                        @Override
                                        public void setRequest(@Nullable Request request) {

                                        }
                                    });
                        } else {

                            AssetManager assetManager = context.getAssets();
                            InputStream in = null;
                            OutputStream out = null;
                            File file = new File(dir, webList.getWallapper_name() + ".jpeg");
                            try {
                                in = assetManager.open(webList.getImage_url());  //leer el archivo de assets
                                out = new BufferedOutputStream(new FileOutputStream(file)); //crear el archivo
                                copyFile(in, out);
                                in.close();
                                in = null;
                                out.flush();
                                out.close();

                            } catch (Exception e) {
                                Log.e("tag", e.getMessage());
                            }

                            intent = new Intent(context, WallpaperActivity.class);
                            intent.putExtra("position", position);
                            context.startActivity(intent);
                            progressDialog.dismiss();
                        }
                    } else {
                        intent = new Intent(context, WallpaperActivity.class);
                        intent.putExtra("position", position);
                        context.startActivity(intent);
                    }
                    Utils.ShowInterstitialAds((Activity) context, SettingsAlien.INTERVAL);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }


    private static class ProgressViewHolder extends RecyclerView.ViewHolder {
        private static ProgressBar progressBar;

        private ProgressViewHolder(View v) {
            super(v);
            progressBar = v.findViewById(R.id.progressBar);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgUrl;
        public TextView txtName;
        public ViewHolder(View itemView) {
            super(itemView);
            imgUrl = itemView.findViewById(R.id.imgWall);
            txtName = itemView.findViewById(R.id.txtName);
        }

    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
}
