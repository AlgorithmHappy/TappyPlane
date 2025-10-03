package dev.gerardomarquez.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import dev.gerardomarquez.utils.Constants;
import dev.gerardomarquez.utils.Constants.GROUND_KIND;

/*
 * {@inheritDoc}
 */
public class Ground implements GraphicEntity{

    /*
     * Nombre del Ground, usado para identificar el sprite que usuara el ground.
     */
    private String name;

    /*
     * Sprite a utilizar
     */
    private Sprite sprite;

    /*
     * Constructor para inicializar el sprite
     * @param atlas Atlas para inicializar el sprite
     * @param groundKind Tipo de piso y techo para inicializar el sprite a utilizar
     */
    public Ground(TextureAtlas atlas, GROUND_KIND groundKind){
        this.name = Constants.SPRITE_NAME_GROUND + groundKind.getValue();
        this.sprite = atlas.createSprite(name);
        this.sprite.setY(Constants.ZERO);
    }

    /*
    * {@inheritDoc}
    */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        this.sprite.draw(spriteBatch);
    }

    /*
    * {@inheritDoc}
    */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * Metodo que cambia la posicion para abajo y arriba
     */
    public void topDown(){
        this.sprite.flip(Boolean.FALSE, Boolean.TRUE);
        if(this.sprite.getY() == Constants.ZERO)
            this.sprite.setY(Constants.VIRTUAL_HEIGHT - this.sprite.getHeight() );
        else
            this.sprite.setY(Constants.ZERO);
    }

    /*
     * Metodo que devuelve si el sprite es visible en la pantalla, para que solo se pinte techo y piso que si es visible
     * segun su posicion en la pantalla
     * @return boleano que indica si si es visible o no
     */
    public Boolean isVisible(){
        Float startPosition = this.sprite.getX();
        Float endPosition = this.sprite.getX() + this.sprite.getWidth();
        return endPosition > Constants.ZERO && startPosition < Constants.VIRTUAL_WIDTH;
    }

    /*
     * Metodo que mueve el techo o el piso al lado derecho de otro piso dependiendo la posicion recivida
     * @param positionX posicion en x del piso o techo a la que se le quiere poner del lado derecho
     */
    public void moveToNextRightGround(Float positionX){
        if(positionX != null){
            this.sprite.setX(positionX + this.sprite.getWidth() );
        }
    }

    /*
     * Metodo que devuelve la posicion en x del ground
     * @return posicion en x del groud
     */
    public Float getX(){
        return this.sprite.getX();
    }

    /*
     * Metodo que indica si el ground salio del lado izquierdo de la pantalla
     * @return boleando que indica con true que si salio
     */
    public Boolean isOutOfScreenLeft() {
        return (this.sprite.getX() + this.sprite.getWidth() ) < Constants.ZERO;
    }

    /*
     * Metodo para mover a la izquierda el ground
     * @param add Unidades en pixeles que se le restaran a la izquierda de la posicion actual
     */
    public void moveToLeft(Float add) {
        this.sprite.setX(this.sprite.getX() - add);
    }
}
