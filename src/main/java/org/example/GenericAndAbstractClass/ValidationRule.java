package org.example.GenericAndAbstractClass;

public abstract class ValidationRule<T> {
    public final T value;
    public String errorMessage;

    public ValidationRule(T value, String errorMessage) {
        this.value = value;
        this.errorMessage = errorMessage;
    }

    public abstract boolean isValid();

    public String getErrorMessage() {
        return errorMessage;
    }

}
