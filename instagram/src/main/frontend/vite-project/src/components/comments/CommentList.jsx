import { Link } from "react-router-dom"

export default function CommentList({ comments }) {

    const commentItems = comments.map((comment) => {
        
        return (
            <div key={comment.commentId} className="card-body border p-1">
                <p>
                    <Link to={`/member/${comment.member.username}`}>{comment.member.username}</Link>
                </p>
                <p className="align-middle h-100">
                    {comment.content}
                </p>
            </div>
        )
    })

    return <section id="comment-list" className="col card min-vh-50">
        {commentItems}
    </section>
} 