package de.woock.ddd.stattauto.gui.callcenter.entity.reservierung;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MietObjekt {
	
	private Long mietObjektId;

	public MietObjekt() { }
	
	public MietObjekt(Long mietObjektId) {
		this.mietObjektId = mietObjektId;
	}
}
