package com.mklachl.sopkom.model.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.entity.Incydent;

/**
 * Data Transfer Object (DTO) rozszerzający IncydentDto o dodatkowe informacje.
 */
public class IncydentDtoOutput extends IncydentDto {

    /**
     * DTO kierowcy związanego z incydentem.
     */
    private KierowcaDto kierowca;

    /**
     * DTO autobusu związanego z incydentem.
     */
    private AutobusDto autobus;

    /**
     * DTO kursu związanego z incydentem.
     */
    private KursDto kurs;

    /**
     * Data incydentu w rozszerzonym formacie.
     */
    @JsonFormat(pattern = "EE MMM d y H:m:s ZZZ", locale = "en-US")
    private Date data;

    /**
     * Konstruktor przyjmujący obiekt encji Incydent.
     * @param incydent Obiekt encji Incydent.
     */
    public IncydentDtoOutput(Incydent incydent) {
        super(incydent);

        if (incydent.getAutobus() != null) {
            this.autobus = new AutobusDto(incydent.getAutobus());
        }

        if (incydent.getKierowca() != null) {
            this.kierowca = new KierowcaDto(incydent.getKierowca());
        }

        if (incydent.getPrzejazd() != null) {
            this.kurs = new KursDto(incydent.getPrzejazd().getKurs());
            data = incydent.getPrzejazd().getData();
        }
    }

    // Getters and setters for new fields

    /**
     * Zwraca DTO kierowcy związanego z incydentem.
     * @return kierowca DTO kierowcy.
     */
    public KierowcaDto getKierowca() {
        return kierowca;
    }

    /**
     * Ustawia DTO kierowcy związanego z incydentem.
     * @param kierowca DTO kierowcy.
     */
    public void setKierowca(KierowcaDto kierowca) {
        this.kierowca = kierowca;
    }

    /**
     * Zwraca DTO autobusu związanego z incydentem.
     * @return autobus DTO autobusu.
     */
    public AutobusDto getAutobus() {
        return autobus;
    }

    /**
     * Ustawia DTO autobusu związanego z incydentem.
     * @param autobus DTO autobusu.
     */
    public void setAutobus(AutobusDto autobus) {
        this.autobus = autobus;
    }

    /**
     * Zwraca DTO kursu związanego z incydentem.
     * @return kurs DTO kursu.
     */
    public KursDto getKurs() {
        return kurs;
    }

    /**
     * Ustawia DTO kursu związanego z incydentem.
     * @param kurs DTO kursu.
     */
    public void setKurs(KursDto kurs) {
        this.kurs = kurs;
    }
}
