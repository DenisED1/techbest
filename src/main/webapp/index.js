var info = document.getElementById('productinformatie');
var home = document.getElementById('productenlijst');
var zoek = document.getElementById('zoekresultatenlijst');
var winkel = document.getElementById('winkelmandje');
var nummer = 0;

//STARTSCHERM
function initPage(){
	fetch('/techbest/restservices/producten')
	.then(response => response.json())
	.then(function(myJson){
		for (const product of myJson){
			var rij = document.createElement('tr');
			var td1 = document.createElement('td');
			var naam = document.createTextNode(product.naam);
			td1.appendChild(naam);
			rij.appendChild(td1);
			
			var td2 = document.createElement('td');
			var prijs = document.createTextNode('\u20AC'+product.prijs+",-");
			td2.appendChild(prijs);
			rij.appendChild(td2);
			
			var td3 = document.createElement('td');
			var btn1 = document.createElement('button');
			var bekijk = document.createTextNode('Bekijk Product');
			btn1.setAttribute('id', 'bekijk')
			btn1.appendChild(bekijk);
			td3.appendChild(btn1);
			rij.appendChild(td3);
			
			btn1.addEventListener("click", function (){bekijkProduct(product.productID);});
			
			var td4 = document.createElement('td');
			var btn2 = document.createElement('button');
			var winkelwagen = document.createTextNode('Voeg toe aan winkelwagen');
			btn2.setAttribute('id', 'winkelwagen')
			btn2.appendChild(winkelwagen);
			td4.appendChild(btn2);
			rij.appendChild(td4);
			
			btn2.addEventListener("click", function (){addWinkelmand(product.productID);});
			
			document.getElementById('table1').appendChild(rij);
		}
	})
}

//PRODUCT BEKIJKEN
function bekijkProduct(productID){
	info.style.display = "block";
	zoek.style.display = "none";
	home.style.display = "none";
	winkel.style.display = "none";
	
	fetch('/techbest/restservices/producten/id/'+productID)
	.then(response => response.json())
	.then(function(myJson){
		for (const product of myJson){     
			var naam = document.getElementById("naam");
			naam.innerHTML = product.naam;
			
			var prijs = document.getElementById("prijs");
			prijs.innerHTML = product.prijs;
		
			var kleur = document.getElementById("kleur");
			kleur.innerHTML = product.kleur;
		
			var leverancier = document.getElementById("leverancier");
			leverancier.innerHTML = product.leverancier;
		
			var processor = document.getElementById("processor");
			processor.innerHTML = product.processor;
		
			var geheugen = document.getElementById("geheugen");
			geheugen.innerHTML = product.geheugen;
		
			var opslag = document.getElementById("opslag");
			opslag.innerHTML = product.opslag;
		
			var videokaart = document.getElementById("videokaart");
			videokaart.innerHTML = product.videokaart;
		}
	})
}

//PRODUCTEN ZOEKEN
document.getElementById('zoek').addEventListener("click", function(){zoekProducten()});

function zoekProducten(){
	var Parent = document.getElementById('table2');
	while(Parent.hasChildNodes())
	{
	   Parent.removeChild(Parent.firstChild);
	}
	
	var txtPn = document.getElementById("txtPn").value;
	var txtLc = document.getElementById("txtLc").value;
	var txtKl = document.getElementById("txtKl").value;
	
	if (txtPn != "Naam product"){
		fetch('/techbest/restservices/producten/naam/'+txtPn)
		.then(response => response.json())
		.then(function(myJson){toonProducten(myJson)})
	}
	else if(txtLc != "Leverancier"){
		fetch('/techbest/restservices/producten/leverancier/'+txtLc)
		.then(response => response.json())
		.then(function(myJson){toonProducten(myJson)})
	}
	else if(txtKl != "Kleur"){
		fetch('/techbest/restservices/producten/kleur/'+txtKl)
		.then(response => response.json())
		.then(function(myJson){toonProducten(myJson)})
	}
}

//TOON GEZOCHTE PRODUCTEN
function toonProducten(myJson){	
	info.style.display = "none";
	zoek.style.display = "block";
	home.style.display = "none";
	winkel.style.display = "none";
	
	for (const product of myJson){
		var rij = document.createElement('tr');
		var td1 = document.createElement('td');
		var naam = document.createTextNode(product.naam);
		td1.appendChild(naam);
		rij.appendChild(td1);
		
		var td2 = document.createElement('td');
		var prijs = document.createTextNode('\u20AC'+product.prijs+",-");
		td2.appendChild(prijs);
		rij.appendChild(td2);
		
		var td3 = document.createElement('td');
		var btn1 = document.createElement('button');
		var bekijk = document.createTextNode('Bekijk Product');
		btn1.setAttribute('id', 'bekijk')
		btn1.appendChild(bekijk);
		td3.appendChild(btn1);
		rij.appendChild(td3);
		
		btn1.addEventListener("click", function (){bekijkProduct(product.productID);});
		
		var td4 = document.createElement('td');
		var btn2 = document.createElement('button');
		var winkelwagen = document.createTextNode('Voeg toe aan winkelwagen');
		btn2.setAttribute('id', 'addWinkelwagen')
		btn2.appendChild(winkelwagen);
		td4.appendChild(btn2);
		rij.appendChild(td4);
		
		btn2.addEventListener("click", function (){});
		
		document.getElementById('table2').appendChild(rij);
	}
}

//TOEVOEGEN AAN WINKELMANDJE
function addWinkelmand(productID){
	//if(nummer = 0){
		fetch('/techbest/restservices/producten/createOrder', {method: 'POST'})
		.then(response => response.json())
		.then(function(myJson){
			nummer = myJson.order_id;
			console.log(myJson);
		})
	//}
	
	console.log(nummer);
}

//WINKELMANDJE
document.getElementById('winkelwagen').addEventListener("click", function(){bekijkWinkelmand()});

function bekijkWinkelmand(){	
	info.style.display = "none";
	zoek.style.display = "none";
	home.style.display = "none";
	winkel.style.display = "block";
}

//TERUG NAAR STARTSCHERM
document.getElementById('back1').addEventListener("click", function (){backToHome()});
document.getElementById('back2').addEventListener("click", function (){backToHome()});

function backToHome(){
	document.getElementById("txtPn").value = "Naam product";
	document.getElementById("txtLc").value = "Leverancier";
	document.getElementById("txtKl").value = "Kleur";
	
	info.style.display = "none";
	zoek.style.display = "none";
	home.style.display = "block";
	winkel.style.display = "none";
}

window.onload = initPage;
