package de.woock.ddd.stattauto.gui.callcenter.entity.reservierung;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mieter {

	private Long mieterId;
	
	public Mieter() { }

	public Mieter(Long mieterId) {
		this.mieterId = mieterId;
	}
	
	
}
