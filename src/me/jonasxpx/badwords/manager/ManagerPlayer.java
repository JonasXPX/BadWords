package me.jonasxpx.badwords.manager;

import br.com.devpaulo.legendchat.api.Legendchat;
import me.jonasxpx.badwords.BadWords;

public class ManagerPlayer {

	public static boolean containsPlayer(String player){
		if(BadWords.getConfigPlayer().contains(player.toLowerCase()))
			return true;
		return false;
	}
	
	
	/**
	 * @param player
	 * @return caso retorne -1 é defido a falta de regitro do jogador;
	 */
	public static int getPoints(String player){
		if(containsPlayer(player)){
			return BadWords.getConfigPlayer().getInt(player.toLowerCase());
		}
		return -1;
	}
	
	public static void addPoints(String player){
		if(containsPlayer(player))
			BadWords.getConfigPlayer().set(player.toLowerCase(), getPoints(player) + 1);
		else
			BadWords.getConfigPlayer().set(player.toLowerCase(), 1);
		BadWords.saveConfigPlayer();
	}
	
	public static int mutePlayer(String player){
		if(getPoints(player) == -1){
			addPoints(player);
			Legendchat.getMuteManager().mutePlayer(player, BadWords.defaultTime);
			return BadWords.defaultTime;
		}
		addPoints(player);
		Legendchat.getMuteManager().mutePlayer(player, getPoints(player) * BadWords.defaultTime);
		return (getPoints(player) * BadWords.defaultTime);
	}
}
