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
export function RequestDetails(props){
    const request = props.customer;
    // console.log("Table Data:"+request);
    return(     
    <TableContainer component={Paper} style={{marginLeft:"15%", marginTop:"10%", maxWidth:1000}}>
    <Table sx={{ maxWidth:1000}} aria-label="simple table">
      <TableHead style={{textAlign:"center"}}>
        <TableRow>
          <TableCell align="right">Account Number</TableCell>
          <TableCell align="right">Account Type</TableCell>
          <TableCell align="right">First Name</TableCell>
          <TableCell align="right">Last Name</TableCell>
          <TableCell align="centre">Mobile Number</TableCell>
          <TableCell align="right">Email Id</TableCell>
          <TableCell align="centre">Residential Address</TableCell>
          <TableCell align="right">Residential City</TableCell>
          <TableCell align="centre">Residential Pin Code</TableCell>
          <TableCell align="right">Occupation Type</TableCell>
          <TableCell align="right">Source of Income</TableCell>
          <TableCell align="right">Gross Annual Income</TableCell>
        </TableRow>
        </TableHead>
      <TableBody>
          <TableRow
            key={request.accountNo}
            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
          >
            <TableCell component="th" scope="row">{request.accountNo}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.accountType}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.firstName}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.lastName}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.mobileNo}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.emailId}</TableCell>           
            <TableCell align="right" style={{textAlign:"center"}}>{request.residentialAddressLine1}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.residentialCity}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.residentialPincode}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.occupationType}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.sourceOfIncome}</TableCell>
            <TableCell align="right" style={{textAlign:"center"}}>{request.grossAnnualIncome}</TableCell>
          </TableRow>
      </TableBody>
    </Table>
  </TableContainer>
  )
}