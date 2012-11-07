package com.cgaltier;

public class CTileSlotPos 
{
	private int m_iX ;
	private int m_iY ;
	public CTileSlotPos ()
	{
		m_iX = -99 ;
		m_iY = -99 ;
	}
	public CTileSlotPos (int iX, int iY)
	{
		m_iX = iX ;
		m_iY = iY ;
	}
	public void set (int iX, int iY)
	{
		m_iX = iX ;
		m_iY = iY ;
	}
	public void set (CTileSlotPos srce)
	{
		m_iX = srce.m_iX ;
		m_iY = srce.m_iY ;
	}
	public void add (int dX, int dY)
	{
		m_iX = m_iX+dX ;
		m_iY = m_iY+dY ;
	}		
	public void add (CTileSlotPos srce)
	{
		m_iX = m_iX+srce.m_iX ;
		m_iY = m_iY+srce.m_iY ;
	}	
	public int x ()
	{
		return m_iX;
	}
	public int y ()
	{
		return m_iY ;
	}
}
