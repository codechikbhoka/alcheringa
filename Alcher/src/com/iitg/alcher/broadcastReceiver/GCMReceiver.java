package com.iitg.alcher.broadcastReceiver;

import android.content.Context;

import com.google.android.gcm.GCMBroadcastReceiver;

public class GCMReceiver extends GCMBroadcastReceiver { 
    @Override
	protected String getGCMIntentServiceClassName(Context context) { 
		return "com.iitg.alcher.services.GCMIntentService"; 
	} 
}