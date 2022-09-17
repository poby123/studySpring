import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import axios from 'axios';
import BoardHeader from "./BoardHeader";
import ImageCarousel from './ImageCarousel';
import CommentList from './CommentList';
import BoardContent from "./BoardContent";
import { CommentPost } from "./CommentPost";


export default function Board() {
    const { boardId } = useParams();
    const [board, setBoard] = useState();
    const [comments, setComments] = useState();

    useEffect(() => {
        console.log('fetching...');
        axios.get(`/api/boards/${boardId}`)
            .then(response => {
                console.log(response);
                setBoard(response.data.data);
                setComments(response.data.data.comments);
            })
            .catch(error => console.log(error))
    }, []);

    const onPostComment = (comment) => {
        setComments([comment, ...comments])
    }

    return board && <section className="border">
        <div className="container row gx-0 row-cols-1 mx-auto border">
            <article className="col card gy-3">
                <div className="card-body p-3">
                    <BoardHeader board={board} />
                    <ImageCarousel board={board} />
                    <BoardContent board={board} />
                </div>
            </article>
            <CommentPost boardId={boardId} onPostComment={onPostComment}/>
            <CommentList comments={comments} />
        </div>
    </section>
}