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

const IncidentAdd = () => {
  const [incidentData, setIncidentData] = useState([]);
  const [driverData, setDriverData] = useState([]);
  const [busData, setBusData] = useState([]);
  const [savedMessage, setSavedMessage] = useState("");
  const [formData, setFormData] = useState({
  typ: "",
  date: "",
  krotko: "",
  autobusId: "",
  kierowcaId: "",
  przejazdId: "",
  dodatkoweInformacje: "",
  koszty: "",
  });

  const getCourseData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/incydent" + extractLastPathComponent(window.location.href), { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("Error fetching incident data");
      }
      const data = await response.json();
      setIncidentData(data);
      setFormData(data);
    } catch (error) {
        console.error("Error fetching incident data:", error);
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

  const handleSubmit = async (e) => {
    e.preventDefault();
        try {
          const response = await fetch(SERVER_URL + "/api/incydent/save", {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            credentials: "include",
            body: JSON.stringify(formData)
          });
    
          if (!response.ok) {
            throw new Error("Failed to add incident");
          }
    
          setTimeout(() => {
            window.location.href = '/incident';
          }, 100);
    
          setSavedMessage("Dodano incydent");
    
        } catch (error) {
          setSavedMessage("Nie udało się dodać incydentu");
        }
  };

  useEffect(() => {
    getDriverData();
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


  const handleChange = (e) => {
    const { name, value } = e.target;
    const modifiedValue = name === 'prawoJazdyWazneDo' ? `${value.split('T')[0]}T00:00` : value;
    setFormData({ ...formData, [name]: modifiedValue });
  };

  return (
    <div className="pt-40">
      <div className="listDiv">
        {savedMessage && <p>{savedMessage}</p>}
        <table className="underListFormat">
          <tbody>
            <tr>
              <th>Typ incydentu:</th>
              <td><input className="infoInput" required type="text" name="typ" value={incidentData.typ || ''} onChange={handleInputChange} /></td>
            </tr>
            <tr>
              <th>Krótki opis:</th>
              <td><input className="infoInput" required type="text" name="krotko" value={incidentData.krotko || ''} onChange={handleInputChange} /></td>
            </tr>
            <tr>
              <th>Data incydentu:</th>
              <td>
                <input className="addInput"
                    type="datetime-local"
                    name="date"
                    value={formData.date}
                    onChange={handleChange}
                    required
                />
              </td>
            </tr>
            <tr>
              <th>Koszt:</th>
              <td><input className="infoInput" required type="text" name="koszty" value={incidentData.koszty || ''} onChange={handleInputChange} /></td>
            </tr>
            <tr>
              <th>Kierowca:</th>
              <td>
                <select
                  id="drv_sel"
                  name="kierowcaId"
                  className="dropDownCourse"
                  defaultValue={""}
                  onChange={handleInputChange}
                >
                  <option key="-1" value="">Brak kierowcy</option>
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
                  defaultValue={""}
                  onChange={handleInputChange}
                >
                  <option key="-1" value="">Brak autobusu</option>
                  {Array.isArray(busData) && busData.map((bus) => (
                    <option key={bus.autbousId} value={bus.autbousId}>
                      {bus.autbousId} - {bus.numerRejestracyjny}
                    </option>
                  ))}
                </select>
              </td>
            </tr>
            <tr>
              <th>Przejazd:</th>
              <td>
                <select
                  id="przejazd_sel"
                  name="przejazdId"
                  className="dropDownCourse"
                  defaultValue={""}
                  onChange={handleInputChange}
                >
                  <option key="-1" value="">Brak przejazdu</option>
                  {Array.isArray(busData) && busData.map((bus) => (
                    <option key={bus.autbousId} value={bus.autbousId}>
                      {bus.autbousId} - {bus.numerRejestracyjny}
                    </option>
                  ))}
                </select>
              </td>
            </tr>
            <tr>
              <th>Dodatkowe informacje:</th>
              <td><textarea className="textAreaInput" required name="dodatkoweInformacje" value={incidentData.dodatkoweInformacje || ''} onChange={handleInputChange} /></td>
            </tr>
          </tbody>
        </table>
        <div>
          <Link className="infoBtn" to={`/incident`}>Powrót</Link>
          <button className="infoBtn" onClick={handleSubmit}>Zapisz incydent</button>
        </div>
      </div>
    </div>
  );
}

export default IncidentAdd;
