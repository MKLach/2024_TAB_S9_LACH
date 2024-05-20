package com.mklachl.sopkom.raporty.rozkład;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mklachl.sopkom.model.dto.harmonogram.HarmonogramDto;
import com.mklachl.sopkom.model.dto.kurs.KursDto;
import com.mklachl.sopkom.model.dto.linia.LiniaDto;
import com.mklachl.sopkom.model.entity.Harmonogram;
import com.mklachl.sopkom.model.entity.Kurs;
import com.mklachl.sopkom.repository.KursPrzystanekWliniRepository;
import com.mklachl.sopkom.repository.KursRepository;

@Service
public class RozkladHelper {
	
	public static TreeSet<Date> tempDniSwiete = new TreeSet<>();
	
	static {
		 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		try {
			
			 // Fixed holidays
	        tempDniSwiete.add(sdf.parse("01.01.2024")); // New Year's Day
	        tempDniSwiete.add(sdf.parse("06.01.2024")); // Epiphany
	        tempDniSwiete.add(sdf.parse("01.05.2024")); // Labour Day
	        tempDniSwiete.add(sdf.parse("03.05.2024")); // Constitution Day
	        tempDniSwiete.add(sdf.parse("15.08.2024")); // Assumption of Mary
	        tempDniSwiete.add(sdf.parse("01.11.2024")); // All Saints' Day
	        tempDniSwiete.add(sdf.parse("11.11.2024")); // Independence Day
	        tempDniSwiete.add(sdf.parse("25.12.2024")); // Christmas Day
	        tempDniSwiete.add(sdf.parse("26.12.2024")); // Second Day of Christmas
	        
	        Date easterSunday = calculateEasterSunday(2024);
	        tempDniSwiete.add(easterSunday); // Easter Sunday
	        tempDniSwiete.add(addDays(easterSunday, 1)); // Easter Monday
	        tempDniSwiete.add(addDays(easterSunday, 49)); // Pentecost
	        tempDniSwiete.add(addDays(easterSunday, 60)); // Corpus Christi
	        
		} catch (Exception e) {
		
		}
		
		
	}
	
	 // Helper method to add days to a date
    private static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    // Method to calculate the date of Easter Sunday
    private static Date calculateEasterSunday(int year) {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int month = (h + l - 7 * m + 114) / 31;
        int day = ((h + l - 7 * m + 114) % 31) + 1;

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day); // month is 0-based
        return cal.getTime();
    }
	
	public static class $ implements Comparable<$>{
		private Time t;
		private LiniaDto linia;
		private boolean odw;
		
		
		public $(Time t, LiniaDto linia, boolean odw) {
			super();
			this.setT(t);
			this.setLinia(linia);
			this.setOdw(odw);
			
			
		}
		
		public $(Date date, LiniaDto linia, boolean odw) {
			this(new Time(date.getTime()), linia, odw);
			
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
		
		

	}
	
	@Autowired
	public KursRepository kursRepo;
	
	/**
	 * SUNDAY - first
	 * MONMDAY - second
	 * 
	 * @param id
	 * @param date
	 * @return
	 */
	public TreeSet<$> getAllByPrzystanekId(Long id, Date date) {
		
		TreeSet<$> data = new TreeSet<>();
		List<Kurs> kursy = kursRepo.findAll();
		
		
		for(int i = 0; i < kursy.size(); i++) {
			Kurs kurs = kursy.get(i);
			Harmonogram harmonogram = kurs.getHarmonogram();
			
			if(harmonogramZalicza(date, harmonogram)) {
				
				for(int p_ind = 0; p_ind < kurs.getKursPrzystanekWlinii().size(); p_ind++) {
					var kursprzystanek =  kurs.getKursPrzystanekWlinii().get(p_ind);
					var przystanek =  kursprzystanek.getPrzystanekWlini().getPrzystanek();
					
					if(przystanek.getPrzystanekId().equals(id)) {
						data.add(new $(kursprzystanek.getGodzinna(), new LiniaDto(kurs.getLinia()), kurs.getKierunek().equals(Short.valueOf((short) 1))));
						continue;
					}
					
				}
				
			}
		}
		
		return data;
		
	}
	
	public static boolean harmonogramZalicza(Date date, Harmonogram harm) {
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		
		//System.out.println(c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pl")));
		int day_val = c.get(Calendar.DAY_OF_WEEK);
		
		
		// TODO ;#
		if(tempDniSwiete.contains(date)) {
			return false;
		}
	
		switch (day_val) {
			case 1: {
				return  harm.isNie();
			}
			case 2: {
				return  harm.isPon();	
			}
			case 3: {
				return  harm.isWto();
			}
			case 4: {
				return  harm.isSro();
				
			}
			case 5: {
				return  harm.isCzw();
			}
			case 6: {
				return  harm.isPia();
				
			}
			case 7: {
				return  harm.isSob();
			}
			default:
				return false;
			}
		
	}
	
}
