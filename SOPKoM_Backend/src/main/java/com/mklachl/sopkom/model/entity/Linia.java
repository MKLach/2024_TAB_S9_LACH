package com.mklachl.sopkom.model.entity;

import jakarta.persistence.*;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "linie")
public class Linia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linia_id")
    private Long liniaId;

    @Column(name = "numer", nullable = false, length = 50)
    private String numer;

    @OneToMany(mappedBy = "linia")
    private List<PrzystanekWlini> przystankiWlini;

    @Transient
    public boolean odwracalna() {
    	
    	for(int i = 0; i < this.przystankiWlini.size(); i++) {
    		
    		Przystanek p = this.przystankiWlini.get(i).getPrzystanek();
    		
    		if(p.getPrzystanekOdwrotny() == null) {
    			return false;
    		}
    		
    		
    		
    	}
    	
    	return true;
    }
    
    
    
    
    // Getters
    public Long getLiniaId() {
        return liniaId;
    }

    public String getNumer() {
        return numer;
    }

    public List<PrzystanekWlini> getPrzystankiWlini() {
        return przystankiWlini;
    }

    // Setters
    public void setLiniaId(Long liniaId) {
        this.liniaId = liniaId;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public void setPrzystankiWlini(List<PrzystanekWlini> przystankiWlini) { this.przystankiWlini = przystankiWlini; }

}
