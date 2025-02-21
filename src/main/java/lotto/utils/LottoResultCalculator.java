package lotto.utils;

import lotto.enums.LottoRank;
import lotto.model.dto.LottoResultDto;
import lotto.model.entity.LottoTicket;
import lotto.model.entity.WinningLotto;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResultCalculator {
    public static LottoResultDto calculateResults(LottoTicket lottoTicket, WinningLotto winningLotto) {
        Map<LottoRank, Integer> results = lottoTicket.getTickets().stream()
                .map(ticket -> LottoRank.findRank(winningLotto.countMatchingNumbers(ticket), winningLotto.hasBonusNumber(ticket)))
                .collect(Collectors.groupingBy(rank -> rank, () -> new EnumMap<>(LottoRank.class), Collectors.summingInt(e -> 1)));

        return new LottoResultDto(results);
    }
}
