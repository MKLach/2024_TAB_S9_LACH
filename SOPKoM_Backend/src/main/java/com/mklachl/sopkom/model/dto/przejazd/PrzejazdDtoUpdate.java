package com.mklachl.sopkom.model.dto.przejazd;

import java.util.List;

public class PrzejazdDtoUpdate  {
  
	long przejazdId;
	double spalanie;
	double cenaZaLitr;
	double dlugoszTrasy;
	int liczbaBiletowUlgowych;
	int liczbaBiletowNormalnych;
	
	List<PKPWLDtoUpdate> przystanki;
    
    public PrzejazdDtoUpdate() {
    	
    }

	public double getSpalanie() {
		return spalanie;
	}

	public void setSpalanie(double spalanie) {
		this.spalanie = spalanie;
	}

	public double getCenaZaLitr() {
		return cenaZaLitr;
	}

	public void setCenaZaLitr(double cenaZaLitr) {
		this.cenaZaLitr = cenaZaLitr;
	}


	public double getDlugoszTrasy() {
		return dlugoszTrasy;
	}


	public void setDlugoszTrasy(double dlugoszTrasy) {
		this.dlugoszTrasy = dlugoszTrasy;
	}


	public int getLiczbaBiletowUlgowych() {
		return liczbaBiletowUlgowych;
	}


	public void setLiczbaBiletowUlgowych(int liczbaBiletowUlgowych) {
		this.liczbaBiletowUlgowych = liczbaBiletowUlgowych;
	}


	public int getLiczbaBiletowNormalnych() {
		return liczbaBiletowNormalnych;
	}


	public void setLiczbaBiletowNormalnych(int liczbaBiletowNormalnych) {
		this.liczbaBiletowNormalnych = liczbaBiletowNormalnych;
	}

	public List<PKPWLDtoUpdate> getPrzystanki() {
		return przystanki;
	}

	public void setPrzystanki(List<PKPWLDtoUpdate> przystanki) {
		this.przystanki = przystanki;
	}
	
	

	public long getPrzejazdId() {
		return przejazdId;
	}

	public void setPrzejazdId(long przejazdId) {
		this.przejazdId = przejazdId;
	}

	@Override
	public String toString() {
		return "PrzejazdDtoUpdate [przejazdId=" + przejazdId + ", spalanie=" + spalanie + ", cenaZaLitr=" + cenaZaLitr
				+ ", dlugoszTrasy=" + dlugoszTrasy + ", liczbaBiletowUlgowych=" + liczbaBiletowUlgowych
				+ ", liczbaBiletowNormalnych=" + liczbaBiletowNormalnych + ", przystanki=" + przystanki + "]";
	}
    
	
}
