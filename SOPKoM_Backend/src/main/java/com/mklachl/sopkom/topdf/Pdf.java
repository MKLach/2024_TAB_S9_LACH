package com.mklachl.sopkom.topdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.entity.*;
import com.mklachl.sopkom.repository.KursRepository;
import com.mklachl.sopkom.repository.LiniaRepository;
import com.mklachl.sopkom.repository.PrzystanekRepository;
import com.mklachl.sopkom.repository.PrzystanekWliniRepository;
import com.mklachl.sopkom.services.PrzystanekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Time;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

@Service
//public class Pdf implements CommandLineRunner {
public class Pdf{
	
	static BaseFont BASE_FONT_PL;
	static {
		
		try {
			BASE_FONT_PL = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
    private void addTableHeader(PdfPTable table) {
        Stream.of("Linia", "Godzina")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private void addLinieHeader(PdfPTable table, Long id) {
        TreeSet<$> linieInfo = getAllByPrzystanekId(id);
        Set<String> uniqueLines = new HashSet<>();

        for ($ liniaInfo : linieInfo) {
            String liniaNumer = liniaInfo.getLinia().getNumer();
            uniqueLines.add(liniaNumer);
        }

        for (String liniaNumer : uniqueLines) {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(liniaNumer));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);
        }

    }

    private void addLinieRows(PdfPTable table, Long id) throws URISyntaxException, IOException {


        TreeSet<$> linieInfo = getAllByPrzystanekId(id);
        Set<String> uniqueLines = new HashSet<>();

        for ($ liniaInfo : linieInfo) {
            String liniaNumer = liniaInfo.getLinia().getNumer();
            uniqueLines.add(liniaNumer);
        }

        for (String liniaNumer : uniqueLines) {
            Linia l = liniaRepos.findByNumer(liniaNumer);

            List<PrzystanekWlini> p = przystanekWliniRepos.findAllByLinia(l);
            Phrase przystankiPhrase = new Phrase();
            for (PrzystanekWlini pp : p){
            Chunk chunk = new Chunk(pp.getPrzystanek().getNazwa(), new Font(BASE_FONT_PL));
            przystankiPhrase.add(chunk);
            przystankiPhrase.add(chunk.NEWLINE);
            }
            PdfPCell przystankiCell = new PdfPCell(przystankiPhrase);
            przystankiCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            przystankiCell.setPaddingTop(10);
            przystankiCell.setPaddingBottom(10);
            table.addCell(przystankiCell);

        }

    }
    private void addCustomRows(PdfPTable table, Long id) throws URISyntaxException, IOException {
        var vat = getAllByPrzystanekId(id);
        Map<String, List<String>> linieGodzinyMap = new HashMap<>();
        Map<String, List<BaseColor>> linieKoloryMap = new HashMap<>();
        Map<String, List<Boolean>> linieOdwMap = new HashMap<>();

        for (var $$$ : vat) {
            String liniaNumer = $$$.getLinia().getNumer();
            String godzinaStr = $$$.getT().toString();
            String formattedTime = godzinaStr.substring(0, 5);
            BaseColor textColor = getTextColor($$$.getHarmonogram().getNazwa());
            boolean isOdw = $$$.isOdw();

            linieGodzinyMap.putIfAbsent(liniaNumer, new ArrayList<>());
            linieGodzinyMap.get(liniaNumer).add(formattedTime);

            linieKoloryMap.putIfAbsent(liniaNumer, new ArrayList<>());
            linieKoloryMap.get(liniaNumer).add(textColor);

            linieOdwMap.putIfAbsent(liniaNumer, new ArrayList<>());
            linieOdwMap.get(liniaNumer).add(isOdw);
        }
      
        for (Map.Entry<String, List<String>> entry : linieGodzinyMap.entrySet()) {
            String liniaNumer = entry.getKey();
            List<String> godzinyList = entry.getValue();
            List<BaseColor> koloryList = linieKoloryMap.get(liniaNumer);
            List<Boolean> odwList = linieOdwMap.get(liniaNumer); // Lista z informacją o znaku podkreślenia

            PdfPCell liniaCell = new PdfPCell(new Phrase(liniaNumer));
            liniaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            liniaCell.setPaddingTop(10);
            liniaCell.setPaddingBottom(10);
            table.addCell(liniaCell);

            Phrase godzinyPhrase = new Phrase();
            int i = 0;
            for (var godzina : godzinyList) {
                BaseColor textColor = koloryList.get(i);
                boolean isOdw = odwList.get(i);
                
                Font font = new Font(BASE_FONT_PL, 12, Font.NORMAL, textColor);
                
                if (isOdw) {
                    font.setStyle(Font.UNDERLINE);
                }

                Chunk godzinaChunk = new Chunk(godzina, font);
                godzinyPhrase.add(godzinaChunk);
                godzinyPhrase.add(Chunk.SPACETABBING);
                i++;
            }

            PdfPCell godzinaCell = new PdfPCell(godzinyPhrase);
            godzinaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            godzinaCell.setPaddingTop(10);
            godzinaCell.setPaddingBottom(10);
            table.addCell(godzinaCell);
        }
    }
    private BaseColor getTextColor(String harmonogram) {
        if (harmonogram.equals("Dni robocze")) {
            return BaseColor.BLACK;
        } else if (harmonogram.equals("Dni robocze i weekendy")) {
            return BaseColor.BLUE;
        } else if (harmonogram.equals("weekendy")) {
            return BaseColor.RED;
        } else  {
            return BaseColor.BLACK;
        }
    }


    @Autowired
    public KursRepository kursRepository;

    @Autowired
    public PrzystanekRepository przystanekRepos;

    @Autowired
    public PrzystanekWliniRepository przystanekWliniRepos;

    @Autowired
    public LiniaRepository liniaRepos;


    public TreeSet<$> getAllByPrzystanekId(Long id) {
        TreeSet<$> data = new TreeSet<>();
        List<Kurs> kursy = kursRepository.findAll();

        for (Kurs kurs : kursy) {
            for (var kursprzystanek : kurs.getKursPrzystanekWlinii()) {
                var przystanek = kursprzystanek.getPrzystanekWlini().getPrzystanek();
                if (przystanek.getPrzystanekId().equals(id)) {

                    data.add(new $(kursprzystanek.getGodzinna(), new LiniaDto(kurs.getLinia()), kurs.getKierunek().equals(Short.valueOf((short) 1)), kurs.getHarmonogram()));
                    continue;
                }
            }
        }

        return data;
    }

    public int getLiczbaLiniiNaPrzystanku(Long id) {
        TreeSet<$> linieInfo = getAllByPrzystanekId(id);
        Set<String> uniqueLines = new HashSet<>();

        for ($ liniaInfo : linieInfo) {
            String liniaNumer = liniaInfo.getLinia().getNumer();
            uniqueLines.add(liniaNumer);
        }

        return uniqueLines.size();
    }

    
    public File genRozklad(Long id)  throws Exception{
    	String przystanekName = "nazwa";
    	File result = new File("rozklad_jazdy_"+przystanekName+".pdf");

        //Long id = (long)1;

        var przystanki = przystanekRepos.findAll();
        for(Przystanek p : przystanki) {
            if(p.getPrzystanekId().equals(id)) {
            przystanekName = p.getNazwa();
            }
        }
     
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(result));
        
        document.open();

        Font font = new Font(BASE_FONT_PL, 18, Font.BOLD);
        Paragraph header = new Paragraph("Rozklad jazdy", font);
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);
        document.add(new Paragraph(" "));

