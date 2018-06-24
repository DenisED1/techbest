var info = document.getElementById('productinformatie');
var home = document.getElementById('productenlijst');
var zoek = document.getElementById('zoekresultatenlijst');
var winkel = document.getElementById('winkelmandje');
var bevestigingscherm = document.getElementById('orderbevestigen');
var buttons = document.getElementById('hideButton');
var afgerond = document.getElementById('afgerond');
var zoekbar = document.getElementById('zoekbar');
var bevestigannuleren = document.getElementById('bevestigannuleren');

var order_ID = null;
var pID = null;
var pPrijs = null;

//STARTSCHERM
function initPage(){
	fetch('restservices/producten/getAll')
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
	bevestigannuleren.style.display = "none";
	
	pID = null;
	pPrijs = null;
	
	fetch('restservices/producten/id/'+productID)
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
		fetch('restservices/producten/naam/'+txtPn)
		.then(response => response.json())
		.then(function(myJson){toonProducten(myJson)})
	}
	else if(txtLc != "Leverancier"){
		fetch('restservices/producten/leverancier/'+txtLc)
		.then(response => response.json())
		.then(function(myJson){toonProducten(myJson)})
	}
	else if(txtKl != "Kleur"){
		fetch('restservices/producten/kleur/'+txtKl)
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
	bevestigannuleren.style.display = "none";
	
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
		fetch('restservices/producten/createOrder', {method: 'POST'})
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
	fetch('restservices/producten/createOrderItem', {
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
		}
	})
}

//WINKELMAND BEKIJKEN
document.getElementById('back4').addEventListener("click", function(){bekijkWinkelmand()});
document.getElementById('back6').addEventListener("click", function(){bekijkWinkelmand()});

function bekijkWinkelmand(){	
	info.style.display = "none";
	zoek.style.display = "none";
	home.style.display = "none";
	winkel.style.display = "block";
	bevestigingscherm.style.display = "none";
	afgerond.style.display = "none";
	zoekbar.style.display = "block";
	bevestigannuleren.style.display = "none";
	
	var tt = null;
	var ttPrijs = document.getElementById("totaalPrijs");
	ttPrijs.innerHTML = "-";
	var ttPrijs2 = document.getElementById("totaalPrijs2");
	ttPrijs.innerHTML = "-";
	
	var elmtTable = document.getElementById('table3');
	var tableRows = elmtTable.getElementsByTagName('tr');
	var rowCount = tableRows.length;

	for (var x=rowCount-1; x>0; x--) {
	   elmtTable.removeChild(tableRows[x]);
	}
	
	if(order_ID !== null){
		buttons.style.display = "block";
		
		fetch('restservices/producten/bekijkOrderItems/'+order_ID)
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
			
				//var td4 = document.createElement('td');
				//var btn1 = document.createElement('button');
				//var wijzig = document.createTextNode('wijzig');
				//btn1.setAttribute('id', 'wijzig')
				//btn1.appendChild(wijzig);
				//td4.appendChild(btn1);
				//rij.appendChild(td4);
			
				//btn1.addEventListener("click", function (){wijzigItem(item.itemID);});
			
				var td5 = document.createElement('td');
				var btn2 = document.createElement('button');
				var verwijder = document.createTextNode('verwijder');
				btn2.setAttribute('id', 'verwijder')
				btn2.appendChild(verwijder);
				td5.appendChild(btn2);
				rij.appendChild(td5);
				
				btn2.addEventListener("click", function (){verwijderItem(item.itemID);});
			
				document.getElementById('table3').appendChild(rij);
				
				tt = (tt + item.totaal);
				ttPrijs.innerHTML = '\u20AC'+tt+",-";
				ttPrijs2.innerHTML = '\u20AC'+tt+",-";
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

//ITEM VERWIJDEREN
function verwijderItem(item_ID){
	fetch('restservices/producten/verwijderItem/'+item_ID, {method: 'DELETE'})
	.then(function(response) {
      if (response.ok) // response-status = 200 OK
        console.log("Item "+item_ID+" deleted!");
      else if (response.status == 404)
        console.log("Item not found!");
      else console.log("Cannot delete item!");
    })
	.then(function(){bekijkWinkelmand();})
}

//ITEM WIJZIGEN
function wijzigItem(item_ID){
	
}

//BEVESTIG ORDER VERWIJDEREN
document.getElementById('annuleren').addEventListener("click", function(){toonBevestigOrderVerwijderen();});

function toonBevestigOrderVerwijderen(){
	info.style.display = "none";
	zoek.style.display = "none";
	home.style.display = "none";
	winkel.style.display = "none";
	bevestigingscherm.style.display = "none";
	buttons.style.display = "none";
	afgerond.style.display = "none";
	zoekbar.style.display = "none";
	bevestigannuleren.style.display = "block";
}

//ORDER VERWIJDEREN
document.getElementById('akkoord').addEventListener("click", function(){verwijderOrder(order_ID);});

function verwijderOrder(order_ID){
	fetch('restservices/producten/verwijderOrder/'+order_ID, {method: 'DELETE'})
	.then(function(response) {
      if (response.ok) // response-status = 200 OK
        console.log("Order "+order_ID+" deleted!");
      else if (response.status == 404)
        console.log("Order not found!");
      else console.log("Cannot delete order!");
    })
	.then(function(){
		setOrderIDNull();
		
		var orderNummer = document.getElementById("orderNummer");
		orderNummer.innerHTML = "-";
		
		backToHome();
		})
}


//Order_ID null maken
function setOrderIDNull(){
	order_ID = null;
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
	bevestigannuleren.style.display = "none";
	
	var elmtTable = document.getElementById('table4');
	var tableRows = elmtTable.getElementsByTagName('tr');
	var rowCount = tableRows.length;

	for (var x=rowCount-1; x>0; x--) {
	   elmtTable.removeChild(tableRows[x]);
	}
	
	fetch('restservices/producten/bekijkOrderItems/'+order_ID)
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
	bevestigannuleren.style.display = "none";
	
	setOrderIDNull();
	pID = null;
	pPrijs = null;
	
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
	bevestigannuleren.style.display = "none";
}

window.onload = initPage;
