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

function formatDateTime(dateTimeStr) {
console.log(dateTimeStr)
	if(!dateTimeStr){
		return { hour: "--:--", date: "--.--.----" };
	}
	
	const dateIn = new Date(dateTimeStr);
	
    return dateIn.toLocaleTimeString().slice(0,5) + " "+ dateIn.toLocaleDateString() ;
}



const PlannedCourseList = () => {
	const [courseData, setCourseData] = useState(null);
	/*const [courseData, setCourseData] = useState([
    {
        "przejazdId": 5,
        "kursId": 1,
        "liniaNumer": "M500",
        "dataStartu": "Mon May 27 2024 10:2:0 +0000",
        "dataKonca": "Mon May 27 2024 13:30:0 +0000",
        "data": "Sun May 26 2024 22:0:0 +0000",
        "kierowcaImieNazwisko": "Jan Nowak",
        "autobusNazwa": "SKHAKL",
        "autobusNumerRejestracyjny": "SKHAKL",
        "spalanie": 0.0,
        "cenaZaLitr": 0.0,
        "dlugoscTrasy": 0.0,
        "liczbaBiletow": 0,
        "status": "Zaplanowany",
        "kierunek": 0,
        "przystanki": [
            {
                "przejazdKursPrzystanekWliniId": 1,
                "kolejnosc": 1,
                "przewidywanaGodzinna": "12:32:00",
                "realnaGodzinna": null,
                "nazwa": "Centralny",
                "przystanekId": 1
            },
            {
                "przejazdKursPrzystanekWliniId": 2,
                "kolejnosc": 2,
                "przewidywanaGodzinna": "13:41:00",
                "realnaGodzinna": null,
                "nazwa": "Testowy1",
                "przystanekId": 3
            },
            {
                "przejazdKursPrzystanekWliniId": 3,
                "kolejnosc": 3,
                "przewidywanaGodzinna": "14:02:00",
                "realnaGodzinna": null,
                "nazwa": "Wolny",
                "przystanekId": 4
            },
            {
                "przejazdKursPrzystanekWliniId": 4,
                "kolejnosc": 4,
                "przewidywanaGodzinna": "15:00:00",
                "realnaGodzinna": null,
                "nazwa": "124 NOR",
                "przystanekId": 10
            }
        ]
    },
    {
        "przejazdId": 6,
        "kursId": 7,
        "liniaNumer": "LM500",
        "dataStartu": "Mon May 27 2024 8:32:0 +0000",
        "dataKonca": "Mon May 27 2024 21:34:0 +0000",
        "data": "Sun May 26 2024 22:0:0 +0000",
        "kierowcaImieNazwisko": "Aleksander Makowski",
        "autobusNazwa": "SK55557",
        "autobusNumerRejestracyjny": "SK55557",
        "spalanie": 0.0,
        "cenaZaLitr": 0.0,
        "dlugoscTrasy": 0.0,
        "liczbaBiletow": 0,
        "status": "Zaplanowany",
        "kierunek": 0,
        "przystanki": [
            {
                "przejazdKursPrzystanekWliniId": 5,
                "kolejnosc": 1,
                "przewidywanaGodzinna": "11:02:00",
                "realnaGodzinna": null,
                "nazwa": "123 NOR",
                "przystanekId": 8
            },
            {
                "przejazdKursPrzystanekWliniId": 6,
                "kolejnosc": 2,
                "przewidywanaGodzinna": "11:03:00",
                "realnaGodzinna": null,
                "nazwa": "Centralny",
                "przystanekId": 1
            },
            {
                "przejazdKursPrzystanekWliniId": 7,
                "kolejnosc": 3,
                "przewidywanaGodzinna": "22:05:00",
                "realnaGodzinna": null,
                "nazwa": "124 NOR",
                "przystanekId": 10
            },
            {
                "przejazdKursPrzystanekWliniId": 8,
                "kolejnosc": 4,
                "przewidywanaGodzinna": "23:04:00",
                "realnaGodzinna": null,
                "nazwa": "Wolny",
                "przystanekId": 4
            }
        ]
    },
    {
        "przejazdId": 7,
        "kursId": 3,
        "liniaNumer": "LM500",
        "dataStartu": "Sun May 26 2024 9:36:0 +0000",
        "dataKonca": "Sun May 26 2024 13:33:0 +0000",
        "data": "Sat May 25 2024 22:0:0 +0000",
        "kierowcaImieNazwisko": "Jan Nowak",
        "autobusNazwa": "SKHAKL",
        "autobusNumerRejestracyjny": "SKHAKL",
        "spalanie": 0.0,
        "cenaZaLitr": 0.0,
        "dlugoscTrasy": 0.0,
        "liczbaBiletow": 0,
        "status": "Zakończony",
        "kierunek": 1,
        "przystanki": [
            {
                "przejazdKursPrzystanekWliniId": 9,
                "kolejnosc": 1,
                "przewidywanaGodzinna": "15:03:00",
                "realnaGodzinna": "Sun May 26 2024 13:6:59 +0000",
                "nazwa": "123 NOR",
                "przystanekId": 8
            },
            {
                "przejazdKursPrzystanekWliniId": 10,
                "kolejnosc": 2,
                "przewidywanaGodzinna": "14:04:00",
                "realnaGodzinna": "Sun May 26 2024 12:6:59 +0000",
                "nazwa": "Centralny",
                "przystanekId": 1
            },
            {
                "przejazdKursPrzystanekWliniId": 11,
                "kolejnosc": 3,
                "przewidywanaGodzinna": "13:05:00",
                "realnaGodzinna": "Sun May 26 2024 11:6:59 +0000",
                "nazwa": "124 NOR",
                "przystanekId": 10
            },
            {
                "przejazdKursPrzystanekWliniId": 12,
                "kolejnosc": 4,
                "przewidywanaGodzinna": "12:06:00",
                "realnaGodzinna": "Sun May 26 2024 10:6:59 +0000",
                "nazwa": "Wolny",
                "przystanekId": 4
            }
        ]
    },
    {
        "przejazdId": 10,
        "kursId": 1,
        "liniaNumer": "M500",
        "dataStartu": "Tue May 28 2024 10:2:0 +0000",
        "dataKonca": "Tue May 28 2024 13:30:0 +0000",
        "data": "Mon May 27 2024 22:0:0 +0000",
        "kierowcaImieNazwisko": "Jan Nowak",
        "autobusNazwa": "SKHAKL",
        "autobusNumerRejestracyjny": "SKHAKL",
        "spalanie": 0.0,
        "cenaZaLitr": 0.0,
        "dlugoscTrasy": 0.0,
        "liczbaBiletow": 0,
        "status": "Zaplanowany",
        "kierunek": 0,
        "przystanki": [
            {
                "przejazdKursPrzystanekWliniId": 21,
                "kolejnosc": 1,
                "przewidywanaGodzinna": "12:32:00",
                "realnaGodzinna": null,
                "nazwa": "Centralny",
                "przystanekId": 1
            },
            {
                "przejazdKursPrzystanekWliniId": 22,
                "kolejnosc": 2,
                "przewidywanaGodzinna": "13:41:00",
                "realnaGodzinna": null,
                "nazwa": "Testowy1",
                "przystanekId": 3
            },
            {
                "przejazdKursPrzystanekWliniId": 23,
                "kolejnosc": 3,
                "przewidywanaGodzinna": "14:02:00",
                "realnaGodzinna": null,
                "nazwa": "Wolny",
                "przystanekId": 4
            },
            {
                "przejazdKursPrzystanekWliniId": 24,
                "kolejnosc": 4,
                "przewidywanaGodzinna": "15:00:00",
                "realnaGodzinna": null,
                "nazwa": "124 NOR",
                "przystanekId": 10
            }
        ]
    },
    {
        "przejazdId": 11,
        "kursId": 1,
        "liniaNumer": "M500",
        "dataStartu": "Wed May 29 2024 10:2:0 +0000",
        "dataKonca": "Wed May 29 2024 13:30:0 +0000",
        "data": "Tue May 28 2024 22:0:0 +0000",
        "kierowcaImieNazwisko": "Jan Nowak",
        "autobusNazwa": "SKHAKL",
        "autobusNumerRejestracyjny": "SKHAKL",
        "spalanie": 0.0,
        "cenaZaLitr": 0.0,
        "dlugoscTrasy": 0.0,
        "liczbaBiletow": 0,
        "status": "Zaplanowany",
        "kierunek": 0,
        "przystanki": [
            {
                "przejazdKursPrzystanekWliniId": 25,
                "kolejnosc": 1,
                "przewidywanaGodzinna": "12:32:00",
                "realnaGodzinna": null,
                "nazwa": "Centralny",
                "przystanekId": 1
            },
            {
                "przejazdKursPrzystanekWliniId": 26,
                "kolejnosc": 2,
                "przewidywanaGodzinna": "13:41:00",
                "realnaGodzinna": null,
                "nazwa": "Testowy1",
                "przystanekId": 3
            },
            {
                "przejazdKursPrzystanekWliniId": 27,
                "kolejnosc": 3,
                "przewidywanaGodzinna": "14:02:00",
                "realnaGodzinna": null,
                "nazwa": "Wolny",
                "przystanekId": 4
            },
            {
                "przejazdKursPrzystanekWliniId": 28,
                "kolejnosc": 4,
                "przewidywanaGodzinna": "15:00:00",
                "realnaGodzinna": null,
                "nazwa": "124 NOR",
                "przystanekId": 10
            }
        ]
    }
]);

	
	*/
	const getCourseData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/przejazd", {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setCourseData(data);

		} catch (error){


		}
	}

	 useEffect(()=>{
		getCourseData();
	}, []);

  return (
        <div className="pt-40">
                    <h2>Lista Przejazdów:</h2>
            <div className="addDiv">
                <Link className="infoBtn" to={`/planned_course/save`}>Zaplanuj przejazd</Link>
            </div>
            <div className="listDiv">
            <table className="tableFormat">
                <thead>
                    <tr>
                        <th>Lp.</th>
                        <th>Kurs</th>
                        <th>Data</th>
                        <th>Kierowca</th>
                        <th>Autobus</th>
                        <th>Status</th>
                    </tr>
                </thead>

                <tbody>
                    {Array.isArray(courseData) && courseData.map((course) => (

                        <tr>
                            <td>{course.przejazdId}</td>
                            <td><Link className="infoBtn" to={`/planned_course/info/${course.przejazdId}`}>{`${course.liniaNumer}`}</Link></td>
                            <td>{formatDateTime(course.dataStartu)}</td>
                            <td>{course.kierowcaImieNazwisko}</td>
                            <td>{course.autobusNazwa}</td>
                            <td>{course.status}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            </div>
        </div>
  )
}

export default PlannedCourseList