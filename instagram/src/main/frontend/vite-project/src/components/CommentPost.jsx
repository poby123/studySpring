import axios from 'axios';
import { useState } from 'react';
import loginStateInstance from "../states/LoginState";

export function CommentPost({ boardId, onPostComment }) {
    const [comment, setComment] = useState('');

    const onSubmit = (e) => {
        e.preventDefault();

        const commentDto = {
            'boardId': boardId,
            'content': comment
        };

        axios.post(`/api/comment`, commentDto, { headers: { 'Authorization': 'Bearer ' + loginStateInstance.getToken() }, baseURL: 'http://wj-code-server.com:8080/' })
            .then((response) => {
                onPostComment(response.data.data);
                setComment('');
            })
            .catch(error => console.error(error))
    }

    const onChangeComment = (e) => {
        setComment(e.currentTarget.value);
    }

    return (
        <section className="col card">
            <form className="g-3 align-items-center w-100 border" onSubmit={onSubmit}>
                <div className="col-md-auto">
                    <input type="text" className="form-control" onChange={onChangeComment} value={comment} required />
                </div>
                <div className="col-md-auto">
                    <button type="submit" className="btn btn-primary">Submit</button>
                </div>
            </form>
        </section>
    )
}