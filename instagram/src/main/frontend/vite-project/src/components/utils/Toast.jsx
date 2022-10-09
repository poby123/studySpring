import { useEffect } from "react";
import { useToastContext } from "../../states/hooks/ToastHook";

const Toast = ({toast}) => {
    const {toasts, appendToast, removeToast} = useToastContext();
    const { id, value, color, visible, date }  = toast;

    return (
        <div className={`toast bg-${color} show text-white max-vw-50`} role="alert" aria-live="assertive" aria-atomic="true">
            <div className="toast-header">
                <strong className="me-auto">Instagram</strong>
                <small className="text-muted">{id}</small>
                <button type="button" className="btn-close" data-bs-dismiss="toast" aria-label="Close" onClick={() => removeToast(id)}></button>
            </div>
            <div className="toast-body">
                {value}
            </div>
        </div>
    )
}

export const Toasts = () => {
    const {toasts, appendToast, removeToast} = useToastContext();

    useEffect(()=>{
        if(toasts.length > 0 ){
            const timer = setTimeout(() => { removeToast(toasts[0].id) }, 3000);
            return () => { clearTimeout(timer) }
        }
     }, [toasts]);

    const toastItems = toasts.map((t) => <Toast key={t.id} toast={t} />)

    return (
        <section>
            <div aria-live="polite" aria-atomic="true" className="position-relative">
                <div className="toast-container position-fixed bottom-0 end-0 ">
                    {toastItems}
                </div>
            </div>
        </section>
    )
}