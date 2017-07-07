package com.dev.baqari.binding.viewBinding.apply;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.dev.baqari.binding.viewBinding.annotation.component.Receiver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ComponentApply {

    public static Context context;
    private static List<BroadcastReceiver> receiverList = new ArrayList<>();

    public static void registerReceiver(final Method method, final Object object) {
        String[] actions = method.getAnnotation(Receiver.class).actions();
        IntentFilter intentFilter = new IntentFilter();
        for (String action : actions) {
            intentFilter.addAction(action);

        }
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    method.invoke(object, intent);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        context.registerReceiver(receiver, intentFilter);
        receiverList.add(receiver);
    }

    public static void unregisterReceiver() {
        try{
            for (BroadcastReceiver receiver : receiverList) {
                context.unregisterReceiver(receiver);
            }
            context = null;
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
