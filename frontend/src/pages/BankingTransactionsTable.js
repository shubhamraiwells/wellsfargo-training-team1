import React, { useState } from 'react';
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

const BankingTransactionsTable = ({ transactions }) => {
  const classes = useStyles();
  const [page, setPage] = useState(1);
  const [isDialogOpen, setIsDialogOpen] = useState(false);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const startIndex = (page - 1) * rowsPerPage;
  const endIndex = startIndex + rowsPerPage;

  const displayedTransactions = transactions.slice(startIndex, endIndex);

  return (
    <div>
      <Button variant="contained" color="primary" onClick={() => setIsDialogOpen(true)} type="submit">
        Get Transactions
      </Button>
      <Dialog open={isDialogOpen} onClose={() => setIsDialogOpen(false)}>
        <DialogTitle>Banking Transactions</DialogTitle>
        <DialogContent>
          <TableContainer component={Paper} className={classes.tableContainer}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell className={classes.smallText}>Transaction ID</TableCell>
                  <TableCell>Sender's Address</TableCell>
                  <TableCell>Receiver's Address</TableCell>
                  <TableCell>Amount</TableCell>
                  <TableCell>Date</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {displayedTransactions.map((transaction, index) => (
                  <TableRow key={index}>
                    <TableCell className={classes.smallText}>{transaction.transactionId}</TableCell>
                    <TableCell>{transaction.senderAddress}</TableCell>
                    <TableCell>{transaction.receiverAddress}</TableCell>
                    <TableCell>
                      <span
                        style={{
                          color: transaction.amount >= 0 ? 'green' : 'red',
                          display: 'flex',
                          alignItems: 'center',
                        }}
                      >
                        {transaction.amount >= 0 ? (
                          <Typography className={classes.icon}><ArrowUpward /></Typography>
                        ) : (
                          <Typography className={classes.icon}><ArrowDownward /></Typography>
                        )}
                        {Math.abs(transaction.amount)}
                        </span>
                        </TableCell>
                    <TableCell>{transaction.date}</TableCell>
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
        <DialogActions>
          <Button onClick={() => setIsDialogOpen(false)} color="primary" >
            Close
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default BankingTransactionsTable;
