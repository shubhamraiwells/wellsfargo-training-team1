import * as React from 'react';
import { useState } from 'react';
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import CameraIcon from '@mui/icons-material/PhotoCamera';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Link from '@mui/material/Link';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Withdrawal from './Withdrawal';
import Deposit from './Deposit';
import { Component ,useContext} from "react";
import DangerousIcon from '@mui/icons-material/Dangerous';
import TransactionalHistory from './TransactionalHistory';
import apiCall from '../apiCall/apiCall';
import { useToken } from '../context/TokenContext';
import Transfer from './Transfer';

function Copyright() {
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

export default function Features(props) {
  const [viewBal, setViewBal] = useState(false);
//const {token,role,username,isTokenValid}=useToken();
const [balAmt, setBalAmt] = useState(0);
const checkBal = async() =>{

  const url = "http://localhost:8080/GetTotalBalance";
  const obj = {"username":props.username};
  //const temp_token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYXR5YVNyZWVOYXJheWFuYW4yIiwiaWF0IjoxNjk1MDQ4NjA1LCJleHAiOjE2OTUwNTM2MDV9.2W_Neg49PEphekn-zjDjwMpBc8x9LXAQweSZYnr1fj0";
  console.log("Token:"+props.token);
  const result = await apiCall(url, "POST", obj, props.token);
  console.log(result.data);
  console.log("User:"+props.username);
  setBalAmt(result.data);
  setViewBal(true);
}

const viewTransactions = () =>{
  alert("Your previous transactions are: 1. *** 2. **");
}

const transferMoney = () =>{
  alert("Transferring Money");
}
// TODO remove, this demo shouldn't need to reset the theme.
const defaultTheme = createTheme();

  const [isWithdrawalModalOpen, setIsWithdrawalModelOpen] = useState(false);
  const [isDepositModalOpen, setIsDepositModelOpen] = useState(false);
  const [isTransactionalHistoryModalOpen, setIsTransactionalHistoryModalOpen] = useState(false);
  const [isTransferModalOpen, setIsTransferModalOpen] = useState(false);

  const handleOpenTransactionalHistoryModal = () => {
    setIsTransactionalHistoryModalOpen(true);
  };

  const handleCloseTransactionalHistoryModal = () => {
    setIsTransactionalHistoryModalOpen(false);
  };
  const [accountNumbers, setAccountNumbers] = useState(['Account 1', 'Account 2', 'Account 3']);

  const handleWithdraw = () => {
    // Add logic here to handle the successful withdrawal
  };

  const handleDeposit = () => {
    // Add logic here to handle the successful withdrawal
  };

  const handleTransfer = () => {
  }

    return (
    <ThemeProvider theme={defaultTheme}>
      <CssBaseline />
      <main>
        {/* Hero unit */}
        <Box
          sx={{
            bgcolor: 'background.paper',
            pt: 8,
            pb: 6,
          }}
        >
        </Box>
        <Container sx={{ py: 8 }} maxWidth="md">
          {/* End hero unit */}
          <Grid container spacing={6}>

            <Grid item key="1" xs={12} sm={6} md={4}>
            <Card
                sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
              >
              <CardMedia
                  component="div"
                  sx={{
                    // 16:9
                    pt: '56.25%',
                  }}
                  image="https://i.pinimg.com/236x/0b/96/48/0b9648211fb19c8d77e82f078094a0af.jpg"
                />
                  <CardContent sx={{ flexGrow: 1 }}>
                    {!viewBal && <Typography gutterBottom variant="h5" component="h2">
                      Check Your Balance
                    </Typography>
                    }
                    {viewBal && <Typography gutterBottom variant='h6' component='h3'>
                      Balance: {balAmt}</Typography>}
                  </CardContent>
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                    {!viewBal && <Button   style={{ background: "#101073" }} size="large" variant="contained" onClick={checkBal}>View</Button>}
                    {viewBal && <Button   style={{ background: "#101073" }} size="large" variant="contained" onClick={()=>{setViewBal(false)}}><DangerousIcon /></Button>}
                  </CardActions>
                </Card>
              </Grid>
              <Grid item key={2} xs={12} sm={6} md={4}>
            <Card
                sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
              >
              <CardMedia
                  component="div"
                  sx={{
                    // 16:9
                    pt: '56.25%',
                  }}
                  image="https://i.pinimg.com/236x/e5/cd/fe/e5cdfec959dd13ec0744de7eca7d1f22.jpg"
                />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      Transaction History
                    </Typography>
                  </CardContent>
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                    <Button style={{ background: "#101073" }} size="large" variant="contained" onClick={handleOpenTransactionalHistoryModal}>View</Button>
                    <TransactionalHistory isOpen={isTransactionalHistoryModalOpen} handleClose={handleCloseTransactionalHistoryModal} />
                  </CardActions>
                </Card>
              </Grid>
              <Grid item key={3} xs={12} sm={6} md={4}>
                <Card
                  sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                >
                   <CardMedia
                component="div"
                sx={{
                  // 16:9
                  pt: '56.25%',
                }}
                image="https://i.pinimg.com/236x/5f/6f/eb/5f6feb880b0692b6daa5200c292592f1.jpg"
              />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      Withdraw Money
                    </Typography>
                  </CardContent>
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                    <Button style={{ background: "#101073" }} size="large" variant="contained" onClick={() => setIsWithdrawalModelOpen(true)}>View</Button>
                    <Withdrawal isOpen={isWithdrawalModalOpen}
                      onClose={() => setIsWithdrawalModelOpen(false)}
                      accountNumbers={accountNumbers}
                      onWithdraw={handleWithdraw} />
                  </CardActions>
                </Card>
              </Grid>
              <Grid item key={4} xs={12} sm={6} md={4}>
                <Card
                  sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                >
                  <CardMedia
                component="div"
                sx={{
                  // 16:9
                  pt: '56.25%',
                }}
                image="https://i.pinimg.com/236x/3b/6f/86/3b6f8605934d9df8f021cd91eca560e9.jpg"
              />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      Deposit Money
                    </Typography>
                  </CardContent>
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                    <Button style={{ background: "#101073" }} size="large" variant="contained" onClick={() => setIsDepositModelOpen(true)}>View</Button>
                    <Deposit isOpen={isDepositModalOpen}
                      onClose={() => setIsDepositModelOpen(false)}
                      accountNumbers={accountNumbers}
                      onDeposit={handleDeposit} />
                  </CardActions>
                </Card>
              </Grid>
              <Grid item key={5} xs={12} sm={6} md={4}>
                <Card
                  sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                >
                   <CardMedia
                component="div"
                sx={{
                  // 16:9
                  pt: '56.25%',
                }}
                image="https://i.pinimg.com/564x/8c/52/fd/8c52fd4e6f3f6e497219d02e9750b2a2.jpg"
              />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      Transfer Money
                    </Typography>
                  </CardContent>
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                    <Button style={{ background: "#101073" }} size="large" variant="contained" onClick={() => setIsTransferModalOpen(true)}>View</Button>
                    <Transfer isOpen={isTransferModalOpen}
                      onClose={() => setIsTransferModalOpen(false)}
                      accountNumbers={accountNumbers}
                      onTransfer={handleTransfer} />
                  </CardActions>
                </Card>
              </Grid>
          </Grid>
        </Container>
      </main>
      {/* Footer */}
      <Box sx={{ bgcolor: 'background.paper', p: 6 }} component="footer">
        <Copyright />
      </Box>
      {/* End footer */}
    </ThemeProvider>
  );
}