package de.woock.ddd.stattauto.gui.callcenter.entity.station;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Station {
	
	private Long stationsId;
	
	private List<Auto> fahrzeuge = new ArrayList<>();
	
	private Auswahlkriterien auswahlkriterien;
	private Spezifikation    spezifikation;
	
	public Station () {}
	
	public Station(Auswahlkriterien auswahlkriterien, Spezifikation spezifikation) {
		this.auswahlkriterien = auswahlkriterien;
		this.spezifikation    = spezifikation;
	}
	
}
