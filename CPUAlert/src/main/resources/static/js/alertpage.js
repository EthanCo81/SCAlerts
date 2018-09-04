
countryCode = null;
ebuNbr = null;


function getAlerts() {
    console.log("called get alerts")
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = sendAlert;
    xhttp.open("GET", "alert/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();
    function sendAlert() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            alert = JSON.parse(xhttp.responseText);
            console.log(alert);
            if (alert.alertStatus === 1) {
                $("#alert").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                clearInterval(interval);
            }
        }
    }
}

function sendAcknowledge() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "acknowledge/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();

    interval = setInterval(getAlerts, 10000);
}

function sendEbuInfo() {
    ebuNbr = document.getElementById("ebuNbr").value;
    countryCode = document.getElementById("countryCode").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = isStore;
    xhttp.open("GET", "ebu/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();
    
    function isStore() {
        if (xhttp.readyState === 4) {
        	console.log(xhttp)
        	if(xhttp.status === 404) {
        		console.log(xhttp.responseText);
                document.getElementById("null-div").innerHTML = "Invalid store code";
            } else if (xhttp.status === 200){
            	console.log(xhttp.responseText);
            	ebu = JSON.parse(xhttp.responseText);
                document.getElementById("store-info").innerText =
                    `${ebu.city}, ${ebu.state}	
                     Store #${ebuNbr}	`;
                $("#ebu-input").modal('hide');

                interval = setInterval(getAlerts(), 10000);
            }
        }
        
    }
}

window.onload = function () {
    document.getElementById("acknowledge-button").addEventListener("click", sendAcknowledge);
    document.getElementById("ebu-button").addEventListener("click", sendEbuInfo);
};

