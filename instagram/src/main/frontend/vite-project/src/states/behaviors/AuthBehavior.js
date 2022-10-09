import axios from "axios";
import { LoginDto } from "../dtos/AuthDto";
import { baseURL, requestHeader } from "../constants/requestConstants";

export const handleAuthenticationException = async (error, authUtils) => {

    if (!error.response || !error.response.data) {
        return null;
    }

    switch (error.response.data.data) {
        case 'ACCOUNT_MISMATCH':
            console.log('아이디 혹은 비밀번호가 일치하지 않습니다!');
            break;

        case 'EXPIRED_ACCESS_TOKEN':
            await doReissue(authUtils);
            console.log('Do reissue!');
            break;

        case 'ERR_BAD_REQUEST':
            console.log('재로그인 필요!');
            break;
    }
}

const doReissue = async (authUtils) => {
    try {
        const response = await axios.post('/reissue', null, { headers: requestHeader(authUtils), baseURL: baseURL, withCredentials: true });
        (response && response.data) && authUtils.setLogined(response.data.data.accessToken);
    } catch (e) {
        console.log(e);
        authUtils.setLogout();
    }
}

export const doLogin = async (username, password, authUtils) => {
    try {
        const result = await axios.post('/login', new LoginDto(username, password), { baseURL: baseURL, withCredentials: true });
        const { data } = result;
        authUtils.setLogined(data.data.accessToken)
        return true;
    }
    catch (e) {
        if (e.response.data) {
            throw new Error(e.response.data.message);
        }
        throw new Error(e);
    }
}

export const doLogout = async (authUtils) => {
    try {
        const result = await axios.post('/logout', null, { headers: requestHeader(authUtils), baseURL: baseURL, withCredentials: true });
        console.log(result);
        authUtils.setLogout();
    }
    catch (e) {
        throw new Error(e);
    }
} 