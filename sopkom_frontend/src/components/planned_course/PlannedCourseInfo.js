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

const PlannedCourseInfo = () => {
	const [driverData, setDriverData] = useState([]);
    const [savedMessage, setSavedMessage] = useState("");

    const saveChanges = async () => {
        try {
            const response = await fetch(SERVER_URL + "/api/kierowca/" + driverData.kierowcaId, {
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(driverData)
            });
            if (!response.ok) {
                throw new Error("Failed to save changes");
            }
            setSavedMessage("Zmiany zostały zapisane pomyślnie!");
        } catch (error) {
            setSavedMessage("Zmiany nie zostały zapisane.");

        }
    };


	const getDriverData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/kierowca"+extractLastPathComponent(window.location.href), {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setDriverData(data);

		} catch (error){


		}
	}

	 useEffect(()=>{
		getDriverData();
	}, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setDriverData(prevData => ({
            ...prevData,
            [name]: value
        }));
    };

    const deleteDriver = async () => {
            try {
                const response = await fetch(SERVER_URL + "/api/kierowca/" + driverData.kierowcaId, {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json"
                    }
                });
                if (!response.ok) {
                    throw new Error("Failed to delete driver");
                }
                setTimeout(() => {
                    window.location.href = '/driver';
                }, 100);

            } catch (error) {
                setSavedMessage("Nie udało się usunąć kierowcy.");
            }
        };

    return (
        <div className="pt-40">

        <p>Szczegóły kursu</p>

           <div className="listDiv">
            {savedMessage && <p>{savedMessage}</p>}

            <table className="tableFormat">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Imię</th>
                        <th>Nazwisko</th>
                        <th>Wygaśnięcie prawa jazdy</th>
                        <th>Pesel</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{driverData.kierowcaId}</td>
                        <td><input className="infoInput" type="text" name="imie" value={driverData.imie || ''} onChange={handleInputChange} /></td>
                        <td><input className="infoInput" type="text" name="nazwisko" value={driverData.nazwisko || ''} onChange={handleInputChange} /></td>
                        <td><input className="infoInput" type="text" name="prawoJazdyWazneDo" value={driverData.prawoJazdyWazneDo || ''} onChange={handleInputChange} /></td>
                        <td><input className="infoInput" type="text" name="pesel" value={driverData.pesel || ''} onChange={handleInputChange} /></td>
                        <td><button className="infoBtn" onClick={deleteDriver} >Usuń</button></td>
                    </tr>
                </tbody>
            </table>
            <div>
            <Link className="infoBtn" to={`/driver`}>Powrót</Link>
            <button className="infoBtn" onClick={saveChanges} >Zapisz zmiany</button>
            </div>
        </div>
        </div>
    )
}

export default PlannedCourseInfo