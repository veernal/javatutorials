let object = new Object();

// object.property = value

// let person = new Object();
let person = {};
person.name = "Tom";
person.address = "Tokyo";

let emp = {
    "name": "Mike",
    "address": "New York"
};

delete emp.name;



class Student{
    constructor(n, a){
        this.name = n;
        this.age = a;
    }

    getDetails(){
        // return "Student[name: "+this.name+", age: "+this.age+"]";
        return `Student[name: ${this.name}, age: ${this.age}]`;
    }
}


let miley = new Student("Miley", 23);

miley.name 
miley["name"]
