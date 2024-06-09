package com.mklachl.sopkom.model.dto.rozklad;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mklachl.sopkom.model.dto.PrzystanekDto;
import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.entity.Przystanek;

/**
 * Data Transfer Object (DTO) dla rozkładu jazdy.
 */
public class RozkladDto {

    /**
     * Klasa wewnętrzna reprezentująca godzinę odjazdu i kierunek kursu.
     */
    public static class $ implements Comparable<$> {
        /**
         * Czas odjazdu.
         */
        public Time time;

        /**
         * Flaga wskazująca, czy kurs jest w odwrotnym kierunku.
         */
        public boolean odw;

        /**
         * Konstruktor przyjmujący czas odjazdu i flagę odwrotności.
         * @param time Czas odjazdu.
         * @param odw Flaga odwrotności.
         */
        public $(Time time, boolean odw) {
            this.time = time;
            this.odw = odw;
        }

        @Override
        public int hashCode() {
            return Objects.hash(odw, time);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            $ other = ($) obj;
            return odw == other.odw && Objects.equals(time, other.time);
        }

        @Override
        public int compareTo($ o) {
            return this.time.compareTo(o.time);
        }
    }

    /**
     * Obiekt DTO dla przystanku.
     */
    private PrzystanekDto normalny;

    /**
     * Data rozkładu w formacie "dd.MM.yyyy".
     */
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Europe/Warsaw")
    private Date date;

    /**
     * Mapa rozkładu, gdzie kluczem jest numer linii, a wartością jest zbiór godzin odjazdów.
     */
    private HashMap<String, TreeSet<$>> rozklad = new HashMap<>();

    /**
     * Konstruktor przyjmujący obiekt Przystanek, KursDto oraz datę.
     * @param p Obiekt przystanku.
     * @param kursDto Obiekt DTO kursu.
     * @param date Data rozkładu.
     */
    public RozkladDto(Przystanek p, KursDto kursDto, Date date) {
        this.normalny = new PrzystanekDto(p);
        this.date = date;
    }

    /**
     * Konstruktor domyślny.
     */
    public RozkladDto() {
    }

    // Getters and Setters

    /**
     * Zwraca mapę rozkładu.
     * @return Mapa rozkładu.
     */
    public HashMap<String, TreeSet<$>> getRozklad() {
        return rozklad;
    }

    /**
     * Ustawia mapę rozkładu.
     * @param rozklad Mapa rozkładu.
     */
    public void setRozklad(HashMap<String, TreeSet<$>> rozklad) {
        this.rozklad = rozklad;
    }

    /**
     * Dodaje kurs do rozkładu.
     * @param linia Obiekt DTO linii.
     * @param godzinna Czas odjazdu.
     * @param odwrotna Flaga odwrotności.
     * @return Obiekt RozkladDto.
     */
    public RozkladDto addKurs(LiniaDto linia, Time godzinna, boolean odwrotna) {
        rozklad.computeIfAbsent(linia.getNumer(), k -> new TreeSet<>()).add(new $(godzinna, odwrotna));
        return this;
    }

    /**
     * Zwraca obiekt DTO przystanku.
     * @return Obiekt DTO przystanku.
     */
    public PrzystanekDto getNormalny() {
        return normalny;
    }

    /**
     * Ustawia obiekt DTO przystanku.
     * @param normalny Obiekt DTO przystanku.
     */
    public void setNormalny(PrzystanekDto normalny) {
        this.normalny = normalny;
    }

    /**
     * Zwraca datę rozkładu.
     * @return Data rozkładu.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Ustawia datę rozkładu.
     * @param date Data rozkładu.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
