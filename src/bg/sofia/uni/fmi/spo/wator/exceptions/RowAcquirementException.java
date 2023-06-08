package bg.sofia.uni.fmi.spo.wator.exceptions;

public class RowAcquirementException extends RuntimeException {
    public RowAcquirementException(int row) {
        super("Row acquirement was not successful for row " + row);
    }
}
