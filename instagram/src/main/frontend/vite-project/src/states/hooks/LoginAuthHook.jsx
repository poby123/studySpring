import { useContext, useState, createContext } from 'react';
import loginStateInstance from '../LoginState';

const AuthContext = createContext();

export const useAuthContext = () => {
    return useContext(AuthContext);
}


const useAuth = () => {
    const [authState, setAuthState] = useState(loginStateInstance.getValues());

    const isLogined = () => {
        return loginStateInstance.isLogined();
    }

    const getToken = () => {
        return loginStateInstance.getToken();
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
        setAuthState({ ...authState, newValues });
    }


    return { authState, setLogined, setLogout, isLogined, getToken };
}


export const AuthProvider = ({ children }) => {
    const authUtils = useAuth();
    return <AuthContext.Provider value={authUtils}>
        {children}
    </AuthContext.Provider>
}