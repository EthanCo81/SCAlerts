
countryCode = null;
ebuNbr = null;


function getAlerts() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = sendAlert;
    xhttp.open("GET", "alert/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();
    function sendAlert() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            alert = JSON.parse(xhttp.responseText);
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
    xhttp.open("PUT", "acknowledge/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();

    interval = setInterval(getAlerts, 10000);
}

function sendEbuInfo() {
    ebuNbr = document.getElementById("ebuNbr").value;
    countryCode = document.getElementById("countryCode").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = isStore;
    xhttp.onerror = isStore;
    xhttp.open("GET", "ebu/" + countryCode + "/" + ebuNbr, true);
    
    xhttp.send();
    
    function isStore() {
        if (xhttp.readyState === 4) {
        	if(xhttp.status === 404) {
                document.getElementById("null-div").innerHTML = "Invalid store code";
            } else if (xhttp.status === 200){
            	ebu = JSON.parse(xhttp.responseText);
                document.getElementById("store-info").innerText =
                    `${ebu.city}, ${ebu.state}	
                     Store #${ebuNbr}	`;
                $("#ebu-input").modal('hide');

                interval = setInterval(getAlerts, 10000);
            }
        }
        
    }
}

window.onload = function () {
    document.getElementById("acknowledge-button").addEventListener("click", sendAcknowledge);
    document.getElementById("ebu-button").addEventListener("click", sendEbuInfo);
};

