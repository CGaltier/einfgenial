package com.cgaltier;

import com.cgaltier.CTileSlot.eColour;

public class CTile 
{
	public CTileSlotPos m_cPosition1;
	public CTileSlotPos m_cPosition2;
	public eColour m_eColour1 = eColour.eError;
	public eColour m_eColour2 = eColour.eError;
	
	public CTile ()
	{
		m_cPosition1 = new CTileSlotPos ();
		m_cPosition2 = new CTileSlotPos ();
	}
	
	public void setPos (CTileSlotPos pos)
	{
		//TODO
	}
	
	public void rotateCW ()
	{
		//TODO
	}
	public void rotateCCW ()
	{
		//TODO
	}

	public void set(eColour ecolourA, eColour ecolourB) 
	{
		m_eColour1 = ecolourA;
		m_eColour2 = ecolourB;		
	}
	
}
