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