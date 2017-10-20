package de.woock.ddd.stattauto.gui.callcenter.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import de.woock.ddd.stattauto.gui.callcenter.view.TabView;
import de.woock.ddd.stattauto.gui.fuhrpark.entity.auto.Auto;
import de.woock.ddd.stattauto.gui.fuhrpark.entity.auto.AutoResource;

@Component
public class NachrichtenReceiver {
	
	@Autowired TabView view;

	@JmsListener(destination = "DefektmeldungAuto", containerFactory = "myFactory")
	public void receiveMessage(AutoResource<Auto> auto) {
		System.out.println("!!!");
		view.setTitle(auto.getKennung());
		System.out.println("Received <" + auto.getKennung() + ">");
	}
}
