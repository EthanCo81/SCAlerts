alertCheckInterval = 10000;
countryCode = null;
ebuNbr = null;
historyTableDisplay = false;
alertDisplay = false;
noNewOrderHTML = '<div id="no-orders">No new orders</div>';
errorText = 'ERROR OCCURED. CONTACT ADMINISTRATOR';
invalidClubNumberMessage = 'Invalid Club Number';


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
                alertDisplay = true;
            } else if (alert.alertStatus === 0 && alertDisplay === true) {
                $("#alert").modal('hide');
                alertDisplay = false;
            }
        }
    }
}

function sendAcknowledge() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = dismiss;
    xhttp.open("POST", "acknowledge/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();
    function dismiss() {
        if (xhttp.status === 200 && xhttp.readyState === 4) {
            $('#alert').modal('hide');
            document.getElementById("ordertable").innerHTML = noNewOrderHTML;
            historyTableDisplay = false;
            alertDisplay = false;
        } else if (xhttp.readyState === 4 && xhttp.status !== 200) {
            $(".alert-text").innerText = errorText;
        }
    }

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
                document.getElementById("null-div").innerHTML = invalidClubNumberMessage;
            } else if (xhttp.status === 200){
            	ebu = JSON.parse(xhttp.responseText);
                document.getElementById("store-info").innerText =
                    `${ebu.city}, ${ebu.state}	
                     Store #${ebuNbr}	`;
                $("#ebu-input").modal('hide');
                getAlerts();
                interval = setInterval(getAlerts, alertCheckInterval);
                document.getElementById("null-div").innerHTML = "";
            }
        }
        
    }
}

function checkTime() {
    document.getElementById("datetime").innerText = (new Date()).toDateString()+ "\n" + (new Date()).toLocaleTimeString();
}

function viewHistory() {
    if (historyTableDisplay === true) {
        document.getElementById("ordertable").innerHTML = noNewOrderHTML;
        historyTableDisplay = false;
        return;
    }
    historyTableDisplay = true;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = returnedHistory;
    xhttp.open("GET", "/alert/history/" + countryCode + "/" + ebuNbr, true);
    xhttp.send();

    function returnedHistory() {
        console.log(xhttp.readyState + " " + xhttp.status);
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var historyTable = JSON.parse(xhttp.responseText);
            document.getElementById("ordertable").innerHTML = `<table border="1"><tbody id="history-table"><tr><th>Alert Code</th><th>Alert Start Time</th><th>Alert Acknowledge Time</th></tr></tbody></table>`;
            for (a in historyTable) {
                document.getElementById("history-table").innerHTML +=
                    `<tr><td>${historyTable[a].alertHistoryId.alertType.alertTypeName}</td>
                        <td>${new Date(historyTable[a].alertStartLtz).toLocaleString()}</td>
                        <td>${new Date(historyTable[a].alertEndLtz).toLocaleString()}</td>
                      </tr>
                    `;
                
                document.getElementById("history-table").innerHTML += `</tr>`;
            }

        }
    }
}

function createNewAlert() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "alert/" + document.getElementById("countryCodeNewAlert").value + "/" + document.getElementById("ebuNbrNewAlert").value + "/timeZone=" + Intl.DateTimeFormat().resolvedOptions().timeZone + "&?alertType=" + document.getElementById("alertTypeNbr").value, true);
    xhttp.send();
}

window.onload = function () {
    document.getElementById("acknowledge-button").addEventListener("click", sendAcknowledge);
    document.getElementById("ebu-button").addEventListener("click", sendEbuInfo);
    document.getElementById("alert-history-button").addEventListener("click", viewHistory);
    document.getElementById("new-alert-button").addEventListener("click", createNewAlert);
    time = setInterval(checkTime, 1000);
};

