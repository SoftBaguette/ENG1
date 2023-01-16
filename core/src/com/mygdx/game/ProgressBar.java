package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ProgressBar extends Actor{
    ShapeRenderer shapeRenderer;
    int progress;
    int x;
    int y;
    int width;
    int height;
    Texture back;
    Texture loading;

    public ProgressBar(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        progress = 0;
        shapeRenderer = new ShapeRenderer();
        this.setPosition(x, y);
        back = new Texture("ProgressBarBack.png");
        loading = new Texture("ProgressBarFull.png");
    }

    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        batch.draw(back, x,y, width,height);
        batch.draw(loading, x,y,(float) width * progress/100f,height);

        /*
        // Set the projection matrix of the shape renderer to the batch's projection matrix
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());

        // Begin drawing shapes
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw the background of the progress bar
        shapeRenderer.setColor(Color.GRAY);

        shapeRenderer.rect(getX(), getY(), width, height);
        // Draw the foreground of the progress bar (the part that represents the progress)
        shapeRenderer.setColor(Color.GREEN);
        float progressBarWidth = (float) width * progress / 100.0f;
        shapeRenderer.rect(getX(), getY(), progressBarWidth, height);

        // End drawing shapes
        shapeRenderer.end();*/
    }

    public void setProgress (int progress){
        this.progress = progress;
    }
}
