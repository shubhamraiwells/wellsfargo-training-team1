import AdminNavbar from "./AdminNavbar";
import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
function createData(number, item, qty, price) {
    return { number, item, qty, price };
   }
     
   const rows = [
    createData(1, "Apple", 5, 3),
    createData(2, "Orange", 2, 2),
    createData(3, "Grapes", 3, 1),
    createData(4, "Tomato", 2, 1.6),
    createData(5, "Mango", 1.5, 4)
   ];
export default function ApproveUsers(){

    return(<div className="container">
        <AdminNavbar/>
        
<div style={{marginTop:"5%"}}>
    <Grid
    container
    direction="column"
    justifyContent="center"
    alignItems="center"
    spacing={2}
    >
    <Grid item xs="auto">
        <Card sx={{ height: "auto", display: 'flex', flexDirection: 'column', border: '2px solid black' }}>                    
                        <CardContent sx={{ flexGrow: 1 }}>
                        <Typography gutterBottom variant="h5" component="h2">
                        User: Satya
                        </Typography>
                        </CardContent>
                        <CardActions sx={{display:'flex', justifyContent:'center', flexDirection:'column'}}>
                            <div style={{display:'flex', justifyContent:'center'}}>
                        <Button size="large" variant="contained" >View</Button>
                            </div>
                        <div style={{display:"flex", flexDirection:"row", margin:"3%"}}>
                        <Button size="large" variant="contained" style={{marginRight:"1%", backgroundColor:"Green"}}>Approve</Button>
                        <Button size="large" variant="contained"style={{marginRight:"1%", backgroundColor:"Purple"}}>Reject</Button>
                        </div>
                        </CardActions>
        </Card>    
    </Grid>
    <Grid item xs="auto">
        <Card sx={{ height: "auto", display: 'flex', flexDirection: 'column', border: '2px solid black' }}>                    
                        <CardContent sx={{ flexGrow: 1 }}>
                        <Typography gutterBottom variant="h5" component="h2">
                        User: Shubham
                        </Typography>
                        </CardContent>
                        <CardActions sx={{display:'flex', justifyContent:'center', flexDirection:'column'}}>
                            <div style={{display:'flex', justifyContent:'center'}}>
                        <Button size="large" variant="contained" >View</Button>
                            </div>
                        <div style={{display:"flex", flexDirection:"row", margin:"3%"}}>
                        <Button size="large" variant="contained" style={{marginRight:"1%", backgroundColor:"Green"}}>Approve</Button>
                        <Button size="large" variant="contained"style={{marginRight:"1%", backgroundColor:"Purple"}}>Reject</Button>
                        </div>
                        </CardActions>
        </Card>    
    </Grid>
    <Grid item xs="auto">
        <Card sx={{ height: "auto", display: 'flex', flexDirection: 'column', border: '2px solid black', maxWidth:"100%"}}>                    
                        <CardContent sx={{ flexGrow: 1 }}>
                        <Typography gutterBottom variant="h5" component="h2">
                        User: Nivedha
                        </Typography>
                        </CardContent>
                        <CardActions sx={{display:'flex', justifyContent:'center', flexDirection:'column'}}>
                            <div style={{display:'flex', justifyContent:'center'}}>
                        <Button size="large" variant="contained" >View</Button>
                            </div>
                        <div style={{display:"flex", flexDirection:"row", margin:"3%"}}>
                        <Button size="large" variant="contained" style={{marginRight:"1%", backgroundColor:"Green"}}>Approve</Button>
                        <Button size="large" variant="contained"style={{marginRight:"1%", backgroundColor:"Purple"}}>Reject</Button>
                        </div>
                        </CardActions>
        </Card>    
    </Grid>
    </Grid>
</div>
    </div>)
}