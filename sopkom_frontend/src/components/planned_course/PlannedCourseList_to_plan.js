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

function formatDateTime2(dateTimeStr) {
    if (!dateTimeStr) {
        return "--.--.----";
    }

    const dateIn = new Date(dateTimeStr);

    return dateIn.toLocaleDateString();
}

const PlannedCourseList_to_plan = () => {
    const [courseData, setCourseData] = useState([]);
    const [searchParams] = useSearchParams();
    const [selectedLine, setSelectedLine] = useState('');
    const [lineData, setLineData] = useState([]);
    const [filteredData, setFilteredData] = useState([]);

    const getCourseData = async (lineId) => {
        try {
            console.log(`Fetching course data for line: ${lineId}`);
            const response = await fetch(`${SERVER_URL}/api/przejazd/przyszle`, { method: "GET", credentials: "include" });
            if (!response.ok) {
                throw new Error("error on get przyszle");
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
    }, [courseData]);

    const handleLineChange = (e) => {
        console.log('Selected line changed to:', e.target.value);
        setSelectedLine(e.target.value);
    };

   
    const filterCourses = () => {
        let filtered = courseData;

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
              
                <div className="dropDiv">
                    <p>Filtry</p>
                    <select className="dropDownCourse" value={selectedLine} onChange={handleLineChange}>
                        <option value="">Wybierz linię</option>
                        {lineData.map((line) => (
                            <option key={line.id} value={line.numer}>{line.numer}</option>
                        ))}
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
                            <th>Status</th>
                        </tr>
                    </thead>

                    <tbody>
                        {Array.isArray(filteredData) && filteredData.map((course, index) => (
                            <tr key={index}>
                                <td>{index + 1}</td>
                                <td><Link className="infoBtn" to={`/planned_course/with_data?kurs_id=${course.kursId}&data=${formatDateTime2(course.data)}`}>{course.liniaNumer}</Link></td>
                                <td>{formatDateTime(course.dataStartu)}</td>

                                <td>{course.status}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default PlannedCourseList_to_plan;
