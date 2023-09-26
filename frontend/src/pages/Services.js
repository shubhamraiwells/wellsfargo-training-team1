import Features from "./Features";
import Navbar from "./NavBar";
import { useToken } from '../context/TokenContext';
import {useNavigate} from "react-router-dom"
import { useEffect } from 'react';
const Services = ()=>{
    const {token,role,username,isTokenValid}=useToken();
    const navigate = useNavigate();
    console.log("Us:"+username);
    console.log("Role:"+role);
    useEffect(()=>{
        if(role!=null){
          const authorizedFlag = role.trim()=="ROLE_USER";    
          console.log("Role:"+role);
          console.log("Username:"+username)
          console.log(role.trim()=="ROLE_USER")
        }
        else{
          alert("Kindly log in to view this page");
          console.log("Navigating...")
          navigate('/Login');
      }
      },[])
    return(
        // isTokenValid() ? <div className="Container">
        // <Navbar />
        // <Album token={token}/>
        <div className="Container">
        <Navbar />
        {isTokenValid() && <Features token={token} username={username} />}
    </div>)
}
export default Services;