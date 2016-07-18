package com.jayqqaa12.abase.util.media;

import android.media.MediaPlayer;

import com.jayqqaa12.abase.core.AbaseUtil;

public class SoundUtil extends AbaseUtil
{
	
	public static void playSound(int resId, float volume)
	{
		MediaPlayer player = MediaPlayer.create(getContext(), resId);
		player.setVolume(volume, volume);
		player.start();

	}

}
