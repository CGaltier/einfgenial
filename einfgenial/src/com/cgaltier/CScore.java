package com.cgaltier;

import com.cgaltier.CTileSlot.eColour;

public class CScore 
{
	private int m_iScoreColour1;
	private int m_iScoreColour2;
	private int m_iScoreColour3;
	private int m_iScoreColour4;
	private int m_iScoreColour5;
	private int m_iScoreColour6;
	public static int s_iScoreMax = 18 ;
	
	public CScore ()
	{
		reset();
	}
	public CScore copy (CScore srce)
	{
		m_iScoreColour1 = srce.m_iScoreColour1;
		m_iScoreColour2 = srce.m_iScoreColour2;
		m_iScoreColour3 = srce.m_iScoreColour3;
		m_iScoreColour4 = srce.m_iScoreColour4;
		m_iScoreColour5 = srce.m_iScoreColour5;
		m_iScoreColour6 = srce.m_iScoreColour6;
		return this;
	}
	public boolean addScore (eColour colour1, int iScoreColour1,eColour colour2, int iScoreColour2)
	{
		int iReplay = 0 ;
		iReplay  = addScoreColour (colour1, iScoreColour1);
		iReplay += addScoreColour (colour2, iScoreColour2);
		return (iReplay >= 1 );
	}
	private int addScoreColour(eColour colour1, int iScoreColour) 
	{
		int iPrevious = 0;
		int iNew = 0 ;
		switch (colour1)
		{
			case eColour1:
				iPrevious = m_iScoreColour1 ;
				m_iScoreColour1 += iScoreColour;
				if ( m_iScoreColour1 > s_iScoreMax )
					m_iScoreColour1 = s_iScoreMax ; 
				iNew = m_iScoreColour1 ;
				break;
			case eColour2:
				iPrevious = m_iScoreColour2 ;
				m_iScoreColour2 += iScoreColour;
				if ( m_iScoreColour2 > s_iScoreMax )
					m_iScoreColour2 = s_iScoreMax ; 
				iNew = m_iScoreColour2 ;				
				break;
			case eColour3:
				iPrevious = m_iScoreColour3 ;
				m_iScoreColour3 += iScoreColour;
				if ( m_iScoreColour3 > s_iScoreMax )
					m_iScoreColour3 = s_iScoreMax ; 
				iNew = m_iScoreColour3 ;
				break;
			case eColour4:
				iPrevious = m_iScoreColour4 ;
				m_iScoreColour4 += iScoreColour;
				if ( m_iScoreColour4 > s_iScoreMax )
					m_iScoreColour4 = s_iScoreMax ; 
				iNew = m_iScoreColour4 ;				
				break;
			case eColour5:
				iPrevious = m_iScoreColour5 ;
				m_iScoreColour5 += iScoreColour;
				if ( m_iScoreColour5 > s_iScoreMax )
					m_iScoreColour5 = s_iScoreMax ; 
				iNew = m_iScoreColour5 ;
				break;
			case eColour6:
				iPrevious = m_iScoreColour6 ;
				m_iScoreColour6 += iScoreColour;
				if ( m_iScoreColour6 > s_iScoreMax )
					m_iScoreColour6 = s_iScoreMax ; 
				iNew = m_iScoreColour6 ;
				break;
		}
		if (iPrevious < s_iScoreMax && iNew >= s_iScoreMax)
			return 1;//1 replay
		return 0;
	}
	public boolean allScoreMax() 
	{
		if (m_iScoreColour6 + m_iScoreColour5 + m_iScoreColour4 + m_iScoreColour3 + m_iScoreColour2 + m_iScoreColour1 >= (6*s_iScoreMax) )
			return true ;
		return false;
	}
	public void reset() 
	{
		m_iScoreColour1 = 0;
		m_iScoreColour2 = 0;
		m_iScoreColour3 = 0;
		m_iScoreColour4 = 0;
		m_iScoreColour5 = 0;
		m_iScoreColour6 = 0;		
	}
	
}
