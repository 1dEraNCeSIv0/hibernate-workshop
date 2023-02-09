package de.andrena.hibernateworkshop.exception;

public class MandatoryFieldException extends RuntimeException {

    public MandatoryFieldException(String fieldName) {
        super(String.format("Field %s is mandatory in this context.", fieldName));
    }

}
