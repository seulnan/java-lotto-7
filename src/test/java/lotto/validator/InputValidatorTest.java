package lotto.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

class InputValidatorTest {

    @Test
    @DisplayName("정상적인 구입 금액 검증")
    void validatePurchaseAmount_valid() {
        int amount = InputValidator.validatePurchaseAmount("5000");
        assertThat(amount).isEqualTo(5000);
    }

    @Test
    @DisplayName("구입 금액이 1000원 단위가 아닐 경우 예외 발생")
    void validatePurchaseAmount_invalid() {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount("5500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 값이 입력될 경우 예외 발생")
    void validatePurchaseAmount_nonNumeric() {
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount("abcd"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바른 숫자를 입력하세요.");
    }

    @Test
    @DisplayName("정상적인 당첨 번호 검증")
    void validateWinningNumbers_valid() {
        List<Integer> numbers = InputValidator.validateWinningNumbers("1,2,3,4,5,6");
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아닐 경우 예외 발생")
    void validateWinningNumbers_invalidSize() {
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개의 서로 다른 숫자여야 합니다.");
    }

    @Test
    @DisplayName("중복된 당첨 번호가 포함될 경우 예외 발생")
    void validateWinningNumbers_duplicate() {
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers("1,2,3,4,5,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개의 서로 다른 숫자여야 합니다.");
    }

    @Test
    @DisplayName("1~45 범위를 벗어난 당첨 번호가 포함될 경우 예외 발생")
    void validateWinningNumbers_outOfRange() {
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers("1,2,3,4,5,46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1~45 범위여야 합니다.");
    }

    @Test
    @DisplayName("정상적인 보너스 번호 검증")
    void validateBonusNumber_valid() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = InputValidator.validateBonusNumber("7", winningNumbers);
        assertThat(bonusNumber).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복될 경우 예외 발생")
    void validateBonusNumber_duplicate() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> InputValidator.validateBonusNumber("5", winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 달라야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 1~45 범위를 벗어날 경우 예외 발생")
    void validateBonusNumber_outOfRange() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> InputValidator.validateBonusNumber("50", winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1~45 범위여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 보너스 번호 입력 시 예외 발생")
    void validateBonusNumber_nonNumeric() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> InputValidator.validateBonusNumber("abc", winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바른 숫자를 입력하세요.");
    }
}