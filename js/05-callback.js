
// read data from database
// filter data
// print data


function readData(from, nextTask){
    console.log("Read data from: "+from);
    let d = 123;
    nextTask(d, function(){
        return 1;
    });
}

readData("file path", function(data, filterLogic){
    console.log("Filter :"+data);
    let fl = filterLogic();
    console.log(fl)
})
