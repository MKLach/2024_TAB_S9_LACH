package com.mklachl.sopkom.model.dto.kurs;

import com.mklachl.sopkom.model.dto.linia.PrzystanekDtoDlaLinia;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;

/**
 * Data Transfer Object (DTO) dla encji KursPrzystanekWlini.
 */
public class PrzystanekWKursieDto extends AbstractPrzystanekWKursieDto {

    /**
     * DTO przystanku w linii.
     */
    private PrzystanekDtoDlaLinia przystanekWLini;

    /**
     * Konstruktor domyślny.
     */
    public PrzystanekWKursieDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji KursPrzystanekWlini.
     * @param kpw Obiekt encji KursPrzystanekWlini.
     */
    public PrzystanekWKursieDto(KursPrzystanekWlini kpw) {
        super(kpw);
        this.przystanekWLini = new PrzystanekDtoDlaLinia(kpw.getPrzystanekWlini());
    }

    // Getters and Setters

    /**
     * Zwraca DTO przystanku w linii.
     * @return przystanekWLini DTO przystanku w linii.
     */
    public PrzystanekDtoDlaLinia getPrzystanekWLini() {
        return przystanekWLini;
    }

    /**
     * Ustawia DTO przystanku w linii.
     * @param przystanekWLini DTO przystanku w linii.
     */
    public void setPrzystanekWLini(PrzystanekDtoDlaLinia przystanekWLini) {
        this.przystanekWLini = przystanekWLini;
    }
}
