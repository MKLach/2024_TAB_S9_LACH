package com.mklachl.sopkom.model.dto.przejazd;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PrzejazdDtoInput {
    private Long kursId;
    private Long kierowcaId;
    private Long autobusId;
    
    
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date dataPrzejazdu;

    public PrzejazdDtoInput() {}

    // Getters and Setters
    public Long getKursId() {
        return kursId;
    }

    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    public Long getKierowcaId() {
        return kierowcaId;
    }

    public void setKierowcaId(Long kierowcaId) {
        this.kierowcaId = kierowcaId;
    }

    public Long getAutobusId() {
        return autobusId;
    }

    public void setAutobusId(Long autobusId) {
        this.autobusId = autobusId;
    }

    public Date getDataPrzejazdu() {
        return dataPrzejazdu;
    }

    public void setDataPrzejazdu(Date dataPrzejazdu) {
        this.dataPrzejazdu = dataPrzejazdu;
    }

	@Override
	public String toString() {
		return "PrzejazdDtoInput [kursId=" + kursId + ", kierowcaId=" + kierowcaId + ", autobusId=" + autobusId
				+ ", dataPrzejazdu=" + dataPrzejazdu + "]";
	}
    
    
}
