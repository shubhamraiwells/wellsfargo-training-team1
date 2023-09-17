import {BrowserRouter, Route, Routes,NavLink } from 'react-router-dom';
import Login from './Login';
import RegisterAccount from './RegisterAccount';
import SignUp from "./SignUp";
import React, { Component, useState } from "react";
import Typography from "@mui/material/Typography";
import { Sidebar, Menu, MenuItem, useProSidebar } from "react-pro-sidebar";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import PeopleOutlinedIcon from "@mui/icons-material/PeopleOutlined";
import ContactsOutlinedIcon from "@mui/icons-material/ContactsOutlined";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
import HistoryIcon from "@mui/icons-material/History"
import "./Style.css";

export default function Home() {
  
    

  const { collapseSidebar } = useProSidebar();

  return (
    <div className="container">
    <div id="app" style={({ height: "100vh" }, { display: "flex" })}>
      <Sidebar style={{ height: "100vh" }}>
        <Menu>
          <MenuItem
            icon={<MenuOutlinedIcon />}
            onClick={() => {
              collapseSidebar();
            }}
            style={{ textAlign: "center" }}
          >
            {" "}
            <h2>User Home</h2>
          </MenuItem>
          <MenuItem icon={<HomeOutlinedIcon />}><NavLink to="/RegisterAccount" style={{textDecoration:"none"}}>Open Savings Account</NavLink>Open Savings Account</MenuItem>
          <MenuItem icon={<PeopleOutlinedIcon />}><NavLink to="/SignUp" style={{textDecoration:"none"}}>Register for Net Banking</NavLink></MenuItem>
          <MenuItem icon={<ContactsOutlinedIcon />}><NavLink to="/Login" style={{textDecoration:"none"}}>Login to the portal</NavLink></MenuItem>
        
        </Menu>
        </Sidebar>
        
        <Typography component="h3" variant="h5" className="titlebar" >
      
          Welcome to WF Net Banking
       
      </Typography>
     
    </div>
    </div>
  );
}
