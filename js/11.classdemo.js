function Person(name, age){

   

}

let mike = new Person()


// ----------

class Person{
    constructor(name){
        this.name = name;
    }

    set username(n){
        this.name = n;
    }
    get username(){
        return this.name;
    }
}


let carl = new Person("Carl");

carl.username = "Carl Hooper"; // setter
carl.username // getter
