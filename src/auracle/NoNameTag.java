package auracle;


import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class NoNameTag extends JavaPlugin implements Listener {
    static Server server;
    static ConsoleCommandSender console;
    ScoreboardManager sbm;
    Team NoNameTagTeam;

    public void onEnable() {
        server = this.getServer();
        server.getPluginManager().registerEvents(this, this);
        sbm = server.getScoreboardManager();
        console = server.getConsoleSender();
        console.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "NoNameTag Loaded");
        for(Player player : server.getOnlinePlayers())
            removeNameTag(player.getDisplayName());
    }

    public void onDisable() { console.sendMessage(ChatColor.RED + "NoNameTag Disabled"); }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) { removeNameTag(event.getPlayer().getDisplayName()); }

    private void removeNameTag(String PlayerName) {
        if (!teamExists()) makeTeam();
        NoNameTagTeam.addEntry(PlayerName);
    }

    public boolean teamExists() { return sbm.getMainScoreboard().getTeam("NoNameTagTeam") != null; }

    public void makeTeam() {
        sbm.getMainScoreboard().registerNewTeam("NoNameTagTeam");
        NoNameTagTeam = sbm.getMainScoreboard().getTeam("NoNameTagTeam");
        NoNameTagTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
    }
}