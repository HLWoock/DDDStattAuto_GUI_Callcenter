package de.woock.ddd.stattauto.gui.callcenter.eventlistener;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;

import com.jgoodies.animation.Animator;

import de.woock.ddd.stattauto.gui.callcenter.animation.SimpleSizeAnimation;
import de.woock.ddd.stattauto.gui.callcenter.entity.mitglied.Mitglied;
import de.woock.ddd.stattauto.gui.callcenter.event.MitgliedAuthentifiziertEvent;
import de.woock.ddd.stattauto.gui.callcenter.view.FahrzeugReservierenView;
import de.woock.ddd.stattauto.gui.callcenter.view.MitgliedAuthentifizierenView;
import de.woock.ddd.stattauto.gui.callcenter.view.TabView;
import de.woock.ddd.stattauto.gui.callcenter.view.VerfuegbareFahrzeugeView;

@Controller
public class MitgliedAuthentifiziertEventListener implements ApplicationListener<MitgliedAuthentifiziertEvent> {
	
	@Autowired private TabView                      tabView;
	@Autowired private MitgliedAuthentifizierenView viewMitgliedAuthentifizieren;
	@Autowired private FahrzeugReservierenView      viewFahrzeugReservieren;
	@Autowired private VerfuegbareFahrzeugeView     viewVerfuegbareFahrzeuge;


	final int DEFAULT_RATE = 30;
	final int DURATION     = 250;
	
	private Animator animator;
	
	@PostConstruct
	public void post(){
		animator = new Animator(new SimpleSizeAnimation(DURATION, tabView), DEFAULT_RATE);
	}
	
	@Override
	public void onApplicationEvent(MitgliedAuthentifiziertEvent event) {
		Mitglied mitglied = event.mitglied;
		if (mitglied != null) {
			viewMitgliedAuthentifizieren.setValues(mitglied);
			
			animator.start();
			
			tabView.removeTabs();
			tabView.addTab("Fahrzeug Reservieren", viewFahrzeugReservieren.buildPanel());
			tabView.addTab("Verfügbare Fahrzeuge", viewVerfuegbareFahrzeuge.buildPanel());
		}
	}

}
