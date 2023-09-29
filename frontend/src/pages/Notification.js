import React, { useState } from 'react';
import { Snackbar, SnackbarContent, Button } from '@mui/material';
import Alert from "@mui/material/Alert";

function Notification({ open, message, severity, onClose }) {
  return (
    <Snackbar
      open={open}
      autoHideDuration={5000} // Adjust the duration as needed (in milliseconds)
      onClose={onClose}
      anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
    >
    <Alert onClose={onClose} severity={severity}>
    {message}
      </Alert>
    </Snackbar>
  );
}

export default Notification;
