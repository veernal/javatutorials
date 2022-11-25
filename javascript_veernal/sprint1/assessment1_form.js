const form = document.querySelector('#form_registration');
const fullname = document.querySelector('#fullname');
const email = document.querySelector('#email');
const phone_number = document.querySelector('#phone_number');

// form.addEventListener('submit', (event)=>{
//     event.preventDefault();
//     validateForm();
// });

function validateForm() {
    //Full name
    if(fullname.value.trim()==''){
        setError(fullname, 'Name cannot be empty');
    }else if(typeof fullname.value === 'number' || !isValidName(fullname.value)){
        setError(fullname, 'Alphabets only');     
        }
    else if(fullname.value.trim().length < 5 || fullname.value.trim().length >15){
        setError(fullname, 'Name must be min 5 and max 15 characters')
    }

    //EMAIL
    if(email.value.trim() ==''){
        setError(email, 'Provide email address');
    }
    else if(!isEmailValid(email.value)){
        setError(email, 'Provide valid email address')
    }

    //PHONE NUMBER
    if(phone_number.value.trim()==''){
        setError(phone_number, 'Please provide phone Number');
    }else if(!isPhoneNumberValid(phone_number.value)){
        setError(phone_number,'Phone number should have 10 digits')
    }

}

function setError(element, errorMessage){
    const parent = element.parentElement;
    parent.classList.add('error');
    const paragraph = parent.querySelector('p');
    paragraph.textContent = errorMessage;
}

function isEmailValid(email){
    const reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return reg.test(String(email).toLowerCase());
}

function isPhoneNumberValid(phone_number){
    const reg = /^\d{10}$/;
    return reg.test(phone_number);
}

function isValidName(fullname){
    const reg= /^[A-Za-z]+$/;
    return reg.test(fullname)
}