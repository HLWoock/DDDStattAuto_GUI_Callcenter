package de.woock.ddd.stattauto.gui.fuhrpark.entity.auto;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class Bild implements Serializable {
	
	private AutoMap map;
	
	public Bild () { }
}
