import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { Link } from 'react-router-dom';

function extractLastPathComponent(url) {
    let index = 0;
    let result = "";
    for (let i = url.length - 1; i > 0; i--) {
        if (url[i] === '/') {
            index = i+1;
            break;
        }
    }
    for (let i = index; i < url.length; i++) {
        result += url[i];
    }
    return result;
}

const BusLineInfo = () => {
    const [lineData, setLineData] = useState(null);
    const [busStopData, setBusStopData] = useState([]);
    const listId = extractLastPathComponent(window.location.pathname);
    console.log(listId);
    const getLineData = async () => {
        try {
            const response = await fetch(SERVER_URL + '/api/linia/' +  + extractLastPathComponent(window.location.pathname), { method: 'GET', credentials: 'include' });
            if (!response.ok) {
                throw new Error('error on get');
            }
            const data = await response.json();
            setLineData(data);
        } catch (error) {
            console.error('Error getting line data:', error);
        }
    };

    const getBusStopData = async () => {
        try {
            const response = await fetch(SERVER_URL + '/api/przystanek/' + extractLastPathComponent(window.location.pathname), { method: 'GET', credentials: 'include' });
            if (!response.ok) {
                throw new Error('error on get');
            }
            const data = await response.json();
            setBusStopData(data);
        } catch (error) {
            console.error('Error getting bus stop data:', error);
        }
    };

    useEffect(() => {
        getLineData();
        getBusStopData();
    }, []);

    if (!lineData) {
        return <div>Loading...</div>;
    }

    return (
        <div className="pt-40">
            <h2>Lista przystanków linii "{lineData.numer || ''}":</h2>
            <div className="listDiv">
                <table className="tableFormat">
                    <thead>
                        <tr>
                            <th>Kolejność</th>
                            <th>ID</th>
                            <th>Przystanek</th>
                        </tr>
                    </thead>
                    <tbody>
                        {lineData.przystanki
                            .sort((a, b) => a.kolejnosc - b.kolejnosc)
                            .map((stop, stopIndex) => (
                                <tr key={stopIndex}>
                                    <td>{stop.kolejnosc}</td>
                                    <td>{stop.przystanekId}</td>
                                    <td><Link className="infoBtn" to={`/bus_stop/info/${stop.przystanekId}`}>{stop.nazwa}</Link></td>
                                </tr>
                            ))}
                    </tbody>
                </table>
            </div>

            <Link className="infoBtn" to={`/bus_line`}>Powrót</Link>
            <Link className="infoBtn" to={`/bus_line`}>Edytuj linię</Link>

        </div>
    );

};

export default BusLineInfo;
