package dev.gerardomarquez.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import dev.gerardomarquez.utils.Constants;

/*
 * {@inheritDoc}
 */
public class Player implements GraphicEntity {

    /*
     * Nombre del jugado, usado para identificar el sprite que usuara el jugador.
     */
    private String name;

    /*
     * GraphicEntity actual de la animacion
     */
    private Plane currentPlane;

    /*
     * Animacion del jugador, del avion
     */
    private Animation<Plane> animation;

    /*
     * Duracion de cada frame de la animacion
     */
    private Float frameDuration;

    /*
     * Tiempo que ha transcurrido en total para el animation
     */
    private Float stateTime;

    /*
     * Velocidad vertical de caida del avion
     */
    private Float velocity;

    /*
     * Gravedad que se le va aplicar al avion
     */
    private Float gravity;

    /*
     * Grado de inclinacion que se le aplicara
     */
    private Float gradeRotation;

    /*
     * Fuerza que se aplicara en proporcion al tiempo sostenido del click
     */
    private Float liftPower;

    /*
     * Velocidad maxima que se puede lograr al dejar presionado el click
     */
    private Float maxLiftSpeed;

    /*
     * Constructor que indica que frames se van usar para los sprites que utilizara el
     * jugador
     * @param frames Array de frames de la animacion
     */
    public Player(Plane[] frames){
        for(Plane entity : frames){
            this.name += entity.getName() + Constants.PIPE;
        }
        
        this.frameDuration = Constants.FRAME_RATE_ANIMATION_PLAYER;
        this.animation = new Animation<>(frameDuration, frames);
        this.stateTime = 0f;
        this.velocity = 0f;
        this.gravity = -45f;
        this.gradeRotation = 2f;
        this.liftPower = 180f;
        this.maxLiftSpeed = 40f;
    }

    /*
    * {@inheritDoc}
    */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        Float delta = Gdx.graphics.getDeltaTime();
        this.stateTime += delta;
        currentPlane = animation.getKeyFrame(stateTime, Boolean.TRUE);
        gameplay(delta);
        applyGravity(delta);
        currentPlane.draw(spriteBatch);
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
        return this.currentPlane.getSprite();
    }

    /*
     * Metodo para aplicar gravedad para que el jugador se vaya callendo
     * @param delta Tiempo transcurrido entre cada frame para ver cuanto se tendra que mover
     * el sprite o grafico
     */
    private void applyGravity(Float delta) {
        this.velocity += delta * gravity;
        Float rotation = Math.min(Math.max(velocity * this.gradeRotation, -45), 45);
        for(Plane plane : this.animation.getKeyFrames() ){
            plane.applyGravity(this.velocity, rotation);
        }
    }

    private void gameplay(Float delta){
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) ){
            velocity += liftPower * delta;
            if(velocity > maxLiftSpeed){
                velocity = maxLiftSpeed;
            }
        }
    }
}
