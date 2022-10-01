package controller;

import bo.BoFactory;
import bo.custom.PurchasePlaceProgramBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import dto.StudentDTO;
import dto.ProgramDTO;


import dto.StudentProgramDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.PlaceProgramDetailTM;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;


public class ManagePlaceProgramFormController {

    private final PurchasePlaceProgramBO purchasePlaceProgramBO = (PurchasePlaceProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PURCHASE_PLACEPROGRAM);
    public Label lblDate;
    public Label lblTotal;
    public JFXButton btnSave;
    public JFXButton btnPlaceProgram;
    public JFXTextField txtDescription;
    public JFXTextField txtQty;
    public JFXTextField txtStudentName;
    public JFXTextField txtFreeSpace;
    public JFXTextField txtProgramFee;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtStudentCity;
    public JFXComboBox <String> cmbStudentId;
    public JFXComboBox <String> cmbProgramCode;
    public TableView <PlaceProgramDetailTM> tblPlaceProgramDetails;
    List<StudentDTO> allStudents = purchasePlaceProgramBO.getAllStudents();
    List<ProgramDTO> allPrograms = purchasePlaceProgramBO.getAllPrograms();
    public void initialize() {

        tblPlaceProgramDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblPlaceProgramDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblPlaceProgramDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblPlaceProgramDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("programFee"));
        tblPlaceProgramDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<PlaceProgramDetailTM, Button> lastCol = (TableColumn<PlaceProgramDetailTM, Button>) tblPlaceProgramDetails.getColumns().get(5);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("DELETE");

            btnDelete.setOnAction(event -> {
                tblPlaceProgramDetails.getItems().remove(param.getValue());
                tblPlaceProgramDetails.getSelectionModel().clearSelection();
                calculateTotal();
                enableOrDisablePlaceOrderButton();
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        lblDate.setText(LocalDate.now().toString());
        btnPlaceProgram.setDisable(true);
        txtStudentName.setFocusTraversable(false);
        txtStudentName.setEditable(false);
        txtDescription.setFocusTraversable(false);
        txtDescription.setEditable(false);
        txtProgramFee.setFocusTraversable(false);
        txtProgramFee.setEditable(false);
        txtFreeSpace.setFocusTraversable(false);
        txtFreeSpace.setEditable(false);
        txtQty.setOnAction(event -> btnSave.fire());
        txtQty.setEditable(false);
        btnSave.setDisable(true);


        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            enableOrDisablePlaceOrderButton();

            if (newValue != null) {
                try {
                    try {
                        if (!existStudent(newValue + "")) {
                            new Alert(Alert.AlertType.ERROR, "There is no such student associated with the id " + newValue + "").show();
                        }
                        StudentDTO studentDTO = purchasePlaceProgramBO.searchStudent(newValue + "");
                        txtStudentName.setText(studentDTO.getName());
                        txtStudentAddress.setText(studentDTO.getAddress());
                        txtStudentCity.setText(studentDTO.getCity());

                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to find the student " + newValue + "" + e).show();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                txtStudentName.clear();
                txtStudentAddress.clear();
                txtStudentCity.clear();
            }
        });

        cmbProgramCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newProgramCode) -> {
            txtQty.setEditable(newProgramCode != null);
            btnSave.setDisable(newProgramCode == null);

            if (newProgramCode != null) {
                try {
                    if (!existProgram(newProgramCode + "")) {
                    }
                    ProgramDTO program = purchasePlaceProgramBO.searchProgram(newProgramCode);
                    txtDescription.setText(program.getDescription());
                    txtProgramFee.setText(program.getpFee().setScale(2).toString());
                    Optional<PlaceProgramDetailTM> optOrderDetail = tblPlaceProgramDetails.getItems().stream().filter(detail -> detail.getCode().equals(newProgramCode)).findFirst();
                    txtFreeSpace.setText((optOrderDetail.isPresent() ? program.getFreeSpace() - optOrderDetail.get().getQty() : program.getFreeSpace()) + "");

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {
                txtDescription.clear();
                txtQty.clear();
                txtFreeSpace.clear();
                txtProgramFee.clear();
            }
        });

        tblPlaceProgramDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {
            if (selectedOrderDetail != null) {
                cmbProgramCode.setDisable(true);
                cmbProgramCode.setValue(selectedOrderDetail.getCode());
                btnSave.setText("Update Details");
                txtFreeSpace.setText(Integer.parseInt(txtFreeSpace.getText()) + selectedOrderDetail.getQty() + "");
                txtQty.setText(selectedOrderDetail.getQty() + "");
            } else {
                btnSave.setText("Add To Cart");
                cmbProgramCode.setDisable(false);
                cmbProgramCode.getSelectionModel().clearSelection();
                txtQty.clear();
            }
        });

            loadAllStudentsIds();
            loadAllProgramCodes();

    }

