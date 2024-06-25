import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link,  } from "react-router-dom";


const BusList = ({ userRole }) => {
  const [busData, setBusData] = useState([]);

  const getBusData = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/autobus/", { method: "GET", credentials: "include" });
      if (!response.ok) {
        throw new Error("error on get 2");
      }
      const data = await response.json();
      setBusData(data);
    } catch (error) {
	  console.error(SERVER_URL + "/api/autobus/");
      console.error("Error fetching bus data:", error);
    }
  };

  useEffect(() => {
    getBusData();
  }, []);

  return (
    <div className="pt-40">
      <h2>Autobusy:</h2>
      <div className="addDiv">
        {userRole !== 'DISPATCHER' && (
          <Link className="infoBtn" to={`/bus/save`}>Dodaj autobus</Link>
        )}
      </div>
      <div className="listDiv">
        <table className="tableFormat">
          <thead>
            <tr>
              <th>ID</th>
              <th>Numer rejestracyjny</th>
            </tr>
          </thead>
          <tbody>
            {busData.map((bus, index) => (
              <tr key={index}>
                <td>{bus.autbousId}</td>
                <td><Link className="infoBtn" to={`/bus/info/${bus.autbousId}`}>{bus.numerRejestracyjny}</Link></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default BusList;
