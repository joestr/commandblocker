package de.geniustimo;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Main extends Plugin
implements Listener
{
static Configuration config;
static Main plugin;

public void onEnable()
{
  plugin = this;
  LoadFile();
  new Event(this);
}

static void LoadFile()
{
  if (!plugin.getDataFolder().exists()) {
    plugin.getDataFolder().mkdir();
  }
  File configfile = new File(plugin.getDataFolder(), "config.yml");
  if (!configfile.exists())
    try
    {
      Files.copy(plugin.getResourceAsStream("config.yml"), configfile.toPath(), new CopyOption[0]);
    }
    catch (IOException localIOException1)
    {
    }
  try {
    config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "config.yml"));
  }
  catch (IOException e)
  {
    e.printStackTrace();
  }
}
}
