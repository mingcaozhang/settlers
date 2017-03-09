/**
 * Created by tooji on 3/8/2017.
 */


var eventFace1 = new Image();
eventFace1.src = "/images/barbarianShip.png";
var eventFace2 = new Image();
eventFace2.src = "/images/barbarianShip.png";
var eventFace3 = new Image();
eventFace3.src = "/images/barbarianShip.png";
var eventFace4 = new Image();
eventFace4.src = "/images/eventGreen.png";
var eventFace5 = new Image();
eventFace5.src = "/images/eventYellow.png";
var eventFace6 = new Image();
eventFace6.src = "/images/eventBlue.png";

//die1 faces
var die1Face1 = new Image();
die1Face1.src = "/images/Ydot1.png";
var die1Face2 = new Image();
die1Face2.src = "/images/Ydot2.png";
var die1Face3 = new Image();
die1Face3.src = "/images/Ydot3.png";
var die1Face4 = new Image();
die1Face4.src = "/images/Ydot4.png";
var die1Face5 = new Image();
die1Face5.src = "/images/Ydot5.png";
var die1Face6 = new Image();
die1Face6.src = "/images/Ydot6.png";

//die2 faces
var die2Face1 = new Image();
die2Face1.src = "/images/Rdot1.png";
var die2Face2 = new Image();
die2Face2.src = "/images/Rdot2.png";
var die2Face3 = new Image();
die2Face3.src = "/images/Rdot3.png";
var die2Face4 = new Image();
die2Face4.src = "/images/Rdot4.png";
var die2Face5 = new Image();
die2Face5.src = "/images/Rdot5.png";
var die2Face6 = new Image();
die2Face6.src = "/images/Rdot6.png";

//Attributes values
var nRoad = 0;
var nShip = 0;
var nKnight1 = 0;
var nKnight2 = 0;
var nKnight3 = 0;
var nTotalKnight = nKnight1 + nKnight2 + nKnight3;
var nCity = 0;
var nWall = 0;
var nSettlement = 0;
var nBrick = 0;
var nWood = 0;
var nOre = 0;
var nSheep = 0;
var nWheat = 0;
var nResourceCard = nBrick + nWood + nOre + nSheep + nWheat;
var nCoin = 0;
var nCloth = 0;
var nBook = 0;
var nCommodityCard = nCoin + nCloth + nBook;
var nVictoryPt = 0;
var nGold = 0;
var barbarianCount = 0;

//Place road, ship, settlement, city
var pRoad;
var pSettlement;
var pCity;

//Attributes for id
var road;
var ship;
var knight1;
var knight2;
var knight3;
var totalKnight;
var city;
var wall;
var settlement;
var brick;
var wood;
var ore;
var sheep;
var wheat;
var resourceCard;
var coin;
var cloth;
var book;
var commodityCard;
var victoryPt;
var gold;
var barbarian;

