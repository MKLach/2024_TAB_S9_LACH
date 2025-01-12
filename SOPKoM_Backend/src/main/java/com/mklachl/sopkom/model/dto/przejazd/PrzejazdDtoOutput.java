package com.mklachl.sopkom.model.dto.przejazd;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.dto.PrzejazdKursPrzystanekWliniDto;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.model.entity.PrzejazdKursPrzystanekWlini;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PrzejazdDtoOutput {
    private Long przejazdId;
    private Long kursId;
    private String liniaNumer;
    @JsonFormat(pattern="EE MMM d y H:m:s ZZZ", locale = "en-US")
    private Date dataStartu;
    @JsonFormat(pattern="EE MMM d y H:m:s ZZZ", locale = "en-US")
    private Date dataKonca;
    @JsonFormat(pattern="EE MMM d y H:m:s ZZZ", locale = "en-US")
    private Date data;
    
    
    private String kierowcaImieNazwisko;
    private String autobusNazwa;
    private String autobusNumerRejestracyjny;
    private float spalanie;
    private float cenaZaLitr;
    private float dlugoscTrasy;
    private int liczbaBiletow;
    private String status;
    private short kierunek;
    
    private List<PrzejazdKursPrzystanekWliniDto> przystanki;

    public PrzejazdDtoOutput() {}

    public PrzejazdDtoOutput(Przejazd przejazd) {
        this.przejazdId = przejazd.getPrzejazdId();
        this.kursId = przejazd.getKurs().getKursId();
        this.liniaNumer = przejazd.getKurs().getLinia().getNumer();
        this.dataStartu = przejazd.getDataStartu();
        this.dataKonca = przejazd.getDataKonca();
        this.kierowcaImieNazwisko = przejazd.getKierowca().getImie() + " " + przejazd.getKierowca().getNazwisko();
        this.autobusNazwa = przejazd.getAutobus().getNumerRejestracyjny(); //do sprawdzenia
        this.autobusNumerRejestracyjny = przejazd.getAutobus().getNumerRejestracyjny();
        this.spalanie = przejazd.getSpalanie();
        this.cenaZaLitr = przejazd.getCenaZaLitr();
        this.dlugoscTrasy = przejazd.getDlugoscTrasy();
        this.liczbaBiletow = przejazd.getPrzejazdBilet().size();
        this.kierunek = przejazd.getKurs().getKierunek().shortValue();
        Date now = new Date();
        
        this.data = przejazd.getData();
       
        this.przystanki = przejazd.getPrzejazdKursPrzystanekWlini().stream()
                .map(PrzejazdKursPrzystanekWliniDto::new).collect(Collectors.toList());
        
        this.status = "Zaplanowany";
        
        int count = 0;
        
        for (Iterator<PrzejazdKursPrzystanekWliniDto> iterator = przystanki.iterator(); iterator.hasNext();) {
			PrzejazdKursPrzystanekWliniDto przejazdKursPrzystanekWliniDto = (PrzejazdKursPrzystanekWliniDto) iterator
					.next();
			if(przejazdKursPrzystanekWliniDto.getRealnaGodzinna() != null) {
				count+=1;
				 this.status = "W trakcie";
			}
			
			
		}
        if(count == przystanki.size()) {
        	 this.status = "Zakończony";
        }
        
        
        if (now.after(przejazd.getDataKonca())){
    		  
			if( status.equals("Zaplanowany")) {
				this.status = "Nie odbył się";
			} else if (status.equals("W trakcie")) {
				this.status = "Spóźniony";
			}
	       
        } 
        
        
        
        System.out.println(przystanki.size());

    }
    
    public PrzejazdDtoOutput(Przejazd przejazd, boolean a) {
        this.przejazdId = przejazd.getPrzejazdId();
        this.kursId = przejazd.getKurs().getKursId();
        this.liniaNumer = przejazd.getKurs().getLinia().getNumer();
        this.dataStartu = przejazd.getDataStartu();
        this.dataKonca = przejazd.getDataKonca();
        this.kierowcaImieNazwisko = null;
        this.autobusNazwa = null;
        this.autobusNumerRejestracyjny = null;
        this.spalanie = 0;
        this.cenaZaLitr = 0;
        this.dlugoscTrasy = 0;
        this.liczbaBiletow = 0;
        this.kierunek = przejazd.getKurs().getKierunek().shortValue();
       
        
        this.data = przejazd.getData();
       
        this.przystanki = przejazd.getPrzejazdKursPrzystanekWlini().stream()
                .map(PrzejazdKursPrzystanekWliniDto::new).collect(Collectors.toList());
        
        this.status = "Do zaplanowania";
        
      
        
    }
    

    // Getters and Setters
    public Long getPrzejazdId() {
        return przejazdId;
    }

    public void setPrzejazdId(Long przejazdId) {
        this.przejazdId = przejazdId;
    }

    public Long getKursId() {
        return kursId;
    }

    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    public String getLiniaNumer() {
        return liniaNumer;
    }

    public void setLiniaNumer(String liniaNumer) {
        this.liniaNumer = liniaNumer;
    }

    public Date getDataStartu() {
        return dataStartu;
    }

    public void setDataStartu(Date dataStartu) {
        this.dataStartu = dataStartu;
    }

    public Date getDataKonca() {
        return dataKonca;
    }

    public void setDataKonca(Date dataKonca) {
        this.dataKonca = dataKonca;
    }

    public String getKierowcaImieNazwisko() {
        return kierowcaImieNazwisko;
    }

    public void setKierowcaImieNazwisko(String kierowcaImieNazwisko) {
        this.kierowcaImieNazwisko = kierowcaImieNazwisko;
    }

    public String getAutobusNazwa() {
        return autobusNazwa;
    }

    public void setAutobusNazwa(String autobusNazwa) {
        this.autobusNazwa = autobusNazwa;
    }

    public String getAutobusNumerRejestracyjny() {
        return autobusNumerRejestracyjny;
    }

    public void setAutobusNumerRejestracyjny(String autobusNumerRejestracyjny) {
        this.autobusNumerRejestracyjny = autobusNumerRejestracyjny;
    }

    public float getSpalanie() {
        return spalanie;
    }

    public void setSpalanie(float spalanie) {
        this.spalanie = spalanie;
    }

    public float getCenaZaLitr() {
        return cenaZaLitr;
    }

    public void setCenaZaLitr(float cenaZaLitr) {
        this.cenaZaLitr = cenaZaLitr;
    }

    public float getDlugoscTrasy() {
        return dlugoscTrasy;
    }

    public void setDlugoscTrasy(float dlugoscTrasy) {
        this.dlugoscTrasy = dlugoscTrasy;
    }

    public int getLiczbaBiletow() {
        return liczbaBiletow;
    }

    public void setLiczbaBiletow(int liczbaBiletow) {
        this.liczbaBiletow = liczbaBiletow;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PrzejazdKursPrzystanekWliniDto> getPrzystanki() {
		return przystanki;
	}

	public void setPrzystanki(List<PrzejazdKursPrzystanekWliniDto> przystanki) {
		this.przystanki = przystanki;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public short getKierunek() {
		return kierunek;
	}

	public void setKierunek(short kierunek) {
		this.kierunek = kierunek;
	}

	
    
    /*public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/
}
