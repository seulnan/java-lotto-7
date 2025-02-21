package lotto.service;

import lotto.model.dto.LottoResultDto;
import lotto.model.entity.Lotto;
import lotto.model.entity.LottoTicket;
import lotto.model.entity.WinningLotto;
import lotto.enums.LottoRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService();
    }

    @Test
    void 로또_티켓_구매_테스트() {
        // given
        int purchaseAmount = 5000; // 1000원 단위로 나누어 5개 구매

        // when
        LottoTicket lottoTicket = lottoService.purchaseLottoTickets(purchaseAmount);

        // then
        assertThat(lottoTicket.getTickets()).hasSize(5);
    }

    @Test
    void 로또_결과_계산_테스트() {
        // given
        LottoTicket lottoTicket = new LottoTicket(2); // 2개의 로또 티켓 생성
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), // 당첨 번호와 동일한 로또
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 당첨되지 않는 로또
        );

        // LottoTicket 내부의 리스트를 교체하여 테스트에 맞춤
        lottoTicket.getTickets().clear();
        lottoTicket.getTickets().addAll(tickets);

        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        LottoResultDto getResultDto = lottoService.calculateResults(lottoTicket, winningLotto);

        // then
        Map<LottoRank, Integer> resultMap = getResultDto.resultMap();
        assertThat(resultMap.get(LottoRank.FIRST)).isEqualTo(1); // 첫 번째 로또가 6개 일치
        assertThat(resultMap.get(LottoRank.NONE)).isEqualTo(1); // 두 번째 로또는 당첨되지 않음
    }
}
