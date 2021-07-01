package com.cellularautomation.cell;

import com.badlogic.gdx.graphics.Color;
import java.lang.Math;

public class CellHelper
{
    public static Color RandomizeColor(Color inputColor, float randomRange, boolean affectAlpha)
    {
        float r = inputColor.r * (1 + GiveRandomFloatInRange(randomRange));
        float g = inputColor.g * (1 + GiveRandomFloatInRange(randomRange));
        float b = inputColor.b * (1 + GiveRandomFloatInRange(randomRange));
        float a = (affectAlpha)? inputColor.a * (1 + GiveRandomFloatInRange(randomRange)) : inputColor.a;
        r = Math.min(r,1);
        g = Math.min(g,1);
        b = Math.min(b,1);
        a = Math.min(a,1);
        return new Color(r,g,b,a);
    }

    private static float GiveRandomFloatInRange(float range)
    {
        float rand = (float)Math.random();
        return (rand - 0.5f) * 2 * range;
    }
}
