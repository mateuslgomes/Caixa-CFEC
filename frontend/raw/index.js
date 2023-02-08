function getAPI() {
    let url = `http://localhost:8080/caixa`;
    fetch(url).then(function(response) {
        response.json().then(function(data) {
            console.log(data)
        })
    });
}