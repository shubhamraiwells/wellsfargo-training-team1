import * as React from 'react';
import { NavLink } from "react-router-dom";
import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import SavingsIcon from '@mui/icons-material/Savings';
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
import Link from '@mui/material/Link';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { purple, blue } from '@mui/material/colors';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import AdminPanelSettingsIcon from '@mui/icons-material/AdminPanelSettings';

function Copyright() {
  return (
    <Typography variant="body2" color="text.secondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}


const defaultTheme = createTheme();

export default function HomePage() {
  return (
    <ThemeProvider theme={defaultTheme}>
      <CssBaseline />
      <AppBar position="relative" style={{ background: "#101073" }}>
        <Toolbar>
          <SavingsIcon style={{ color: "white" }} sx={{ mr: 2 }} />
          <Typography variant="h6" noWrap>
            WF BANK
          </Typography>
        </Toolbar>
      </AppBar>
      <main>
        {/* Hero unit */}
        <Box
          sx={{
            bgcolor: 'background.paper',
            pt: 8,
            pb: 6,
          }}
        >
          <Container maxWidth="sm">
            <Typography
              component="h1"
              variant="h2"
              align="center"
              color="text.primary"
              gutterBottom
            >
              WF Net Banking
            </Typography>

            <Typography variant="h5" align="center" color="text.secondary" paragraph>
              Welcome to WF Banking Services, where trust meets innovation.
              We are dedicated to providing exceptional financial services tailored to your needs,
              ensuring a brighter and more prosperous future for you.
              Join our community to Celebrate Financial Freedom and Secure Futures!
            </Typography>
            <Stack
              sx={{ pt: 4 }}
              direction="row"
              spacing={2}
              justifyContent="center"
            >
              <NavLink to="/Login" style={{ "textDecoration": "none" }}><Button style={{ background: "#101073" }} size="large" variant="contained" startIcon={<AccountCircleIcon />}>User</Button></NavLink>
              <NavLink to="/Admin" style={{ textDecoration: "none", cursor: "pointer" }}><Button style={{ background: "#101073" }} size="large" variant="contained" startIcon={<AdminPanelSettingsIcon />}>Admin</Button></NavLink>

            </Stack>
          </Container>
        </Box>
        <Container sx={{ py: 8 }} maxWidth="md">
          {/* End hero unit */}
          <Grid container spacing={4}>

            <Grid item key="1" xs={12} sm={6} md={4}>
              <Card
                sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
              >
                <CardMedia
                  component="div"
                  sx={{
                    // 16:9
                    pt: '56.25%',
                  }}
                  image="https://i.pinimg.com/236x/e8/64/4f/e8644f6c2dc87a743b5077116bf4b93c.jpg"
                />
                <CardContent sx={{ flexGrow: 1 }}>
                  <Typography gutterBottom variant="h5" component="h2">
                    Personal Loan
                  </Typography>
                  <Typography>
                    Get quick funds at best interest rates!
                  </Typography>
                </CardContent>

              </Card>
            </Grid>
            <Grid item key="2" xs={12} sm={6} md={4}>
              <Card
                sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
              >
                <CardMedia
                  component="div"
                  sx={{
                    // 16:9
                    pt: '56.25%',
                  }}
                  image="https://i.pinimg.com/236x/d6/24/46/d6244636a907eb2d654a42cef2fd792c.jpg"
                />
                <CardContent sx={{ flexGrow: 1 }}>
                  <Typography gutterBottom variant="h5" component="h2">
                    Credit Card
                  </Typography>
                  <Typography>
                    Grab the exclusive offers now!
                  </Typography>
                </CardContent>

              </Card>
            </Grid>
            <Grid item key="3" xs={12} sm={6} md={4}>
              <Card
                sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
              >
                <CardMedia
                  component="div"
                  sx={{
                    // 16:9
                    pt: '56.25%',
                  }}
                  image="https://i.pinimg.com/236x/ea/58/1f/ea581faa2e6858a1f75204496a7ab83e.jpg"
                />
                <CardContent sx={{ flexGrow: 1 }}>
                  <Typography gutterBottom variant="h5" component="h2">
                    Home Loan
                  </Typography>
                  <Typography>
                    Exclusive offer processing fee, don't miss it!
                  </Typography>
                </CardContent>

              </Card>
            </Grid>
          </Grid>
        </Container>
      </main>
      {/* Footer */}
      <Box sx={{ bgcolor: 'background.paper', p: 6 }} component="footer">
        <Typography variant="h6" align="center" gutterBottom>
          Contact Us
        </Typography>
        <Typography
          variant="subtitle1"
          align="center"
          color="text.secondary"
          component="p"
        >
          For queries react out to queries@wfbanking.com
        </Typography>
        <Copyright />
      </Box>
      {/* End footer */}
    </ThemeProvider>
  );
}