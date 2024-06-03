import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link, useSearchParams } from "react-router-dom";

const CourseList = ({ userRole }) => {
  const [lineData, setLineData] = useState([]);
  const [selectedLine, setSelectedLine] = useState('');
  const [coursesData, setCouresData] = useState([]);
  const [courseId, setCourseId] = useState(0);
  const [searchParams] = useSearchParams();

  const getLineData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/linia", { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("error on get 2");
      }
      const data = await response.json();
      setLineData(data);
      if (searchParams.get("linia_id")) {
        setSelectedLine(searchParams.get("linia_id"));
      }
    } catch (error) {
      console.error("Error fetching line data:", error);
    }
  };

  const getStopsData = async (lineId) => {
    try {
      const response = await fetch(`${SERVER_URL}/api/kurs/?linia_id=${lineId}`, { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("Error fetching stops data");
      }
      const data = await response.json();
      setCouresData(data);
    } catch (error) {
      console.error("Error fetching stops data:", error);
    }
  };

  useEffect(() => {
    getLineData();
  }, []);

  useEffect(() => {
    if (selectedLine) {
      getStopsData(selectedLine);
    } else {
      setCouresData([]);
    }
  }, [selectedLine]);

  const handleLineChange = (e) => {
    if (e.target.value !== "") {
      setSelectedLine(e.target.value);
      setCourseId(e.target.value);
    } else {
      setSelectedLine('');
      setCourseId('');
    }
  };

  const formatTime = (timeString) => {
    return timeString.slice(0, 5);
  };

  return (
    <div className="pt-40">
      <h2>Kursy:</h2>
      <div className="addDiv">
        {userRole !== 'DISPATCHER' && (
          <Link className="infoBtn" to={`/course/save`}>Dodaj kurs</Link>
        )}
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
        {coursesData.length > 0 && (
          coursesData.map((course) => (
            <div key={course.kursId}>
              <h1>{course.kursId} Kierunek - {course.kierunek === 0 ? "Normalny" : "Odwrotny"}</h1>
              <p>{course.harmonogram.nazwa}</p>
              <table className="tableFormat">
                <thead>
                  <tr>
                    <th>Przystanek</th>
                    <th>Godzina</th>
                  </tr>
                </thead>
                <tbody>
                  {course.przystanki.sort((a, b) => {
                    if (course.kierunek === 0) {
                      return a.przystanekWLini.kolejnosc - b.przystanekWLini.kolejnosc;
                    } else {
                      return b.przystanekWLini.kolejnosc - a.przystanekWLini.kolejnosc;
                    }
                  }).map((stop) => (
                    <tr key={stop.przystanekwKursieId}>
                      <td>{stop.przystanekWLini.nazwa}</td>
                      <td>{formatTime(stop.godzinna)}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
              <Link className="infoBtn" to={`/course/edit?kurs_id=${course.kursId}`}>Edytuj kurs</Link>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default CourseList;
