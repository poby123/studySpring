function appendToast() {
    const toastContainer = $('.toast-container')[0];
    console.log(toastContainer);
    $(`
    <div id="liveToast" class="toast hide text-white bg-primary" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
          <strong class="me-auto">Bootstrap</strong>
          <small>11 mins ago</small>
          <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close" onclick="hideToast(event)"></button>
        </div>
        <div class="toast-body">
          Hello, world! This is a toast message.
        </div>
    </div>
    `).append(toastContainer);
}

/**
 * Hide toast
 */
function showToast(e) {
    const toast = document.getElementById('liveToast');
    toast.classList.toggle('hide');
    toast.classList.toggle('fade');
    toast.classList.toggle('show');
}

/**
 * Hide toast
 */
function hideToast(e) {
    console.log(e.currentTarget.parentNode.parentNode);
    const toast = e.currentTarget.parentNode.parentNode;
    toast.classList.toggle('hide');
    toast.classList.toggle('fade');
    toast.classList.toggle('show');
    // e.currentTarget.classList.append('fade hide');
}

/**
 * Toggle board contents truncation.
 */
const cardContents = document.querySelectorAll('.text-truncate');
cardContents.forEach(card => {
    card.addEventListener('click', e => {
        e.currentTarget.classList.toggle('text-truncate');
    });
});

/**
 * Delete posts
 */
function deletePost(boardId) {
    console.log('delete : ', boardId);
    fetch(`http://wj-code-server.com:8080/api/boards/${boardId}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            return response.json();
        }
        throw new Error('Failed to delete post !!');
    }).then(data => {
        console.log('성공:', data);
        // redirect
        // 
    }).catch(error => {
        console.error('실패:', error);
        // toast
    });
}

/**
 * Add a new comment to list dynamically.
 */
function addCommentNode(comment) {
    const commentList = document.querySelector('#comment-list');

    const container = document.createElement('div');
    container.classList.add('card-body', 'border', 'p-1', 'bg-success', 'text-white');

    const writerText = document.createElement('a');
    writerText.setAttribute('href', `/member/${comment.member.username}`);
    writerText.style = 'color: white;';
    writerText.innerText = `${comment.member.username}`;

    const writerSection = document.createElement('p');
    writerSection.appendChild(writerText);

    const commentText = document.createElement('p');
    commentText.classList.add('align-middle', 'h-100');
    commentText.innerText = `${comment.content}`;

    container.append(writerSection, commentText);
    commentList.prepend(container);
}

/**
 * Set comment save failed alert.
 * @param {Boolean} show 
 */
function setShowFailedAlert(show) {
    const alert = document.querySelector('#comment-save-failed-alert');
    if (show) {
        alert.classList.remove('d-none');
    } else {
        alert.classList.add('d-none');
    }
}

/**
 * Save comment by sending post request to a server.
 * @param {Object} e 
 */
function commentSave(e) {
    e.preventDefault();
    const boardId = Number(document.querySelector('#boardId').value);
    const contentNode = document.querySelector('#commentContent');

    const saveDto = {
        'boardId': boardId,
        'content': contentNode.value
    };

    $.ajax({
        type: "POST",
        url: "http://wj-code-server.com:8080/api/comment",
        data: JSON.stringify(saveDto),
        dataType: 'json',
        contentType: 'application/json',
        success: ({ data }) => {
            console.log(data);
            setShowFailedAlert(false);
            addCommentNode(data);
            contentNode.value = '';
        },
        error: err => {
            setShowFailedAlert(true);
        }
    });
}

/**
 * Toggle board contents truncation.
 */
const boardLikeButtons = document.querySelectorAll('.board-like-button');
boardLikeButtons.forEach(b => {
    b.addEventListener('click', boardLikeOrDisLike);
});

/**
 * 
 * @param {Object} e 
 */
function boardLikeOrDisLike(e) {

    const boardId = e.currentTarget.id;
    const iconNode = e.currentTarget.querySelector('i');
    const numOfLikeNode = document.querySelector(`#numOfLike-${boardId}`);

    $.ajax({
        type: "POST",
        url: `http://wj-code-server.com:8080/api/boards/like/${boardId}`,
        th: href = "|/api/boards/like/${board?.id}|",
        success: ({ data }) => {
            iconNode.classList.toggle('fa-regular');
            iconNode.classList.toggle('fa-solid');

            if (data) {
                numOfLikeNode.innerText = Number(numOfLikeNode.innerText) + 1;
            } else {
                numOfLikeNode.innerText = Number(numOfLikeNode.innerText) - 1;
            }
        },
        error: err => {
            console.log(err);
        }
    });
}
// th:href="|/api/boards/like/${board?.id}|"