import React, { useState } from 'react';
import Modal from '@mui/material/Modal';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import './Withdrawal.css';
import { useToken } from '../context/TokenContext';
import apiCall from '../apiCall/apiCall';
const Deposit = ({ onApiCall, isOpen, onClose, accountNumbers, onDeposit }) => {
  const {token,role,username,isTokenValid}=useToken()
  const [selectedAccount, setSelectedAccount] = useState('');
  const [amount, setAmount] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleDeposit = async () => {
    setIsLoading(true);

    // Make an API call to perform the deposit here
    try {
   
    const res=await apiCall("http://localhost:8080/api/transactions/deposit","POST",{
      username,
      amount
    },token)
    onApiCall(res.data, 'success')

      onDeposit();
    } catch (error) {
      onApiCall(error.response.data, 'error')
      console.error('Error:', error);
    }

    setIsLoading(false);
    onClose();
  };

  const handleClose = () => {
    onClose();
  };

  return (
    isTokenValid()?<Modal open={isOpen} onClose={handleClose}>
      <div className="modal-overlay">
        <Card className="modal-card">
          <CardContent>
            <Typography variant="h5" gutterBottom>
              Deposit Money
            </Typography>
            {/* <TextField
              select
              label="Select Account Number"
              fullWidth
              variant="outlined"
              value={selectedAccount}
              onChange={(e) => setSelectedAccount(e.target.value)}
            >
              {accountNumbers.map((account) => (
                <MenuItem key={account} value={account}>
                  {account}
                </MenuItem>
              ))}
            </TextField> */}
            <TextField
              type="number"
              label="Enter Amount (Rs)"
              variant="outlined"
              fullWidth
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
            />
            <div className="default-amount-buttons">
              <Button
                variant="outlined"
                onClick={() => setAmount('100')}
              >
                100 Rs
              </Button>
              <Button
                variant="outlined"
                onClick={() => setAmount('500')}
              >
                500 Rs
              </Button>
              <Button
                variant="outlined"
                onClick={() => setAmount('1000')}
              >
                1000 Rs
              </Button>
            </div>
            <div className="modal-button-group">
              <Button
                variant="contained"
                color="primary"
                onClick={handleDeposit}
                disabled={ !amount || isLoading}
              >
                Deposit
              </Button>
              <Button
                variant="outlined"
                onClick={handleClose}
                disabled={isLoading}
              >
                Cancel
              </Button>
            </div>
          </CardContent>
        </Card>
      </div>
    </Modal>:<h1>Unauthorized</h1>
  );
};

export default Deposit;