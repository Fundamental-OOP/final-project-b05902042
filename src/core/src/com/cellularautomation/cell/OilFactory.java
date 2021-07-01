package com.cellularautomation.cell;

import com.badlogic.gdx.graphics.Color;
import com.cellularautomation.cell.Behaviour.CellMove_Flow;
import com.cellularautomation.cell.Behaviour.CellPropertyBehaviour;
import com.cellularautomation.cell.Behaviour.CellProperty_Flammable;

public class OilFactory extends CellFactory {

    @Override
    public String GetName()
    {
        return "Oil";
    }

    @Override
    public Cell CreateCell()
    {
        Cell c = new Cell();
        c.name = GetName();
        c.density = 0.9f;
        c.color = Color.MAROON;
        c.moveBehaviour = new CellMove_Flow(1);
        c.moveBehaviour.InjectCell(c);

        CellPropertyBehaviour b = new CellProperty_Flammable(1,0.15f);
        b.InjectCell(c);
        c.propertyBehaviours.add(b);
        return c;
    }
}
