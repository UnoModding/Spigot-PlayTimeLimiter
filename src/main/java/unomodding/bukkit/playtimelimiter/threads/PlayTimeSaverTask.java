/**
 * Copyright 2013-2014 by ATLauncher and Contributors
 *
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */
package unomodding.bukkit.playtimelimiter.threads;

import java.util.TimerTask;

import unomodding.bukkit.playtimelimiter.PlayTimeLimiter;

public class PlayTimeSaverTask extends TimerTask {
	private final PlayTimeLimiter plugin;

	public PlayTimeSaverTask(PlayTimeLimiter instance) {
		this.plugin = instance;
	}

	@Override
	public void run() {
		this.plugin.savePlayTime(); // Save playtime every 10 minutes
	}
}
