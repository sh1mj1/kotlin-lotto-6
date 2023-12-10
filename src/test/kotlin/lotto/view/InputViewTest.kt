package lotto.view

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputViewTest {
    private lateinit var inputView: InputView

    @BeforeEach
    fun setUp() {
        inputView = InputView()
    }

    @ParameterizedTest
    @ValueSource(strings = ["숫자", "천원"])
    fun `금액 입력에서 숫자가 아닌 것 입력 시 예외`(input: String) {
        val exception = assertThrows<IllegalArgumentException> {
            inputView.readPayment(input)
        }
        assertThat(exception.message).isEqualTo("숫자로 입력해주세요.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["1030", "13010"])
    fun `금액 입력에서 숫자 입력 시 통과`(input: String) {
        assertDoesNotThrow {
            inputView.readPayment(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1|2|3|4|5|6", "1;2;3;4;5;6", "sdlfk"])
    fun `당첨 번호 입력 시 다른 구분자 입력`(input: String) {
        val exception = assertThrows<IllegalArgumentException> {
            inputView.readWinningNumbers(input)
        }
        assertThat(exception.message).isEqualTo("당첨 번호를 제대로 입력해주세요. (구분자는 , 입니다.)")
    }

    @ParameterizedTest
    @ValueSource(strings = ["1,2", "1", "1,2,3,4,5,6"])
    fun `당첨번호 입력 시 숫자와 올바른 구분자만 입력 시 통과`(input: String) {
        assertDoesNotThrow {
            inputView.readWinningNumbers(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["십구", "1십8"])
    fun `보너스 번호 입력에서 숫자가 아닌 것 입력 시 예외`(input: String) {
        val exception = assertThrows<IllegalArgumentException> {
            inputView.readBonusNumber(input)
        }
        assertThat(exception.message).isEqualTo("숫자로 입력해주세요.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "-19", "200"])
    fun `보너스 번호 입력에서 숫자만 입력 시 통과`(input: String) {
        assertDoesNotThrow {
            inputView.readBonusNumber(input)
        }
    }
}