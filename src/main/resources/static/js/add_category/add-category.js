function addCategory(){
    var xhr = new XMLHttpRequest();

    var categoryName = document.getElementById('categoryName');


    var body = JSON.stringify({
        name: categoryName.value,
    });

    xhr.open("POST", '/add-category', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function () {
            if (xhr.readyState == 4 && xhr.status == "201") {
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