<!-- Error Page -->
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
  <script src="StockPage.js"></script>
  </head>
  <body style="background-image: linear-gradient(to bottom, #024166, #34a8eb);">
    <nav class="navbar navbar-dark bg-dark">
        <div class="col-1">
          <a href="http://dogtrader-env.kgd6nfmk8q.us-east-1.elasticbeanstalk.com/home" class="btn btn-secondary" style="width:100%">Home</a>
        </div>
        <div class="col-1">
          <a href="http://dogtrader-env.kgd6nfmk8q.us-east-1.elasticbeanstalk.com/profile" class="btn btn-secondary" style="width:100%; margin-left:-1.6em">Profile</a>
        </div>
        <div class="col-8">
          <form class="form-inline" style="width:100%" method="post">
            <input class="form-control mr-sm-2 col-10" type="search" placeholder="Search" aria-label="Search" name="search">
            <button class="btn btn-outline-success my-2 my-sm-0 col-1" type="submit">Search</button>
          </form>
        </div>
        <div class="col-1" style="width:100%">
        </div>
        <div class="col-1" style="width:100%">
          <a href="#logout" class="btn btn-secondary">Logout</a>
        </div>
    </nav>
  	<div class="container">
		<br>
		<h1 class="bg-danger rounded-pill" style="text-align:center">The stock symbol you searched for does not exist. Please search for a valid stock symbol</h1>
		<hr>
		<h1 class="bg-info rounded-pill" style="text-align:center;">Other Stocks</h1><hr>
    <div class="row" style="padding:0.2em 0em 0.2em 0em;">
      <div class="col-10" id="display_stock_1">
        <div class="card shadow-lg bg-dark" style="height:100%; padding:0.2em 0em 0.2em 0em;">
          <div class="row">
            <div class="col-2 bg-success rounded" style="width:30%;margin-left: 1.2em" id="s1Color">
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
    <div class="row" style="padding:0.2em 0em 0.2em 0em;">
      <div class="col-10" id="display_stock_1">
        <div class="card shadow-lg bg-dark" style="height:100%; padding:0.2em 0em 0.2em 0em;">
          <div class="row">
            <div class="col-2 bg-success rounded" style="width:30%;margin-left: 1.2em" id="s2Color">
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
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
 </body>
