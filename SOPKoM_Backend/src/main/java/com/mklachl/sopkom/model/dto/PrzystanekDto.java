package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Przystanek;

public class PrzystanekDto {
    private Long przystanekId;

    private Long przystanekOdwrotnyId;
    private String przystanekOdwrotnyNazwa;

    private String nazwa;

    private String kodPocztowy;

    private String miasto;

    private String ulica;

    private String dlugoscGeograficzna;

    private String szerokoscGeograficzna;

    public PrzystanekDto(){

    }

    public PrzystanekDto(Przystanek przystanek){
        this.przystanekId = przystanek.getPrzystanekId();
        
        if(przystanek.getPrzystanekOdwrotny() != null) {
        	this.przystanekOdwrotnyId = przystanek.getPrzystanekOdwrotny().getPrzystanekId();
            this.przystanekOdwrotnyNazwa = przystanek.getPrzystanekOdwrotny().getNazwa();
        }
        
        this.nazwa = przystanek.getNazwa();
        this.kodPocztowy = przystanek.getKodPocztowy();
        this.miasto = przystanek.getMiasto();
        this.ulica = przystanek.getUlica();
        this.dlugoscGeograficzna = przystanek.getDlugoscGeograficzna();
        this.szerokoscGeograficzna = przystanek.getSzerokoscGeograficzna();
    }

    // Getters
    public Long getPrzystanekId() {
        return przystanekId;
    }

    public Long getPrzystanekOdwrotny() {
        return przystanekOdwrotnyId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public String getDlugoscGeograficzna() {
        return dlugoscGeograficzna;
    }

    public String getSzerokoscGeograficzna() {
        return szerokoscGeograficzna;
    }

    /*public List<PrzystanekWlini> getPrzystankiWlini() {
        return przystankiWlini;
    }*/

    // Setters
    public void setPrzystanekId(Long przystanekId) {
        this.przystanekId = przystanekId;
    }

    public void setPrzystanekOdwrotny(Long przystanekOdwrotny) {
        this.przystanekOdwrotnyId = przystanekOdwrotny;
    }
    
    public String getPrzystanekOdwrotnyNazwa() {
		return przystanekOdwrotnyNazwa;
	}

	public void setPrzystanekOdwrotnyNazwa(String przystanekOdwrotnyNazwa) {
		this.przystanekOdwrotnyNazwa = przystanekOdwrotnyNazwa;
	}

	public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public void setDlugoscGeograficzna(String dlugoscGeograficzna) {
        this.dlugoscGeograficzna = dlugoscGeograficzna;
    }

    public void setSzerokoscGeograficzna(String szerokoscGeograficzna) {
        this.szerokoscGeograficzna = szerokoscGeograficzna;
    }
}
