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
import Home from "./Home.js";
import apiCall from "../apiCall/apiCall";

const userNameRegex=/^(?!.*\.\.)(?!.*\.$)[A-Za-z0-9_.]{8,20}$/;
const passwordRegex=/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{12,}$/;


export default function SignUp() {

 
  const [formData,setFormData]=useState({
    username:"",
    password:"",
    accountNo:""
});
const [isaccntnoEmpty,isaccntnoEmptyUpdate] = useState(false);
const [isusernameEmpty,isusernameEmptyUpdate] = useState(false);
const [ispasswordEmpty,ispasswordEmptyUpdate] = useState(false);


const handleSubmit = async (e) => {
    const url="http://localhost:8080/createIbAccount";
    e.preventDefault();
    const checkUsername=userNameRegex.test(formData.username);
    const checkPassword=passwordRegex.test(formData.password);
    const checkAccountNo=formData.accountNo.length>0?true:false;

    if(checkUsername && checkPassword && checkAccountNo){

    const result=await apiCall(url,"POST",formData,null);
    alert(result.data);
    }else{
        var res="";
        console.log(checkUsername+" "+checkPassword+" "+checkAccountNo)
        if(checkUsername===false){
            res=res.concat("invalid username\n");
        }
        if(checkPassword===false){
            res=res.concat("invalid password");
        }
        if(checkAccountNo===false){
            res=res.concat("invalid account number");
        }
        alert(res);
    }
  }

  return (
  
    <div className="container">
    <Container component="main" maxWidth="sm" 
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
          Sign Up
        </Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
        <TextField
          className="input"
            margin="normal"
            required
            fullWidth
            name="accoutnnumber"
            label="Account Number"
          
            id="accountnumber"
            value={formData.accountNo}
            onChange={(e)=>{
              if(e.target.value=='' || e.target.value===null){
                  isaccntnoEmptyUpdate(true);
              }
              else{
              isaccntnoEmptyUpdate(false);
              }
              console.log(isaccntnoEmpty);
              setFormData({...formData,accountNo:e.target.value})
              
          }}
          error={isaccntnoEmpty}
          />
            <p style={{color:"red", textAlign:"left", marginTop:2}} hidden={isaccntnoEmpty?false:true}>Account number cannot be empty!</p>
          <TextField
            className="input"
            margin="normal"
            required
            fullWidth
            id="username"
            label="username"
            name="username"
            autoComplete="username"
            value={formData.username}
            onChange={(e)=>{
              if(e.target.value=='' || e.target.value===null){
                  isusernameEmptyUpdate(true);
              }
              else{
              isusernameEmptyUpdate(false);
              }
              console.log(isusernameEmpty);
              setFormData({...formData,username:e.target.value})
              
          }}
          error={isusernameEmpty}
          autoFocus
          />
            <p style={{color:"red", textAlign:"left", marginTop:2}}  hidden={isusernameEmpty?false:true}>Username cannot be empty!</p>

          
          
          <TextField
            className="input"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            value={formData.password}
            onChange={(e)=>{
              if(e.target.value=='' || e.target.value===null){
                  ispasswordEmptyUpdate(true);
              }
              else{
              ispasswordEmptyUpdate(false);
              }
              console.log(ispasswordEmpty);
              setFormData({...formData,password:e.target.value})}
              
          }
          error={ispasswordEmpty}
          autoComplete="current-password"
          />
          <p style={{color:"red", textAlign:"left", marginTop:2}} hidden={ispasswordEmpty?false:true}>Password cannot be empty!</p>

       
      
      
          <Button 
            className="button"
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Sign Up
          </Button>
          <Grid container>
          
            <Grid item >
              <Link href="/Login" variant="body2"  style={{textDecoration:"none", marginLeft:120}}>
                {"Already have an account? Sign In"}
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
  </div>
   
  );
}
