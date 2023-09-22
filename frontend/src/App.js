import React, { Component, useState } from "react";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import RegisterAccount from "./pages/RegisterAccount";
import SignUp from "./pages/SignUp";
// import { ProSidebarProvider } from "react-pro-sidebar";
import {
  Context as UserContext,
  Provider as UserProvider,
} from "./context/AuthContext";
import { TokenProvider } from "./context/TokenContext";
import Services from "./pages/Services";
import Navbar from "./pages/NavBar";
import AdminSignIn from "./pages/AdminSignUp";
import AdminSearchUsers from "./pages/AdminSearchUsers";
const App = () => {
  return (
    // <ProSidebarProvider>
    <div className="App">
      <BrowserRouter>
        <Routes>
        <Route path="/AdminSearchUsers" element={<AdminSearchUsers />}></Route>
          <Route path="/" element={<Navbar />}></Route>
          <Route path="/SignUp" element={<SignUp />}></Route>
          <Route path="/RegisterAccount" element={<RegisterAccount />}></Route>
          <Route path="/Login" element={<Login />}></Route>
          <Route path="/services" element={<Services/>}></Route>
          <Route path="/admin" element={<AdminSignIn/>}></Route>
        </Routes>
      </BrowserRouter>
    </div>
    // </ProSidebarProvider>
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
