import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";

const CourseAdd = () => {
  const [lineData, setLineData] = useState([]);
  const [selectedLine, setSelectedLine] = useState([]);
  const [harmonogramData, setHarmonogramData] = useState([]);
  const [stopsData, setStopsData] = useState([]);

  const [savedMessage, setSavedMessage] = useState("");

  const [formData, setFormData] = useState({
    liniaId: 0,
    kierunek: 0,
    harmonogramId: 0,
    typ_autobusu: 0,
    //przystanki: [{}]
    przystanki: [{ przystanekWLini: {nazwa: "", przystanekId: "", kolejnosc: 0 }, godzinna: "" }]
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
      formData.liniaId = Id;
      formData.kierunek = 0;
      formData.przystanki = [];
      
      for (let i = 0; i < data.przystanki.length; i++){
		  
		  console.log(data.przystanki[i].przystanekId);
		  formData.przystanki.push({przystanekWLini: {nazwa: data.przystanki[i].nazwa, przystanekwliniId: data.przystanki[i].przystanekwliniId, przystanekId: data.przystanki[i].przystanekId, godzinna:"00:00", kolejnosc: data.przystanki[i].kolejnosc }});
	  }
	  setFormData(formData);
      
      console.log(JSON.stringify(formData));
      
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
  
  const handleChangeKierunek = (e) => {
    const { name, value } = e.target;
    
    /*formData.przystanki.sort((a,b) => {
		if(formData.kierunek == 1){
			
			return a.przystanekWLini.kolejnosc - b.przystanekWLini.kolejnosc;
		} else {
			
			return b.przystanekWLini.kolejnosc - a.przystanekWLini.kolejnosc;
		}
	})*/
    
    setFormData({ ...formData, [name]: parseInt(value) });
    
   // let array = formData.przystanki.reverse();
    console.log(JSON.stringify(formData));
   // formData.przystanki = [];
  //  setFormData(formData);
    
    
  //  formData.przystanki = array;
    
  };

  const handleChangeLine = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    if (value !== ""){
      getSelectedLine(value);
    }else{
      setSelectedLine([]);
    }
  };

  const handleChangePwL = (przystanekWLini, e, index) => {
    console.log(e.target.value);
    //console.log(index);

    formData.przystanki[index].godzinna=e.target.value+":00";
    console.log(formData);
    setFormData(formData);
  };
	
  const handleSubmit = async (e) => {
    e.preventDefault();
    
    
    try {
		console.log(JSON.stringify(formData));
      const response = await fetch(SERVER_URL + "/api/kurs/save", {
        method: "POST",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        throw new Error("Error saving course");
      }
       setSavedMessage("Dodano!");
  
      setTimeout(() => {
        window.location.href = '/course/?linia_id='+formData.liniaId;
      }, 100);
     
       
      // Handle successful response
    } catch (error) {
      console.error("Error saving course:", error);
      setSavedMessage("error");
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
     <p>{savedMessage}</p>
      <form onSubmit={handleSubmit}>
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
                    <option value="">Wybierz harmonogram</option>
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
                    <option value="0">Normalny</option>
                    <option value="1">Niskopodłogowy</option>
                  </select>
                </td>
              </tr>
              {isLineSelected() && (
                <tr>
                  <td className="addWhat">Kierunek</td>
                  <td>
                    <select className="dropDownPrzystanek" name="kierunek" onChange={handleChangeKierunek} required>
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
          {formData.przystanki.length > 1 && (
            <table className="tableFormat">
              <thead>
                <tr>
                  <th>Nazwa przystanku</th>
                  <th>Godzina</th>
                </tr>
              </thead>
              <tbody>
                {formData.przystanki.sort((a,b) => {
						if(formData.kierunek == 0){
							
							return a.przystanekWLini.kolejnosc - b.przystanekWLini.kolejnosc;
						} else {
							
							return b.przystanekWLini.kolejnosc - a.przystanekWLini.kolejnosc;
						}
					}).map((course, index) =>
                  //course.map((przystanekWLini, index) => (
                    <tr key={course.przystanekWLini.przystanekId}>
                      <td>{course.przystanekWLini.nazwa}</td>
                      <td>
                        <input
                          className="addInput"
                          type="time"
                          name="godzinna"
                          onChange={(e) => handleChangePwL(course.przystanekWLini, e, index)}
                          required
                        />
                      </td>
                    </tr>
                  //))
                )}
              </tbody>
            </table>
          )}

          <button className="infoBtn" type="submit">Dodaj Kurs</button>
         
          <Link className="infoBtn" to={`/course`}>Powrót</Link>
        </div>
      </form>
    </div>
  );
}

export default CourseAdd;