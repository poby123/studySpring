import axios from "axios";
import { LoginDto } from "../dtos/AuthDto";
import loginStateInstance from "../LoginState";


export const handleAuthenticationException = (error, setLogined, setLogout) => {
    if (!error.response || !error.response.data) {
        return null;
    }

    switch (error.response.data.data) {
        case 'ACCOUNT_MISMATCH':
            console.log('아이디 혹은 비밀번호가 일치하지 않습니다!');
            break;

        case 'EXPIRED_ACCESS_TOKEN':
            doReissue(setLogined, setLogout);
            console.log('Do reissue!');
            break;

        case 'ERR_BAD_REQUEST':
            console.log('재로그인 필요!');
            break;
    }
}

const doReissue = async (setLogined, setLogout) => {
    try {
        const { data } = await axios.post('/reissue', null, { headers: { 'Authorization': 'Bearer ' + loginStateInstance.getToken() }, baseURL: 'http://wj-code-server.com:8080/', withCredentials: true });
        setLogined(data.data.accessToken);
    } catch (e) {
        setLogout();
    }
}

export const doLogin = async (username, password, setLogined) => {
    try {
        const result = await axios.post('/login', new LoginDto(username, password), { baseURL: 'http://wj-code-server.com:8080/', withCredentials: true });

        const { data } = result;
        setLogined(data.data.accessToken)
        return true;
    }
    catch (e) {
        throw new Error(e);
        // handleAuthenticationException(e, setLogined, setLogout);
    }
}

export const doLogout = async(setLogout) => {
    try {
        const result = await axios.post('/logout', null, { headers: { 'Authorization': 'Bearer ' + loginStateInstance.getToken() }, baseURL: 'http://wj-code-server.com:8080/', withCredentials: true });
        console.log(result);
        setLogout();
    }
    catch (e) {
        throw new Error(e);
        // handleAuthenticationException(e, setLogined, setLogout);
    }
} 