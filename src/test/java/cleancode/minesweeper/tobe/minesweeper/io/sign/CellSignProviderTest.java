package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CellSignProviderTest {

    @DisplayName("CellSnapshot에 따라 적절한 셀 기호를 반환한다.")
    @Test
    void findCellSignFrom() {
        // given // when
        String emptySign = CellSignProvider.findCellSignFrom(CellSnapshot.ofEmpty());
        String uncheckedSign = CellSignProvider.findCellSignFrom(CellSnapshot.ofUnchecked());
        String flagSign = CellSignProvider.findCellSignFrom(CellSnapshot.ofFlag());
        String number = CellSignProvider.findCellSignFrom(CellSnapshot.ofNumber(1));
        String landMine = CellSignProvider.findCellSignFrom(CellSnapshot.ofLandMine());

        // then
        assertThat(emptySign).isEqualTo("■");
        assertThat(uncheckedSign).isEqualTo("□");
        assertThat(flagSign).isEqualTo("⚑");
        assertThat(number).isEqualTo("1");
        assertThat(landMine).isEqualTo("☼");
    }

    @DisplayName("매칭되는 CellSnapshot이 없을 경우 예외가 발생한다.")
    @Test
    void findCellSignFrom_exception() {
        // given
        CellSnapshot cellSnapshot = CellSnapshot.of(null, 1);

        // when // then
        assertThatThrownBy(() -> CellSignProvider.findCellSignFrom(cellSnapshot))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("확인할 수 없는 셀입니다.");
    }
}
