package lotto.service;

import lotto.enums.LottoPrice;
import lotto.model.dto.LottoResultDto;
import lotto.model.entity.LottoTicket;
import lotto.model.entity.WinningLotto;
import lotto.utils.LottoResultCalculator;

public class LottoService {
    public LottoTicket purchaseLottoTickets(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int ticketCount = purchaseAmount / LottoPrice.PRICE_PER_TICKET.getPrice();
        return new LottoTicket(ticketCount);
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0 || purchaseAmount % LottoPrice.PRICE_PER_TICKET.getPrice() != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    public LottoResultDto calculateResults(LottoTicket lottoTicket, WinningLotto winningLotto) {
        return LottoResultCalculator.calculateResults(lottoTicket, winningLotto);
    }
}
