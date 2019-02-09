package de.geniustimo;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerCommandListener implements Listener {
  Plugin plugin;

  public Event(Plugin plugin) {
    this.plugin = plugin;
    this.plugin.getProxy().getPluginManager().registerListener(this.plugin, this);
  }

  @EventHandler
  public void playercmd(ChatEvent event) {
    if ((event.getSender() instanceof ProxiedPlayer)) {
      ProxiedPlayer player = (ProxiedPlayer) event.getSender();
      if (!event.getMessage().startsWith("/")) {
        return;
      }
      String[] command = event.getMessage().toLowerCase().split(" ", 2);
      if ((player.hasPermission("commandblocker.bypass." + command[0].substring(1)))
          || (player.hasPermission("commandblocker.bypass"))) {
        return;
      }
      if (Main.config.getStringList("blocked_commands").contains(command[0].substring(1))) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.config.getString("message")));
        event.setCancelled(true);
      }
    }
  }
}
