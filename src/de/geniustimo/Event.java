package de.geniustimo;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Event
  implements Listener
{
  Main plugin;

  public Event(Main Nethad)
  {
    this.plugin = Nethad;
    this.plugin.getProxy().getPluginManager().registerListener(this.plugin, this);
  }

  @EventHandler
  public void playercmd(ChatEvent event) {
    if ((event.getSender() instanceof ProxiedPlayer)) {
      ProxiedPlayer player = (ProxiedPlayer)event.getSender();
      if (!event.getMessage().startsWith("/")) {
        return;
      }
      String[] command = event.getMessage().toLowerCase().split(" ", 2);
      if ((player.hasPermission("cmd_block.bypass." + command[0].substring(1))) || (player.hasPermission("cmd_block.bypass"))) {
        return;
      }
      if (Main.config.getStringList("List").contains(command[0].substring(1))) {
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.config.getString("Message")));
          event.setCancelled(true);
      }
    }
  }
}
