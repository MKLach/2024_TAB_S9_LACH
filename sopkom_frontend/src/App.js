import React, { useEffect, useState } from 'react';
import { SERVER_URL , setServerUrl} from './components/constant';
import './App.css';
import DriverList from './components/driver/DriverList';
import DriverAdd from './components/driver/DriverAdd';
import DriverInfo from './components/driver/DriverInfo';
import IncidentInfo from './components/incident/IncidentInfo';
import IncidentAdd from './components/incident/IncidentAdd';
import IncidentList from './components/incident/IncidentList';
import BusInfo from './components/bus/BusInfo';
import BusAdd from './components/bus/BusAdd';
import BusList from './components/bus/BusList';
import CourseAdd from './components/course/CourseAdd';
import CourseList from './components/course/CourseList';
import CourseEdit from './components/course/CourseEdit';
import PlannedCourseList from './components/planned_course/PlannedCourseList';
import PlannedCourseList_to_plan from './components/planned_course/PlannedCourseList_to_plan';
import PlannedCourseAdd from './components/planned_course/PlannedCourseAdd';
import PlannedCourseInfo from './components/planned_course/PlannedCourseInfo';
import PlannedCourseAdd_with_data from './components/planned_course/PlannedCourseAdd_with_data';
import BusStopList from './components/bus_stop/BusStopList';
import BusStopInfo from './components/bus_stop/BusStopInfo';
import BusStopAdd from './components/bus_stop/BusStopAdd';
import BusStopCourses from './components/bus_stop/BusStopCourses';
import BusStopTimeTableInfo from './components/rozklad/rozklad';
import BusLineList from './components/bus_line/BusLineList';
import BusLineInfo from './components/bus_line/BusLineInfo';
import BusLineAdd from './components/bus_line/BusLineAdd';
import BusLineEdit from './components/bus_line/BusLineEdit';
import Navbar from './components/Navbar';
import Dashboard from './components/Dashboard';
import UserPage from './components/UserPage';
import Footer from './components/Footer';
import Home from './components/Home';
import {  BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  const [userRole, setUserRole] = useState('ROLE_USER');
  
  const getUser = async () => {
	  //SERVER_URL = window.location.hostname+":8080";
	  setServerUrl("http://"+window.location.hostname+":8080");
	  console.log(SERVER_URL);
    try {
      const response = await fetch(
		  
        SERVER_URL + '/user',
        { method: 'GET', redirect: "follow", credentials: 'include', origin:"*" }
      );
      console.log("a "+response.url);
      if (response.redirected) {
        console.log("a "+response.url);
        document.location = response.url;
        return;
      }
      
      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }
      const data = await response.json();
      console.log(data);
      setUserRole(data.role);
    } catch (error) {
      console.error('Error fetching data:', error.message);
    }
  }

  useEffect(() => {
    getUser();
  }, []);

  return (
    <Router>
      <div className="App">
        <Navbar userRole={userRole} />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/driver" element={<DriverList userRole={userRole} />} />
          <Route path="/driver/save" element={<DriverAdd />} />
          <Route path="/driver/info/:id" element={<DriverInfo />} />
          <Route path="/bus_stop" element={<BusStopList BusList userRole={userRole}/>} />
          <Route path="/bus_stop/save" element={<BusStopAdd />} />
          <Route path="/bus_stop/info/:id" element={<BusStopInfo />} />
          <Route path="/bus_stop/timetable" element={<BusStopTimeTableInfo />} />
          <Route path="/bus_line" element={<BusLineList userRole={userRole} />} />
          <Route path="/bus_line/info/:id" element={<BusLineInfo />} />
          <Route path="/bus_line/save" element={<BusLineAdd />} />
          <Route path="/bus_line/edit/:id" element={<BusLineEdit />} />
          <Route path="/course" element={<CourseList userRole={userRole} />} />
          <Route path="/course/save" element={<CourseAdd />} />
          <Route path="/course/edit" element={<CourseEdit />} />
          <Route path="/planned_course" element={<PlannedCourseList BusList userRole={userRole} />} />
          <Route path="/planned_course_to_plan" element={<PlannedCourseList_to_plan />} />
          <Route path="/planned_course/save" element={<PlannedCourseAdd />} />
          <Route path="/planned_course/with_data" element={<PlannedCourseAdd_with_data />} />
          <Route path="/planned_course/info/:id" element={<PlannedCourseInfo />} />
          <Route path="/incident" element={<IncidentList />} />
          <Route path="/incident/save" element={<IncidentAdd />} />
          <Route path="/incident/info/:id" element={<IncidentInfo />} />
          <Route path="/bus" element={<BusList userRole={userRole} />} />
          <Route path="/bus/save" element={<BusAdd />} />
          <Route path="/bus/info/:id" element={<BusInfo />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/me" element={<UserPage />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
        <Footer />
      </div>
    </Router>
  );
}

// Define a NotFound component to handle unknown routes
const NotFound = () => (
  <div className="pt-80 h-[100vh]">
    <h1 className="text-4xl md:text-5xl text-center lg:text-6xl font-bold leading-tight mb-4">404 Not Found</h1>
    <p className="text-lg md:text-xl text-center lg:text-2xl mb-4">The page You are looking for does not exist.</p>
  </div>
);

export default App;
