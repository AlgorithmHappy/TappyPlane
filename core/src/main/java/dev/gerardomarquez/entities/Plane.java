package dev.gerardomarquez.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import dev.gerardomarquez.utils.Constants;
import dev.gerardomarquez.utils.Constants.PLANE_COLOR;

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
     * @param atlas TextureAtlas para crear el sprite porque el nombre del atlas del plane
     * debe ser una combinacion de parametros para construir el nombre por completo
     */
    public Plane(TextureAtlas atlas, PLANE_COLOR color, Integer number) {
        this.name = Constants.SPRITE_NAME_PLANE +
            color.getValue() +
            number.toString();

        this.sprite = atlas.createSprite(name);
        this.sprite.setPosition(Constants.VIRTUAL_WIDTH / 6, Constants.VIRTUAL_HEIGHT / 2);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    /*
     * Aplicar la gravedad cada tiempo "delta"
     * @param y Posicion en el eje de la y al que se tendria que mover abajo cada tiempo "delta"
     * @param rotation Rotacion que tendria que tener el avion para que haga el efecto de caer en picada
     */
    public void applyGravity(Float y, Float rotation){
        Float currentPosition = this.sprite.getY();
        this.sprite.setY(currentPosition + y);
        this.sprite.setRotation(rotation);
    }
}
