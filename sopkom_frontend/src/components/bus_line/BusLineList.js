import React from 'react'
import { useEffect , useState} from 'react';
import {SERVER_URL} from '../constant';
import { Link } from "react-router-dom";



const BusLineList = () => {
	const [LineData, setLineData] = useState([]);
	const getLineData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/linia", {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setLineData(data);

		} catch (error){


		}
	}

	 useEffect(()=>{
		getLineData();
	}, []);

  return (
            <div className="pt-40">
                    <h2>Lista kursów:</h2>
                <div className="addDiv">
                    <Link className="infoBtn" to={`/bus_line/save`}>Dodaj linię</Link>
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
                            {LineData.map((line, index) => (
                                <tr key={index}>
                                    <td>{line.id}</td>
                                    <td><Link className="infoBtn" to={`/bus_line/info/${line.id}`}>{line.numer}</Link></td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
            </div>
            </div>
  )
}

export default BusLineList