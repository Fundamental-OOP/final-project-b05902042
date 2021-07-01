package com.cellularautomation.cell.Behaviour;

import com.badlogic.gdx.math.GridPoint2;
import com.cellularautomation.cell.Cell;

import java.lang.Math;

public class CellMove_Flow extends CellMoveBehaviour
{
    public CellMove_Flow(int speed)
    {
        flowSpeed = speed;
    }

    private int flowSpeed;

    @Override
    public void PerformCellAction()
    {
        GridPoint2 previousPosion = cell.position;


        for(int i = 0; i < flowSpeed; i ++)
        {
            GridPoint2 down = new GridPoint2(cell.position.x, cell.position.y - 1);
            GridPoint2 leftDown = new GridPoint2(cell.position.x - 1, cell.position.y - 1);
            GridPoint2 rightDown = new GridPoint2(cell.position.x + 1, cell.position.y - 1);
            GridPoint2 left= new GridPoint2(cell.position.x - 1, cell.position.y);
            GridPoint2 right = new GridPoint2(cell.position.x + 1, cell.position.y);

            GridPoint2 up = new GridPoint2(cell.position.x, cell.position.y + 1);

            float downDen = CellMoveHelper.CheckDensity(down);
            float leftDownDen = CellMoveHelper.CheckDensity(leftDown);
            float rightDownDen = CellMoveHelper.CheckDensity(rightDown);
            float leftDen = CellMoveHelper.CheckDensity(left);
            float rightDen = CellMoveHelper.CheckDensity(right);

            boolean leftRight = ((float)Math.random() > 0.5f);
            boolean goUp = ((float)Math.random() < 0.25f);

            if(downDen < cell.density)
                Move(down);
            else if(leftDownDen < cell.density || rightDownDen < cell.density)
            {
                if(leftDownDen < rightDownDen)
                    Move(leftDown);
                else if(rightDownDen < leftDownDen)
                    Move(rightDown);
                else if(leftRight)
                    Move(leftDown);
                else
                    Move(rightDown);
            }
            else if(leftDen < cell.density || rightDen < cell.density)
            {
                if(leftDen < rightDen)
                    Move(left);
                else if(rightDen < leftDen)
                    Move(right);
                else if(leftRight)
                    Move(left);
                else
                    Move(right);
            }

            if(goUp && Matrix().isValidPosition(up))
            {
                Cell c = Matrix().getCell(up);
                if(c == null || c.moveBehaviour instanceof CellMove_Static || c.density <= cell.density)
                    continue;
                Move(up);
            }

            /*boolean leftRight = ((float)Math.random() > 0.5f);

            if(!Matrix().isValidPosition(down))
            {
                if(leftRight)
                {
                    if(!CellMoveHelper.CheckEmpty(cell,left))
                        CellMoveHelper.CheckPosition(cell,right,true);
                }
                else
                {
                    if(!CellMoveHelper.CheckEmpty(cell,right))
                        CellMoveHelper.CheckPosition(cell,left,true);
                }
                continue;
            }
            if(Matrix().getCell(down) == null)
            {
                cell.position = down;
                continue;
            }
            if(leftRight)
            {
                if(!CellMoveHelper.CheckPosition(cell,leftDown,true))
                    if(!CellMoveHelper.CheckPosition(cell,rightDown,true))
                        if(!CellMoveHelper.CheckEmpty(cell,left))
                            CellMoveHelper.CheckPosition(cell,right,true);
            }
            else
            {
                if(!CellMoveHelper.CheckPosition(cell,rightDown,true))
                    if(!CellMoveHelper.CheckPosition(cell,leftDown,true))
                        if(!CellMoveHelper.CheckEmpty(cell,right))
                            CellMoveHelper.CheckPosition(cell,left,true);
            }*/
        }

        if(cell.position != previousPosion)
            updated = true;
    }

    private void Move(GridPoint2 pos)
    {
        Cell c = Matrix().getCell(pos);
        if(c == null)
            cell.position = pos;
        else
            Matrix().SwapCell(cell,c);
    }
}
