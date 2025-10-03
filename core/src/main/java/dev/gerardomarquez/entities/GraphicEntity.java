package dev.gerardomarquez.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * Entidad del mundo del juego que tiene una representación gráfica, estos contendran
 * sprites para dibujar.
 */
public interface GraphicEntity {
    /*
     * Para dibujar la entidad en el juego.
     * @param spriteBatch El batch que pintará el sprite.
     */
    public void draw(SpriteBatch spriteBatch);

    /*
     * Obtiene el nombre de la entidad gráfica.
     * @return El nombre de la entidad gráfica.
     */
    public String getName();
}
