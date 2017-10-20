package de.woock.ddd.stattauto.gui.callcenter.entity.station;

import lombok.Data;

@Data
public class Spezifikation {
	private StationMap map;
	private String     beschreibung;
	private GPS        position;
	private String     oepnv;
	
	public Spezifikation () { }

	public Spezifikation(String beschreibung, GPS position, String oepnv) {
		this.beschreibung = beschreibung;
		this.position = position;
		this.oepnv = oepnv;
	}
	

}
