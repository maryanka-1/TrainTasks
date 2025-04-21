package org.example.GenericAndAbstractClass;



public class AgeValidationRule extends ValidationRule<Byte> {
    public AgeValidationRule(Byte value) {
        super(value, "You age must be more 18");
    }

    @Override
    public boolean isValid() {
        return value >= 18;
    }
}