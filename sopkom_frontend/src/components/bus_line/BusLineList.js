import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from "react-router-dom";

const BusLineList = ({ userRole }) => {
  const [lineData, setLineData] = useState([]);

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
  };

  useEffect(() => {
    getLineData();
  }, []);

  return (
    <div className="pt-40">
      <h2>Lista Lini Autobusowych:</h2>
      <div className="addDiv">
        {userRole !== 'DISPATCHER' && (
          <Link className="infoBtn" to={`/bus_line/save`}>Dodaj linię</Link>
        )}
      </div>
      <div className="listDiv">
        <table className="tableFormat">
          <thead>
            <tr>
              <th>ID</th>
              <th>Numer linii</th>
            </tr>
          </thead>
          <tbody>
            {lineData.map((line, index) => (
              <tr key={index}>
                <td>{line.id}</td>
                <td><Link className="infoBtn" to={`/bus_line/info/${line.id}`}>{line.numer}</Link></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default BusLineList;
