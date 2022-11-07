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