import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";

const CourseAdd = () => {

  const [lineData, setLineData] = useState([]);
  const [selectedLine, setSelectedLine] = useState([]);
  const [harmonogramData, setHarmonogramData] = useState([]);
  const [stopsData, setStopsData] = useState([]);

    const [formData, setFormData] = useState({
      linia: "",
      kieruenk: "",
      przystanki: [{ przystanekId: "", godzinna: "" }]
    });

  const getLineData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/linia", { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("error on get 2");
      }
      const data = await response.json();
      setLineData(data);
    } catch (error) {
      console.error("Error fetching line data:", error);
    }
  }

    const getSelectedLine = async (Id) => {
      try {
        const response = await fetch(SERVER_URL + "/api/linia/" + Id, { method: "GET", credentials: "include" });
        if (!response.ok) {
          throw new Error("error on get 2");
        }
        const data = await response.json();
        setSelectedLine(Array.isArray(data) ? data : [data]);
      } catch (error) {
        console.error("Error fetching line data:", error);
      }
    }

  const fetchHarmonogramData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/harmonogram", { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("Error fetching harmonogram data");
      }
      const data = await response.json();
      setHarmonogramData(data);
    } catch (error) {
      console.error("Error fetching harmonogram data:", error);
    }
  };

  useEffect(() => {
    getLineData();
    fetchHarmonogramData();
  }, []);

  const handleLineChange = (event) => {
    setSelectedLine(event.target.value);
  }

  const formatTime = (timeString) => {
    return timeString.slice(0, 5);
  }

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };


  const handleChangeLine = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    if (value != ""){
      getSelectedLine(value);
    }else{
      setSelectedLine([]);
    }
  };

  const isLineSelected = () => {
    if(selectedLine.length > 0){
        return selectedLine[0].odwracalna;
    }
    return false;
  }

  return (
    <div className="pt-40">
      <div className="listDiv">
        <table className="addFormat">
            <tbody>
              <tr>
                <td className="addWhat">Linia:</td>
                <td>
                  <select className="dropDownPrzystanek" name="liniaId" value={formData.liniaId} onChange={handleChangeLine} required>
                    <option value="">Wybierz linię</option>
                    {lineData.map((line) => (
                      <option key={line.id} value={line.id}>{line.numer}</option>
                    ))}
                  </select>
                </td>
              </tr>
              <tr>
                <td className="addWhat">Harmonogram:</td>
                <td>
                  <select className="dropDownPrzystanek" name="harmonogramId" value={formData.harmonogramId} onChange={handleChange} required>
                    <option value="-1">Wybierz harmonogram</option>
                    {harmonogramData.map((harmonogram) => (
                      <option key={harmonogram.harmonogramId} value={harmonogram.harmonogramId}>{harmonogram.nazwa}</option>
                    ))}
                  </select>
                </td>
              </tr>
              <tr>
                <td className="addWhat">Typ autobusu:</td>
                <td>
                  <select className="dropDownPrzystanek" name="typ_autobusu" value={formData.typ_autobusu} onChange={handleChange} required>
                    <option value="-1">Wybierz typ autobusu</option>
                    <option value="0">Normalny</option>
                    <option value="1">Niskopodłogowy</option>
                  </select>
                </td>
              </tr>
              {isLineSelected() && (
                <tr>
                    <td className="addWhat">Kierunek</td>
                    <td>
                        <select className="dropDownPrzystanek"
                            name="kierunek"
                            onChange={handleChange} required>
                            <option value="0">Normalny</option>
                            <option value="1">Odwrotny</option>
                        </select>
                    </td>
                </tr>
              )}
            </tbody>
          </table>
      </div>

      <div className="listDiv">
        {selectedLine.length > 0 && (
            <table className="tableFormat">
                <thead>
                    <tr>
                        <th>Nazwa przystanku</th>
                        <th>Godzina</th>
                    </tr>
                </thead>
                <tbody>
                    {selectedLine.map((course) =>
                        course.przystanki.map((stop) => (
                            <tr key={stop.przystanekId}>
                                <td>{stop.nazwa}</td>
                                <td>
                                    <input
                                        className="addInput"
                                        type="text"
                                        name="godzinna"
                                        onChange={handleChange}
                                        required
                                    />
                                </td>
                            </tr>
                        ))
                    )}
                </tbody>
            </table>
        )}

        <button className="infoBtn" type="submit">Dodaj linię</button>
        <Link className="infoBtn" to={`/course`}>Powrót</Link>
      </div>
    </div>
  )
}

export default CourseAdd;
