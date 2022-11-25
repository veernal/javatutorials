let audio1 = document.getElementById("player");
let audio2 = document.getElementById("player1");
let audio3 = document.getElementById("player2");

var song1PlayStopBTN = document.getElementById("song1PlayStopBTN");
var song2PlayStopBTN = document.getElementById("song2PlayStopBTN");
var song3PlayStopBTN = document.getElementById("song3PlayStopBTN");

var count =0;
function playStop1(){
    if(count==0){
        count = 1;
        audio1.play();
        song1PlayStopBTN.innerHTML = "Stop Playing!"
        console.log("playing First song")
    }else{
        count=0;
        audio1.currentTime = 0;
        audio1.pause()
        song1PlayStopBTN.innerHTML = "Start Playing again"
        console.log("stop playing")
    }
}

function playStop2(){
    if(count==0){
        count = 1;
        audio2.play();
        song2PlayStopBTN.innerHTML = "Stop Playing!"
        console.log("playing second song")
    }else{
        count=0;
        audio2.currentTime = 0;
        audio2.pause()
        song2PlayStopBTN.innerHTML = "Start Playing again"
        console.log("stop playing")
    }
}

function playStop3(){
    if(count==0){
        count = 1;
        audio3.play();
        song3PlayStopBTN.innerHTML = "Stop Playing!"
        console.log("playing third song")
    }else{
        count=0;
        audio3.currentTime = 0;
        audio3.pause()
        song3PlayStopBTN.innerHTML = "Start Playing again"
        console.log("stop playing")
    }
}