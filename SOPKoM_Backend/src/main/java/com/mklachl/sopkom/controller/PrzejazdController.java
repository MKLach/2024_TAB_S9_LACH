package com.mklachl.sopkom.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mklachl.sopkom.model.dto.AutobusDto;
import com.mklachl.sopkom.model.dto.KierowcaDto;
import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoInput;
import com.mklachl.sopkom.model.dto.przejazd.PrzejazdDtoOutput;
import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.model.entity.Przejazd;
import com.mklachl.sopkom.raporty.rozkład.DateHelper;
import com.mklachl.sopkom.repository.AutobusRepository;
import com.mklachl.sopkom.repository.HarmonogramRepository;
import com.mklachl.sopkom.repository.KierowcaRepository;
import com.mklachl.sopkom.repository.KursRepository;
import com.mklachl.sopkom.repository.PrzejazdRepository;
import com.mklachl.sopkom.services.KursService;
import com.mklachl.sopkom.services.PrzejazdService;

@RestController
@RequestMapping("/api/przejazd")
public class PrzejazdController {

    @Autowired
    public HarmonogramRepository harmonogramRepository;
    
    @Autowired
    public KursRepository kursRepository;
    
    @Autowired
    public KursService kursService;
    
    @Autowired
    public PrzejazdService przejazdService;
    
    @Autowired
    public KierowcaRepository kierowcaRepository;
    
    @Autowired
    public AutobusRepository autobusRepository;
    
    @Autowired
    public PrzejazdRepository przejazdRepository;
    
    /**
     * Endpoint do zapisywania nowego przejazdu.
     * @param input DTO przejazdu do zapisania
     * @return Odpowiedź HTTP
     */
    @PostMapping({"/save", "/save/"})
    public ResponseEntity<?> savePrzejazd(@RequestBody PrzejazdDtoInput input){
        
        System.out.println(input);
        
        przejazdService.savePrzejazd(input);
        
        return ResponseEntity.ok().build();
    }
    
    /**
     * Endpoint do pobierania przejazdu według ID.
     * @param przejazdid ID przejazdu
     * @return Obiekt PrzejazdDtoOutput
     */
    @GetMapping({"/{przejazd_id}", "/{przejazd_id}/"})
    public ResponseEntity<?> getPrzejazd(@PathVariable(name="przejazd_id") Long przejazdid){
        
        try {
            var p = przejazdService.findPrzejazdById(przejazdid).orElseThrow();
            
            PrzejazdDtoOutput output = new PrzejazdDtoOutput(p);
            
            return ResponseEntity.ok(output);
            
        } catch (Exception e) {
            
            return ResponseEntity.notFound().build();
        }
    
    }
    
    /**
     * Endpoint do usuwania przejazdu według ID.
     * @param przejazdid ID przejazdu
     * @return Odpowiedź HTTP
     */
    @DeleteMapping({"/{przejazd_id}", "/{przejazd_id}/"})
    public ResponseEntity<?> deletePrzejazd(@PathVariable(name="przejazd_id") Long przejazdid){
        
        try {
            przejazdRepository.deleteById(przejazdid);
            
            return ResponseEntity.ok("deleted");
            
        } catch (Exception e) {
            
            return ResponseEntity.notFound().build();
        }
    
    }
    
    /**
     * Endpoint do pobierania przejazdów z opcjonalnym filtrowaniem według kursu.
     * @param kursId ID kursu
     * @return Lista przejazdów
     */
    @GetMapping()
    public ResponseEntity<?> getPrzejazdy(@RequestParam(name="kurs_id", required = false) Long kursId){
        try {
            List<Przejazd> przejazdy = null;
            
            if(kursId == null) {
                przejazdy = przejazdRepository.findAll();
            } else {
                
                przejazdy = przejazdRepository.findAllByKurs(kursRepository.findById(kursId).orElseThrow());
            }
            
            return ResponseEntity.ok(przejazdy.stream().map(PrzejazdDtoOutput::new).collect(Collectors.toList()));
            
        } catch (Exception e) {
            
            return ResponseEntity.notFound().build();
        }
    
    }
    
