/**
 * Copyright 2013-2015 by UnoModding, ATLauncher and Contributors
 *
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */
package unomodding.bukkit.playtimelimiter;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import unomodding.bukkit.playtimelimiter.utils.FileUtils;
import unomodding.bukkit.playtimelimiter.utils.Timestamper;

public class PlayTimeListener implements Listener {
	private final PlayTimeLimiter plugin;

	public PlayTimeListener(PlayTimeLimiter instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		FileUtils.appendStringToFile(new File(this.plugin.getDataFolder(),
				"playtime.log"), String.format("[%s] %s logged in",
				Timestamper.now(), event.getPlayer().getName()));
		if (this.plugin
				.getTimeAllowedInSeconds(event.getPlayer().getUniqueId()) <= 0) {
			FileUtils.appendStringToFile(new File(this.plugin.getDataFolder(),
					"playtime.log"), String.format(
					"[%s] %s was kicked for exceeding play time",
					Timestamper.now(), event.getPlayer().getName()));
			event.getPlayer()
					.kickPlayer(
							"You have exceeded the time allowed to play! Come back in "
									+ this.plugin
											.secondsToDaysHoursSecondsString(this.plugin
													.secondsUntilNextDay())
									+ "!");
		}
		this.plugin.setPlayerLoggedIn(event.getPlayer().getUniqueId());
		event.getPlayer().sendMessage(
				"You have "
						+ ChatColor.GREEN
						+ plugin.secondsToDaysHoursSecondsString(plugin
								.getTimeAllowedInSeconds(event.getPlayer()
										.getUniqueId())) + ChatColor.RESET
						+ " of playtime left!");
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		FileUtils.appendStringToFile(new File(this.plugin.getDataFolder(),
				"playtime.log"), String.format("[%s] %s logged out",
				Timestamper.now(), event.getPlayer().getName()));
		this.plugin.setPlayerLoggedOut(event.getPlayer().getUniqueId());
	}
}
