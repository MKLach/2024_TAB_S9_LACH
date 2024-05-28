import React, { useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";

const BusAdd = () => {
  const [formData, setFormData] = useState({
    numerRejestracyjny: "",
    przegladWaznyDo: "",
    status: "",
    przebieg: "",
  });

  const [showInfo, setShowInfo] = useState(false);

  const [savedMessage, setSavedMessage] = useState("");


  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(SERVER_URL + "/api/autobus/save", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        credentials: "include",
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error("Failed to add driver");
      }

      setTimeout(() => {
        window.location.href = '/bus';
      }, 100);

      setSavedMessage("Dodano autobus");

    } catch (error) {
      setSavedMessage("Nie udało się dodać kierowcy");
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    const modifiedValue = name === 'przegladWaznyDo' ? `${value.split('T')[0]}T00:00` : value;
    setFormData({ ...formData, [name]: modifiedValue });
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
                <td className="addWhat">Numer rejestracyjny:</td>
                <td>
                  <input className="addInput"
                    type="text"
                    name="numerRejestracyjny"
                    value={formData.numerRejestracyjny}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Przegląd ważny do:</td>
                <td>
                  <input className="addInput"
                    type="datetime-local"
                    name="przegladWaznyDo"
                    value={formData.przegladWaznyDo}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Czy jest sprawny:</td>
                <td>
                    <input
                        className="checkBox"
                        type="checkbox"
                        name="status"
                        checked={formData.status === "OK"}
                        onChange={(e) => {
                            const value = e.target.checked ? "OK" : "uszkodzony";
                            handleChange({ target: { name: "status", value }});
                        }}
                    />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Przebieg:</td>
                <td>
                  <input className="addInput"
                    type="number"
                    name="przebieg"
                    value={formData.przebieg}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <button className="infoBtn" type="submit">Dodaj autobus</button>
        </form>
      </div>
      <Link className="infoBtn" to={`/bus`}>Powrót</Link>
    </div>
  );
};

export default BusAdd;
