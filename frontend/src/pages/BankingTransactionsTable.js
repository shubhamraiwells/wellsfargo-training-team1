import React, { Component, useState, useContext, useEffect }   from 'react';

import apiCall from '../apiCall/apiCall';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogContentText,
  DialogActions,
} from '@mui/material';
import Pagination from '@mui/material/Pagination';
import styled from '@emotion/styled';
import { ArrowUpward, ArrowDownward } from '@mui/icons-material';
import { useToken } from "../context/TokenContext";
const useStyles = styled({
  tableContainer: {
    maxWidth: '800px',
    margin: '0 auto',
    marginTop: '20px',
  },
  inflow: {
    color: 'green',
    fontSize: '100px'
  },
  outflow: {
    color: 'red',
  },
  icon: {
    fontSize: '18px',
    marginRight: '8px',
  },
  smallText: {
    fontSize: '0.8rem',
  },
  paginationContainer: {
    display: 'flex',
    justifyContent: 'center',
    marginTop: '20px',
  },
});


const rowsPerPage = 4; // Number of rows to display per page

const BankingTransactionsTable = (props) => {
  const {transactions, isOpen, handleClose} = props
  const classes = useStyles();
  const [page, setPage] = useState(1);
  const [isDialogOpen, setIsDialogOpen] = useState(false);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };
  const { token, role, username, isTokenValid } = useToken()
  const startIndex = (page - 1) * rowsPerPage;
  const endIndex = startIndex + rowsPerPage;

  const displayedTransactions = transactions.slice(startIndex, endIndex);
  const [originalTransactions, setDummyData] = useState([])
  useEffect((e) => {
    if (isTokenValid() == false) {
      return;
    }
    (async () => {
      if(username!=null){
      const res = await apiCall('http://localhost:8080/api/transactions/getTransactionsByUsername?username=' + username, "GET", {}, token);
      console.log(res)
      setDummyData(res.data)
      }
      // console
    })()
  },[])

  return (
    
   originalTransactions.length>0? <div>
      {/* <Button variant="contained" color="primary" onClick={() => setIsDialogOpen(true)} type="submit">
        Get Transactions
      </Button> */}
      <Dialog open={isOpen} onClose={handleClose}>
        <DialogTitle>Banking Transactions</DialogTitle>
        <DialogContent>
          <TableContainer component={Paper} className={classes.tableContainer}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell className={classes.smallText}>Transaction ID</TableCell>
                  <TableCell>Sender's Account</TableCell>
                  <TableCell>Receiver's Account</TableCell>
                  <TableCell>Amount</TableCell>
                  <TableCell>Date</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {originalTransactions.map((transaction, index) => (
                  <TableRow key={index}>
                    <TableCell className={classes.smallText}>{transaction.id}</TableCell>
                    <TableCell>{transaction.fromAccountNo}</TableCell>
                    <TableCell>{transaction.toAccountNo}</TableCell>
                    <TableCell>
                      <span
                        style={{
                          color: transaction.transactionAmount >= 0 ? 'green' : 'red',
                          display: 'flex',
                          alignItems: 'center',
                        }}
                      >
                        {transaction.transactionAmount >= 0 ? (
                          <Typography className={classes.icon}><ArrowUpward /></Typography>
                        ) : (
                          <Typography className={classes.icon}><ArrowDownward /></Typography>
                        )}
                        {Math.abs(transaction.transactionAmount)}
                        </span>
                        </TableCell>
                    <TableCell>{transaction.transactionDate}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
          <div className={classes.paginationContainer}>
            <Pagination
              count={Math.ceil(transactions.length / rowsPerPage)}
              page={page}
              onChange={handleChangePage}
              color="primary"
            />
          </div>

        </DialogContent>
        {/* <DialogActions> */}
          <Button onClick={() => handleClose()} color="primary" >
            Close
          </Button>
        {/* </DialogActions> */}
      </Dialog>
    </div>:<h3>No transactions</h3>
  );
};

export default BankingTransactionsTable;
