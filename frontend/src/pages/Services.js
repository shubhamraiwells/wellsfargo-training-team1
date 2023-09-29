import Features from "./Features";
import Navbar from "./NavBar";
import { useToken } from '../context/TokenContext';
const Services = ()=>{
    const {token,role,username,isTokenValid}=useToken();
    return(

        <div className="Container">
        <Navbar />
        {isTokenValid() && <Features token={token} username={username} />}
    </div>)
}
export default Services;