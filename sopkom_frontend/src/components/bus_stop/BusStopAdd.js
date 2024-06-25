import React, { useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link, useNavigate } from "react-router-dom";

const BusStopAdd = () => {
  const [formData, setFormData] = useState({
    nazwa: "",
    kodPocztowy: "",
    miasto: "",
    ulica: "",
    dlugoscGeograficzna: "",
    szerokoscGeograficzna: "",
  });

  const [showInfo, setShowInfo] = useState(false);

  const [savedMessage, setSavedMessage] = useState("");
  const history = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(SERVER_URL + "/api/przystanek/save", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData),
        credentials: "include"
      });

      if (!response.ok) {
        throw new Error("Failed to add bus stop");
      }

      setTimeout(() => {
        history('/bus_stop');
      }, 100);

      setSavedMessage("Dodano przystanek");

    } catch (error) {
      setSavedMessage("Nie udało się dodać przystanku");
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleInfoButtonClick = () => {
    setShowInfo(true);
  };

  return (
    <div className="pt-40">
      <p>{savedMessage}</p>
      <div className="addForm">
        <form className="addForm" onSubmit={handleSubmit}>
          <table className="addFormat">
            <tbody>
              <tr>
                <td className="addWhat">Nazwa:</td>
                <td>
                  <input className="addInput"
                    type="text"
                    name="nazwa"
                    value={formData.nazwa}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Miasto:</td>
                <td>
                  <input className="addInput"
                    type="text"
                    name="miasto"
                    value={formData.miasto}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Ulica:</td>
                <td>
                  <input className="addInput"
                    type="text"
                    name="ulica"
                    value={formData.ulica}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Kod pocztowy:</td>
                <td>
                  <input className="addInput"
                    type="text"
                    name="kodPocztowy"
                    value={formData.kodPocztowy}
                    onChange={handleChange}
                    required
                    maxLength={6}
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Długość geograficzna:</td>
                <td>
                  <input className="addInput"
                    type="number"
                    name="dlugoscGeograficzna"
                    value={formData.dlugoscGeograficzna}
                    onChange={handleChange}
                    required
                    step="0.0001"
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Szerokość geograficzna:</td>
                <td>
                  <input className="addInput"
                    type="number"
                    name="szerokoscGeograficzna"
                    value={formData.szerokoscGeograficzna}
                    onChange={handleChange}
                    required
                    step="0.0001"
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <button className="infoBtn" type="submit">Dodaj przystanek</button>
        </form>
      </div>
      <Link className="infoBtn" to={`/bus_stop`}>Powrót</Link>
    </div>
  );
};

export default BusStopAdd;
