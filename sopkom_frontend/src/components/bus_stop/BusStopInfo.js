import React from 'react'
import { useEffect , useState} from 'react';
import {SERVER_URL} from '../constant';
import { Link, useNavigate } from "react-router-dom";


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

const BusStopInfo = () => {
	const [stopData, setStopData] = useState({
	    nazwa: ''
	});
    const [savedMessage, setSavedMessage] = useState("");
    const [availableStops, setAvailableStops] = useState([]);
	const history = useNavigate();
    const saveChanges = async () => {
        try {
            const response = await fetch(SERVER_URL + "/api/przystanek/" + stopData.przystanekId, {
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(stopData)
            });
            if (!response.ok) {
                throw new Error("Failed to save changes");
            }
            setSavedMessage("Zmiany zostały zapisane pomyślnie!");
        } catch (error) {
            setSavedMessage("Zmiany nie zostały zapisane.");

        }
    };


	const getStopData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/przystanek"+extractLastPathComponent(window.location.href), {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setStopData(data);

		} catch (error){


		}
	}



    const getAvailableStops = async () => {
        try {
            const response = await fetch(SERVER_URL + "/api/przystanek", { method: "GET", credentials: "include" });
            if (!response.ok) {
                throw new Error("error on get available stops");
            }
            const data = await response.json();
            setAvailableStops(data);
        } catch (error) {
            // handle error
        }
    };

    useEffect(() => {
        getStopData();
        getAvailableStops();
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setStopData(prevData => ({
            ...prevData,
            [name]: value
        }));
    };

    const deleteStop = async () => {
            try {
                const response = await fetch(SERVER_URL + "/api/przystanek/" + stopData.przystanekId, {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json"
                    }
                });
                if (!response.ok) {
                    throw new Error("Failed to delete bus stop");
                }
                setTimeout(() => {
                    history('/bus_stop');
                }, 100);

            } catch (error) {
                setSavedMessage("Nie udało się usunąć przystanku.");
            }
        };

	const downloadPdf= async () => {
		/*try {
	        const response = await fetch(SERVER_URL + "/api/raporty/rozklad?id=" + stopData.przystanekId, {
	            method: "GET",
	            headers: {
	                "Content-Type": "application/pdf"
	            },
	            credentials: "include"
	            
	        });
	        
	        
	        if (!response.ok) {
	            throw new Error("Failed to load file");
	        }
	        
	        let blob = response.blob()
	        
	        const url = window.URL.createObjectURL(
		      new Blob([blob]),
		    );
		    const link = document.createElement('a');
		    link.href = url;
		    link.setAttribute(
		      'download',
		      `FileName.pdf`,
		    );
		
		    // Append to html link element page
		    document.body.appendChild(link);
		
		    // Start download
		    link.click();
		
		    // Clean up and remove the link
		    link.parentNode.removeChild(link);
	        
	        setSavedMessage("Zmiany zostały zapisane pomyślnie!");
	    } catch (error) {
	        setSavedMessage(error.message);
			return;
	    }*/
	    
	    fetch(SERVER_URL + "/api/raporty/rozklad?id=" + stopData.przystanekId, {
		    method: 'GET',
		    headers: {
		      'Content-Type': 'application/pdf',
		    },
		  })
		  .then((response) => response.blob())
		  .then((blob) => {
		    // Create blob link to download
		    const url = window.URL.createObjectURL(
		      new Blob([blob]),
		    );
		    const link = document.createElement('a');
		    link.href = url;
		    link.setAttribute(
		      'download',
		       stopData.nazwa +  ` - rozklad ` + new Date().toLocaleDateString() + `.pdf`,
		    );
		
		    // Append to html link element page
		    document.body.appendChild(link);
		
		    // Start download
		    link.click();
		
		    // Clean up and remove the link
		    link.parentNode.removeChild(link);
		  });
		
	
	}



    return (
        <div className="pt-40">
           <div className="listDiv">
            {savedMessage && <p>{savedMessage}</p>}

            <table className="tableFormat">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nazwa</th>
                        <th>Miasto</th>
                        <th>Ulica</th>
                        <th>Kod pocztowy</th>
                        <th>Długość geogradiczna</th>
                        <th>Szerokość geogradiczna</th>
                        <th>Przystanek odwrotny</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{stopData.przystanekId}</td>
                        <td><input className="infoInput" type="text" name="nazwa" value={stopData.nazwa || ''} onChange={handleInputChange} /></td>
                        <td><input className="infoInput" type="text" name="miasto" value={stopData.miasto || ''} onChange={handleInputChange} /></td>
                        <td><input className="infoInput" type="text" name="ulica" value={stopData.ulica || ''} onChange={handleInputChange} /></td>
                        <td><input className="infoInput" type="text" name="kodPocztowy" value={stopData.kodPocztowy || ''} onChange={handleInputChange} /></td>
                        <td><input className="infoInput" type="text" name="dlugoscGeograficzna" value={stopData.dlugoscGeograficzna || ''} onChange={handleInputChange} /></td>
                        <td><input className="infoInput" type="text" name="szerokoscGeograficzna" value={stopData.szerokoscGeograficzna || ''} onChange={handleInputChange} /></td>
                        <td>
                            <select disabled={stopData.nazwa.includes("ODW")} className="selectInput" name="przystanekOdwrotny" value={stopData.przystanekOdwrotny || 'brak'} onChange={handleInputChange}>
                                <option value="-1">Brak</option>
                                {availableStops.map(stop => {
                                    if (stop.przystanekId === stopData.przystanekId) {
                                        return null;
                                    }
                                    return <option key={stop.przystanekId} value={stop.przystanekId}>{stop.przystanekId} - {stop.nazwa}</option>;
                                })}
                            </select>
                        </td>
                        <td><button className="infoBtn" onClick={deleteStop} >Usuń</button></td>
                    </tr>
                </tbody>
            </table>
            <div>
            <button className="infoBtn" onClick={saveChanges} >Zapisz zmiany</button>
            <div>
            {  <Link className="infoBtn" onClick={downloadPdf}>Pobierz Rozkład</Link>}
            <Link className="infoBtn" to={`/bus_stop`}>Powrót do przystanków</Link>
            <Link className="infoBtn" to={`/bus_line`}>Powrót do lini</Link>
            </div>
            </div>
        </div>
        </div>
    )
}

export default BusStopInfo