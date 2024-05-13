import React from 'react'
import { useEffect , useState} from 'react';
import {SERVER_URL} from '../constant';
import { Link } from "react-router-dom";



const BusStopList = () => {
	const [busStopData, setBusStopData] = useState([]);
	const getBusStopData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/przystanek/full", {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setBusStopData(data);

		} catch (error){


		}
	}

	 useEffect(()=>{
		getBusStopData();
	}, []);

  return (
            <div className="pt-40">
                    <h2>Lista przystanków:</h2>
                <div className="addDiv">
                    <Link className="infoBtn" to={`/bus_stop/save`}>Dodaj przystanek</Link>
                </div>

                <div className="listDiv">
                    <table className="tableFormat">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nazwa</th>
                                <th>Adres</th>
                            </tr>
                        </thead>
                        <tbody>
                            {busStopData.map((busStop, index) => (
                                <tr key={index}>
                                    <td>{busStop.przystanekId}</td>
                                    <td><Link className="infoBtn" to={`/bus_stop/info/${busStop.przystanekId}`}>{busStop.nazwa}</Link></td>
                                    <td>
                                        {busStop.miasto}
                                        <span> - </span>
                                        {busStop.ulica}
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
            </div>
            </div>
  )
}

export default BusStopList