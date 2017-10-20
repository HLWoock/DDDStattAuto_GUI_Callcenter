package de.woock.ddd.stattauto.gui.callcenter.event;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class MitgliedAuthentifizierenEvent extends ApplicationEvent {
	
	public String mitgliedId;
	
	public MitgliedAuthentifizierenEvent(Object source, String mitgliedId) {
		super(source);
		this.mitgliedId = mitgliedId;
	}

}
