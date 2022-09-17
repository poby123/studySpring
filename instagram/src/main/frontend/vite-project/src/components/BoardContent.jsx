import { useState } from "react";
import axios from 'axios';

export default function BoardContent({board}) {

    const [currentMemberLike, setCurrentMemberLike] = useState(board.like.currentMemberLike);
    const [numberOfLikes, setNumberOfLikes] = useState(board.like.numberOfLikes);
    const [textTruncated, setTextTruncated] = useState(true);

    const onClickLike = () => {
        currentMemberLike ? setNumberOfLikes(numberOfLikes - 1) : setNumberOfLikes(numberOfLikes + 1);
        setCurrentMemberLike(!currentMemberLike);

        axios.post(`/api/boards/like/${board.id}`)
            .catch(error => console.error(error))
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