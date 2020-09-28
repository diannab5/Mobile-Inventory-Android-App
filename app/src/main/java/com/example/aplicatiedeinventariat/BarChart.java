package com.example.aplicatiedeinventariat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Collections;
import java.util.List;

public class BarChart extends View {
    private List<Float> valori;
    public BarChart(Context context, List<Float> valori) {
        super(context);
        this.valori=valori;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint instrumentDesenare = new Paint();
        instrumentDesenare.setTextSize(30);
        float val=0;
        for(int i=0;i<valori.size();i++){
            instrumentDesenare.setColor(Color.BLACK);
            canvas.drawRect(i*110,1600-(valori.get(i)*450/Collections.max(valori)),val+80,1600,instrumentDesenare);
            val=val+110;
            canvas.drawText("Rating"+(i+1),i*110,1620,instrumentDesenare);
        }
    }
}
