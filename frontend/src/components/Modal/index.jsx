import { Box, Button, Modal, TextField, Typography } from "@mui/material";
import React, { useContext, useEffect, useState } from "react";
import { toast } from "react-toastify";
import { ModalContext } from "../../context/useModal";
import { ProductContext } from "../../context/useProduct";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "50%",
  height: "auto",
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

export default function ModalCustom() {
  const { modalActive, setModalActive } = useContext(ModalContext);
  const { createProduct, updateProduct } = useContext(ProductContext);

  const [formData, setFormData] = useState({
    titulo: modalActive.dataForm?.titulo ?? "",
    preco: modalActive.dataForm?.preco ?? 0,
    estoque: modalActive.dataForm?.estoque ?? 0,
  });

  useEffect(() => {
    if (modalActive.dataForm) setFormData(modalActive.dataForm);
  }, [modalActive.dataForm]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const allFieldsFilled = Object.values(formData).every((field) =>
      Boolean(field)
    );
    if (!allFieldsFilled) {
      toast.error("Por favor, preencha todos os campos.");
      return;
    }

    if (modalActive.isEditing) {
      updateProduct({
        id: modalActive.dataForm.id,
        ...formData,
      });

      handleCloseModal();
    } else {
      createProduct(formData);
      handleCloseModal();
    }
  };

  const handleCloseModal = () => {
    return setModalActive({ ...modalActive, isOpen: false });
  };

  return (
    <Modal
      open={modalActive.isOpen}
      onClose={handleCloseModal}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <Typography id="modal-modal-title" variant="h6" component="h2">
          Cadastro
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          <form
            style={{
              width: "100%",
              height: "100%",
              display: "flex",
              justifyContent: "space-around",
              alignItems: "center",
            }}
            onSubmit={(e) => handleSubmit(e)}
          >
            <TextField
              label="Serviço"
              name="titulo"
              value={formData.titulo}
              onChange={handleChange}
            />
            <TextField
              label="Estoque"
              name="estoque"
              type="number"
              value={formData.estoque}
              onChange={handleChange}
            />
            <TextField
              label="Preço"
              name="preco"
              type="number"
              value={formData.preco}
              onChange={handleChange}
            />
            <Button variant="contained" color="primary" type="submit">
              {modalActive.isEditing ? "Editar" : "Criar"}
            </Button>
          </form>
        </Typography>
      </Box>
    </Modal>
  );
}
