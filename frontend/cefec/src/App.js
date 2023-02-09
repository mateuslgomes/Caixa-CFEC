import { useState, useEffect } from 'react';
import './App.css';
import Table from './components/Table'
import axios from 'axios'

function App() {
  const [servico, setServico] = useState([])

  const getServicos = async () => {
    try {
      const res = await axios.get("http://localhost:8080/caixa")
      setServico(res.data.sort((a, b) => (a.name > b.name ? 1 : -1)))
    } catch (error) {
      console.log("arrooorrrrrrrrrrrr pola")
    }
  }

  useEffect(() => {
      getServicos()
  }, [setServico])

  return (
    <div>
      <div id="root"></div>
    <div className="container-xl">
      <div className="table-responsive">
        <div className="table-wrapper">
          <div className="table-title">
            <div className="row">
              <div className="col-sm-6">
                <h2>Manage <b>Employees</b></h2>
              </div>
              <div className="col-sm-6">
                <a
                  href="#addEmployeeModal"
                  className="btn btn-success"
                  data-toggle="modal"
                  ><i className="material-icons">&#xE147;</i>
                  <span>Add New Employee</span></a
                >
                <a
                  href="#deleteEmployeeModal"
                  className="btn btn-danger"
                  data-toggle="modal"
                  ><i className="material-icons">&#xE15C;</i> <span>Delete</span></a
                >
              </div>
            </div>
          </div>
          <div><Table data={servico} /></div>

          <div className="clearfix">
            <div className="hint-text">
              Showing <b>5</b> out of <b>25</b> entries
            </div>
            <ul className="pagination">
              <li className="page-item disabled"><a href="#">Previous</a></li>
              <li className="page-item"><a href="#" className="page-link">1</a></li>
              <li className="page-item"><a href="#" className="page-link">2</a></li>
              <li className="page-item active">
                <a href="#" className="page-link">3</a>
              </li>
              <li className="page-item"><a href="#" className="page-link">4</a></li>
              <li className="page-item"><a href="#" className="page-link">5</a></li>
              <li className="page-item"><a href="#" className="page-link">Next</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div id="addEmployeeModal" className="modal fade">
      <div className="modal-dialog">
        <div className="modal-content">
          <form>
            <div className="modal-header">
              <h4 className="modal-title">Add Employee</h4>
              <button
                type="button"
                className="close"
                data-dismiss="modal"
                aria-hidden="true"
              >
                &times;
              </button>
            </div>
            <div className="modal-body">
              <div className="form-group">
                <label>Name</label>
                <input type="text" className="form-control" required />
              </div>
              <div className="form-group">
                <label>Email</label>
                <input type="email" className="form-control" required />
              </div>
              <div className="form-group">
                <label>Address</label>
                <textarea className="form-control" required></textarea>
              </div>
              <div className="form-group">
                <label>Phone</label>
                <input type="text" className="form-control" required />
              </div>
            </div>
            <div className="modal-footer">
              <input
                type="button"
                className="btn btn-default"
                data-dismiss="modal"
                value="Cancel"
              />
              <input type="submit" className="btn btn-success" value="Add" />
            </div>
          </form>
        </div>
      </div>
    </div>
    </div>
  );
}

export default App;