    private void loadAllStudentsIds(){
        for (StudentDTO allStudent : allStudents) {
            cmbStudentId.getItems().add(allStudent.getId());
        }


    }
    private void loadAllProgramCodes() {
            for (ProgramDTO dto : allPrograms) {
                cmbProgramCode.getItems().add(dto.getCode());
            }
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        if (!txtQty.getText().matches("1") || Integer.parseInt(txtQty.getText()) <= 0 ||
                Integer.parseInt(txtQty.getText()) > Integer.parseInt(txtFreeSpace.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid quantity. ").show();
            txtQty.requestFocus();
            txtQty.selectAll();
            return;
        }

        String programCode = cmbProgramCode.getSelectionModel().getSelectedItem();
        String description = txtDescription.getText();
        BigDecimal programFee = new BigDecimal(txtProgramFee.getText()).setScale(2);
        int qty = Integer.parseInt(txtQty.getText());
        BigDecimal total = programFee.multiply(new BigDecimal(qty)).setScale(2);

        boolean exists = tblPlaceProgramDetails.getItems().stream().anyMatch(detail -> detail.getCode().equals(programCode));

        if (exists) {
            PlaceProgramDetailTM placeProgramDetailTM = tblPlaceProgramDetails.getItems().stream().filter(detail -> detail.getCode().equals(programCode)).findFirst().get();

            if (btnSave.getText().equalsIgnoreCase("Update Details")) {
                placeProgramDetailTM.setQty(qty);
                placeProgramDetailTM.setTotal(total);
                tblPlaceProgramDetails.getSelectionModel().clearSelection();
            } else {
                placeProgramDetailTM.setQty(placeProgramDetailTM.getQty() + qty);
                total = new BigDecimal(placeProgramDetailTM.getQty()).multiply(programFee).setScale(2);
                placeProgramDetailTM.setTotal(total);
            }
            tblPlaceProgramDetails.refresh();
        } else {
            tblPlaceProgramDetails.getItems().add(
                    new PlaceProgramDetailTM(
                            programCode,
                            description,
                            qty,
                            programFee,
                            total
                    ));
        }
        cmbProgramCode.getSelectionModel().clearSelection();
        cmbProgramCode.requestFocus();
        calculateTotal();
        enableOrDisablePlaceOrderButton();
    }

    private void calculateTotal() {
        BigDecimal total = new BigDecimal(0);
        for (PlaceProgramDetailTM detail : tblPlaceProgramDetails.getItems()) {
            total = total.add(detail.getTotal());
        }
        lblTotal.setText("Place Program total :- " + total);
    }

    private void enableOrDisablePlaceOrderButton() {
        btnPlaceProgram.setDisable(!(cmbStudentId.getSelectionModel().getSelectedItem() != null &&
                !tblPlaceProgramDetails.getItems().isEmpty()));
    }


    public ProgramDTO findItem(String code) {
        try {
            return purchasePlaceProgramBO.searchProgram(code);
        } catch (SQLException e) {
            throw new RuntimeException("PROGRAM find Failed " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean existProgram(String code) throws SQLException, ClassNotFoundException {
        return purchasePlaceProgramBO.ifProgramExist(code);
    }

    boolean existStudent(String id) throws SQLException, ClassNotFoundException {
        return purchasePlaceProgramBO.ifStudentExist(id);
    }

    public void placeProgramOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO =null;
        ProgramDTO programDTO =null;

        for (StudentDTO student : allStudents) {
            if (cmbStudentId.getValue().equals(student.getId())){
                studentDTO=student;
            }
        }
        for (ProgramDTO program : allPrograms) {
            if (cmbProgramCode.getValue().equals(program.getCode())){
                programDTO=program;
            }
        }


        StudentProgramDTO studentProgramDTO = new StudentProgramDTO();
        studentProgramDTO.setStudentDTO(studentDTO);
        studentProgramDTO.setProgramDTO(programDTO);


        if (studentDTO!=null & programDTO!=null) {

            purchasePlaceProgramBO.purchasePlaceProgram(studentProgramDTO);
            new Alert(Alert.AlertType.INFORMATION, "SUCCESSFULLY Place Program has been PLACED").show();

            cmbStudentId.getSelectionModel().clearSelection();
            cmbProgramCode.getSelectionModel().clearSelection();
            tblPlaceProgramDetails.getItems().clear();
            txtQty.clear();
            calculateTotal();
        }
    }
}
