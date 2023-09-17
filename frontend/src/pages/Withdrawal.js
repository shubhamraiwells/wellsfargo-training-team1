import React, { useState } from 'react';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import './Withdrawal.css';

const Withdrawal = ({ isOpen, onClose }) => {
  const [amount, setAmount] = useState('');
  const [balance, setBalance] = useState(1000); // Dummy balance, replace with API data

  const handleWithdraw = () => {
    if (amount <= balance) {
      // Handle withdrawal logic here (e.g., update the balance)
      setBalance(balance - amount);
      onClose();
    } else {
      alert('Insufficient funds');
    }
  };

  const handleDefaultWithdrawal = (defaultAmount) => {
    setAmount(defaultAmount);
  };

  return (
    <Modal open={isOpen} onClose={onClose}>
      <div className="modal-overlay">
        <div className="modal-content">
          <h2>Banking Modal</h2>
          <Card>
            <CardContent>
              <Typography variant="h5" gutterBottom>
                Current Balance: {balance} Rs
              </Typography>
              <div className="default-amount-buttons">
                <Button
                  variant="outlined"
                  onClick={() => handleDefaultWithdrawal(100)}
                >
                  100 Rs
                </Button>
                <Button
                  variant="outlined"
                  onClick={() => handleDefaultWithdrawal(500)}
                >
                  500 Rs
                </Button>
                <Button
                  variant="outlined"
                  onClick={() => handleDefaultWithdrawal(1000)}
                >
                  1000 Rs
                </Button>
              </div>
              <TextField
                type="number"
                label="Amount (Rs)"
                variant="outlined"
                fullWidth
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
              />
              <Button
                variant="contained"
                color="primary"
                onClick={handleWithdraw}
              >
                Withdraw
              </Button>
            </CardContent>
          </Card>
        </div>
      </div>
    </Modal>
  );
};

export default Withdrawal;
