import React from 'react'
import {useEffect , useState} from 'react';
import {SERVER_URL} from '../constant';
import { Link } from "react-router-dom";

function DayTime(dateTimeStr) {

    const [datePart, timePart] = dateTimeStr.split('T');
    const [day, month, year] = datePart.split('-');
    const time = timePart.split(' ')[0];

    const validDateTimeStr = `${year}-${month}-${day}T${time}`;

    const date = new Date(validDateTimeStr);

    if (isNaN(date.getTime())) {
        return 'Invalid Date';
    }

    const formattedTime = date.toLocaleTimeString();
    const formattedDate = date.toLocaleDateString();

    const back = formattedDate + " " + formattedTime;

    return back;
}

const PlannedCourseList = () => {
	const [courseData, setCourseData] = useState([{
                                                   "przejazd_id": 4,
                                                   "kursid": 6,
                                                   "linia": {
                                                     "numer": "M500"
                                                   },
                                                   "data_startu": "04-09-2020T10:13:40 -02:00",
                                                   "data_konca": "11-01-2018T12:55:54 -01:00",
                                                   "kierowca": {
                                                     "imienazwisko": "Imie1 Kierowca1"
                                                   },
                                                   "autobus": {
                                                     "nazwa": "Autobus0",
                                                     "numer_rejestracyjny": "SK 11231"
                                                   },
                                                   "spalanie": 11.4435,
                                                   "cena_za_litr": 6.5,
                                                   "dlugosc_trasy": 20.1709,
                                                   "liczba_biletow": 71,
                                                   "status": "zapl",
                                                   "przystanki": [
                                                     {
                                                       "przejazdkursprzystanekid": 1,
                                                       "kolejnosc": 1,
                                                       "przewidywana_godzinna": "28-07-2023T03:25:19 -02:00",
                                                       "realna_godzinna": "27-01-2022T04:00:57 -01:00",
                                                       "nazwa": "Snowville, Pooles Lane",
                                                       "przystanekid": 4
                                                     },
                                                     {
                                                       "przejazdkursprzystanekid": 4,
                                                       "kolejnosc": 2,
                                                       "przewidywana_godzinna": "14-01-2017T02:31:01 -01:00",
                                                       "realna_godzinna": "18-08-2017T11:19:18 -02:00",
                                                       "nazwa": "Kylertown, Dobbin Street",
                                                       "przystanekid": 9
                                                     },
                                                     {
                                                       "przejazdkursprzystanekid": 9,
                                                       "kolejnosc": 3,
                                                       "przewidywana_godzinna": "22-09-2017T08:19:43 -02:00",
                                                       "realna_godzinna": "16-11-2021T03:11:09 -01:00",
                                                       "nazwa": "Marshall, Brooklyn Avenue",
                                                       "przystanekid": 2
                                                     },
                                                     {
                                                       "przejazdkursprzystanekid": 7,
                                                       "kolejnosc": 4,
                                                       "przewidywana_godzinna": "20-02-2014T03:48:34 -01:00",
                                                       "realna_godzinna": "28-03-2024T11:52:55 -01:00",
                                                       "nazwa": "Lynn, Gardner Avenue",
                                                       "przystanekid": 8
                                                     },
                                                     {
                                                       "przejazdkursprzystanekid": 1,
                                                       "kolejnosc": 5,
                                                       "przewidywana_godzinna": "09-12-2018T03:30:06 -01:00",
                                                       "realna_godzinna": "06-06-2015T06:40:21 -02:00",
                                                       "nazwa": "Celeryville, Olive Street",
                                                       "przystanekid": 6
                                                     }
                                                   ]
                                                 }]);

	const getCourseData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/kursy/", {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setCourseData(data);

		} catch (error){


		}
	}

	 useEffect(()=>{
//		getCourseData();
	}, []);

  return (
        <div className="pt-40">
                    <h2>Lista zaplanowanyuch kursów:</h2>
            <div className="addDiv">
                <Link className="infoBtn" to={`/planned_course/save`}>Zaplanuj kurs</Link>
            </div>
            <div className="listDiv">
            <table className="tableFormat">
                <thead>
                    <tr>
                        <th>Lp.</th>
                        <th>Kurs</th>
                        <th>Data</th>
                    </tr>
                </thead>

                <tbody>
                    {Array.isArray(courseData) && courseData.map((course) => (

                        <tr>
                            <td>{course.przejazd_id}</td>
                            <td><Link className="infoBtn" to={`/planned_course/info/${course.przejazd_id}`}>{`${course.linia.numer}`}</Link></td>
                            <td>{DayTime(course.data_startu)}</td>
                        </tr>

                    ))}
                </tbody>
            </table>
            </div>
        </div>
  )
}

export default PlannedCourseList