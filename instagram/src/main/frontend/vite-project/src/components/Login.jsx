import { useEffect, useState } from 'react';
import { Navigate, useNavigate } from "react-router-dom";
import { doLogin } from '../states/behaviors/AuthBehavior';

export default function Login({ authUtils }) {

    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { authState, setLogined, setLogout, isLogined } = authUtils;

    useEffect(() => {
        console.log(authState);
    }, [])

    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            const result = await doLogin(username, password, setLogined, setLogout);
            console.log(result);
        } catch (e) {
            console.log(e);
        }
    }

    const onChangeUsername = (e) => {
        setUsername(e.currentTarget.value);
    }

    const onChangePassword = (e) => {
        setPassword(e.currentTarget.value);
    }

    return (
        isLogined() ? <Navigate to={'/'} redirect /> :
            <section className="login">
                <div className='container'>
                    <form className="g-3 align-items-center w-100 border" onSubmit={onSubmit}>
                        <div className="col-md-auto">
                            <input type="text" className="form-control" onChange={onChangeUsername} value={username} placeholder="username" required />
                            <input type="password" className="form-control" onChange={onChangePassword} value={password} placeholder="password" required />
                        </div>
                        <div className="col-md-auto">
                            <button type="submit" className="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </section>
    )
}