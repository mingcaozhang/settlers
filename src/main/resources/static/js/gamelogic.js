//Event die faces
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
var pShip;
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

//Knight state (active/inactive)
var knight;

//Turn Counter
var counter = 1;
var color = 'black';

if (counter == 1){ var color = 'blue';}
if (counter == 2){ var color = 'yellow';}
if (counter == 3){ var color = 'red';}
if (counter == 4){ var color = 'orange';}

//Roll Dice
function rollDice() {
    var status = document.getElementById("status");
    var d1 = Math.floor(Math.random() * 6) + 1;
    var d2 = Math.floor(Math.random() * 6) + 1;
    var d3 = Math.floor(Math.random() * 6) + 1;

    document.images["die1"].src = eval("die1Face" + d1 + ".src");
    document.images["die2"].src = eval("die2Face" + d2 + ".src");
    document.images["eventDie"].src = eval("eventFace" + d3 + ".src");

    //Increment barbarian count if event die is (1, 2, 3)
    if(d3 == 1 || d3 == 2 || d3 == 3) {
        barbarianCount += 1;

        //Barbarian attack
        if(barbarianCount == 6) {
            barbarian = 0;
        }
    }

    var diceTotal = d1 + d2;
    status.innerHTML = "You rolled " + diceTotal + ".";
    if(diceTotal == 7){
        status.innerHTML += " Robber!";
    }
}
//Used to enable rollDice button when end turn button is pressed
function enableBtn() {
    document.getElementById('rolldice').disabled = false;

    counter++;
    if(counter == 5) {
        counter = 1;
    }
    var player = document.getElementById("player");
    player.innerHTML = "Player " + counter;
}

//Activated to show attributes when player button is clicked
function setAttributes() {
    //Place road, ship, settlement, city
    pRoad = document.getElementById("pRoad");
    pShip = document.getElementById("pShip");
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
    pShip.innerHTML = "Ship " + nShip;
    pSettlement.innerHTML = "Settlements " + nSettlement;
    pCity.innerHTML = "Cities " + nCity;

    //Set each attributes
    road.innerHTML = "Roads " + nRoad;
    ship.innerHTML = "Ships " + nShip;
    knight1.innerHTML = "Rank 1: " + nKnight1;
    knight2.innerHTML = "Rank 2: " + nKnight2;
    knight3.innerHTML = "Rank 3: " + nKnight3;
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
    if (nRoad < 15 && nBrick > 0 && nWood > 0 ) {
        nBrick--;
        nWood--;
        nRoad++;
        road = document.getElementById("road");
        road.innerHTML = "Roads " + nRoad;
    }
    else {

        //Set no resource message to true
    }
}
//Place road
function placeRoad() {
    nRoad++;
    pRoad = document.getElementById("pRoad");
    pRoad.innerHTML = "Roads " + nRoad;
}

//Build ship
function buildShip() {
    if (nShip < 15 && nSheep > 0 && nWood > 0) {
        nSheep--;
        nWood--;
        nShip++;
        ship = document.getElementById("ship");
        ship.innerHTML = "Ship " + nShip;
    }
    else {
        //Set no resource message to true
    }
}

//Place ship
function placeShip() {
    nShip++;
    pShip = document.getElementById("pShip");
    pShip.innerHTML = "Ship" + nShip;
}

