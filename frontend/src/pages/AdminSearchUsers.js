import React, { useState, useEffect } from 'react';
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
import AdminNavBar from "./AdminNavbar";
import Modal from '@mui/material/Modal';
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import apiCall from '../apiCall/apiCall';
import Switch from "@mui/material/Switch";
import RequestDetails from "./RequestDetails";
import { useToken } from '../context/TokenContext';


const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: "#101073",
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
  width: 800,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};



export default function AdminSearchUsers() {


  const handleSearch = (event) => {
    setSearch(event.target.value);
  };

  const { token, role, username, isTokenValid } = useToken()
  const [users, setUsers] = useState([])
  const [loading, setLoading] = useState(false)
  const [loadingModal, setLoadingModal] = useState(true)
  const [loadingModal1, setLoadingModal1] = useState(true)
  const [search, setSearch] = useState('');
  const [filteredData, setFilteredData] = useState(users)
  const [open1, setOpen1] = useState(false);
  const handleClose1 = () => setOpen1(false);
  const [open2, setOpen2] = useState(false);
  const handleClose2 = () => setOpen2(false);
  const [userStates, setUserStates] = useState({});
  const [transactions, setTransactions] = useState([]);
  const [customer, setCustomer] = useState([]);
  const handleToggle = (userStates, accountNo) => {
    setUserStates((userStates) => ({
      ...userStates,
      [accountNo]: !userStates[accountNo],
    }));
  }
  const handleSwitch = async (accountNo) => {
    handleToggle(userStates, accountNo);
    // const url = `http://localhost:8080/api/admin/deactivateUser?accountNo=${accountNo}`;
    const url = `${process.env.REACT_APP_DEVELOPMENT_URL}/api/admin/deactivateUser?accountNo=${accountNo}`;
    const response = await apiCall(url, "GET", null, token)
    console.log(response)
  }
  const handleChange = async (accountNo) => {
    setOpen1(true);
    // const url = `http://localhost:8080/api/transactions/getTransactions?accountNo=${accountNo}`;
    const url = `${process.env.REACT_APP_DEVELOPMENT_URL}/api/transactions/getTransactions?accountNo=${accountNo}`;

    try {
      console.log(accountNo);
      const response = await apiCall(url, "GET", null, token);
      if (response.status === 200) {

        if (response.data != null) {
          setTransactions(response.data);
          console.log(transactions);
        } else {
          console.log("No Transactions Performed");
        }
      } else {
        // Handle non-200 status codes here
        console.error(`Request failed with status code ${response.status}`);

      }
    } catch (error) {
      console.error("Error:", error);
    } finally {
      setLoadingModal(false);
    }
  };

  const handleChangeCustomer = async (ownerId) => {
    setOpen2(true);
    const url = `${process.env.REACT_APP_DEVELOPMENT_URL}/api/admin/getAllCustomers?ownerId=${ownerId}`;

    try {
      console.log(ownerId);
      const response = await apiCall(url, "GET", null, token);
      console.log("Response Status:", response.status);
      console.log("Response Data:", response.data);

      if (response.status === 200) {

        if (response.data != null) {
          setCustomer(response.data);

          console.log(customer);
        } else {
          console.log("No Data found");
        }
      } else {
        // Handle non-200 status codes here
        console.error(`Request failed with status code ${response.status}`);

      }
    } catch (error) {
      console.error("Error:", error);

    } finally {
      setLoadingModal1(false);
    }
  };


  useEffect(() => {
    setLoading(true);

    const fetchData = async () => {
      try {
        const response = await apiCall(`${process.env.REACT_APP_DEVELOPMENT_URL}/api/admin/getAllAccounts`, "GET", null, token);
        const responseData = response.data;
        console.log(responseData);

        if (responseData != null) {
          setUsers(responseData);
          responseData.map(item => (
            setUserStates((userStates) => ({ ...userStates, [item.id]: item.isActive ? true : false }))))
        } else {
          setUsers([]);
        }
      } catch (error) {
        console.error("Error fetching data:", error);
        // Handle errors here
      } finally {
        setLoading(false);
      }
    };

    fetchData();

  }, []);

  // ...



  // Update filteredData when search changes
  useEffect(() => {
    const filtered = users.filter(item =>
      item.id.includes(search)
    );
    setFilteredData(filtered);
  }, [search, users]);
  return (
    <div>      
    <AdminNavBar />
    {loading ? (
      <div>Loading</div>
    ) : isTokenValid() && (
      <div>
        <Box sx={{ '& > :not(style)': { m: 1 } }} style={{ margin: 75 }}>


          <Box sx={{ display: 'flex', alignItems: 'flex-end' }}>
            <SearchIcon sx={{ color: 'action.active', mr: 1, my: 0.5 }} />
            <TextField id="search" label="Search" variant="standard" type="text" onChange={handleSearch} />
          </Box>
        </Box>

        <TableContainer component={Paper} className="TableContainer" style={{ marginLeft: 100 }} sx={{ maxWidth: 1100, maxHeight: 400 }}>
          <Table aria-label="customized table" data={filteredData}>
            <TableHead>
              <TableRow>
                <StyledTableCell>Account Number</StyledTableCell>
                <StyledTableCell align="right">Account Type</StyledTableCell>
                <StyledTableCell align="right">Owner ID</StyledTableCell>
                <StyledTableCell align="right">Active</StyledTableCell>
                <StyledTableCell align="right">Activation Date</StyledTableCell>
                <StyledTableCell align="right">Total Balance</StyledTableCell>
                <StyledTableCell align="right">Transaction History</StyledTableCell>
                <StyledTableCell align="right">Customer Details</StyledTableCell>
                <StyledTableCell align="right">Enable / Disable User</StyledTableCell>
              </TableRow>
            </TableHead>

            <TableBody>


              {filteredData.map(item => (

                <StyledTableRow key={item.id}>
                  <StyledTableCell component="th" scope="row">
                    {item.id}
                  </StyledTableCell>
                  <StyledTableCell align="right">{item.accountType}</StyledTableCell>
                  <StyledTableCell align="right">{item.ownerId}</StyledTableCell>
                  <StyledTableCell align="right">{item.isActive ? "true" : "false"}</StyledTableCell>
                  <StyledTableCell align="right">{item.accountActivationDate}</StyledTableCell>
                  <StyledTableCell align="right">{item.totalBalance}</StyledTableCell>

                  <StyledTableCell align="right">

                    <Button
                      className="button1"
                      onClick={() => {
                        console.log(item.id);
                        handleChange(item.id);
                      }}
                      variant="contained"
                      sx={{ mt: 3, mb: 2 }}
                    >
                      Open
                    </Button>
                  </StyledTableCell>
                  <StyledTableCell align="right">

                    <Button
                      className="button1"
                      onClick={() => {
                        console.log(item.ownerId);
                        handleChangeCustomer(item.ownerId);
                        // handleChangeCustomer(item.ownerId);
                      }}
                      variant="contained"
                      sx={{ mt: 3, mb: 2 }}
                    >
                      Open
                    </Button>
                  </StyledTableCell>
                  <StyledTableCell align="right">
                    <Switch
                      checked={userStates[item.id]}
                      onChange={() => handleSwitch(item.id)}
                      color="primary"
                      inputProps={{ 'aria-label': 'toggle switch' }}

                    />
                  </StyledTableCell>
                </StyledTableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>


        {loadingModal ?
          <Modal
            open={open1}
            onClose={handleClose1}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
          >
            <Typography id="modal-modal-description" sx={{ mt: 2 }}>
              Loading...
            </Typography>
          </Modal>
          :
          <Modal
            open={open1}
            onClose={handleClose1}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
          >
            <Box sx={style}>
              <TableContainer component={Paper} className="TableContainer" sx={{ maxWidth: 1100, maxHeight: 400 }}>
                <Table aria-label="customized table" data={transactions}>
                  <TableHead>
                    <TableRow>

                      <StyledTableCell align="right">id</StyledTableCell>
                      <StyledTableCell align="right">fromAccountNo</StyledTableCell>
                      <StyledTableCell align="right">toAccountNo</StyledTableCell>
                      <StyledTableCell align="right">transactionAmount</StyledTableCell>
                      <StyledTableCell align="right">TransactionDate</StyledTableCell>

                    </TableRow>
                  </TableHead>
                  <TableBody>


                    {transactions.map(item => (
                      <StyledTableRow key={item.id}>
                        <StyledTableCell component="th" scope="row">
                          {item.id}
                        </StyledTableCell>
                        <StyledTableCell align="right">{item.fromAccountNo}</StyledTableCell>
                        <StyledTableCell align="right">{item.toAccountNo}</StyledTableCell>
                        <StyledTableCell align="right">{item.transactionAmount}</StyledTableCell>
                        <StyledTableCell align="right">{item.transactionDate}</StyledTableCell>

                      </StyledTableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
            </Box>
          </Modal>
        }
        {loadingModal1 ?
          <Modal
            open={open2}
            onClose={handleClose2}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
          >
            <Typography id="modal-modal-description" sx={{ mt: 2 }}>
              Loading...
            </Typography>
          </Modal>
          :
          <Modal
            open={open2}
            onClose={handleClose2}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
          >
            <Box sx={style}>
              {customer.length != 0 ? <RequestDetails customer={customer} /> : <p>Data could not be displayed, try again</p>}

            </Box>
          </Modal>
        }

      </div>
    )}
    </div>
  );
  
}