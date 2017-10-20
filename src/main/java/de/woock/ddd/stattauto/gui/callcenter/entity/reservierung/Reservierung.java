package de.woock.ddd.stattauto.gui.callcenter.entity.reservierung;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservierung {
	
	private Long       reservierungsId;
	private Mieter     mieter;
	private MietObjekt mietObjekt;
	private Zeitraum   zeitraum;
	
	public Reservierung() { }
	
	public Reservierung(Mieter mieter, MietObjekt mietObjekt, Zeitraum zeitraum) {
		this.mieter     = mieter;
		this.mietObjekt = mietObjekt;
		this.zeitraum   = zeitraum;
	}
}
