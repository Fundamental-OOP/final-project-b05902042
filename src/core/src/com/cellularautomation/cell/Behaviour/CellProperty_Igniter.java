package com.cellularautomation.cell.Behaviour;

import com.badlogic.gdx.math.GridPoint2;
import com.cellularautomation.cell.Cell;

public class CellProperty_Igniter extends CellPropertyBehaviour
{
    @Override
    public void PerformCellAction()
    {
        GridPoint2 up = new GridPoint2(cell.position.x, cell.position.y + 1);
        GridPoint2 down = new GridPoint2(cell.position.x, cell.position.y - 1);
        GridPoint2 left = new GridPoint2(cell.position.x - 1, cell.position.y);
        GridPoint2 right = new GridPoint2(cell.position.x + 1, cell.position.y);

        boolean canSurvive = false;
        canSurvive |= CheckPosition(up);
        canSurvive |= CheckPosition(down);
        canSurvive |= CheckPosition(left);
        canSurvive |= CheckPosition(right);

        if(!canSurvive)
            Matrix().RemoveCell(cell);
    }

    private boolean CheckPosition(GridPoint2 position)
    {
        if(!Matrix().isValidPosition(position))
            return true;
        Cell c = Matrix().getCell(position);
        if(c == null)
            return true;

        boolean result = false;

        for (CellPropertyBehaviour b : c.propertyBehaviours)
        {
            if(b instanceof CellProperty_Flammable)
            {
                result = true;
                if(!((CellProperty_Flammable) b).isHeated())
                    ((CellProperty_Flammable) b).Heated(cell.name);
                break;
            }
        }

        for (CellPropertyBehaviour b : c.propertyBehaviours)
        {
            if(b instanceof CellProperty_Extinguisher)
            {
                ((CellProperty_Extinguisher) b).OnExtinguish();
                Matrix().RemoveCell(cell);
                result = true;
                break;
            }
        }

        return result;
    }
}
