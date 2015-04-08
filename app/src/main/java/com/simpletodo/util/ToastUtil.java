package com.simpletodo.util;

import android.app.Activity;
import android.widget.Toast;


public class ToastUtil {

    private static String oldMsg;
    protected static Toast toast   = null;
    private static long oneTime=0;
    private static long twoTime=0;

    public static void showLongToast(final Activity context, final String s){
        if(toast==null){
            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            oneTime=System.currentTimeMillis();
        }else{
            twoTime=System.currentTimeMillis();
            if(s.equals(oldMsg)){
                if(twoTime-oneTime>Toast.LENGTH_LONG){
                    context.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            toast.show();
                        }
                    });
                    toast.show();
                }
            }else{
                oldMsg = s;
                context.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        toast.setText(s);
                        toast.show();
                    }
                });
            }
        }
        oneTime=twoTime;
    }

    public static void showLongToast(final Activity context, int resId){
			    showLongToast(context, context.getString(resId));
    }

    public static void showShortToast(final Activity context, final String s){
        if(toast==null){
            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            oneTime=System.currentTimeMillis();
        }else{
            twoTime=System.currentTimeMillis();
            if(s.equals(oldMsg)){
                if(twoTime-oneTime>Toast.LENGTH_SHORT){
                    context.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            toast.show();
                        }
                    });
                    toast.show();
                }
            }else{
                oldMsg = s;
                context.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        toast.setText(s);
                        toast.show();
                    }
                });
            }
        }
        oneTime=twoTime;
    }


}