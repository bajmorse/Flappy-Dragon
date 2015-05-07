package framework.ui;

import android.opengl.GLSurfaceView;
import android.util.Log;

import framework.graphics.textures.text.TextAtlas;

/**
 * Created by Morsecode Gaming on 2015-03-19.
 */
public class Label {
    private int color;
    private int[] textIndices;
    private GLSurfaceView view;
    protected float x, y, width, height;
    protected String text;

    public Label(String text, float x, float y, int color, GLSurfaceView view) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.text = text;
        this.view = view;
        show();
    }

    public void show() {
        showText();
    }

    private void showText() {
        textIndices = TextAtlas.getInstance().addText(text, x, y, 1.25f, color);
    }

    public void hide() {
        hideText();
    }

    private void hideText() {
        if (textIndices != null) {
            for (int index : textIndices) {
                TextAtlas.getInstance().removeTexture(index);
            }
        }
        textIndices = null;
    }

    public void setText(String text) {
        if (this.text != null && this.text.equals(text)) {
            Log.w("BUTTON", "That text is the same as the text already set");
            return;
        }
        this.text = text;
        view.queueEvent(new Runnable() {
            @Override
            public void run() {
                hideText();
                showText();
            }
        });
    }
}
