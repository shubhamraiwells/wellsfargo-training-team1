
import apiCall from "../apiCall/apiCall";


export const signUp = (dispatch) => async (user,url) => {
    try {
        dispatch({ type: 'SIGNUP_USER_LOADING' })
        const res = await apiCall(url, 'POST', user)
        dispatch({ type: 'SIGNUP_USER_SUCCESS', payload: res })
        return res;
    } catch (err) {
        return dispatch({ type: 'SIGNUP_USER_FAILURE', payload: err.response.data })
    }
}

export const signIn = (dispatch) => async (user,url) => {
    try {
        dispatch({ type: 'SET_CURRENT_USER_LOADING' })
        const res = await apiCall(url, 'POST', user);
        dispatch({ type: 'SIGNIN_USER_SUCCESS', payload: res});
        return res;
    } catch (err) {
        return dispatch({ type: 'SIGNIN_USER_FAILURE', payload: err.response.data })
    }
}

export const setCurrentUser=(dispatch)=>async (Cookies,jwtDecode)=>{
    try{
        dispatch({type:'SETTING_CURRENT_USER_LOADING'})
        const payload=jwtDecode(Cookies.get('token'));
        dispatch({type:'SET_CURRENT_USER_SUCCESS',payload});
    }catch(err){
        dispatch({type:'SET_CURRENT_USER_FAILURE',payload:err.response.data})
    }
}