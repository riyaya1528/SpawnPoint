package net.riyaya.spawnpoint.Utils;

import net.riyaya.spawnpoint.SpawnPoint;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerSavePointEvent implements Listener {

    @EventHandler
    public void onPlayerStandBlock(PlayerMoveEvent event) {
        if(!event.getPlayer().hasPermission("SpawnPoint.bypass")) {
            return;
        }
        Location loc = event.getPlayer().getLocation();
        loc.setX(loc.getBlockX());
        loc.setZ(loc.getBlockZ());

        loc.setX(loc.getBlockX() + 0.5);
        loc.setY(loc.getBlockZ() + 0.5);

        loc.setY(event.getPlayer().getLocation().getBlockY() - 1);
        if(!loc.getBlock().getType().equals(Material.matchMaterial(SpawnPoint.config.getString("spawn-block")))) {
            return;
        }
        loc.setY(event.getPlayer().getLocation().getBlockY() + 1);

        if(loc.getX() == SpawnPoint.config.getDouble("spawn." + event.getPlayer().getUniqueId() + ".x")
                && loc.getY() == SpawnPoint.config.getDouble("spawn." + event.getPlayer().getUniqueId() + ".y")
                && loc.getZ() == SpawnPoint.config.getDouble("spawn." + event.getPlayer().getUniqueId() + ".z")) {
            return;
        }

        SpawnPoint.config.set("spawn." + event.getPlayer().getUniqueId() + ".x", loc.getX());
        SpawnPoint.config.set("spawn." + event.getPlayer().getUniqueId() + ".y", loc.getY());
        SpawnPoint.config.set("spawn." + event.getPlayer().getUniqueId() + ".z", loc.getZ());
        SpawnPoint.config.set("spawn." + event.getPlayer().getUniqueId() + ".yaw", (double) loc.getYaw());
        SpawnPoint.config.set("spawn." + event.getPlayer().getUniqueId() + ".pitch", (double) loc.getPitch());

        event.getPlayer().sendMessage(ChatColor.YELLOW + "チェックポイントを通過しました!");
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }
}
