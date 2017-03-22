package org.akanksha.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.akanksha.rest.messenger.database.DatabaseClass;
import org.akanksha.rest.messenger.model.Profile;

public class ProfileService {
	
	public Map<String, Profile> profiles=DatabaseClass.getProfiles();

	public ProfileService(){
		profiles.put("Akanksha1", new Profile(1L,"Akanksha","Akanksha", "Avhad"));
		profiles.put("Saily", new Profile(2L,"Saily","Saily", "Jawkar"));
		profiles.put("Asas", new Profile(3L,"Asas","Asas", "Syed"));
		
		
	}
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getId()<=0){
			return null;
		}
		else{
			profiles.put(profile.getProfileName(), profile);
			return profile;
		}
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
}
