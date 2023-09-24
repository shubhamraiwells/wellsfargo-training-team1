import React, { Component, useState, useContext, useEffect }   from 'react';

import apiCall from '../apiCall/apiCall';
import {
  Table,
  TableBody,
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
import Pagination from '@mui/material/Pagination';
import { ArrowUpward, ArrowDownward } from '@mui/icons-material';
import { useToken } from "../context/TokenContext";
import RequestDetails from './RequestDetails';
import Box from '@mui/material/Box';
import DangerousIcon from '@mui/icons-material/Dangerous';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import { styled } from '@mui/material/styles';
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

const UserRequestsTable = (props) =>{

    const [openView, setOpenView] = useState(false);   
    const [requests, setRequests] = useState(props.requests); 
    const [modalProp, setModalProp] = useState();
    const viewRequest = () =>{
        setOpenView(true);
      }
    const performOperation = async(accNo, approveAccount) =>{
      const body ={"accountNo":accNo, "approveAccount":approveAccount}
      const response = await apiCall("http://localhost:8080/api/auth/approveBankAccount", "POST", body);
      console.log(response);
    }
      const handleRequest = (index) =>{
        console.log("Index:"+index);
        performOperation(requests[index].accountNo, false);
        modifyTable(index);
      }
      const closeRequest = () =>{
        setOpenView(false);
      }
      const modifyTable = (index) =>{
        const newReqs = [...requests];
        newReqs.splice(index, 1);
        setRequests(newReqs);
        //console.log(newReqs);
      }      
  console.log("Received Reqs:"+requests);
  console.log(props.requests);
  return (
    <div className="container">
         <TableContainer component={Paper} style={{marginLeft:"30%", marginTop:"10%", maxWidth:800}}>
      <Table sx={{ maxWidth:800}} aria-label="customized table">
        <TableHead style={{textAlign:"center"}}>
          <TableRow>
            <StyledTableCell>Account Number</StyledTableCell>
            <StyledTableCell align="right">Account Type</StyledTableCell>
            <StyledTableCell align="right"></StyledTableCell>
            <StyledTableCell align="right"></StyledTableCell>
            <StyledTableCell align="right" style={{backgroundColor:"black"}}></StyledTableCell>
            <StyledTableCell align="right" style={{backgroundColor:"black"}}></StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {requests.map((request, index) => (
            <StyledTableRow
              key={request.accountNo}
            >
              <StyledTableCell component="th" scope="row">
                {request.accountNo}
              </StyledTableCell>
              <StyledTableCell align="right" style={{textAlign:"center"}}>{request.accountType}</StyledTableCell>
              <StyledTableCell align="right" style={{textAlign:"center"}}>{request.customerId}</StyledTableCell>
              <StyledTableCell align="right" style={{textAlign:"center"}} onClick={()=>{setModalProp(request); viewRequest();}} ><Button size="small" variant="contained" style={{backgroundColor:"blue"}}>View</Button></StyledTableCell>
              <StyledTableCell align="right" style={{textAlign:"center"}} onClick={()=>{handleRequest(index)}} ><Button size="small" variant="contained" style={{backgroundColor:"green"}}>Approve</Button></StyledTableCell>
              <StyledTableCell align="right" style={{textAlign:"center"}}onClick={()=>{handleRequest(index)}} ><Button size="small" variant="contained" style={{backgroundColor:"red"}}>Reject</Button></StyledTableCell>
              
            </StyledTableRow>
            
          ))
          }
        </TableBody>
      </Table>
    </TableContainer>
    <Modal open={openView}>
            <Box>
                <div style={{display:"flex",flexDirection:"column", alignItems:"center"}}>
                <RequestDetails customer={modalProp}/>
                <Button size="large" variant='contained' onClick={closeRequest} style={{width:"5%", marginLeft:"12%", marginTop:"2%"}}><DangerousIcon/></Button>
                </div>
            </Box>
    </Modal>
    </div>
  );
}

export default UserRequestsTable;
