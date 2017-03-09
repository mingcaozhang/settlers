/**
 * Created by tooji on 3/8/2017.
 */




var stompClient = null;
var color =
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
            //showCorrectView(JSON.parse(turnObject.body).content);


        });

        stompClient.subscribe('/topic/dice', function (turnObject) {
            // showJoinedUser(JSON.parse(str.body).content);
            //showCorrectView(JSON.parse(turnObject.body).content);


        });

        stompClient.subscribe('/topic/settlement', function(settlement){
            settlement = JSON.parse(settlement.body);
            $("#"+settlement.id).attr("fill", settlement.color);

        });

        stompClient.subscribe('/topic/city', function(city){
            city = JSON.parse(city.body);
            $("#"+city.id).attr("stroke", "black");

        });

        stompClient.subscribe('/topic/road', function(road){
            road = JSON.parse(road.body);
            $("#"+road.id).attr("fill", road.color);

        });



        stompClient.subscribe('/user/queue/hand', function(myResources){


        });



    });
}


function showCorrectView(turnObject){
    //if(turnObject.username.matches())

}

function updateDice(){


    stompClient.send('/app/updateDice', {}, dicevalue);


}

function buySettlement(){


    stompClient.send('/app/buySettlement', {});


}

function buySettlement(){
    stompClient.send('/app/buyRoad');

}

function placeRoad(){
    stompClient.send('/app/placeRoad');
}

function placeCity(){
    stompClient.send('/app/placeCity');
}

function placeSettlement(){
    stompClient.send('/app/placeSettlement');
}

$(function () {

    // $("#connect").click(function() { connect(); });
    $("#joingame").click(function() { go(); $("#joingame").prop("disabled", true);});

});









