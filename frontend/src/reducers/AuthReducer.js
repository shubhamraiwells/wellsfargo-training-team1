import { initialstate } from "../context/AuthContext";
const auth=(state=initialstate,action)=>{
    switch (action.type){
        case 'SIGNUP_USER_SUCCESS':
            return {...state,user:action.payload.user}
        case 'SIGNUP_USER_FAILURE':
            return {...state,signUpErr:action.payload.error}
        case 'SET_CURRENT_USER_SUCCESS':
            return {...state,user:action.payload}
        case 'SIGNIN_USER_SUCCESS':
            return {...state,user:action.payload.user,isAuthenticated:true}
        case 'SIGNIN_USER_FAILURE':
            return {...state,signInErr:action.payload.error}
        default:
            return state

    }
}

export default auth;