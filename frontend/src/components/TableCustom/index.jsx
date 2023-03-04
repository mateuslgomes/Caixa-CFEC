import React, { useContext } from "react";
import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import MenuActions from "./MenuActions";
import ControlPointIcon from "@mui/icons-material/ControlPoint";
import { Box, Button, Container, Toolbar, Typography } from "@mui/material";
import PaginationCustom from "./Pagination";
import { ModalContext } from "../../context/useModal";
import { ProductContext } from "../../context/useProduct";
import ReactLoading from "react-loading";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: "#a4a5fa",
    color: "#000",
    fontWeight: "bold",
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 18,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

const columns = [
  { name: "Adicionar", key: "add", align: "left" },
  { name: "Serviço", key: "servico", align: "left" },
  { name: "Estoque", key: "estoque", align: "right" },
  { name: "Valor", key: "valor", align: "right" },
  { name: "Ações", key: "acoes", align: "right" },
];

export default function TableCustom() {
  const { modalActive, setModalActive } = useContext(ModalContext);
  const { setTotalOfProducts, products, total, loading } =
    useContext(ProductContext);

  const handleAddInTotal = (price) => {
    setTotalOfProducts(price);
  };

  return (
    <Box sx={{ width: "100%" }}>
      <Paper sx={{ width: "100%", mb: 2 }}>
        <Toolbar
          sx={{
            display: "flex",
            alignItems: "center",
            background: "#7e80f7",
            justifyContent: "space-between",
            pl: { sm: 2 },
            pr: { xs: 1, sm: 1 },
          }}
        >
          <Typography
            sx={{
              flex: "1 1 100%",
              fontWeight: "bold",
              letterSpacing: 1,
              fontSize: 25,
            }}
            variant="h6"
            id="tableTitle"
            component="div"
          >
            Serviços TI
          </Typography>

          <Button
            variant="contained"
            sx={{ background: "#040258", ":hover": { background: "#382af3" } }}
            onClick={() =>
              setModalActive({
                ...modalActive,
                isOpen: true,
                isEditing: false,
                dataForm: { titulo: "", preco: 0, estoque: 0 },
              })
            }
          >
            Cadastrar
          </Button>
        </Toolbar>

        <TableContainer>
          <Table sx={{ minWidth: 700 }} aria-label="customized table">
            <TableHead>
              <TableRow>
                {columns.map(({ name, align, key }) => (
                  <StyledTableCell align={align} key={key}>
                    {name}
                  </StyledTableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {!loading && !!products.length ? (
                products.map((product) => (
                  <StyledTableRow key={product.id}>
                    <StyledTableCell component="th" scope="row">
                      <Button
                        variant="text"
                        onClick={() => handleAddInTotal(product.preco)}
                      >
                        <ControlPointIcon sx={{ color: "#009e28" }} />
                      </Button>
                    </StyledTableCell>
                    <StyledTableCell
                      component="th"
                      scope="row"
                      noWrap
                      style={{ textOverflow: "ellipsis" }}
                    >
                      {product.titulo}
                    </StyledTableCell>
                    <StyledTableCell
                      align="right"
                      noWrap
                      style={{ textOverflow: "ellipsis" }}
                    >
                      {product.estoque}
                    </StyledTableCell>
                    <StyledTableCell
                      align="right"
                      noWrap
                      style={{ textOverflow: "ellipsis" }}
                    >
                      {new Intl.NumberFormat("PT-BR", {
                        style: "currency",
                        currency: "BRL",
                      }).format(product.preco)}
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      <MenuActions product={product} />
                    </StyledTableCell>
                  </StyledTableRow>
                ))
              ) : (
                <Typography
                  sx={{ p: 4 }}
                  variant="h6"
                  id="tableTitle"
                  component="div"
                  textAlign={"center"}
                >
                  {loading ? (
                    <Container
                      sx={{
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "space-evenly",
                      }}
                    >
                      <ReactLoading
                        type={"spin"}
                        color={"#000000"}
                        height={"10%"}
                        width={"10%"}
                      />
                      Carregando...
                    </Container>
                  ) : (
                    "Você não possui nenhum produto na sua listagem."
                  )}
                </Typography>
              )}
            </TableBody>
          </Table>
        </TableContainer>
      </Paper>

      <Paper
        sx={{
          width: "100%",
          height: "100%",
          mt: 2,
          display: "flex",
          justifyContent: "flex-end",
          alignItems: "center",
        }}
      >
        <PaginationCustom />
      </Paper>
      <Paper sx={{ width: "100%", mt: 2, p: 2 }}>
        <Typography
          sx={{ flex: "1 1 100%", fontWeight: "bold" }}
          variant="h5"
          id="tableTitle"
          component="div"
        >
          Total:
          {new Intl.NumberFormat("PT-BR", {
            style: "currency",
            currency: "BRL",
          }).format(total)}
        </Typography>
      </Paper>
    </Box>
  );
}
