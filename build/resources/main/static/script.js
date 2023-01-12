const xhr = new XMLHttpRequest()
//open a get request with the remote server URL
xhr.open("GET", "http://localhost:7654/leagueposition/Premier League")
//send the Http request
xhr.send()
xhr.onload = function() {
  if (xhr.status === 200) {
    //parse JSON datax`x
    data = JSON.parse(xhr.responseText)
    console.log(data.count)
    console.log(data.products)
  } else if (xhr.status === 404) {
    console.log("No records found")
  }
  document.getElementById('response').innerHTML = xhr.responseText
}
var el_up = document.getElementById("GFG_UP");

		var list = data

		function constructTable(selector) {

			// Getting the all column names
			var cols = Headers(list, selector);

			// Traversing the JSON data
			for (var i = 0; i < list.length; i++) {
				var row = $('<tr/>');
				for (var colIndex = 0; colIndex < cols.length; colIndex++)
				{
					var val = list[i][cols[colIndex]];

					// If there is any key, which is matching
					// with the column name
					if (val == null) val = "";
						row.append($('<td/>').html(val));
				}

				// Adding each row to the table
				$(selector).append(row);
			}
		}

		function Headers(list, selector) {
			var columns = [];
			var header = $('<tr/>');

			for (var i = 0; i < list.length; i++) {
				var row = list[i];

				for (var k in row) {
					if ($.inArray(k, columns) == -1) {
						columns.push(k);

						// Creating the header
						header.append($('<th/>').html(k));
					}
				}
			}

			// Appending the header to the table
			$(selector).append(header);
				return columns;
		}
