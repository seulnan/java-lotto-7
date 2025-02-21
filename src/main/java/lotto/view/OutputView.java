package lotto.view;

import java.util.Comparator;
import java.util.stream.Stream;
import lotto.model.dto.LottoResultDto;
import lotto.enums.LottoRank;
import lotto.model.domain.LottoTicket;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.Collectors;

public class OutputView {
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.KOREA);

    public void printLottoTickets(LottoTicket lottoTicket) {
        System.out.printf("%d개를 구매했습니다.%n", lottoTicket.getTickets().size());
        lottoTicket.getTickets().forEach(lotto -> System.out.println(lotto.getNumbers()));
        System.out.println();
    }

    public void printResults(LottoResultDto result, int purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.print(formatLottoRank(result));
        printProfitRate(result, purchaseAmount);
    }

    private String formatLottoRank(LottoResultDto result) {
        return Stream.of(LottoRank.values())
                .filter(rank -> rank != LottoRank.NONE)
                .sorted(Comparator.comparingInt(LottoRank::getMatchCount).reversed())
                .map(rank -> formatLottoRankString(rank, result))
                .collect(Collectors.joining());
    }

    private String formatLottoRankString(LottoRank rank, LottoResultDto result) {
        return String.format("%d개 일치%s (%s원) - %d개%n",
                rank.getMatchCount(),
                rank == LottoRank.SECOND ? ", 보너스 볼 일치" : "",
                NUMBER_FORMAT.format(rank.getPrize()),
                result.resultMap().getOrDefault(rank, 0));
    }

    private void printProfitRate(LottoResultDto result, int purchaseAmount) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", result.getProfitRate(purchaseAmount));
    }
}