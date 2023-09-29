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
import { useNavigate } from "react-router-dom"
import Link from "@mui/material/Link"
const ForgotPassword = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [isusernameEmpty, isusernameEmptyUpdate] = useState(false);
    const [ispasswordEmpty, ispasswordEmptyUpdate] = useState(false);
    const [error, setError] = useState('');
    const [isError, isErrorUpdate] = useState(false);

    const { setTokenWithExpiry } = useToken();
    const navigate = useNavigate();
//    console.log(process.env.REACT_APP_DEVELOPMENT_URL)
    const handleUsernameChange = (e) => {
        if (e.target.value == "" || e.target.value === null) {
            isusernameEmptyUpdate(true);
        } else {
            isusernameEmptyUpdate(false);
        }
        console.log(isusernameEmpty);
        setUsername(e.target.value);
    };



    const handleSignIn = async () => {

        const response = await apiCall(`${process.env.REACT_APP_DEVELOPMENT_URL}/api/auth/forgotpassword?username=${username}`, "GET", {}, null)
        console.log(response)

    };

    return (
        <Container component="main" maxWidth="xs" style={{ marginTop: "10%" }} spacing={3}>
            <CssBaseline />
            <Paper elevation={3} sx={{ padding: 3, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                <Avatar sx={{ m: 1, bgcolor: "#101073" }}>
                    <LockOutlinedIcon style={{ background: "#101073" }} />
                </Avatar>
                <Typography style={{ marginTop: "5%" }} component="h1" variant="h5">
                    Forgot Password
                </Typography>
                <form noValidate>
                    <Grid container spacing={3} style={{ marginTop: "5%" }}>
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
                                style={{ color: "red", textAlign: "left", marginTop: 2, fontStyle: "italic", fontSize: 12 }}
                                hidden={isusernameEmpty ? false : true}
                            >
                                username cannot be empty!
                            </p>
                        </Grid>
                    </Grid>
                    <Button
                        fullWidth
                        variant="contained"
                        color="primary"
                        sx={{ mt: 3 }}
                        onClick={() => {
                            handleSignIn();
                        }}
                        style={{ background: "#101073" }}
                    >
                        Get New Password
                    </Button>
                    <Typography style={{ color: "red", textAlign: "left", marginTop: 2, fontStyle: "italic", fontSize: 12, textAlign: 'center' }} hidden={isError ? false : true} id="modal-modal-description" sx={{ mt: 2 }} dangerouslySetInnerHTML={{ __html: error }} />

                </form>
            </Paper>
            <Grid item>
                <Link
                    href="/SignUp"
                    variant="body2"
                    style={{ textDecoration: "none", marginLeft: 150 }}
                >
                    {"Don't have an account? Sign Up"}
                </Link>

            </Grid>
        </Container>
    );
};

export default ForgotPassword;