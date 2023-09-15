import React, { Component, useState, useContext } from "react";
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import apiCall from '../apiCall/apiCall';
import { Context } from "../context/AuthContext";

export default function TransactionalHistory(props) {
    const {transactionalHistory}=useContext(Context);
    const [formData, setFormData] = useState({
        accountNumber:"",
        startingDate:"",
        endingDate:""
    })
    const [isaccountNumberEmpty,isaccountNumberEmptyUpdate] = useState(false);
    const [isStartingDateEmpty,isStartingDateEmptyUpdate] = useState(false);
    const [isEndingDateEmpty,isEndingDateEmptyUpdate] = useState(false);

    const handleSubmit = async (e) => {
        const url = "http://localhost:8080/history";
        e.preventDefault();
        // const checkAccountNumber=accountNumberRegex.test(formData.accountNumber);
        // const checkPassword=passwordRegex.test(formData.password);
        // const checkAccountNo=formData.accountNo.length>0?true:false;

        // if(checkAccountNumber && checkPassword && checkAccountNo) {

        // }

        const result = await apiCall(url, "POST", formData, null);
        console.log(result);

    }

    function Copyright(props) {
      return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
          {'Copyright © '}
          <Link color="inherit" href="https://mui.com/">
            Your Website
          </Link>{' '}
          {new Date().getFullYear()}
          {'.'}
        </Typography>
      );
    }
    
    // TODO remove, this demo shouldn't need to reset the theme.
    
    const defaultTheme = createTheme();
    
      return (
        <ThemeProvider theme={defaultTheme}>
          <Container component="main" maxWidth="xs">
            <CssBaseline />
            <Box
              sx={{
                marginTop: 8,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
              }}
            >
              <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                {/* <LockOutlinedIcon />s */}
              </Avatar>
              <Typography component="h1" variant="h5">
                Transactional History
              </Typography>
              <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                <TextField 
                  className='input'
                  margin="normal"
                  required
                  fullWidth
                  id="accountNumber"
                  label="Account Number"
                  name="accountNumber"
                  autoComplete='accountNumber'
                  value={formData.accountNumber}
                  onChange={(e) => {
                    if(e.target.value=='' || e.target.value===null) {
                        isaccountNumberEmptyUpdate(true)
                    } else {
                        isaccountNumberEmptyUpdate(false)
                    }
                    console.log(isaccountNumberEmpty)
                    setFormData({...formData, accountNumber:e.target.value})
                  }}
                  error={isaccountNumberEmpty}
                  autoFocus
                />
                <TextField
                  className='input'
                  margin="normal"
                  required
                  fullWidth
                  name="startingDate"
                  label="Starting Date"
                  type="startingDate"
                  id="startingDate"
                  onChange={(e) => {
                    if(e.target.value == '' || e.target.value === null) {
                        isStartingDateEmptyUpdate(true);
                    } else {
                        isStartingDateEmptyUpdate(false);
                    }
                    console.log(isStartingDateEmpty)
                    setFormData({...formData, startingDate: e.target.value})
                  }}
                  error={isStartingDateEmpty}
                />
                <TextField
                  className='input'
                  margin="normal"
                  required
                  fullWidth
                  name="endingDate"
                  label="Ending Date"
                  type="endingDate"
                  id="endingDate"
                  onChange={(e) => {
                    if(e.target.value == '' || e.target.value === null) {
                        isEndingDateEmptyUpdate(true);
                    } else {
                        isEndingDateEmptyUpdate(false);
                    }
                    console.log(isEndingDateEmpty)
                    setFormData({...formData, endingDate: e.target.value})
                  }}
                  error={isEndingDateEmpty}
                />
                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 3, mb: 2 }}
                >
                  Get transactional history
                </Button>
              </Box>
            </Box>
            <Copyright sx={{ mt: 8, mb: 4 }} />
          </Container>
        </ThemeProvider>
      );
}


