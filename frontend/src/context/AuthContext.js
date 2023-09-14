import createDataContext from "./createDataContext";
import jwtDecode from "jwt-decode";
import authReducer from '../reducers/AuthReducer';
import {signUp,signIn,setCurrentUser} from '../actions/authActions';
export const initialstate={
    user:localStorage.getItem('token')?jwtDecode(localStorage.getItem('token')).sub:null,
    isAuthenticated:localStorage.getItem('token')?true:false,
    signUpErr:'',
    signInErr:''
}

export const {Context,Provider}=createDataContext(authReducer,{signIn,signUp,setCurrentUser},initialstate,);

