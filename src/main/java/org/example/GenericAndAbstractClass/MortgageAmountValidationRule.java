package org.example.GenericAndAbstractClass;



// Дополните класс для проверки суммы ипотеки пользователя
public class MortgageAmountValidationRule extends ValidationRule<Integer> {

    public MortgageAmountValidationRule(Integer value) {
        super(value, "The mortgage amount should be between 1 million and 10 millions");
    }

    @Override
    public boolean isValid() {
        return value >= 1_000_000 && value <= 10_000_000;
    }
}