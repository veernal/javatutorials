function getFormValue(ev) {
    ev.preventDefault();

    const form = document.getElementById('form1');

    const allInputs = form.querySelectorAll('input,select')

    let formObject = {};
    allInputs.forEach(e => {
        const key = e.getAttribute('name')

        if(key) {
            formObject[key] = e.value;
        }
    }
        )
        console.log(formObject);
    
}