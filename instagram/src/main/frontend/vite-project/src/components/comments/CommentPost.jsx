import axios from 'axios';
import { useState } from 'react';
import { handleAuthenticationException } from '../../states/behaviors/AuthBehavior';
import { doPostComment } from '../../states/behaviors/BoardBehavior';
import { useAuthContext } from '../../states/hooks/LoginAuthHook';
import { useToastContext } from '../../states/hooks/ToastHook';
import { Toasts } from '../utils/Toast';

export function CommentPost({ boardId, onPostComment }) {
    const [comment, setComment] = useState(localStorage.getItem('commentContent') || '');
    const authUtils = useAuthContext();
    const toastUtils = useToastContext();

    const onSubmit = (e) => {
        e.preventDefault();

        const commentDto = {
            'boardId': boardId,
            'content': comment
        };

        (async () => {
            localStorage.setItem('commentContent', comment);
            try {
                const result = await doPostComment(commentDto, authUtils);
                onPostComment(result.data.data);
                setComment('');
                localStorage.removeItem('commentContent');
                toastUtils.appendToast('댓글을 성공적으로 저장했습니다', 'primary', true);

            } catch (e) {
                handleAuthenticationException(e, authUtils);
                toastUtils.appendToast('문제가 발생했었습니다. 다시 시도해주십시오!', 'danger', true);
                console.log(e);
            }
        })();
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
            <Toasts/>
        </section>
    )
}