        Paragraph przystanek = new Paragraph("Przystanek: " + przystanekName, font);
        przystanek.setAlignment(Element.ALIGN_CENTER);
        document.add(przystanek);
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(2);
        table.setWidths(new float[]{1, 3});
        addTableHeader(table);
        addCustomRows(table, id);
        document.add(table);

        document.add(new Paragraph(" "));

        
        Paragraph legend = new Paragraph();
        legend.add(new Paragraph("Legenda: ", new Font(BASE_FONT_PL, 12, Font.BOLD)));
        legend.add(new Paragraph("Czarny - Dni robocze ", new Font(BASE_FONT_PL, 12, Font.NORMAL, BaseColor.BLACK)));
        legend.add(new Paragraph("Czerwony - Weekendy", new Font(BASE_FONT_PL, 12, Font.NORMAL, BaseColor.RED)));
        legend.add(new Paragraph("Niebieski - Dni robocze i weekendy", new Font(BASE_FONT_PL, 12, Font.NORMAL, BaseColor.BLUE)));
        legend.add(new Paragraph("Podkreślone - kierunek odwrotny", new Font(BASE_FONT_PL, 12, Font.UNDERLINE, BaseColor.BLACK)));
        
        document.add(legend);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        int liczbaLiniNaPrzystanku= getLiczbaLiniiNaPrzystanku(id);
        PdfPTable tableLinie = new PdfPTable(liczbaLiniNaPrzystanku);
        addLinieHeader(tableLinie, id);
        addLinieRows(tableLinie, id);
        document.add(tableLinie);

