import React, { Component, useState, useContext } from "react";
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import apiCall from '../apiCall/apiCall';
import { Context } from "../context/AuthContext";
import BankingTransactionsTable from "./BankingTransactionsTable";
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';

const usernameRegex=/^(?!.*\.\.)(?!.*\.$)[A-Za-z0-9_.]{8,20}$/;

const dummyTransactions = [
  {
    transactionId: 'tx123',
    senderAddress: '1234567890',
    receiverAddress: '9876543210',
    amount: 1000,
    date: '2023-09-15',
  },
  {
    transactionId: 'tx124',
    senderAddress: '9876543210',
    receiverAddress: '1234567890',
    amount: -500,
    date: '2023-09-14',
  },
  {
    transactionId: 'tx125',
    senderAddress: '1234567890',
    receiverAddress: '7890123456',
    amount: 200,
    date: '2023-09-13',
  },
  {
    transactionId: 'tx126',
    senderAddress: '7890123456',
    receiverAddress: '1234567890',
    amount: 300,
    date: '2023-09-12',
  },
  {
    transactionId: 'tx127',
    senderAddress: '1234567890',
    receiverAddress: '5678901234',
    amount: -100,
    date: '2023-09-11',
  },
  {
    transactionId: 'tx128',
    senderAddress: '5678901234',
    receiverAddress: '1234567890',
    amount: 800,
    date: '2023-09-10',
  },
  {
    transactionId: 'tx129',
    senderAddress: '1234567890',
    receiverAddress: '3456789012',
    amount: -250,
    date: '2023-09-09',
  },
  {
    transactionId: 'tx130',
    senderAddress: '3456789012',
    receiverAddress: '1234567890',
    amount: 150,
    date: '2023-09-08',
  },
];



