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
    numer: '',
    odwrotna: false,
    przystanki: [{ przystanekId: '', kolejnosc: '' }]
    
  });

  const [LineData, setLineData] = useState([]);
  const [showInfo, setShowInfo] = useState(false);
  const [savedMessage, setSavedMessage] = useState("");
  const [busStops, setBusStops] = useState([]);
  const listId = extractLastPathComponent(window.location.pathname);


  useEffect(() => {
    getLineData();
    fetchBusStops();
  }, []);

	const getLineData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/linia/" + listId, {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			
			//setLineData(data);
			setFormData(data);
			//for(let i = 0; i < data.przystanki.length; i++){
				
			//	handleAddNewStop();
			//}
	

		} catch (error){


		}
	}
	
  const deleteBusLine = async () => {
        try {
            const response = await fetch(SERVER_URL + "/api/linia/" + listId, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            if (!response.ok) {
                throw new Error("Failed to delete driver");
            }
            setTimeout(() => {
                window.location.href = '/bus_line';
            }, 100);

        } catch (error) {
            let errorMessage = error.message;
            if (error.message.includes(errorMessage)) {
                setSavedMessage("Nie można usunąć przystanku - występuje on w linii");
            } else {
                setSavedMessage("Nie udało się usunąć przystanku.");
            }
        }
    };

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
      const response = await fetch(SERVER_URL + "/api/linia/" + listId, {
        method: 'PATCH',
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

      setSavedMessage("Zaktualizowano Linię");

    } catch (error) {
      setSavedMessage("Nie udało się dodać linii! " + error.message);
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
  
   const handleAddNewStopWithData = (kolejnosc, val ) => {
    setFormData(prevState => ({
      ...prevState,
      przystanki: [...prevState.przystanki, { przystanekId: 'val', kolejnosc }]
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
               {/*  <th className="addWhat">
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
                      {busStops.map(stop => (
                        <option key={stop.przystanekId} value={stop.przystanekId}>
                          {`${stop.nazwa}, ${stop.miasto}, ${stop.ulica}`}
                        </option>
                      ))}
                    </select>
                  </td>
                  <td>
                    {(formData.przystanki.length > 2 && formData.przystanki.length-1 === index) && (
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
        <div>
            <button className="infoBtn" onClick={deleteBusLine}>Usuń linię</button>
        </div>
      <Link className="infoBtn" to={`/bus_line/info/${listId}`}>Powrót</Link>
    </div>
  );
};

export default BusLineEdit;
