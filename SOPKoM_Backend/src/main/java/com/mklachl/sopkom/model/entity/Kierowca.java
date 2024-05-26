package com.mklachl.sopkom.model.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "kierowcy")
public class Kierowca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kierowca_id")
    private Long kierowcaId;

    @Column(name = "prawojazdywaznedo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date prawoJazdyWazneDo;

    @Column(name = "imie", nullable = false, length = 30)
    private String imie;

    @Column(name = "nazwisko", nullable = false, length = 30)
    private String nazwisko;

    @Column(name = "pesel", nullable = false, length = 30)
    private String pesel;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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

    public User getUser() {
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

    public void setUser(User user) {
        this.user = user;
    }

	@Override
	public int hashCode() {
		return Objects.hash(imie, kierowcaId, nazwisko, pesel, prawoJazdyWazneDo, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kierowca other = (Kierowca) obj;
		return Objects.equals(imie, other. imie) && Objects.equals(kierowcaId, other.kierowcaId)
				&& Objects.equals(nazwisko, other.nazwisko) && Objects.equals(pesel, other.pesel)
				&& Objects.equals(prawoJazdyWazneDo, other.prawoJazdyWazneDo);
	}
    
    
    
}