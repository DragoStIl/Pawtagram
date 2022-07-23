

body.onload = function onLoadBooks() {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };


    fetch("http://localhost:8080/feed", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(book => {
            var body = document.getElementById('body');

            var container = document.createElement('div');
            container.id = 'container'

            var image = document.createElement('img');
            image.id = 'image'
            image.src = book;

            container.appendChild(image)
            body.appendChild(container);
        }))
}

