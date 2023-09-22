import {
    Table,
    TableBody,
    TableContainer,
    TableHead,
    Paper,
  } from '@mui/material';
  import { styled } from '@mui/material/styles';
  import TableCell, { tableCellClasses } from '@mui/material/TableCell';
  import TableRow from '@mui/material/TableRow';

  const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
    },
  }));
  
  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
      border: 0,
    },
  }));
  
  
export default function RequestDetails(props){
    const request = props.customer;
    console.log("Reqsss:")
    console.log(request);
    return(     
       
        <TableContainer component={Paper} className="TableContainer"  sx={{ maxWidth: 1500 , maxHeight:400}}>  
        <Table  aria-label="customized table" data={request}>
      <TableHead style={{textAlign:"right"}}>
        <StyledTableRow>
          <StyledTableCell align="right">Owner Id</StyledTableCell>
          <StyledTableCell align="right">Account Type</StyledTableCell>
          <StyledTableCell align="right">First Name</StyledTableCell>
          <StyledTableCell align="right">Last Name</StyledTableCell>
          <StyledTableCell align="centre">Mobile Number</StyledTableCell>
          <StyledTableCell align="right">Email Id</StyledTableCell>
          <StyledTableCell align="centre">Residential Address</StyledTableCell>
          <StyledTableCell align="right">Residential City</StyledTableCell>
          <StyledTableCell align="centre">Residential Pin Code</StyledTableCell>
          <StyledTableCell align="right">Occupation Type</StyledTableCell>
          <StyledTableCell align="right">Source of Income</StyledTableCell>
          <StyledTableCell align="right">Gross Annual Income</StyledTableCell>
        </StyledTableRow>
        </TableHead>
      <TableBody>
          <StyledTableRow
            key={request.id}
            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
          >
            <StyledTableCell component="th" scope="row">{request.id}</StyledTableCell>
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.accountType}</StyledTableCell>
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.firstName}</StyledTableCell>
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.lastName}</StyledTableCell>
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.mobileNo}</StyledTableCell>
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.emailId}</StyledTableCell>           
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.residentialAddressLine1}</StyledTableCell>
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.residentialCity}</StyledTableCell>
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.residentialPincode}</StyledTableCell>
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.occupationType}</StyledTableCell>
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.sourceOfIncome}</StyledTableCell>
            <StyledTableCell align="right" style={{textAlign:"center"}}>{request.grossAnnualIncome}</StyledTableCell>
          </StyledTableRow>
      </TableBody>
    </Table>
  </TableContainer>
  )
}