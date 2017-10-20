package de.woock.ddd.stattauto.gui.callcenter.entity.station;

import lombok.Data;

@Data
public class Auto {

	Long        id;
	String      kennung;
	
	public Auto(){
	}

	public Auto(String kennung) {
		this.kennung     = kennung;
	}
}
