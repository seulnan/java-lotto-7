package lotto.controller;

import java.util.List;
import lotto.model.dto.LottoResultDto;
import lotto.model.entity.LottoTicket;
import lotto.model.entity.WinningLotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(LottoService lottoService, InputView inputView, OutputView outputView) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startGame() {
        int purchaseAmount = inputView.readPurchaseAmount();
        LottoTicket lottoTicket = lottoService.purchaseLottoTickets(purchaseAmount);
        outputView.printLottoTickets(lottoTicket);

        WinningLotto winningLotto = getWinningLotto();
        LottoResultDto result = lottoService.calculateResults(lottoTicket, winningLotto);
        outputView.printResults(result, purchaseAmount);
    }

    private WinningLotto getWinningLotto() {
        List<Integer> winningNumbers = inputView.readWinningNumbers(); // 당첨 번호 입력
        int bonusNumber = inputView.readBonusNumber(winningNumbers); // 당첨 번호를 넘겨줌
        return new WinningLotto(winningNumbers, bonusNumber);
    }

}
