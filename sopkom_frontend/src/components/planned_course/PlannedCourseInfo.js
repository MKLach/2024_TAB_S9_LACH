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
console.log(dateTimeStr)
	if(!dateTimeStr){
		return { hour: "--:--", date: "--.--.----" };
	}
	
	const dateIn = new Date(dateTimeStr);
	
   /* const [datePart, timePart] = dateTimeStr.split('T');
    const [day, month, year] = datePart.split('-');
    const time = timePart.split(' ')[0];

    const validDateTimeStr = `${year}-${month}-${day}T${time}`;

    const date = new Date(validDateTimeStr);

    if (isNaN(date.getTime())) {
        return 'Invalid Date';
    }

    const formattedTime = date.toLocaleTimeString();
    const formattedDate = date.toLocaleDateString();*/

    return { hour: dateIn.toLocaleTimeString().slice(0,5), date: dateIn.toLocaleDateString() };
}

function formatTime(dateTimeStr) {

	
    return { hour: dateTimeStr.slice(0,5) };
}

function parseCustomDate(dateString) {
    // Split the date string into components
    const parts = dateString.split(/[-T: ]/);

    // Extract timezone offset
    const timezoneOffset = dateString.match(/[-+]\d{2}:\d{2}$/)[0];

    // Create a new Date object
    const parsedDate = new Date(
        parts[2], // Year
        parts[1] - 1, // Month (Note: Months are zero-based in JavaScript)
        parts[0], // Day
        parts[3], // Hour
        parts[4], // Minute
        parts[5] // Second
    );

    // Adjust for timezone offset
    const timezoneOffsetInMinutes = parseInt(timezoneOffset.slice(1, 3)) * 60 + parseInt(timezoneOffset.slice(4));
    const utcMilliseconds = parsedDate.getTime();// + (parsedDate.getTimezoneOffset() * 60000);
    const parsedDateWithOffset = new Date(utcMilliseconds + (timezoneOffset.startsWith('-') ? 1 : -1) * timezoneOffsetInMinutes * 60000);

    return parsedDateWithOffset.toDateString();
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
	const [courseData, setCourseData] = useState([]);
    const [savedMessage, setSavedMessage] = useState("");

    //Trza zmienić API
	const getCourseData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/przejazd"+extractLastPathComponent(window.location.href), {method: "GET", credentials: "include"});
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
		getCourseData();
		console.log(courseData);
	}, []);

    //Trza zmienić API
    const deleteCourse = async () => {
            try {
                const response = await fetch(SERVER_URL + "/api/przejazd/" + courseData.przejazdId, {
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

        <h1>Szczegóły Przejazdu:</h1>

           <div className="listDiv">
            {savedMessage && <p>{savedMessage}</p>}

                <table className="underListFormat">
                    <tbody>
                    	 <tr>
                            <th>Numer Linii:</th>
                            <td>{courseData.liniaNumer}</td>
                        </tr>
                         <tr>
                            <th>Kierunek:</th>
                            <td>{courseData.kierunek ? "Odwrotny" : "Normalny"}</td>
                        </tr>
                        <tr>
                            <th>Zaplanowany na:</th>
                            <td>{new Date(courseData.data).toLocaleDateString()}</td>
                        </tr>
                         <tr>
                            <th>Rezerwacje od:</th>
                            <td>{
							    formatDateTime(courseData.dataStartu).hour + ", "+
							    formatDateTime(courseData.dataStartu).date 	 
							}</td>
                        </tr>
                         <tr>
                            <th>Rezerwacje do:</th>
                            <td>{
							    formatDateTime(courseData.dataKonca).hour + ", "+
							    formatDateTime(courseData.dataKonca).date 	 
							}</td>
                        </tr>
                        
                        <tr>
                            <th>Kierowca:</th>
                            <td>{courseData.kierowcaImieNazwisko}</td>
                        </tr>
                        <tr>
                            <th>Autobus:</th>
                            <td>{courseData.autobusNazwa}</td>
                        </tr>
                        <tr>
                            <th>Numer rejestracyjny:</th>
                            <td>{courseData.autobusNumerRejestracyjny}</td>
                        </tr>
                        <tr>
                            <th>Spalanie:</th>
                            <td>{courseData.spalanie ? courseData.spalanie + "l/km" : "brak" } </td>
                        </tr>
                        
                        <tr>
                            <th>Cena za litr:</th>
                            <td>{courseData.cenaZaLitr ? courseData.cenaZaLitr + " zł": "brak"} </td>
                        </tr>
                        <tr>
                            <th>Długość trasy:</th>
                            <td>{courseData.dlugoscTrasy ? courseData.dlugoscTrasy + " km" : "brak"}</td>
                        </tr>
                        <tr>
                            <th>Liczba biletów:</th>
                            <td>{courseData.liczbaBiletow ? courseData.liczbaBiletow : "brak"}</td>
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
                        <th colSpan="1">Zaplanowany</th>
                        <th colSpan="2">Rzeczywisty przyjazd</th>
                    </tr>
                    <tr>
                        <th>Godzina</th>

                        <th>Godzina</th>
                        <th>Data</th>
                    </tr>
                </thead>
                <tbody>
                    {courseData.przystanki && courseData.przystanki
                    .sort((a, b) => {
						if(courseData.kierunek == 0){
							
							return a.kolejnosc - b.kolejnosc;
						} else {
							
							return b.kolejnosc - a.kolejnosc;
						}
					})
                    .map((stop, stopIndex) => {
                        const plannedArrival = formatTime(stop.przewidywanaGodzinna);
                        const actualArrival = formatDateTime(stop.realnaGodzinna);
                        return (
                            <tr key={stopIndex}>
                                <td>{stopIndex+1}</td>
                                <td>{plannedArrival.hour}</td>
                                
                                <td>{actualArrival.hour}</td>
                                
                                <td>{actualArrival.date}</td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>

            {courseData.status === "Zaplanowany" && (
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