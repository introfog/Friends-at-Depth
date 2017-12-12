package com.game.messages;

import com.game.addition.math.Rectangle;
import com.game.mesh.objects.GameObject;

public class ReturnStartPositionMessage extends GameMessage{ //создается в ответ на сообщение о получение позиций
	public Rectangle body;
	public Rectangle sprite;
	
	
	public ReturnStartPositionMessage (GameObject object, float spriteX, float spriteY, float spriteW, float spriteH,
									   float bodyX, float bodyY, float bodyW, float bodyH){
		this.type = MessageType.returnStartPosition;
		this.object = object;
		this.objectType = object.objectType;
		
		body = new Rectangle (bodyX, bodyY, bodyW, bodyH);
		sprite = new Rectangle (spriteX, spriteY, spriteW, spriteH);
	}
}
