package com.mklachl.sopkom.model.dto.przejazd;

import java.util.Date;

public class PrzejazdDtoInput {
    private Long kursId;
    private Long kierowcaId;
    private Long autobusId;
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
}
