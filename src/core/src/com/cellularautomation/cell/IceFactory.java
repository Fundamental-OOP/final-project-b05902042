package com.cellularautomation.cell;

import com.badlogic.gdx.graphics.Color;
import com.cellularautomation.cell.Behaviour.*;

public class IceFactory extends CellFactory
{
    @Override
    public String GetName()
    {
        return "Ice";
    }

    @Override
    public Cell CreateCell()
    {
        Cell c = new Cell();
        c.name = GetName();
        c.density = 1;
        c.color = CellHelper.RandomizeColor(Color.CYAN, 0.1f, false);
        c.moveBehaviour = new CellMove_Static();
        c.moveBehaviour.InjectCell(c);

        CellPropertyBehaviour b = new CellProperty_Extinguisher("Water");
        b.InjectCell(c);
        c.propertyBehaviours.add(b);

        b = new CellProperty_Freezer(0.025f);
        b.InjectCell(c);
        c.propertyBehaviours.add(b);

        b = new CellProperty_Volatile(100,0.025f, "Water");
        b.InjectCell(c);
        c.propertyBehaviours.add(b);

        return c;
    }
}
