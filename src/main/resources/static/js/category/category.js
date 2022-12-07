function itemCategoryDelete(categoryId){
    var xhr = new XMLHttpRequest();

    xhr.open("DELETE", '/delete-category?id=' + categoryId, true);
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
