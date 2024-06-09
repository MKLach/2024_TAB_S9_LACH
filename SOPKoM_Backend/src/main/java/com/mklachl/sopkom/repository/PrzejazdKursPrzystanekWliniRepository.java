package com.mklachl.sopkom.repository;

import com.mklachl.sopkom.model.entity.KursPrzystanekWlini;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.model.entity.PrzejazdKursPrzystanekWlini;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Interfejs repository dla encji PrzejazdKursPrzystanekWlini
 */
public interface PrzejazdKursPrzystanekWliniRepository extends CrudRepository<PrzejazdKursPrzystanekWlini, Long> {

    /**
     * Znajduje wszystkie kursy przystanku w przejeździe dla danego przejazdu
     * @param przejazd przejazd
     * @return Lista kursów przystanku w przejeździe
     */
    List<PrzejazdKursPrzystanekWlini> findAllByPrzejazd(Przejazd przejazd);

    /**
     * Znajduje wszystkie kursy przystanku w przejeździe dla danego kursu przystanku w linii
     * @param kursPrzystanekWlini kurs przystanku w linii
     * @return Lista kursów przystanku w przejeździe
     */
    List<PrzejazdKursPrzystanekWlini> findAllByKursPrzystanekWlini(KursPrzystanekWlini kursPrzystanekWlini);

    /**
     * Znajduje wszystkie kursy przystanku w przejeździe po rzeczywistej godzinie
     * @param realnaGodzinna rzeczywista godzina
     * @return Lista kursów przystanku w przejeździe
     */
    List<PrzejazdKursPrzystanekWlini> findAllByRealnaGodzinna(Date realnaGodzinna);

    /**
     * Znajduje wszystkie kursy przystanku w przejeździe po rzeczywistej godzinie wcześniejszej niż podana
     * @param realnaGodzinna rzeczywista godzina
     * @return Lista kursów przystanku w przejeździe
     */
    List<PrzejazdKursPrzystanekWlini> findAllByRealnaGodzinnaBefore(Date realnaGodzinna);

    /**
     * Znajduje wszystkie kursy przystanku w przejeździe po rzeczywistej godzinie późniejszej niż podana
     * @param realnaGodzinna rzeczywista godzina
     * @return Lista kursów przystanku w przejeździe
     */
    List<PrzejazdKursPrzystanekWlini> findAllByRealnaGodzinnaAfter(Date realnaGodzinna);

    /**
     * Znajduje wszystkie kursy przystanku w przejeździe
     * @return Lista kursów przystanku w przejeździe
     */
    List<PrzejazdKursPrzystanekWlini> findAll();
}
