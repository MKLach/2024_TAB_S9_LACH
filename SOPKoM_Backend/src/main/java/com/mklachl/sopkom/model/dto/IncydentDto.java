package com.mklachl.sopkom.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.entity.Incydent;
import java.util.Date;

public class IncydentDto {

    private Long incydentId;
    private String typ;
    
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;
    
    private Long autobusId;
    private Long kierowcaId;
    private Long przejazdId;
    private String dodatkoweInformacje;
    
    private String krotko;
    
    private float koszty;

    public IncydentDto() {}

    public IncydentDto(Incydent incydent) {
    	this.krotko = incydent.getKrotko();
        this.incydentId = incydent.getIncydentId();
        this.typ = incydent.getTyp();
        this.date = incydent.getDate();
        this.autobusId = incydent.getAutobus() != null ? incydent.getAutobus().getAutbousId() : null;
        this.kierowcaId = incydent.getKierowca() != null ? incydent.getKierowca().getKierowcaId() : null;
        this.przejazdId = incydent.getPrzejazd() != null ? incydent.getPrzejazd().getPrzejazdId() : null;
        this.dodatkoweInformacje = incydent.getDodatkoweInformacje();
        this.koszty = incydent.getKoszty();
    }

    // Getters and Setters

    public String getKrotko() {
		return krotko;
	}

	public void setKrotko(String krotko) {
		this.krotko = krotko;
	}

	public Long getIncydentId() {
        return incydentId;
    }

    public void setIncydentId(Long incydentId) {
        this.incydentId = incydentId;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAutobusId() {
        return autobusId;
    }

    public void setAutobusId(Long autobusId) {
        this.autobusId = autobusId;
    }

    public Long getKierowcaId() {
        return kierowcaId;
    }

    public void setKierowcaId(Long kierowcaId) {
        this.kierowcaId = kierowcaId;
    }

    public Long getPrzejazdId() {
        return przejazdId;
    }

    public void setPrzejazdId(Long przejazdId) {
        this.przejazdId = przejazdId;
    }

    public String getDodatkoweInformacje() {
        return dodatkoweInformacje;
    }

    public void setDodatkoweInformacje(String dodatkoweInformacje) {
        this.dodatkoweInformacje = dodatkoweInformacje;
    }

    public float getKoszty() {
        return koszty;
    }

    public void setKoszty(float koszty) {
        this.koszty = koszty;
    }
}
