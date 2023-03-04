import React, { createContext, useState } from "react";
import { toast } from "react-toastify";
import {
  deleteProduct,
  fetchAllProducts,
  getTotalProducts,
  createNewProduct,
  addValueInTotal,
  fetchUpdateProduct,
} from "../../components/apiProduct";
import {
  messageCreateError,
  messageDeleteError,
  messageEditError,
  messageGetError,
  messageSuccess,
  messageTotalError,
} from "../../constants/messages";

export const ProductContext = createContext();

export const UseProductProvider = ({ children }) => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [total, setTotal] = useState(0);

  const fetchListOfProducts = async () => {
    try {
      setLoading(true);
      const { data } = await fetchAllProducts();

      setProducts(data);
    } catch (error) {
      toast(
        !error.message
          ? messageGetError("os dados da listagem")
          : error.message,
        { type: "error" }
      );
    } finally {
      setTimeout(() => {
        setLoading(false);
      }, 500);
    }
  };

  const fetchTotalOfProducts = async () => {
    try {
      const { data: total } = await getTotalProducts();
      setTotal(total);
    } catch (error) {
      toast(!error.message ? messageGetError("o total") : error.message, {
        type: "error",
      });
    }
  };

  const removeProduct = async (productId) => {
    try {
      await deleteProduct(productId);
      setProducts(products.filter((product) => product.id !== productId));
      toast(messageSuccess("Produto deletado"), { type: "success" });
    } catch (error) {
      toast(!error.message ? messageDeleteError("o Produto") : error.message, {
        type: "error",
      });
    }
  };

  const updateProduct = async (productEdit) => {
    try {
      await fetchUpdateProduct(productEdit);
      setProducts(
        products.map((product) =>
          product.id === productEdit.id ? productEdit : product
        )
      );
      toast(messageSuccess("Produto editado"), { type: "success" });
    } catch (error) {
      toast(!error.message ? messageEditError("o Produto") : error.message, {
        type: "error",
      });
    }
  };

  const createProduct = async (newProduct) => {
    try {
      const { data } = await createNewProduct(newProduct);

      toast(messageSuccess("Produto criado"), { type: "success" });
      setProducts([...products, { ...data }]);
    } catch (error) {
      toast(!error.message ? messageCreateError("o produto") : error.message, {
        type: "error",
      });
    }
  };

  const setTotalOfProducts = async (valor) => {
    try {
      await addValueInTotal({ valor });
      setTotal(total + valor);

      toast(
        messageSuccess(
          `Valor ${new Intl.NumberFormat("PT-BR", {
            style: "currency",
            currency: "BRL",
          }).format(valor)} Foi somado  ao total`
        ),
        { type: "success" }
      );
    } catch (error) {
      toast(!error.message ? messageTotalError() : error.message, {
        type: "error",
      });
    }
  };

  return (
    <ProductContext.Provider
      value={{
        products,
        total,
        fetchListOfProducts,
        removeProduct,
        updateProduct,
        fetchTotalOfProducts,
        createProduct,
        loading,
        setTotalOfProducts,
      }}
    >
      {children}
    </ProductContext.Provider>
  );
};
