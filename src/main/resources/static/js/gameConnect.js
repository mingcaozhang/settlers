//<![CDATA[
var stompClient = null;
connect();

function setConnected(connected) {
    //$("#connect").prop("disabled", connected);

}

function connect() {
    //showJoinedUser("I just connected!");
    var socket = new SockJS('/game-web-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/gameinit', function (str) {
           // showJoinedUser(JSON.parse(str.body).content);
            showJoinedUser(str.body);
        });
    });
}


function go(){
    stompClient.send("/app/ready",{},{});
}

function showJoinedUser(user){

    $("#playerlist").append("<button type=\"button\" class=\"player-btn\">"+user+" has connected!</button>");

}

$(function () {

   // $("#connect").click(function() { connect(); });
    $("#joingame").click(function() { go(); $("#joingame").prop("disabled", true);});

});

//]]>