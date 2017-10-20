package de.woock.ddd.stattauto.gui.callcenter.entity.station;

import org.springframework.hateoas.ResourceSupport;

public class StationsResource extends ResourceSupport {
	
	private Long             stationsId;
	private Auswahlkriterien auswahlkriterien;
	
	public void setStationsId(Long stationsId) {
		this.stationsId = stationsId;
	}

	public Long getStationsId() {
		return stationsId;
	}

	public void setAuswahlkriterien(Auswahlkriterien auswahlkriterien) {
		this.auswahlkriterien = auswahlkriterien;
	}
	
	public Auswahlkriterien getAuswahlkriterien() {
		return auswahlkriterien;
	}
}
