package controller;

import bo.BoFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import dto.StudentDTO;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageStudentsFormController {
    private final StudentBO studentBO = (StudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT);
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public JFXButton btnReport;
    public JFXButton btnAddNewStudent;
    public TableView <StudentTM> tblStudents;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtStudentCity;
    public JFXTextField txtStudentFee;
    public JFXTextField txtStudentAge;
    public JFXTextField txtStudentTitle;

    public void initialize() {

        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("city"));
        tblStudents.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblStudents.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("age"));

        initUI();

        tblStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtStudentId.setText(newValue.getId());
                txtStudentTitle.setText(newValue.getTitle());
                txtStudentName.setText(newValue.getName());
                txtStudentAddress.setText(newValue.getAddress());
                txtStudentCity.setText(newValue.getCity());
                txtStudentFee.setText(newValue.getFee());
                txtStudentAge.setText(newValue.getAge());

                txtStudentId.setDisable(false);
                txtStudentTitle.setDisable(false);
                txtStudentName.setDisable(false);
                txtStudentAddress.setDisable(false);
                txtStudentCity.setDisable(false);
                txtStudentFee.setDisable(false);
                txtStudentAge.setDisable(false);
            }
        });
        txtStudentFee.setOnAction(event -> btnSave.fire());
        loadAllStudents();
    }

    private void loadAllStudents() {
        tblStudents.getItems().clear();
        try {
            List<StudentDTO> allStudents = studentBO.getAllStudents();
            for (StudentDTO student : allStudents) {
                tblStudents.getItems().add(new StudentTM(student.getId(),student.getTitle(), student.getName(), student.getAddress(), student.getCity(), student.getFee(), student.getAge()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void initUI() {
        txtStudentId.clear();
        txtStudentTitle.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentCity.clear();
        txtStudentFee.clear();
        txtStudentAge.clear();

        txtStudentId.setDisable(true);
        txtStudentTitle.setDisable(true);
        txtStudentName.setDisable(true);
        txtStudentAddress.setDisable(true);
        txtStudentCity.setDisable(true);
        txtStudentFee.setDisable(true);
        txtStudentAge.setDisable(true);

        txtStudentId.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String title = txtStudentTitle.getText();
        String name = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String city = txtStudentCity.getText();
        String fee = txtStudentFee.getText();
        String age = txtStudentAge.getText();

        if (!title.matches("^(Male|Female)$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Title. You can entered 'Male' or 'Female' ").show();
            txtStudentTitle.requestFocus();
            return;

        }else if (!name.matches("^[A-z]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Name should be at least 3 characters long").show();
            txtStudentName.requestFocus();
            return;

        }else if (!address.matches("^[A-z]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtStudentAddress.requestFocus();
            return;

        }else if (!city.matches("^[A-z]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "City should be at least 3 characters long").show();
            txtStudentCity.requestFocus();
            return;

        }else if (!fee.matches("^[0-5]{5,6}$")) {
            new Alert(Alert.AlertType.ERROR, "Fee should be at least 5 characters long").show();
            txtStudentFee.requestFocus();
            return;

        }else if (!age.matches("^[1-9]{2}$")) {
            new Alert(Alert.AlertType.ERROR, "Age should be at least 3 letters. ").show();
            txtStudentAge.requestFocus();
            return;
        }
        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existStudent(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }
                StudentDTO studentDTO = new StudentDTO(id,title, name,  address, city, fee, age);
                studentBO.addStudent(studentDTO);
                tblStudents.getItems().add(new StudentTM(id, title, name,  address, city,fee, age));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the Student " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {
            try {
                if (!existStudent(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such Student associated with the id " + id).show();
                }
                StudentDTO studentDTO = new StudentDTO(id,title, name,  address, city, fee, age);
                studentBO.updateStudent(studentDTO);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the Student " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            StudentTM selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
            selectedStudent.setTitle(title);
            selectedStudent.setName(name);
            selectedStudent.setAddress(address);
            selectedStudent.setCity(city);
            selectedStudent.setFee(fee);
            selectedStudent.setAge(age);
            tblStudents.refresh();
        }
        btnAddNewStudent.fire();
    }

    boolean existStudent(String id) throws SQLException, ClassNotFoundException {
        return studentBO.ifStudentExist(id);
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = tblStudents.getSelectionModel().getSelectedItem().getId();
        StudentTM studentTM=tblStudents.getSelectionModel().getSelectedItem();
        try {
            if (!existStudent(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such Student associated with the id " + id).show();
            }
            studentBO.deleteStudent(new StudentDTO(studentTM.getId(),studentTM.getTitle(),studentTM.getName(),studentTM.getAddress(),studentTM.getCity(),studentTM.getFee(),studentTM.getAge()));
            tblStudents.getItems().remove(tblStudents.getSelectionModel().getSelectedItem());
            tblStudents.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Student " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private String getLastCustomerId() {
        List<StudentTM> tempCustomersList = new ArrayList<>(tblStudents.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getId();
    }

    private String generateNewId() {
        try {
            return studentBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblStudents.getItems().isEmpty()) {
            return "S-001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("S-", "")) + 1;
            return String.format("S-%03d", newCustomerId);
        }
    }

    public void searchStudent(ActionEvent actionEvent){
    }

    public void addNewStudentOnAction(ActionEvent actionEvent) {
        txtStudentId.setDisable(false);
        txtStudentTitle.setDisable(false);
        txtStudentName.setDisable(false);
        txtStudentAddress.setDisable(false);
        txtStudentCity.setDisable(false);
        txtStudentFee.setDisable(false);
        txtStudentAge.setDisable(false);

        txtStudentId.clear();
        txtStudentId.setText(generateNewId());
        txtStudentTitle.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentCity.clear();
        txtStudentFee.clear();
        txtStudentAge.clear();

        txtStudentName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblStudents.getSelectionModel().clearSelection();
    }

    public void studentReport(ActionEvent actionEvent) {}
}
