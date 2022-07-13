package com.cellularautomation.cell;

import com.badlogic.gdx.graphics.Color;
import com.cellularautomation.cell.Behaviour.*;

public class WoodFactory extends CellFactory
{
    @Override
    public String GetName()
    {
        return "Wood";
    }

    @Override
    public Cell CreateCell()
    {
        Cell c = new Cell();
        c.name = GetName();
        c.density = 0.9f;
        c.color = CellHelper.RandomizeColor(Color.BROWN, 0.1f,false);
        c.moveBehaviour = new CellMove_Static();
        c.moveBehaviour.InjectCell(c);

        CellPropertyBehaviour b = new CellProperty_Flammable(0.1f,0.01f);
        b.InjectCell(c);
        c.propertyBehaviours.add(b);
        return c;
    }
}
