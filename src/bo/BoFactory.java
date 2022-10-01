package bo;

import bo.custom.impl.PurchasePlaceProgramBOImpl;
import bo.custom.impl.StudentBOImpl;
import bo.custom.impl.ProgramBOImpl;


public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case PROGRAM:
                return new ProgramBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case PURCHASE_PLACEPROGRAM:
                return new PurchasePlaceProgramBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        STUDENT, PROGRAM, PURCHASE_PLACEPROGRAM
    }
}
