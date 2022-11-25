try{
    throw new Error("This is a demo error")
} catch(e){
    console.log("Error: "+e.message);
}