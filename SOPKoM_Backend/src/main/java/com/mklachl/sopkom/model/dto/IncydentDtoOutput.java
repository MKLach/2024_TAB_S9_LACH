package com.mklachl.sopkom.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.entity.Incydent;

public class IncydentDtoOutput extends IncydentDto {
    private KierowcaDto kierowca;
    private AutobusDto autobus;
    private KursDto kurs;
    
    @JsonFormat(pattern="EE MMM d y H:m:s ZZZ", locale = "en-US")
    private Date data;
    

 
    public IncydentDtoOutput(Incydent incydent) {
		super(incydent);
		
		if(incydent.getAutobus() != null) {
			this.autobus = new AutobusDto(incydent.getAutobus());
		}
		
		if(incydent.getKierowca() != null) {
			this.kierowca = new KierowcaDto(incydent.getKierowca());
		}
		
		if(incydent.getPrzejazd() != null) {
			
			this.kurs = new KursDto(incydent.getPrzejazd().getKurs());
			data = incydent.getPrzejazd().getData();
		}
		
   	}
    
    
    // Getters and setters for new fields
    public KierowcaDto getKierowca() {
        return kierowca;
    }

    public void setKierowca(KierowcaDto kierowca) {
        this.kierowca = kierowca;
    }

    public AutobusDto getAutobus() {
        return autobus;
    }

    public void setAutobus(AutobusDto autobus) {
        this.autobus = autobus;
    }

    public KursDto getKurs() {
        return kurs;
    }

    public void setKurs(KursDto kurs) {
        this.kurs = kurs;
    }
}