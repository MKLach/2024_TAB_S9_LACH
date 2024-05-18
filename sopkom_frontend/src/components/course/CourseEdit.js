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
    const [lineData, setLineData] = useState(null); // Set initial state to null
    const [selectedLine, setSelectedLine] = useState([]);
    const [harmonogramData, setHarmonogramData] = useState([]);
    const [stopsData, setStopsData] = useState([]);
  const [savedMessage, setSavedMessage] = useState("");
    const courseId = extractLastPathComponent(window.location.pathname);

    const [formData, setFormData] = useState({
        linia: "",
        kieruenk: "",
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
            setLineData(data);
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
        return timeString.slice(0, 5);
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

  const deleteBusLine = async () => {
        try {
            const response = await fetch(SERVER_URL + "/api/kurs/" + courseId, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                }
            });
            if (!response.ok) {
                throw new Error("Failed to delete driver");
            }
            setTimeout(() => {
                window.location.href = '/course';
            }, 100);

        } catch (error) {
            let errorMessage = error.message;
                setSavedMessage("Nie udało się usunąć przystanku.");
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
                                <label>{lineData?.linia?.numer}</label>
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

                        {lineData && lineData.przystanki && lineData.przystanki.map((stop) => (
                            <tr key={stop.przystanekwKursieId}>
                                <td>{stop.przystanekWLini.nazwa}</td>
                                <td>
                                    <input
                                        className="addInput"
                                        type="text"
                                        name={`godzinna-${stop.przystanekId}`}
                                        value={formatTime(stop.godzinna)}
                                        onChange={handleChange}
                                        required
                                    />
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <button className="infoBtn" type="submit">Zapisz zmiany</button>

                <Link className="infoBtn" to={`/course`}>Powrót</Link>
                <div className="deleteCourse">
                    <button className="infoBtn" onClick={deleteBusLine}>Usuń kurs</button>
                </div>
            </div>
        </div>
    )
}

export default CourseEdit;
