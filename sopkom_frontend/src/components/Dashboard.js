import React from 'react'
import { useEffect , useState} from 'react';
import {SERVER_URL} from './constant';

const Dashboard = () => {
	const [get2, setGet2] = useState();
	const getGet2 = async () => {
		
		try {
			
			const response = await fetch(SERVER_URL + "/get2", {method: "GET", credentials: "include"});
			
			if(!response.ok){
				throw new Error("error on get 2");
			}
			const data = await response.text();
			setGet2(data);
			
		} catch (error){
			
			
		}
	}
	
	 useEffect(()=>{
		getGet2();
	}, []);
	
  return (
  
    <div className="pt-40">{get2}</div>
  )
}

export default Dashboard