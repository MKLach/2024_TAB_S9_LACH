import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";


const PlannedCourseAdd = () => {
  const [formData, setFormData] = useState({
    kursId: "",
    kierowcaId: "",
    autobusId: "",
    dataPrzejazdu: "",
  });

  const [coursesData, setCouresData] = useState([]);
  const [driverData, setDriverData] = useState([]);
  const [busData, setBusData] = useState([]);
  const [savedMessage, setSavedMessage] = useState("");
  
  const [coursesDataDates, setCouresDataDates] = useState([
	  "dd.MM.yyyy"
  ]);

//zmienić API
  const handleSubmit = async (e) => {
	  
	
    try {
      const response = await fetch(SERVER_URL + "/api/przejazd/save", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json' 
        },
        body: JSON.stringify(formData),
        credentials: "include"
        
      });

      if (!response.ok) {
        throw new Error("Failed to add planned course");
      }

      setTimeout(() => {
        window.location.href = '/planned_course';
      }, 10000);

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
  const getDates = async() => {
	  try {
      const response = await fetch(SERVER_URL + "/api/przejazd/allDates?kurs_id=" + formData.kursId, { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("error on get 2");
      }
      const data = await response.json();
      
      setCouresDataDates(data);
    } catch (error) {
      console.error(error);
    }
	  
	  
  }

  const getDriverData = async () => {
    try {
      const response = await fetch(
		  SERVER_URL + "/api/przejazd/allDrivers?kurs_id=" + formData.kursId + "&data="+formData.dataPrzejazdu, 
      
      { method: "GET", credentials: "include" });
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
      const response = await fetch(
		  SERVER_URL + "/api/przejazd/allBuses?kurs_id=" + formData.kursId + "&data="+formData.dataPrzejazdu, 
      
		   { method: "GET", credentials: "include" });
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
    setFormData({ ...formData, [name]: parseInt(value) });
    
  };
  
  const handleChangeData = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  useEffect(() => {
    getCourseData();
    getBusData();
  }, []);
  
  
  useEffect(() => {
	  formData.dataPrzejazdu = null
	  formData.autobusId = null
	  formData.kierowcaId = null
	  getDates();
	  if(formData.kursId){
		document.getElementById('date_sel').selectedIndex = 0;
		if(formData.dataPrzejazdu){
			document.getElementById('drv_sel').selectedIndex = 0;
	  		document.getElementById('bus_sel').selectedIndex = 0;
	  
		}
	  
	  }
	 
  }, [formData.kursId])
  
  useEffect(() => {
	  getDriverData();
	  getBusData();
  }, [formData.dataPrzejazdu])

  return (
    <div className="pt-40">
      <p>{savedMessage}</p>
      <h1>Zaplanuj Przejazd</h1>
      <div className="addForm">
        <form className="addForm" onSubmit={handleSubmit}>
          <table className="addFormat">
            <tbody>
              <tr>
                <td className="addWhat">Kurs: </td>
                <td>
                  <select
                    name="kursId"
                    className="dropDownCourse"
                    value={formData.kursId}
                    onChange={handleChange}
                    required
                  >
                    <option key = "-1" value="">Wybierz kurs</option>
                    {Array.isArray(coursesData) && coursesData.map((course) => (
                      <option key={course.kursId} value={course.kursId}>
                        {
							
							course.kierunek ? course.linia.numer + " " + course.przystanki[course.przystanki.length-1].godzinna + " "
							+ " ODW" : course.linia.numer + " " + course.przystanki[0].godzinna + " "
							
							
							
						}
                      </option>
                    ))}
                  </select>
                </td>
              </tr>
              
              
             
             { formData.kursId && coursesDataDates.length > 0 &&
              <tr>
               {/* <td className="addWhat">Data przejazdu : </td>
                <td>
                  <input
                    className="addInput"
                    type="date"
                    name="data_przejazdu"
                    value={formData.data_przejazdu}
                    onChange={handleChange}
                    required
                  />
                </td>
               */ }
                
                <td className="addWhat">Data przejazdu:</td>
                <td>
                  <select
                    id = "date_sel"
                    name="dataPrzejazdu"
                    className="dropDownCourse"
                    defaultValue={formData.dataPrzejazdu}
                    value={formData.dataPrzejazdu}
                    onChange={handleChangeData}
                    required
                  >
                    <option key = "-1" value="">Wybierz Datę</option>
                    {Array.isArray(coursesDataDates) && coursesDataDates.map((courseDate) => (
                      <option key={courseDate} value={courseDate}>
                        {courseDate}
                      </option>
                    ))}
                  </select>
                </td>
                
                
              </tr>
              
              }
              
              { formData.kursId && formData.dataPrzejazdu &&
              <tr>
                <td className="addWhat">Kierowca:</td>
                <td>
                  <select
                    id = "drv_sel"
                    name="kierowcaId"
                    className="dropDownCourse"
                    value={formData.kierowcaId}
                    onChange={handleChange}
                    required
                  >
                    <option key = "-1" value="">Wybierz kierowcę</option>
                    {Array.isArray(driverData) && driverData.map((driver) => (
                      <option key={driver.kierowcaId} value={driver.kierowcaId}>
                        {driver.kierowcaId} - {driver.imie} {driver.nazwisko}
                      </option>
                    ))}
                  </select>
                </td>
              </tr>
              }
              
              { formData.kursId && formData.dataPrzejazdu &&
              <tr>
                <td className="addWhat">Autobus:</td>
                <td>
                  <select
                    id = "bus_sel"
                    name="autobusId"
                    className="dropDownCourse"
                    value={formData.autobusId}
                    onChange={handleChange}
                    required
                  >
                    <option key = "-1" value="">Wybierz autobus</option>
                    {Array.isArray(busData) && busData.map((bus) => (
                      <option key={bus.autbousId} value={bus.autbousId}>
                        {bus.autbousId} - {bus.numerRejestracyjny}
                      </option>
                    ))}
                  </select>
                </td>
              </tr>
              }
            </tbody>
          </table>
          <button className="infoBtn" type="submit">Dodaj kurs</button>
        </form>
      </div>
      <Link className="infoBtn"  to={`/planned_course`} >Powrót</Link>
    </div>
  );
};

export default PlannedCourseAdd;
