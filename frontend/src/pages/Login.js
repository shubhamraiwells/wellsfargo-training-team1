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

export default function Login() {
const[username,usernameUpdate] = useState('');
const[password,passwordUpdate] = useState('');
  const handleSubmit = (event) => {
    let result=validate();
    if(result){
   event.preventDefault();
   let obj = {username,password};
   fetch('http://localhost:8080/loginIbAccount/',{
    method:"POST",
    headers:{'content-type':'application/json'},
    body:JSON.stringify(obj)
   }).then((res)=>{
    console.log("Registered Successfully")
   }).catch((err)=>{
    console.log(err.message)
   });
   alert("Successfully logged in");
  }
  else{
    alert("Some fields are empty");
  }
  };
const validate=()=>{
  let result = true;
  if(username==='' || username === null){
    result = false;
    console.log("username is empty");
  }
  if(password==='' || password === null){
    result = false;
    console.log("password is empty");
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
          Sign in
        </Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="username"
            label="Username"
            name="username"
            autoComplete="username"
            value={username}
            onChange={e=>usernameUpdate(e.target.value)}
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
          <FormControlLabel
            control={<Checkbox value="remember" color="primary" />}
            label="Remember me"
          />
          <Button 
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Sign In
          </Button>
          <Grid container>
            <Grid item xs>
              <Link href="#" variant="body2">
                Forgot password?
              </Link>
            </Grid>
            <Grid item>
              <Link href="/SignUp" variant="body2">
                {"Don't have an account? Sign Up"}
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
  
   
  );
}
