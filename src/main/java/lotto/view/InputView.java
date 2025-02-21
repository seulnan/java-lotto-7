package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.validator.InputValidator;

import java.util.List;

public class InputView {
    public int readPurchaseAmount() {
        return getValidatedInput(
                "구입 금액을 입력해 주세요.",
                InputValidator::validatePurchaseAmount
        );
    }

    public List<Integer> readWinningNumbers() {
        return getValidatedInput(
                "당첨 번호를 입력해 주세요.",
                InputValidator::validateWinningNumbers
        );
    }

    public int readBonusNumber(List<Integer> winningNumbers) {
        return getValidatedInput(
                "보너스 번호를 입력해 주세요.",
                input -> InputValidator.validateBonusNumber(input, winningNumbers)
        );
    }

    private <T> T getValidatedInput(String message, InputFunction<String, T> validator) {
        while (true) {
            try {
                System.out.println(message);
                T value = validator.apply(Console.readLine());
                System.out.println();
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FunctionalInterface
    private interface InputFunction<T, R> {
        R apply(T t);
    }
}