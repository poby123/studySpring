import axios from 'axios';
import { useEffect, useState } from 'react';
import { handleAuthenticationException } from "../states/behaviors/AuthBehavior";
import loginStateInstance from "../states/LoginState";
import BoardContent from './BoardContent';
import BoardHeader from './BoardHeader';
import ImageCarousel from './ImageCarousel';

export default function BoardList({ authUtils }) {
    const [boards, setBoards] = useState();
    const { authState, setLogined, setLogout } = authUtils;

    useEffect(() => {
        console.log('fetching...');
        axios.get('/api/boards', { headers: { 'Authorization': 'Bearer ' + loginStateInstance.getToken() }, baseURL: 'http://wj-code-server.com:8080/' })
            .then(response => {
                setBoards(response.data.data.content)
            })
            .catch(error => handleAuthenticationException(error, setLogined, setLogout))
    }, [authState]);

    useEffect(() => {
        handleScrollPosition();
    }, [boards])

    const handleScrollPosition = () => {
        const scrollPosition = sessionStorage.getItem("scrollPosition");
        if (scrollPosition) {
            window.scrollTo({ left: 0, top: parseInt(scrollPosition), "behavior": 'instant' });
        }
    };

    const handleClick = e => {
        sessionStorage.setItem("scrollPosition", window.pageYOffset);
    };

    const boardItems = boards && boards.map((board) => {
        return <article className="col card gy-3" key={board.id}>
            <div className="card-body p-2">
                <BoardHeader board={board} />
                <ImageCarousel board={board} onClickLink={handleClick} />
                <BoardContent board={board} />
            </div>
        </article>
    })

    return (
        <section className="border">
            <div className="container row gx-0 row-cols-1 row-cols-md-2 row-cols-lg-3 mx-auto border">
                {boardItems}
            </div>
        </section>
    )
}