package lotto.domain.data

data class WinningLotto(val lotto: Lotto, val bonusNumber: BonusNumber) {
    init {
        require(lotto.notHaveBonusNumber(bonusNumber)) { "당첨 번호와 보너스 번호는 같으면 안 됩니다." }
    }

}