import { cloneElement } from "react";
import { Navigate, useLocation } from "react-router-dom";
import { useAuthContext } from "../../states/hooks/LoginAuthHook";

export function RequireAuth({ children }) {
    let location = useLocation();
    const authUtils = useAuthContext();

    if (!authUtils.isLogined()) {
        return <Navigate to="/login" state={{ from: location }} replace />;
    } else {
        return cloneElement(children);
    }
}