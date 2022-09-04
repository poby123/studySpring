const cardContents = document.querySelectorAll('.text-truncate');
cardContents.forEach((card) => {
    card.addEventListener('click', (e) => {
        e.currentTarget.classList.toggle('text-truncate');
    })
})


function commentSave(e) {
    e.preventDefault();
    const boardId = Number(document.querySelector('#boardId').value);
    const content = document.querySelector('#commentContent').value;

    const saveDto = {
        'boardId': boardId,
        'content': content
    }

    console.log(saveDto);

    $.ajax({
        type: "POST",
        url: "http://wj-code-server.com:8080/api/comment",
        data: JSON.stringify(saveDto),
        dataType: 'json',
        contentType: 'application/json',
        success: (data) => {
            console.log(data);
        },
        error: (err) => {
            console.log(err);
        }
    });
}