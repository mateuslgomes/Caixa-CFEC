import React, { useState, createContext } from "react";

export const ModalContext = createContext();

const UseModalProvider = ({ children }) => {
  const [modalActive, setModalActive] = useState({
    isOpen: false,
    dataForm: null,
  });
  return (
    <ModalContext.Provider value={{ modalActive, setModalActive }}>
      {children}
    </ModalContext.Provider>
  );
};

export default UseModalProvider;
