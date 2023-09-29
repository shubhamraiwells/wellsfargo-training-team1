import React, { Component, useState, useContext } from "react";
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
import jwtDecode from "jwt-decode";
import Cookies from "js-cookie";
import { Context } from "../context/AuthContext";
import NavBar from "./NavBar";
import { useNavigate, Navigate } from "react-router-dom";
import { useToken } from "../context/TokenContext";
//const userNameRegex=/^(?!.*\.\.)(?!.*\.$)[A-Za-z0-9_.]{8,20}$/;
//const passwordRegex=/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{12,}$/;

const Login = () => {
  const { signIn } = useContext(Context);
  const { setTokenWithExpiry,token, role, isTokenValid } = useToken();
  const [username, usernameUpdate] = useState("");
  const [password, passwordUpdate] = useState("");
  const [isusernameEmpty, isusernameEmptyUpdate] = useState(false);
  const [ispasswordEmpty, ispasswordEmptyUpdate] = useState(false);
  const [error, setError] = useState('');
  const [isError, isErrorUpdate] = useState(false);
  // console.log(jwtDecode('eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaHViaGFtcmFpIiwiaWF0IjoxNjk0NjE4OTgxLCJleHAiOjE2OTQ2MjM5ODF9.9vQkEkTPfGsK72afYPIpQm59ar3L9Ah2Kuq4kRJAeVo'));
  let navigate = useNavigate();
  const [redirect, setRedirect] = useState(false);
  const handleSubmit = async (e) => {
    const obj = { username, password };
    const url = `${process.env.REACT_APP_DEVELOPMENT_URL}/api/auth/signinIb`;
    e.preventDefault();
    if (validate()) {
      
      try {
        var response= await apiCall(url,"POST",obj,null);
        if (response.status === 200) {

          if (response && response.data) {
            const { token, role, username } = response.data;
            console.log(token, role, username)
            setTokenWithExpiry(token, role, username);
            isErrorUpdate(false);
            setRedirect(true);
          }
        }
      } catch (error) {
        console.log(error)
        isErrorUpdate(true);
      setError(error.response.data)
    
      }
    } else {
      isErrorUpdate(true);
      setError("Not logged in, some fields are empty");

    }
  };
  const validate = () => {
    let result = true;
    if (username === "" || username === null) {
      result = false;
    }
    if (password === "" || password === null) {
      result = false;
    }
    return result;
  };

  const style = {
    "& label.Mui-focused": {
      borderColor: "rgba(34,193,195,0.7)",
    },
  };
  return (
    <div className="container">
      {redirect && <Navigate to='/Services' />}
      {role==='ROLE_USER' && <Navigate to='/Services' />}
      <NavBar />
      <Container maxWidth="sm" className="container" style={{ marginTop: "10%" }} >
        <Box
          sx={{
            boxShadow: 3,
            borderRadius: 5,
            px: 4,
            py: 6,

            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            background: "white",
          }}
        >
          <Typography component="h1" variant="h5" className="title">
            Sign in
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
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
              onChange={(e) => {
                if (e.target.value == "" || e.target.value === null) {
                  isusernameEmptyUpdate(true);
                } else {
                  isusernameEmptyUpdate(false);
                }
                console.log(isusernameEmpty);
                usernameUpdate(e.target.value);
              }}
              error={isusernameEmpty}
            />
            <p
              style={{ color: "red", textAlign: "left", marginTop: 2, fontStyle: "italic", fontSize: 12 }}
              hidden={isusernameEmpty ? false : true}
            >
              username cannot be empty!
            </p>

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
              onChange={(e) => {
                if (e.target.value == "" || e.target.value === null) {
                  ispasswordEmptyUpdate(true);
                } else {
                  ispasswordEmptyUpdate(false);
                }
                console.log(ispasswordEmpty);
                passwordUpdate(e.target.value);
              }}
              error={ispasswordEmpty}
            />
            <p
              style={{ color: "red", textAlign: "left", marginTop: 2, fontStyle: "italic", fontSize: 12 }}
              hidden={ispasswordEmpty ? false : true}
            >
              Password cannot be empty!
            </p>
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
              style={{ background: "#101073" }}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item>
                <Link
                  href="/forgotpassword"
                  variant="body2"
                  style={{ textDecoration: "none" }}
                >
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <Link
                  href="/SignUp"
                  variant="body2"
                  style={{ textDecoration: "none", marginLeft: 150 }}
                >
                  {"Don't have an account? Sign Up"}
                </Link>

              </Grid>

            </Grid>
            <Typography style={{ color: "red", marginTop: 2, fontStyle: "italic", fontSize: 12, textAlign: 'center' }} hidden={isError ? false : true} id="modal-modal-description" sx={{ mt: 2 }} dangerouslySetInnerHTML={{ __html: error }} />

          </Box>
        </Box>
      </Container>
    </div>
  );
};
export default Login;
