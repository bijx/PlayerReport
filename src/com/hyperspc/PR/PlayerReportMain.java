package com.hyperspc.PR;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerReportMain extends JavaPlugin{
	
	
	public void OnEnable() {
		this.saveDefaultConfig();
		
	}
	
	public void OnDisable() {
		
	}
	
	@Override
	public boolean onCommand(final CommandSender s, final Command cmd, final String label, final String[] args) {
		Player p = (Player) s;
		if(label.equalsIgnoreCase("playerreport") || label.equalsIgnoreCase("playereport") || label.equalsIgnoreCase("pr")) {
			if(args.length==1) {
				Player target = null;
				for(Player player : Bukkit.getOnlinePlayers()){
					if(player.getName().equals(args[0])) {
						target=player;
						break;
					}
				}
				if(target == null) {
					format(p, "Player not found! Make sure they are online!");
				}else {
					DataLog(p, target, target.getAddress().getHostString());
				}
			}else {
				format(p,"Correct Usage: /playerreport <player> or /pr <player>");
			}
		}
		
		
		return true;
	}
	
	public void DataLog(Player requester, Player target, String ip) {
		try {
			//##### CAPTURE DATA FROM IP #####
			String geolocation = "http://ip-api.com/line/"+ip;
			URL url = new URL(geolocation);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			String resultIP = sb.toString();
			
			//##### CAPTURE DATA FROM PLAYER STATISTICS #####
			String webPage = "https://minecraft-statistic.net/api/player/info/"+target.getName();
			url = new URL(webPage);
			urlConnection = url.openConnection();
			is = urlConnection.getInputStream();
			isr = new InputStreamReader(is);

			charArray = new char[1024];
			sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			String resultStats = sb.toString();
			
			
			//Log IP data			
			String[] ipData = resultIP.split("\n");
			if(ipData[0].equals("success")) {
				
				PlayerData targetData = new PlayerData(target.getName(), ipData);
			}else {
				if(ipData[1].equals("private range")) {
					format(requester, ChatColor.RED.toString() + "Error fetching data from servers. REASON: Private range (Server is likely LAN)");
				}else if(ipData[1].equals("reserved range")) {
					format(requester, ChatColor.RED.toString() + "Error fetching data from servers. REASON: Reserved range (Server is likey on private network)");
				}else if(ipData[1].equals("invalid query")) {
					format(requester, ChatColor.RED.toString() + "Error fetching data from servers. REASON: Invalid query (Target's hostname is likely incorrectly formatted)");
				}else if(ipData[1].equals("quota")) {
					format(requester, ChatColor.RED.toString() + "Error fetching data from servers. REASON: You have issued more than 150 requests in the last minute, please slow down.");
				}else {
					format(requester, ChatColor.RED.toString() + "Error fetching data from servers. REASON: Unexpected error, we've never seen this before!");
				}
				
			}
			
			
			
			
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	
	public void format(Player p, String msg) {
		
		p.sendMessage(ChatColor.BOLD + ChatColor.DARK_GREEN.toString() + "["+ChatColor.LIGHT_PURPLE.toString()+"PlayerReport"+ChatColor.DARK_GREEN.toString()+"] "+ChatColor.RESET.toString()+ msg);
		
	}
	
	
}
