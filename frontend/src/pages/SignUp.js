import React, { Component, useState } from "react";
import "./Login.css";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Home from "./Home.js";
export default function SignUp() {
    const[accountnumber,accountnumberUpdate] = useState('');
const[email,emailUpdate] = useState('');
const[password,passwordUpdate] = useState('');
  const handleSubmit = (event) => {
    let result=validate();
    if(result){
   event.preventDefault();
   let obj = {accountnumber,email,password};
   fetch('http://localhost:8000/newuser/',{
    method:"POST",
    headers:{'content-type':'application/json'},
    body:JSON.stringify(obj)
   }).then((res)=>{
    console.log("Registered Successfully")
   }).catch((err)=>{
    console.log(err.message)
   });
   alert("Successfully Registered");
  }
  else{
    alert("Some fields are empty");
  }
  };
const validate=()=>{
  let result = true;
  if(email==='' || email === null){
    result = false;
    console.log("Email is empty");
  }
  if(password==='' || password === null){
    result = false;
    console.log("password is empty");
  }
  if(accountnumber==='' || accountnumber === null){
    result = false;
    console.log("Account Number is empty");
  }
  return result;
}
  return (
  
    <Container component="main" maxWidth="sm">
       
      <div className="App-header">
          <Typography  component="h1" variant="h5">
          Welcome to WF NetBanking
        </Typography>
        </div>
      <Box 
        sx={{
          boxShadow: 3,
          borderRadius: 2,
          px: 4,
          py: 6,
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Typography component="h1" variant="h5">
          Sign Up
        </Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
        <TextField
            margin="normal"
            required
            fullWidth
            name="accoutnnumber"
            label="Account Number"
            type="number"
            id="accountnumber"
            value={accountnumber}
            onChange={e=>accountnumberUpdate(e.target.value)}

          />
          <TextField
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            autoComplete="email"
            value={email}
            onChange={e=>emailUpdate(e.target.value)}
            autoFocus
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            value={password}
            onChange={e=>passwordUpdate(e.target.value)}
            autoComplete="current-password"
          />
      
          <Button 
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Sign Up
          </Button>
          <Grid container>
          
            <Grid item >
              <Link href="/Login" variant="body2">
                {"Already have an account? Sign In"}
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
  
   
  );
}
