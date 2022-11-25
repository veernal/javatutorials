

interface Superhero{
	showPower: any;
}

class Superman implements Superhero{
	showPower= function():void{
		console.log("Superman can fly");
	};
}


let clark = new Superman();
clark.showPower();
