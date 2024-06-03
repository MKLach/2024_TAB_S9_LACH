import React, { useState, useCallback } from 'react';
import { Link } from 'react-router-dom';
import { AiOutlineClose, AiOutlineMenu } from 'react-icons/ai';
import { IoMdListBox, IoMdHome } from 'react-icons/io';
import { RiUserFill } from 'react-icons/ri';
import { GiBusStop } from "react-icons/gi";
import { GrSchedules, GrPlan } from "react-icons/gr";
import { LuShieldAlert } from "react-icons/lu";
import { TbBusStop } from "react-icons/tb";
import { PiNotePencil } from "react-icons/pi";
import { FaBusAlt, FaRoute } from "react-icons/fa";
import { FaPersonMilitaryToPerson } from "react-icons/fa6";
import { IoLogOut } from 'react-icons/io5';
import { SERVER_URL } from './constant';

const NavItem = ({ icon, to, onClick }) => (
  <li className='border border-gray-500 rounded-xl hover:scale-90 cursor-pointer duration-300' onClick={onClick}>
    <Link to={to} className="flex items-center p-4">
      {icon}
    </Link>
  </li>
);

const NavItemMobile = ({ icon, to, onClick }) => (
  <li className='hover:bg-[#9DB4C0] rounded-xl mt-2 cursor-pointer duration-300 flex items-center justify-center' onClick={onClick}>
    <Link to={to} className="flex items-center p-4">
      {icon}
    </Link>
  </li>
);

const Navbar = ({ userRole }) => {
  const [nav, setNav] = useState(false);

  const handleNav = useCallback(() => {
    setNav(prevNav => !prevNav);
  }, []);

  const renderNavItems = () => {
    const commonItems = [
      { icon: <IoMdHome style={{ color: 'black' }} size={20} />, to: "/" },
      { icon: <RiUserFill style={{ color: 'black' }} size={20} />, to: "/me" },
      { icon: <IoLogOut style={{ color: 'black' }} size={20} />, to: `${SERVER_URL}/logout` }
      
    ];

    const adminManagerItems = [
      { icon: <FaRoute style={{ color: 'black' }} size={20} />, to: "/course" },
      { icon: <TbBusStop style={{ color: 'black' }} size={20} />, to: "/bus_line" },
      { icon: <GrPlan style={{ color: 'black' }} size={20} />, to: "/bus_stop/timetable" },
      { icon: <PiNotePencil  style={{ color: 'black' }} size={20} />, to: "/planned_course_to_plan" },
      { icon: <GrSchedules style={{ color: 'black' }} size={20} />, to: "/planned_course" },
      { icon: <GiBusStop style={{ color: 'black' }} size={20} />, to: "/bus_stop" },
      { icon: <FaPersonMilitaryToPerson style={{ color: 'black' }} size={20} />, to: "/driver" },
      { icon: <FaBusAlt style={{ color: 'black' }} size={20} />, to: "/bus" },
      { icon: <LuShieldAlert style={{ color: 'black' }} size={20} />, to: "/incident" },
      { icon: <IoMdListBox style={{ color: 'black' }} size={20} />, to: "/dashboard" }
    ];

    const dispatcherItems = [
      { icon: <FaRoute style={{ color: 'black' }} size={20} />, to: "/course" },
      { icon: <TbBusStop style={{ color: 'black' }} size={20} />, to: "/bus_line" },
      { icon: <PiNotePencil  style={{ color: 'black' }} size={20} />, to: "/planned_course_to_plan" },
      { icon: <GrSchedules style={{ color: 'black' }} size={20} />, to: "/planned_course" },
      { icon: <GiBusStop style={{ color: 'black' }} size={20} />, to: "/bus_stop" },
      { icon: <FaPersonMilitaryToPerson style={{ color: 'black' }} size={20} />, to: "/driver" },
      { icon: <FaBusAlt style={{ color: 'black' }} size={20} />, to: "/bus" },
    ];

    if (userRole === 'ADMIN' || userRole === 'MANAGER') {
      return [...adminManagerItems, ...commonItems];
    } else if (userRole === 'DISPATCHER') {
      return [...dispatcherItems, ...commonItems];
    } else {
      return commonItems;
    }
  };

  const navItems = renderNavItems();

  return (
    <div className='fixed bg-white flex justify-between items-center mx-auto w-full z-10 pr-4 pl-12 border-b'>
      <h1 className="pl-4 bg-clip-text text-4xl font-black">SOPKoM</h1>
      <ul className='hidden md:flex gap-2 rounded-xl bg-white m-2'>
        {navItems.map((item, index) => (
          <NavItem key={index} icon={item.icon} to={item.to} />
        ))}
      </ul>
      <div onClick={handleNav} className='md:hidden z-10 m-2 rounded-xl bg-white border border-gray-500 hover:scale-90 duration-300 p-4'>
        {nav ? <AiOutlineClose style={{ color: 'black' }} size={20} /> : <AiOutlineMenu style={{ color: 'black' }} size={20} />}
      </div>
      <ul className={nav ? 'fixed md:hidden left-0 top-0 w-[12%] h-full bg-[#e0fbfc] ease-in-out duration-500 z-10 flex content-center items-center flex-col' : 'ease-in-out w-[15%] duration-500 fixed top-0 bottom-0 left-[-100%] z-10'}>
        {navItems.map((item, index) => (
          <NavItemMobile key={index} icon={item.icon} to={item.to} onClick={handleNav} />
        ))}
      </ul>
    </div>
  );
};

export default Navbar;
