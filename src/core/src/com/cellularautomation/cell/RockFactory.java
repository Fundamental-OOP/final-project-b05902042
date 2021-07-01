package com.cellularautomation.cell;

import com.badlogic.gdx.graphics.Color;
import com.cellularautomation.cell.Behaviour.CellMove_Static;

public class RockFactory extends CellFactory
{
    @Override
    public String GetName()
    {
        return "Rock";
    }

    @Override
    public Cell CreateCell()
    {
        Cell c = new Cell();
        c.name = GetName();
        c.density = 9999999;
        c.color = CellHelper.RandomizeColor(Color.GRAY, 0.1f,false);
        c.moveBehaviour = new CellMove_Static();
        c.moveBehaviour.InjectCell(c);
        return c;
    }
}
