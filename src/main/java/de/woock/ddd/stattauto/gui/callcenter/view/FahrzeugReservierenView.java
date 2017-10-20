package de.woock.ddd.stattauto.gui.callcenter.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.Paddings;

import de.woock.ddd.stattauto.gui.callcenter.controller.OverviewController;
import de.woock.ddd.stattauto.gui.callcenter.entity.station.StationsResource;
import de.woock.ddd.stattauto.gui.callcenter.event.FreieFahrzeugeSuchenEvent;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

@View
public final class FahrzeugReservierenView implements ApplicationEventPublisherAware,
                                                      ConfigurableView,
                                                      ActionListener   {
	
	private static Logger log = Logger.getLogger(FahrzeugReservierenView.class);
	private FahrzeugReservierenView source;
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	public  JDatePickerImpl    txDatumVon   = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
	public  JDatePickerImpl    txDatumBis   = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
	public  JComboBox<String>  cbStadt      = new JComboBox<>();
	public  JComboBox<String>  cbStadtteil  = new JComboBox<>();
	public  JComboBox<String>  cbStandort   = new JComboBox<>();
	private OverviewController controller;
	private Set<String> stadtteile = new HashSet<>();
	private Set<String> standorte  = new HashSet<>();
	
	private JTextField         txZeitBis    = new JTextField();
	private JTextField         txZeitVon    = new JTextField();
	private JButton            bnSuche      = new JButton("Fahrzeuge Suchen");

	private ApplicationEventPublisher applicationEventPublisher;
	
	public FahrzeugReservierenView(OverviewController controller) {
		this.controller = controller;
	}
	
	@PostConstruct
	public void setSource() {
		this.source = this;
	}
	
    public JComponent buildPanel() {
	initComponents();
	fuelleComboBoxes();
	
	return FormBuilder.create()
	                  .columns("right:[40dlu,pref], 3dlu, 70dlu, 7dlu, right:[40dlu,pref], 3dlu, 70dlu")
	                  .rows("7*(p, 3dlu, p, 3dlu, p, 3dlu, p, 9dlu, p, 3dlu)")
	     	          .padding(Paddings.DIALOG)
	
	                  .addSeparator("Zeitraum").xyw(1,  1, 7)
	                  	.add("Begin")          .xy (1,  3)   .add("Ende")     .xy (5,  3)   
	                  	.add("Datum:")         .xy (1,  5)   .add(txDatumVon) .xy (3,  5)
	                  	.add("Datum:")         .xy (5,  5)   .add(txDatumBis) .xy (7,  5)
	                  	.add("Uhrzeit:")       .xy (1,  7)   .add(txZeitVon)  .xy (3,  7)
	                  	.add("Uhrzeit:")       .xy (5,  7)   .add(txZeitBis)  .xy (7,  7)
	                  .addSeparator("Station") .xyw(1,  9, 7)
	                  	.add("Stadt:")         .xy (1, 11)   .add(cbStadt)    .xy (3, 11)
	                  	.add("Stadtteil:")     .xy (1, 13)   .add(cbStadtteil).xy (3, 13)
	                  	.add("Standort:")      .xy (5, 13)   .add(cbStandort) .xy (7, 13)
					  .add(bnSuche)            .xyw(3, 17, 3)
	                  .build();     
	}

	public <T> void setValues(T dto) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = ((JComponent)e.getSource()).getName();
		switch (name) {
		case "cbStadt":
			stadtChanged(controller.zeigeStationen());
			break;
		case "cbStadtteil":
			stadtteilChanged(controller.zeigeStationen());
			break;
		default:
			break;
		}		
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	private void initComponents() {
		bnSuche    .setName("bnSuche");
		cbStadt    .setName("cbStadt");
		cbStadtteil.setName("cbStadtteil");
		cbStandort .setName("cbStandort");
		
		bnSuche    .addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				log.debug("suche freie Fahrzeuge...");
				String stadt     = cbStadt.getSelectedItem().toString();
				String stadtteil = cbStadtteil.getSelectedItem().toString();
				String standort  = cbStandort.getSelectedItem().toString();
			    Date   dateVon   = (Date) txDatumVon.getModel().getValue();
			    Date   dateBis   = (Date) txDatumBis.getModel().getValue();
				applicationEventPublisher.publishEvent(new FreieFahrzeugeSuchenEvent(source, dateVon, dateBis, stadt, stadtteil, standort));
			}
		});
		cbStadt    .addActionListener(this);
		cbStadtteil.addActionListener(this);
		cbStandort .addActionListener(this);
    }

	private void fuelleComboBoxes() {
		holeStationenVomServer().forEach(s -> {
		                                	      String stadt = s.getAuswahlkriterien().getStadt();
		                                          if (((DefaultComboBoxModel<String>)cbStadt.getModel()).getIndexOf(stadt) == -1) {
		                                              cbStadt.addItem(stadt);
		                                          }
		                                      });
	}

	private List<StationsResource> holeStationenVomServer() {
		return (List<StationsResource>)controller.zeigeStationen();
	}
	
	private void stadtChanged(List<StationsResource> stationen) {
		String stadtSelection = cbStadt.getSelectedItem().toString();
		
		stadtteile.clear();
		((List<StationsResource>)stationen).stream()
                                           .filter(s -> s.getAuswahlkriterien().getStadt().equals(stadtSelection))
                                           .forEach(s -> stadtteile.add(s.getAuswahlkriterien().getStadtteil()));
		cbStadtteil.removeAllItems();
		List<String> sortierteStadtteile = new ArrayList<>(stadtteile);
		Collections.sort(sortierteStadtteile);
		sortierteStadtteile.stream().forEach(s -> cbStadtteil.addItem(s));
	}

	private void stadtteilChanged(List<StationsResource> stationen) {
		String stadtSelection     = cbStadt.getSelectedItem().toString();
		if (cbStadtteil.getSelectedItem() != null) {
			String stadtteilSelection = cbStadtteil.getSelectedItem().toString();
			standorte.clear();
			((List<StationsResource>)stationen).stream()
			                                   .filter(s -> s.getAuswahlkriterien().getStadtteil().equals(stadtteilSelection))
  			                                   .filter(s -> s.getAuswahlkriterien().getStadt().equals(stadtSelection))
			                                   .forEach(s -> standorte.add(s.getAuswahlkriterien().getStandort()));
			cbStandort.removeAllItems();
			standorte.stream().forEach(s -> cbStandort.addItem(s));
		} else {
			cbStadtteil.removeAllItems();
		}
	}	
}
