package lotto.view;

import java.util.Comparator;
import lotto.model.dto.LottoResultDto;
import lotto.enums.LottoRank;
import lotto.model.entity.LottoTicket;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class OutputView {
    private static final NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);

    public void printLottoTickets(LottoTicket lottoTicket) {
        System.out.printf("%d개를 구매했습니다.%n", lottoTicket.getTickets().size());
        lottoTicket.getTickets().forEach(lotto -> System.out.println(lotto.getNumbers()));
        System.out.println();
    }

    public void printResults(LottoResultDto result, int purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.print(formatLottoRank(result));
        System.out.printf("총 수익률은 %.1f%%입니다.%n", result.getProfitRate(purchaseAmount));
    }

    private String formatLottoRank(LottoResultDto result) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank != LottoRank.NONE)
                .sorted(Comparator.comparingInt(LottoRank::getMatchCount))
                .map(rank -> String.format("%d개 일치%s (%s원) - %d개%n",
                        rank.getMatchCount(),
                        rank == LottoRank.SECOND ? ", 보너스 볼 일치" : "",
                        numberFormat.format(rank.getPrize()),
                        result.resultMap().getOrDefault(rank, 0)))
                .collect(Collectors.joining());
    }
}