//Build settlement
function buildSettlement() {
    if (nSettlement < 5 && nWood > 0 && nBrick > 0 && nSheep > 0 && nWheat > 0) {
        nWood--;
        nBrick--;
        nSheep--;
        nWheat--;
        nSettlement++;
        settlement = document.getElementById("settlement");
        settlement.innerHTML = "Settlements " + nSettlement;
    }
    else {
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
    if (nCity < 4 && nOre > 2 && nWheat > 1) {
        nOre -= 3;
        nWheat -= 2;
        nCity++;
        city = document.getElementById("city");
        city.innerHTML = "Cities " + nCity;
    }
    else {
        //Set no resource message to true
    }
}

//Place city
function placeCity() {
    nCity++;
    pCity = document.getElementById("pCity");
    pCity.innerHTML = "Cities " + nCity;
}


//Build wall
function buildWall() {
    if (nBrick > 1 && nWall < 3) {
        nBrick -= 2;
        nWall++;
        wall = document.getElementById("wall");
        wall.innerHTML = "Walls " + nWall;
    }
    else {
        //Set no resource message to true
    }
}

//get Knight1
function getKnight1() {
    if (nKnight1 < 2 && nOre > 0 && nSheep > 0) {
        nOre--;
        nSheep--;
        nKnight1++;
        knight = false;
        knight1 = document.getElementById("knight1");
        knight1.innerHTML = "Rank 1: " + nKnight1;
    }
    else {
        //Set no resource message to true
    }
}
//get Knight2
function getKnight2() {
    if (nKnight2 < 2 && nOre > 0 && nSheep > 0) {
        nOre--;
        nSheep--;
        nKnight1--;
        nKnight2++;
        knight1 = document.getElementById("knight1");
        knight1.innerHTML = "Rank 1: " + nKnight1;
        knight2 = document.getElementById("knight2");
        knight2.innerHTML = "Rank 2: " + nKnight2;
    }
    else {
        //Set no resource message to true
    }
}
//get Knight3
function getKnight3() {
    if (nKnight3 < 2 && nOre > 0 && nSheep > 0) {
        nOre--;
        nSheep--;
        nKnight2--;
        nKnight3++;
        knight2 = document.getElementById("knight2");
        knight2.innerHTML = "Rank 2: " + nKnight2;
        knight3 = document.getElementById("knight3");
        knight3.innerHTML = "Rank 3: " + nKnight3;
    }
    else {
        //Set no resource message to true
    }
}
//Activate Knight
function activateKnight() {
    if (nWheat > 0) {
        knight = true;
    }
}


/////////////////////////// COPY PASTED BOARDMAP BELOW //////////////////////////////////

$('button').prop('disabled', false);

function HexBlueprint(axial_x, axial_y, axial_z, size, resource)
{

    this.axial_x = axial_x;
    this.axial_y = axial_y;
    this.axial_z = axial_z;
    this.size = size;
    this.resource = resource;


    var height = this.size*2;
    var width = Math.sqrt(3)/2*height;

    var t = 600 + (this.axial_x-this.axial_y)*width/2;
    var h = 350 + this.axial_z*(0.75)*height;

    this.centre = {x:  t, y:  h}; // CONVERT POINTS INTO VERTICES NEED!!!!!

    this.points = "";

    for(var i = 0; i<=5; i++) // HOLY SHIT
    {
        this.points += this.get_Hex_corner(i).i + "," + this.get_Hex_corner(i).l + " ";
    }


}

HexBlueprint.prototype.get_Hex_corner = function(vertex)
{ // OH. MY. LIFE.
    var angle_deg = 60 * vertex + 30;
    var angle_rad = Math.PI / 180 * angle_deg;
    var a = this.centre.x + (this.size - 16) * Math.cos(angle_rad);
    var b = this.centre.y + (this.size - 16) * Math.sin(angle_rad);

    return {i: a, l: b};

}

function EdgeBlueprint(axial_x, axial_y, axial_z, size)
{

    this.axial_x = axial_x;
    this.axial_y = axial_y;
    this.axial_z = axial_z;
    this.height = size;
    this.width = 2*size/3;
    //this.resource = resource;

    var heightoffset =3;
    var widthoffset = 3.9;

    var height = this.height*heightoffset;
    var width = this.width*widthoffset;

    var t = 600 + (this.axial_x-this.axial_y)*width/2;
    var h = 350 + this.axial_z*(0.75)*height;

    this.centre = {x:  t, y:  h}; // CONVERT POINTS INTO VERTICES NEED!!!!!

    this.points = "";

    for(var i = 0; i<=3; i++) // HOLY SHIT
    {
        this.points += this.get_Edge_corner(i).i + "," + this.get_Edge_corner(i).l + " ";
    }


}

EdgeBlueprint.prototype.get_Edge_corner = function(vertex)
{ // OH. MY. LIFE.
    var angle_deg = 90 * (vertex+1) +45;
    var horizontaloffset = 52;

    var angle_rad = Math.PI / 180 * angle_deg;
    var a = this.centre.x + (this.width - 16) * Math.cos(angle_rad)-horizontaloffset;
    var b = this.centre.y + (this.height -13) * Math.sin(angle_rad);

    return {i: a, l: b};

}

function SideEdgeBlueprint(axial_x, axial_y, axial_z, size, side)
{
    //SIDE IS ALWAYS LEFT!! VAR SIDE STORES UP OR DOWN
    this.axial_x = axial_x;
    this.axial_y = axial_y;
    this.axial_z = axial_z;
    this.height = size*1.4;
    this.width = 3*size/4;
    //	this.resource = resource;

    var heightoffset =2.15;
    var widthoffset = 3.45;

    var height = this.height*heightoffset;
    var width = this.width*widthoffset;

    var t = 600 + (this.axial_x-this.axial_y)*width/2;
    var h = 350 + this.axial_z*(0.75)*height;

    this.centre = {x:  t, y:  h}; // CONVERT POINTS INTO VERTICES NEED!!!!!

    this.points = "";
    for(var i = 0; i<=3; i++) // HOLY SHIT
    {
        this.points += this.get_SideEdge_corner(i,side).i + "," + this.get_SideEdge_corner(i,side).l + " ";
    }


}

SideEdgeBlueprint.prototype.get_SideEdge_corner = function(vertex,side)
{ // OH. MY. LIFE.
    if(vertex%2==0 && side=='top')
        var angle_deg = 90 * (vertex+1)+70;
    else if(vertex%2==0 && side=='bottom')
        var angle_deg = 90 * (vertex+1)-70;
    else
        var angle_deg = 90 * (vertex+1);

    var horizontaloffset = 52;
    var angle_rad = Math.PI / 180 * angle_deg;

    var a = this.centre.x + (this.width - 16) * Math.cos(angle_rad)-horizontaloffset/2;
    if(side=='top')
        var b = this.centre.y + (this.height -13) * Math.sin(angle_rad)-46;
    else if(side=='bottom')
        var b = this.centre.y + (this.height -13) * Math.sin(angle_rad)+46;

    return {i: a, l: b};

}

function IntersectionBlueprint(axial_x, axial_y, axial_z,count)
{

    this.axial_x = axial_x;
    this.axial_y = axial_y;
    this.axial_z = axial_z;
    this.radius = 8;



    var height = 120;
    var width = Math.sqrt(3)/2*height;

    var t = 600 + (this.axial_x-this.axial_y)*width/2;
    var h = 350 + this.axial_z*(0.75)*height;

    if(count==3)
        hoffset=65;
    else
        hoffset=57;

    this.centre = {x:  t, y:  h}; // CONVERT POINTS INTO VERTICES NEED!!!!!
    angle = this.get_Intersection_corner(count);
    t +=60* Math.cos(angle);
    h +=hoffset* Math.sin(angle);
    this.centre = {x:  t, y:  h};

}

IntersectionBlueprint.prototype.get_Intersection_corner = function(vertex)
{ // OH. MY. LIFE.
    var angle_deg = 60 * vertex + 30;
    var angle_rad = Math.PI / 180 * angle_deg;
    //var a = this.centre.x + (this.size - 16) * Math.cos(angle_rad);
    //var b = this.centre.y + (this.size - 16) * Math.sin(angle_rad);

    return angle_rad;

}



function init()
{
    var canvas = document.getElementById("svgcanvas");
    var q, r1, r2, r, b;
    var count = 0;
    //var jsonPolygons = [];


    jsonPolygons =[{"x":340.19237886466846,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"378.2974966311838,642 340.19237886466846,664 302.08726109815314,642 302.08726109815314,598 340.19237886466846,576 378.2974966311838,598 ","id":"h_4_1","terrain_type":"sea"},
        {"x":288.23085463760214,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"326.33597240411746,552 288.23085463760214,574 250.12573687108681,552 250.12573687108684,508 288.23085463760214,486 326.33597240411746,508 ","id":"h_4_2","terrain_type":"sea"},
        {"x":236.26933041053582,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"274.37444817705114,462 236.26933041053582,484 198.1642126440205,462 198.16421264402052,418 236.26933041053582,396 274.37444817705114,418 ","id":"h_4_3","terrain_type":"wood","number":3},
        {"x":184.3078061834695,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"222.41292394998482,372 184.3078061834695,394 146.20268841695417,372 146.2026884169542,328 184.3078061834695,306 222.4129239499848,328 ","id":"h_4_4","terrain_type":"ore","number":5},
        {"x":444.11542731880104,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"482.22054508531636,642 444.11542731880104,664 406.0103095522857,642 406.0103095522857,598 444.11542731880104,576 482.2205450853163,598 ","id":"h_3_0","terrain_type":"sea"},
        {"x":392.1539030917347,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"430.25902085825004,552 392.1539030917347,574 354.0487853252194,552 354.0487853252194,508 392.1539030917347,486 430.25902085825,508 ","id":"h_3_1","terrain_type":"sea"},
        {"x":340.19237886466846,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"378.2974966311838,462 340.19237886466846,484 302.08726109815314,462 302.08726109815314,418 340.19237886466846,396 378.2974966311838,418 ","id":"h_3_2","terrain_type":"sea"},
        {"x":288.23085463760214,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"326.33597240411746,372 288.23085463760214,394 250.12573687108681,372 250.12573687108684,328 288.23085463760214,306 326.33597240411746,328 ","id":"h_3_3","terrain_type":"sea"},
        {"x":236.26933041053582,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"274.37444817705114,282 236.26933041053582,304 198.1642126440205,282 198.16421264402052,238 236.26933041053582,216 274.37444817705114,237.99999999999997 ","id":"h_3_4","terrain_type":"brick","number":8},
        {"x":548.0384757729337,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"586.143593539449,642 548.0384757729337,664 509.93335800641836,642 509.93335800641836,598 548.0384757729337,576 586.143593539449,598 ","id":"h_2_-1","terrain_type":"sea"},
        {"x":496.07695154586736,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"534.1820693123826,552 496.07695154586736,574 457.97183377935204,552 457.97183377935204,508 496.07695154586736,486 534.1820693123826,508 ","id":"h_2_0","terrain_type":"brick","number":9},
        {"x":444.11542731880104,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"482.22054508531636,462 444.11542731880104,484 406.0103095522857,462 406.0103095522857,418 444.11542731880104,396 482.2205450853163,418 ","id":"h_2_1","terrain_type":"sheep","number":10},
        {"x":392.1539030917347,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"430.25902085825004,372 392.1539030917347,394 354.0487853252194,372 354.0487853252194,328 392.1539030917347,306 430.25902085825,328 ","id":"h_2_2","terrain_type":"ore","number":11},
        {"x":340.19237886466846,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"378.2974966311838,282 340.19237886466846,304 302.08726109815314,282 302.08726109815314,238 340.19237886466846,216 378.2974966311838,237.99999999999997 ","id":"h_2_3","terrain_type":"sea"},
        {"x":288.23085463760214,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"326.33597240411746,192 288.23085463760214,214 250.12573687108681,192 250.12573687108684,148 288.23085463760214,126 326.33597240411746,147.99999999999997 ","id":"h_2_4","terrain_type":"sea"},
        {"x":651.9615242270663,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"690.0666419935816,642 651.9615242270663,664 613.856406460551,642 613.856406460551,598 651.9615242270663,576 690.0666419935816,598 ","id":"h_1_-2","terrain_type":"sea"},
        {"x":600,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"638.1051177665153,552 600,574 561.8948822334847,552 561.8948822334847,508 600,486 638.1051177665153,508 ","id":"h_1_-1","terrain_type":"wood","number":6},
        {"x":548.0384757729337,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"586.143593539449,462 548.0384757729337,484 509.93335800641836,462 509.93335800641836,418 548.0384757729337,396 586.143593539449,418 ","id":"h_1_0","terrain_type":"wheat","number":6},
        {"x":496.07695154586736,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"534.1820693123826,372 496.07695154586736,394 457.97183377935204,372 457.97183377935204,328 496.07695154586736,306 534.1820693123826,328 ","id":"h_1_1","terrain_type":"wood","number":4},
        {"x":444.11542731880104,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"482.22054508531636,282 444.11542731880104,304 406.0103095522857,282 406.0103095522857,238 444.11542731880104,216 482.2205450853163,237.99999999999997 ","id":"h_1_2","terrain_type":"wood","number":12},
        {"x":392.1539030917347,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"430.25902085825004,192 392.1539030917347,214 354.0487853252194,192 354.0487853252194,148 392.1539030917347,126 430.25902085825,147.99999999999997 ","id":"h_1_3","terrain_type":"sea"},
        {"x":340.19237886466846,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"378.2974966311838,102 340.19237886466846,124 302.08726109815314,102 302.08726109815314,58 340.19237886466846,36 378.2974966311838,57.99999999999998 ","id":"h_1_4","terrain_type":"sea"},
        {"x":755.884572681199,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"793.9896904477142,642 755.884572681199,664 717.7794549146837,642 717.7794549146837,598 755.884572681199,576 793.9896904477142,598 ","id":"h_0_-3","terrain_type":"sea"},
        {"x":703.9230484541326,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"742.0281662206479,552 703.9230484541326,574 665.8179306876174,552 665.8179306876174,508 703.9230484541326,486 742.0281662206479,508 ","id":"h_0_-2","terrain_type":"wheat","number":2},
        {"x":651.9615242270663,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"690.0666419935816,462 651.9615242270663,484 613.856406460551,462 613.856406460551,418 651.9615242270663,396 690.0666419935816,418 ","id":"h_0_-1","terrain_type":"wheat","number":3},
        {"x":600,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"638.1051177665153,372 600,394 561.8948822334847,372 561.8948822334847,328 600,306 638.1051177665153,328 ","id":"h_0_0","terrain_type":"sheep","number":5},
        {"x":548.0384757729337,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"586.143593539449,282 548.0384757729337,304 509.93335800641836,282 509.93335800641836,238 548.0384757729337,216 586.143593539449,237.99999999999997 ","id":"h_0_1","terrain_type":"ore","number":4},
        {"x":496.07695154586736,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"534.1820693123826,192 496.07695154586736,214 457.97183377935204,192 457.97183377935204,148 496.07695154586736,126 534.1820693123826,147.99999999999997 ","id":"h_0_2","terrain_type":"sheep","number":8},
        {"x":444.11542731880104,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"482.22054508531636,102 444.11542731880104,124 406.0103095522857,102 406.0103095522857,58 444.11542731880104,36 482.2205450853163,57.99999999999998 ","id":"h_0_3","terrain_type":"sea"},
        {"x":859.8076211353316,"y":620,"stroke":"black","stroke_width":"4","fill":"white","points":"897.9127389018469,642 859.8076211353316,664 821.7025033688163,642 821.7025033688163,598 859.8076211353316,576 897.9127389018469,598 ","id":"h_1_-4","terrain_type":"sea"},
        {"x":807.8460969082653,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"845.9512146747805,552 807.8460969082653,574 769.74097914175,552 769.74097914175,508 807.8460969082653,486 845.9512146747805,508 ","id":"h_1_-3","terrain_type":"sea"},
        {"x":755.884572681199,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"793.9896904477142,462 755.884572681199,484 717.7794549146837,462 717.7794549146837,418 755.884572681199,396 793.9896904477142,418 ","id":"h_1_-2","terrain_type":"wood","number":8},
        {"x":703.9230484541326,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"742.0281662206479,372 703.9230484541326,394 665.8179306876174,372 665.8179306876174,328 703.9230484541326,306 742.0281662206479,328 ","id":"h_1_-1","terrain_type":"sheep","number":9},
        {"x":651.9615242270663,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"690.0666419935816,282 651.9615242270663,304 613.856406460551,282 613.856406460551,238 651.9615242270663,216 690.0666419935816,237.99999999999997 ","id":"h_1_0","terrain_type":"desert","number":10},
        {"x":600,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"638.1051177665153,192 600,214 561.8948822334847,192 561.8948822334847,148 600,126 638.1051177665153,147.99999999999997 ","id":"h1_1","terrain_type":"brick","number":6},
        {"x":548.0384757729337,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"586.143593539449,102 548.0384757729337,124 509.93335800641836,102 509.93335800641836,58 548.0384757729337,36 586.143593539449,57.99999999999998 ","id":"h_1_2","terrain_type":"sea"},
        {"x":911.7691453623979,"y":530,"stroke":"black","stroke_width":"4","fill":"white","points":"949.8742631289132,552 911.7691453623979,574 873.6640275958827,552 873.6640275958827,508 911.7691453623979,486 949.8742631289132,508 ","id":"h_2_-4","terrain_type":"sea"},
        {"x":859.8076211353316,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"897.9127389018469,462 859.8076211353316,484 821.7025033688163,462 821.7025033688163,418 859.8076211353316,396 897.9127389018469,418 ","id":"h_2_-3","terrain_type":"sea"},
        {"x":807.8460969082653,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"845.9512146747805,372 807.8460969082653,394 769.74097914175,372 769.74097914175,328 807.8460969082653,306 845.9512146747805,328 ","id":"h2_-2","terrain_type":"ore","number":2},
        {"x":755.884572681199,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"793.9896904477142,282 755.884572681199,304 717.7794549146837,282 717.7794549146837,238 755.884572681199,216 793.9896904477142,237.99999999999997 ","id":"h2_-1","terrain_type":"brick","number":6},
        {"x":703.9230484541326,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"742.0281662206479,192 703.9230484541326,214 665.8179306876174,192 665.8179306876174,148 703.9230484541326,126 742.0281662206479,147.99999999999997 ","id":"h2_0","terrain_type":"wheat","number":8},
        {"x":651.9615242270663,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"690.0666419935816,102 651.9615242270663,124 613.856406460551,102 613.856406460551,58 651.9615242270663,36 690.0666419935816,57.99999999999998 ","id":"h2_1","terrain_type":"sea","number":9},
        {"x":963.7306695894642,"y":440,"stroke":"black","stroke_width":"4","fill":"white","points":"1001.8357873559795,462 963.7306695894642,484 925.625551822949,462 925.625551822949,418 963.7306695894642,396 1001.8357873559795,418 ","id":"h3_-4","terrain_type":"wheat","number":5},
        {"x":911.7691453623979,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"949.8742631289132,372 911.7691453623979,394 873.6640275958827,372 873.6640275958827,328 911.7691453623979,306 949.8742631289132,328 ","id":"h3_-3","terrain_type":"sea"},
        {"x":859.8076211353316,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"897.9127389018469,282 859.8076211353316,304 821.7025033688163,282 821.7025033688163,238 859.8076211353316,216 897.9127389018469,237.99999999999997 ","id":"h3_-2","terrain_type":"sea"},
        {"x":807.8460969082653,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"845.9512146747805,192 807.8460969082653,214 769.74097914175,192 769.74097914175,148 807.8460969082653,126 845.9512146747805,147.99999999999997 ","id":"h3_-1","terrain_type":"sea"},
        {"x":755.884572681199,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"793.9896904477142,102 755.884572681199,124 717.7794549146837,102 717.7794549146837,58 755.884572681199,36 793.9896904477142,57.99999999999998 ","id":"h3_0","terrain_type":"sea"},
        {"x":1015.6921938165306,"y":350,"stroke":"black","stroke_width":"4","fill":"white","points":"1053.797311583046,372 1015.6921938165306,394 977.5870760500153,372 977.5870760500153,328 1015.6921938165306,306 1053.797311583046,328 ","id":"h4_-4","terrain_type":"sheep","number":5},
        {"x":963.7306695894642,"y":260,"stroke":"black","stroke_width":"4","fill":"white","points":"1001.8357873559795,282 963.7306695894642,304 925.625551822949,282 925.625551822949,238 963.7306695894642,216 1001.8357873559795,237.99999999999997 ","id":"h4_-3","terrain_type":"sheep","number":4},
        {"x":911.7691453623979,"y":170,"stroke":"black","stroke_width":"4","fill":"white","points":"949.8742631289132,192 911.7691453623979,214 873.6640275958827,192 873.6640275958827,148 911.7691453623979,126 949.8742631289132,147.99999999999997 ","id":"h4_-2","terrain_type":"sea"},
        {"x":859.8076211353316,"y":80,"stroke":"black","stroke_width":"4","fill":"white","points":"897.9127389018469,102 859.8076211353316,124 821.7025033688163,102 821.7025033688163,58 859.8076211353316,36 897.9127389018469,57.99999999999998 ","id":"h4_-1","terrain_type":"sea"}];








    var jsonEdges = [];
    var jsonIntersections = [];
    var board_radius = 4;
    var hxradius = 60;

    var polyPoints = [];
    for(q = -board_radius; q<= board_radius; q++)
    {
        r1 = Math.max(-board_radius, -q - board_radius);
        r2 = Math.min(board_radius, -q + board_radius);

        for(r = r1; r <= r2; r++)
        {
            b = -q - r;

            //draw empty hexes save the top most and bottom most rows
            if (b != board_radius && b != -board_radius)
            {
                x=q;
                y=r;
                ny = y-1;
                my = y+1;
                nx = x-1;
                mx = x+1;

                var EdgeHeight = 2*hxradius/3;
                var EdgeWidth = hxradius;

                // MAKE HEX

                //	var hex = new HexBlueprint(x, y, x+y, hxradius, '#00b377');
                //	var Random = Math.floor(Math.random() * 12) +1;
                //	var polyValues = {"x": hex.centre.x, "y": hex.centre.y,
                //	 "stroke":"black", "stroke_width": "4", "fill" : "white", "points": hex.points,"id": "h_"+x+"_"+y,"number": Random };
                //	jsonPolygons.push(polyValues);

                // MAKE EDGES

                var edge = new SideEdgeBlueprint(x, y, x+y, EdgeHeight,'top');
                var edgeValues = {"x": edge.centre.x, "y": edge.centre.y,
                    "stroke":"black", "stroke_width": "3", "fill" : "white", "points": edge.points, "id": "e1_"+x+"_"+y};

                var tempEdge;
                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;

                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }

                edge = new EdgeBlueprint(x, y, x+y, EdgeHeight);
                edgeValues = {"x": edge.centre.x, "y": edge.centre.y,
                    "stroke":"black", "stroke_width": "3", "fill" : "white", "points": edge.points, "id": "e2_"+x+"_"+y};

                var tempEdge;
                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;

                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }

                edge = new SideEdgeBlueprint(x, y, x+y, EdgeHeight,'bottom');
                edgeValues = {"x": edge.centre.x, "y": edge.centre.y,
                    "stroke":"black", "stroke_width": "3", "fill" : "white", "points": edge.points,"id": "e3_"+x+"_"+y};
                var tempEdge;
                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;

                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }


                edge = new SideEdgeBlueprint(x, ny, x+ny, EdgeHeight,'bottom');
                edgeValues = {"x": edge.centre.x, "y": edge.centre.y,
                    "stroke":"black", "stroke_width": "3", "fill" : "white", "points": edge.points,"id": "e3_"+x+"_"+ny};
                var tempEdge;
                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;

                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }

                nx = x+1;
                edge = new EdgeBlueprint(mx, ny, mx+ny, EdgeHeight);
                edgeValues = {"x": edge.centre.x, "y": edge.centre.y,
                    "stroke":"black", "stroke_width": "3", "fill" : "white", "points": edge.points, "id": "e2_"+nx+"_"+ny};
                var tempEdge;
                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;

                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }

                edge = new SideEdgeBlueprint(mx, y, mx+y, EdgeHeight,'top');
                edgeValues = {"x": edge.centre.x, "y": edge.centre.y,
                    "stroke":"black", "stroke_width": "3", "fill" : "white", "points": edge.points, "id": "e1_"+nx+"_"+y};
                var tempEdge;
                var search = true;
                for(var i=0;i<jsonEdges.length;i++){
                    tempEdge = jsonEdges[i];
                    if(tempEdge.id == edgeValues.id)
                    {
                        search = false;

                    }
                }
                if(search==true)
                {
                    jsonEdges.push(edgeValues);
                }

                // INTERSECTION ---------------------------------------------
                // 0,0,3 & 0,0,4 & 0,+1,4 & 1,-1,3 & 1,0,3 & 1,0,4

                var IntersectionNeighbours = [];

                var Intersection = new IntersectionBlueprint(x, y, x+y, 3);
                circleValues = {"x_axis": Intersection.centre.x, "y_axis": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i3_"+x+"_"+y};
                var tempIntersection;
                var search = true;
                for(var i=0;i<jsonIntersections.length;i++){
                    tempIntersection = jsonIntersections[i];
                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempEdge);
                    }
                }
                if(search==true)
                {
                    IntersectionNeighbours.push(circleValues);
                    jsonIntersections.push(circleValues);
                }

                var Intersection = new IntersectionBlueprint(x, y, x+y, 4);
                circleValues = {"x_axis": Intersection.centre.x, "y_axis": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i4_"+x+"_"+y};
                var tempIntersection;
                var search = true;
                for(var i=0;i<jsonIntersections.length;i++){
                    tempIntersection = jsonIntersections[i];
                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempIntersection);
                    }
                }
                if(search==true)
                {
                    jsonIntersections.push(circleValues);
                    IntersectionNeighbours.push(circleValues);
                }

                var Intersection = new IntersectionBlueprint(x, my, x+my, 4);
                circleValues = {"x_axis": Intersection.centre.x, "y_axis": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i4_"+x+"_"+my};
                var tempIntersection;
                var search = true;
                for(var i=0;i<jsonIntersections.length;i++){
                    tempIntersection = jsonIntersections[i];
                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempIntersection);
                    }
                }
                if(search==true)
                {
                    jsonIntersections.push(circleValues);
                    IntersectionNeighbours.push(circleValues);
                }


                var Intersection = new IntersectionBlueprint(mx, ny, mx+ny, 3);
                circleValues = {"x_axis": Intersection.centre.x, "y_axis": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i3_"+mx+"_"+ny};
                var tempIntersection;
                var search = true;
                for(var i=0;i<jsonIntersections.length;i++){
                    tempIntersection = jsonIntersections[i];
                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempIntersection);
                    }
                }
                if(search==true)
                {
                    jsonIntersections.push(circleValues);
                    IntersectionNeighbours.push(circleValues);
                }


                var Intersection = new IntersectionBlueprint(mx, y, mx+y, 3);
                circleValues = {"x_axis": Intersection.centre.x, "y_axis": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i3_"+mx+"_"+y};
                var tempIntersection;
                var search = true;
                for(var i=0;i<jsonIntersections.length;i++){
                    tempIntersection = jsonIntersections[i];
                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempIntersection);
                    }
                }
                if(search==true)
                {
                    jsonIntersections.push(circleValues);
                    IntersectionNeighbours.push(circleValues);
                }


                var Intersection = new IntersectionBlueprint(mx, y, mx+y, 4);
                circleValues = {"x_axis": Intersection.centre.x, "y_axis": Intersection.centre.y,"radius" : 8, "color": "black", "id": "i4_"+mx+"_"+y};
                var tempIntersection;
                var search = true;
                for(var i=0;i<jsonIntersections.length;i++){
                    tempIntersection = jsonIntersections[i];
                    if(tempIntersection.id == circleValues.id)
                    {
                        search = false;
                        IntersectionNeighbours.push(tempIntersection);
                    }
                }
                if(search==true)
                {
                    jsonIntersections.push(circleValues);
                    IntersectionNeighbours.push(circleValues);
                }


            }
        }





    }

















     var circs = [];
     var halfwidth = Math.sqrt(3)/2*hxradius;
     var ybaby = (Math.tan(Math.PI*3/18))*halfwidth;


     /*

     var i,j;

     for( i = -3; i <= 3; i++)
     {

     circs.push({ "x_axis": 600 + 2*i*halfwidth, "y_axis": 350 + hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 2*i*halfwidth, "y_axis": 350 + 2*hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 2*i*halfwidth, "y_axis": 350 + 4*hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 2*i*halfwidth, "y_axis": 350 + 5*hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 2*i*halfwidth, "y_axis": 350 - hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 2*i*halfwidth, "y_axis": 350 - 2*hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 2*i*halfwidth, "y_axis": 350 - 4*hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 2*i*halfwidth, "y_axis": 350 - 5*hxradius, "radius": 4, "color" : "yellow" });


     circs.push({ "x_axis": 600 + (2*(i-1)+1)*halfwidth, "y_axis": 350 + ybaby, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + (2*(i-1)+1)*halfwidth, "y_axis": 350 + 5*ybaby, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + (2*(i-1)+1)*halfwidth, "y_axis": 350 + 7*ybaby, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + (2*(i-1)+1)*halfwidth, "y_axis": 350 + 11*ybaby, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + (2*(i-1)+1)*halfwidth, "y_axis": 350 - ybaby, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + (2*(i-1)+1)*halfwidth, "y_axis": 350 - 5*ybaby, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + (2*(i-1)+1)*halfwidth, "y_axis": 350 - 7*ybaby, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + (2*(i-1)+1)*halfwidth, "y_axis": 350 - 11*ybaby, "radius": 4, "color" : "yellow" });

     //d3.select("svg").append("circle").attr("cx", 600 + 2*i*halfwidth).attr("cy", 500).attr("r", 4).attr("fill", "yellow");

     }

     circs.push({ "x_axis": 600 + 8*halfwidth, "y_axis": 350 - hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 8*halfwidth, "y_axis": 350 - 2*hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 8*halfwidth, "y_axis": 350 + hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 8*halfwidth, "y_axis": 350 + 2*hxradius, "radius": 4, "color" : "yellow" });

     circs.push({ "x_axis": 600 - 8*halfwidth, "y_axis": 350 - hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 - 8*halfwidth, "y_axis": 350 - 2*hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 - 8*halfwidth, "y_axis": 350 + hxradius, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 - 8*halfwidth, "y_axis": 350 + 2*hxradius, "radius": 4, "color" : "yellow" });

     circs.push({ "x_axis": 600 - 9*halfwidth, "y_axis": 350 + ybaby, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 - 9*halfwidth, "y_axis": 350 - ybaby, "radius": 4, "color" : "yellow" });

     circs.push({ "x_axis": 600 + 9*halfwidth, "y_axis": 350 + ybaby, "radius": 4, "color" : "yellow" });
     circs.push({ "x_axis": 600 + 9*halfwidth, "y_axis": 350 - ybaby, "radius": 4, "color" : "yellow" });

     */
    /*
     var xcoord = 600;
     var ycoord = 350;

     var jsonIntersections = [
     { "x_axis": xcoord, "y_axis": ycoord + hxradius, "radius": 8, "color" : "red", "id" : "a00 10 01"},
     { "x_axis": xcoord, "y_axis": ycoord + 2*hxradius, "radius": 8, "color" : "green" },
     { "x_axis": xcoord, "y_axis": ycoord + 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord, "y_axis": ycoord + 5*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord, "y_axis": ycoord - hxradius, "radius": 8, "color" : "blue" },
     { "x_axis": xcoord, "y_axis": ycoord - 2*hxradius, "radius": 8, "color" : "yellow" },
     { "x_axis": xcoord, "y_axis": ycoord - 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord, "y_axis": ycoord - 5*hxradius, "radius": 8, "color" : "black" },

     { "x_axis": xcoord - 2*halfwidth, "y_axis": ycoord + hxradius, "radius": 8, "color" : "gray" },
     { "x_axis": xcoord - 2*halfwidth, "y_axis": ycoord + 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 2*halfwidth, "y_axis": ycoord + 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 2*halfwidth, "y_axis": ycoord + 5*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 2*halfwidth, "y_axis": ycoord - hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 2*halfwidth, "y_axis": ycoord - 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 2*halfwidth, "y_axis": ycoord - 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 2*halfwidth, "y_axis": ycoord - 5*hxradius, "radius": 8, "color" : "black" },

     { "x_axis": xcoord - 4*halfwidth, "y_axis": ycoord + hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 4*halfwidth, "y_axis": ycoord + 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 4*halfwidth, "y_axis": ycoord + 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 4*halfwidth, "y_axis": ycoord + 5*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 4*halfwidth, "y_axis": ycoord - hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 4*halfwidth, "y_axis": ycoord - 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 4*halfwidth, "y_axis": ycoord - 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 4*halfwidth, "y_axis": ycoord - 5*hxradius, "radius": 8, "color" : "black" },

     { "x_axis": xcoord - 6*halfwidth, "y_axis": ycoord + hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 6*halfwidth, "y_axis": ycoord + 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 6*halfwidth, "y_axis": ycoord + 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 6*halfwidth, "y_axis": ycoord + 5*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 6*halfwidth, "y_axis": ycoord - hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 6*halfwidth, "y_axis": ycoord - 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 6*halfwidth, "y_axis": ycoord - 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 6*halfwidth, "y_axis": ycoord - 5*hxradius, "radius": 8, "color" : "black" },

     { "x_axis": xcoord + 2*halfwidth, "y_axis": ycoord + hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 2*halfwidth, "y_axis": ycoord + 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 2*halfwidth, "y_axis": ycoord + 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 2*halfwidth, "y_axis": ycoord + 5*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 2*halfwidth, "y_axis": ycoord - hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 2*halfwidth, "y_axis": ycoord - 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 2*halfwidth, "y_axis": ycoord - 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 2*halfwidth, "y_axis": ycoord - 5*hxradius, "radius": 8, "color" : "black" },

     { "x_axis": xcoord + 4*halfwidth, "y_axis": ycoord + hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 4*halfwidth, "y_axis": ycoord + 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 4*halfwidth, "y_axis": ycoord + 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 4*halfwidth, "y_axis": ycoord + 5*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 4*halfwidth, "y_axis": ycoord - hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 4*halfwidth, "y_axis": ycoord - 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 4*halfwidth, "y_axis": ycoord - 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 4*halfwidth, "y_axis": ycoord - 5*hxradius, "radius": 8, "color" : "black" },

     { "x_axis": xcoord + 6*halfwidth, "y_axis": ycoord + hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 6*halfwidth, "y_axis": ycoord + 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 6*halfwidth, "y_axis": ycoord + 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 6*halfwidth, "y_axis": ycoord + 5*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 6*halfwidth, "y_axis": ycoord - hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 6*halfwidth, "y_axis": ycoord - 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 6*halfwidth, "y_axis": ycoord - 4*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 6*halfwidth, "y_axis": ycoord - 5*hxradius, "radius": 8, "color" : "black" },

     { "x_axis": xcoord + 8*halfwidth, "y_axis": ycoord - hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 8*halfwidth, "y_axis": ycoord - 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 8*halfwidth, "y_axis": ycoord + hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 8*halfwidth, "y_axis": ycoord + 2*hxradius, "radius": 8, "color" : "black" },

     { "x_axis": xcoord - 8*halfwidth, "y_axis": ycoord - hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 8*halfwidth, "y_axis": ycoord - 2*hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 8*halfwidth, "y_axis": ycoord + hxradius, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 8*halfwidth, "y_axis": ycoord + 2*hxradius, "radius": 8, "color" : "black" },






     { "x_axis": xcoord + halfwidth, "y_axis": ycoord + ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + halfwidth, "y_axis": ycoord + 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + halfwidth, "y_axis": ycoord + 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + halfwidth, "y_axis": ycoord + 11*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + halfwidth, "y_axis": ycoord - ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + halfwidth, "y_axis": ycoord - 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + halfwidth, "y_axis": ycoord - 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + halfwidth, "y_axis": ycoord - 11*ybaby, "radius": 8, "color" : "black" },

     { "x_axis": xcoord + 3*halfwidth, "y_axis": ycoord + ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 3*halfwidth, "y_axis": ycoord + 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 3*halfwidth, "y_axis": ycoord + 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 3*halfwidth, "y_axis": ycoord + 11*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 3*halfwidth, "y_axis": ycoord - ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 3*halfwidth, "y_axis": ycoord - 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 3*halfwidth, "y_axis": ycoord - 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 3*halfwidth, "y_axis": ycoord - 11*ybaby, "radius": 8, "color" : "black" },

     { "x_axis": xcoord + 5*halfwidth, "y_axis": ycoord + ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 5*halfwidth, "y_axis": ycoord + 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 5*halfwidth, "y_axis": ycoord + 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 5*halfwidth, "y_axis": ycoord + 11*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 5*halfwidth, "y_axis": ycoord - ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 5*halfwidth, "y_axis": ycoord - 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 5*halfwidth, "y_axis": ycoord - 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 5*halfwidth, "y_axis": ycoord - 11*ybaby, "radius": 8, "color" : "black" },

     { "x_axis": xcoord - halfwidth, "y_axis": ycoord + ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - halfwidth, "y_axis": ycoord + 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - halfwidth, "y_axis": ycoord + 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - halfwidth, "y_axis": ycoord + 11*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - halfwidth, "y_axis": ycoord - ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - halfwidth, "y_axis": ycoord - 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - halfwidth, "y_axis": ycoord - 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - halfwidth, "y_axis": ycoord - 11*ybaby, "radius": 8, "color" : "black" },

     { "x_axis": xcoord - 3*halfwidth, "y_axis": ycoord + ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 3*halfwidth, "y_axis": ycoord + 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 3*halfwidth, "y_axis": ycoord + 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 3*halfwidth, "y_axis": ycoord + 11*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 3*halfwidth, "y_axis": ycoord - ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 3*halfwidth, "y_axis": ycoord - 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 3*halfwidth, "y_axis": ycoord - 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 3*halfwidth, "y_axis": ycoord - 11*ybaby, "radius": 8, "color" : "black" },

     { "x_axis": xcoord - 5*halfwidth, "y_axis": ycoord + ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 5*halfwidth, "y_axis": ycoord + 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 5*halfwidth, "y_axis": ycoord + 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 5*halfwidth, "y_axis": ycoord + 11*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 5*halfwidth, "y_axis": ycoord - ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 5*halfwidth, "y_axis": ycoord - 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 5*halfwidth, "y_axis": ycoord - 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 5*halfwidth, "y_axis": ycoord - 11*ybaby, "radius": 8, "color" : "black" },

     { "x_axis": xcoord - 7*halfwidth, "y_axis": ycoord + ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 7*halfwidth, "y_axis": ycoord + 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 7*halfwidth, "y_axis": ycoord + 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 7*halfwidth, "y_axis": ycoord - 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 7*halfwidth, "y_axis": ycoord - 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 7*halfwidth, "y_axis": ycoord - ybaby, "radius": 8, "color" : "black" },

     { "x_axis": xcoord - 9*halfwidth, "y_axis": ycoord + ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord - 9*halfwidth, "y_axis": ycoord - ybaby, "radius": 8, "color" : "black" },

     { "x_axis": xcoord + 7*halfwidth, "y_axis": ycoord + ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 7*halfwidth, "y_axis": ycoord + 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 7*halfwidth, "y_axis": ycoord + 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 7*halfwidth, "y_axis": ycoord - 7*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 7*halfwidth, "y_axis": ycoord - 5*ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 7*halfwidth, "y_axis": ycoord - ybaby, "radius": 8, "color" : "black" },

     { "x_axis": xcoord + 9*halfwidth, "y_axis": ycoord + ybaby, "radius": 8, "color" : "black" },
     { "x_axis": xcoord + 9*halfwidth, "y_axis": ycoord - ybaby, "radius": 8, "color" : "black" }



     ];


     */

    var holder = d3.select("svg"); // --------------------

    var polygons = holder.selectAll("polygon")
        .data(jsonPolygons)
        .enter()
        .append("polygon");

    var circles = holder.selectAll("circle")
        .data(jsonIntersections)
        .enter()
        .append("circle");

    var circleAttributes = circles
        .attr("cx", function (d) { return d.x_axis; })
        .attr("cy", function (d) { return d.y_axis; })
        .attr("r", function (d) { return d.radius; })
        .attr("id",function (d) { return d.id; })
        .attr("fill","black")
        .attr("stroke","white")
        .on("dblclick", function (d) { d3.select(this).attr("fill", color)
            .attr("stroke","black")})
        .on("click", function (d) { d3.select(this).attr("fill", color)});

    /*var testcircs = holder.selectAll("circle")
     .data(circs)
     .enter()
     .append("circle");
     console.log(testcircs);
     var testcircleAttributes = testcircs
     .attr("cx", function (d) { return d.x_axis; })
     .attr("cy", function (d) { return d.y_axis; })
     .attr("r", function (d) { return d.radius; })
     .style("fill", function(d) { return d.color; });*/




    var polygonAttrs = polygons
        .attr("class", "hex " + "woood")
        .attr("points", function (d) { return d.points; })
        .attr("stroke", function (d) { return d.stroke; })
        .attr("id", function(d) { return d.id; }) // NEW
        .attr("number", function(d) { return d.number; }) // NEW
        .attr("stroke-width", function (d) { return d.stroke_width; })
        .style("fill", function (d) {if (d.terrain_type === "sea"){ return "#336699";}
        else if(d.terrain_type === "wheat") { return "#ffff66"}
        else if(d.terrain_type === "sheep"){ return "#99ff66"}
        else if(d.terrain_type === "wood"){return "#008060"}
        else if(d.terrain_type === "ore"){return "#999999"}
        else if(d.terrain_type === "brick"){return "#c65353"}
        else if(d.terrain_type === "desert"){return "#ffffb3"}
        else if(d.terrain_type === "gold"){return "#e6b800"}
        else {return "white";}})
        .on("dblclick", function (d) {d3.select("svg")
            .append("polygon")
            .attr("cx", d.x)
            .attr("cy", d.y)
            .attr("r", 25)
            .attr("fill","#d9d9d9");
            d3.select("svg").append("text")
                .style("fill", "black")
                .attr("x", d.x)
                .attr("y", d.y)
                .attr("text-anchor", "middle")
                .text(document.getElementById('searchTxt').value);})
        .on("click", function(d){var c = $('input[name="resource"]:checked').val();
            d3.select(this).attr("fill", c);
            d.fill=c; console.log(JSON.stringify(d));});


    // NEED
    var edges = holder.selectAll("edges")
        .data(jsonEdges)
        .enter()
        .append("polygon");

    var edgeAttrs = edges
            .attr("class", "hex " + "woood")
            .attr("points", function (d) { return d.points; })
            .attr("stroke", function (d) { return d.stroke; })
            .attr("id", function(d) { return d.id; })
            .attr("stroke-width", function (d) { return d.stroke_width; })
            .attr("fill", "white")
            .on("dblclick", function (d) { d3.select(this).attr("fill", color);})
        ;


    //------------------------------

    //				.on("dblclick", function (d) {})
    //				.on("click", function(d) {})


//console.log(polyPoints);
//console.log(JSON.stringify(jsonPolygons));
    //	var holder = d3.select("svg");

    //	holder.append("text")         // append text
    //		 .style("fill", "black")   // fill the text with the colour black
    //		 .attr("x", 200)           // set x position of left side of text
    //		 .attr("y", 100)           // set y position of bottom of text
    //		 .attr("text-anchor", "end")  // set anchor y justification
    //		 .text(JSON.stringify(jsonPolygons.length));        // define the text to display


    //	$('button').prop('disabled', true);
    //		$("#i3_0_0").attr("style","fill:red;");
}

function roll(dice)
{

}