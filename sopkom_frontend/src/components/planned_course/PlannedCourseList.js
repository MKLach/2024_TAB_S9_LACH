import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link, useSearchParams } from "react-router-dom";

function formatDateTime(dateTimeStr) {
    if (!dateTimeStr) {
        return "--:-- --.--.----";
    }

    const dateIn = new Date(dateTimeStr);

    return dateIn.toLocaleTimeString().slice(0, 5) + " " + dateIn.toLocaleDateString();
}

const PlannedCourseList = () => {
    const [courseData, setCourseData] = useState([]);
    const [searchParams] = useSearchParams();
    const [selectedLine, setSelectedLine] = useState('');
    const [selectedStatus, setSelectedStatus] = useState('');
    const [lineData, setLineData] = useState([]);
    const [filteredData, setFilteredData] = useState([]);

    const getCourseData = async (lineId) => {
        try {
            console.log(`Fetching course data for line: ${lineId}`);
            const response = await fetch(`${SERVER_URL}/api/przejazd${lineId ? `?linia_id=${lineId}` : ''}`, { method: "GET", credentials: "include" });
            if (!response.ok) {
                throw new Error("error on get 2");
            }
            const data = await response.json();
            console.log('Fetched course data:', data);
            setCourseData(data);
        } catch (error) {
            console.error("Error fetching course data:", error);
        }
    };

    const getLineData = async () => {
        try {
            const response = await fetch(SERVER_URL + "/api/linia", { method: "GET", credentials: "include" });
            if (!response.ok) {
                throw new Error("error on get 2");
            }
            const data = await response.json();
            console.log('Fetched line data:', data);
            setLineData(data);
            if (searchParams.get("linia_id")) {
                setSelectedLine(searchParams.get("linia_id"));
            }
        } catch (error) {
            console.error("Error fetching line data:", error);
        }
    };

    useEffect(() => {
        getLineData();
    }, []);

    useEffect(() => {
        if (selectedLine) {
            getCourseData(selectedLine);
        } else {
            getCourseData();
        }
    }, [selectedLine]);

    useEffect(() => {
        filterCourses();
    }, [courseData, selectedStatus]);

    const handleLineChange = (e) => {
        console.log('Selected line changed to:', e.target.value);
        setSelectedLine(e.target.value);
    };

    const handleStatusChange = (e) => {
        console.log('Selected status changed to:', e.target.value);
        setSelectedStatus(e.target.value);
    };

    const filterCourses = () => {
        let filtered = courseData;

        if (selectedStatus) {
            filtered = filtered.filter(course => course.status === selectedStatus);
        }

        if (selectedLine) {
            console.log(filtered = filtered.filter(course => course.liniaNumer === selectedLine));
        }

        console.log('Filtered data:', filtered);
        setFilteredData(filtered);
    };

    return (
        <div className="pt-40">
            <h2>Lista Przejazdów:</h2>
            <div className="columnDiv">
                <div className="addDiv">
                    <Link className="infoBtn" to={`/planned_course/save`}>Zaplanuj przejazd</Link>
                </div>

                <div className="dropDiv">
                    <p>Filtry</p>
                    <select className="dropDownCourse" value={selectedLine} onChange={handleLineChange}>
                        <option value="">Wybierz linię</option>
                        {lineData.map((line) => (
                            <option key={line.id} value={line.numer}>{line.numer}</option>
                        ))}
                    </select>

                    <select className="dropDownCourse" value={selectedStatus} onChange={handleStatusChange}>
                        <option value="">Wybierz status</option>
                        <option value="Zakończony">Zakończony</option>
                        <option value="Zaplanowany">Zaplanowany</option>
                        <option value="Nie odbył się">Nie odbył się</option>
                    </select>
                </div>
            </div>

            <div className="listDiv">
                <table className="tableFormat">
                    <thead>
                        <tr>
                            <th>Lp.</th>
                            <th>Kurs</th>
                            <th>Data</th>
                            <th>Kierowca</th>
                            <th>Autobus</th>
                            <th>Status</th>
                        </tr>
                    </thead>

                    <tbody>
                        {Array.isArray(filteredData) && filteredData.map((course, index) => (
                            <tr key={course.przejazdId}>
                                <td>{index + 1}</td>
                                <td><Link className="infoBtn" to={`/planned_course/info/${course.przejazdId}`}>{course.liniaNumer}</Link></td>
                                <td>{formatDateTime(course.dataStartu)}</td>
                                <td>{course.kierowcaImieNazwisko}</td>
                                <td>{course.autobusNazwa}</td>
                                <td>{course.status}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default PlannedCourseList;
