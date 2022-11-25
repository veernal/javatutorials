/*
Regular expressions
9898989898
09898989898
+919898989898
919898989898
+91 9898989898
91 9898989898
+91-9898989898
91-9898989898

credit card
    8989-9898-7878
    8989 9898 7878
    898998987878


    Basics:
        char -> .

        string -> apple
        reg -> [o]*
        reg -> [a-g]*
    
        reg -> [1-9]*

        email: 
            ([a-z]+[\\.])+....

    */


var reg = new RegExp("^analy[zs]e$");

reg.test("analyse")
reg.test("analyze")
reg.test("analyme")

// var emailReg = new RegExp("^[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*$");
var emailReg = new RegExp("^[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*@([a-zA-Z0-9]{3,8})(?:\.([a-zA-Z0-9]{2,3}))*$");

emailReg.test("arun@gmail.com")
emailReg.test("arun07@gmail.com")
emailReg.test("arun.07.kumar@gmail.com")
emailReg.test("arun.07.kumar@y7mail.com")


emailReg2 = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,8}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,2}[a-zA-Z0-9])?)*$/;
emailReg2.test("arun.07.kumar@y7mail.com")
// true

emailReg2.test("arun.07.kumar@gmailymailrediffgoogle.gmailymailrediffgoogle")
// false

emailReg2.test("arun.07.kumar@google.gmailymailrediffgoogle")
// false

emailReg2.test("arun.07.kumar@google.mail")
// true

emailReg2.test("arun.07.kumar@google.com")
// true

emailReg2.test("arun.07.kumar@google.co.in")

// true
