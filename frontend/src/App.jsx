/* eslint-disable react-hooks/exhaustive-deps */
import { useContext, useEffect } from "react";
import ModalCustom from "./components/Modal";
import TableCustom from "./components/TableCustom";
import { ProductContext } from "./context/useProduct";

function App() {
  const { fetchTotalOfProducts, fetchListOfProducts } =
    useContext(ProductContext);

  useEffect(() => {
    fetchTotalOfProducts();
    fetchListOfProducts();
  }, []);

  return (
    <>
      <ModalCustom />
      <TableCustom />
    </>
  );
}

export default App;
