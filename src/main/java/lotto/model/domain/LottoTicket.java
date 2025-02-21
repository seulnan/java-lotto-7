package lotto.model.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class LottoTicket {
    private final List<Lotto> tickets;
    private static final int LOTTO_MIN_NUMBER = 1;  // 🎯 로또 번호 최소값
    private static final int LOTTO_MAX_NUMBER = 45; // 🎯 로또 번호 최대값
    private static final int LOTTO_SIZE = 6;

    public LottoTicket(int count) {
        this.tickets = IntStream.range(0, count)
                .mapToObj(i -> generateLotto())
                .collect(Collectors.toList());
    }

    private Lotto generateLotto() {
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER, LOTTO_SIZE));
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public List<Lotto> getTickets() {
        return tickets;
    }
}
