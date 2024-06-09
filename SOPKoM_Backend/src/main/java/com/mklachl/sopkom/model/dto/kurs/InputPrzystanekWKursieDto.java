package com.mklachl.sopkom.model.dto.kurs;

import com.mklachl.sopkom.model.dto.linia.PrzystanekDtoDlaLinia;
import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;

/**
 * Data Transfer Object (DTO) wejściowy dla encji KursPrzystanekWlini.
 */
public class InputPrzystanekWKursieDto extends AbstractPrzystanekWKursieDto {

    /**
     * DTO przystanku w linii.
     */
    private PrzystanekDtoDlaLinia przystanekWLini;

    /**
     * Konstruktor domyślny.
     */
    public InputPrzystanekWKursieDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji KursPrzystanekWlini.
     * @param kpw Obiekt encji KursPrzystanekWlini.
     */
    public InputPrzystanekWKursieDto(KursPrzystanekWlini kpw) {
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
