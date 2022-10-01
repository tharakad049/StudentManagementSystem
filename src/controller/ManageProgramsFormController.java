package controller;

import bo.BoFactory;
import bo.custom.ProgramBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import dto.ProgramDTO;
import view.tm.ProgramTM;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageProgramsFormController {
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public JFXButton btnPrintReport;
    public JFXButton btnAddNewProgram;
    public TableView <ProgramTM> tblPrograms;
    public JFXTextField txtDuration;
    public JFXTextField txtPFee;
    public JFXTextField txtFreeSpace;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    private final ProgramBO programBO = (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);

    public void initialize() {
        tblPrograms.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblPrograms.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblPrograms.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblPrograms.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("pFee"));
        tblPrograms.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("freeSpace"));

        initUI();
        tblPrograms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                txtDuration.setText(newValue.getDuration());
                txtPFee.setText(newValue.getpFee().setScale(2).toString());
                txtFreeSpace.setText(newValue.getFreeSpace() + "");

                txtCode.setDisable(false);
                txtDescription.setDisable(false);
                txtDuration.setDisable(false);
                txtPFee.setDisable(false);
                txtFreeSpace.setDisable(false);
            }
        });

        txtFreeSpace.setOnAction(event -> btnSave.fire());
        loadAllPrograms();
    }

    private void loadAllPrograms() {
        tblPrograms.getItems().clear();
        try {
            List<ProgramDTO> allPrograms = programBO.getAllPrograms();
            for (ProgramDTO program : allPrograms) {
                tblPrograms.getItems().add(new ProgramTM(program.getCode(), program.getDescription(), program.getDuration(), program.getpFee(), program.getFreeSpace()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtCode.clear();
        txtDescription.clear();
        txtDuration.clear();
        txtPFee.clear();
        txtFreeSpace.clear();

        txtCode.setDisable(true);
        txtDescription.setDisable(true);
        txtDuration.setDisable(true);
        txtPFee.setDisable(true);
        txtFreeSpace.setDisable(true);

        txtCode.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        /*String code = tblPrograms.getSelectionModel().getSelectedItem().getCode();
        ProgramTM programTM=tblPrograms.getSelectionModel().getSelectedItem();
        try {
            if (!existProgram(code)) {
                new Alert(Alert.AlertType.ERROR, "There is no such Program associated with the code " + code).show();
            }

            programBO.deleteProgram(new );
            tblPrograms.getItems().remove(tblPrograms.getSelectionModel().getSelectedItem());
            tblPrograms.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Program " + code).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        String duration = txtDuration.getText();
        BigDecimal pFee = new BigDecimal(txtPFee.getText()).setScale(2);
        int freeSpace = Integer.parseInt(txtFreeSpace.getText());

        if (!description.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtDescription.requestFocus();
            return;

        } else if (!duration.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Duration").show();
            txtDuration.requestFocus();
             return;

        } else if (!txtPFee.getText().matches("^[0-9]+[.]?[0-9]*$" /*"^[0-5]{5}$"*/)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Program Fee").show();
            txtPFee.requestFocus();
            return;

        } else if (!txtFreeSpace.getText().matches(  "^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Free Space").show();
            txtFreeSpace.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existProgram(code)) {
                    new Alert(Alert.AlertType.ERROR, code + " already exists").show();
                }
                ProgramDTO dto = new ProgramDTO(code, description, duration, pFee, freeSpace);
                programBO.addProgram(dto);

                tblPrograms.getItems().add(new ProgramTM(code, description, duration,pFee,freeSpace));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!existProgram(code)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such Program associated with the code " + code).show();
                }
                ProgramDTO dto = new ProgramDTO(code, description, duration,pFee,freeSpace);
                programBO.updateProgram(dto);

                ProgramTM selectedProgram = tblPrograms.getSelectionModel().getSelectedItem();
                selectedProgram.setDescription(description);
                selectedProgram.setFreeSpace(freeSpace);
                selectedProgram.setpFee(pFee);
                tblPrograms.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        btnAddNewProgram.fire();
    }

    private String generateNewId() {
        try {
            return programBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "P-001";
    }

    private boolean existProgram(String code) throws SQLException, ClassNotFoundException {
        return programBO.ifProgramExist(code);
    }

    public void printReportOnAction(ActionEvent actionEvent) {}

    public void addNewProgramOnAction(ActionEvent actionEvent) {
        txtCode.setDisable(false);
        txtDescription.setDisable(false);
        txtDuration.setDisable(false);
        txtPFee.setDisable(false);
        txtFreeSpace.setDisable(false);

        txtCode.clear();
        txtCode.setText(generateNewId());
        txtDescription.clear();
        txtDuration.clear();
        txtPFee.clear();
        txtFreeSpace.clear();
        txtDescription.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblPrograms.getSelectionModel().clearSelection();
    }
}
