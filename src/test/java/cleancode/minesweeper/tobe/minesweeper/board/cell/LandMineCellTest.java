package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LandMineCellTest {

    @DisplayName("사용자가 랜드마인 셀을 열면, LANDMINE 상태의 스냅샷을 반환한다.")
    @Test
    void getSnapshotWithOpenedLandMineCell() {
        // given
        LandMineCell landMineCell = new LandMineCell();
        landMineCell.open();

        // when
        CellSnapshot snapshot = landMineCell.getSnapshot();

        // then
        assertThat(snapshot)
                .extracting("status", "nearbyLandMineCount")
                .containsExactlyInAnyOrder(CellSnapshotStatus.LAND_MINE, 0);
    }

    @DisplayName("사용자가 랜드마인 셀에 깃발을 꽂으면, FLAG 상태의 스냅샷을 반환한다.")
    @Test
    void getSnapshotWithFlaggedLandMineCell() {
        // given
        LandMineCell landMineCell = new LandMineCell();
        landMineCell.flag();

        // when
        CellSnapshot snapshot = landMineCell.getSnapshot();

        // then
        assertThat(snapshot)
                .extracting("status", "nearbyLandMineCount")
                .containsExactlyInAnyOrder(CellSnapshotStatus.FLAG, 0);
    }

    @DisplayName("셀을 열지 않은 경우, 스냅샷의 상태는 UNCHECKED이다.")
    @Test
    void getSnapshotWithUncheckedLandMineCell() {
        // given
        LandMineCell landMineCell = new LandMineCell();

        // when
        CellSnapshot snapshot = landMineCell.getSnapshot();

        // then
        assertThat(snapshot)
                .extracting("status", "nearbyLandMineCount")
                .containsExactlyInAnyOrder(CellSnapshotStatus.UNCHECKED, 0);
    }

}
