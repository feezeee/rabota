function incFunc(bookId){
    var quantityId = 'quantity' + bookId;
    var quantity = document.getElementById(quantityId);
    var number = parseInt(quantity.value);
    number += 1;
    if(number < parseInt(quantity.min))
    {
        number = parseInt(quantity.min);
    }
    if(number > parseInt(quantity.max))
    {
        number = parseInt(quantity.max);
    }

    quantity.value = number;

}

function decFunc(bookId){
    var quantityId = 'quantity' + bookId;
    var quantity = document.getElementById(quantityId);
    var number = parseInt(quantity.value);
    number -= 1;
    if(number < parseInt(quantity.min))
    {
        number = parseInt(quantity.min);
    }
    if(number > parseInt(quantity.max))
    {
        number = parseInt(quantity.max);
    }

    quantity.value = number;

}


function deleteBookFromBasket(bookId){
    var xhr = new XMLHttpRequest();

    xhr.open("DELETE", '/delete-book-from-basket' + '?book-id=' + bookId, true);
    xhr.send(null);
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

}