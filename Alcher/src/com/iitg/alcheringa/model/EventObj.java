package com.iitg.alcheringa.model;

public class EventObj {

	// private variables
	private String id;
	private String name;
	private String timeDay0;
	private String timeDay1;
	private String timeDay2;
	private String timeDay3;
	private String venue;
	private String type;
	private String venueId;
	private String description;

	// Empty constructor
	public EventObj() {

	}

	// constructor
	public EventObj(String id, String name, String time0,
			String time1, String time2, String time3, String venue,
			String type, String venueid, String description) {
		this.id = id;
		this.name = name;
		this.timeDay0 = time0;
		this.timeDay1 = time1;
		this.timeDay2 = time2;
		this.timeDay3 = time3;
		this.venue = venue;
		this.type = type;
		this.venueId = venueid;
		this.description = description;

	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeDay0() {
		return timeDay0;
	}

	public void setTimeDay0(String timeDay0) {
		this.timeDay0 = timeDay0;
	}

	public String getTimeDay1() {
		return timeDay1;
	}

	public void setTimeDay1(String timeDay1) {
		this.timeDay1 = timeDay1;
	}

	public String getTimeDay2() {
		return timeDay2;
	}

	public void setTimeDay2(String timeDay2) {
		this.timeDay2 = timeDay2;
	}

	public String getTimeDay3() {
		return timeDay3;
	}

	public void setTimeDay3(String timeDay3) {
		this.timeDay3 = timeDay3;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVenueId() {
		return venueId;
	}

	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
