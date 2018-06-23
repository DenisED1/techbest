var info = document.getElementById('productinformatie');
var home = document.getElementById('productenlijst');
var zoek = document.getElementById('zoekresultatenlijst');
var winkel = document.getElementById('winkelmandje');
var bevestigingscherm = document.getElementById('orderbevestigen');
var buttons = document.getElementById('hideButton');
var afgerond = document.getElementById('afgerond');
var zoekbar = document.getElementById('zoekbar');

var order_ID = null;
var pID = null;
var pPrijs = null;
var tt = null;

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
			btn2.setAttribute('id', 'addWinkelwagen')
			btn2.appendChild(winkelwagen);
			td4.appendChild(btn2);
			rij.appendChild(td4);
			
			btn2.addEventListener("click", function (){createOrder(product.productID, product.prijs);});
			
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
	bevestigingscherm.style.display = "none";
	afgerond.style.display = "none";
	zoekbar.style.display = "block";
	
	pID = null;
	pPrijs = null;
	
	fetch('/techbest/restservices/producten/id/'+productID)
	.then(response => response.json())
	.then(function(myJson){
		for (const product of myJson){    
			pID = product.productID;
			
			var naam = document.getElementById("naam");
			naam.innerHTML = product.naam;
			
			var prijs = document.getElementById("prijs");
			prijs.innerHTML = product.prijs;
			pPrijs = product.prijs;
		
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
	bevestigingscherm.style.display = "none";
	afgerond.style.display = "none";
	zoekbar.style.display = "block";
	
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
		btn2.setAttribute('id', 'addWinkelwagen2')
		btn2.appendChild(winkelwagen);
		td4.appendChild(btn2);
		rij.appendChild(td4);
		
		btn2.addEventListener("click", function (){createOrder(product.productID, product.prijs);});
		
		document.getElementById('table2').appendChild(rij);
	}
}

//ORDER MAKEN ALS ER NOG GEEN IS
document.getElementById('winkelwagen').addEventListener("click", function(){bekijkWinkelmand()});
document.getElementById('addWinkelwagen3').addEventListener("click", function(){createOrder(pID, pPrijs);});

function createOrder(productID, prijs){
	if (order_ID === null){
		fetch('/techbest/restservices/producten/createOrder', {method: 'POST'})
		.then(response => response.json())
		.then(function(myJson){
			for(const order of myJson){
				order_ID = order.orderID;
				var orderNummer = document.getElementById("orderNummer");
				orderNummer.innerHTML = order_ID;
				addWinkelwagen(order_ID, productID, prijs);
			}
		})
	}
	else{
		addWinkelwagen(order_ID, productID, prijs);
	}
}

//TOEVOEGEN AAN WINKELWAGEN
function addWinkelwagen(order_ID, product_ID, pr){
	fetch('/techbest/restservices/producten/createOrderItem', {
		method: 'POST',
		body: JSON.stringify({
			orderID: order_ID,
			productID: product_ID,
			prijs: pr
		}),
		headers:{
		    'Content-Type': 'application/json'
		  }
		})
	.then(response => response.json())
	.then(function(myJson){
		for (const item of myJson){
			tt = (tt + item.totaal);
			var ttPrijs = document.getElementById("totaalPrijs");
			ttPrijs.innerHTML = '\u20AC'+tt+",-";
		}
	})
}

//WINKELMAND BEKIJKEN
document.getElementById('back4').addEventListener("click", function(){bekijkWinkelmand()});

function bekijkWinkelmand(){	
	info.style.display = "none";
	zoek.style.display = "none";
	home.style.display = "none";
	winkel.style.display = "block";
	bevestigingscherm.style.display = "none";
	afgerond.style.display = "none";
	zoekbar.style.display = "block";
	
	var elmtTable = document.getElementById('table3');
	var tableRows = elmtTable.getElementsByTagName('tr');
	var rowCount = tableRows.length;

	for (var x=rowCount-1; x>0; x--) {
	   elmtTable.removeChild(tableRows[x]);
	}
	
	if(order_ID !== null){
		buttons.style.display = "block";
		
		fetch('/techbest/restservices/producten/bekijkOrderItems/'+order_ID)
		.then(response => response.json())
		.then(function(myJson){
			for (const item of myJson){
				var rij = document.createElement('tr');
				var td1 = document.createElement('td');
				var naam = document.createTextNode(item.naam);
				td1.appendChild(naam);
				rij.appendChild(td1);
			
				var td2 = document.createElement('td');
				var aantal = document.createTextNode(item.aantal);
				td2.appendChild(aantal);
				rij.appendChild(td2);
				
				var td3 = document.createElement('td');
				var totaal = document.createTextNode('\u20AC'+item.totaal+",-");
				td3.appendChild(totaal);
				rij.appendChild(td3);
			
				var td4 = document.createElement('td');
				var btn1 = document.createElement('button');
				var wijzig = document.createTextNode('wijzig');
				btn1.setAttribute('id', 'wijzig')
				btn1.appendChild(wijzig);
				td4.appendChild(btn1);
				rij.appendChild(td4);
			
				btn1.addEventListener("click", function (){});
			
				var td5 = document.createElement('td');
				var btn2 = document.createElement('button');
				var verwijder = document.createTextNode('verwijder');
				btn2.setAttribute('id', 'verwijder')
				btn2.appendChild(verwijder);
				td5.appendChild(btn2);
				rij.appendChild(td5);
				
				btn2.addEventListener("click", function (){});
			
				document.getElementById('table3').appendChild(rij);
			}
		})
	}
	else{
		var rij = document.createElement('tr');
		var td1 = document.createElement('td');
		var naam = document.createTextNode("-");
		td1.appendChild(naam);
		rij.appendChild(td1);
	
		var td2 = document.createElement('td');
		var aantal = document.createTextNode("-");
		td2.appendChild(aantal);
		rij.appendChild(td2);
		
		var td3 = document.createElement('td');
		var totaal = document.createTextNode("-");
		td3.appendChild(totaal);
		rij.appendChild(td3);
		
		document.getElementById('table3').appendChild(rij);
	}
}

//BEVESTIGING SCHERM
document.getElementById('afronden').addEventListener("click", function(){bekijkBevestiging(order_ID)});

function bekijkBevestiging(order_ID){
	info.style.display = "none";
	zoek.style.display = "none";
	home.style.display = "none";
	winkel.style.display = "none";
	bevestigingscherm.style.display = "block";
	afgerond.style.display = "none";
	zoekbar.style.display = "none";
	
	var ttPrijs2 = document.getElementById("totaalPrijs2");
	ttPrijs2.innerHTML = '\u20AC'+tt+",-";
	
	var elmtTable = document.getElementById('table4');
	var tableRows = elmtTable.getElementsByTagName('tr');
	var rowCount = tableRows.length;

	for (var x=rowCount-1; x>0; x--) {
	   elmtTable.removeChild(tableRows[x]);
	}
	
	fetch('/techbest/restservices/producten/bekijkOrderItems/'+order_ID)
	.then(response => response.json())
	.then(function(myJson){
		for (const item of myJson){
			var rij = document.createElement('tr');
			var td1 = document.createElement('td');
			var naam = document.createTextNode(item.naam);
			td1.appendChild(naam);
			rij.appendChild(td1);
			
			var td2 = document.createElement('td');
			var aantal = document.createTextNode(item.aantal);
			td2.appendChild(aantal);
			rij.appendChild(td2);
				
			var td3 = document.createElement('td');
			var totaal = document.createTextNode('\u20AC'+item.totaal+",-");
			td3.appendChild(totaal);
			rij.appendChild(td3);
			
			document.getElementById('table4').appendChild(rij);
			}
		})
}

//BETALING AFGEROND
document.getElementById('bevestigen').addEventListener("click", function(){afronden();});

function afronden(){
	info.style.display = "none";
	zoek.style.display = "none";
	home.style.display = "none";
	winkel.style.display = "none";
	bevestigingscherm.style.display = "none";
	buttons.style.display = "none";
	afgerond.style.display = "block";
	zoekbar.style.display = "none";
	
	order_ID = null;
	pID = null;
	pPrijs = null;
	tt = null;
	
	var orderNummer = document.getElementById("orderNummer");
	orderNummer.innerHTML = "-";
}

//TERUG NAAR STARTSCHERM
document.getElementById('back1').addEventListener("click", function (){backToHome()});
document.getElementById('back2').addEventListener("click", function (){backToHome()});
document.getElementById('back3').addEventListener("click", function (){backToHome()});
document.getElementById('back5').addEventListener("click", function (){backToHome()});

function backToHome(){
	document.getElementById("txtPn").value = "Naam product";
	document.getElementById("txtLc").value = "Leverancier";
	document.getElementById("txtKl").value = "Kleur";
	
	info.style.display = "none";
	zoek.style.display = "none";
	home.style.display = "block";
	winkel.style.display = "none";
	bevestigingscherm.style.display = "none";
	buttons.style.display = "none";
	afgerond.style.display = "none";
	zoekbar.style.display = "block";
}

window.onload = initPage;
