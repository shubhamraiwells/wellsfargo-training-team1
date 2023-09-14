import axios from 'axios';
const composeToken = (token) => token ? { Authorization: `Bearer ${token}` } : {};

const apiCall = (url, method, body = {}, token = '') => axios({
    method,
    url,
    data: body,
    headers: {
        ...composeToken(token),
        'Content-Type':'application/json'
    }

});

export default apiCall;
