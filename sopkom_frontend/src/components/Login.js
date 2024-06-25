import React, {useEffect, useState} from 'react'

import { useSearchParams, useNavigate  } from "react-router-dom";
import { SERVER_URL } from '../components/constant';



const Login = () => {
	const [savedMessage, setSavedMessage] = useState("");
	const [formData, setFormData] = useState({
		username: "",
		password: ""
		
	});
	const [searchParams, setSearchParams] = useSearchParams();
	
	useEffect(() => {
		if(searchParams.get("username")){
			formData.username = searchParams.get("username");
		}
    
    }, []);
	
	const handleSubmit = async (e) => {
	 e.preventDefault();
	    try {
	      const response = await fetch(SERVER_URL + "/login?username="+formData.username+"&password"+formData.password, {
	        method: 'POST',
	        headers: {
	          'Content-Type': 'application/json'
	        },
	        //body: JSON.stringify(formData),
	        credentials: "include"
	      });
	
	      if (!response.ok) {
	        throw new Error("Failed to add bus stop");
	      } else {
			  
	      setTimeout(() => {
	        window.location.href = '/';
	      }, 100);
	
		  }
	
	      setSavedMessage("Login succesfull");
	
	    } catch (error) {
	      setSavedMessage("Nie udało się dodać przystanku");
	    }
}

	
  return (
    <div className="pt-40">
		<p>{savedMessage}</p>
		<form class="addForm" onSubmit={handleSubmit}>
		 <table className="addFormat">
		 	<tr>
			<td className="addWhat2">
				<h1>Logowanie</h1>
				
			</td>
			
			</tr>
			<tr>
				<td className="addWhat2">
				<input className="addInput" type="text" name="username" defaultValue={formData.username} placeholder="Nazwa użytkownika" required/>
				</td>
			</tr>
			<tr>
				<td className="addWhat2">
				<input className="addInput" type="password" name="password" placeholder="Hasło" required/>
				</td>
			</tr>
			<input className="infoBtn" type="submit" value="Zaloguj się" />
		</table>
        </form>
	</div>
    
  )
}

export default Login