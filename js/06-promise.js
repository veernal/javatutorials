let readPromise = 
    new Promise((resolve, reject)=>{
        console.log("reading a file");
        if(false) {// file found
            console.log("resolving")
            resolve("hello friend");
        } else {
            console.log("rejecting")
            reject("file not found")
        }
    });

readPromise
    .then(data=>{console.log(data); return data.toUpperCase();})
    .then(data=>{console.log(data)})
.catch(err=>{console.log(err)})
    