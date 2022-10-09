import { useEffect, useState } from 'react';
import { handleAuthenticationException } from '../../states/behaviors/AuthBehavior';
import { doGetBoards } from '../../states/behaviors/BoardBehavior';
import { useAuthContext } from '../../states/hooks/LoginAuthHook';
import Loading from '../utils/Loading';
import BoardContent from './BoardContent';
import BoardHeader from './BoardHeader';
import ImageCarousel from './ImageCarousel';

export default function BoardList() {
    const [isLoading, setLoading] = useState(true);
    const [boards, setBoards] = useState();
    const authUtils = useAuthContext();

    useEffect(() => {
        console.log('fetching...');
        setLoading(true);

        (async () => {
            try {
                const result = await doGetBoards(authUtils);
                setLoading(false);
                setBoards(result.data.data.content);
            } catch (e) {
                handleAuthenticationException(e, authUtils);
            }
        })();

    }, [authUtils.authState]);


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
        isLoading ?
            <Loading />
            :
            <section className="border">
                <div className="container row gx-0 row-cols-1 row-cols-md-2 row-cols-lg-3 mx-auto border">
                    {boardItems}
                </div>
            </section>
    )
}