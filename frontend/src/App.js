import React, { Component, useState } from "react";
import "./App.css";
import {BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import RegisterAccount from './pages/RegisterAccount';
import SignUp from "./pages/SignUp";
import NavBar from "./pages/NavBar";
import Services from "./pages/Services";
export default function App() {
  
   return (
    
<div className="App">
  
  <BrowserRouter>
  <Routes>
  <Route path='/' element={<NavBar/>}></Route>
  <Route path='/' element={<Home/>}></Route>
  <Route path='/SignUp' element={<SignUp/>}></Route>
  <Route path='/RegisterAccount' element={<RegisterAccount/>}></Route>
  <Route path='/Login' element={<Login/>}></Route>
  <Route path="/Services" element ={<Services/>}></Route>  
  </Routes>
  </BrowserRouter>
  </div>
 
   );
}
