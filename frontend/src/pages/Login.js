import React, { Component, useState } from "react";
import "./Style.css";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import apiCall from "../apiCall/apiCall";
import NavBar from "./NavBar";
//const userNameRegex=/^(?!.*\.\.)(?!.*\.$)[A-Za-z0-9_.]{8,20}$/;
//const passwordRegex=/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{12,}$/;


const Login = () => {
const[username,usernameUpdate] = useState('');
const[password,passwordUpdate] = useState('');
const [isusernameEmpty,isusernameEmptyUpdate] = useState(false);
const [ispasswordEmpty,ispasswordEmptyUpdate] = useState(false);
const obj = {username,password};

const handleSubmit = async (e) => {
  const url="http://localhost:8080/loginIbAccount";
  e.preventDefault();

  if(validate()){

  const result=await apiCall(url,"POST",obj,null);
  alert("Logged in successfully");
  }else{
      alert("Not logged in, some fields are empty");
  }
}
const validate=()=>{
  let result = true;
  if(username==='' || username === null){
    result = false;
  }
  if(password==='' || password === null){
    result = false;
  }
  return result;
}

const style = {"& label.Mui-focused":{
  borderColor : 'rgba(34,193,195,0.7)'
}}
  return (
  <div className="container">
    <NavBar/>
    <Container maxWidth="sm" 
   className="container" 
    >
      <Box 
        sx={{
       
          boxShadow: 3,
          borderRadius: 5,
          px: 4,
          py: 6,
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
      background : "white",
      
        }}
      >
        <Typography component="h1" variant="h5" className="title">
          Sign in
        </Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
          sx={style}
          className="input"
            margin="normal"
            required
            fullWidth
            id="username"
            label="username"
            name="username"
            autoComplete="username"
            value={username}
            onChange={(e)=>{
              if(e.target.value=='' || e.target.value===null){
                  isusernameEmptyUpdate(true);
              }
              else{
              isusernameEmptyUpdate(false);
              }
              console.log(isusernameEmpty);
              usernameUpdate(e.target.value);
              
          }}
          error={isusernameEmpty}
          />
          <p style={{color:"red", textAlign:"left", marginTop:2}} hidden={isusernameEmpty?false:true}>username cannot be empty!</p>
         
          <TextField
          sx={style}
            className="input"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            value={password}
            
            onChange={(e)=>{
              if(e.target.value=='' || e.target.value===null){
                  ispasswordEmptyUpdate(true);
              }
              else{
              ispasswordEmptyUpdate(false);
              }
              console.log(ispasswordEmpty);
              passwordUpdate(e.target.value);
              
          }}
          error={ispasswordEmpty}
          />
          <p style={{color:"red", textAlign:"left", marginTop:2}} hidden={ispasswordEmpty?false:true}>Password cannot be empty!</p>
          <FormControlLabel 
            control={<Checkbox value="remember" color="primary" />}
            label="Remember me"
          />
          <Button 
            className="button"
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Sign In
          </Button>
          <Grid container>
            <Grid item>
              <Link href="#" variant="body2" style={{textDecoration:"none"}}>
                Forgot password?
              </Link>
            </Grid>
            <Grid item>
              <Link href="/SignUp" variant="body2" style={{textDecoration:"none",marginLeft:150}}>
                {"Don't have an account? Sign Up"}
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
    </div>
  );
}
export default Login;