import AdminNavbar from "./AdminNavbar";
import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import UserRequestsTable from "./UserRequestTable";
import { useEffect } from "react";
import apiCall from "../apiCall/apiCall";
import { useState} from 'react';
import { useToken } from "../context/TokenContext";
import {useNavigate} from "react-router-dom"
export default function ApproveUsers(){
    const [requests, setRequests] = useState([]);
    const [returned, setReturned] = useState(false);
    const fetchRequests = async() =>{
        const response= await apiCall("http://localhost:8080/api/auth/getPendingRequests","GET",{});
        setRequests(response.data);
        setReturned(true);
        console.log("Hi");
        console.log(response.data);

    }
    useEffect(()=>{
            fetchRequests();
    },[]);
    const {token,role,username,isTokenValid}=useToken();
    console.log(username);
    return(
    <div>
    {returned && <UserRequestsTable requests={requests} />}
    <AdminNavbar/>
    </div> 
    ); 
}