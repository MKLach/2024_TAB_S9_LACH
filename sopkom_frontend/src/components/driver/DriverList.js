import React from 'react'
import {useEffect , useState} from 'react';
import {SERVER_URL} from '../constant';
import { Link } from "react-router-dom";

const DriverList = () => {
	const [driverData, setDriverData] = useState([]);

	const getDriverData = async () => {

		try {
			const response = await fetch(SERVER_URL + "/api/kierowca/", {method: "GET", credentials: "include"});
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.json();
			setDriverData(data);

		} catch (error){


		}
	}

	 useEffect(()=>{
		getDriverData();
	}, []);

  return (
        <div className="pt-40">
                    <h2>Kierowcy:</h2>
            <div className="addDiv">
                <Link className="infoBtn" to={`/driver/save`}>Dodaj kierowcę</Link>
            </div>
            <div className="listDiv">
            <table className="tableFormat">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Imię i Nazwisko</th>
                    </tr>
                </thead>

                <tbody>
                    {driverData.map((driver, index) => (

                        <tr key={index}>
                            <td>{driver.kierowcaId}</td>
                            <td><Link className="infoBtn" to={`/driver/info/${driver.kierowcaId}`}>{`${driver.imie} ${driver.nazwisko}`}</Link></td>
                        </tr>

                    ))}
                </tbody>
            </table>
            </div>
        </div>
  )
}

export default DriverList