/**
 * Copyright 2013-2014 by ATLauncher and Contributors
 *
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */
package unomodding.bukkit.playtimelimiter.threads;

import unomodding.bukkit.playtimelimiter.PlayTimeLimiter;

public class ShutdownThread extends Thread
{
    private final PlayTimeLimiter plugin;

    public ShutdownThread(PlayTimeLimiter plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run()
    {
        this.plugin.savePlayTime(true); // Force save playtime when server is
                                        // shut down
    }
}
