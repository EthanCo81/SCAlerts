
countryCode;
ebuNbr;


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
            }
        }
    }
}

function sendAcknowledge() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "acknowledge/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();
}

function sendEbuInfo() {
    ebuNbr = document.getElementById("ebuNbr").value;
    countryCode = document.getElementById("countryCode").hiddenvalue;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = isStore;
    xhttp.open("GET", "ebu/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();
    
    function isStore() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            if (xhttp.responseText === null) {
                document.getElementById("null-div").innerText = "Invalid store code";
            } else {
                ebu = JSON.parse(xhttp.responseText);
                document.getElementById("store-info").innerText =
                    `${ebu.city}, ${ebu.state}
                     Store #${ebuNbr}`;
                $("#modal_new_project").modal('hide');
            }
        }
        
    }
}

window.onload = function () {
    document.getElementById("acknowledge-button").addEventListener("click", sendAcknowledge);
    document.getElementById("ebu-button").addEventListener("click", sendEbuInfo);
    setInterval(getAlerts, 10000);
};
