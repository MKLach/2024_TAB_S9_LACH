import React from 'react'
import { useEffect , useState} from 'react';
import {SERVER_URL} from './constant';
import { Link } from "react-router-dom";


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

const BusInfo = () => {
	const [busData, setBusData] = useState([]);
    const [savedMessage, setSavedMessage] = useState("");

const saveChanges = async () => {
    try {
        const response = await fetch(SERVER_URL + "/api/autobus/" + busData.autobusId, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(busData)
        });
        if (!response.ok) {
            throw new Error("Failed to save changes");
        }
        setSavedMessage("Zmiany zostały zapisane pomyślnie!");
    } catch (error) {
//        setSavedMessage("Zmiany nie zostały zapisane.");
        setSavedMessage("Nie można jeszcze aktualizować bazy autobusów");

    }
};


	const getBusData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/autobus"+extractLastPathComponent(window.location.href), {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setBusData(data);

		} catch (error){


		}
	}

	 useEffect(()=>{
		getBusData();
	}, []);


    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setBusData(prevData => ({
            ...prevData,
            [name]: value
        }));
    };

  return (
            <div className="pt-40">
                <div className="listDiv">
            {savedMessage && <p>{savedMessage}</p>}

                    <table className="tableFormat">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Numer rejestracyjny</th>
                                <th>Status</th>
                                <th>Ważność przeglądu</th>
                                <th>Przebieg</th>
                            </tr>
                        </thead>
                        <tbody>
                                <tr>
                                    <td>{busData.autbousId}</td>
                                    <td><input className="infoInput" type="text" name="numerRejestracyjny" value={busData.numerRejestracyjny || ''} onChange={handleInputChange} /></td>
                                    <td><input className="infoInput" type="text" name="status" value={busData.status || ''} onChange={handleInputChange} /></td>
                                    <td><input className="infoInput" type="text" name="przegladWaznyDo" value={busData.przegladWaznyDo || ''} onChange={handleInputChange} /></td>
                                    <td><input className="infoInput" type="text" name="przebieg" value={busData.przebieg || ''} onChange={handleInputChange} /></td>
                                </tr>
                        </tbody>
                    </table>
            <div>
            <Link className="infoBtn" to={`/bus`}>Powrót</Link>
            <button className="infoBtn" onClick={saveChanges} >Zapisz zmiany</button>
            </div>
            </div>
            </div>
  )
}

export default BusInfo