import loginStateInstance from '../states/LoginState';
import { useState } from 'react';

export const useAuth = () => {
    const [authState, setAuthState] = useState(loginStateInstance);

    const isLogined = () => {
        return loginStateInstance.isLogined();
    }

    const setLogined = (token) => {
        loginStateInstance.setLogined(token);
        doRendering();
    }

    const setLogout = () => {
        loginStateInstance.setLogout();
        doRendering();
    }

    const doRendering = () => {
        const newValues = loginStateInstance.getValues();
        setAuthState({...authState, newValues});
    }


    return {authState, setLogined, setLogout, isLogined};
}