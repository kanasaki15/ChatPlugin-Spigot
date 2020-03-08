package xyz.n7mn.mine.chatplugin;

import com.ibm.icu.text.Transliterator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("StartUp Chat-Plugin Ver 1.0");
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Shutdown Chat-Plugin Ver 1.0");
        AsyncPlayerChatEvent.getHandlerList().unregister(this);
    }

    public class ChatListener implements Listener {
        @EventHandler
        public void chatMain(AsyncPlayerChatEvent e){
            String msg = e.getMessage();
            e.setMessage(msg + " ("+r2k(msg)+")");
        }

        public String r2k(String msg){
            Transliterator trans = Transliterator.getInstance("Latin-Hiragana");
            String msg2 = trans.transliterate(msg);
            getLogger().info(msg);
            return msg2;

        }
    }
}
