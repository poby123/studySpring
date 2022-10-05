import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Board from './components/Board';
import BoardList from './components/BoardList';
import Login from './components/Login';
import { useAuth } from './components/LoginAuthHook';
import Logout from './components/Logout';
import { RequireAuth } from './components/RequireAuth';

function App() {

    const authUtils = useAuth();

    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path='/' element={<RequireAuth authUtils={authUtils}><BoardList /></RequireAuth>} />
                    <Route path='/login' element={<Login authUtils={authUtils}/>} />
                    <Route path='/logout' element={<Logout authUtils={authUtils}/>} />
                    <Route path='/boards/:boardId' element={<RequireAuth authUtils={authUtils}>
                        <Board authUtils={authUtils} />
                    </RequireAuth>} />
                    <Route path='/member/:memberId' />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;