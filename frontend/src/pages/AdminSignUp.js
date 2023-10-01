import React, { useState } from 'react';
import {
  Container,
  Typography,
  TextField,
  Button,
  Grid,
  CssBaseline,
  Paper,
  Avatar,
} from '@mui/material';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import apiCall from '../apiCall/apiCall';
import AdminNavbar from "./AdminNavbar";
import Notification from './Notification';
import { useNavigate, Navigate } from "react-router-dom";
import { useToken } from "../context/TokenContext";
const AdminSignIn = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');  
  const [isusernameEmpty, isusernameEmptyUpdate] = useState(false);
  const [ispasswordEmpty, ispasswordEmptyUpdate] = useState(false);
  const [error,setError] = useState('');
  const [isError, isErrorUpdate] = useState(false);
  const { setTokenWithExpiry,token, role, isTokenValid}=useToken();
  const navigate = useNavigate();
//  console.log(role)
  const handleUsernameChange = (e) => {
    // console.log(username)
    if (e.target.value == "" || e.target.value === null) {
      isusernameEmptyUpdate(true);
    } else {
      isusernameEmptyUpdate(false);
    }
    console.log(isusernameEmpty);
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    
    if (e.target.value == "" || e.target.value === null) {
      ispasswordEmptyUpdate(true);
    } else {
      ispasswordEmptyUpdate(false);
    }
    console.log(ispasswordEmpty);
    setPassword(e.target.value);
  };

  const [notificationOpen, setNotificationOpen] = useState(false);
  const [notificationMessage, setNotificationMessage] = useState('');
  const [notificationSeverity, setNotificationSeverity] = useState('success'); // Default severity

  const handleNotificationClose = () => {
    setNotificationOpen(false);
  };

  // Function to trigger the notification
  const showNotification = (message, severity) => {
    setNotificationMessage(message);
    setNotificationSeverity(severity);
    setNotificationOpen(true);
  };


  const handleSignIn = async () => {
    // Add your authentication logic here.
    const response=await apiCall(`${process.env.REACT_APP_DEVELOPMENT_URL}/api/auth/signinAdmin`,"POST",{
        username,
        password
    },null)
    console.log(response)
    if(response.data.token!=null){
        const { token, role, username } = response.data;
        
        setTokenWithExpiry(token, role, username);
        console.log(role);
        showNotification('Admin signin success', 'success')
        isErrorUpdate(false);
        // alert("Admin signin success");
        navigate("/Admin/Home");
   }else{
      showNotification('Do not have admin access', 'error')
      isErrorUpdate(true);
      setError("Admin access denied");
    }
  
  };

  return (
    <div className="container">
    {role==='ROLE_ADMIN' && <Navigate to='/ADMIN/Home' />}
    <Container component="main" maxWidth="xs" style={{marginTop:"10%"}} spacing={3}>
      <CssBaseline />
      <Notification
        open={notificationOpen}
        message={notificationMessage}
        severity={notificationSeverity}
        onClose={handleNotificationClose}
      />
      <Paper elevation={3}  sx={{ padding: 3, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
        <Avatar sx={{ m: 1, bgcolor: "#101073" }}>
          <LockOutlinedIcon style={{ background: "#101073" }}/>
        </Avatar>
        <Typography style={{marginTop:"5%"}} component="h1" variant="h5">
          Admin Sign In
        </Typography>
        <form noValidate>
          <Grid container spacing={3} style={{marginTop:"5%"}}>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Username"
                variant="outlined"
                value={username}
                onChange={handleUsernameChange}
                required
                error={isusernameEmpty}
              />
              <p
              style={{ color: "red", textAlign: "left", marginTop: 2,fontStyle:"italic",fontSize:12}}
              hidden={isusernameEmpty ? false : true}
            >
              username cannot be empty!
            </p>
            </Grid>
            <Grid item xs={12}>
              <TextField
                fullWidth
                label="Password"
                variant="outlined"
                type="password"
                value={password}
                onChange={handlePasswordChange}
                required
                error={ispasswordEmpty}
              />
              <p
              style={{ color: "red", textAlign: "left", marginTop: 2,fontStyle:"italic",fontSize:12}}
              hidden={ispasswordEmpty ? false : true}
            >
              Password cannot be empty!
            </p>
            </Grid>
          </Grid>
          <Button
            fullWidth
            variant="contained"
            color="primary"
            sx={{ mt: 3 }}
            onClick={()=>{              
              handleSignIn(); }}
            style={{ background: "#101073" }}
          >
            Sign In
          </Button>
          <Typography style={{ color: "red", textAlign: "left", marginTop: 2,fontStyle:"italic",fontSize:12,textAlign:'center' }} hidden={isError ? false : true} id="modal-modal-description" sx={{ mt: 2 }} dangerouslySetInnerHTML={{ __html: error }}/>
            
        </form>
      </Paper>
    </Container>
    </div>
  );
};

export default AdminSignIn;