//Used to let the player know that they do not have enough resources
var noResource;



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

        stompClient.subscribe('/topic/dice', function (dice) {
            // showJoinedUser(JSON.parse(str.body).content);
            //showCorrectView(JSON.parse(turnObject.body).content);
            dice = JSON.parse(dice.body);
            document.images["die1"].src = eval("die1Face" + dice.red + ".src");
            document.images["die2"].src = eval("die2Face" + dice.yellow + ".src");
            document.images["eventDie"].src = eval("eventFace" + dice.event + ".src");

            //Increment barbarian count if event die is (1, 2, 3)
            if(d3 == 1 || d3 == 2 || d3 == 3) {
                barbarianCount += 1;
            }

            var diceTotal = d1 + d2;
            status.innerHTML = "You rolled " + diceTotal + ".";
            if(diceTotal == 7){
                status.innerHTML += " Robber!";
            }


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





//Roll Dice
function rollDice(){

    var status = document.getElementById("status");
    var d1 = Math.floor(Math.random() * 6) + 1;
    var d2 = Math.floor(Math.random() * 6) + 1;
    var d3 = Math.floor(Math.random() * 6) + 1;

    stompClient.send('/app/updateDice', {}, JSON.stringify({'red':d1, 'yellow':d2, 'event':d3}));


}

//Used to enable rollDice button when end turn button is pressed
function enableBtn() {
    document.getElementById('rolldice').disabled = false;
}

//Activated to show attributes when player button is clicked
function setAttributes() {
    //Place road, ship, settlement, city
    pRoad = document.getElementById("pRoad");
    pSettlement = document.getElementById("pSettlement");
    pCity = document.getElementById("pCity");

    //Attribute id getters
    road = document.getElementById("road");
    ship = document.getElementById("ship");
    knight1 = document.getElementById("knight1");
    knight2 = document.getElementById("knight2");
    knight3 = document.getElementById("knight3");
    totalKnight = document.getElementById("totalKnight");
    city = document.getElementById("city");
    wall = document.getElementById("wall");
    settlement = document.getElementById("settlement");
    brick = document.getElementById("brick");
    wood = document.getElementById("wood");
    ore = document.getElementById("ore");
    sheep = document.getElementById("sheep");
    wheat = document.getElementById("wheat");
    resourceCard = document.getElementById("resourceCard");
    coin = document.getElementById("coin");
    cloth = document.getElementById("cloth");
    book = document.getElementById("book");
    commodityCard = document.getElementById("commodityCard");
    victoryPt = document.getElementById("victoryPt");
    gold = document.getElementById("gold");
    barbarian = document.getElementById("barbarian");

    //Place road, ship, settlement, city
    pRoad.innerHTML = "Roads " + nRoad;
    pSettlement.innerHTML = "Settlements " + nSettlement;
    pCity.innerHTML = "Cities " + nCity;

    //Set each attributes
    road.innerHTML = "Roads " + nRoad;
    ship.innerHTML = "Ships " + nShip;
    knight1.innerHTML = nKnight1;
    knight2.innerHTML = nKnight2;
    knight3.innerHTML = nKnight3;
    totalKnight.innerHTML = "Knights " + nTotalKnight;
    city.innerHTML = "Cities " + nCity;
    wall.innerHTML = "Walls " + nWall;
    settlement.innerHTML = "Settlements " + nSettlement;
    brick.innerHTML = nBrick;
    wood.innerHTML = nWood;
    ore.innerHTML = nOre;
    sheep.innerHTML = nSheep;
    wheat.innerHTML = nWheat;
    resourceCard.innerHTML = "Resource Cards " + nResourceCard;
    coin.innerHTML = nCoin;
    cloth.innerHTML = nCloth;
    book.innerHTML = nBook;
    commodityCard.innerHTML = "Commodity Cards " + nCommodityCard;
    victoryPt.innerHTML = "Victory Points " + nVictoryPt;
    gold.innerHTML = "Golds " + nGold;
}

//Build road
function buildRoad() {
    if (nBrick > 0) {
        if (nWood > 0) {
            nBrick--;
            nWood--;
            nRoad++;
            road = document.getElementById("road");
            road.innerHTML = "Roads " + nRoad;
        } else {
            //Set no resource message to true
        }
    } else {
        //Set no resource message to true
    }
}
//Place road
function placeRoad() {
    nRoad++;
    pRoad = document.getElementById("pRoad");
    pRoad.innerHTML = "Roads " + nRoad;
}

//Build settlement
function buildSettlement() {
    if (nWood > 0) {
        if (nBrick > 0) {
            if (nSheep > 0) {
                if (nWheat > 0) {
                    nWood--;
                    nBrick--;
                    nSheep--;
                    nWheat--;
                    nSettlement++;
                    settlement = document.getElementById("settlement");
                    settlement.innerHTML = "Settlements " + nSettlement;
                } else {
                    //Set no resource message to true
                }
            } else {
                //Set no resource message to true
            }
        } else {
            //Set no resource message to true
        }
    } else {
        //Set no resource message to true
    }
}
//Place settlement
function placeSettlement() {
    nSettlement++;
    pSettlement = document.getElementById("pSettlement");
    pSettlement.innerHTML = "Settlements " + nSettlement;
}

//Build city
function buildCity() {
    if (nOre > 2) {
        if (nWheat > 1) {
            nOre -= 3;
            nWheat -= 2;
            nCity++;
            city = document.getElementById("city");
            city.innerHTML = "Cities " + nCity;
        } else {
            //Set no resource message to true
        }
    } else {
        //Set no resource message to true
    }
}
//Place city
function placeCity() {
    nCity++;
    pCity = document.getElementById("pCity");
    pCity.innerHTML = "Cities " + nCity;
}



