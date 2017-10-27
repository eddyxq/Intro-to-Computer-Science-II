package game;

import java.util.Random;
/*
 * This class contains the player logic
 */
public class Player extends Character
{
	private int maxHealth;
	private int numSmallPotions;
	private int numMediumPotions;
	private int numLargePotions;
	private int currentLevel;
	private int currentEXP;
	private int expToLvl;
	private String currentWeapon;
	private boolean hasDagger = false;
	private boolean hasSword = false;
	private boolean hasExcalibur = false;
	private int damage;
	/**
	 * This constructor initializes the player weapon, inventory, and statistics
	 */
	public Player(String name, int health, int attack)
	{
		super(name,health,attack);
		maxHealth = 20;
		currentLevel = 1;
		currentEXP = 0;
		expToLvl = 1;
	}
	/**
	 * This is the getter method for level, returns currentLvl
	 */
	public int getCurrentLevel()
	{
		return new Integer(currentLevel);
	}
	/**
	 * This is the setter method for player level
	 * @param aLvl The level value
	 */
	public void setLvl(int aLvl)
	{
		currentLevel = aLvl;
	}
	/**
	 * This method increases the player's level when sufficient experience is gained
	 */
	public void checkExp()
	{
		if(currentEXP >= expToLvl) 
		{
			levelUp();
		}
	}
	/**
	 * This method increases the level of the player
	 */
	private void levelUp()
	{
		currentLevel += 1;
		if(currentEXP > expToLvl) 
		{
			currentEXP -= expToLvl;
		}
		expToLvl *= 2;
		setAttack(getAttack() + 1);
	}
	/**
	 * This is the getter method for experience required to level up, returns expToLvl
	 */
	public int getExpToLvl()
	{
		return new Integer(expToLvl);
	}
	/**
	 * This is the setter method for required experience to level up
	 * @param expVal The experience value
	 */
	public void setExpToLvl(int expVal)
	{
		expToLvl = expVal;
	}
	/**
	 * This is the getter method for current experience, returns currentEXP
	 */
	public int getCurrentExp()
	{
		return new Integer(currentEXP);
	}
	/**
	 * This is the setter method for current player experience
	 * @param expVal The experience value
	 */
	public void setCurrentExp(int expVal)
	{
		currentEXP = expVal;
	}
	/**
	 * This is called to increase the player's exp after defeating an enemy
	 * @param player The player
	 * @param enemy The enemy
	 */
	public void obtainExp(Enemy enemy)
	{
		currentEXP += enemy.getExp();
	}
	/**
	 * This method allows the player to consume a item to restore their health points
	 * @return itemUsed The item consumed
	 */
	public void useItem(int itemID)
	{
		switch (itemID)
		{
		case 1:
			if (getNumSmallPotions() > 0)
			{
				restoreHp(3);
				numSmallPotions -= 1;
			}
				break;
		case 2:
			if (getNumMediumPotions() > 0)
			{
				restoreHp(5);
				numMediumPotions -= 1;
			}
				break;
		case 3:
			if (getNumLargePotions() > 0)
			{
				restoreHp(7);
				numLargePotions -= 1;
			}
				break;
		}
	}
	/**
	 * This method checks to see what item the player has obtained
	 * @param item The item the player obtained
	 */
	public void pickUp(int itemID)
	{
		switch (itemID)
		{
		case 1:
			numSmallPotions += 1;
			break;
		case 2:
			numMediumPotions += 1;
			break;
		case 3:
			numLargePotions += 1;
			break;
		case 4:
			hasDagger = true;
			break;
		case 5:
			hasSword = true;
			break;
		case 6:
			hasExcalibur = true;
			break;
		}
	}
	/**
	 * This method increases the player's health
	 * @param amount The amount to be restored
	 */
	private void restoreHp(int amount)
    {
		setHealth(getHealth()+amount);
		if(getHealth() > maxHealth) 
		{
			int excessHp = getHealth() - maxHealth;
			setHealth(getHealth()-excessHp);
		}
    }
	/*
	 * This method checks to see if the player is alive
	 * Returns true if player is alive, false otherwise
	 */
	public boolean isAlive()
	{
		boolean isAlive = true;
		if (getHealth() < 1)
		{
			isAlive = false;
		}
		return isAlive;
	}
	/**
	 * This is the getter method for small potions, returns numSmallPotions
	 */
	public int getNumSmallPotions()
	{
		return new Integer(numSmallPotions);
	}
	/**
	 * This is the getter method for medium potions, returns numMediumPotions
	 */
	public int getNumMediumPotions()
	{
		return new Integer(numMediumPotions);
	}
	/**
	 * This is the getter method for large potions, returns numLargePotions
	 */
	public int getNumLargePotions()
	{
		return new Integer(numLargePotions);
	}
	/**
	 * This is the getter method for current weapon, returns currentWeapon
	 */
	public String getCurrentWeapon()
	{
		return currentWeapon;
	}
	/**
	 * This is the getter method for weapon damage, returns weaponDamage
	 */
	public int getWeaponDamage()
	{
		int weaponDamage = 0;
		if (hasExcalibur)
		{
			weaponDamage = 3;
			currentWeapon = "Excalibur";
		}
		else if (hasSword)
		{
			weaponDamage = 2;
			currentWeapon = "Iron Longsword";
		}
		else if (hasDagger)
		{
			weaponDamage = 1;
			currentWeapon = "Rusty Dagger";
		}
		else
		{
			currentWeapon = "No Weapon";
		}
		return new Integer(weaponDamage);
	}
	/**
	 * This method reduces the enemy's health by the amount of damage inflicted by the player
	 *@param player The player
	 *@param enemy The enemy
	 */
	@Override 
	public void attack(Player player, Enemy enemy) 
	{	
		Random rng = new Random();		
		int totalDamage = (player.getAttack() + player.getWeaponDamage() + rng.nextInt(4) - 2);
		enemy.setHealth(enemy.getHealth()-totalDamage);
		damage = totalDamage;
	}
	/**
	 * This is the getter method for damage, returns damage
	 */
	public int getDamage()
	{
		return damage;
	}
}