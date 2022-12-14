//on click show the hider div and the message
//$(".item-book").click(function () {
//     const book = $(this).data('book');
//     $("#exampleModalCenter").attr("data-book", book).modal('show');
//});
//$("#exampleModalCenter").on('show.bs.modal', function(e) {
////    var book = $(this).data('book').toObject();
//    // $('#exampleModalCenterTitle').text(bookId);
//});

//var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
//var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
//  return new bootstrap.Tooltip(tooltipTriggerEl)
//})

function itemBookClick(bookId){
    var modalBook = "#modalBook" + bookId
    var modalMenu = $(modalBook);

    modalMenu.modal('show');
}

function itemBookAddToBasket(bookId){
var xhr = new XMLHttpRequest();

var body = JSON.stringify({
book_id: bookId,
count: 1
});

xhr.open("POST", '/add-book-to-basket', true);
xhr.setRequestHeader('Content-Type', 'application/json');
xhr.onload = function () {
        if (xhr.readyState == 4 && xhr.status == "200") {
                window.location.reload();
        }
        else{
            var toastMessage = document.getElementById('toastMessage');
            toastMessage.textContent = "Произошла ошибка!"
            $('#toastPlacement').toast('show')
        }
    };

xhr.send(body);

}

function itemBookDelete(bookId){
    var xhr = new XMLHttpRequest();

    xhr.open("DELETE", '/delete-book?id=' + bookId, true);
    xhr.onload = function () {
            if (xhr.readyState == 4 && xhr.status == "200") {
                    window.location.reload();
            }
            else{
                var toastMessage = document.getElementById('toastMessage');
                toastMessage.textContent = "Произошла ошибка!"
                $('#toastPlacement').toast('show')
            }
        };

    xhr.send(null);
}
