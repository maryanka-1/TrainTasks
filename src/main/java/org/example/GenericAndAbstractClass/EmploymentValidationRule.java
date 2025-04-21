package org.example.GenericAndAbstractClass;



// Дополните класс для проверки трудоустроенности пользователя
public class EmploymentValidationRule extends ValidationRule<Boolean> {

    public EmploymentValidationRule(Boolean value) {
        super(value, "You must have a work");
    }

    @Override
    public boolean isValid() {
        return value;
    }

// Объявите и реализуйте метод
}
