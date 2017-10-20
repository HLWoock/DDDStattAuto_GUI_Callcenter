package de.woock.ddd.stattauto.gui.callcenter.entity.station;

import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationMap {

	private byte[] map;
	
	public StationMap(byte[] map) {
		this.map = map;
	}
	
	public StationMap() {}
	
	public byte[] getMap(String kuerzel) throws IOException {
		return map;
	}
}
