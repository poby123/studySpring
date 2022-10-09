import { useEffect } from "react";
import { Navigate } from "react-router-dom";
import { doLogout } from "../../states/behaviors/AuthBehavior";
import { useAuthContext } from "../../states/hooks/LoginAuthHook";

const Logout = () => {
    const authUtils = useAuthContext();

    useEffect(() => {
        if (authUtils.isLogined()) {
            doLogout(authUtils);
        }
    })

    return (
        <Navigate to={'/'} redirect />
    )
}

export default Logout;