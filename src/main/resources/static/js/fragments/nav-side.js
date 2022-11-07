// Please see documentation at https://docs.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.
//function fillInputs(form) {
//    let url = new URL(window.location.href);
//    if (!url.search) return;
//    for (let [name, value] of url.searchParams) {
//        form.elements[name].value = value;
//    }
//}
//fillInputs(document.forms.test);
function btnNavMenuClick(){
    var sidebar = document.querySelector(".sidebar");
    sidebar.classList.toggle("open");
    menuBtnChange();
}

function menuBtnChange() {
    var sidebar = document.querySelector(".sidebar");
    var closeBtn = document.querySelector("#btn_nav_menu");
    if (sidebar.classList.contains("open")) {
                    closeBtn.classList.replace("bx-menu", "bx-menu-alt-right");//replacing the iocns class
                } else {
                    closeBtn.classList.replace("bx-menu-alt-right", "bx-menu");//replacing the iocns class
                }
}

function onMouseEnterLi(elementId){
    document.getElementById(elementId).classList.add('bx-tada');
}
function onMouseLeaveLi(elementId){
    document.getElementById(elementId).classList.remove('bx-tada');
}

function onMouseEnterLogOut(logOutElement){
    logOutElement.classList.remove('bx-log-out');
    logOutElement.classList.add('bxs-log-out');
}
function onMouseLeaveLogOut(logOutElement){
    logOutElement.classList.remove('bxs-log-out');
    logOutElement.classList.add('bx-log-out');
}
