import React from 'react'
import {useEffect , useState} from 'react';
import {SERVER_URL} from '../constant';
import { Link } from "react-router-dom";

const IncidentList = () => {
	const [incidentData, setIncidentData] = useState([]);

	const getIncidentData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/incydent", {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setIncidentData(data);
			console.log(incidentData);

		} catch (error){


		}
	}

	 useEffect(()=>{
		getIncidentData();
	}, []);

  return (
        <div className="pt-40">
                    <h2>Incydenty:</h2>
            <div className="addDiv">
                <Link className="infoBtn" to={`/incident/save`}>Dodaj incydent</Link>
            </div>
            <div className="listDiv">
            <table className="tableFormat">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Krótki opis</th>
                        <th>Koszt</th>
                        <th>Typ</th>
                    </tr>
                </thead>

                <tbody>
                    {incidentData.map((incidnet, index) => (

                        <tr key={index}>
                            <td>{incidnet.incydentId}</td>
                            <td><Link className="infoBtn" to={`/incident/info/${incidnet.incydentId}`}>{incidnet.krotko}</Link></td>
                            <td>{incidnet.koszty}</td>
                            <td>{incidnet.typ}</td>
                        </tr>

                    ))}
                </tbody>
            </table>
            </div>
        </div>
  )
}

export default IncidentList