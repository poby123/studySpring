import axios from 'axios';
import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import { handleAuthenticationException } from "../states/behaviors/AuthBehavior";
import loginStateInstance from "../states/LoginState";
import BoardContent from "./BoardContent";
import BoardHeader from "./BoardHeader";
import CommentList from './CommentList';
import { CommentPost } from "./CommentPost";
import ImageCarousel from './ImageCarousel';

export default function Board({ authUtils }) {
    const { boardId } = useParams();
    const [board, setBoard] = useState();
    const [comments, setComments] = useState();
    const { authState, setLogined, setLogout } = authUtils;

    useEffect(() => {
        console.log('fetching...');
        axios.get(`/api/boards/${boardId}`, { headers: { 'Authorization': 'Bearer ' + loginStateInstance.getToken() }, baseURL: 'http://wj-code-server.com:8080/' })
            .then(response => {
                console.log(response);
                setBoard(response.data.data);
                setComments(response.data.data.comments);
            })
            .catch(error => handleAuthenticationException(error, setLogined, setLogout))
    }, [authState]);

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
            <CommentPost boardId={boardId} onPostComment={onPostComment} />
            <CommentList comments={comments} />
        </div>
    </section>
}