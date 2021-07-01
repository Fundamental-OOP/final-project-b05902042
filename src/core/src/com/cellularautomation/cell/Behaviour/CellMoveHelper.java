package com.cellularautomation.cell.Behaviour;

import com.badlogic.gdx.math.GridPoint2;
import com.cellularautomation.cell.Cell;
import com.cellularautomation.matrix.CellularMatrix;

import java.lang.Math;

public class CellMoveHelper
{
    private static CellularMatrix Matrix() { return CellularMatrix.GetInstance(); }

    public static boolean CheckPosition(Cell cell, GridPoint2 pos, boolean checkGreaterDensity)
    {
        boolean value;

        if(!Matrix().isValidPosition(pos))
            value = false;
        else
        {
            Cell c = Matrix().getCell(pos);
            if(c == null)
                value = true;
            else if(c.moveBehaviour instanceof CellMove_Static)
                return false;
            else if(cell.density != c.density && (cell.density > c.density) == checkGreaterDensity && !c.moveBehaviour.updated)
            {
                Matrix().SwapCell(cell, c);
                return true;
            }
            else
                value = false;
        }

        if(value)
            cell.position = pos;
        return value;
    }

    public static float CheckDensity(GridPoint2 pos)
    {
        if(!Matrix().isValidPosition(pos))
            return 99999999;
        Cell c = Matrix().getCell(pos);
        if(c == null)
            return 0;
        if(c.moveBehaviour instanceof CellMove_Static)
            return 99999999;
        if(c.moveBehaviour.updated)
            return 99999999;
        return c.density;
    }

    public static boolean CheckEmpty(Cell cell, GridPoint2 pos)
    {
        boolean value;

        if(!Matrix().isValidPosition(pos))
            value = false;
        else
        {
            Cell c = Matrix().getCell(pos);
            if(c == null)
                value = true;
            else
                value = false;
        }

        if(value)
            cell.position = pos;
        return value;
    }
}
