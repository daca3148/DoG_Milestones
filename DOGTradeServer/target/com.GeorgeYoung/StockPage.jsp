<!-- Home Page -->
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Stock Information</title>
    <!-- Bootstrap core CSS -->
  <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="StockPage.css">
  <style>

		#thermometer_inner { width: 95%; height: 20%; margin:2.5%; background: red; position:absolute; bottom:0;}
		#thermometer_outer {height:400px; position:relative;}
		#temp_today { color:white; text-align:center; position:relative; top:50%; }
	</style>

    <script>
      window.onload = function () {

        var chart = new CanvasJS.Chart("chartContainer", {
          animationEnabled: true,
          theme: "light2",
          title:{
            text: "${symbol}"
          },
          axisY:{
            includeZero: false,
            title: "Stock Value(USD)"
          },
          data: [{
            type: "line",
            dataPoints: [
              { y: ${stockDays[6].close} },
              { y: ${stockDays[5].close} },
              { y: ${stockDays[4].close} },
              { y: ${stockDays[3].close} },
              { y: ${stockDays[2].close} },
              { y: ${stockDays[1].close} },
              { y: ${stockDays[0].close} }
            ]
          }]
        });
        chart.render();

      }
    </script>
  </head>
  <body style="background-image: linear-gradient(to bottom, #024166, #34a8eb);">
    <nav class="navbar navbar-dark bg-dark">
        <div class="col-1">
          <a href="#home" class="btn btn-secondary" style="width:100%">Home</a>
        </div>
        <div class="col-1">
          <a href="#about" class="btn btn-secondary" style="width:100%; margin-left:-1.6em">About</a>
        </div>
        <div class="col-8">
          <form class="form-inline" style="width:100%" method="post">
            <input class="form-control mr-sm-2 col-10" type="search" placeholder="Search" aria-label="Search" name="search">
            <button class="btn btn-outline-success my-2 my-sm-0 col-1" type="submit">Search</button>
          </form>
        </div>
        <div class="col-1" style="width:100%">
          <a href="#login" class="btn btn-secondary">Login/Signup</a>
        </div>
        <div class="col-1" style="width:100%">
          <a href="#logout" class="btn btn-secondary">Logout</a>
        </div>
    </nav>
  	<div class="container">
		<br>
		<h1 class="bg-info rounded-pill" style="text-align:center">Placeholder for Default Stock</h1>
		<hr>
		<div class="row">
			<div class="col-6">
				<div class="card shadow-lg bg-white rounded" style="height:100%;">
					<div class="my-auto">
						<!-- source for images: https://www.iconfinder.com/iconsets/weather-line-19 -->
                      <div id="chartContainer" style="height: 370px; width: 100%;"></div>
                      <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
						<p id="stock_symbol" class="h3 " style="text-align:center">${symbol}</p>
					</div>
				</div>
			</div>
			<div class="col-6">
				<div class="card shadow-lg  bg-white rounded " style="height:100%;">
					<div class="my-auto">
						<p class="h3" style="padding-left:15px;">Change Today <span id="precip_today">0.5%</span><br>
					    High Today: <span id="humidity_today">${stockDays[0].high}</span><br>
					    Low Today: <span id="wind_today">${stockDays[0].low}</span><br>
					    Current Value: <span id="summary_today">${stockDays[0].close}</span></p>
					</div>
				</div>
			</div>
		</div>
		<br>
		<h1 class="bg-info rounded-pill" style="text-align:center;">Other Stocks</h1><hr>
    <div class="row">
      <div class="col-10" id="display_stock_1">
        <div class="card shadow-lg bg-dark" style="height:100%; padding:0.2em 0em 0.2em 0em;">
          <div class="row">
            <div class="col-2 bg-success rounded" style="width:30%;margin-left: 1.2em">
              <p id="display_stock_1_symbol" class="h3" style="text-align:left">${otherStocks[0].symbol}</p>
            </div>
            <p id="display_stock_1_full_name" class="h3 col" style="width:30%;text-align:center">${otherStocks[0].name}</p>
            <p id="display_stock_1_current_value" class="h3 col" style="width:30%;text-align:center">${otherStocks[0].close}</p>
            <p id="display_stock_1_change" class="h3 col" style="width:30%; text-align:center">% Change</p>
          </div>
        </div>
      </div>
      <div class="col-2" id="display_stock_1_view">
        <a href="http://dogtrader-env.kgd6nfmk8q.us-east-1.elasticbeanstalk.com/stock?symbol=${otherStocks[0].symbol}"><button class="btn btn-primary shadow-lg" style="width:100%;height:100%" type="button" id="display_stock_1_button" >
        View
        </button></a>
      </div>
    </div>
    <span class="brsmall"></span>
    <div class="row">
      <div class="col-10" id="display_stock_1">
        <div class="card shadow-lg bg-dark" style="height:100%; padding:0.2em 0em 0.2em 0em;">
          <div class="row">
            <div class="col-2 bg-success rounded" style="width:30%;margin-left: 1.2em">
              <p id="display_stock_2_symbol" class="h3" style="text-align:left">${otherStocks[1].symbol}</p>
            </div>
            <p id="display_stock_2_full_name" class="h3 col" style="width:30%;text-align:center">${otherStocks[1].name}</p>
            <p id="display_stock_2_current_value" class="h3 col" style="width:30%;text-align:center">${otherStocks[1].close}</p>
            <p id="display_stock_2_change" class="h3 col" style="width:30%; text-align:center">% Change</p>
          </div>
        </div>
      </div>
      <div class="col-2" id="display_stock_2_view">
        <a href="http://dogtrader-env.kgd6nfmk8q.us-east-1.elasticbeanstalk.com/stock?symbol=${otherStocks[1].symbol}"><button class="btn btn-primary shadow-lg" style="width:100%;height:100%" type="button" id="display_stock_2_button" >
        View
        </button></a>
      </div>
    </div>
    </div>
 </body>
