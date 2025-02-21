package lotto.model.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class LottoTicket {
    private final List<Lotto> tickets;

    public LottoTicket(int count) {
        this.tickets = IntStream.range(0, count)
                .mapToObj(i -> generateLotto())
                .collect(Collectors.toList()); // ✅ Immutable List 대신 변경 가능한 List 사용
    }

    private Lotto generateLotto() {
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6)); // ✅ 변경 가능한 List로 변환
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public List<Lotto> getTickets() {
        return tickets;
    }
}
