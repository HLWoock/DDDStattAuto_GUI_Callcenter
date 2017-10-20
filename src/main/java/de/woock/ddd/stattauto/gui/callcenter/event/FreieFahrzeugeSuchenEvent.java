package de.woock.ddd.stattauto.gui.callcenter.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import de.woock.ddd.stattauto.gui.callcenter.entity.reservierung.Reservierung;

@SuppressWarnings("serial")
public class FreieFahrzeugeSuchenEvent extends ApplicationEvent {
	
	public Reservierung reservierung;
	public Date       dateVon; 
	public Date       dateBis;  
	public String       stadt;
	public String       stadtteil;
	public String       standort;
	
	public FreieFahrzeugeSuchenEvent(Object source, Date dateVon, Date dateBis, String stadt, String stadtteil, String standort) {
		super(source);
		this.dateVon   = dateVon;
		this.dateBis   = dateBis;
		this.stadt     = stadt;
		this.stadtteil = stadtteil;
		this.standort  = standort;
				
	}
}
