package de.woock.ddd.stattauto.gui.callcenter.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

@SuppressWarnings("serial")
@View
public class TabView extends JFrame {
	
	private MitgliedAuthentifizierenView viewMitgliedAuthentifizieren;
	
	private JTabbedPane tabbedPane;
	
	public TabView(MitgliedAuthentifizierenView mitgliedAuthentifizierenView) {
		this.viewMitgliedAuthentifizieren = mitgliedAuthentifizierenView;
	}
	
	public void removeTabs() {
		while (tabbedPane.getTabCount() > 1) {
			tabbedPane.removeTabAt(1);
		}
	}
	
	public void addTab(String title, Component component) {
		tabbedPane.addTab(title, component);
	}
	
	@PostConstruct
	private void init() {
		setTitle("StattAuto CallCenter");
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 1));
		add(createTabPane(), BorderLayout.CENTER);
		setSize(480, 159);
		setLocationRelativeTo(null);
		UIManager.put("swing.boldMetal", Boolean.TRUE);
		setVisible(true);		
	}
	
	private JPanel createTabPane() {
		JPanel tabPane = new JPanel(new GridLayout(1, 1));
		tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Mitglied Authentifizieren", viewMitgliedAuthentifizieren.buildPanel());
		
		tabPane.add(tabbedPane);
		
		return tabPane;
	}
}
