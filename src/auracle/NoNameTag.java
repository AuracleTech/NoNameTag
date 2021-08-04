package auracle;


import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class NoNameTag extends JavaPlugin implements Listener {
    static Server server;
    static ConsoleCommandSender console;
    ScoreboardManager sbm;
    String NoTagTeamName = "NoNameTagTeam";

    public void onEnable() {
        server = this.getServer();
        server.getPluginManager().registerEvents(this, this);
        sbm = server.getScoreboardManager();
        console = server.getConsoleSender();
        console.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "No Name Tag Loaded");
        removeNameTags();
    }

    public void onDisable() {
        console.sendMessage(ChatColor.RED + "No Name Tag Disabled");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        removeNameTags();
    }

    private void removeNameTags() {
        Team NoNameTagTeam = sbm.getMainScoreboard().getTeam(NoTagTeamName);
        if (NoNameTagTeam == null) {
            sbm.getMainScoreboard().registerNewTeam(NoTagTeamName);
            NoNameTagTeam = sbm.getMainScoreboard().getTeam(NoTagTeamName);
            NoNameTagTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }
        NoNameTagTeam = sbm.getMainScoreboard().getTeam(NoTagTeamName);
        for (Player player : server.getOnlinePlayers())
            NoNameTagTeam.addEntry(player.getDisplayName());
    }
}