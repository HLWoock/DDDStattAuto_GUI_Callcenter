package de.woock.ddd.stattauto.gui.callcenter.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;

import de.woock.ddd.stattauto.gui.callcenter.entity.mitglied.Mitglied;
import de.woock.ddd.stattauto.gui.callcenter.entity.reservierung.MietObjekt;
import de.woock.ddd.stattauto.gui.callcenter.entity.reservierung.Mieter;
import de.woock.ddd.stattauto.gui.callcenter.entity.reservierung.Reservierung;
import de.woock.ddd.stattauto.gui.callcenter.entity.reservierung.Zeitraum;
import de.woock.ddd.stattauto.gui.callcenter.entity.station.Auto;
import de.woock.ddd.stattauto.gui.callcenter.entity.station.StationsResource;
import de.woock.ddd.stattauto.gui.callcenter.event.ReservierungAngelegtEvent;
import de.woock.ddd.stattauto.gui.callcenter.service.ReservierungsService;
import de.woock.ddd.stattauto.gui.fuhrpark.entity.auto.AutoResource;

@Controller
public class OverviewController implements ApplicationEventPublisherAware,
                                           ListSelectionListener {

	private ReservierungsService         reservierungsService;
	private List<StationsResource>       stationen;
	private ApplicationEventPublisher    publisher;
	
	public OverviewController(ReservierungsService reservierungsService         ) {
		this.reservierungsService = reservierungsService;
	}

	@PostConstruct
	public void post(){
		stationen = reservierungsService.zeigeStationen();
	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		publisher = applicationEventPublisher;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Reservierung reservierung = null; //mitglied.reservierungen.get(e.getFirstIndex());
		publisher.publishEvent(new ReservierungAngelegtEvent(this, reservierung));
	}

	public Mitglied getMitglied(String mitgliedsId) {
		return reservierungsService.getMitglied(mitgliedsId);
	}
	
	public List<StationsResource> zeigeStationen() {
		return stationen;
	}
	
	public List<AutoResource<Auto>> holeFreieFahrzeugAnStation(Date dateVon, Date dateBis, String stadt, String stadtteil, String standort) {
		return reservierungsService.holeFreieFahrzeugAnStation(dateVon, dateBis, stadt, stadtteil, standort);
	}

	public void reserviereFahrzeug(Long mitgliedId, Long von, Long bis, Long fahrzeugId) {
		Reservierung reservierung = new Reservierung(new Mieter(mitgliedId), new MietObjekt(fahrzeugId), new Zeitraum(new Date(von), new Date(bis)));
		reservierungsService.reserviere(reservierung);
	}
}
