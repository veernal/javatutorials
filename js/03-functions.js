function add1(a, b){
    return a+b;
}

let add2 = function(a, b){
    return a+b;
}

let add3 = function(){
    console.log(arguments);
    let addition = 0;
    // add all numbers from the arguments
    return addition;
}

add3(3,4,5)
add3("3", 4, "5"); // 12
add3(3, null, new Object()); // 3
