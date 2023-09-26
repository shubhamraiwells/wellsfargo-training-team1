import * as React from 'react';
import { styled, useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import MuiDrawer from '@mui/material/Drawer';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import CssBaseline from '@mui/material/CssBaseline';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import MailIcon from '@mui/icons-material/Mail';
import { ThemeProvider, createTheme } from "@mui/material/styles";
import Home from './Home';
import Login from './Login';
import RegisterAccount from './RegisterAccount';
import SignUp from "./SignUp";
import Button from '@mui/material/Button';
import ManageSearchIcon from '@mui/icons-material/ManageSearch';
import ApprovalIcon from '@mui/icons-material/Approval';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import PersonIcon from '@mui/icons-material/Person';
import HailIcon from '@mui/icons-material/Hail';
import { 
    useState, 
    }  from 'react';
import { Switch } from "@mui/material/";
import {NavLink} from 'react-router-dom';
import LogoutIcon from '@mui/icons-material/Logout';
import { useToken } from "../context/TokenContext";

import {useNavigate} from "react-router-dom"
import { useEffect } from 'react';
const drawerWidth = 240;

const openedMixin = (theme) => ({
  width: drawerWidth,
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.enteringScreen,
  }),
  overflowX: 'hidden',
});

const closedMixin = (theme) => ({
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  overflowX: 'hidden',
  width: `calc(${theme.spacing(7)} + 1px)`,
  [theme.breakpoints.up('sm')]: {
    width: `calc(${theme.spacing(8)} + 1px)`,
  },
});

const DrawerHeader = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'flex-end',
  padding: theme.spacing(0, 1),
  // necessary for content to be below app bar
  ...theme.mixins.toolbar,
}));

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));


const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
    boxSizing: 'border-box',
    ...(open && {
      ...openedMixin(theme),
      '& .MuiDrawer-paper': openedMixin(theme),
    }),
    ...(!open && {
      ...closedMixin(theme),
      '& .MuiDrawer-paper': closedMixin(theme),
    }),
  }),
);

export default function AdminNavbar() {
  // const theme = useTheme();
  const [open, setOpen] = React.useState(false);   
  const {token,role,username,isTokenValid,clearToken}=useToken();
  const navigate = useNavigate();
  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  const handleLogout = () =>{
    alert("Logged out");
  }
  const [mode, setMode] = useState("light");
  const darkTheme = createTheme({
    palette: {
      type: "dark",
      background: {
        default: "hsl(230, 8%, 71%)"
      },
      color:"#FFFFFF"
    }
  });
  
  const lightTheme = createTheme({
    palette: {
      type: "light",
      background: {
        default: "hsl(0, 0%, 100%)"
      }
    }
  });
  const selectedtheme = mode === "dark" ? darkTheme : lightTheme; 
  // useEffect(()=>{
  //   if(username!=null){
  //     const authorizedFlag = username.trim()=="ROLE_ADMIN";    
  //   console.log("Role:"+role);
  //   console.log("Username:"+username)
  //   console.log(username.trim()=="ROLE_ADMIN")
  //   }
  //   else{
  //     alert("Kindly log in to view this page");
  //     console.log("Navigating...")
  //     navigate('/Admin');
  // }
  // },[])  
 
    return (   
      <ThemeProvider theme={selectedtheme}>
      <Box sx={{ display: 'flex' }}>
        <CssBaseline />
        <AppBar position="fixed" open={open} style={{ background: "#101073" }}>
          <Toolbar>
            <IconButton
              color="inherit"
              aria-label="open drawer"
              onClick={handleDrawerOpen}
              edge="start"
              sx={{
                marginRight: 5,
                ...(open && { display: 'none' }),
              }}
            >
              <MenuIcon />
            </IconButton>
            {/* Box helps to only right-flush Dark/Bright Button */}
              <Box display='flex' flexGrow={1}>
                  
                  {/* <Assessment className={classes.icon} /> */}
                  <Typography variant ="h6" noWrap component="div" >
                    Admin Dashboard WF
                  </Typography>
              
              </Box>
              {isTokenValid() ?<Typography variant ="h6" noWrap component="div" style={{textAlign:"right"}}>
                   Welcome {role}
                </Typography>:<div/>}
          </Toolbar>
        </AppBar>
        <Drawer variant="permanent" open={open}>
          <DrawerHeader>
            <IconButton onClick={handleDrawerClose}>
              {selectedtheme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
            </IconButton>
          </DrawerHeader>
          <Divider />
              <List>
                <ListItem disablePadding sx={{ display: 'block' }}>
                <ListItemButton
                  sx={{
                    minHeight: 48,
                    justifyContent: open ? 'initial' : 'center',
                    px: 2.5,
                  }}
                >
                  <ListItemIcon
                    sx={{
                      minWidth: 0,
                      mr: open ? 3 : 'auto',
                      justifyContent: 'center',
                    }}
                  >
  <NavLink to="/Admin/ApproveUsers"><Button><ApprovalIcon style={{color:"black"}}/></Button></NavLink> 
                
  </ListItemIcon>
  <ListItemText><NavLink to="/" style={{textDecoration:'None',color:'black'}}>
  Admin Approval
        </NavLink></ListItemText>
  
  </ListItemButton>
                </ListItem>
                <ListItem disablePadding sx={{ display: 'block' }}>
                      <ListItemButton
                          sx={{
                          minHeight: 48,
                          justifyContent: open ? 'initial' : 'center',
                          px: 2.5,
                          }}
                      >
                          <ListItemIcon
                          sx={{
                              minWidth: 0,
                              mr: open ? 3 : 'auto',
                              justifyContent: 'center',
                          }}
                          >
          <NavLink to="/Admin/SearchUsers"><Button><ManageSearchIcon style={{color:"black"}}/></Button></NavLink> 
                      
          </ListItemIcon>
          <ListItemText><NavLink to="/AdminSearchUsers" style={{textDecoration:'None',color:'black'}}>
            Search Users
              </NavLink></ListItemText>
          
          </ListItemButton>
                </ListItem>
                <ListItem disablePadding sx={{ display: 'block' }}>
                      <ListItemButton
                          sx={{
                          minHeight: 48,
                          justifyContent: open ? 'initial' : 'center',
                          px: 2.5,
                          }}
                      >
                          <ListItemIcon
                          sx={{
                              minWidth: 0,
                              mr: open ? 3 : 'auto',
                              justifyContent: 'center',
                          }}
                          >
          <NavLink to=""><Button onClick={()=>{
          clearToken()
          navigate("/Admin");
          window.location.reload();
          
         
        }}><LogoutIcon style={{color:"black"}}/></Button></NavLink> 
                      
          </ListItemIcon>
          <ListItemText>Logout</ListItemText>
          
          </ListItemButton>
                </ListItem>
              </List>
        
          <Divider />
        </Drawer>
      </Box>
      </ThemeProvider>
    ); 
 
}