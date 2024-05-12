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
            const requestBody = {
                numerRejestracyjny: busData.numerRejestracyjny,
                status: busData.status,
                przebieg: parseFloat(busData.przebieg),
                przegladWaznyDo: new Date(busData.przegladWaznyDo).toISOString()
            };

            const response = await fetch(SERVER_URL + "/api/autobus/" + busData.autbousId, {
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(requestBody)
            });

            const responseData = await response.json();

            if (!response.ok) {
                throw new Error(responseData.message || "Failed to save changes");
            }

            setSavedMessage("Zmiany zostały zapisane pomyślnie!");
        } catch (error) {
            setSavedMessage("Nie udało się zaktualizować bazy autobusów!");
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

        const deleteBus = async () => {
                try {
                    const response = await fetch(SERVER_URL + "/api/autobus/" + busData.autbousId, {
                        method: "DELETE",
                        headers: {
                            "Content-Type": "application/json"
                        }
                    });
                    if (!response.ok) {
                        throw new Error("Failed to delete driver");
                    }
                    setTimeout(() => {
                        window.location.href = '/bus';
                    }, 200);

                } catch (error) {
                    setSavedMessage("Nie udało się usunąć pojazdu.");
                }
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
                                    <td><button className="infoBtn" onClick={deleteBus} >Usuń</button></td>
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