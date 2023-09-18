import Album from "./Album";
import Navbar from "./NavBar";
import { useToken } from '../context/TokenContext';
const Services = ()=>{
    const {token,role,username,isTokenValid}=useToken();
    return(
        // isTokenValid() ? <div className="Container">
        // <Navbar />
        // <Album token={token}/>
        <div className="Container">
        <Navbar />
        {isTokenValid() && <Album token={token} username={username} />}
    </div>)
}
export default Services;