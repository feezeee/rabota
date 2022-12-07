function updateCategory(){
    var xhr = new XMLHttpRequest();

    var categoryId = document.getElementById('categoryId');
    var categoryName = document.getElementById('categoryName');

    var body = JSON.stringify({
        id: categoryId.value,
        name: categoryName.value
    });

    xhr.open("PUT", '/update-category', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function () {
            if (xhr.readyState == 4 && xhr.status == "200") {
                    window.location.href = "/category";
            }
            else{
                var toastMessage = document.getElementById('toastMessage');
                toastMessage.textContent = "Произошла ошибка!"
                $('#toastPlacement').toast('show')
            }
        };


    xhr.send(body);

}