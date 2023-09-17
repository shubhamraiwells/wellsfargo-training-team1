import React, { createContext, useContext, useEffect, useState } from 'react';
import jwt_decode from 'jwt-decode'; 
import Cookies from 'js-cookie';
const TokenContext = createContext();


export function useToken() {
  return useContext(TokenContext);
}

export function TokenProvider({ children }) {
  const [token, setToken] = useState(null);
  const [expirationTime, setExpirationTime] = useState(null);
  const [role, setRole] = useState(null);
  const [username, setUsername] = useState(null);

  useEffect(() => {
    const storedToken = Cookies.get('token');
    const storedRole = Cookies.get('role');
    const storedUsername = Cookies.get('username');

    if (storedToken && storedRole && storedUsername) {
      const decodedToken = jwt_decode(storedToken); // Decode the JWT token
      const storedExpirationTime = decodedToken.exp; // Extract the expiration time

      // Check if the token has expired
      if (storedExpirationTime > Math.floor(Date.now() / 1000)) {
        setToken(storedToken);
        setExpirationTime(storedExpirationTime);
        setRole(storedRole);
        setUsername(storedUsername);
      } else {
        // Token has expired; clear it
        Cookies.remove('token');
        Cookies.remove('role');
        Cookies.remove('username');
      }
    }
  }, []);

  const setTokenWithExpiry = (newToken, newRole, newUsername) => {
    setToken(newToken);
    setRole(newRole);
    setUsername(newUsername);

    const decodedToken = jwt_decode(newToken); // Decode the new JWT token
    const newExpirationTime = decodedToken.exp; // Extract the expiration time

    // Store the new token, role, and username in cookies
    Cookies.set('token', newToken);
    Cookies.set('expirationTime', newExpirationTime.toString());
    Cookies.set('role', newRole);
    Cookies.set('username', newUsername);

    setExpirationTime(newExpirationTime); // Update the expiration time in the context
  };

  const clearToken = () => {
    setToken(null);
    setExpirationTime(null);
    setRole(null);
    setUsername(null);

    // Clear the cookies
    Cookies.remove('token');
    Cookies.remove('expirationTime');
    Cookies.remove('role');
    Cookies.remove('username');
  };

  const isTokenValid = () => {
    const currentTime = Math.floor(Date.now() / 1000);
    return expirationTime && expirationTime > currentTime;
  };

  const value = {
    token,
    setTokenWithExpiry,
    clearToken,
    isTokenValid,
    role,
    username,
  };

  return (
    <TokenContext.Provider value={value}>{children}</TokenContext.Provider>
  );
}
