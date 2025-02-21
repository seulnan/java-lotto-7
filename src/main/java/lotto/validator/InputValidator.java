package lotto.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class InputValidator {
    private static final int LOTTO_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public static int validatePurchaseAmount(String input) {
        int amount = parseInteger(input);
        if (amount <= 0 || amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
        return amount;
    }

    public static List<Integer> validateWinningNumbers(String input) {
        List<Integer> numbers = parseIntegerList(input);
        validateLottoNumbers(numbers);
        return numbers;
    }

    public static int validateBonusNumber(String input, List<Integer> winningNumbers) {
        int bonusNumber = parseInteger(input);
        validateNumberRange(bonusNumber, "[ERROR] 보너스 번호는 1~45 범위여야 합니다.");
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 달라야 합니다.");
        }
        return bonusNumber;
    }

    private static void validateLottoNumbers(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE || new HashSet<>(numbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개의 서로 다른 숫자여야 합니다.");
        }
        numbers.forEach(n -> validateNumberRange(n, "[ERROR] 로또 번호는 1~45 범위여야 합니다."));
    }

    private static void validateNumberRange(int number, String errorMessage) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static int parseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 올바른 숫자를 입력하세요.");
        }
    }

    private static List<Integer> parseIntegerList(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 올바른 숫자를 입력하세요.");
        }
    }
}