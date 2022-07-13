package com.cellularautomation.cell.Behaviour;

import com.badlogic.gdx.math.GridPoint2;
import com.cellularautomation.cell.Cell;
import java.lang.Math;

public class CellMove_Ascend extends CellMoveBehaviour
{
    public CellMove_Ascend(int speed) { ascendSpeed = speed; }

    private int ascendSpeed;

    @Override
    public void PerformCellAction()
    {
        GridPoint2 previousPosition = cell.position;

        for(int i = 0; i < ascendSpeed; i ++)
        {
            GridPoint2 up = new GridPoint2(cell.position.x, cell.position.y + 1);
            GridPoint2 leftUp = new GridPoint2(cell.position.x - 1, cell.position.y + 1);
            GridPoint2 rightUp = new GridPoint2(cell.position.x + 1, cell.position.y + 1);
            GridPoint2 left= new GridPoint2(cell.position.x - 1, cell.position.y);
            GridPoint2 right = new GridPoint2(cell.position.x + 1, cell.position.y);

            if(!Matrix().isValidPosition(up))
            {
                break;
            }

            float rand = (float)Math.random();

            if(rand < 0.5f)
            {
                if(Matrix().getCell(up) == null)
                {
                    cell.position = up;
                    continue;
                }
                if(rand < 0.25f)
                {
                    if(!CellMoveHelper.CheckPosition(cell,leftUp,false))
                        if(!CellMoveHelper.CheckPosition(cell,rightUp,false))
                            if(!CellMoveHelper.CheckEmpty(cell,left))
                                CellMoveHelper.CheckPosition(cell,right,false);
                }
                else
                {
                    if(!CellMoveHelper.CheckPosition(cell,rightUp,false))
                        if(!CellMoveHelper.CheckPosition(cell,leftUp,false))
                            if(!CellMoveHelper.CheckEmpty(cell,right))
                                CellMoveHelper.CheckPosition(cell,left,false);
                }
            }
            else
            {
                if(rand > 0.75f)
                {
                    if(!CellMoveHelper.CheckPosition(cell,leftUp,false))
                        if(!CellMoveHelper.CheckPosition(cell,rightUp,false))
                            if(!CellMoveHelper.CheckEmpty(cell,left))
                                CellMoveHelper.CheckPosition(cell,right,false);
                    if(cell.position != previousPosition)
                        continue;
                }
                else
                {
                    if(!CellMoveHelper.CheckPosition(cell,rightUp,false))
                        if(!CellMoveHelper.CheckPosition(cell,leftUp,false))
                            if(!CellMoveHelper.CheckEmpty(cell,right))
                                CellMoveHelper.CheckPosition(cell,left,false);
                    if(cell.position != previousPosition)
                        continue;
                }
                if(Matrix().getCell(up) == null)
                {
                    cell.position = up;
                    continue;
                }
            }
        }

        if(cell.position != previousPosition)
            updated = true;
    }
}
