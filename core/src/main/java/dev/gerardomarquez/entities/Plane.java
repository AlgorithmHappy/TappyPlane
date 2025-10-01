package dev.gerardomarquez.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.gerardomarquez.utils.Constants;

/*
 * {@inheritDoc}
 */
public class Plane implements GraphicEntity {

    /*
     * Nombre del sprite
     */
    private String name;

    /*
     * Sprite que se va a pintar en el juego
     */
    private Sprite sprite;

    /*
     * Constructor que define el color y el frame del avion
     */
    public Plane(String color, Integer number) {

        this.name = Constants.SPRITE_NAME_PLANE +
            color.substring(Constants.FIRST_INDEX, Constants.SECOND_INDEX).toUpperCase() +
            color.substring(Constants.SECOND_INDEX).toLowerCase() +
            number.toString();
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    @Override
    public Sprite getSprite() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSprite'");
    }

}
