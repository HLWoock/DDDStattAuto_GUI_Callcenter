package de.woock.ddd.stattauto.gui.callcenter.eventlistener;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;

import de.woock.ddd.stattauto.gui.callcenter.controller.OverviewController;
import de.woock.ddd.stattauto.gui.callcenter.entity.station.Auto;
import de.woock.ddd.stattauto.gui.callcenter.event.FreieFahrzeugeSuchenEvent;
import de.woock.ddd.stattauto.gui.callcenter.view.VerfuegbareFahrzeugeView;
import de.woock.ddd.stattauto.gui.fuhrpark.entity.auto.AutoResource;

@Controller
public class FreieFahrzeugeSuchenEventListener implements ApplicationListener<FreieFahrzeugeSuchenEvent> {
	
	private VerfuegbareFahrzeugeView viewVerfuegbareFahrzeuge;
	private OverviewController       controller;
	
	public FreieFahrzeugeSuchenEventListener(OverviewController controller, VerfuegbareFahrzeugeView viewVerfuegbareFahrzeuge) {
		this.controller               = controller;
		this.viewVerfuegbareFahrzeuge = viewVerfuegbareFahrzeuge;
	}

	@Override
	public void onApplicationEvent(FreieFahrzeugeSuchenEvent event) {
		List<AutoResource<Auto>> freieFahrzeuge = controller.holeFreieFahrzeugAnStation(event.dateVon, event.dateBis, event.stadt, event.stadtteil, event.standort);
		viewVerfuegbareFahrzeuge.setValues(freieFahrzeuge);
	}
}
