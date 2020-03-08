package xyz.n7mn.mine.chatplugin;

import com.google.gson.Gson;
import com.ibm.icu.text.Transliterator;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        public void chatMain(AsyncPlayerChatEvent e) {
            String msg = e.getMessage();
            e.setMessage(msg + ChatColor.YELLOW + " (" + r2k(msg) + ")");
        }

        public String r2k(String msg) {
            Transliterator trans = Transliterator.getInstance("Latin-Hiragana");
            String msg2 = trans.transliterate(msg);

            return msg2;
        }

        public String kana2kanji(String str){
            // http://www.google.com/transliterate?langpair=ja-Hira|ja&text=
            String RequestText = HttpGet("http://www.google.com/transliterate?langpair=ja-Hira|ja&text="+str);
            Gson gson = new Gson();
            String[] arr = gson.fromJson(RequestText,String[].class);
            getLogger().info("debug : " + RequestText);
            StringBuffer sb = new StringBuffer();
            return "";
        }
    }

    public String HttpGet(String url) {
        URL url2 = null;
        try {
            url2 = new URL(url);
            HttpURLConnection http = (HttpURLConnection) url2.openConnection();
            http.setRequestMethod("GET");
            http.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String xml = "", line = "";
            while((line = reader.readLine()) != null){
                xml += line;
            }
            reader.close();
            return xml;
        } catch (Exception e) {
            getLogger().info(e.getMessage());
        }
        return new String();
    }
}
