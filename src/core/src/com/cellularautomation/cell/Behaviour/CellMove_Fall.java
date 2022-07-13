package com.cellularautomation.cell.Behaviour;

import com.badlogic.gdx.math.GridPoint2;
import com.cellularautomation.cell.Cell;
import java.lang.Math;

public class CellMove_Fall extends CellMoveBehaviour
{
    public CellMove_Fall(int speed)
    {
        fallSpeed = speed;
    }

    private int fallSpeed;

    @Override
    public void PerformCellAction()
    {
        GridPoint2 previousPosion = cell.position;

        for(int i = 0; i < fallSpeed; i ++)
        {
            GridPoint2 down = new GridPoint2(cell.position.x, cell.position.y - 1);
            GridPoint2 left = new GridPoint2(cell.position.x - 1, cell.position.y - 1);
            GridPoint2 right = new GridPoint2(cell.position.x + 1, cell.position.y - 1);

            if(!Matrix().isValidPosition(down))
                break;

            if(Matrix().getCell(down) == null)
            {
                cell.position = down;
                continue;
            }
            if((float)Math.random() > 0.5f)
            {
                if(!CellMoveHelper.CheckPosition(cell,left,true))
                    if(!CellMoveHelper.CheckPosition(cell,right,true))
                        if(Matrix().getCell(down).moveBehaviour instanceof CellMove_Ascend || Matrix().getCell(down).moveBehaviour instanceof CellMove_Flow)
                            Matrix().SwapCell(cell, Matrix().getCell(down));
            }
            else
            {
                if(!CellMoveHelper.CheckPosition(cell,right,true))
                    if(!CellMoveHelper.CheckPosition(cell,left,true))
                        if(Matrix().getCell(down).moveBehaviour instanceof CellMove_Ascend || Matrix().getCell(down).moveBehaviour instanceof CellMove_Flow)
                            Matrix().SwapCell(cell, Matrix().getCell(down));
            }
        }

        if(cell.position != previousPosion)
            updated = true;
    }
}
