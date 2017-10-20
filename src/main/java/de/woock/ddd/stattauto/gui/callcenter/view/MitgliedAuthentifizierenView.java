package de.woock.ddd.stattauto.gui.callcenter.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.jsdl.common.action.ActionObject;

import de.woock.ddd.stattauto.gui.callcenter.entity.mitglied.Mitglied;
import de.woock.ddd.stattauto.gui.callcenter.event.MitgliedAuthentifizierenEvent;

@View
public final class MitgliedAuthentifizierenView extends ActionObject implements ConfigurableView, ApplicationEventPublisherAware {
	
	private Logger log = Logger.getLogger(MitgliedAuthentifizierenView.class);
	
    private ApplicationEventPublisher applicationEventPublisher;

	private JLabel     txName            = new JLabel();
    private JLabel     txVorname         = new JLabel();
    private JLabel     txAnrede          = new JLabel();
    private JLabel     txGeburtsdatum    = new JLabel();
    private JLabel     txStrasse         = new JLabel();
    private JLabel     txTelMobil        = new JLabel();
    private JLabel     txTitel           = new JLabel();
    public  JLabel     txId              = new JLabel();
    private JButton    bnAuthentifizeren = new JButton("Authentifizieren");
    
    
    public  JTextField txMitgliedsId     = new JTextField();


	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    	this.applicationEventPublisher = applicationEventPublisher;
	}

	public JComponent buildPanel() {
	initComponents();
	
	return FormBuilder.create()
	                  .columns("right:[40dlu,pref], 3dlu, 70dlu, 7dlu, right:[40dlu,pref], 3dlu, 70dlu")
	                  .rows("4*(p, 3dlu, p, 3dlu, p, 3dlu, p, 9dlu, p, 3dlu)")
	     	          .padding(Paddings.DIALOG)
	
	                  .addSeparator("Mitglied")          .xyw(1,  1, 7)
	                  	.add("MitgliedsId:")             .xy (1,  3)   .add(txMitgliedsId)   .xyw(3,  3, 3)
	                  	.add(bnAuthentifizeren)          .xyw(3,  5, 3)
		              
	                  	.addSeparator("Personendaten")   .xyw(1,  7, 7)
	                  	.add(txAnrede)                   .xyw(2,  9, 6)
	                  	.add(txTitel)                    .xyw(2, 11, 6)	                  	
	                  	.add(txVorname)                  .xyw(2, 13, 6)   
	                  	.add(txName)                     .xyw(2, 15, 6)	                  	
	                  	.add(txStrasse)                  .xyw(2, 17, 6)
	                  	.addSeparator("Persönliches")    .xyw(1, 19, 6)
	                  	.add("Geburtsdatum:")            .xy (1, 21)   .add(txGeburtsdatum)      .xy (3, 21)
	                  	.add("EMail:")                   .xy (1, 23)   .add(txTelMobil)          .xyw(3, 23, 3)
	                  	.addSeparator("Internes")        .xyw(1, 25, 6)
	                  	.add("Id:")                      .xy (1, 27)   .add(txId)                .xyw(3, 27, 3)
	
	                  .build();     
	}

	public <T> void setValues(T dto) {
    	Mitglied mitglied = null;
    	if (dto instanceof Mitglied) {
    		mitglied = (Mitglied)dto;
    	}

		log.debug("setValues: Mitglied");
		
		// Personendaten
		txAnrede            .setText(mitglied.getVollerName().getVollenNamen());
		txTitel             .setText(mitglied.getAdresse().volleStrassenbezeichnung());
		txVorname           .setText(mitglied.getAdresse().volleOrtsbezeichnung());
		txName              .setText(mitglied.getTelefon().telFestnetz());
		txStrasse           .setText(mitglied.getTelefon().telMobil());
		
		// Persönliches
		txGeburtsdatum      .setText(mitglied.getGeburtsdatum());
		txTelMobil          .setText(mitglied.getAdresse().getEmail());
		
		// ID
		txId                .setText(mitglied.getId().toString());
	}

	private void initComponents() {
		bnAuthentifizeren.setName("bnAuthentifizeren");
        bnAuthentifizeren.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String mitgliedsId = txMitgliedsId.getText();
				MitgliedAuthentifizierenEvent mitgliedAuthentifiziertEvent = new MitgliedAuthentifizierenEvent(this, mitgliedsId);
				applicationEventPublisher.publishEvent(mitgliedAuthentifiziertEvent);
			}
		});
    }
}