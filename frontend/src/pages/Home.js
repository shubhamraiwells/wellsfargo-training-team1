import {BrowserRouter, Route, Routes,NavLink } from 'react-router-dom';
import Login from './Login';
import RegisterAccount from './RegisterAccount';
import InternetBankingSignUp from "./SignUp";
import React, { Component, useState } from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import Divider from "@mui/material/Divider";
import Drawer from "@mui/material/Drawer";
import IconButton from "@mui/material/IconButton";
import InboxIcon from "@mui/icons-material/MoveToInbox";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import MailIcon from "@mui/icons-material/Mail";
import MenuIcon from "@mui/icons-material/Menu";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import { Sidebar, Menu, MenuItem, useProSidebar } from "react-pro-sidebar";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import PeopleOutlinedIcon from "@mui/icons-material/PeopleOutlined";
import ContactsOutlinedIcon from "@mui/icons-material/ContactsOutlined";
import ReceiptOutlinedIcon from "@mui/icons-material/ReceiptOutlined";
import CalendarTodayOutlinedIcon from "@mui/icons-material/CalendarTodayOutlined";
import HelpOutlineOutlinedIcon from "@mui/icons-material/HelpOutlineOutlined";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";

export default function Home() {
  
    

  const { collapseSidebar } = useProSidebar();

  return (
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
          <MenuItem icon={<HomeOutlinedIcon />}><NavLink to="/RegisterAccount">Open Savings Account</NavLink>Open Savings Account</MenuItem>
          <MenuItem icon={<PeopleOutlinedIcon />}><NavLink to="/SignUp">Register for Net Banking</NavLink></MenuItem>
          <MenuItem icon={<ContactsOutlinedIcon />}><NavLink to="/Login">Login to the portal</NavLink></MenuItem>
        
        </Menu>
        </Sidebar>
        
        <Typography component="h1" variant="h5">
      
          Welcome to WF Net Banking
       
      </Typography>
     
    </div>
  );
}
