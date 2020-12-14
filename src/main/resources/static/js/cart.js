function increase(obj) {
    obj.parentNode.children[1].value = parseInt(obj.parentNode.children[1].value) + 1;
}
function decrease(obj) {
    if (obj.parentNode.children[1].value > 0)
        obj.parentNode.children[1].value = parseInt(obj.parentNode.children[1].value) - 1;
}
function allSelect() {
    var buys = document.getElementsByName("buy");
    for (var i = 0; i < buys.length; i++) {
        buys[i].checked = true;
    }
}
function allNotSelect() {
    var buys = document.getElementsByName("buy");
    for (var i = 0; i < buys.length; i++) {
        buys[i].checked = false;
    }
}
function del(obj) {
    obj.parentNode.parentNode.parentNode.remove();
}
function sum() {
    var a = document.getElementsByClassName("price");
    var b = document.getElementsByClassName("amount");
    var buys = document.getElementsByName("buy");
    var s = 0;
    for (var i = 0; i < buys.length; i++) {
        if (buys[i].checked)
            s += parseFloat(a[i].innerText, 10) * parseFloat(b[i].value, 10);
    }
    document.getElementById('allPrice').innerText = s;
}
function delSelect() {
    var buys = document.getElementsByName("buy");
    for (var i = 0; i < buys.length; i++) {
        if (buys[i].checked == true)
            del(buys[i]);
    }
}
function check(){
    var n=0;
    var buys = document.getElementsByName("buy");
    for (var i = 0; i < buys.length; i++) {
        if (buys[i].checked == true)
            n++;
    }
    if(n==0)
        alert("请选择商品！")
    else{
        document.getElementById ('buy').click ();
    }
}