package de.woock.ddd.stattauto.gui.callcenter.event;

import org.springframework.context.ApplicationEvent;

import de.woock.ddd.stattauto.gui.callcenter.entity.mitglied.Mitglied;

@SuppressWarnings("serial")
public class MitgliedAuthentifiziertEvent extends ApplicationEvent {
	
	public Mitglied mitglied;
	
	public MitgliedAuthentifiziertEvent(Object source, Mitglied mitglied) {
		super(source);
		this.mitglied = mitglied;
	}

}
