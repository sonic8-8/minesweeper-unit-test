package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CellPositionTest {

    @DisplayName("사용자 입력 좌표를 기반으로 셀 위치(CellPosition)을 생성한다.")
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

    @DisplayName("생성된 셀 위치(CellPosition)의 Row 인덱스가 게임 보드 행 크기(rowSize) 이상일 경우, InvalidCellPosition 이다.")
    @Test
    void isRowIndexMoreThanOrEqual() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isRowIndexMoreThanOrEqual(0);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("생성된 셀 위치(CellPosition)의 Row 인덱스가 게임 보드 열 크기(RowSize) 안에 들어올 경우, InvalidCellPosition 이 아니다.")
    @Test
    void isRowIndexMoreThanOrEqual_withinBounds() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isRowIndexMoreThanOrEqual(1);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("생성된 셀 위치(CellPosition)의 Col 인덱스가 게임 보드 열 크기(colSize) 이상일 경우, InvalidCellPosition 이다.")
    @Test
    void isColIndexMoreThanOrEqual() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isColIndexMoreThanOrEqual(0);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("생성된 셀 위치(CellPosition)의 Col 인덱스가 게임 보드 열 크기(ColSize) 안에 들어올 경우, InvalidCellPosition 이 아니다.")
    @Test
    void isColIndexMoreThanOrEqual_withinBounds() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isColIndexMoreThanOrEqual(1);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("계산된 셀 위치(CellPosition)의 Row 인덱스가 게임 보드 행 크기(RowSize) 보다 작을 경우, 유효한 위치다.")
    @Test
    void isRowIndexLessThan() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isRowIndexLessThan(1);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("계산된 셀 위치(CellPosition)의 Row 인덱스가 게임 보드 행 크기(RowSize) 이상일 경우, 유효하지 않은 위치다.")
    @Test
    void isRowIndexLessThan_outOfBounds() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isRowIndexLessThan(0);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("계산된 셀 위치(CellPosition)의 Col 인덱스가 게임 보드 열 크기(ColSize) 보다 작을 경우, 유효한 위치다.")
    @Test
    void isColIndexLessThan() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isColIndexLessThan(1);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("계산된 셀 위치(CellPosition)의 Col 인덱스가 게임 보드 열 크기(ColSize) 이상일 경우, 유효하지 않은 위치다.")
    @Test
    void isColIndexLessThan_outOfBounds() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isColIndexLessThan(0);

        // then
        assertThat(result).isFalse();
    }
}
