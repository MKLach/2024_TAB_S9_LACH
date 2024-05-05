package com.mklachl.sopkom.model.dto;
import com.mklachl.sopkom.model.entity.Kierowca;
import com.mklachl.sopkom.model.entity.User;

import java.util.Date;

public class KierowcaDto {
    private Long kierowcaId;

    private Date prawoJazdyWazneDo;

    private String imie;

    private String nazwisko;

    private String pesel;

    private UserDto user;

    public KierowcaDto(){

    }

    public KierowcaDto(Kierowca kierowca){
        this.kierowcaId = kierowca.getKierowcaId();
        this.prawoJazdyWazneDo = kierowca.getPrawoJazdyWazneDo();
        this.imie = kierowca.getImie();
        this.nazwisko = kierowca.getNazwisko();
        this.pesel = kierowca.getPesel();
        
        if(kierowca.getUser() != null) {
        	this.user = new UserDto(kierowca.getUser());
        }
        
    }

    // Getters
    public Long getKierowcaId() {
        return kierowcaId;
    }

    public Date getPrawoJazdyWazneDo() {
        return prawoJazdyWazneDo;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public UserDto getUser() {
        return user;
    }

    // Setters
    public void setKierowcaId(Long kierowcaId) {
        this.kierowcaId = kierowcaId;
    }

    public void setPrawoJazdyWazneDo(Date prawoJazdyWazneDo) {
        this.prawoJazdyWazneDo = prawoJazdyWazneDo;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setUserDto(UserDto user) {
        this.user = user;
    }

	@Override
	public String toString() {
		return "KierowcaDto [kierowcaId=" + kierowcaId + ", prawoJazdyWazneDo=" + prawoJazdyWazneDo + ", imie=" + imie
				+ ", nazwisko=" + nazwisko + ", pesel=" + pesel + ", user=" + user + "]";
	}
    
    
}
