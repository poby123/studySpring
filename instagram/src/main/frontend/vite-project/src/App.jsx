import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './components/auth/Login';
import Logout from './components/auth/Logout';
import { RequireAuth } from './components/auth/RequireAuth';
import Board from './components/boards/Board';
import BoardList from './components/boards/BoardList';
import { BoardProvider } from './states/hooks/BoardHook';
import { ToastProvider } from './states/hooks/ToastHook';
import { AuthProvider } from './states/hooks/LoginAuthHook';

function App() {

    return (
        <AuthProvider>
            <BoardProvider>
                <ToastProvider>
                    <BrowserRouter>
                        <Routes>
                            <Route path='/' element={<RequireAuth><BoardList /></RequireAuth>} />
                            <Route path='/login' element={<Login />} />
                            <Route path='/logout' element={<RequireAuth><Logout /></RequireAuth>} />
                            <Route path='/boards/:boardId' element={<RequireAuth><Board /></RequireAuth>} />
                            <Route path='/member/:memberId' />
                        </Routes>
                    </BrowserRouter>
                </ToastProvider>
            </BoardProvider>
        </AuthProvider>
    );
}

export default App;