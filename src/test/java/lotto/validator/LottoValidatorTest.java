package lotto.validator;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

class LottoValidatorTest {

    @Test
    void 로또_번호_유효성_검사_정상() {
        // given
        List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then (예외 발생하지 않아야 함)
        LottoValidator.validateLottoNumbers(validNumbers);
    }

    @Test
    void 로또_번호_개수_오류() {
        // given
        List<Integer> invalidNumbers = List.of(1, 2, 3, 4, 5); // 5개만 있음

        // when & then
        assertThatThrownBy(() -> LottoValidator.validateLottoNumbers(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 6개의 서로 다른 숫자여야 합니다.");
    }

    @Test
    void 보너스_번호_중복_오류() {
        // given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int duplicateBonusNumber = 3; // 당첨 번호에 포함됨

        // when & then
        assertThatThrownBy(() -> LottoValidator.validateBonusNumber(duplicateBonusNumber, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 당첨 번호와 달라야 합니다.");
    }
}