        document.close();
        
        
        
        return result;
    }
    
    //@Override
    @Transactional
    public void genruj(Long id) throws Exception {
    //public void run(String... args) throws Exception {
        String przystanekName = "nazwa";


        //Long id = (long)1;

        var przystanki = przystanekRepos.findAll();
        for(Przystanek p : przystanki) {
            if(p.getPrzystanekId().equals(id)) {
            przystanekName = p.getNazwa();
            }
        }
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("rozklad_jazdy_"+przystanekName+".pdf"));

        document.open();

        Font font = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph header = new Paragraph("Rozklad jazdy", font);
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);
        document.add(new Paragraph(" "));

        Paragraph przystanek = new Paragraph("Przystanek: " + przystanekName, font);
        przystanek.setAlignment(Element.ALIGN_CENTER);
        document.add(przystanek);
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(2);
        table.setWidths(new float[]{1, 3});
        addTableHeader(table);
        addCustomRows(table, id);
        document.add(table);

        document.add(new Paragraph(" "));

        Paragraph legend = new Paragraph();
        legend.add(new Paragraph("Legenda: ", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        legend.add(new Paragraph("Czarny - Dni robocze ", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK)));
        legend.add(new Paragraph("Czerwony - Weekendy", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED)));
        legend.add(new Paragraph("Niebieski - Dni robocze i weekendy", new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLUE)));
        document.add(legend);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        int liczbaLiniNaPrzystanku= getLiczbaLiniiNaPrzystanku(id);
        PdfPTable tableLinie = new PdfPTable(liczbaLiniNaPrzystanku);
        addLinieHeader(tableLinie, id);
        addLinieRows(tableLinie, id);
        document.add(tableLinie);


        document.close();
    }

public static class $ implements Comparable<$>{
    private Time t;
    private LiniaDto linia;
    private boolean odw;
    private Harmonogram harmonogram;


    public $(Time t, LiniaDto linia, boolean odw, Harmonogram harm) {
        super();
        this.setT(t);
        this.setLinia(linia);
        this.setOdw(odw);
        this.setHarmonogram(harm);

    }

    public $(Date date, LiniaDto linia, boolean odw, Harmonogram harm) {
        this(new Time(date.getTime()), linia, odw, harm);

    }

    @Override
    public int compareTo($ o) {

        return getT().compareTo(o.getT());
    }

    public LiniaDto getLinia() {
        return linia;
    }

    public void setLinia(LiniaDto linia) {
        this.linia = linia;
    }

    public Time getT() {
        return t;
    }

    public void setT(Time t) {
        this.t = t;
    }

    public boolean isOdw() {
        return odw;
    }

    public void setOdw(boolean odw) {
        this.odw = odw;
    }

    public void setHarmonogram(Harmonogram h) {
        this.harmonogram = h;
    }

    public Harmonogram getHarmonogram() {return harmonogram;}

}
}