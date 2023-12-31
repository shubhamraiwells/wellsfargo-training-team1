import React, { Component, useState } from "react";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import RegisterAccount from "./pages/RegisterAccount";
import SignUp from "./pages/SignUp";
import HomePage from "./pages/HomePage";
import AdminServices from "./pages/AdminServices";
import AdminNavbar from "./pages/AdminNavbar";
import ApproveUsers from "./pages/ApproveUsers";
import {
  Context as UserContext,
  Provider as UserProvider,
} from "./context/AuthContext";
import { TokenProvider } from "./context/TokenContext";
import Services from "./pages/Services";
import Navbar from "./pages/NavBar";
import AdminSignIn from "./pages/AdminSignUp";
import AdminSearchUsers from "./pages/AdminSearchUsers";
import ForgotPassword from "./pages/ForgotPassword";
const App = () => {
  return (
    // <ProSidebarProvider>
    <div className="App">
      <BrowserRouter>
        <Routes>

          <Route path="/" element={<HomePage/>}></Route>  
          <Route path="/Admin/SearchUsers" element={<AdminSearchUsers />}></Route>
          <Route path="/SignUp" element={<SignUp />}></Route>
          <Route path="/RegisterAccount" element={<RegisterAccount />}></Route>
          <Route path="/Login" element={<Login />}></Route>
          <Route path="/services" element={<Services/>}></Route>
          <Route path="/Admin/Home" element={<AdminNavbar/>}></Route>
          <Route path="/User/Home" element={<Navbar/>}></Route>
          <Route path='/Admin/ApproveUsers' element={<ApproveUsers/>}></Route>
          <Route path="/Admin" element={<AdminSignIn/>}></Route>
          <Route path="/forgotpassword" element={<ForgotPassword/>}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default () => {
  return (
    <TokenProvider>
      <UserProvider>
        <App />
      </UserProvider>
    </TokenProvider>
  );
};
