package de.woock.ddd.stattauto.gui.callcenter.entity.mitglied;

import java.time.temporal.Temporal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
public class Mitglied {
	
	private Long id;
	private VollerName vollerName;
	private String     mitgliedsId;
	private String     password;
	private Adresse    adresse;
	private String     geburtsdatum;
	private Telefon    telefon;
	
	public Mitglied() {}
	
	public Mitglied(VollerName vollerName, String mitgliedsId, Adresse adresse, Temporal geburtsdatum, Telefon telefon) {
		this.vollerName   = vollerName;
		this.mitgliedsId  = String.valueOf(vollerName.getVorname().charAt(0)) + vollerName.getName().toLowerCase();
		this.password     = vollerName.getVorname();
		this.adresse      = adresse;
		this.geburtsdatum = geburtsdatum.toString();
		this.telefon      = telefon;
	}	
}
