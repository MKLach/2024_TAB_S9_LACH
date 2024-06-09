package com.mklachl.sopkom.model.dto.kurs;

import java.util.ArrayList;
import java.util.List;
import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.entity.Kurs;

/**
 * Data Transfer Object (DTO) dla encji Kurs.
 */
public class KursDto extends AbstractKursDto {

    /**
     * Lista przystanków w kursie.
     */
    private List<InputPrzystanekWKursieDto> przystanki;

    /**
     * DTO harmonogramu związanego z kursem.
     */
    private HarmonogramDto harmonogram;

    /**
     * DTO linii związanej z kursem.
     */
    private LiniaDto linia;

    /**
     * Flaga określająca, czy linia jest odwracalna.
     */
    private boolean odwracalny;

    /**
     * Konstruktor domyślny.
     */
    public KursDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji Kurs.
     * @param kurs Obiekt encji Kurs.
     */
    public KursDto(Kurs kurs) {
        super(kurs);
        this.setLinia(new LiniaDto(kurs.getLinia()));
        this.setHarmonogram(new HarmonogramDto(kurs.getHarmonogram()));
        this.setOdwracalny(kurs.getLinia().odwracalna());
        this.przystanki = new ArrayList<>();
        
        for (var k : kurs.getKursPrzystanekWlinii()) {
            przystanki.add(new InputPrzystanekWKursieDto(k));
        }
    }

    // Getters and Setters

    /**
     * Zwraca listę przystanków w kursie.
     * @return przystanki Lista przystanków w kursie.
     */
    public List<InputPrzystanekWKursieDto> getPrzystanki() {
        return przystanki;
    }

    /**
     * Ustawia listę przystanków w kursie.
     * @param przystanki Lista przystanków w kursie.
     */
    public void setPrzystanki(List<InputPrzystanekWKursieDto> przystanki) {
        this.przystanki = przystanki;
    }

    /**
     * Zwraca DTO harmonogramu związanego z kursem.
     * @return harmonogram DTO harmonogramu.
     */
    public HarmonogramDto getHarmonogram() {
        return harmonogram;
    }

    /**
     * Ustawia DTO harmonogramu związanego z kursem.
     * @param harmonogram DTO harmonogramu.
     */
    public void setHarmonogram(HarmonogramDto harmonogram) {
        this.setHarmonogramId(harmonogram.getHarmonogramId());
        this.harmonogram = harmonogram;
    }

    /**
     * Zwraca DTO linii związanej z kursem.
     * @return linia DTO linii.
     */
    public LiniaDto getLinia() {
        return linia;
    }

    /**
     * Ustawia DTO linii związanej z kursem.
     * @param linia DTO linii.
     */
    public void setLinia(LiniaDto linia) {
        this.linia = linia;
        this.setLiniaId(linia.getId());
    }

    /**
     * Zwraca wartość flagi określającej, czy linia jest odwracalna.
     * @return odwracalny Wartość flagi odwracalności linii.
     */
    public boolean getOdwracalny() {
        return odwracalny;
    }

    /**
     * Ustawia wartość flagi określającej, czy linia jest odwracalna.
     * @param odwracalny Wartość flagi odwracalności linii.
     */
    public void setOdwracalny(boolean odwracalny) {
        this.odwracalny = odwracalny;
    }
}
