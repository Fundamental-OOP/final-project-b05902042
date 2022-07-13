package com.cellularautomation.matrix;
import com.badlogic.gdx.graphics.Color;

public class testMatrix implements Matrix{
	int times=0;
	int r=0;
	public Color getColor(int i,int j){
		
		int rvalue=r;
		if(rvalue>255){
			rvalue=511-rvalue;
		}
		return new Color(rvalue*0x01000000+((i+50)%256)*0x00010000+((j+50)%256)*0x00000100+0x000000ff);
		/*if(i==times&&j==times){
			return new Color(0xffffffff);
		}
		return new Color(0x00000000);*/
	}
	public int getWidth(){
		return 128;
	}
	public int getHeight(){
		return 128;
	}
	public void update(){
		/*times-=1;
		if(times<0)times=127;*/
		r+=1;
		if(r>=512)r=0;
	}
}