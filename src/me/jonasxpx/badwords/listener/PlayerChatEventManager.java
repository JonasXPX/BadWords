package me.jonasxpx.badwords.listener;

import me.jonasxpx.badwords.BadWords;
import me.jonasxpx.badwords.manager.ManagerBadWords;
import me.jonasxpx.badwords.manager.ManagerPlayer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;

public class PlayerChatEventManager implements Listener{


	@EventHandler(priority = EventPriority.LOWEST)
	public void playerChatEvent(ChatMessageEvent ev){
		if(ev.getSender().isOp() || BadWords.getIgnoredChannels().contains(ev.getChannel().getName().toLowerCase()))
			return;
		
		if(ev.getSender().hasPermission("badwords.allow"))
			return;
		
		if(ManagerBadWords.isBadWord(ev.getMessage())){
			ev.getSender().sendMessage("§6----------------------------------------------"
					+ "\nVocê foi mutado por " + ManagerPlayer.mutePlayer(ev.getSender().getName()) + " Minuto(s)."
							+ "\n§cMotivo: Usando a palavra: " + ManagerBadWords.getBadWords(ev.getMessage())
							+ "\nSeu mute será auto incrementado caso fale novamente."
							+ "\n§6----------------------------------------------");
			ev.setCancelled(true);
		}
		
	}
	
}
