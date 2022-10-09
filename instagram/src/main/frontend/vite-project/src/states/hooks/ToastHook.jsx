import { createContext, useState, useContext } from "react";

const ToastContext = createContext();

export const useToastContext = () => {
    return useContext(ToastContext);
}

const useToast = () => {
    const [toasts, setToasts] = useState([]);

    const randomInt = (max) => {
        return Math.floor(Math.random() * max);
    }

    const appendToast = (value, color, visible) => {
        const date = new Date();
        const newItem = { id: randomInt(100000), value, color, visible, date };
        console.log(newItem);

        if (toasts.length >= 3) {
            const [firstItem, ...others] = toasts;
            setToasts([...others, newItem])
        }
        else{
            setToasts([...toasts, newItem]);
        }
    }

    const removeToast = (id) => {
        setToasts(toasts.filter(t => t.id != id));
    }

    return { toasts, appendToast, removeToast };

}

export const ToastProvider = ({ children }) => {
    const toastUtils = useToast();
    return <ToastContext.Provider value={toastUtils}>{children}</ToastContext.Provider>
}