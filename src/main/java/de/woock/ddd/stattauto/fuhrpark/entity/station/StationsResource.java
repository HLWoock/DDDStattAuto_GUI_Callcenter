package de.woock.ddd.stattauto.fuhrpark.entity.station;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationsResource<S> extends ResourceSupport {
	
	private Long             stationsId;
	private Auswahlkriterien auswahlkriterien;
	private Spezifikation    spezifikation;
	
	// F�r JavaFX TableView n�tig. Nicht l�schen
	public String getStadt() {
		return auswahlkriterien.getStadt();
	}
	
	public String getStadtteil() {
		return auswahlkriterien.getStadtteil();
	}
	
	public String getStandort() {
		return auswahlkriterien.getStandort();
	}
	
	public String getKuerzel() {
		return auswahlkriterien.getKuerzel();
	}
}
