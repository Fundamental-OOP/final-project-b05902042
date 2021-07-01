package com.cellularautomation.cell;

import java.util.HashMap;
import java.util.Set;

public abstract class CellFactory
{
    public abstract String GetName();
    public abstract Cell CreateCell();
    public static HashMap<String, CellFactory> cellFactoryHashMap = new HashMap<String, CellFactory>();
    public static void AddMapEntry(CellFactory factory)
    {
        String name = factory.GetName();
        if(!cellFactoryHashMap.containsKey(name))
            cellFactoryHashMap.put(name, factory);
    }
    public static boolean CheckFactoryExist(String name)
    {
        return cellFactoryHashMap.containsKey(name);
    }
    public static CellFactory FindFactory(String name)
    {
        if(cellFactoryHashMap.containsKey(name))
            return cellFactoryHashMap.get(name);
        else
            return null;
    }
	public static String[] getCellTypeList(){
		if(cellFactoryHashMap.keySet().size()>0){
			String[] array=new String[cellFactoryHashMap.keySet().size()];
			cellFactoryHashMap.keySet().toArray(array);
			return array;
		}else{
			return null;
		}
	}
}
