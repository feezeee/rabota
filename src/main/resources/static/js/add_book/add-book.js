function addBook(){
    var xhr = new XMLHttpRequest();

    var bookName = document.getElementById('bookName');
    var bookDescription = document.getElementById('bookDescription');
    var bookPrice = document.getElementById('bookPrice');
    var bookCount = document.getElementById('bookCount');


    var body = JSON.stringify({
        name: bookName.value,
        description: bookDescription.value,
        price: bookPrice.value,
        count: bookCount.value
    });

    xhr.open("POST", '/add-book', true);
    xhr.setRequestHeader('Content-Type', 'application/json');


    xhr.send(body);

}