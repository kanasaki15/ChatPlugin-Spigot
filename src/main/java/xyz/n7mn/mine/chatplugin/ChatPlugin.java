package xyz.n7mn.mine.chatplugin;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.*;

public final class ChatPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("StartUp Chat-Plugin Ver 1.0");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutdown Chat-Plugin Ver 1.0");
    }

     @EventHandler
     public void AsyncPlayerChatEvent(AsyncPlayerChatEvent e){
        e.setCancelled(true);
        String msg = e.getMessage();
        e.setMessage("てすと : " + msg);

     }
}
