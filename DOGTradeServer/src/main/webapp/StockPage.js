var close = ${stockDays[0].close};
var open = ${stockDays[0].open};
var percentChange = ((close-open)/open)*100;
document.getElementById('change_today').innerHTML = percentChange + "%";
var close1 = {otherStocks[0].close};
var open1 = {otherStocks[0].open};
var percentChange1 = ((close1-open1)/open1)*100;
document.getElementById('display_stock_1_change').innerHTML = percentChange1 + "%";
var close2 = {otherStocks[1].close};
var open2 = {otherStocks[1].open};
var percentChange2 = ((close2-open2)/open2)*100;
document.getElementById('display_stock_2_change').innerHTML = percentChange2 + "%";
if(percentChange1 < 0){
  document.getElementById("s1Color").setAttribute('class', 'col-2 bg-danger rounded')
}
else{
  document.getElementById("s1Color").setAttribute('class', 'col-2 bg-success rounded')
}
if(percentChange2 < 0){
  document.getElementById("s2Color").setAttribute('class', 'col-2 bg-danger rounded')
}
else{
  document.getElementById("s2Color").setAttribute('class', 'col-2 bg-success rounded')
}
