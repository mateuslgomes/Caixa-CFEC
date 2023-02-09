import { useState } from 'react'
import axios from 'axios'
let url = `http://localhost:8080/caixa` 


const Table = ({ data }) => {

    return (
        
          <table className="table table-striped table-hover">
            <thead>
              <tr>
                <th>#</th>
                <th>Serviço</th>
                <th>Esoque</th>
                <th>Preço</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
                {data.map((item, i) => (
              <tr key={i}>
                <td>{item.id.substr(0, 8) + "..."}</td>
                <td>{item.titulo}</td>
                <td>{item.estoque}</td>
                <td>{item.preco}</td>
                <td>
                  <a href="#editEmployeeModal" className="edit" data-toggle="modal"
                    ><i
                      className="material-icons"
                        data-toggle="tooltip"
                      title="Edit"
                      >&#xE254;</i
                    ></a
                  >
                  <a
                    href="#deleteEmployeeModal"
                    className="delete"
                    data-toggle="modal"
                    ><i
                        onClick={() => deletById(item)}
                      className="material-icons"    
                      title="Delete"
                      >&#xE872;</i
                    ></a
                  >
                </td>
              </tr>
                ))}
            </tbody>
          </table>

    )
}
                    
function deletById(data) {
    if (window.confirm(`O serviço ${data.title} será deletado!`)) {
        axios.delete(`http://localhost:8080/caixa/${data.id}`)
        window.location.reload()
    }
}


export default Table;