import { useEffect, useState } from 'react';
import axios from 'axios';
import Board from './Board';
import ImageCarousel from './ImageCarousel';
import BoardHeader from './BoardHeader';
import BoardContent from './BoardContent';

export default function BoardList(props) {
    const [boards, setBoards] = useState();

    useEffect(() => {
        console.log('fetching...');
        axios.get('/api/boards')
            .then(response => {
                console.log('response data : ', response.data.data.content);
                setBoards(response.data.data.content)
                // console.log('boards : ', boards);
            })
            .catch(error => console.log(error))
    }, []);

    // const boardItems = boards;

    const boardItems = boards && boards.map((board) => {
        return <article className="col card gy-3" key={board.id}>
            <div className="card-body p-2">
                <BoardHeader board={board} />
                <ImageCarousel board={board} />
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