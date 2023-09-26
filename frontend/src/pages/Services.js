import Features from "./Features";
import Navbar from "./NavBar";
import { useToken } from '../context/TokenContext';
import {useNavigate, Navigate} from "react-router-dom"
import { useEffect } from 'react';
const Services = ()=>{
    const {token,role,username,isTokenValid}=useToken();
    const navigate = useNavigate();
    console.log("Us:"+username);
    console.log("Role:"+role);
    if(role==null)
      alert('Kindly Login to view Services offered');
    else 
      if(role.trim()!='ROLE_USER')
        alert('Kindly Login to view Services offered');
    return(
        // isTokenValid() ? <div className="Container">
        // <Navbar />
        // <Album token={token}/>
        <div className="Container">
        <Navbar />
        {(isTokenValid() && role!=null && role.trim()=='ROLE_USER') && <Features token={token} username={username} />}
        {!isTokenValid() && <Navigate to='/Login'/> }
      
    </div>)
}
export default Services;