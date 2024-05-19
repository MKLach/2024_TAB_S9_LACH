import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";

function extractLastPathComponent(url) {
    let index = 0;
    let result = "";
    for (let i = url.length - 1; i > 0; i--) {
        if (url[i] === '/') {
            index = i + 1;
            break;
        }
    }
    for (let i = index; i < url.length; i++) {
        result += url[i];
    }
    return result;
}

const CourseEdit = () => {
    const [lineData, setLineData] = useState();
    const [selectedLine, setSelectedLine] = useState([]);
    const [harmonogramData, setHarmonogramData] = useState([]);
    const [stopsData, setStopsData] = useState([]);
    const [savedMessage, setSavedMessage] = useState("");
    const courseId = extractLastPathComponent(window.location.pathname);

    const [formData, setFormData] = useState({
        linia: "",
        kierunek: "",
        przystanki: [{ przystanekId: "", godzinna: "" }]
    });

    const getLineData = async () => {
        try {
            const response = await fetch(SERVER_URL + "/api/kurs/template/" + courseId, { method: "GET", credentials: "include" });
            if (!response.ok) {
                throw new Error("error on get 2");
            }
            const data = await response.json();
            console.log("Line Data:", data); // Log the line data to check its structure
            setFormData(data);
            console.log("Form Data:", formData);
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

    const getSelectedLine = async (Id) => {
        try {
            const response = await fetch(SERVER_URL + "/api/linia/" + Id, { method: "GET", credentials: "include" });
            if (!response.ok) {
                throw new Error("error on get 2");
            }
            const data = await response.json();
            setSelectedLine(Array.isArray(data) ? data : [data]);
        } catch (error) {
            console.error("Error fetching line data:", error);
        }
    }

    useEffect(() => {
        getLineData();
        fetchHarmonogramData();
        getSelectedLine(courseId)
    }, []);

    const formatTime = (timeString) => {
        return timeString ? timeString.slice(0, 5) : "";
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        const [key, index] = name.split('-');

        if (key === 'godzinna') {
            setFormData(prevFormData => {
                const updatedPrzystanki = [...prevFormData.przystanki];
                updatedPrzystanki[parseInt(index)].godzinna = value;
                return { ...prevFormData, przystanki: updatedPrzystanki };
            });
        } else {
            setFormData({ ...formData, [name]: value });
        }
    };

    const deleteCourse = async () => {
        try {
            const response = await fetch(SERVER_URL + "/api/kurs/" + courseId, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            if (!response.ok) {
                throw new Error("Failed to delete course");
            }
            setTimeout(() => {
                window.location.href = '/course';
            }, 500);
        } catch (error) {
            let errorMessage = error.message;
            setSavedMessage("Nie udało się usunąć kursu.");
        }
    };

      const AddCourse = async () => {
            try {
                const response = await fetch(SERVER_URL + "/api/kurs/" + formData.kursId, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(formData)
                });
                console.log(response);
                if (!response.ok) {
                    throw new Error("Failed to save course");
                }
                setSavedMessage("Zmiany zostały zapisane.");
            } catch (error) {
                console.error("Error saving course:", error);
                setSavedMessage("Nie udało się zapisać zmian.");
            }
        };

    return (
        <div className="pt-40">
            <p>{savedMessage}</p>
            <div className="listDiv">
                <table className="addFormat">
                    <tbody>
                        <tr>
                            <td className="addWhat">Linia:</td>
                            <td>
                                <label>{formData?.linia?.numer}</label>
                            </td>
                        </tr>
                        <tr>
                            <td className="addWhat">Harmonogram:</td>
                            <td>
                                <select
                                    className="dropDownPrzystanek"
                                    name="harmonogramId"
                                    value={formData.harmonogramId}
                                    onChange={handleChange}
                                    required>
                                    {harmonogramData.map((harmonogram) => (
                                        <option
                                            key={harmonogram.harmonogramId}
                                            value={harmonogram.harmonogramId}
                                            defaultValue={lineData?.harmonogram?.harmonogramId === harmonogram.harmonogramId ? 'selected' : undefined}
                                        >
                                            {harmonogram.nazwa}
                                        </option>
                                    ))}
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td className="addWhat">Typ autobusu:</td>
                            <td>
                                <select className="dropDownPrzystanek" name="typ_autobusu"
                                    value={lineData?.typ_autobusu ?? formData.typ_autobusu} onChange={handleChange} required>
                                    <option value="0">Normalny</option>
                                    <option value="1">Niskopodłogowy</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td className="addWhat">Kierunek</td>
                            <td>
                                <select className="dropDownPrzystanek"
                                    name="kierunek"
                                    onChange={handleChange}
                                    value={lineData?.kierunek ?? formData.kierunek} required>
                                    <option value="0">Normalny</option>
                                    <option value="1">Odwrotny</option>
                                </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div className="listDiv">
                <table className="tableFormat">
                    <thead>
                        <tr>
                            <th>Nazwa przystanku</th>
                            <th>Godzina</th>
                        </tr>
                    </thead>
                    <tbody>
                        {formData.przystanki && formData.przystanki.map((stop, index) => (
                            <tr key={stop.przystanekwKursieId}>
                                <td>{stop.przystanekWLini?.nazwa}</td>
                                <td>
                                    <input
                                        className="addInput"
                                        type="text"
                                        name={`godzinna-${index}`}
                                        value={formatTime(stop.godzinna)}
                                        onChange={handleChange}
                                        required
                                    />
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <button className="infoBtn" type="submit" onClick={AddCourse}>Zapisz zmiany</button>

                <Link className="infoBtn" to={`/course`}>Powrót</Link>
                <div className="deleteCourse">
                    <button className="infoBtn" onClick={deleteCourse}>Usuń kurs</button>
                </div>
            </div>
        </div>
    )
}

export default CourseEdit;
