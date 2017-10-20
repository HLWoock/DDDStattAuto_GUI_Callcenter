package de.woock.ddd.stattauto.gui.callcenter.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.jgoodies.forms.builder.ListViewBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.jsdl.component.JGComponentFactory;

import de.woock.ddd.stattauto.gui.callcenter.controller.OverviewController;
import de.woock.ddd.stattauto.gui.callcenter.entity.station.Auto;
import de.woock.ddd.stattauto.gui.fuhrpark.entity.auto.AutoResource;

@View
public final class VerfuegbareFahrzeugeView  implements ConfigurableView {

	private Logger log = Logger.getLogger(VerfuegbareFahrzeugeView.class);
	
	private OverviewController           controller;
	private FahrzeugReservierenView      fahrzeugReservierenView;
	private MitgliedAuthentifizierenView mitgliedAuthentifizierenView;
	
	
	public VerfuegbareFahrzeugeView(OverviewController controller, FahrzeugReservierenView fahrzeugReservierenView, MitgliedAuthentifizierenView mitgliedAuthentifizierenView) {
		this.controller                   = controller;
		this.fahrzeugReservierenView      = fahrzeugReservierenView;
		this.mitgliedAuthentifizierenView = mitgliedAuthentifizierenView;
	}
	
	private DefaultTableModel model;

    private JTable  tbFahrzeuge;
    private JButton bnReservieren;
    
    public JComponent buildPanel() {
	initComponents();
	
	return ListViewBuilder.create()
	    	.padding(Paddings.DIALOG)
	    	.labelText("Fahrzeuge:")
	    	.listView(tbFahrzeuge)
	    	.listBar(bnReservieren)
	    	.build();
	}

    @SuppressWarnings("unchecked")
	@Override
	public <T> void setValues(T dto) {
    	List<AutoResource<Auto>> autos = null;
    	log.debug("setValues");
    	((DefaultTableModel) tbFahrzeuge.getModel()).setRowCount(0);
    	if (dto instanceof List) {
    		DefaultTableModel model = (DefaultTableModel) tbFahrzeuge.getModel();
    		autos = (List<AutoResource<Auto>>) dto;
    		for (AutoResource<Auto> auto : autos) {
    			model.addRow(new Object[]{auto.getAutoId(), auto.getKennung(), auto.getKlasse() ,auto.getTyp()});
			}
    	}
	}

	private void initComponents() {
    	JGComponentFactory factory = JGComponentFactory.getCurrent();
    	model = new DefaultTableModel(new Object[][] {},
    			new Object[] { "Id", "Kennzeichen", "Klasse", "Typ"});
    	
    	tbFahrzeuge = factory.createReadOnlyTable(model);
    	tbFahrzeuge.setAutoCreateRowSorter(true);
    	ListSelectionModel selectionModel = tbFahrzeuge.getSelectionModel();
    	selectionModel.addListSelectionListener(controller);
    	
    	bnReservieren = factory.createButton("Fahrzeug Reservieren");
    	bnReservieren.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tbFahrzeuge.getModel();
				Long fahrzeugId = (Long) model.getValueAt(tbFahrzeuge.getSelectedRow(), 0);
				System.out.println(String.format("reserviere: %s", fahrzeugId));
				Long von = ((Date)fahrzeugReservierenView.txDatumVon.getModel().getValue()).getTime();
				Long bis = ((Date)fahrzeugReservierenView.txDatumBis.getModel().getValue()).getTime();
				Long mitgliedId = Long.valueOf((mitgliedAuthentifizierenView.txId.getText()));
				controller.reserviereFahrzeug(mitgliedId, von, bis, fahrzeugId);
			}
		});
    }

}