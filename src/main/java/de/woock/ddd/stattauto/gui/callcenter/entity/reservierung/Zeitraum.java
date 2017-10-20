/*
 * Copyright oose innovative Informatik GmbH All Rights Reserved.
 *
 * This software is the proprietary information of oose.de GmbH
 * Use is subject to license terms.
 * 
 * http://www.oose.de
 */
package de.woock.ddd.stattauto.gui.callcenter.entity.reservierung;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Zeitraum implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static final long MS_PRO_S = 1000;
	private static final long S_PRO_MIN = 60;
	private static final long MIN_PRO_H = 60;
	private static final long MS_PRO_H = MS_PRO_S*S_PRO_MIN*MIN_PRO_H;
	private static final long H_PRO_D = 24;
	private static final long D_PRO_W = 7;
	
	
	
	private Date startZeit=null;
	
	private Date endZeit=null;

	Zeitraum() {
		
	}
	
	public Zeitraum(Date startZeit, Date endZeit) {
		this.startZeit = startZeit;
		this.endZeit = endZeit;
	}
	
	public Zeitraum(String startZeit, String endZeit) {
		try {
			SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			this.startZeit= parser.parse(startZeit);
			this.endZeit= parser.parse(endZeit);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Ungültiges Datum");
		}
	}
	
	public boolean schneidet(Zeitraum z) {
		return (!(startZeit.after(z.endZeit) || (endZeit.before(z.startZeit))));
	}
	
	public double getStunden() {
		return ((double)(endZeit.getTime()-startZeit.getTime())) / MS_PRO_H; 
	}
	
	public double getTage() {
		return getStunden() / H_PRO_D; 
	}
	
	public double getWochen() {
		return getTage() / D_PRO_W; 
	}
}
