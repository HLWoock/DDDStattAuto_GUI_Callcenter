package de.woock.ddd.stattauto.gui.fuhrpark.entity.auto;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class AutoMap implements Serializable {

	private byte[] map;
	
	public AutoMap(byte[] map) {
		this.map = map;
	}
	
	public AutoMap() {}
	
}
