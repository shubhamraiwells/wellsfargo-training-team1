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

export default function Album(props) {
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

  const handleOpenTransactionalHistoryModal = () => {
    setIsTransactionalHistoryModalOpen(true);
  };

  const handleCloseTransactionalHistoryModal = () => {
    setIsTransactionalHistoryModalOpen(false);
  };
  const [accountNumbers, setAccountNumbers] = useState(['Account 1', 'Account 2', 'Account 3']);

  const handleWithdraw = () => {
    // Add logic here to handle the successful withdrawal
    console.log('Withdrawal successful');
  };

  const handleDeposit = () => {
    // Add logic here to handle the successful withdrawal
    console.log('Deposit successful');
  };

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
        <Container sx={{ py: 2 }} maxWidth="md">
          {/* End hero unit */}
          <Grid container spacing={8}>
            {/* {cards.map((card) => ( */}
              <Grid item key={1} xs={12} sm={6} md={4} >
                <Card
                  sx={{ height: '100%', display: 'flex', flexDirection: 'column', border: '2px solid black' }}
                >
                  <CardMedia
                    component="div"
                    sx={{
                      // 16:9
                      pt: '56.25%',
                      objectFit: 'contain',
                      borderBottom: '1px solid black'
                    }}
                    image="https://im.indiatimes.in/content/2020/Jul/indian-currency-389006_1920_5f1547587ee6e.jpg"
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
                    {!viewBal && <Button size="large" variant="contained" onClick={checkBal}>View</Button>}
                    {viewBal && <Button size="large" variant="contained" onClick={()=>{setViewBal(false)}}><DangerousIcon /></Button>}
                  </CardActions>
                </Card>
              </Grid>
              <Grid item key={2} xs={12} sm={6} md={4}>
                <Card
                  sx={{ height: '100%', display: 'flex', flexDirection: 'column', border: '2px solid black' }}
                >
                  <CardMedia
                    component="div"
                    sx={{
                      // 16:9
                      pt: '56.25%',
                      objectFit: "contain",
                      borderBottom:'1px solid black'
                    }}
                    image="https://thumbs.dreamstime.com/z/transaction-history-icon-isolated-white-background-your-web-mobile-app-design-133862670.jpg?w=768"
                  />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      Transaction History
                    </Typography>
                  </CardContent>
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                    <Button size="large" variant="contained" onClick={handleOpenTransactionalHistoryModal}>View</Button>
                    <TransactionalHistory isOpen={isTransactionalHistoryModalOpen} handleClose={handleCloseTransactionalHistoryModal} />
                  </CardActions>
                </Card>
              </Grid>
              <Grid item key={3} xs={12} sm={6} md={4}>
                <Card
                  sx={{ height: '100%', display: 'flex', flexDirection: 'column', border: '2px solid black' }}
                >
                  <CardMedia
                    component="div"
                    sx={{
                      // 16:9
                      pt: '56.25%',
                      objectFit: "contain",
                      borderBottom:'1px solid black'
                    }}
                    image="data:image/png;base64,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"
                  />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      Withdraw Money
                    </Typography>
                  </CardContent>
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                    <Button size="large" variant="contained" onClick={() => setIsWithdrawalModelOpen(true)}>View</Button>
                    <Withdrawal isOpen={isWithdrawalModalOpen}
                      onClose={() => setIsWithdrawalModelOpen(false)}
                      accountNumbers={accountNumbers}
                      onWithdraw={handleWithdraw} />
                  </CardActions>
                </Card>
              </Grid>
              <Grid item key={4} xs={12} sm={6} md={4}>
                <Card
                  sx={{ height: '100%', display: 'flex', flexDirection: 'column', border: '2px solid black' }}
                >
                  <CardMedia
                    component="div"
                    sx={{
                      // 16:9
                      pt: '56.25%',
                      objectFit: "contain",
                      borderBottom:'1px solid black'
                    }}
                    image="data:image/png;base64,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"
                  />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      Deposit Money
                    </Typography>
                  </CardContent>
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                    <Button size="large" variant="contained" onClick={() => setIsDepositModelOpen(true)}>View</Button>
                    <Deposit isOpen={isDepositModalOpen}
                      onClose={() => setIsDepositModelOpen(false)}
                      accountNumbers={accountNumbers}
                      onDeposit={handleDeposit} />
                  </CardActions>
                </Card>
              </Grid>
              <Grid item key={5} xs={12} sm={6} md={4}>
                <Card
                  sx={{ height: '100%', display: 'flex', flexDirection: 'column', border: '2px solid black' }}
                >
                  <CardMedia
                    component="div"
                    sx={{
                      // 16:9
                      pt: '56.25%',
                      objectFit: "contain",
                      borderBottom:'1px solid black'
                    }}
                    image="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRi96ih86f3piohUO4F39byIxTOTg0n_BaVBQ&usqp=CAU"
                  />
                  <CardContent sx={{ flexGrow: 1 }}>
                    <Typography gutterBottom variant="h5" component="h2">
                      Transfer Money
                    </Typography>
                  </CardContent>
                  <CardActions sx={{display:'flex', justifyContent:'center'}}>
                    <Button size="large" variant="contained" onClick={transferMoney}>View</Button>
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