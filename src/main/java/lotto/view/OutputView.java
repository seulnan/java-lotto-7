package lotto.view;

import lotto.model.dto.LottoResultDto;
import lotto.model.entity.LottoTicket;
import lotto.enums.LottoRank;

import java.text.NumberFormat;
import java.util.Locale;

public class OutputView {
    private static final NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA); // ✅ 천 단위 콤마 추가

    public void printLottoTickets(LottoTicket lottoTicket) {
        System.out.printf("%d개를 구매했습니다.%n", lottoTicket.getTickets().size());
        lottoTicket.getTickets().forEach(lotto -> System.out.println(lotto.getNumbers()));
        System.out.println(); // ✅ 구매한 로또 출력 후 개행
    }

    public void printResults(LottoResultDto result, int purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("---");

        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NONE) continue; // NONE 등수는 출력 안 함

            System.out.printf("%d개 일치%s (%s원) - %d개%n",
                    rank.getMatchCount(),
                    rank == LottoRank.SECOND ? ", 보너스 볼 일치" : "",
                    numberFormat.format(rank.getPrize()), // ✅ 천 단위 콤마 추가
                    result.resultMap().getOrDefault(rank, 0));
        }

        System.out.println(); // ✅ 당첨 통계 출력 후 개행
        System.out.printf("총 수익률은 %.1f%%입니다.%n", result.getProfitRate(purchaseAmount));
    }
}
