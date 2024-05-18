import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";

const CourseList = () => {
  const [lineData, setLineData] = useState([]);
  const [selectedLine, setSelectedLine] = useState('');
  const [stopsData, setStopsData] = useState([]);
  const [courseId, setCourseId] = useState(0);

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

  const getStopsData = async (lineId) => {
    try {
      const response = await fetch(`${SERVER_URL}/api/kurs/?linia_id=${lineId}`, { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("Error fetching stops data");
      }
      const data = await response.json();
      setStopsData(data);
    } catch (error) {
      console.error("Error fetching stops data:", error);
    }
  }

  useEffect(() => {
    getLineData();
  }, []);

  useEffect(() => {
    if (selectedLine) {
      getStopsData(selectedLine);
    } else {
      setStopsData([]);
    }
  }, [selectedLine]);

  const handleLineChange = (e) => {
        if (e.target.value != ""){
            setSelectedLine(e.target.value);
            setCourseId(e.target.value);
        }else{
            setSelectedLine('');
            setCourseId('');
        }
  }

  const formatTime = (timeString) => {
    return timeString.slice(0, 5);
  }

  const editable = () => {
    if(selectedLine.length > 0){
        return courseId;
    }
    return false;
  }


//                <th>Adres</th>
//                    <td>{stop.przystanekWLini.adres}</td>

  return (
    <div className="pt-40">
      <h2>Kursy:</h2>
      <div className="addDiv">
        <Link className="infoBtn" to={`/course/save`}>Dodaj kurs</Link>
      </div>

      <div className="listDiv">
        <table className="addFormat">
          <tbody>
            <tr>
              <td className="addWhat">Wybierz linię:</td>
              <td>
                <select className="dropDownCourse" value={selectedLine} onChange={handleLineChange}>
                  <option value="">Wybierz linię</option>
                  {lineData.map((line) => (
                    <option key={line.id} value={line.id}>{line.numer}</option>
                  ))}
                </select>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div className="listDiv">
        {stopsData.length > 0 && (
          <table className="tableFormat">
            <thead>
              <tr>
                <th>Nazwa przystanku</th>
                <th>Godzina</th>
              </tr>
            </thead>
            <tbody>
              {stopsData.map((course) =>
                course.przystanki.map((stop) => (
                  <tr key={stop.przystanekwKursieId}>
                    <td>{stop.przystanekWLini.nazwa}</td>
                    <td>{formatTime(stop.godzinna)}</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        )}

      </div>
      {editable() && (
          <Link className="infoBtn" to={`/course/edit/${courseId}`}>Edytuj kurs</Link>
      )}
    </div>
  )
}

export default CourseList;
