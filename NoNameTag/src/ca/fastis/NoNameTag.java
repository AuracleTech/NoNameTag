package ca.fastis;


import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

@SuppressWarnings("deprecation")
public class NoNameTag extends JavaPlugin implements Listener  {
	static Server server;
	static ConsoleCommandSender console;
	static Plugin plugin;
	Team NoNameTagTeam;

	public void onEnable() {
		plugin = this;
		server = this.getServer();
		console = server.getConsoleSender();
		server.getPluginManager().registerEvents(this, this);
		ScoreboardManager sbm = server.getScoreboardManager();
		if (sbm.getMainScoreboard().getTeam("NoNameTagTeam") == null) sbm.getMainScoreboard().registerNewTeam("NoNameTagTeam");
		NoNameTagTeam = sbm.getMainScoreboard().getTeam("NoNameTagTeam");
		NoNameTagTeam.setNameTagVisibility(NameTagVisibility.NEVER);
		console.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "NoNameTag Loaded");
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		NoNameTagTeam.addPlayer(event.getPlayer());
	}

	@Override
	public void onDisable() {
		console.sendMessage(ChatColor.RED + "NoNameTag Disabled");
	}
}