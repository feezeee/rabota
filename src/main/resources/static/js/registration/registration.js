function registrate(){
    var xhr = new XMLHttpRequest();

    var first_name = document.getElementById('first_name');
    var last_name = document.getElementById('last_name');
    var phone_number = document.getElementById('phone_number');
    var login = document.getElementById('login');
    var password = document.getElementById('password');

    var body = JSON.stringify({
        first_name: first_name.value,
        last_name: last_name.value,
        phone_number: phone_number.value,
        login: login.value,
        password: password.value
    })

    xhr.open("POST", '/registration', true);
    xhr.setRequestHeader('Content-Type', 'application/json');


    xhr.send(body);

}