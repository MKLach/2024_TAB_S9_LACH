import React, { useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";

const PlannedCourseAdd = () => {
  const [formData, setFormData] = useState({
    prawoJazdyWazneDo: "",
    imie: "",
    nazwisko: "",
    pesel: "",
  });

  const [savedMessage, setSavedMessage] = useState("");

  const handleSubmit = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/kierowca/save", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error("Failed to add driver");
      }

      setTimeout(() => {
        window.location.href = '/driver';
      }, 100);

      setSavedMessage("Dodano kierowcę");

    } catch (error) {
      setSavedMessage("Nie udało się dodać kierowcy");
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    const modifiedValue = name === 'prawoJazdyWazneDo' ? `${value.split('T')[0]}T00:00` : value;
    setFormData({ ...formData, [name]: modifiedValue });
  };

  return (
    <div className="pt-40">
      <p>{savedMessage}</p>
      <p>dodawanie kursów</p>
      <div className="addForm">
        <form className="addForm" onSubmit={handleSubmit}>
          <table className="addFormat">
            <tbody>
              <tr>
                <td className="addWhat">Imię:</td>
                <td>
                  <input className="addInput"
                    type="text"
                    name="imie"
                    value={formData.imie}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Nazwisko:</td>
                <td>
                  <input className="addInput"
                    type="text"
                    name="nazwisko"
                    value={formData.nazwisko}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">PESEL:</td>
                <td>
                  <input className="addInput"
                    type="text"
                    name="pesel"
                    value={formData.pesel}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Prawo jazdy ważne do:</td>
                <td>
                    <input className="addInput"
                        type="datetime-local"
                        name="prawoJazdyWazneDo"
                        value={formData.prawoJazdyWazneDo}
                        onChange={handleChange}
                        required
                    />
                </td>
              </tr>
            </tbody>
          </table>
          <button className="infoBtn" type="submit">Dodaj kierowcę</button>
        </form>
      </div>
      <Link className="infoBtn" to={`/driver`}>Powrót</Link>
    </div>
  );
};

export default PlannedCourseAdd;
