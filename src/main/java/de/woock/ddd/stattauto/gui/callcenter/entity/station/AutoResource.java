package de.woock.ddd.stattauto.gui.callcenter.entity.station;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoResource<A> extends ResourceSupport implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long           AutoId;
	private String         kennung;
	private String         typ;
	private FahrzeugKlasse klasse;
	private String         details;
	private GPS            position;
	
	public String getFormatedDetails() {
		return String.format(String.format("Details:\n%s", details));
	}

}
