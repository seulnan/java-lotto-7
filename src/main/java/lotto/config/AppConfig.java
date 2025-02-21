package lotto.config;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();
    private static final LottoService LOTTO_SERVICE = new LottoService();

    private static final LottoController LOTTO_CONTROLLER =
            new LottoController(LOTTO_SERVICE, INPUT_VIEW, OUTPUT_VIEW);

    public static LottoController getLottoController() {
        return LOTTO_CONTROLLER;
    }
}
