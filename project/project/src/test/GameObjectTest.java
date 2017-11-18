package test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import game.GameObject;

public class GameObjectTest {
	private class Temp extends GameObject{
		public Temp(String name) {
			super(name);
		}
	}
	
	private boolean isAbstract(){
		boolean classAbstract = false;
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/game/GameObject.java"));
			String line = in.readLine();
			while (line != null) {
				if (line.contains("public abstract class GameObject")){
					classAbstract = true;
				}
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			classAbstract = false;
		} catch (IOException e) {
			classAbstract =  false;
		}
		return classAbstract;
	}
	//Constructor Test
	@Test
	public void constructorTest() {
		assertTrue("Character should be declared abstract and contain abstract attack method", isAbstract());
		GameObject gameOBJ = new Temp("Default");
		assertEquals("GameObject should be named 'Default'","Default",gameOBJ.getName());
	}
	//Set Name Test
	@Test
	public void SetNameTest() {
		assertTrue("Character should be declared abstract and contain abstract attack method", isAbstract());
		GameObject gameOBJ = new Temp("Default");
		gameOBJ.setName("New");
		assertEquals("GameObject should be named 'New'","New",gameOBJ.getName());
	}
	
}