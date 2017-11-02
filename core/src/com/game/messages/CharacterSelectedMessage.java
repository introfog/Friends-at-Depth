package com.game.messages;

import com.game.mesh.objects.GameObject;
import com.game.mesh.objects.ObjectType;

public class CharacterSelectedMessage extends GameMessage{
	public float spriteX;
	public float spriteY;
	
	
	public CharacterSelectedMessage (GameObject object, float spriteX, float spriteY){
		this.type = MessageType.characterSelected;
		this.object = object;
		this.objectType = ObjectType.character;
		
		this.spriteX = spriteX;
		this.spriteY = spriteY;
	}
}
