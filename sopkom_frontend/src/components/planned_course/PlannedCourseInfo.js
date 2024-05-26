import React from 'react'
import { useEffect , useState} from 'react';
import {SERVER_URL} from '../constant';
import { Link } from "react-router-dom";
import { useHistory } from 'react-router-dom';


function extractLastPathComponent(url) {
    let index = 0;
    let result = "";
    for (let i = url.length - 1; i > 0; i--) {
        if (url[i] === '/') {
            index = i;
            break;
        }
    }
    for (let i = index; i < url.length; i++) {
        result += url[i];
    }
    return result;
}

function formatDateTime(dateTimeStr) {

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

    return { hour: formattedTime, date: formattedDate };
}

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

    const back = formattedTime + " " + formattedDate;

    return back;
}

const PlannedCourseInfo = () => {
	const [courseData, setCourseData] = useState(
      {
        "przejazd_id": 2,
        "kursid": 1,
        "data_startu": "05-07-2022T03:13:05 -02:00",
        "data_konca": "24-12-2016T07:38:53 -01:00",
        "kierowca": {
          "imienazwisko": "Imie1 Kierowca1"
        },
        "autobus": {
          "nazwa": "Autobus0",
          "numer_rejestracyjny": "SK 11231"
        },
        "spalanie": 13.4703,
        "cena_za_litr": 6.5,
        "dlugosc_trasy": 25.6875,
        "liczba_biletow": 76,
        "status": "zapl",
        "przystanki": [
          {
            "przejazdkursprzystanekid": 4,
            "kolejnosc": 1,
            "przewidywana_godzinna": "13-03-2015T03:00:08 -01:00",
            "realna_godzinna": "17-03-2020T05:55:26 -01:00",
            "nazwa": "Enlow, Kimball Street",
            "przystanekid": 9
          },
          {
            "przejazdkursprzystanekid": 6,
            "kolejnosc": 2,
            "przewidywana_godzinna": "16-01-2018T05:52:19 -01:00",
            "realna_godzinna": "23-03-2019T01:20:57 -01:00",
            "nazwa": "Fingerville, Middagh Street",
            "przystanekid": 6
          },
          {
            "przejazdkursprzystanekid": 10,
            "kolejnosc": 3,
            "przewidywana_godzinna": "11-12-2014T01:37:32 -01:00",
            "realna_godzinna": "01-07-2016T09:59:42 -02:00",
            "nazwa": "Bodega, Melba Court",
            "przystanekid": 5
          },
          {
            "przejazdkursprzystanekid": 7,
            "kolejnosc": 4,
            "przewidywana_godzinna": "18-07-2022T08:44:04 -02:00",
            "realna_godzinna": "27-01-2024T05:39:05 -01:00",
            "nazwa": "Ona, Irving Avenue",
            "przystanekid": 5
          },
          {
            "przejazdkursprzystanekid": 9,
            "kolejnosc": 5,
            "przewidywana_godzinna": "30-10-2014T12:28:34 -01:00",
            "realna_godzinna": "01-02-2015T04:52:52 -01:00",
            "nazwa": "Hoehne, Bridgewater Street",
            "przystanekid": 7
          }
        ]
      }
    );
    const [savedMessage, setSavedMessage] = useState("");

    //Trza zmienić API
	const getCourseData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/nic"+extractLastPathComponent(window.location.href), {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setCourseData(data);
            console.log(data);

		} catch (error){

		}
	}

	 useEffect(()=>{
		//getCourseData();
		console.log(courseData);
	}, []);

    //Trza zmienić API
    const deleteCourse = async () => {
            try {
                const response = await fetch(SERVER_URL + "/api/nic/" + courseData.przejazd_id, {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json"
                    }
                });
                if (!response.ok) {
                    throw new Error("Failed to delete planned course");
                }
                setTimeout(() => {
                    window.location.href = '/planned_course';
                }, 100);

            } catch (error) {
                setSavedMessage("Nie udało się usunąć kursu.");
            }
        };

    return (
        <div className="pt-40">

        <h1>Szczegóły kursu:</h1>

           <div className="listDiv">
            {savedMessage && <p>{savedMessage}</p>}

                <table className="underListFormat">
                    <tbody>
                        <tr>
                            <th>Zaplanowany start:</th>
                            <td>{DayTime(courseData.data_startu)}</td>
                        </tr>
                        <tr>
                            <th>Kierowca:</th>
                            <td>{courseData.kierowca.imienazwisko}</td>
                        </tr>
                        <tr>
                            <th>Autobus:</th>
                            <td>{courseData.autobus.nazwa}</td>
                        </tr>
                        <tr>
                            <th>Numer rejestracyjny:</th>
                            <td>{courseData.autobus.numer_rejestracyjny}</td>
                        </tr>
                        <tr>
                            <th>Spalanie:</th>
                            <td>{courseData.spalanie} l/km</td>
                        </tr>
                        <tr>
                            <th>Cena za litr:</th>
                            <td>{courseData.cena_za_litr} zł</td>
                        </tr>
                        <tr>
                            <th>Długość trasy:</th>
                            <td>{courseData.dlugosc_trasy} km</td>
                        </tr>
                        <tr>
                            <th>Liczba biletów:</th>
                            <td>{courseData.liczba_biletow}</td>
                        </tr>
                        <tr>
                            <th>Status:</th>
                            <td>{courseData.status}</td>
                        </tr>
                    </tbody>
                </table>

            <table className="tableFormat">
                <thead>
                    <tr>
                        <th rowspan="2">Przystanek</th>
                        <th colSpan="2">Zaplanowany przyjazd</th>
                        <th colSpan="2">Rzeczywisty przyjazd</th>
                    </tr>
                    <tr>
                        <th>Godzina</th>
                        <th>Data</th>
                        <th>Godzina</th>
                        <th>Data</th>
                    </tr>
                </thead>
                <tbody>
                    {courseData.przystanki && courseData.przystanki
                    .sort((a, b) => a.kolejnosc - b.kolejnosc)
                    .map((stop, stopIndex) => {
                        const plannedArrival = formatDateTime(stop.przewidywana_godzinna);
                        const actualArrival = formatDateTime(stop.realna_godzinna);
                        return (
                            <tr key={stopIndex}>
                                <td>{stop.kolejnosc}</td>
                                <td>{plannedArrival.hour}</td>
                                <td>{plannedArrival.date}</td>
                                <td>{actualArrival.hour}</td>
                                <td>{actualArrival.date}</td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>

            {courseData.status === "zapl" && (
                                <button className="infoBtn" onClick={deleteCourse}>Usuń kurs</button>
            )}

            <div>
            <Link className="infoBtn" to={`/planned_course`}>Powrót</Link>
            </div>
        </div>
        </div>
    )
}

export default PlannedCourseInfo