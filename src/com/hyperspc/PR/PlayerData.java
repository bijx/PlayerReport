package com.hyperspc.PR;

import java.math.BigDecimal;
import java.util.Map;

import com.google.gson.Gson;

public class PlayerData {
	private String playerName;
	private String country, countryCode, region, regionName, city, zip, lat, lon, timezone, isp, org, as,ip;
	private String lastPlay, uuid; 
	int online; 
	boolean licensed;
	double totalPlay;
	
	public PlayerData(String player, String[] data, String statsData) {
		super();
		this.playerName = player;
		this.country = data[1];
		this.countryCode = data[2];
		this.region = data[3];
		this.regionName = data[4];
		this.city = data[5];
		this.zip = data[6];
		this.lat = data[7];
		this.lon = data[8];
		this.timezone = data[9];
		this.isp = data[10];
		this.org = data[11];
		this.as = data[12];
		this.ip = data[13];
		
		Map<String, Object> js = new Gson().fromJson(statsData, Map.class);
		String jsString = js.get("data").toString();
		Map js1 = new Gson().fromJson(jsString, Map.class);
		String lastPlay = new BigDecimal(js1.get("last_play").toString()).toPlainString();
		int online = (int) Double.parseDouble(js1.get("online").toString());
		int licensed = (int) Double.parseDouble(js1.get("license").toString());
		String uuid = js1.get("uuid").toString();
		String totalPlay = new BigDecimal(js1.get("total_time_play").toString()).toPlainString();
		if(licensed == 1) {
			this.licensed = true;
		}else {
			this.licensed = false;
		}
		this.lastPlay = lastPlay;
		this.online = online;
		this.uuid = uuid;
		this.totalPlay = Math.round(Double.parseDouble(totalPlay)/60.0);
		
	}
	
	

	public double getTotalPlay() {
		return totalPlay;
	}



	public void setTotalPlay(double totalPlay) {
		this.totalPlay = totalPlay;
	}



	public String getLastPlay() {
		return lastPlay;
	}



	public void setLastPlay(String lastPlay) {
		this.lastPlay = lastPlay;
	}



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public int getOnline() {
		return online;
	}



	public void setOnline(int online) {
		this.online = online;
	}



	public boolean getLicensed() {
		return licensed;
	}



	public void setLicensed(boolean licensed) {
		this.licensed = licensed;
	}



	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getAs() {
		return as;
	}

	public void setAs(String as) {
		this.as = as;
	}

	
}
