package com.hyperspc.PR;

import java.io.Reader;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import com.google.common.graph.ElementOrder.Type;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class test {

	public static void main(String[] args) {
		String yourJson = "{\"status\":\"ok\",\"data\":{\"last_play\":1521840539,\"online\":0,\"total_time_play\":45640,\"license\":1,\"name\":\"bijx\",\"uuid\":\"e2acb299130a471fba07308afead69da\"}}";
		
		Map<String, Object> js = new Gson().fromJson(yourJson, Map.class);
		String jsString = js.get("data").toString();
		Map js1 = new Gson().fromJson(jsString, Map.class);
		String lastPlay = new BigDecimal(js1.get("last_play").toString()).toPlainString();
		int online = (int) Double.parseDouble(js1.get("online").toString());
		int licensed = (int) Double.parseDouble(js1.get("license").toString());
		String uuid = js1.get("uuid").toString();
		String totalPlay = new BigDecimal(js1.get("total_time_play").toString()).toPlainString();
		System.out.println(js1.toString());
	}

}