package de.woock.ddd.stattauto.gui.callcenter.eventlistener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;

import de.woock.ddd.stattauto.gui.callcenter.controller.OverviewController;
import de.woock.ddd.stattauto.gui.callcenter.entity.mitglied.Mitglied;
import de.woock.ddd.stattauto.gui.callcenter.event.MitgliedAuthentifizierenEvent;
import de.woock.ddd.stattauto.gui.callcenter.event.MitgliedAuthentifiziertEvent;

@Controller
public class MitgliedAuthentifizierenEventListener implements ApplicationListener<MitgliedAuthentifizierenEvent>, 
                                                              ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;
	private OverviewController        controller;

	public MitgliedAuthentifizierenEventListener(OverviewController controller) {
		this.controller = controller;
	}
	
	@Override
	public void onApplicationEvent(MitgliedAuthentifizierenEvent event) {
		String mitgliedsId = event.mitgliedId;
		Mitglied mitglied  = controller.getMitglied(mitgliedsId);
		MitgliedAuthentifiziertEvent mitgliedAuthentifiziertEvent = new MitgliedAuthentifiziertEvent(this, mitglied);
		publisher.publishEvent(mitgliedAuthentifiziertEvent);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		publisher = applicationEventPublisher;
	}

}