export default function TransactionalHistory(props) {
  const { isOpen, handleClose } = props;
  
  const [isAccountNoEmpty, isAccountNoEmptyUpdate] = useState(false);
  const [isUsernameEmpty, isUsernameEmptyUpdate] = useState(false);
  const [isStartingDateEmpty,isStartingDateEmptyUpdate] = useState(false);
  const [isEndingDateEmpty,isEndingDateEmptyUpdate] = useState(false);
  const [isBankingTransactionTableOpen, setIsBankingTransactionTableOpen] = useState(false);
  const [show, setShow] = useState(false);
  const handleCloseBankingTransactionTable = () => {
    setIsBankingTransactionTableOpen(false);
  }

    const [formData, setFormData] = useState({
        username:"",
        accountNo:"",
        startingDate:"",
        endingDate:""
    })

    // const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const handleSubmit = async (e) => {
        const url = "http://localhost:8080/history";
        e.preventDefault();
        const checkUsername=usernameRegex.test(formData.accountNumber);
        const checkAccountNo=formData.accountNo.length>4?true:false;

        if(checkUsername && checkAccountNo) {
            // const result = await apiCall(url, "GET", formData, null);
            setIsBankingTransactionTableOpen(true);
            // console.log(result);
        } else {
            var res = "";
            if(!checkUsername) {
                res = res.concat("Invalid username\n");
            }
            if (!checkAccountNo) {
                res = res.concat("Invalid account number");
            }
            alert(res);
        }

        handleShow();
    }

    function convertDate(inputFormat) {
        function pad(s) { return (s < 10) ? '0' + s : s; }
        var d = new Date(inputFormat)
        return [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/')
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
        <Dialog open={isOpen} onClose={handleClose}>
        <DialogContent>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
      <TextField
        className='input'
        margin="normal"
        required
        fullWidth
        name="username"
        label="Username"
        type="text"
        id="username"
        onChange={(e) => {
          if(e.target.value == '' || e.target.value === null) {
              isUsernameEmptyUpdate(true);
          } else {
              isUsernameEmptyUpdate(false);
          }
          setFormData({...formData, username: e.target.value})
        }}
        error={isUsernameEmpty}
      />
      <TextField
        className='input'
        margin="normal"
        required
        fullWidth
        name="accountNo"
        label="Account Number"
        type="text"
        id="accountNo"
        onChange={(e) => {
          if(e.target.value == '' || e.target.value === null) {
              isAccountNoEmptyUpdate(true);
          } else {
              isAccountNoEmptyUpdate(false);
          }
          setFormData({...formData, accountNo: e.target.value})
        }}
        error={isAccountNoEmpty}
      />
      <TextField
        className='input'
        margin="normal"
        required
        fullWidth
        name="startingDate"
        label="Starting Date"
        type="date"
        id="startingDate"
        onChange={(e) => {
          if(e.target.value == '' || e.target.value === null) {
              isStartingDateEmptyUpdate(true);
          } else {
              isStartingDateEmptyUpdate(false);
          }
          console.log(isStartingDateEmpty)
          var date = convertDate(new Date(e.target.value.toString()))
          setFormData({...formData, startingDate: date})
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
        type="date"
        id="endingDate"
        onChange={(e) => {
          if(e.target.value == '' || e.target.value === null) {
              isEndingDateEmptyUpdate(true);
          } else {
              isEndingDateEmptyUpdate(false);
          }
          console.log(isEndingDateEmpty)
          var date = convertDate(new Date(e.target.value.toString()))
          setFormData({...formData, endingDate: date})
        }}
        error={isEndingDateEmpty}
      />
      <BankingTransactionsTable transactions={dummyTransactions} isOpen={isBankingTransactionTableOpen} handleClose={handleCloseBankingTransactionTable} />
    </Box>
        </DialogContent>
        </Dialog>

      );
}




{/* <ThemeProvider theme={defaultTheme}>
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
//     </Avatar>
//     <Typography component="h1" variant="h5">
//       Transactional History
//     </Typography>
//     <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
//       <TextField
//         className='input'
//         margin="normal"
//         required
//         fullWidth
//         name="username"
//         label="Username"
//         type="text"
//         id="username"
//         onChange={(e) => {
//           if(e.target.value == '' || e.target.value === null) {
//               isUsernameEmptyUpdate(true);
//           } else {
//               isUsernameEmptyUpdate(false);
//           }
//           setFormData({...formData, username: e.target.value})
//         }}
//         error={isUsernameEmpty}
//       />
//       <TextField
//         className='input'
//         margin="normal"
//         required
//         fullWidth
//         name="accountNo"
//         label="Account Number"
//         type="text"
//         id="accountNo"
//         onChange={(e) => {
//           if(e.target.value == '' || e.target.value === null) {
//               isAccountNoEmptyUpdate(true);
//           } else {
//               isAccountNoEmptyUpdate(false);
//           }
//           setFormData({...formData, accountNo: e.target.value})
//         }}
//         error={isAccountNoEmpty}
//       />
//       <TextField
//         className='input'
//         margin="normal"
//         required
//         fullWidth
//         name="startingDate"
//         label="Starting Date"
//         type="date"
//         id="startingDate"
//         onChange={(e) => {
//           if(e.target.value == '' || e.target.value === null) {
//               isStartingDateEmptyUpdate(true);
//           } else {
//               isStartingDateEmptyUpdate(false);
//           }
//           console.log(isStartingDateEmpty)
//           var date = convertDate(new Date(e.target.value.toString()))
//           setFormData({...formData, startingDate: date})
//         }}
//         error={isStartingDateEmpty}
//       />
//       <TextField
//         className='input'
//         margin="normal"
//         required
//         fullWidth
//         name="endingDate"
//         label="Ending Date"
//         type="date"
//         id="endingDate"
//         onChange={(e) => {
//           if(e.target.value == '' || e.target.value === null) {
//               isEndingDateEmptyUpdate(true);
//           } else {
//               isEndingDateEmptyUpdate(false);
//           }
//           console.log(isEndingDateEmpty)
//           var date = convertDate(new Date(e.target.value.toString()))
//           setFormData({...formData, endingDate: date})
//         }}
//         error={isEndingDateEmpty}
//       />
//       <BankingTransactionsTable transactions={dummyTransactions} />
//     </Box>
//   </Box>
//   <Copyright sx={{ mt: 8, mb: 4 }} />
// </Container>
// </ThemeProvider> */}
