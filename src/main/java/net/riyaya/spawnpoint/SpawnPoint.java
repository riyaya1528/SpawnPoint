package net.riyaya.spawnpoint;

import net.riyaya.spawnpoint.Utils.PlayerRespawnEvent;
import net.riyaya.spawnpoint.Utils.PlayerSavePointEvent;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnPoint extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();

        if(!checkConfigBlockConsistency()) {
            getLogger().warning("config内にあるブロックの値が正しくありません");
            getServer().getPluginManager().disablePlugin(this);
        }
        registerEvent(this);

        getLogger().info("プラグインが有効になりました");
    }

    @Override
    public void onDisable() {
        saveConfig();
        getLogger().info("プラグインが無効になりました");
    }

    private void registerEvent(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new PlayerSavePointEvent(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerRespawnEvent(), plugin);
    }

    private boolean checkConfigBlockConsistency() {
        try {
            Material.matchMaterial(config.getString("spawn-block"));
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
