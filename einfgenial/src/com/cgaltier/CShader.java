package com.cgaltier;

import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class CShader 
{
	String mFragmentProgram ;
	String mVertexProgram ;
	public ShaderProgram mProgram; 
	CShader (String name, String ext)
	{
    	InputStream inFS = Gdx.files.internal("data/shaders/"+name+"_fs."+ext).read();
    	InputStream inVS = Gdx.files.internal("data/shaders/"+name+"_vs."+ext).read();
    	mFragmentProgram = Helper.convertStreamToString(inFS) ;
    	mVertexProgram = Helper.convertStreamToString(inVS) ;
	}
	
	public ShaderProgram createProgram ()
	{
		mProgram = new ShaderProgram(mVertexProgram, mFragmentProgram);
		if (mProgram.isCompiled() == false) 
		{
            Gdx.app.log("einfgenial", mProgram.getLog());
            System.exit(0);                    
		}		
		return mProgram;
	}
	
	public void dispose()
	{
		mProgram.dispose();
	}
}
