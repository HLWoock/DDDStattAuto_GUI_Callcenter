package de.woock.ddd.stattauto.gui.callcenter.event;

import org.springframework.context.ApplicationEvent;

import de.woock.ddd.stattauto.gui.callcenter.entity.reservierung.Reservierung;

@SuppressWarnings("serial")
public class ReservierungAngelegtEvent extends ApplicationEvent {
	
	public Reservierung reservierung;
	
	public ReservierungAngelegtEvent(Object source, Reservierung reservierung) {
		super(source);
		this.reservierung = reservierung;
	}

}
