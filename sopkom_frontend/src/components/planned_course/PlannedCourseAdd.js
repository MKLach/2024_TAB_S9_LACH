import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";

const PlannedCourseAdd = () => {
  const [formData, setFormData] = useState({
    kurs_id: "",
    kierowcaid: "",
    autobusid: "",
    data_przejazdu: "",
  });

  const [coursesData, setCouresData] = useState([]);
  const [driverData, setDriverData] = useState([]);
  const [busData, setBusData] = useState([]);
  const [savedMessage, setSavedMessage] = useState("");

//zmienić API
  const handleSubmit = async (e) => {
    try {
      const response = await fetch(SERVER_URL + "/api/nic/save", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error("Failed to add planned course");
      }

      setTimeout(() => {
        window.location.href = '/planned_course';
      }, 100);

      setSavedMessage("Dodano kurs");

    } catch (error) {
      setSavedMessage("Nie udało się dodać kursu");
    }
  };

  const getCourseData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/kurs/", { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("error on get 2");
      }
      const data = await response.json();
      setCouresData(data);
    } catch (error) {
      console.error(error);
    }
  }

  const getDriverData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/kierowca/", { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("error on get 2");
      }
      const data = await response.json();
      setDriverData(data);
    } catch (error) {
      console.error(error);
    }
  }

  const getBusData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/autobus/", { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("error on get 2");
      }
      const data = await response.json();
      setBusData(data);
    } catch (error) {
      console.error(error);
    }
  }

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  useEffect(() => {
    getCourseData();
    getDriverData();
    getBusData();
  }, []);

  return (
    <div className="pt-40">
      <p>{savedMessage}</p>
      <h1>Zaplanuj kurs</h1>
      <div className="addForm">
        <form className="addForm" onSubmit={handleSubmit}>
          <table className="addFormat">
            <tbody>
              <tr>
                <td className="addWhat">Kurs: </td>
                <td>
                  <select
                    name="kurs_id"
                    className="dropDownCourse"
                    value={formData.kurs_id}
                    onChange={handleChange}
                  >
                    <option value="">Wybierz kurs</option>
                    {Array.isArray(coursesData) && coursesData.map((course) => (
                      <option key={course.kursId} value={course.kursId}>
                        {course.linia.numer}
                      </option>
                    ))}
                  </select>
                </td>
              </tr>
              <tr>
                <td className="addWhat">Data przejazdu : </td>
                <td>
                  <input
                    className="addInput"
                    type="datetime-local"
                    name="data_przejazdu"
                    value={formData.data_przejazdu}
                    onChange={handleChange}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td className="addWhat">Kierowca:</td>
                <td>
                  <select
                    name="kierowcaid"
                    className="dropDownCourse"
                    value={formData.kierowcaid}
                    onChange={handleChange}
                  >
                    <option value="">Wybierz kierowcę</option>
                    {Array.isArray(driverData) && driverData.map((driver) => (
                      <option key={driver.kierowcaId} value={driver.kierowcaId}>
                        {driver.kierowcaId} - {driver.imie} {driver.nazwisko}
                      </option>
                    ))}
                  </select>
                </td>
              </tr>
              <tr>
                <td className="addWhat">Autobus:</td>
                <td>
                  <select
                    name="autobusid"
                    className="dropDownCourse"
                    value={formData.autobusid}
                    onChange={handleChange}
                  >
                    <option value="">Wybierz autobus</option>
                    {Array.isArray(busData) && busData.map((bus) => (
                      <option key={bus.autbousId} value={bus.autbousId}>
                        {bus.autbousId} - {bus.numerRejestracyjny}
                      </option>
                    ))}
                  </select>
                </td>
              </tr>
            </tbody>
          </table>
          <button className="infoBtn" type="submit">Dodaj kurs</button>
        </form>
      </div>
      <Link className="infoBtn" to={`/planned_course`}>Powrót</Link>
    </div>
  );
};

export default PlannedCourseAdd;
