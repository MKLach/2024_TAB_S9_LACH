import React, { useState, useEffect } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";

function extractLastPathComponent(url) {
    let index = 0;
    let result = "";
    for (let i = url.length - 1; i > 0; i--) {
        if (url[i] === '/') {
            index = i+1;
            break;
        }
    }
    for (let i = index; i < url.length; i++) {
        result += url[i];
    }
    return result;
}

const BusLineEdit = () => {
  const [formData, setFormData] = useState({
    miasta: []});

  const [LineData, setLineData] = useState([]);
  const [showInfo, setShowInfo] = useState(false);
  const [savedMessage, setSavedMessage] = useState("");
  const [busStops, setBusStops] = useState([]);
  const listId = extractLastPathComponent(window.location.pathname);


  useEffect(() => {
    getLineData();
    fetchBusStops();
    handleAddNewStop();
    handleAddNewStop();
    handleAddNewStop();
  }, []);

	const getLineData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/linia/" + listId, {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setLineData(data);

		} catch (error){


		}
	}

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
        window.location.href = '/bus_stop';
      }, 100);

      setSavedMessage("Dodano przystanek");

    } catch (error) {
      setSavedMessage("Nie udało się dodać linii! " + error.message);
    }
  };

  const handleChange = (e, index) => {
    const { value } = e.target;
    const newMiasta = [...formData.miasta];
    newMiasta[index] = value;
    setFormData({ ...formData, miasta: newMiasta });
  };

  const handleAddNewStop = () => {
    setFormData(prevState => ({
      ...prevState,
      miasta: [...prevState.miasta, ""]
    }));
  };

  const handleRemoveStop = (index) => {
    if (formData.miasta.length <= 3) {
      return;
    }
    const newMiasta = [...formData.miasta];
    newMiasta.splice(index, 1);
    setFormData({ ...formData, miasta: newMiasta });
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
                        <input type="text"
                            name="nazwa"
                            className="addInput"
                            value={LineData.numer}
                               onChange={handleChange}
                               required/>
                    </th>
                </tr>
                <tr>
                    <th className="addWhat"><label htmlFor="odwrotna">Odwrotna trasa:</label></th>
                    <th>
                        <input className="checkBox"
                            type="checkbox"
                            id="odwrotna"
                            name="odwrotna"
                            checked={formData.odwrotna}
                            onChange={() => setFormData({...formData, odwrotna: !formData.odwrotna})}/>
                    </th>
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
              {formData.miasta.map((miasto, index) => (
                <tr key={index}>
                  <td className="addWhat">Przystanek {index + 1}:</td>
                  <td>
                    <select
                      className="dropDownPrzystanek"
                      value={miasto}
                      onChange={(e) => handleChange(e, index)}
                      required>
                      <option value="">Wybierz przystanek</option>
                      {busStops.map(stop => (
                        <option key={stop.przystanekId} value={stop.przystanekId}>
                          {`${stop.przystanekId} - ${stop.miasto}, ${stop.ulica}`}
                        </option>
                      ))}
                    </select>
                  </td>
                  <td>
                    {(formData.miasta.length > 3) && (
                      <button className="infoBtn" type="button" onClick={() => handleRemoveStop(index)}>Usuń</button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <button className="infoBtn" type="button" onClick={handleAddNewStop}>Dodaj kolejny przystanek</button>
          <button className="infoBtn" type="submit">Aktualizuj linię</button>
        </form>
      </div>
      <Link className="infoBtn" to={`/bus_line`}>Powrót</Link>
    </div>
  );
};

export default BusLineEdit;
