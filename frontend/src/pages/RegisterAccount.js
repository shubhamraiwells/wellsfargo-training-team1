import React, { Component, useState } from "react";

import validator from 'validator';

import "./Style.css";

import Button from "@mui/material/Button";

import TextField from "@mui/material/TextField";
import InputLabel from '@mui/material/InputLabel';

import FormControlLabel from "@mui/material/FormControlLabel";

import Checkbox from "@mui/material/Checkbox";

import Link from "@mui/material/Link";

import Grid from "@mui/material/Grid";

import Box from "@mui/material/Box";

import Typography from "@mui/material/Typography";

import Container from "@mui/material/Container";

import Modal from '@mui/material/Modal';
import apiCall from "../apiCall/apiCall";
import NavBar from "./NavBar";
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { Navigate } from "react-router-dom";
export default function RegisterAccount() {

  const [redirect,setRedirect] = useState(false);

  const [title,titleUpdate] = useState('');

  const [firstName, firstNameUpdate] = useState('');

  const [middleName, middleNameUpdate] = useState('');

  const [lastName, lastNameUpdate] = useState('');

  const [fatherName, fatherNameUpdate] = useState('');

  const [mobileNo, mobileNoUpdate] = useState('');

  const [emailId, emailIdUpdate] = useState('');

  const [aadharCardNo, aadharCardNoUpdate] = useState('');

  const [dateOfBirth, dateOfBirthUpdate] = useState('');

  const [residentialAddressLine1, residentialAddressLine1Update] = useState('');

  const [residentialAddressLine2, residentialAddressLine2Update] = useState('');

  const [residentialLandmark, residentialLandmarkUpdate] = useState('');

  const [residentialCity, residentialCityUpdate] = useState('');

  const [residentialPincode, residentialPincodeUpdate] = useState('');

  const [permanentAddressLine1, permanentAddressLine1Update] = useState('');

  const [permanentAddressLine2, permanentAddressLine2Update] = useState('');

  const [permanentLandmark, permanentLandmarkUpdate] = useState('');

  const [permanentCity, permanentCityUpdate] = useState('');

  const [permanentPincode, permanentPincodeUpdate] = useState('');

  const [occupationType, occupationTypeUpdate] = useState('');

  const [sourceOfIncome, sourceOfIncomeUpdate] = useState('');

  const [grossAnnualIncome, grossAnnualIncomeUpdate] = useState('');

  const [accountType, accountTypeUpdate] = useState('');

  const [errMsg,setErrMsg] = useState('');
  const handleChange = (event) => {
    accountTypeUpdate(event.target.value);
  };
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const numberCheckRegex = /^[0-9]+$/
  const dobregex = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/
  const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
  };

  const handleSubmit = async (event) => {

    event.preventDefault();
    console.log(event.target)
    let result = validate();
    if (result) {
      const obj = { title, firstName, middleName, lastName, fatherName, mobileNo, emailId, aadharCardNo, dateOfBirth, residentialAddressLine1, residentialAddressLine2, residentialLandmark, residentialCity, residentialPincode, permanentAddressLine1, permanentAddressLine2, permanentLandmark, permanentCity, permanentPincode, occupationType, sourceOfIncome, grossAnnualIncome, accountType };
      result = await apiCall("http://localhost:8080/api/savingAccount/createSavingsAccount", "POST", obj, null);
      if(result.status==200){
      console.log(obj)
      console.log(result)
      setOpen(true)
      setErrMsg("Your request is being processed. Status will be notified at the earliest.");
      setTimeout(()=>setRedirect(true),5000)
    }
      else{
        alert("Some issue with registration, please try again");
      }
    }
    else {
      console.log("Not registered");
    }
  };

  const validate = () => {
    let msg = ""
   
    const mobile = numberCheckRegex.test(mobileNo);
    if(!mobile || mobileNo.length != 10){
    
      msg = msg.concat("Check your mobile number"+"<br>");
    }
    if (!(validator.isEmail(emailId))) {
      
      msg = msg.concat("Enter a valid Email"+"<br>");
    }
    const aadhar = numberCheckRegex.test(aadharCardNo);
    if(!aadhar || aadharCardNo.length != 12){
      
      msg = msg.concat("Check your Aadhar number"+"<br>");
    }
    const dob = dobregex.test(dateOfBirth);
    if(!dob){
      msg = msg.concat("DOB should be in dd/mm/yyyy format"+"<br>");
    }
    const checkPincode1 = numberCheckRegex.test(permanentPincode);
    if(!checkPincode1 || permanentPincode.length != 6){
      
      msg = msg.concat("Check your permanent Pincode"+"<br>");
    }
    const checkPincode2 = numberCheckRegex.test(residentialPincode);
    if(!checkPincode2 || residentialPincode.length != 6){
      
      msg = msg.concat("Check your residential Pincode"+"<br>");
    }
    const checkIncome = numberCheckRegex.test(grossAnnualIncome);
    if(!checkIncome){
      
      msg = msg.concat("Enter annual income in numbers"+"<br>");
    }
    if (firstName === '' || firstName === null || lastName === '' || lastName === null || fatherName === '' || fatherName === null || mobileNo === '' || mobileNo === null || emailId === '' || emailId === null || aadharCardNo === '' || aadharCardNo === null || dateOfBirth === '' || dateOfBirth === null
      || residentialAddressLine1 === '' || residentialAddressLine1 === null || residentialAddressLine2 === '' || residentialAddressLine2 === null || residentialLandmark === '' || residentialLandmark === null || residentialCity === '' || residentialCity === null ||
      permanentAddressLine1 === '' || permanentAddressLine1 === null || permanentAddressLine2 === '' || permanentAddressLine2 === null ||
      permanentLandmark === '' || permanentLandmark === null || permanentCity === '' || permanentCity === null || grossAnnualIncome === '' || grossAnnualIncome === null ||
      permanentPincode === '' || permanentPincode === null || accountType === '' || accountType === null || occupationType === '' || occupationType === null || sourceOfIncome === '' || sourceOfIncome === null) {
        
      msg = msg.concat("Some fields are empty"+"<br>");
    }
    if (msg.length==0) {
      
      console.log("returned true");
      return true;
    }
    else{
      console.log("returned alse");
      setOpen(true);
      setErrMsg(msg);
      return false;
     
    }

  }

  return (

    <div className="container">
       {redirect && <Navigate to='/'/>}
      <NavBar />
      <Container component="main" maxWidth="md" className="container" style={{ marginTop: "10%" }}>

        <div className="App-header">



        </div>

        <Box



          sx={{
            boxShadow: 3,

            borderRadius: 5,

            px: 1,

            py: 1,

            display: "flex",

            flexDirection: "column",

            alignItems: "center",
            background: "white",
          }}

        >



          <Box

            sx={{

              px: 4,

              py: 6,



              display: "flex",

              flexDirection: "column",

              alignItems: "center",

            }}

          >
            <Typography component="h1" variant="h5" className="title">

              User Registration Details

            </Typography>
            <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
                className="input"
                margin="normal"

               

                fullWidth

                id="title"

                label="Mr/Mrs/Ms"

                name="title"

                value={title}

                onChange={e => titleUpdate(e.target.value)}

                autoFocus

              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                id="fname"

                label="First Name"

                name="fname"

                value={firstName}

                onChange={e => firstNameUpdate(e.target.value)}

                autoFocus

              /><TextField
                className="input"
                margin="normal"

              

                fullWidth

                id="mname"

                label="Middle Name"

                name="mname"

                value={middleName}

                onChange={e => middleNameUpdate(e.target.value)}

                autoFocus

              /><TextField
                className="input"
                margin="normal"

                required

                fullWidth

                id="lname"

                label="Last Name"

                name="lname"

                value={lastName}

                onChange={e => lastNameUpdate(e.target.value)}

                autoFocus

              /><TextField
                className="input"
                margin="normal"

                required

                fullWidth

                id="fatherName"

                label="Father's Name"

                name="fatherName"



                value={fatherName}

                onChange={e => fatherNameUpdate(e.target.value)}

                autoFocus

              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                id="mobileNo"

                label="Mobile Number"

                name="mobileNo"

                inputProps={{ maxLength: 10 }}

                value={mobileNo}

                onChange={e => mobileNoUpdate(e.target.value)}

                autoFocus

              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="emailId"

                label="Email"

                type="email"

                id="emailId"

                value={emailId}
                onChange={e => emailIdUpdate(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="aadharCardNo"

                label="Aadhar Number"

                id="aadharCardNo"

                value={aadharCardNo}

                inputProps={{ maxLength: 12 }}
                onChange={e => aadharCardNoUpdate(e.target.value)}


              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="dateOfBirth"

                label="Date of Birth (dd/mm/yyyy)"



                id="dateOfBirth"

                value={dateOfBirth}

                onChange={e => dateOfBirthUpdate(e.target.value)}





              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="residentialAddressLine1"

                label="residential addressline 1"



                id="residentialAddressLine1"

                value={residentialAddressLine1}

                onChange={e => residentialAddressLine1Update(e.target.value)}



              />
              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                id="residentialAddressLine2"

                label="residential addressline 2"

                name="residentialAddressLine2"



                value={residentialAddressLine2}

                onChange={e => residentialAddressLine2Update(e.target.value)}

                autoFocus

              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="residentialLandmark"

                label="residential landmark"



                id="residentialLandmark"

                value={residentialLandmark}

                onChange={e => residentialLandmarkUpdate(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="residentialCity"

                label="residential city"



                id="residentialCity"

                value={residentialCity}

                onChange={e => residentialCityUpdate(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="residentialPincode"

                label="residential pincode"

                inputProps={{ maxLength: 6 }}

                id="residentialPincode"

                value={residentialPincode}

                onChange={e => residentialPincodeUpdate(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="permanentAddressLine1"

                label="permanent addressline 1"



                id="permanentAddressLine1"

                value={permanentAddressLine1}

                onChange={e => permanentAddressLine1Update(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="permanentAddressLine2"

                label="permanent addressline 2"



                id="permanentAddressLine2"

                value={permanentAddressLine2}

                onChange={e => permanentAddressLine2Update(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="permanentLandmark"

                label="permanent landmark"



                id="permanentLandmark"

                value={permanentLandmark}

                onChange={e => permanentLandmarkUpdate(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="permanentCity"

                label="permanent city"



                id="permanentCity"

                value={permanentCity}

                onChange={e => permanentCityUpdate(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="permanentPincode"

                label="permanent pincode"

                inputProps={{ maxLength: 6 }}

                id="permanentPincode"

                value={permanentPincode}

                onChange={e => permanentPincodeUpdate(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="occupationType"

                label="occupation type"



                id="occupationType"

                value={occupationType}

                onChange={e => occupationTypeUpdate(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="sourceOfIncome"

                label="source of income"



                id="sourceOfIncome"

                value={sourceOfIncome}

                onChange={e => sourceOfIncomeUpdate(e.target.value)}



              />

              <TextField
                className="input"
                margin="normal"

                required

                fullWidth

                name="grossAnnualIncome"

                label="gross annual income"


                id="grossAnnualIncome"

                value={grossAnnualIncome}

                onChange={e => grossAnnualIncomeUpdate(e.target.value)}

                inputProps={{ inputMode: 'numeric', pattern: '[0-9]*' }}



              />
              <FormControl fullWidth>
                <InputLabel id="demo-simple-select-label">Account Type</InputLabel>
                <Select
                  style={{ alignItems: 'right' }}
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={accountType}
                  label="Account Type"
                  onChange={handleChange}
                  className="input"
                >
                  <MenuItem value="Savings Account">Savings Account</MenuItem>
                  <MenuItem value="Checking Account">Checking Account</MenuItem>
                  <MenuItem value="Money Market Account">Money Market Account</MenuItem>
                  <MenuItem value="Joint Account">Joint Account</MenuItem>
                  <MenuItem value="Student Account">Student Account</MenuItem>
                  <MenuItem value="Custodial Account">Custodial Account</MenuItem>

                </Select>
              </FormControl>
              <Button
                fullWidth
                className="button"
                type="submit"
                style={{ background: "#101073" }}
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Submit
              </Button>
            </Box>
            <Modal
              open={open}
              onClose={handleClose}
              aria-labelledby="modal-modal-title"
              aria-describedby="modal-modal-description"
            >
              <Box sx={style}>
                <Typography style={{color:"red"}} id="modal-modal-title" variant="h6" component="h2">
                  Alert!
                </Typography>
                <Typography style={{color:"red"}} id="modal-modal-description" sx={{ mt: 2 }} dangerouslySetInnerHTML={{ __html: errMsg }}/>
                
              </Box>
            </Modal>

          </Box>

        </Box>
      </Container>

    </div>



  );

}

