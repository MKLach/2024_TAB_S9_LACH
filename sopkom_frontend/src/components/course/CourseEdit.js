import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link, useSearchParams } from "react-router-dom";

const CourseEdit = () => {
  const [courseData, setCourseData] = useState([]);
  const [harmonogramData, setHarmonogramData] = useState([]);
  const [searchParams, setSearchParams] = useSearchParams();

  const [savedMessage, setSavedMessage] = useState("");

  const [formData, setFormData] = useState({
	kursId: 0, 
    liniaId: 0,
    kierunek: 0,
    harmonogramId: 0,
    typ_autobusu: 0,
    linia: {
		numer: "",
		id: 0
	},
    //przystanki: [{}]
    przystanki: [{ przystanekWLini: {nazwa: "", przystanekId: "", kolejnosc: 0, przystanekwKursieId: 0 }, godzinna: "" }]
  });

  const getCourseData = async () => {
	if(!searchParams.get("kurs_id")){
		
		setSavedMessage("brak wybranego kursu!");
		
	}
	  
    try {
      const response = await fetch(SERVER_URL + "/api/kurs/" + searchParams.get("kurs_id"), { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("error on get 2");
      }
      const data = await response.json();
      setCourseData(data);
      data.liniaId = data.linia.id;
      setFormData(data);
      
      
    } catch (error) {
      console.error("Error fetching line data:", error);
      setSavedMessage("error on fetch");
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
    //getLineData();
    getCourseData();
    fetchHarmonogramData();
  }, []);

 
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
      const response = await fetch(SERVER_URL + "/api/kurs/" + formData.kursId, {
        method: "PATCH",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
		setSavedMessage("Error!");
        throw new Error("Error patchin course");
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
  
  const handleDeleteClick = async ()  => {
	
	if(window.confirm("Czy napewno chcesz usunąć ten kurs? (nie można cofnąć!)")){
		 try {
            const response = await fetch(SERVER_URL + "/api/kurs/" + formData.kursId, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            if (!response.ok) {
                throw new Error("Failed to delete course");
            }
            setTimeout(() => {
                window.location.href = "/course?linia_id=" + formData.liniaId;
            }, 100);
 			setSavedMessage("Udało się usunąć kurs.");
        } catch (error) {
            let errorMessage = error.message;
            if (error.message.includes(errorMessage)) {
                setSavedMessage("Nie można usunąć kurs - występuje on w przejezdzie");
            } else {
                setSavedMessage("Nie udało się usunąć kursu.");
            }
        }
		
	}
	  
	  
  }

  const isCourseSelected = () => {
    if(formData.przystanki.length > 0){
		
      return formData.odwracalny;
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
                  <select disabled={true} className="dropDownPrzystanek" name="linia.id" value={formData.liniaId} required>
                    <option value="">{formData.linia.numer}</option>
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
              {isCourseSelected() && (
                <tr>
                  <td className="addWhat">Kierunek</td>
                  <td>
                    <select className="dropDownPrzystanek" defaultValue={courseData.kierunek} name="kierunek" onChange={handleChangeKierunek} required>
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
                          defaultValue={course.godzinna}
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

          <button className="infoBtn" type="submit">Zapisz zmiany</button>
          
          <Link className="infoBtn" to={`/course`}>Powrót</Link>
        </div>
      </form>
          <button className="infoBtn" onClick={handleDeleteClick}>Usuń kurs</button>
    </div>
  );
}

export default CourseEdit;