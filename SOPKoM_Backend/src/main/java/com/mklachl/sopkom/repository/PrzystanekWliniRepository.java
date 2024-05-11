package com.mklachl.sopkom.repository;
import com.mklachl.sopkom.model.entity.Linia;
import com.mklachl.sopkom.model.entity.Przystanek;
import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.PrzystanekWlini;

import java.util.List;

public interface PrzystanekWliniRepository extends CrudRepository<PrzystanekWlini, Long>{

    List<PrzystanekWlini> findAllByPrzystanek(Przystanek przystanek);
    List<PrzystanekWlini> findAllByLinia(Linia linia);
    List<PrzystanekWlini> findAll();

}
