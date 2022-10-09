import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import { handleAuthenticationException } from "../../states/behaviors/AuthBehavior";
import { doGetBoard } from '../../states/behaviors/BoardBehavior';
import { useAuthContext } from '../../states/hooks/LoginAuthHook';
import { useToastContext } from "../../states/hooks/ToastHook";
import CommentList from '../comments/CommentList';
import { CommentPost } from "../comments/CommentPost";
import Loading from '../utils/Loading';
import { Toasts } from "../utils/Toast";
import BoardContent from "./BoardContent";
import BoardHeader from "./BoardHeader";
import ImageCarousel from './ImageCarousel';

export default function Board() {
    const [isLoading, setLoading] = useState(true);
    const { boardId } = useParams();
    const [board, setBoard] = useState();
    const [comments, setComments] = useState();
    const authUtils = useAuthContext();

    useEffect(() => {
        console.log('fetching...');
        setLoading(true);
        
        (async () => {
            try {
                const result = await doGetBoard(boardId, authUtils);
                console.log(result);
                setBoard(result.data.data);
                setComments(result.data.data.comments);
                setLoading(false);
            } catch (e) {
                handleAuthenticationException(e, authUtils);
            }
        })();
    }, [authUtils.authState]);

    const onPostComment = (comment) => {
        setComments([comment, ...comments])
    }

    return (
        isLoading ?
            <Loading />
            :
            board && <section className="border">
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
            </section>)
}