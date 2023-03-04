import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { UseProductProvider } from "./context/useProduct";
import UseModalProvider from "./context/useModal";
const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <React.StrictMode>
    <UseProductProvider>
      <UseModalProvider>
        <ToastContainer position="bottom-right" />
        <App />
      </UseModalProvider>
    </UseProductProvider>
  </React.StrictMode>
);
