package me.jonasxpx.badwords;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandReload implements CommandExecutor{

	private BadWords plugin;
	
	public CommandReload(BadWords bad){
		this.plugin = bad;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {
		if(sender.isOp()){
			try{
				this.plugin.forceReload();
				sender.sendMessage("§cPlugin recarregado");
			}catch(Exception e){sender.sendMessage("§cErro ocorrido " + e.getMessage());}
		}
		return false;
	}
}
