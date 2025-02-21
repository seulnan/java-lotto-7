package lotto.model.entity;


import java.util.List;
import java.util.Set;
import lotto.validator.LottoValidator;


public class WinningLotto {
    private final Set<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        LottoValidator.validateLottoNumbers(numbers); // 로또 번호 검증
        this.winningNumbers = Set.copyOf(numbers);

        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 달라야 합니다.");
        }
        this.bonusNumber = bonusNumber;
    }

    public int countMatchingNumbers(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean hasBonusNumber(Lotto lotto) {
        return winningNumbers.contains(bonusNumber);
    }
}
