import { Api } from "../services/Api";

export const fetchAllProducts = () => {
  const response = Api.get("caixa");

  return response;
};

export const createNewProduct = (params) => {
  const response = Api.post("caixa", params);

  return response;
};

export const deleteProduct = (id) => {
  const response = Api.delete(`caixa/${id}`);

  return response;
};

export const fetchUpdateProduct = (params) => {
  const { id } = params;
  const response = Api.put(`caixa/${id}`, params);

  return response;
};

export const addValueInTotal = (params) => {
  const response = Api.post("caixa/total", params);

  return response;
};

export const getTotalProducts = () => {
  const response = Api.get("caixa/total");

  return response;
};
