/**
 * Created by tooji on 3/8/2017.
 */
//init();
var stompClient = null;
connect();

function connect() {
    //showJoinedUser("I just connected!");
    var socket = new SockJS('/game-board-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);



        stompClient.subscribe('/topic/turn-phase', function (turnObject) {
            // showJoinedUser(JSON.parse(str.body).content);
            showCorrectView(turnObject);
        });

        stompClient.subscribe('/topic/board', function(boardInfo){

        });

        stompClient.subscribe('/user/queue/hand', function(myResources){

        });



    });
}


function showCorrectView(turnObject){

}

function buySettlement(){
    stompClient.send('/app/buySettlement');

}

function buySettlement(){
    stompClient.send('/app/buyRoad');

}

function placeCityAndRoad(){
    stompClient.sent('/app/')
}

$(function () {

    // $("#connect").click(function() { connect(); });
    $("#joingame").click(function() { go(); $("#joingame").prop("disabled", true);});

});









