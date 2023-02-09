function getAPI() {
    let putTb = document.querySelector("#putTb")
    let url = `http://localhost:8080/caixa` 
    fetch(url).then(function(response) {
        response.json().then(function(data) {
            console.log(data)
            console.log(data.length)
            for (var i = 0; i < data.length; i++) {
                var putTr = document.createElement('tr')
                var tdId = document.createElement('td')
                var tdTitulo = document.createElement('td')
                var tdEstoque = document.createElement('td')
                var tdPreco = document.createElement('td')
                var ddd = document.createAttribute('td')
                    // var btn = document.createElement('a')
                    // var tagI = document.createElement('i')

                    // btn.innerHTML = " a"
                    // btn.setAttribute('href', '#')
                    // btn.setAttribute('class', 'view')
                    // btn.setAttribute('title', 'view')
                    // btn.setAttribute('data-toggle', 'tooltip')
                    // tagI.setAttribute('class', 'material-icons')
                    // btn.append(tagI)

                tdId.textContent = data[i].id.substr(1, 7) + "..."
                tdTitulo.textContent = data[i].titulo
                tdEstoque.textContent = data[i].estoque
                tdPreco.textContent = data[i].preco
                ddd.textContent = document.querySelector("#dsdsads")
                

                putTr.append(tdId)
                putTr.append(tdTitulo)
                putTr.append(tdEstoque)
                putTr.append(tdPreco)
                putTr.append(ddd)
                // putTr.append(btn)

                putTb.append(putTr)

                var putTr = document.createElement('tr')
            }
        })
    });
}

// <td>${data[i].id.substr(1, 7) + "..."}</td>
// <td>${data[i].titulo}</td>
// <td>${data[i].estoque}</td>
// <td>${data[i].preco}</td>
// 
// <td><button href="#" class="view" title="view" data-toggle="tooltip"><i class="material-icons"></i><a>
//     <a href="#" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>
//     
//      <a href="#" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
//     <a href="#" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
// </td>

