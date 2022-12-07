function updateBook(){
    var xhr = new XMLHttpRequest();

    var bookId = document.getElementById('bookId');
    var bookName = document.getElementById('bookName');
    var bookDescription = document.getElementById('bookDescription');
    var bookPrice = document.getElementById('bookPrice');
    var bookCount = document.getElementById('bookCount');
    var checkbox = document.querySelectorAll('input[type=checkbox]');
    var i = 0;

    var categories = new Array();


      for (; i < checkbox.length; i++) {
        var check = checkbox[i];

        if (check.checked) {
            var id = parseInt(check.id.slice(10));
            categories.push({id: id});
        }
      }

    var body = JSON.stringify({
        id: bookId.value,
        name: bookName.value,
        description: bookDescription.value,
        price: bookPrice.value,
        count: bookCount.value,
        categories: JSON.parse(JSON.stringify(categories))
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

var checkbox = document.querySelectorAll('input[type=checkbox]');

