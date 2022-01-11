package net.riyaya.spawnpoint.Utils;

import net.riyaya.spawnpoint.SpawnPoint;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerRespawnEvent implements Listener {

    @EventHandler
    public void onPlayerRespawn(org.bukkit.event.player.PlayerRespawnEvent event) {
        if(SpawnPoint.config.getString("spawn." + event.getPlayer().getUniqueId() + ".x") == null) {
            return;
        }
        Location loc = event.getPlayer().getLocation();
        loc.setX(SpawnPoint.config.getDouble("spawn." + event.getPlayer().getUniqueId() + ".x"));
        loc.setY(SpawnPoint.config.getDouble("spawn." + event.getPlayer().getUniqueId() + ".y"));
        loc.setZ(SpawnPoint.config.getDouble("spawn." + event.getPlayer().getUniqueId() + ".z"));
        loc.setYaw((float) SpawnPoint.config.getDouble("spawn." + event.getPlayer().getUniqueId() + ".yaw"));
        loc.setPitch((float) SpawnPoint.config.getDouble("spawn." + event.getPlayer().getUniqueId() + ".pitch"));

        event.setRespawnLocation(loc);
    }
}
