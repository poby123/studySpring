import { useEffect } from "react";
import { cloneElement } from "react";
import { Navigate, useLocation } from "react-router-dom";

export function RequireAuth({ children, authUtils }) {
    let location = useLocation();
    const { authState, isLogined } = authUtils;

    useEffect(() => {
        console.log(authState, ' in requireauth');
    }, [])

    if (!isLogined()) {
        return <Navigate to="/login" state={{ from: location }} replace />;
    } else {
        return cloneElement(children, { authUtils });
    }
}