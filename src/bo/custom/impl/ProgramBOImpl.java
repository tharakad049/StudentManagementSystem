package bo.custom.impl;

import bo.custom.ProgramBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dto.ProgramDTO;
import entity.Program;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {

    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public List<ProgramDTO> getAllPrograms() throws SQLException, ClassNotFoundException {
        List<Program> all = programDAO.getAll();
        List<ProgramDTO> programDTOS=new ArrayList<>();
        for (Program program : all) {
            programDTOS.add(new ProgramDTO(program.getCode(), program.getDescription(),program.getDuration(), program.getpFee(),program.getFreeSpace()));
        }
        return programDTOS;
    }

    @Override
    public void deleteProgram(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
      programDAO.delete(new Program(programDTO.getCode(),programDTO.getDescription(),programDTO.getDuration(),programDTO.getpFee(),programDTO.getFreeSpace()));
    }

    @Override
    public void addProgram(ProgramDTO dto) throws SQLException, ClassNotFoundException {
        programDAO.add(new Program(dto.getCode(),dto.getDescription(),dto.getDuration(),dto.getpFee(),dto.getFreeSpace()));
    }

    @Override
    public void updateProgram(ProgramDTO dto) throws SQLException, ClassNotFoundException {
        programDAO.update(new Program(dto.getCode(),dto.getDescription(),dto.getDuration(),dto.getpFee(),dto.getFreeSpace()));
    }

    @Override
    public boolean ifProgramExist(String code) throws SQLException, ClassNotFoundException {
        return programDAO.ifProgramExist(code);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return programDAO.generateNewID();
    }
}
