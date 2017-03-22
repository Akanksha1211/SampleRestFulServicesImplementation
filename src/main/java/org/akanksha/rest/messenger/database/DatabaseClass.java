package org.akanksha.rest.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.akanksha.rest.messenger.model.Message;
import org.akanksha.rest.messenger.model.Profile;

//Stub class for database
public class DatabaseClass {

	private static Map<Long, Message> messages=new HashMap<>();
	private static Map<String, Profile> profiles =new HashMap<>();
	
	public static Map<Long, Message> getMessages(){
		return messages;
	}
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}
}
