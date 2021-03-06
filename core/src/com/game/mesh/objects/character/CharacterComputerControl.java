package com.game.mesh.objects.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.game.GameSystem;
import com.game.addition.algorithms.aStar.Tile;
import com.game.mesh.objects.*;
import com.game.mesh.objects.singletons.special.*;
import com.game.messages.ComeToMessage;

import java.util.ArrayList;

public class CharacterComputerControl extends CharacterControl{
	private int iterator;
	private Tile next;
	private Tile current;
	private CharacterControl control;
	private ArrayList<Tile> path;
	
	
	public CharacterComputerControl (CharacterControl control){
		next = new Tile ();
		current = new Tile ();
		this.control = control;
	}
	
	public void nextIteration (){
		iterator++;
		current.x = control.character.getBodyX () + control.character.getBodyW () / 2;
		current.y = control.character.getBodyY () + control.character.getBodyH () / 2;
		next.x = (path.get (iterator).x + 0.5f) * GameObject.UNIT + GameSystem.INDENT_BETWEEN_SCREEN_LEVEL;
		next.y = (path.get (iterator).y + 0.5f) * GameObject.UNIT * GameObject.ANGLE;
	}
	
	public void updatedMoveByComputer (){
		boolean moveX = Math.abs (next.x - current.x) > CHARACTER_SPEED * 0.03;
		boolean moveY = Math.abs (next.y - current.y) > CHARACTER_SPEED * 0.03;
		if (moveX){
			if (next.x > current.x){
				control.keyDPressed ();
			}
			else{
				control.keyAPressed ();
			}
			current.x += control.deltaX;
		}
		if (moveY){
			if (next.y > current.y){
				control.keyWPressed ();
			}
			else{
				control.keySPressed ();
			}
			current.y += control.deltaY;
		}
		if (!moveX && !moveY){
			if (iterator == path.size () - 1){ //персонаж дошел до конца
				path.clear ();
				control.movedByComputer = false;
				if (control.character.goToObject){
					control.character.goToObject = false;
					ObjectManager.getInstance ().addMessage (new ComeToMessage (control.character));
					control.character.state = State.abut;
				}
				else{
					control.deltaX += next.x - current.x;
					control.deltaY += next.y - current.y;
				}
			}
			else{
				nextIteration ();
			}
		}
	}
	
	public void setPath (ArrayList <Tile> path){
		control.movedByComputer = true;
		
		this.path = path;
		iterator = 0;
		nextIteration ();
	}
	
	public void updatePushControl (CharacterControl control){
		if (this.control != control){
			return;
		}
		
		switch (control.character.currentDirection){
		case forward:
			if (!control.movedByComputer && Gdx.input.isKeyPressed (Input.Keys.W)){
				control.movedByComputer = true;
				iterator = -1;
				current.x = control.character.getBodyX () + control.character.getBodyW () / 2;
				current.y = control.character.getBodyY () + control.character.getBodyH () / 2;
				next.x = current.x;
				next.y = current.y + GameObject.UNIT * GameObject.ANGLE;
				Level.getInstance ().moveBox (next.x, next.y, control.character.currentDirection);
			}
			break;
		case right:
			if (!control.movedByComputer && Gdx.input.isKeyPressed (Input.Keys.D)){
				control.movedByComputer = true;
				iterator = -1;
				current.x = control.character.getBodyX () + control.character.getBodyW () / 2;
				current.y = control.character.getBodyY () + control.character.getBodyH () / 2;
				next.x = current.x + GameObject.UNIT;
				next.y = current.y;
				Level.getInstance ().moveBox (next.x, next.y, control.character.currentDirection);
			}
			break;
		case back:
			if (!control.movedByComputer && Gdx.input.isKeyPressed (Input.Keys.S)){
				control.movedByComputer = true;
				iterator = -1;
				current.x = control.character.getBodyX () + control.character.getBodyW () / 2;
				current.y = control.character.getBodyY () + control.character.getBodyH () / 2;
				next.x = current.x;
				next.y = current.y - GameObject.UNIT * GameObject.ANGLE;
				Level.getInstance ().moveBox (next.x, next.y, control.character.currentDirection);
			}
			break;
		case left:
			if (!control.movedByComputer && Gdx.input.isKeyPressed (Input.Keys.A)){
				control.movedByComputer = true;
				iterator = -1;
				current.x = control.character.getBodyX () + control.character.getBodyW () / 2;
				current.y = control.character.getBodyY () + control.character.getBodyH () / 2;
				next.x = current.x - GameObject.UNIT;
				next.y = current.y;
				Level.getInstance ().moveBox (next.x, next.y, control.character.currentDirection);
			}
			break;
		}
	}
	
	@Override
	public void clear (){
		path = null;
	}
}