import { useContext, useState, createContext } from 'react';

const BoardContext = createContext();

export const useBoardContext = () => {
    return useContext(BoardContext);
}

export const useBoards = () => {
    const [boards, _setBoards] = useState();

    const getBoards = () => {
        return boards.clone();
    }

    const setBoards = (_boards) => {
        return _setBoards(_boards);
    }
}

export const BoardProvider = ({ children }) => {
    const boardUtils = useBoards();

    return <BoardContext.Provider value={boardUtils}>
        {children}
    </BoardContext.Provider>
}