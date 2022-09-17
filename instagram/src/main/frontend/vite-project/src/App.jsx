import { BrowserRouter, Routes, Route } from 'react-router-dom';
import BoardList from './components/BoardList';
import Board from './components/Board';

function App() {
    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route exact path='/' element={<BoardList />} />
                    <Route path='/boards/:boardId' element={<Board/>}/>
                    <Route path='/member/:memberId' />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;