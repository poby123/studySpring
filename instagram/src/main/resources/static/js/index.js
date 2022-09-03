const cardContents = document.querySelectorAll('.text-truncate');
cardContents.forEach((card) => {
    card.addEventListener('click', (e) => {
        e.currentTarget.classList.remove('text-truncate');
    })
})