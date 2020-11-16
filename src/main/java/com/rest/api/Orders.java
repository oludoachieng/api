package com.rest.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Orders {
	
    private Float source_latitude;
    private Float source_longitude;
    private Float destination_latitude;
    private Float destination_longitude;
	
    
    public Orders(Float source_latitude, Float source_longitude, Float destination_latitude,
			Float destination_longitude) {
		this.source_latitude = source_latitude;
		this.source_longitude = source_longitude;
		this.destination_latitude = destination_latitude;
		this.destination_longitude = destination_longitude;
	}
    public Orders() {
		}
    @XmlElement
	public Float getSource_latitude() {
		return source_latitude;
	}
	public void setSource_latitude(Float source_latitude) {
		this.source_latitude = source_latitude;
	}
	@XmlElement
	public Float getSource_longitude() {
		return source_longitude;
	}
	public void setSource_longitude(Float source_longitude) {
		this.source_longitude = source_longitude;
	}
	@XmlElement
	public Float getDestination_latitude() {
		return destination_latitude;
	}
	public void setDestination_latitude(Float destination_latitude) {
		this.destination_latitude = destination_latitude;
	}
	@XmlElement
	public Float getDestination_longitude() {
		return destination_longitude;
	}
	public void setDestination_longitude(Float destination_longitude) {
		this.destination_longitude = destination_longitude;
	}
}