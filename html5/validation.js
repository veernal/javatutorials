console.log("in valildation js");

function getLogin(e){
    // do not continue with default behaviour
    e.preventDefault();

    console.log("get login");
    // alert("done")


    // let unameElement = document.getElementById("uname");
    // let pwdElement = document.querySelector("[name='pwd']")

    // console.log(unameElement.value, pwdElement.value);
    validate("uname", "[A-Za-z]+(\s[A-Za-z]+)*", "Username is invalid");
    // validate("pwd");

}


function validate(elementId, pattern, errorMsg){
    
    let textElement = document.getElementById(elementId);
    
    let textValue = textElement.value;
    
    if(new RegExp(pattern).test(textValue)){
        console.log("valid");
        document.getElementById(elementId+"Error").innerText = "";
        
        localStorage.setItem("u", textValue);
        
        document.location.pathname = "04-nextPage.html";
        
    } else {
        console.log("invalid");
        
        document.getElementById(elementId+"Error").innerText = errorMsg;
        
        
    }
}