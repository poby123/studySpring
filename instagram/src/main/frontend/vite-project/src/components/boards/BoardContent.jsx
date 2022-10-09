import { useState } from "react";
import { handleAuthenticationException } from "../../states/behaviors/AuthBehavior";
import { doLikeBoard } from '../../states/behaviors/BoardBehavior';
import { useAuthContext } from '../../states/hooks/LoginAuthHook';

export default function BoardContent({ board }) {

    const [currentMemberLike, setCurrentMemberLike] = useState(board.like.currentMemberLike);
    const [numberOfLikes, setNumberOfLikes] = useState(board.like.numberOfLikes);
    const [textTruncated, setTextTruncated] = useState(true);
    const authUtils = useAuthContext();

    const onClickLike = () => {
        currentMemberLike ? setNumberOfLikes(numberOfLikes - 1) : setNumberOfLikes(numberOfLikes + 1);
        setCurrentMemberLike(!currentMemberLike);

        (async () => {
            try {
                const result = await doLikeBoard(board.id, authUtils);
                console.log(result);
            } catch (e) {
                handleAuthenticationException(e, authUtils);
            }
        })
    }

    const onClickBoardContent = () => {
        setTextTruncated(!textTruncated);
    }


    return <section className="boardContent">
        <span text={`${board.title}`} className="card-title"></span>
        <span className="d-flex">좋아요 {numberOfLikes}개</span>
        <button className="board-like-button text-danger" onClick={onClickLike}>
            <i className={`${currentMemberLike ? 'fa-solid fa-heart' : 'fa-regular fa-heart'}`}></i>
        </button>
        <p className={`card-content ${textTruncated ? 'text-truncate' : ''}`} onClick={onClickBoardContent}>
            {board.content}
        </p>
    </section>
}