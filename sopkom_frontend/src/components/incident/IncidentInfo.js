import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link, useSearchParams } from "react-router-dom";

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

const IncidentInfo = () => {
  const [incidentData, setIncidentData] = useState({});
  const [lineData, setLineData] = useState([]);
  const [driverData, setDriverData] = useState([]);
  const [busData, setBusData] = useState([]);
  const [savedMessage, setSavedMessage] = useState("");

  const getCourseData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/incydent" + extractLastPathComponent(window.location.href), { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("Error fetching incident data");
      }
      const data = await response.json();
      setIncidentData(data);
    } catch (error) {
        console.error("Error fetching incident data:", error);
    }
  }

  const getLineData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/linia", {method: "GET", credentials: "include"});
      if (!response.ok) {
        throw new Error("Error fetching line data");
      }
      const data = await response.json();
      setLineData(data);
    } catch (error) {
      console.error("Error fetching line data:", error);
    }
  }

  const getDriverData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/kierowca/", {method: "GET", credentials: "include"});
      if (!response.ok) {
        throw new Error("Error fetching driver data");
      }
      const data = await response.json();
      setDriverData(data);
    } catch (error) {
      console.error("Error fetching driver data:", error);
    }
  }

  const getBusData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/autobus/", {method: "GET", credentials: "include"});
      if (!response.ok) {
        throw new Error("Error fetching bus data");
      }
      const data = await response.json();
      setBusData(data);
    } catch (error) {
      console.error("Error fetching bus data:", error);
    }
  }

  const saveChanges = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/incydent" + extractLastPathComponent(window.location.href), {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(incidentData)
      });
      if (!response.ok) {
        throw new Error("Failed to save changes");
      }
      setSavedMessage("Zmiany zostały zapisane pomyślnie!");
    } catch (error) {
      setSavedMessage("Zmiany nie zostały zapisane.");
      console.error("Error saving changes:", error);
    }
  };

  useEffect(() => {
    getDriverData();
    getLineData();
    getBusData();
    getCourseData();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setIncidentData(prevData => ({
      ...prevData,
      [name]: value
    }));
  };

  return (
    <div className="pt-40">
      <div className="listDiv">
        {savedMessage && <p>{savedMessage}</p>}
        <table className="underListFormat">
          <tbody>
            <tr>
              <th>Typ incydentu:</th>
              <td><input className="incidentInput" required type="text" name="typ" value={incidentData.typ || ''} onChange={handleInputChange} /></td>
            </tr>
            <tr>
              <th>Krótki opis:</th>
              <td><input className="incidentInput" required type="text" name="krotko" value={incidentData.krotko || ''} onChange={handleInputChange} /></td>
            </tr>
            <tr>
              <th>Data incydentu:</th>
              <td><input className="incidentInput" required type="text" name="date" value={incidentData.date || ''} onChange={handleInputChange} /></td>
            </tr>
            <tr>
              <th>Koszt:</th>
              <td><input className="incidentInput" required type="text" name="koszty" value={incidentData.koszty || ''} onChange={handleInputChange} /></td>
            </tr>
            <tr>
              <th>Kierowca:</th>
              <td>
                <select
                  id="drv_sel"
                  name="kierowcaId"
                  className="dropDownCourse"
                  value={incidentData.kierowcaId || ""}
                  onChange={handleInputChange}
                >
                  <option key="-1" value="null">Brak kierowcy</option>
                  {Array.isArray(driverData) && driverData.map((driver) => (
                    <option key={driver.kierowcaId} value={driver.kierowcaId}>
                      {driver.kierowcaId} - {driver.imie} {driver.nazwisko}
                    </option>
                  ))}
                </select>
              </td>
            </tr>
            <tr>
              <th>Autobus:</th>
              <td>
                <select
                  id="bus_sel"
                  name="autobusId"
                  className="dropDownCourse"
                  value={incidentData.autobusId || ""}
                  onChange={handleInputChange}
                >
                  <option key="-1" value="null">Brak autobusu</option>
                  {Array.isArray(busData) && busData.map((bus) => (
                    <option key={bus.autbousId} value={bus.autbousId}>
                      {bus.autbousId} - {bus.numerRejestracyjny}
                    </option>
                  ))}
                </select>
              </td>
            </tr>
           {false && <tr>
               <th>Przejazd:</th>
              <td>
                <select
                  id="przejazd_sel"
                  name="przejazdId"
                  className="dropDownCourse"
                  value={incidentData.przejazdId || ""}
                  onChange={handleInputChange}
                >
                  <option key="-1" value="null">Brak przejazdu</option>
                  {Array.isArray(lineData) && lineData.map((line) => (
                    <option key={line.id} value={line.id}>
                      {line.id} - {line.numer}
                    </option>
                  ))}
                </select>
              </td>
            </tr>}
            <tr>
              <th>Dodatkowe informacje:</th>
              <td><textarea className="textAreaInput" required name="dodatkoweInformacje" value={incidentData.dodatkoweInformacje || ''} onChange={handleInputChange} /></td>
            </tr>
          </tbody>
        </table>
        <div>
          <Link className="infoBtn" to={`/incident`}>Powrót</Link>
          <button className="infoBtn" onClick={saveChanges}>Zapisz zmiany</button>
        </div>
      </div>
    </div>
  );
}

export default IncidentInfo;
