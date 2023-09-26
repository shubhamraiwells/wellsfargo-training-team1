import React, { Component, useState, useContext, useEffect } from "react";
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
import { useToken } from "../context/TokenContext";
const usernameRegex = /^(?!.*\.\.)(?!.*\.$)[A-Za-z0-9_.]{8,20}$/;

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
  const { token, role, username, isTokenValid } = useToken()
  const [isAccountNoEmpty, isAccountNoEmptyUpdate] = useState(false);
  const [isUsernameEmpty, isUsernameEmptyUpdate] = useState(false);
  const [isStartingDateEmpty, isStartingDateEmptyUpdate] = useState(false);
  const [isEndingDateEmpty, isEndingDateEmptyUpdate] = useState(false);
  const [isBankingTransactionTableOpen, setIsBankingTransactionTableOpen] = useState(true);
  const [show, setShow] = useState(false);
  const handleCloseBankingTransactionTable = () => {
    setIsBankingTransactionTableOpen(false);
  }
  const [dummyData, setDummyData] = useState([])
  const [formData, setFormData] = useState({
    // username:"",
    // accountNo:"",
    // startingDate:"",
    // endingDate:""
  })

  // const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const handleSubmit = async (e) => {
    const url = "http://localhost:8080/history";
    e.preventDefault();

    handleShow();
  }

  // useEffect((e) => {
  //   if (isTokenValid() == false) {
  //     return;
  //   }
  //   (async () => {
  //     if(username!=null){
  //     const res = await apiCall('http://localhost:8080/api/transactions/getTransactionsByUsername?username=' + username, "GET", {}, token);
  //     console.log(res)
  //     setDummyData(res.data)
  //     }
  //     // console
  //   })()
  // },[])

  function convertDate(inputFormat) {
    function pad(s) { return (s < 10) ? '0' + s : s; }
    var d = new Date(inputFormat)
    return [pad(d.getDate()), pad(d.getMonth() + 1), d.getFullYear()].join('/')
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
    isTokenValid()  ?
      <Box >
        <BankingTransactionsTable transactions={dummyData} isOpen={isOpen} handleClose={handleClose} />
      </Box> : <h1>unauthorized</h1>


  );
}




