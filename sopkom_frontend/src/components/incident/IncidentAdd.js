import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
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

function formatDateTime2(dateTimeStr) {
    if (!dateTimeStr) {
        return "--.--.----";
    }

    const dateIn = new Date(dateTimeStr);

    return dateIn.toLocaleDateString();
}

const IncidentAdd = () => {
  const [formData, setFormData] = useState({
    typ: "",
    date: "",
    krotko: "",
    autobusId: "null",
    kierowcaId: "null",
    przejazdId: "null",
    dodatkoweInformacje: "",
    koszty: "",
  });
  const [driverData, setDriverData] = useState([]);
  const [lineData, setLineData] = useState([]);
  const [busData, setBusData] = useState([]);
  const [savedMessage, setSavedMessage] = useState("");
  const history = useNavigate();
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

  const getLineData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/przejazd", {method: "GET", credentials: "include"});
      if (!response.ok) {
        throw new Error("Error fetching line data");
      }
      const data = await response.json();
      setLineData(data);
    } catch (error) {
      console.error("Error fetching line data:", error);
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    formData.date = formatDateTime2(formData.date);
    try {
      const response = await fetch(SERVER_URL + "/api/incydent/save", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData),
        credentials: "include",
      });

      if (!response.ok) {
        throw new Error("Failed to add incident");
      }

      setTimeout(() => {
        history('/incident');
      }, 200);

      setSavedMessage("Dodano incydent");

    } catch (error) {
      setSavedMessage("Nie udało się dodać incydentu");
      console.log(formData);
    }
  };

  useEffect(() => {
    getDriverData();
    getLineData();
    getBusData();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    const modifiedValue = value;
    setFormData(prevData => ({
      ...prevData,
      [name]: modifiedValue
    }));
  };

  return (
    <div className="pt-40">
      <div className="listDiv">
        {savedMessage && <p>{savedMessage}</p>}
        <form onSubmit={handleSubmit}>
          <table className="underListFormat">
            <tbody>
              <tr>
                <th>Typ incydentu:</th>
                <td><input className="incidentInput" required type="text" name="typ" value={formData.typ || ''} onChange={handleInputChange} /></td>
              </tr>
              <tr>
                <th>Krótki opis:</th>
                <td><input className="incidentInput" required type="text" name="krotko" value={formData.krotko || ''} onChange={handleInputChange} /></td>
              </tr>
              <tr>
                <th>Data incydentu:</th>
                <td>
                  <input className="incidentInput"
                    type="date"
                    name="date"
                    value={formData.date}
                    onChange={handleInputChange}
                    required
                  />
                </td>
              </tr>
              <tr>
                <th>Koszt:</th>
                <td><input className="incidentInput" required type="text" name="koszty" value={formData.koszty || ''} onChange={handleInputChange} /></td>
              </tr>
              <tr>
                <th>Kierowca:</th>
                <td>
                  <select
                    id="drv_sel"
                    name="kierowcaId"
                    className="dropDownCourse"
                    value={formData.kierowcaId || "null"}
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
                    value={formData.autobusId || "null"}
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
                    value={formData.przejazdId || "null"}
                    onChange={handleInputChange}
                  >
                    <option key="-1" value="null">Brak przejazdu</option>
                    {Array.isArray(lineData) && lineData.map((line) => (
                      <option key={line.liniaNumer} value={line.przejazdId}>
                        {line.przejazdId} - {line.liniaNumer} - {formatDateTime2(line.dataStartu)}
                      </option>
                    ))}
                  </select>
                </td>
              </tr>}
              <tr>
                <th>Dodatkowe informacje:</th>
                <td><textarea className="textAreaInput" required name="dodatkoweInformacje" value={formData.dodatkoweInformacje || ''} onChange={handleInputChange} /></td>
              </tr>
            </tbody>
          </table>
          <div>
            <Link className="infoBtn" to={`/incident`}>Powrót</Link>
            <button className="infoBtn" type="submit">Dodaj incydent</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default IncidentAdd;
