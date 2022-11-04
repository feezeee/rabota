//on click show the hider div and the message
//$(".item-book").click(function () {
//     const book = $(this).data('book');
//     $("#exampleModalCenter").attr("data-book", book).modal('show');
//});
$("#exampleModalCenter").on('show.bs.modal', function(e) {
//    var book = $(this).data('book').toObject();
    // $('#exampleModalCenterTitle').text(bookId);
});

function itemBookClick(bookId){
    var modalBook = "#modalBook" + bookId
    var modalMenu = $(modalBook);

    modalMenu.modal('show');
}
