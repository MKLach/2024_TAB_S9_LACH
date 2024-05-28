
import React, { useEffect, useState } from 'react';
import { SERVER_URL } from '../constant';
import { useSearchParams, useNavigate  } from "react-router-dom";
import Alert from "../ui/Alert"

const BusStopTimeTableInfo = () => {
	
	const [showAlert, setShowAlert] = useState(false);
	const [busStops, setBusStops] = useState([]);
	const [today, setToday] = useState(null);
		;
	const [rtoday, setRToday] = useState(new Date());
	
    const [savedMessage, setSavedMessage] = useState("");
	const [searchParams, setSearchParams] = useSearchParams();
	const [przyid, setPrzyid] = useState(0);
	const history = useNavigate();
    const [busStopTimeTableData, setBusStopTimeTableData] = useState(null);
    
    const utilityDate = (date) => {
		console.log(date);
		
		
		setRToday("06.05.2024");
	}
    
    const fetchBusStops = async () => {
    try {
      const response = await fetch(SERVER_URL + "/api/przystanek");
      if (!response.ok) {
        throw new Error("Failed to fetch bus stops");
      }
      const data = await response.json();
      setBusStops(data);
    } catch (error) {
      console.error("Error fetching bus stops:", error);
    }
  };
    
    const handleShowAlert = (messahe) => {
		setSavedMessage(messahe)
    	setShowAlert(true);
    	setTimeout(() => {
      setShowAlert(false);
     }, 3000);
  	};
    
    const getBusStopTimeTableData = async () => {
		if(!searchParams.get("przystanek_id")){
		
			handleShowAlert("brak wybranego przystanku!");
			return;
		}
		
		if(!today){
			return;
		}
	  
	    try {
	      const response = await fetch(SERVER_URL + "/api/rozklad?id=" + przyid + "&date="+ today, { method: "GET", credentials: "include" });
	      if (!response.ok) {
	        throw new Error("error on get 2");
	      }
	      const data = await response.json();
	      setBusStopTimeTableData(data);
	     
	    } catch (error) {
	      console.error("Error fetching line data:", error);
	      handleShowAlert("error on fetch");
	    }
			
	}
	
	useEffect(
		() => {
			
			if(!searchParams.get("date")){
				let date = new Date();
				console.log("setting");
				setToday(date.getFullYear().toString() + '-' + (date.getMonth() + 1).toString().padStart(2, 0) +
    '-' + date.getDate().toString().padStart(2, 0));
				
			} else {
				setToday(searchParams.get("date"));
			}
			setRToday(today)
			setPrzyid(searchParams.get("przystanek_id"));
			setShowAlert(false);
			
			fetchBusStops();
			
		}, []
		
	)
	
	useEffect(
		() => {
			getBusStopTimeTableData();
			
			
		},[today, przyid]
	)
	

    const getBusLines = () => {
        const { rozklad } = busStopTimeTableData;
        return Object.keys(rozklad);
    };
    
    const onPrzystanekChanged = (e) => {
		setPrzyid(e.target.value);
		console.log(e.target.value);
		history("/bus_stop/timetable?przystanek_id="+e.target.value + "&date="+today);
	}
	
	 const onDateChanged = (e) => {
		console.log(e.target.value);
		setToday(e.target.value);
		setRToday(today);
		history("/bus_stop/timetable?przystanek_id="+przyid + "&date="+today);
		
		
	}

    const getTimetableForLine = (line) => {
        return busStopTimeTableData.rozklad[line].map((schedule, index) => (
            <span key={index}>
                
                <span style={{ color: schedule.odw ? '#AA0000' : 'black' }}>
                    {schedule.time} 
                    
                </span>
                {index < busStopTimeTableData.rozklad[line].length - 1 && ', '}
            </span>
        ));
    };

    return (
        <div className="pt-40">

           <div className="listDiv">
          <h1>Rozkład jazdy dla przystanku:</h1>
           <div className="listScheduleFormat">

            <select
                  className="dropDownPrzystanek"
                  name="przystanek"
                  onChange={onPrzystanekChanged}
                  defaultValue={przyid}
                >
                      <option value="">Wybierz przystanek</option>
                      { busStops.filter((a) => !a.nazwa.includes("ODW") ).map(stop => (
                        <option key={stop.przystanekId} value={stop.przystanekId}>
                          {`${stop.nazwa}, ${stop.miasto}, ${stop.ulica}`}
                        </option>
                      ))}
            </select>
            
           <input className="addInput"
                type="date"
                name="data"
                defaultValue={today}
                onChange={onDateChanged}
                required
            />
             </div>
            {busStopTimeTableData && today &&
             <table  className="tableFormat">
                <thead>
                    <tr>
                        <th>Linia</th>
                        <th>Rozkład</th>
                    </tr>
                </thead>
                <tbody>
                    {getBusLines().map(line => (
                         <tr key={line}>
                            <td>{line}</td>
                            <td>
                                {getTimetableForLine(line)}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            }
            {showAlert && (
       	 <Alert
          	message= {savedMessage}
          	onClose= {() => setShowAlert(false)}
       		 />
      		)}
        </div>
        </div>
        
    );
};

export default BusStopTimeTableInfo;