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
import { useToken } from '../context/TokenContext';
import AdminNavbar from "./AdminNavbar";
import {useNavigate} from "react-router-dom"
const AdminSignIn = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');  
  const { setTokenWithExpiry } = useToken();  
  const navigate = useNavigate();

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSignIn = async () => {
    // Add your authentication logic here.
    const response=await apiCall("http://localhost:8080/api/auth/signinAdmin","POST",{
        username,
        password
    },null)
    console.log(response)
    if(response.data.token!=null){
        const { token, role, username } = response.data;
        setTokenWithExpiry(token, username, role);
        console.log(role);
        alert("Admin signin success");                   
        navigate("/Admin/Home");
    }else{
        alert("do not have admin access")
    }

  };

  return (
    <Container component="main" maxWidth="xs" style={{marginTop:"10%"}} spacing={3}>
      <CssBaseline />
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
              />
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
              />
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
        </form>
      </Paper>
    </Container>
  );
};

export default AdminSignIn;