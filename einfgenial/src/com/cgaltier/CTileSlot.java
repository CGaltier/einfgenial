package com.cgaltier;

public class CTileSlot 
{
	//0 : free
	//1-6 : colour
	public enum eColour
	{
		eError,
		eForbidden,
		eFree,
		eColour1,
		eColour2,
		eColour3,
		eColour4,
		eColour5,
		eColour6
	};
	private eColour m_eValue ;
	
	public CTileSlot ()
	{
		m_eValue = eColour.eFree;
	}
	public CTileSlot (eColour eValue)
	{
		m_eValue = eValue ;
	}
	public void setValue (eColour eValue)
	{
		m_eValue = eValue ;
	}
	public eColour value ()
	{
		return m_eValue ;
	}
	public boolean isFree ()
	{
		return (m_eValue == eColour.eFree);
	}
	public boolean isForbidden ()
	{
		return (m_eValue == eColour.eForbidden);
	}
}