    /**
     * Endpoint do pobierania wszystkich dostępnych kierowców dla kursu w danym dniu.
     * @param kursId ID kursu
     * @param date Data przejazdu
     * @return Lista dostępnych kierowców
     */
    @GetMapping("/allDrivers")
    public ResponseEntity<?> gatAllAvaliableDriversForKurs(@RequestParam(name="kurs_id") Long kursId , @RequestParam(name="data") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date){
        
        System.out.println(date);
        
        Kurs kurs;
        try {
            kurs = kursRepository.findById(kursId).orElseThrow();
            
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
        Date startRaw;
        Date endRaw;
        
        if(kurs.getKierunek().shortValue() == 0) {
            
            startRaw = DateHelper.copyTimeComponents(
                    kurs.getKursPrzystanekWlinii().get(0).getGodzinna(), 
                    date);
            
            endRaw = DateHelper.copyTimeComponents(
                    kurs.getKursPrzystanekWlinii().get(kurs.getKursPrzystanekWlinii().size()-1).getGodzinna(),
                    date);
            
            
        } else {
            startRaw = DateHelper.copyTimeComponents(
                    kurs.getKursPrzystanekWlinii().get(kurs.getKursPrzystanekWlinii().size()-1).getGodzinna(), 
                    date);
            
            endRaw = DateHelper.copyTimeComponents(
                    kurs.getKursPrzystanekWlinii().get(0).getGodzinna(),
                    date);
            
        }
        
        var temp = kierowcaRepository.findAllByAvailability(startRaw, endRaw).stream().map(KierowcaDto::new).collect(Collectors.toList());
        
        return ResponseEntity.ok(temp);
    }
    
    /**
     * Endpoint do pobierania wszystkich dostępnych autobusów dla kursu w danym dniu.
     * @param kursId ID kursu
     * @param date Data przejazdu
     * @return Lista dostępnych autobusów
     */
    @GetMapping("/allBuses")
    public ResponseEntity<?> gatAllAvaliableBusesForKurs(@RequestParam(name="kurs_id") Long kursId , @RequestParam(name="data") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date){
        
        System.out.println(date);
        
        Kurs kurs;
        try {
            kurs = kursRepository.findById(kursId).orElseThrow();
            
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
        Date startRaw;
        Date endRaw;
        
        if(kurs.getKierunek().shortValue() == 0) {
            
            startRaw = DateHelper.copyTimeComponents(
                    kurs.getKursPrzystanekWlinii().get(0).getGodzinna(), 
                    date);
            
            endRaw = DateHelper.copyTimeComponents(
                    kurs.getKursPrzystanekWlinii().get(kurs.getKursPrzystanekWlinii().size()-1).getGodzinna(),
                    date);
            
            
        } else {
            startRaw = DateHelper.copyTimeComponents(
                    kurs.getKursPrzystanekWlinii().get(kurs.getKursPrzystanekWlinii().size()-1).getGodzinna(), 
                    date);
            
            endRaw = DateHelper.copyTimeComponents(
                    kurs.getKursPrzystanekWlinii().get(0).getGodzinna(),
                    date);
            
        }
        
        var temp = autobusRepository.findAllByAvailable(startRaw, endRaw).stream().filter((item ) -> { return item.getStatus().equals("OK");}) .map(AutobusDto::new).collect(Collectors.toList());
        
        return ResponseEntity.ok(temp);
    }
    
    /**
     * Endpoint do pobierania przyszłych przejazdów.
     * @return Lista przyszłych przejazdów
     */
    @GetMapping({"/przyszle", "/przyszle/"})
    public ResponseEntity<?> getPrzyszłePrzejazdy(){
        
        List<PrzejazdDtoOutput> przyszlePrzejazdy = new ArrayList<PrzejazdDtoOutput>();
        
        for(Kurs kurs : kursRepository.findAll()) {
            
            List<Date> datesForKurs = przejazdService.findAllDatesForKurs(kurs, 30);
            
            Date godzinna_startu = kurs.getKierunek() == 0 ? 
                    kurs.getKursPrzystanekWlinii().get(0).getGodzinna() : 
                    kurs.getKursPrzystanekWlinii().get(0).getGodzinna();
            
            List<PrzejazdDtoOutput> a = datesForKurs.stream()
                    .filter((item) -> {
                        Calendar c = Calendar.getInstance();
                        c.setTime(godzinna_startu);
                        
                        Date startWithFulLDate = DateHelper.copyTimeComponents(godzinna_startu, item);
                        System.out.println(new Date());
                        System.out.println(startWithFulLDate);
                        
                        return startWithFulLDate.after(new Date());
                        
                    } )
                    .map((date) -> new PrzejazdDtoOutput(przejazdService.createDetachedPrzejazd(kurs, date), true)).collect(Collectors.toList());
            
            przyszlePrzejazdy.addAll(a);
            
        }
        
        przyszlePrzejazdy.sort(new Comparator<PrzejazdDtoOutput>() {

            @Override
            public int compare(PrzejazdDtoOutput o1, PrzejazdDtoOutput o2) {
                
                return o1.getData().compareTo(o2.getData());
            }
        });
        
        return ResponseEntity.ok(przyszlePrzejazdy);
        
    }
    
    /**
     * Endpoint do pobierania wszystkich dostępnych dat dla kursu.
     * @param kursId ID kursu
     * @return Lista dostępnych dat
     */
    @GetMapping("/allDates")
    public ResponseEntity<?> gatAllAvaliableDatesForKurs(@RequestParam(name="kurs_id") Long kursId){
        Kurs kurs;
        try {
            kurs = kursRepository.findById(kursId).orElseThrow();
            
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
        Harmonogram harmongram = kurs.getHarmonogram();
        
        var result = DateHelper.getAllDatesForHarmonogram(harmongram);
        var copy = new LinkedList<Date>(result);
    
        var data = przejazdService.findPrzejazdByKurs(kurs);
        
        System.out.println(data.size());
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        
        List<String> dates = data.stream()
                 .map(item -> format.format(item.getDataStartu()))
                 .collect(Collectors.toList());
        
        List<String> list = copy.stream().map(item -> format.format(item)).collect(Collectors.toList());
        
        list.removeAll(dates);
        
        return ResponseEntity.ok(list);
    }
    
}
