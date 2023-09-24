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

export default function RegisterAccount() {



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

  const [accountType, accountTypeUpdate] = React.useState('');

  const handleChange = (event) => {
    accountTypeUpdate(event.target.value);
  };
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

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
      const obj = { firstName, middleName, lastName, fatherName, mobileNo, emailId, aadharCardNo, dateOfBirth, residentialAddressLine1, residentialAddressLine2, residentialLandmark, residentialCity, residentialPincode, permanentAddressLine1, permanentAddressLine2, permanentLandmark, permanentCity, permanentPincode, occupationType, sourceOfIncome, grossAnnualIncome };
      result = await apiCall("http://localhost:8080//api/savingAccount/createSavingsAccount", "POST", obj, null);
      console.log(obj)
      console.log(result)
      alert("Registered Successfully");
    }
    else {
      console.log("Not registered");
    }
  };

  const validate = () => {
    let emailid = toString(emailIdUpdate);
    if (!(validator.isEmail(emailid))) {
      console.log('Enter a valid Email');
    }

    let result = true;

    if (firstName === '' || firstName === null || middleName === '' || middleName === null || lastName === '' || lastName === null || fatherName === '' || fatherName === null || mobileNo === '' || mobileNo === null || emailId === '' || emailId === null || aadharCardNo === '' || aadharCardNo === null || dateOfBirth === '' || dateOfBirth === null
      || residentialAddressLine1 === '' || residentialAddressLine1 === null || residentialAddressLine2 === '' || residentialAddressLine2 === null || residentialLandmark === '' || residentialLandmark === null || residentialCity === '' || residentialCity === null ||
      permanentAddressLine1 === '' || permanentAddressLine1 === null || permanentAddressLine2 === '' || permanentAddressLine2 === null ||
      permanentLandmark === '' || permanentLandmark === null || permanentCity === '' || permanentCity === null || grossAnnualIncome === '' || grossAnnualIncome === null ||
      permanentPincode === '' || permanentPincode === null || accountType === '' || accountType === null || occupationType === '' || occupationType === null || sourceOfIncome === '' || sourceOfIncome === null) {

      result = false;
      console.log("Some fields are empty");
    }
    if (!result) {
      setOpen(true);
      console.log("Some fields are empty");
    }
    return result;

  }

  return (

    <div className="container">

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

                required

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

                label="Date of Birth"



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
                  <MenuItem value={10}>Savings Account</MenuItem>
                  <MenuItem value={20}>Checking Account</MenuItem>
                  <MenuItem value={30}>Money Market Account</MenuItem>
                  <MenuItem value={40}>Joint Account</MenuItem>
                  <MenuItem value={50}>Student Account</MenuItem>
                  <MenuItem value={60}>Custodial Account</MenuItem>

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
                <Typography id="modal-modal-title" variant="h6" component="h2">
                  Alert!
                </Typography>
                <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                  Some fields are empty
                </Typography>
              </Box>
            </Modal>

          </Box>

        </Box>
      </Container>

    </div>



  );

}

