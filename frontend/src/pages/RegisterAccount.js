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

 

export default function RegisterAccount() {

 

const [firstname, firstnameUpdate] = useState('');

const [middlename , middlenameUpdate] = useState('');

const [lastname, lastnameUpdate] = useState('');

const [fathername, fathernameUpdate] = useState(''); 

const [mobileno, mobilenoUpdate] = useState('');

const [email, emailUpdate] = useState('');

const [aadharcardno, aadharcardnoUpdate] = useState('');

const [dob, dobUpdate] = useState('');

const [residentialaddressline1, residentialaddressline1Update] =  useState('');

const [residentialaddressline2, residentialaddressline2Update] = useState('');

const [residentiallandmark , residentiallandmarkUpdate] = useState('');

const [residentialcity , residentialcityUpdate] = useState('');

const [residentialpincode , residentialpincodeUpdate] = useState('');

const [permanentaddressline1 , permanentaddressline1Update] =  useState('');

const [permanentaddressline2 , permanentaddressline2Update] = useState('');

const [permanentlandmark , permanentlandmarkUpdate] = useState('');

const [permanentcity  , permanentcityUpdate] = useState('');

const [permanentpincode  , permanentpincodeUpdate] = useState('');

const [occupationtype , occupationtypeUpdate] = useState('');

const [sourceofincome , sourceofincomeUpdate] = useState('');

const [grossannualincome , grossannualincomeUpdate] =  useState('');

 

 


  const handleSubmit = (event) => {

   event.preventDefault();
let result = validate();
if(result){
   let obj = {firstname,middlename,lastname,fathername,mobileno,email,aadharcardno,dob,residentialaddressline1,residentialaddressline2,residentiallandmark,residentialcity,residentialpincode,permanentaddressline1,permanentaddressline2,permanentlandmark,permanentcity,permanentpincode,occupationtype,sourceofincome, grossannualincome};

   fetch('http://localhost:8000/RegisterAccount/',{

    method:"POST",

    headers:{'content-type':'application/json'},

    body:JSON.stringify(obj)

   }).then((res)=>{

    console.log("Registered Successfully")

   }).catch((err)=>{

    console.log(err.message)

   });
   alert("Registered Successfully");
}
else{
    console.log("Not registered");
}
  };

const validate=()=>{

  let result = true;

  if(firstname==='' || firstname === null){

    result = false;

    console.log("firstname is empty");

  }

if(middlename==='' || middlename === null){

    result = false;

    console.log("middlename is empty");

  }

if(lastname==='' || lastname === null){

   result = false;

    console.log("lastname is empty");

  }

if(fathername==='' || fathername === null){

    result = false;

    console.log("fathername is empty");

  }

  if(mobileno==='' || mobileno === null){

    result = false;

    console.log("mobileno is empty");

  }

if(email==='' || email === null){

    result = false;

    console.log("email is empty");

  }

if(aadharcardno==='' || aadharcardno === null){

    result = false;

    console.log("aadharcardno is empty");

  }

if(dob==='' || dob === null){

    result = false;

    console.log("dob is empty");

  }

if(mobileno==='' || mobileno === null){

    result = false;

    console.log("mobileno is empty");

  }

if(residentialaddressline1==='' || residentialaddressline1 === null){

    result = false;

    console.log("residentialaddressline1 is empty");

  }

if(residentialaddressline2==='' || residentialaddressline2 === null){

    result = false;

    console.log("residentialaddressline2 is empty");

  }

if(residentiallandmark==='' || residentiallandmark === null){

    result = false;

    console.log("residentiallandmark is empty");

  }

if(residentialcity==='' || residentialcity === null){

    result = false;

    console.log("residentialcity is empty");

  }

if(residentialpincode==='' || residentialpincode === null){

    result = false;

    console.log("residentialpincode is empty");

  }

if(permanentaddressline1==='' || permanentaddressline1 === null){

    result = false;

    console.log("residentialpincode is empty");

  }

if(permanentaddressline2==='' || permanentaddressline2 === null){

    result = false;

    console.log("permanentaddressline2 is empty");

  }

if(permanentlandmark==='' || permanentlandmark === null){

    result = false;

    console.log("permanentlandmark is empty");

  }

if(permanentcity==='' || permanentcity === null){

    result = false;

    console.log("permanentcity is empty");

  }

if(permanentpincode==='' || permanentpincode === null){

    result = false;

    console.log("permanentpincode is empty");

  }

if(occupationtype==='' || occupationtype === null){

    result = false;

    console.log("occupationtype is empty");

  }

if(sourceofincome==='' || sourceofincome === null){

    result = false;

    console.log("sourceofincome is empty");

  }

if(grossannualincome==='' || grossannualincome === null){

    result = false;

    console.log("grossannualincome is empty");

  }
if(!result)
{alert("Some fields are empty");}  
return result;

}

  return (

  

    <Container component="main" maxWidth="lg">

      <div className="App-header">

          <Typography  component="h1" variant="h5" style={{marginTop:4}}>

        User Registration Details

        </Typography>

        </div>

        <Box

      

        sx={{


            borderRadius: 2,

            px: 1,

            py: 1,

            display: "flex",

            flexDirection: "column",

            alignItems: "center",

          }}

        >
            
    

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


        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>

<TextField

            margin="normal"

            required

            fullWidth

            id="fname"

            label="First Name"

            name="fname"

            value={firstname}

            onChange={e=>firstnameUpdate(e.target.value)}

            autoFocus

          /><TextField

            margin="normal"

            required

            fullWidth

            id="mname"

            label="Middle Name"

            name="mname"

            value={middlename}

            onChange={e=>middlenameUpdate(e.target.value)}

            autoFocus

          /><TextField

            margin="normal"

            required

            fullWidth

            id="lname"

            label="Last Name"

            name="lname"

            value={lastname}

            onChange={e=>lastnameUpdate(e.target.value)}

            autoFocus

          /><TextField

            margin="normal"

            required

            fullWidth

            id="fathername"

            label="Father's Name"

            name="fathername"

           

            value={fathername}

            onChange={e=>fathernameUpdate(e.target.value)}

            autoFocus

          />

          <TextField

            margin="normal"

            required

            fullWidth

            id="mobileno"

            label="Mobile Number"

            name="mobileno"

          

            value={mobileno}

            onChange={e=>mobilenoUpdate(e.target.value)}

            autoFocus

          />

          <TextField

            margin="normal"

            required

            fullWidth

            name="email"

            label="Email"

            type="email"

            id="email"

            value={email}

            onChange={e=>emailUpdate(e.target.value)}

          

          />

     <TextField

            margin="normal"

            required

            fullWidth

            name="aadharcardno"

            label="Aadhar Number"

            id="aadharcardno"

            value={aadharcardno}

            onChange={e=>aadharcardnoUpdate(e.target.value)}

          

          />

<TextField

            margin="normal"

            required

            fullWidth

            name="dob"

            label="Date of Birth"

        

            id="dob"

            value={dob}

            onChange={e=>dobUpdate(e.target.value)}

          

          />

<TextField

            margin="normal"

            required

            fullWidth

            name="residentialaddressline1"

            label="residential addressline 1"

          

            id="residentialaddressline1"

            value={residentialaddressline1}

            onChange={e=>residentialaddressline1Update(e.target.value)}

          

          />

       

    

     

          <TextField

            margin="normal"

            required

            fullWidth

            id="residentialaddressline2"

            label="residential addressline 2"

            name="residentialaddressline2"

           

            value={residentialaddressline2}

            onChange={e=>residentialaddressline2Update(e.target.value)}

            autoFocus

          />

          <TextField

            margin="normal"

            required

            fullWidth

            name="residentiallandmark"

            label="residential landmark"

 

            id="residentiallandmark"

            value={residentiallandmark}

            onChange={e=>residentiallandmarkUpdate(e.target.value)}

         

          />

    <TextField

            margin="normal"

            required

            fullWidth

            name="residentialcity"

            label="residential city"

 

            id="residentialcity"

            value={residentialcity}

            onChange={e=>residentialcityUpdate(e.target.value)}

         

          />

<TextField

            margin="normal"

            required

            fullWidth

            name="residentialpincode"

            label="residential pincode"

 

            id="residentialpincode"

            value={residentialpincode}

            onChange={e=>residentialpincodeUpdate(e.target.value)}

         

          />

     <TextField

            margin="normal"

            required

            fullWidth

            name="permanentaddressline1"

            label="permanent addressline 1"

 

            id="permanentaddressline1"

            value={permanentaddressline1}

            onChange={e=>permanentaddressline1Update(e.target.value)}

         

          />

<TextField

            margin="normal"

            required

            fullWidth

            name="permanentaddressline2"

            label="permanent addressline 2"

 

            id="permanentaddressline2"

            value={permanentaddressline2}

            onChange={e=>permanentaddressline2Update(e.target.value)}

         

          />

<TextField

            margin="normal"

            required

            fullWidth

            name="permanentlandmark"

            label="permanent landmark"

 

            id="permanentlandmark"

            value={permanentlandmark}

            onChange={e=>permanentlandmarkUpdate(e.target.value)}

         

          />   

     <TextField

            margin="normal"

            required

            fullWidth

            name="permanentcity"

            label="permanent city"

 

            id="permanentcity"

            value={permanentcity}

            onChange={e=>permanentcityUpdate(e.target.value)}

         

          /> 

<TextField

            margin="normal"

            required

            fullWidth

            name="permanentpincode"

            label="permanent pincode"

 

            id="permanentpincode"

            value={permanentpincode}

            onChange={e=>permanentpincodeUpdate(e.target.value)}

         

          /> 

<TextField

            margin="normal"

            required

            fullWidth

            name="occupationtype"

            label="occupation type"

 

            id="occupationtype"

            value={occupationtype}

            onChange={e=>occupationtypeUpdate(e.target.value)}

         

          />

<TextField

            margin="normal"

            required

            fullWidth

            name="sourceofincome"

            label="source of income"

 

            id="sourceofincome"

            value={sourceofincome}

            onChange={e=>sourceofincomeUpdate(e.target.value)}

         

          />

<TextField

            margin="normal"

            required

            fullWidth

            name="grossannualincome"

            label="gross annual income"

 

            id="grossannualincome"

            value={grossannualincome}

            onChange={e=>grossannualincomeUpdate(e.target.value)}

         

          />    
<Button

type="submit"



variant="contained"

sx={{ mt: 3, mb: 2 }}

>

Submit

</Button>
        </Box>

        </Box>
        
        </Box>
    </Container>

 

   

  );

}

 