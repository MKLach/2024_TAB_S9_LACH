import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { useSearchParams, useNavigate  } from "react-router-dom";
import { Link } from "react-router-dom";

const PlannedCourseAdd_with_data = () => {
  const [formData, setFormData] = useState({
    kursId: "",
    kierowcaId: "",
    autobusId: "",
    dataPrzejazdu: "",
  });

  const [course, setCourse] = useState(null);
  const [driverData, setDriverData] = useState([]);
  const [busData, setBusData] = useState([]);
  const [savedMessage, setSavedMessage] = useState("");
  const [searchParams, setSearchParams] = useSearchParams();
 
  
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

      
      setSavedMessage("Dodano kurs");

	  setTimeout(() => {
        window.location.href = '/planned_course';
      }, 100);

    } catch (error) {
      setSavedMessage("Nie udało się dodać kursu");
    }
  };

  const getCourseData = async (kurs_id) => {
    try {
      const response = await fetch(SERVER_URL + "/api/kurs/" + kurs_id, { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("error on get kurs");
      }
      const data = await response.json();
      setCourse(data);
    } catch (error) {
      console.error("kurs err: " + error);
      
      
    }
  }
  

  const getDriverData = async () => {
	  
    if(!formData.dataPrzejazdu){
	  return;
    }  
	  
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
	if(!formData.dataPrzejazdu){
	  return;
    }  
	  
	  
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
    JSON.stringify(formData)
  };
  
  const handleChangeData = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  useEffect(() => {
	  formData.dataPrzejazdu = null
	  formData.autobusId = null
	  formData.kierowcaId = null
	  
	  console.log(searchParams.get("data"));
	  console.log(searchParams.get("kurs_id"));
			
	  if(!searchParams.get("data") || !searchParams.get("kurs_id")){
				
		//console.log(searchParams.get("data"));
		setTimeout(() => {
        	//window.location.href = '/planned_course_to_plan';
      	}, 100);
				
	  } else{
		  
		  getCourseData(searchParams.get("kurs_id"));
		  formData.kursId = searchParams.get("kurs_id");
		  formData.dataPrzejazdu = searchParams.get("data")
	  }
	  
	 
  }, [])
  
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
            	 <td className="addWhat"> Kurs:</td>
              { course && 
              	
              	<td> {course.kierunek ? course.linia.numer + " " + course.przystanki[course.przystanki.length-1].godzinna + " "
							+ " ODW" : course.linia.numer + " " + course.przystanki[0].godzinna + " "}</td>
               
               
              }
              
             </tr>
             
             { course &&
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
                
                <td className="addWhat">Data przejazdu: </td>
                <td>
                  {searchParams.get("data")}
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
 					defaultValue={""}
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
                    defaultValue={""}
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
          <button className="infoBtn" type="submit">Zaplanuj</button>
        </form>
      </div>
      <Link className="infoBtn"  to={`/planned_course`} >Powrót</Link>
    </div>
  );
};

export default PlannedCourseAdd_with_data;
