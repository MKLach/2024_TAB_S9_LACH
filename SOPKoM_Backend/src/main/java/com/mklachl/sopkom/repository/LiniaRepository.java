package com.mklachl.sopkom.repository;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.Linia;

import java.util.List;
import java.util.Optional;

public interface LiniaRepository extends CrudRepository<Linia, Long>{

    Optional<Linia> findById(Long id);

    Linia findByNumer(String numer);

    List<Linia> findAll();

}
