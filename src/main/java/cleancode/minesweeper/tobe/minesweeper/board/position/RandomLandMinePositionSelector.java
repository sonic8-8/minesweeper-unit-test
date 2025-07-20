package cleancode.minesweeper.tobe.minesweeper.board.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLandMinePositionSelector implements LandMinePositionSelector {
    @Override
    public List<CellPosition> extractLandMinePositions(CellPositions positions, int landMineCount) {
        List<CellPosition> cellPositions = positions.getPositions();

        Collections.shuffle(cellPositions);
        return cellPositions.subList(0, landMineCount);
    }
}
