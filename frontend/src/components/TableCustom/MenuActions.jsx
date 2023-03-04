import React, { useContext, useState } from "react";
import Button from "@mui/material/Button";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import { ProductContext } from "../../context/useProduct";
import { ModalContext } from "../../context/useModal";

export default function MenuActions({ product }) {
  const { removeProduct } = useContext(ProductContext);
  const { modalActive, setModalActive } = useContext(ModalContext);
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleDelete = () => {
    removeProduct(product.id);
  };

  const handleEdit = () => {
    setModalActive({
      ...modalActive,
      isOpen: true,
      isEditing: true,
      dataForm: product,
    });

    handleClose();
  };

  return (
    <div>
      <Button
        id="basic-button"
        aria-controls={open ? "basic-menu" : undefined}
        aria-haspopup="true"
        aria-expanded={open ? "true" : undefined}
        onClick={handleClick}
      >
        <MoreVertIcon sx={{ color: "#969696" }} />
      </Button>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          "aria-labelledby": "basic-button",
        }}
      >
        <MenuItem
          onClick={handleEdit}
          sx={{ fontWeight: "bold", fontSize: 16 }}
        >
          <EditIcon sx={{ color: "#0026ff", mr: 2 }} /> Editar
        </MenuItem>
        <MenuItem
          onClick={handleDelete}
          sx={{ fontWeight: "bold", fontSize: 16 }}
        >
          <DeleteForeverIcon sx={{ color: "#ff0000", mr: 2 }} /> Excluir
        </MenuItem>
      </Menu>
    </div>
  );
}
