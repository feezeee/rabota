function updateBook(){
    var xhr = new XMLHttpRequest();

    var bookId = document.getElementById('bookId');
    var bookName = document.getElementById('bookName');
    var bookDescription = document.getElementById('bookDescription');
    var bookPrice = document.getElementById('bookPrice');
    var bookCount = document.getElementById('bookCount');


    var body = JSON.stringify({
        id: bookId.value,
        name: bookName.value,
        description: bookDescription.value,
        price: bookPrice.value,
        count: bookCount.value
    });

    xhr.open("PUT", '/update-book', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function () {
            if (xhr.readyState == 4 && xhr.status == "200") {
                    window.location.href = "/book";
            }
            else{
                var toastMessage = document.getElementById('toastMessage');
                toastMessage.textContent = "Произошла ошибка!"
                $('#toastPlacement').toast('show')
            }
        };


    xhr.send(body);

}