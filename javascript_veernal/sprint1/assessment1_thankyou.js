const paragraph = document.querySelector('#paragraph');
const params = new URLSearchParams(window.location.search);
let formObject = {};
params.forEach((value, key) => {
    formObject[key] = value;
    paragraph.append(`${key} = ${value}`);
    paragraph.append(document.createElement('br'))
    
})
var count = 30;
        var redirect = "assessment1_form.html";
        function countDown() {
        if(count >= 0){
        document.getElementById("timer").innerHTML = count--;
        setTimeout("countDown()", 1000);
        }else{
        window.location.href = redirect;
        }
        }
        countDown();

console.log(formObject)