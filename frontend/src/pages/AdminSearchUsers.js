import React, { Component, useState, useContext, useEffect }   from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import "../App.css";
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import SearchIcon from '@mui/icons-material/Search';
import NavBar from "./NavBar";
import Modal from '@mui/material/Modal';
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import apiCall from '../apiCall/apiCall';
import Switch from "@mui/material/Switch";
import AdminNavbar from './AdminNavbar';

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

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};



export default function AdminSearchUsers() {

  

const handleSearch = (event) => {
  setSearch(event.target.value);
};


  const rows = 
    [{ "AccountNumber" : "9048t59u94","AccountType": "Savings", "CustomerID":41, "Active":"true", "ActivationDate":"23/12/2008","TotalBalance":250},
    { "AccountNumber" : "9058uy7554","AccountType": "Salary", "CustomerID":42, "Active":"true", "ActivationDate":"25/09/2008","TotalBalance":1000},
    { "AccountNumber" : "9gjtu48594","AccountType": "Savings", "CustomerID":43, "Active":"true", "ActivationDate":"27/04/2008","TotalBalance":1200},
    { "AccountNumber" : "4930303094","AccountType": "Salary", "CustomerID":44, "Active":"true", "ActivationDate":"29/02/2008","TotalBalance":50},
    { "AccountNumber" : "9048t39394","AccountType": "Savings", "CustomerID":45, "Active":"true", "ActivationDate":"13/06/2008","TotalBalance":10980}
  ];
  
  
    const [users, setUsers] = useState([])
  const [loading, setLoading] = useState(false)
  const [loadingModal, setLoadingModal] = useState(true)
  const [search, setSearch] = useState('');
  const [filteredData, setFilteredData] = useState(users);
const [modalData, setModalData] = useState('');
const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const temp_token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYXR5YVNyZWVOYXJheWFuYW4yIiwiaWF0IjoxNjk1MDQ4NjA1LCJleHAiOjE2OTUwNTM2MDV9.2W_Neg49PEphekn-zjDjwMpBc8x9LXAQweSZYnr1fj0";
  let i = 0;
const [isDisabled,setIsDisabled] = useState(false);
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
    },];  


const handleSwitch = (accountNo) => {
  setIsDisabled((isDisabled) => !isDisabled);
//const temp_token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYXR5YVNyZWVOYXJheWFuYW4yIiwiaWF0IjoxNjk1MDQ4NjA1LCJleHAiOjE2OTUwNTM2MDV9.2W_Neg49PEphekn-zjDjwMpBc8x9LXAQweSZYnr1fj0";
  //  const url = 'http://localhost:8080/api/admin/deactivateUser' 
  //  const response=await apiCall(url,"GET",accountNo,temp_token)
 //   console.log(response)
}
  const handleChange = 
  //async 
  (accountNo) => {
    setOpen(true);
    //const temp_token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYXR5YVNyZWVOYXJheWFuYW4yIiwiaWF0IjoxNjk1MDQ4NjA1LCJleHAiOjE2OTUwNTM2MDV9.2W_Neg49PEphekn-zjDjwMpBc8x9LXAQweSZYnr1fj0";
  //  const url = 'http://localhost:8080/api/transactions/getTransactions'  
  //  const response=await apiCall(url,"GET",accountNo,temp_token)
 //   console.log(response)
  //  if(response.data!=null){
      setLoadingModal(false);
      let res = "";
     
      for(var key in dummyTransactions[i]){
res = res + key + " " + dummyTransactions[i][key]+"\n";
}
if(i>8){i=0;}else{i++};
    //   setModalData(response.data);
    setModalData(res);
        
  //  }else{
  //    setModalData("No Transactions performed");
  //  }

  };

  useEffect(() => {
    setLoading(true)
   
    //  const response=apiCall("http://localhost:8080/api/admin/getAllAccounts","GET",null,temp_token)
   // console.log(response.data)
    setLoading(false)
   // if(response.data!=null){
    
    //  setUsers(response.data) 
   // }else{
      setUsers(rows)
   // }


  }, [])

  
  // Update filteredData when search changes
  useEffect(() => {
    const filtered = users.filter(item =>
      item.AccountNumber.includes(search)
    );
    setFilteredData(filtered);
  }, [search, users]);

  return (
    loading ? (
      <div>Loading</div>
    ) : (
    <div>
      <AdminNavbar/>
      <Box sx={{ '& > :not(style)': { m: 1 } }} style={{marginLeft:"20%", marginTop:"10%"}}>
      
      
      <Box sx={{ display: 'flex', alignItems: 'flex-end' }}>
        <SearchIcon sx={{ color: 'action.active', mr: 1, my: 0.5 }} />
        <TextField id="search" label="Search" variant="standard" type="text" onChange={handleSearch}/>
      </Box>
    </Box>
    
    <TableContainer component={Paper} className="TableContainer" style={{marginLeft:"20%", marginTop:"2%"}} sx={{ maxWidth: 1100 , maxHeight:400}}>  
      <Table  aria-label="customized table" data={filteredData}>
        <TableHead>
          <TableRow>
            <StyledTableCell>Account Number</StyledTableCell>
            <StyledTableCell align="right">Account Type</StyledTableCell>
            <StyledTableCell align="right">Customer ID</StyledTableCell>
            <StyledTableCell align="right">Active</StyledTableCell>
            <StyledTableCell align="right">Activation Date</StyledTableCell>
            <StyledTableCell align="right">Total Balance</StyledTableCell>
            <StyledTableCell align="right">Transaction History</StyledTableCell>
            <StyledTableCell align="right">Enable / Disable User</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
        

          {filteredData.map(item => (
            <StyledTableRow key={item.AccountNumber}>
              <StyledTableCell component="th" scope="row">
                {item.AccountNumber}
              </StyledTableCell>
              <StyledTableCell align="right">{item.AccountType}</StyledTableCell>
              <StyledTableCell align="right">{item.CustomerID}</StyledTableCell>
              <StyledTableCell align="right">{item.Active}</StyledTableCell>
              <StyledTableCell align="right">{item.ActivationDate}</StyledTableCell>
              <StyledTableCell align="right">{item.TotalBalance}</StyledTableCell>
             
              <StyledTableCell align="right">

              <Button
                className="button1"
                onClick={()=>{
                 handleChange("key");
                }}
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Open
              </Button>
              </StyledTableCell>
              <StyledTableCell align="right">
              <Switch 
              checked={isDisabled}
              onChange={() => handleSwitch(item.AccountNumber)}
              color="primary"
              inputProps={{ 'aria-label': 'toggle switch' }}
                
              />
              </StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    {
      loadingModal && open ? 
<Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
           Loading....
          </Typography>
          
        </Box>
      </Modal>

     : <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
           {modalData}
          </Typography>
          
        </Box>
      </Modal>
    }
    </div>
    )
  );
}