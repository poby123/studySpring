import { useEffect } from "react";
import { Navigate } from "react-router-dom";
import { doLogout } from "../states/behaviors/AuthBehavior";

const Logout = ({authUtils}) => {
    const { authState, setLogined, setLogout, isLogined } = authUtils;

    useEffect(() => {
        if (isLogined()) {
            doLogout(setLogout);
        }
    })

    return (
        <Navigate to={'/'} redirect />
    )
}

export default Logout;