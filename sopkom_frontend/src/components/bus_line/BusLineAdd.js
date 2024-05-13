import React, { useState, useEffect } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";

const BusLineAdd = () => {
  const [formData, setFormData] = useState({
    numer: '',
    odwrotna: false,
    przystanki: [{ przystanekId: '', kolejnosc: 1 }]
  });

  const [showInfo, setShowInfo] = useState(false);
  const [savedMessage, setSavedMessage] = useState("");
  const [busStops, setBusStops] = useState([]);

  useEffect(() => {
    fetchBusStops();
    handleAddNewStop();
  }, []);

  const fetchBusStops = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/przystanek");
      if (!response.ok) {
        throw new Error("Failed to fetch bus stops");
      }
      const data = await response.json();
      setBusStops(data);
    } catch (error) {
      console.error("Error fetching bus stops:", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(SERVER_URL + "/api/linia/save", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error("Failed to add bus line");
      }

      setTimeout(() => {
        window.location.href = '/bus_line';
      }, 100);

      setSavedMessage("Dodano przystanek");

    } catch (error) {
      setSavedMessage("Nie udało się dodać linii! "+error.message);
    }
  };

  const handleChange = (e, index) => {
    const { name, value } = e.target;
    if (name === 'numer' || name === 'odwrotna') {
      setFormData(prevState => ({
        ...prevState,
        [name]: value
      }));
    } else {
      const newPrzystanki = [...formData.przystanki];
      newPrzystanki[index][name] = value;
      setFormData(prevState => ({
        ...prevState,
        przystanki: newPrzystanki
      }));
    }
  };

  const handleAddNewStop = () => {
    const kolejnosc = formData.przystanki.length + 1;
    setFormData(prevState => ({
      ...prevState,
      przystanki: [...prevState.przystanki, { przystanekId: '', kolejnosc }]
    }));
  };

  const handleRemoveStop = (index) => {
    if (formData.przystanki.length <= 2) {
      return;
    }
    const newPrzystanki = [...formData.przystanki];
    newPrzystanki.splice(index, 1);
    setFormData({ ...formData, przystanki: newPrzystanki });
  };

  return (
    <div className="pt-40">
      <p>{savedMessage}</p>
      <div className="addForm">
        <form className="addForm" onSubmit={handleSubmit}>
          <table className="addFormat">
            <thead>
              <tr>
                <th className="addWhat">Numer Linii:</th>
                <th>
                  <input
                    type="text"
                    name="numer"
                    className="addInput"
                    value={formData.numer}
                    onChange={handleChange}
                    required
                  />
                </th>
              </tr>
              <tr>
               {/* <th className="addWhat">
                  <label htmlFor="odwrotna">Odwrotna trasa:</label>
                </th>
                <th>
                  <input
                    className="checkBox"
                    type="checkbox"
                    id="odwrotna"
                    name="odwrotna"
                    checked={formData.odwrotna}
                    onChange={handleChange}
                  />
                </th>*/}
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
          <table className="addFormat">
            <thead>
              <tr>
                <th className="addWhat">Kolejność jazdy</th>
                <th>Przystanek</th>
              </tr>
            </thead>
            <tbody>
              {formData.przystanki.map((przystanek, index) => (
                <tr key={index}>
                  <td className="addWhat">Przystanek {przystanek.kolejnosc}:</td>
                  <td>
                    <select
                      className="dropDownPrzystanek"
                      value={przystanek.przystanekId}
                      onChange={(e) => handleChange(e, index)}
                      name="przystanekId"
                      required
                    >
                      <option value="">Wybierz przystanek</option>
                      { busStops.map(stop => (
                        <option key={stop.przystanekId} value={stop.przystanekId}>
                          {`${stop.nazwa}, ${stop.miasto}, ${stop.ulica}`}
                        </option>
                      ))}
                    </select>
                  </td>
                  <td>
                    {(formData.przystanki.length > 2) && (
                      <button className="infoBtn" type="button" onClick={() => handleRemoveStop(index)}>Usuń</button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <button className="infoBtn" type="button" onClick={handleAddNewStop}>Dodaj kolejny przystanek</button>
          <button className="infoBtn" type="submit">Dodaj linię</button>
        </form>
      </div>
      <Link className="infoBtn" to={`/bus_line`}>Powrót</Link>
    </div>
  );
};

export default BusLineAdd;