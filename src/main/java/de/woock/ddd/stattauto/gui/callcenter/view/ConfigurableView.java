package de.woock.ddd.stattauto.gui.callcenter.view;

import javax.swing.JComponent;

public interface ConfigurableView {
	public <T> void   setValues(T dto);
	public JComponent buildPanel();
}
