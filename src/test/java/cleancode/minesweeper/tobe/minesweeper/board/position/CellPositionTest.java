package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CellPositionTest {

    @DisplayName("사용자 입력 좌표를 기반으로 셀의 위치 정보(CellPosition)을 생성한다.")
    @Test
    void of() {
        // given
        int rowIndex = 0;
        int colIndex = 0;

        // when
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);

        // then
        assertThat(cellPosition)
                .extracting("rowIndex", "colIndex")
                .containsExactlyInAnyOrder(0, 0);
    }

    @DisplayName("사용자가 입력한 좌표가 0 미만일 경우, 예외가 발생한다.")
    @Test
    void ofWithNegative() {
        // given
        int rowIndex = -1;
        int colIndex = -1;

        // when // then
        assertThatThrownBy(() -> CellPosition.of(rowIndex, colIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }
}
