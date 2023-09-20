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
    Modal,
  } from '@mui/material';
export function AccountDetails(props){
    const request = props.account;
    return(     
    <TableContainer component={Paper} style={{marginLeft:"15%", marginTop:"10%", maxWidth:1000}}>
    <Table sx={{ maxWidth:1000}} aria-label="simple table">
      <TableHead style={{textAlign:"center"}}>
        <TableRow>
          <TableCell>Account Number</TableCell>
          <TableCell align="right">Account Type</TableCell>
          <TableCell align="right">Customer Id</TableCell>
          <TableCell align="centre">Account Activation Date</TableCell>
          <TableCell align="right">Total Balance</TableCell>
          <TableCell align="right"></TableCell>
        </TableRow>
        </TableHead>
      <TableBody>
          <TableRow
            key={request.accountNo}
            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
          >
            <TableCell component="th" scope="row">{request.accountNo}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.accountType}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.customerId}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.accountActivationDate}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.totalBalance}</TableCell>
          </TableRow>
      </TableBody>
    </Table>
  </TableContainer>)
}