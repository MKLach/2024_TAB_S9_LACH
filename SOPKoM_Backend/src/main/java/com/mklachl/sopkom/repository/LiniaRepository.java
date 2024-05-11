package com.mklachl.sopkom.repository;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.Linia;

import java.util.List;

public interface LiniaRepository extends CrudRepository<Linia, Long>{

    Linia findByNumer(String numer);
    List<Linia> findAll();

}
