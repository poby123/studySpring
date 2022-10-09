export default function BoardHeader({ board }) {
    return (
        <section>
            <p>
                <a className="board-writer">
                    {board.writer.username}
                </a>
            </p>
        </section>
    )
}