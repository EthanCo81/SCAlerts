
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
                clearInterval();
            }
        }
    }
}

function sendAcknowledge() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "acknowledge/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();

    setInterval(getAlerts, 10000);
}

function sendEbuInfo() {
    ebuNbr = document.getElementById("ebuNbr").value;
    countryCode = document.getElementById("countryCode").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = isStore;
    xhttp.open("GET", "ebu/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();
    
    function isStore() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            if (xhttp.responseText === null) {
                document.getElementById("null-div").innerHTML = "Invalid store code";
            } else {
                ebu = JSON.parse(xhttp.responseText);
                document.getElementById("store-info").innerText =
                    `${ebu.city}, ${ebu.state} \t
                     Store #${ebuNbr}\t`;
                $("#ebu-input").modal('hide');

                setInterval(getAlerts(), 10000);
            }
        }
        
    }
}

window.onload = function () {
    document.getElementById("acknowledge-button").addEventListener("click", sendAcknowledge);
    document.getElementById("ebu-button").addEventListener("click", sendEbuInfo);
};
