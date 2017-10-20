package de.woock.ddd.stattauto.gui.callcenter.entity.station;

import lombok.Data;

@Data
public class GPS {
	double lat;
	double lng;
	
	public GPS() {}
	
	public GPS(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	
}
