import * as React from 'react';
import { useState } from 'react';
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import ApprovalIcon from '@mui/icons-material/Approval';
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
  export default function AdminServices(props) {
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
      console.log('Withdrawal successful');
    };
  
    const handleDeposit = () => {
      // Add logic here to handle the successful withdrawal
      console.log('Deposit successful');
    };
  
    const handleTransfer = () => {
      console.log('Transfer successful');
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
          <Container sx={{ py: 2 }} maxWidth="md">
            {/* End hero unit */}
            <Grid container spacing={8}>
              {/* {cards.map((card) => ( */}
                <Grid item key={1}  >
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
                       Approve User Requests
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
                <Grid item key={2}>
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