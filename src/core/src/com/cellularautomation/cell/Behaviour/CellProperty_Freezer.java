package com.cellularautomation.cell.Behaviour;

import com.badlogic.gdx.math.GridPoint2;
import com.cellularautomation.cell.Cell;

public class CellProperty_Freezer extends CellPropertyBehaviour
{
    public CellProperty_Freezer(float freeze)
    {
        freezeProbability = freeze;
    }

    private float freezeProbability;

    @Override
    public void PerformCellAction()
    {
        GridPoint2 up = new GridPoint2(cell.position.x, cell.position.y + 1);
        GridPoint2 down = new GridPoint2(cell.position.x, cell.position.y - 1);
        GridPoint2 left = new GridPoint2(cell.position.x - 1, cell.position.y);
        GridPoint2 right = new GridPoint2(cell.position.x + 1, cell.position.y);

        CheckPosition(up);
        CheckPosition(down);
        CheckPosition(left);
        CheckPosition(right);
    }

    private void CheckPosition(GridPoint2 position)
    {
        if(!Matrix().isValidPosition(position))
            return;
        Cell c = Matrix().getCell(position);
        if(c == null)
            return;

        for (CellPropertyBehaviour b : c.propertyBehaviours)
        {
            if(b instanceof CellProperty_Freezable)
            {
                if((float)Math.random() <= freezeProbability)
                    ((CellProperty_Freezable) b).OnFreeze();
                break;
            }
        }
    }
}
