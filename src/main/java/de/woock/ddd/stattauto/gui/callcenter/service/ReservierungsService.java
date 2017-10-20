package de.woock.ddd.stattauto.gui.callcenter.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.woock.ddd.stattauto.gui.callcenter.entity.mitglied.Mitglied;
import de.woock.ddd.stattauto.gui.callcenter.entity.reservierung.Reservierung;
import de.woock.ddd.stattauto.gui.callcenter.entity.station.Auto;
import de.woock.ddd.stattauto.gui.callcenter.entity.station.StationsResource;
import de.woock.ddd.stattauto.gui.fuhrpark.entity.auto.AutoResource;  

@Service
public class ReservierungsService {
	
	private static final String MITGLIED_MIT_ID             = "%s/mitglieder-service/Mitglieder/%s";
	private static final String ALLE_STATIONEN              = "%s/fuhrpark-service/Stationen/stationen";
	private static final String STATIONS_ID                 = "%s/fuhrpark-service/Stationen/station/%s/%s/%s/stationsId";
	private static final String FAHRZEUGE_AN_STATION_MIT_ID = "%s/fuhrpark-service/Stationen/station/id/%d/fahrzeuge";
	private static final String IST_MIETOBJEKT_FREI         = "%s/reservierung-service/Reservierungen/istMietobjektFrei/%d/%d/%d";
	private static final String STATION_EINRICHTEN          = "%s/reservierung-service/Reservierungen/einrichten";

	@Autowired DiscoveryClient dc;
	@Autowired RestTemplate    restTemplate;

	
	public Mitglied getMitglied(String mitgliedsId) {
		return restTemplate.getForObject(String.format(MITGLIED_MIT_ID, getREST_APIGateway(), mitgliedsId), Mitglied.class); 
	}

	public List<StationsResource> zeigeStationen() {
		return  Arrays.asList(restTemplate.getForObject(String.format(ALLE_STATIONEN, getREST_APIGateway()), StationsResource[].class));
	}
	
	public Long holeStationIdVomServer(String stadt, String stadtteil, String standort) {
		return restTemplate.getForObject(String.format(STATIONS_ID, getREST_APIGateway(),stadt, stadtteil, standort), Long.class);
	}
	
	public List<AutoResource<Auto>> holeFahrzeugeFuerStationMitId(Long stationId) {
		return restTemplate.exchange(String.format(FAHRZEUGE_AN_STATION_MIT_ID, getREST_APIGateway(), stationId), HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<AutoResource<Auto>>>() {}).getBody();
	}

	public List<AutoResource<Auto>> holeFreieFahrzeugAnStation(Date dateVon, Date dateBis, String stadt, String stadtteil, String standort) {
		Long       stationId                          = holeStationIdVomServer(stadt, stadtteil, standort);
		List<AutoResource<Auto>> potentiellefahrzeuge = holeFahrzeugeFuerStationMitId(stationId);
		
		return potentiellefahrzeuge.stream()
				                   .filter(f -> restTemplate.getForObject(String.format(IST_MIETOBJEKT_FREI, getREST_APIGateway(), dateVon.getTime(), dateBis.getTime(), f.getAutoId()), Boolean.class))
				                   .collect(Collectors.toList());
	}

	public void reserviere(Reservierung reservierung) {
		HttpEntity<Reservierung> request = new HttpEntity<Reservierung>(reservierung);
		restTemplate.postForObject(String.format(STATION_EINRICHTEN, getREST_APIGateway()), request, Reservierung.class);
		
	}

	private String getREST_APIGateway() {
		return dc.getInstances("REST_API_GATEWAY").get(0).getUri().toString();
	}
}
