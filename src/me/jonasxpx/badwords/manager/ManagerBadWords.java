package me.jonasxpx.badwords.manager;

import me.jonasxpx.badwords.BadWords;


public class ManagerBadWords {

	
	public static boolean isBadWord(String menssage){
			for(String bad : BadWords.getBadWords()){
				if(menssage.contains(bad)){
					return true;
				}
			}
		return false;
	}

	public static String getBadWords(String menssage){
		StringBuilder sb = new StringBuilder();
		for(String bad : BadWords.getBadWords()){
			if(menssage.contains(bad)){
				sb.append(bad + ", ");
			}
		}
		return sb.toString();
	}
	
}
